package com.amalvadkar.ihms.auth.models.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

public record MenuDto(Long id, String name, String route, BigDecimal displayOrder, List<MenuDto> children) {
}
