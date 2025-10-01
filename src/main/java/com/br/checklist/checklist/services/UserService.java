package com.br.checklist.checklist.services;

import com.br.checklist.checklist.dto.user.AuthenticationRequestDTO;
import com.br.checklist.checklist.dto.user.AuthenticationResponseDTO;
import com.br.checklist.checklist.dto.user.RegisterUserDto;
import com.br.checklist.checklist.dto.user.UserResponseDTO;
import com.br.checklist.checklist.exceptions.UnauthorizedUser;
import com.br.checklist.checklist.exceptions.UserAlreadyRegistered;
import com.br.checklist.checklist.infra.security.SecurityConfiguration;
import com.br.checklist.checklist.infra.security.TokenService;
import com.br.checklist.checklist.models.User;
import com.br.checklist.checklist.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private SecurityConfiguration securityConfiguration;


    public UserResponseDTO createUser(RegisterUserDto data) {
        if (userRepository.existsByUsername(data.userName()) || userRepository.existsByEmail(data.email())) {
            throw new UserAlreadyRegistered();// Retorna erro 400 se login ou e-mail já existir
        }
        var userEntity = new User(data);
        userEntity.setPassword(securityConfiguration.passwordEncoder().encode(data.password()));
        userRepository.save(userEntity);

        return new UserResponseDTO(userEntity);
    }

    public AuthenticationResponseDTO authenticate(final AuthenticationRequestDTO data) {

        // Authenticate the user
        var token = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var authentication = authenticationManager.authenticate(token);

        // Generate JWT access token
        String accessToken = tokenService.generateToken(((User) authentication.getPrincipal()).getUsername());

        // Fetch user and create refresh token
        User user = userRepository.findByUsername(data.username()).orElseThrow(() -> new UnauthorizedUser("Usário não autorizado"));

        String refreshToken = tokenService.generateRefreshToken(user.getUsername());

        return new AuthenticationResponseDTO(accessToken, refreshToken);
    }

    public AuthenticationResponseDTO refreshToken(String refreshToken) {
        String subject = tokenService.getSubject(refreshToken); // Valida o token JWT
        final var newAccessToken = tokenService.generateToken(subject);
        return new AuthenticationResponseDTO(newAccessToken, refreshToken);
    }

    public Page<UserResponseDTO> listUsers(Pageable pageable, User user) {
        return userRepository.findAll(pageable).map(UserResponseDTO::new);
    }

    public UserResponseDTO getCurrentUser(User user) {
        return new UserResponseDTO(user);
    }
}