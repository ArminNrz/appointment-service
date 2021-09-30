package com.appoitment.userservice.controller.user.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class GrantRoleDTO {
    @NotEmpty
    private String username;
    @NotEmpty
    private String roleName;
}
