package com.amalvadkar.ihms.auth;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "ihms")
@Validated
public record ApplicationProperties(
        @NotNull(message = "jwtExpiryTimeInMs property should not be null or empty")
        Integer jwtExpiryTimeInMs,

        @NotEmpty(message = "jwtSecretKey property should not be null or empty")
        String jwtSecretKey

) {
}
