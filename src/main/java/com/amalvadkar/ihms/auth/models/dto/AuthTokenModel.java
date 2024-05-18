package com.amalvadkar.ihms.auth.models.dto;

public record AuthTokenModel(String email, String name, String photoUrl , String authProvider) {
}
