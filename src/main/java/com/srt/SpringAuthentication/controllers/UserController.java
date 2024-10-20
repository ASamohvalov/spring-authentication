package com.srt.SpringAuthentication.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.srt.SpringAuthentication.models.User;
import com.srt.SpringAuthentication.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<User> me() {
        return ResponseEntity.ok(userService.getUser());
    }

    @DeleteMapping("/delete_user")
    public ResponseEntity<String> delete() {
        String message = userService.deleteUser();
        return ResponseEntity.ok(message);
    }

    @PatchMapping("/change_first_name")
    public ResponseEntity<String> changeFirstName(@RequestBody JsonNode newFirstNameJson) {
        String message = userService.changeFirstName(newFirstNameJson.get("firstName").asText());
        return ResponseEntity.ok(message);
    }

    @PatchMapping("/change_last_name")
    public ResponseEntity<String> changeLastName(@RequestBody JsonNode newLastName) {
        String message = userService.changeLastName(newLastName.get("lastName").asText());
        return ResponseEntity.ok(message);
    }
}
