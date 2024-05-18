package com.amalvadkar.ihms.auth.controllers.rest;

import com.amalvadkar.ihms.auth.utils.AppConstants;
import com.amalvadkar.ihms.common.models.response.CustomResModel;
import com.amalvadkar.ihms.auth.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.amalvadkar.ihms.auth.utils.AppConstants.REQUEST_HEADER_AUTH_PROVIDER;
import static com.amalvadkar.ihms.auth.utils.AppConstants.REQUEST_HEADER_DEVICE;
import static com.amalvadkar.ihms.auth.utils.AppConstants.REQUEST_HEADER_TOKEN;

@RestController
@RequestMapping(value = "/api/auth")
@RequiredArgsConstructor
public class AuthRestController {

    private static final String ENDPOINT_SIGN_IN = "/signin";

    private final AuthService authService;

    @PostMapping(ENDPOINT_SIGN_IN)
    public ResponseEntity<CustomResModel> signIn(@RequestHeader(REQUEST_HEADER_AUTH_PROVIDER) String authProvider,
                                                 @RequestHeader(REQUEST_HEADER_TOKEN) String token,
                                                 @RequestHeader(REQUEST_HEADER_DEVICE) String device) {
        return authService.signIn(authProvider , token , device);
    }

}
