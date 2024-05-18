package com.amalvadkar.ihms.auth.models.response;

import lombok.Data;

import java.time.Instant;

@Data
public class SignInResModel {

    private boolean isFirstLogin;

    private Instant lastLoginTime;
}
