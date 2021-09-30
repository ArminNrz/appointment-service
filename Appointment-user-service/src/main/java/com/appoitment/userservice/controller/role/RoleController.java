package com.appoitment.userservice.controller.role;

import com.appoitment.userservice.domain.Role;
import com.appoitment.userservice.controller.role.dto.RoleDTO;
import com.appoitment.userservice.service.entity.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/roles")
@Slf4j
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @PostMapping("/create")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN')")
    public ResponseEntity<Role> create(@Valid @RequestBody RoleDTO dto) {

        log.info("Request to create role: {}", dto);
        Role role = roleService.saveOrUpdate(new Role(null, dto.getName()));

        return ResponseEntity.created(URI.create("/api/role/create")).body(role);
    }

    @PostMapping("/update")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN')")
    public ResponseEntity<Role> update(@Valid @RequestBody RoleDTO dto) {

        log.info("Request to create role: {}", dto);
        Role role = roleService.saveOrUpdate(new Role(dto.getId(), dto.getName()));

        return ResponseEntity.ok().body(role);
    }
}
