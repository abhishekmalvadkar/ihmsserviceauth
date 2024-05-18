package com.amalvadkar.ihms.auth.models.dto;

public record JwtTokenCreateDto(Long userId, String roleId, String device) {
}
