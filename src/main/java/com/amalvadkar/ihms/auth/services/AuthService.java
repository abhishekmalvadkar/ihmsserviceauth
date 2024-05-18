package com.amalvadkar.ihms.auth.services;

import com.amalvadkar.ihms.auth.helpers.JwtTokenHelper;
import com.amalvadkar.ihms.auth.models.dto.JwtTokenCreateDto;
import com.amalvadkar.ihms.common.entities.UserEntity;
import com.amalvadkar.ihms.common.models.response.CustomResModel;
import com.amalvadkar.ihms.common.repositories.UserRepository;
import com.amalvadkar.ihms.common.exceptions.AppException;
import com.amalvadkar.ihms.auth.helpers.IdTokenHelper;
import com.amalvadkar.ihms.auth.models.dto.AuthTokenModel;
import com.amalvadkar.ihms.auth.models.response.SignInResModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

import static com.amalvadkar.ihms.auth.utils.AppConstants.ERROR_MSG_EMAIL_ID_MISSING_IN_SYSTEM;
import static com.amalvadkar.ihms.auth.utils.AppConstants.LOGGED_IN_SUCCESSFULLY_RESPONSE_MSG;
import static com.amalvadkar.ihms.auth.utils.AppConstants.RESPONSE_HEADER_AUTHORIZATION;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class AuthService {

    private final IdTokenHelper idTokenHelper;
    private final UserRepository userRepo;
    private final JwtTokenHelper jwtTokenHelper;

    @Transactional
    public ResponseEntity<CustomResModel> signIn(String authProvider, String token, String device) {
        AuthTokenModel authTokenModel = idTokenHelper.verifyIdToken(authProvider, token);
        UserEntity dbUserEntity = userRepo.
                findByEmailAndDeleteFlagIsFalseAndActiveIsTrue(authTokenModel.email());
        if (emailPresentInSystem(dbUserEntity)){
            SignInResModel signInResModel = prepareSignInResModel(dbUserEntity, authTokenModel);
            CustomResModel customResModel = CustomResModel.success(signInResModel, LOGGED_IN_SUCCESSFULLY_RESPONSE_MSG);
            JwtTokenCreateDto jwtTokenCreateDto = new JwtTokenCreateDto(dbUserEntity.getId(), dbUserEntity.getRoleEntity().getId(), device);
            return ResponseEntity.ok()
                    .header(RESPONSE_HEADER_AUTHORIZATION, jwtTokenHelper.generateToken(jwtTokenCreateDto))
                    .body(customResModel);
        } else {
            throw AppException.from(ERROR_MSG_EMAIL_ID_MISSING_IN_SYSTEM, UNAUTHORIZED);
        }
    }

    private boolean emailPresentInSystem(UserEntity dbUserEntity) {
        return nonNull(dbUserEntity);
    }

    private SignInResModel prepareSignInResModel(UserEntity dbUserEntity, AuthTokenModel authTokenModel) {
        SignInResModel signInResModel = new SignInResModel();
        if (firstTimeLogin(dbUserEntity)){
            signInResModel.setFirstLogin(true);
            Instant lastLoginTime = Instant.now();
            signInResModel.setLastLoginTime(lastLoginTime);
            dbUserEntity.setLastLoginTime(lastLoginTime);
            dbUserEntity.setAuthProvider(authTokenModel.authProvider());
        } else {
            signInResModel.setLastLoginTime(dbUserEntity.getLastLoginTime());
            dbUserEntity.setLastLoginTime(Instant.now());
        }
        dbUserEntity.setName(authTokenModel.name());
        dbUserEntity.setPhotoUrl(authTokenModel.photoUrl());
        userRepo.save(dbUserEntity);
        return signInResModel;
    }

    private boolean firstTimeLogin(UserEntity dbUserEntity) {
        return isNull(dbUserEntity.getLastLoginTime());
    }

}
