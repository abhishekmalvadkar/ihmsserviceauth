package com.amalvadkar.ihms.auth.models.response;

import com.amalvadkar.ihms.auth.MenuNode;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class SignInResModel {

    private boolean isFirstLogin;

    private Instant lastLoginTime;

    private List<MenuNode> menus;


}
