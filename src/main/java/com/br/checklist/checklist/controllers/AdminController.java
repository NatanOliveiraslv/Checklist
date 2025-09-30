package com.br.checklist.checklist.controllers;

import com.br.checklist.checklist.dto.user.RegisterUserDto;
import com.br.checklist.checklist.models.User;
import com.br.checklist.checklist.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RequestMapping("/admins")
@RestController
public class AdminController {
    @Autowired
    private UserService userService;

    @PostMapping
    @PreAuthorize("hasRole('OWNER')")
    public ResponseEntity<User> createAdministrator(@RequestBody RegisterUserDto registerUserDto) {
        User createdAdmin = userService.createAdministrator(registerUserDto);

        return ResponseEntity.ok(createdAdmin);
    }
}