package com.br.checklist.checklist.controllers;

import com.br.checklist.checklist.dto.user.UserResponseDTO;
import com.br.checklist.checklist.models.User;
import com.br.checklist.checklist.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/users")
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getCurrentUser(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(userService.getCurrentUser(user));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'OWNER')")
    public ResponseEntity<Page<UserResponseDTO>> allUsers(@PageableDefault(page = 0, size = 10, sort = "id") Pageable pageable, @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(userService.listUsers(pageable, user));
    }
}
