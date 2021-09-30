package com.appoitment.userservice.security.handler;

import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class SecurityUtility {

    private final JwtHandler jwtHandler;

    public String getAccountId(String token) {

        token = token.substring("Bearer ".length());

        DecodedJWT decodedJWT = jwtHandler.getDecodedJWT(token);

        return decodedJWT.getSubject();
    }
}
