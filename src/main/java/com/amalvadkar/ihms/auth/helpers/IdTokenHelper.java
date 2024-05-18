package com.amalvadkar.ihms.auth.helpers;

import com.amalvadkar.ihms.common.exceptions.AppException;
import com.amalvadkar.ihms.auth.models.dto.AuthTokenModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static com.amalvadkar.ihms.auth.utils.AppConstants.AUTH_PROVIDER_GOOGLE;
import static com.amalvadkar.ihms.auth.utils.AppConstants.ERROR_MSG_INVALID_AUTH_PROVIDER;
import static com.amalvadkar.ihms.auth.utils.AppConstants.ERROR_MSG_INVALID_TOKEN;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@Component
@RequiredArgsConstructor
@Slf4j
public class IdTokenHelper {

    private final FirebaseAuth firebaseAuth;

    public AuthTokenModel verifyIdToken(String authProvider, String token){
        if (googleIs(authProvider)) {
            try {
                FirebaseToken idToken = firebaseAuth.verifyIdToken(token);
                log.debug("Id token verified successfully");
                return new AuthTokenModel(idToken.getEmail() , idToken.getName() , idToken.getPicture() , authProvider);
            } catch (FirebaseAuthException e) {
               throw AppException.from(ERROR_MSG_INVALID_TOKEN, UNAUTHORIZED);
            }
        } else {
            throw AppException.from(ERROR_MSG_INVALID_AUTH_PROVIDER, BAD_REQUEST);
        }
    }

    private static boolean googleIs(String authProvider) {
        return AUTH_PROVIDER_GOOGLE.equals(authProvider);
    }

}
