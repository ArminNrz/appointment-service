package com.appoitment.userservice.security.handler;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtHandler {

    private final Environment environment;

    public DecodedJWT getDecodedJWT(String token) {
        Algorithm algorithm = getAlgorithm();
        JWTVerifier verifier = JWT.require(algorithm).build();
        return verifier.verify(token);
    }

    public Algorithm getAlgorithm() {
        String secret = environment.getProperty("userservice.secret");
        log.debug("secret: {}", secret);
        assert secret != null;
        return Algorithm.HMAC256(secret.getBytes());
    }
}
