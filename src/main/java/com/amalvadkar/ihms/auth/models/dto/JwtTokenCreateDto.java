package com.amalvadkar.ihms.auth.models.dto;

public record JwtTokenCreateDto(Long userId, Long roleId, String device) {
}
