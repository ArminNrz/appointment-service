package com.appoitment.userservice.controller.user.dto;

import com.appoitment.userservice.domain.AppUser;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserDTO {

    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String username;

    public UserDTO(AppUser user) {
        this.id = user.getId();
        this.name = user.getName();
        this.username = user.getUsername();
    }
}
