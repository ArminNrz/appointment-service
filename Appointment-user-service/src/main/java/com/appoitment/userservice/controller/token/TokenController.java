package com.appoitment.userservice.controller.token;

import com.appoitment.userservice.security.SecurityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/api/token")
@Slf4j
@RequiredArgsConstructor
public class TokenController {

    private final SecurityService securityService;

    @GetMapping("/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {

        log.info("Request to refresh token");

        String authorizationHeader = request.getHeader(AUTHORIZATION);

        securityService.refreshToken(authorizationHeader, request, response);
    }
}
