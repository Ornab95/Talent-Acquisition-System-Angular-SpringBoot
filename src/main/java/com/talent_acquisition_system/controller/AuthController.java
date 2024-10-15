package com.talent_acquisition_system.controller;

import com.talent_acquisition_system.model.User;
import com.talent_acquisition_system.model.Hr;
import com.talent_acquisition_system.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/signup/user")
    public ResponseEntity<User> signupUser(@RequestBody User user) {
        User savedUser = authService.registerUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/signup/hr")
    public ResponseEntity<Hr> signupHr(@RequestBody Hr hr) {
        Hr savedHr = authService.registerHr(hr);
        return ResponseEntity.ok(savedHr);
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestParam String email, @RequestParam String password) {
        Hr hr = authService.signinHr(email, password);
        if (hr != null) {
            return ResponseEntity.ok(hr);
        }

        User user = authService.signinUser(email, password);
        if (user != null) {
            return ResponseEntity.ok(user);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials.");
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleException(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
