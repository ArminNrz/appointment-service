package com.appoitment.userservice.service.higLevel.handler;

import com.appoitment.userservice.domain.AppUser;
import com.appoitment.userservice.security.handler.SecurityUtility;
import com.appoitment.userservice.service.entity.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class AppointmentsUtility {

    private final SecurityUtility securityUtility;
    private final UserService userService;

    public AppUser extractUserFromToken(String token) {

        String accountId = securityUtility.getAccountId(token);

        return userService.get(accountId);
    }

    public AppUser getUserByUsername(String username) {

        return userService.get(username);
    }
}
