package com.appoitment.userservice.controller.role.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RoleDTO {
    private Long id;
    @NotEmpty
    private String name;
}
