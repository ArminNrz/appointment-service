package com.appoitment.userservice.controller.user;

import com.appoitment.userservice.domain.AppUser;
import com.appoitment.userservice.controller.user.dto.CreateUserDTO;
import com.appoitment.userservice.controller.user.dto.GrantRoleDTO;
import com.appoitment.userservice.controller.user.dto.UserDTO;
import com.appoitment.userservice.model.UserModel;
import com.appoitment.userservice.service.entity.UserService;
import com.appoitment.userservice.service.higLevel.AppointmentManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    private final AppointmentManagementService appointmentManagementService;

    @PostMapping("/create")
    public ResponseEntity<UserDTO> create(@Valid @RequestBody CreateUserDTO dto) {

        log.info("Request to create user: {}", dto);

        UserDTO userDTO = userService.create(new AppUser(dto));

        return ResponseEntity.created(URI.create("/api/users/create")).body(userDTO);
    }

    @PostMapping("/grant-role")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN')")
    public ResponseEntity<?> grantRole(@Valid @RequestBody GrantRoleDTO dto) {

        log.info("Request to grant role: {}, to username: {}", dto.getRoleName(), dto.getUsername());

        userService.grantRole(dto.getUsername(), dto.getRoleName());

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<UserModel> get(@PathVariable Long id) {

        log.info("Request to get user with id: {}", id);

        return ResponseEntity.ok().body(userService.get(id));
    }

    @GetMapping("/get-all")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN')")
    public ResponseEntity<List<UserModel>> getAll(@RequestParam("page") int page, @RequestParam("size") int size) {

        log.info("Request to get all users with page: {}, size: {}", page, size);

        return ResponseEntity.ok().body(userService.getAll(page, size));
    }

    @PutMapping("/add-super-user")
    @PreAuthorize("hasAnyRole('ROLE_SERVICE_ADMIN')")
    public ResponseEntity<UserModel> addSuperUser(@RequestParam("accountId") String accountId, @RequestHeader("Authorization") String token) {

        log.info("Request to addSuper to accountId: {}", accountId);

        return ResponseEntity.ok().body(userService.addSuperUser(accountId, token));
    }

    @PostMapping("/job-receiver")
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    public ResponseEntity<?> registerJobReceiver(@RequestHeader("Authorization") String token) {

        log.info("Request to register job receiver");

        appointmentManagementService.registerJobReceiver(token);

        return ResponseEntity.created(URI.create("/users/job-receiver")).build();
    }
}
