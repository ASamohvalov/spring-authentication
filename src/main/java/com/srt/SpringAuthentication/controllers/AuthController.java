package com.srt.SpringAuthentication.controllers;

import com.srt.SpringAuthentication.exceptions.UserAlreadyExistException;
import com.srt.SpringAuthentication.models.DTO.RegisterRequest;
import com.srt.SpringAuthentication.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        try {
            String message = authService.register(request);
            return ResponseEntity.ok(message);
        } catch (UserAlreadyExistException | IllegalArgumentException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/is-authenticated")
    public ResponseEntity<Boolean> isAuthenticated() {
        return ResponseEntity.ok(true);
    }
}
