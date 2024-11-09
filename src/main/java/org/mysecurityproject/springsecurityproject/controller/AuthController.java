package org.mysecurityproject.springsecurityproject.controller;

import lombok.RequiredArgsConstructor;
import org.mysecurityproject.springsecurityproject.model.AuthenticationRequest;
import org.mysecurityproject.springsecurityproject.model.AuthenticationResponse;
import org.mysecurityproject.springsecurityproject.model.ResisterRequest;
import org.mysecurityproject.springsecurityproject.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/auth")
public class AuthController  {

    private final AuthenticationService authenticationService;

    @PostMapping(value = "/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody ResisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
