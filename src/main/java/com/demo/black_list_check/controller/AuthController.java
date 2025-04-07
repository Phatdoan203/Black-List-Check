package com.demo.black_list_check.controller;

import com.demo.black_list_check.dto.req.AuthRequest;
import com.demo.black_list_check.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody AuthRequest authRequest) {
        String result = authService.register(authRequest);
        if (result.contains("already exists")) {
            return ResponseEntity.badRequest().body(result);
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthRequest authRequest) {
        Map<String, String> tokens = authService.login(authRequest);
        if (tokens == null) {
            return ResponseEntity.badRequest().body("Invalid credentials");
        }
        return ResponseEntity.ok(tokens);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");
        String accessToken = authService.refresh(refreshToken);
        if (accessToken == null) {
            return ResponseEntity.badRequest().body("Invalid refresh token");
        }
        return ResponseEntity.ok(Map.of("accessToken", accessToken));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");
        String result = authService.logout(refreshToken);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/protected")
    public ResponseEntity<?> protectedEndpoint() {
        String content = authService.getProtectedContent();
        return ResponseEntity.ok(content);
    }
}
