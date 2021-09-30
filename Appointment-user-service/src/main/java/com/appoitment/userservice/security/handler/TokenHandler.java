package com.appoitment.userservice.security.handler;

import com.appoitment.userservice.domain.AppUser;
import com.appoitment.userservice.domain.Role;
import com.appoitment.userservice.service.entity.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
@Slf4j
@RequiredArgsConstructor
public class TokenHandler {

    private final JwtHandler jwtHandler;
    private final UserService userService;

    public String createAccessToken(HttpServletRequest request, String username, List<String> roles) {

        Algorithm algorithm = jwtHandler.getAlgorithm();

        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 60 * 1000))
                .withIssuer(request.getRequestURI())
                .withClaim("roles", roles)
                .sign(algorithm);
    }

    public String createRefreshToken(HttpServletRequest request, User user) {

        Algorithm algorithm = jwtHandler.getAlgorithm();

        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
                .withIssuer(request.getRequestURI())
                .sign(algorithm);
    }

    public void buildRefreshToken(String authorizationHeader, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String refreshToken = authorizationHeader.substring("Bearer ".length());
        DecodedJWT decodedJWT = jwtHandler.getDecodedJWT(refreshToken);

        String username = decodedJWT.getSubject();
        AppUser user = userService.get(username);

        List<String> roles = user.getRoles().stream().map(Role::getName).collect(Collectors.toList());
        String accessToken = createAccessToken(request, user.getUsername(), roles);

        makeResponse(response, accessToken, refreshToken);
    }

    public void makeResponse(HttpServletResponse response, String accessToken, String refreshToken) throws IOException {
        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", accessToken);
        tokens.put("refresh_token", refreshToken);
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), tokens);
    }
}
