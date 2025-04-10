package com.demo.black_list_check.service;

import com.demo.black_list_check.dto.req.AuthRequest;
import com.demo.black_list_check.entity.Role;
import com.demo.black_list_check.entity.User;
import com.demo.black_list_check.repository.RoleRepository;
import com.demo.black_list_check.repository.UserRepository;
import com.demo.black_list_check.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public String register(AuthRequest authRequest) {
        if (userRepository.findByUsername(authRequest.getUsername()) != null) {
            return "Username is already in use";
        }
        Role userRole = roleRepository.findByRoleName("USER");
        if (userRole == null) {
            userRole = new Role("USER");
            roleRepository.save(userRole);
        }
        User user = new User(
                authRequest.getUsername(),
                passwordEncoder.encode(authRequest.getPassword()),
                authRequest.getEmail(),
                userRole
        );
        User userRegister = userRepository.save(user);
        return "User successfully registered";
    }

    public Map<String, String> login(AuthRequest authRequest) {
        User existingUser = userRepository.findByUsername(authRequest.getUsername());
        if (existingUser == null || !passwordEncoder.matches(authRequest.getPassword(), existingUser.getPassword())) {
            return null;
        }

        String accessToken = JwtUtil.generateAccessToken(authRequest.getUsername());
        String refreshToken = JwtUtil.generateRefreshToken();
        existingUser.setRefreshToken(refreshToken);
        userRepository.save(existingUser);

        Map<String, String> token = new HashMap<>();
        token.put("access_token", accessToken);
        token.put("refresh_token", refreshToken);
        return token;
    }

    public String refresh(String refreshToken) {
        User user = userRepository.findByRefreshToken(refreshToken);
        if (user == null) {
            return null;
        }
        return JwtUtil.generateAccessToken(user.getUsername());
    }

    public String logout(String refreshToken) {
        User user = userRepository.findByRefreshToken(refreshToken);
        if (user != null) {
            user.setRefreshToken(null);
            userRepository.save(user);
        }
        return "Logout successful";
    }

    public String getProtectedContent() {
        return "This is a protected endpoint";
    }
}
