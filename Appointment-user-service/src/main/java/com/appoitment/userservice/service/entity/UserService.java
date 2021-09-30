package com.appoitment.userservice.service.entity;

import com.appoitment.userservice.controller.user.dto.UserDTO;
import com.appoitment.userservice.domain.AppUser;
import com.appoitment.userservice.domain.Role;
import com.appoitment.userservice.model.UserModel;
import com.appoitment.userservice.repository.UserRepository;
import com.appoitment.userservice.security.handler.SecurityUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository repository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final SecurityUtility securityUtility;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AppUser user = get(username);

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getName())));

        return new User(user.getUsername(), user.getPassword(), authorities);
    }

    public UserDTO create(AppUser user) {
        log.debug("Try to save new user: {} to the database", user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        grantUserRole(user);
        user = repository.save(user);
        log.info("Saved user with Id: {}", user.getId());

        return new UserDTO(user);
    }

    private void grantUserRole(AppUser user) {

        Role userRole = roleService.get("ROLE_USER");
        user.getRoles().add(userRole);

        log.debug("Grant role user to user: {}", user);
    }

    public void grantRole(String username, String roleName) {
        log.debug("Try to grantRole, roleName: {}, to username: {}", roleName, username);

        Role role = roleService.get(roleName);
        AppUser user = get(username);

        user.getRoles().add(role);
        log.info("Add roleName: {}, to username: {} successfully", roleName, username);
    }

    public AppUser get(String username) {
        log.debug("Try to find user with username: {}", username);
        Optional<AppUser> userOptional = repository.findByUsername(username);

        if (userOptional.isEmpty())
            throw Problem.valueOf(Status.BAD_REQUEST, "User with this username not found");

        AppUser foundUser = userOptional.get();
        log.info("Found user: {}, with username: {}", foundUser, username);

        return foundUser;
    }

    public UserModel get(Long id) {
        log.debug("get, id: {}", id);

        Optional<AppUser> userOptional = repository.findById(id);

        if (userOptional.isEmpty())
            throw Problem.valueOf(Status.NOT_FOUND, "User with this id not exist");

        AppUser user = userOptional.get();
        log.debug("foundUser: {}", user);

        return new UserModel(user);
    }

    public List<UserModel> getAll(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<AppUser> appUsersPage = repository.findAll(pageable);

        if (!appUsersPage.hasContent())
            throw Problem.valueOf(Status.NO_CONTENT, "No user exist");

        List<UserModel> returnValue = new ArrayList<>();

        appUsersPage.stream().forEach(user -> {
            UserModel model = new UserModel(user);
            returnValue.add(model);
        });

        return returnValue;
    }

    public UserModel addSuperUser(String username, String token) {

        log.debug("addSuperUser, username: {}", username);

        /*
        find username of user that token belong to it
         */
        String superUsername = securityUtility.getAccountId(token);

        /*
        check the user that send token is exist
         */
        this.get(superUsername);


        /*
        find user that want to set superUser for it
         */
        AppUser user = this.get(username);
        user.setSuperUsername(superUsername);

        log.debug("try to update user: {}", user);
        repository.save(user);
        log.info("Updated user: {}", user);

        UserModel model = new UserModel(user);
        log.debug("UserModel: {}", model);

        return model;
    }
}
