package com.appoitment.userservice.security.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthorizationData {
    private String username;
    private String[] roles;
}
