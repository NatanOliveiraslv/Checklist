package com.br.checklist.checklist.services;

import com.br.checklist.checklist.dto.user.RegisterUserDto;
import com.br.checklist.checklist.enums.RoleEnum;
import com.br.checklist.checklist.models.Role;
import com.br.checklist.checklist.models.User;
import com.br.checklist.checklist.repositories.RoleRepository;
import com.br.checklist.checklist.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> allUsers() {
        List<User> users = new ArrayList<>();

        userRepository.findAll().forEach(users::add);

        return users;
    }

    public User createAdministrator(RegisterUserDto input) {
        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.ADMIN);

        if (optionalRole.isEmpty()) {
            return null;
        }

        var user = new User();
        user.setFirstName(input.firstName());
        user.setEmail(input.email());
        user.setPassword(passwordEncoder.encode(input.password()));
        user.setRole(optionalRole.get());

        return userRepository.save(user);
    }
}