package com.appoitment.userservice.model;

import com.appoitment.userservice.domain.AppUser;
import com.appoitment.userservice.domain.Role;
import lombok.Data;

import java.util.Collection;

@Data
public class UserModel {

    private Long id;
    private String name;
    private String username;
    private Collection<Role> roles;
    private String superUserAccountId;

    public UserModel(AppUser user) {
        this.id = user.getId();
        this.name = user.getName();
        this.username = user.getUsername();
        this.roles = user.getRoles();
        this.superUserAccountId = user.getSuperUsername() == null ? null : user.getSuperUsername();
    }
}
