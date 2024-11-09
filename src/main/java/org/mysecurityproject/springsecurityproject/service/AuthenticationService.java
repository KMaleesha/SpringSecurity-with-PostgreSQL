package org.mysecurityproject.springsecurityproject.service;

import lombok.RequiredArgsConstructor;
import org.mysecurityproject.springsecurityproject.config.JwtService;
import org.mysecurityproject.springsecurityproject.enums.Role;
import org.mysecurityproject.springsecurityproject.model.AuthenticationRequest;
import org.mysecurityproject.springsecurityproject.model.AuthenticationResponse;
import org.mysecurityproject.springsecurityproject.model.ResisterRequest;
import org.mysecurityproject.springsecurityproject.model.User;
import org.mysecurityproject.springsecurityproject.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(ResisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstname())
                .lastName(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
         var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
