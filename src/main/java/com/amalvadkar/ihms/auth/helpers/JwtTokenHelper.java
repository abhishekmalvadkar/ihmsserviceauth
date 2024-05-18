package com.amalvadkar.ihms.auth.helpers;

import com.amalvadkar.ihms.auth.ApplicationProperties;
import com.amalvadkar.ihms.auth.models.dto.JwtTokenCreateDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenHelper {

    private static final String JWT_CLAIM_KEY_FOR_ROLE_ID = "as";
    private static final String JWT_CLAIMS_KEY_FOR_DEVICE = "device";
    private final ApplicationProperties appProps;

    public String generateToken(JwtTokenCreateDto jwtTokenCreateDto) {
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setSubject(String.valueOf(jwtTokenCreateDto.userId()))
                .claim(JWT_CLAIM_KEY_FOR_ROLE_ID, jwtTokenCreateDto.roleId())
                .claim(JWT_CLAIMS_KEY_FOR_DEVICE, jwtTokenCreateDto.device())
                .setExpiration(new Date(System.currentTimeMillis() + appProps.jwtExpiryTimeInMs()))
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encode(appProps.jwtSecretKey().getBytes()))
                .compact();
    }


}
