package com.yansen.controllers;

import com.yansen.dtos.request.LoginRequest;
import com.yansen.dtos.request.SignupRequest;
import com.yansen.dtos.responses.ApiResponse;
import com.yansen.dtos.responses.LoginResponse;
import com.yansen.exceptions.ValidationErrorException;
import com.yansen.securities.config.JwtTokenProvider;
import com.yansen.securities.entity.Role;
import com.yansen.securities.entity.RoleName;
import com.yansen.securities.entity.User;
import com.yansen.securities.repositories.RoleRepository;
import com.yansen.securities.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.security.Principal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/v2/auth")
public class LoginAuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getPhone(),
                        loginRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        Map<String, String> res = new HashMap<>();
        res.put("token", jwt);
        LoginResponse<?> loginResponse = new LoginResponse<>();
        loginResponse.setData(res);
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getPhone())) {
            return new ResponseEntity(new ApiResponse(false, "Phone is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }
        // Creating user's account
        User user = new User();
        user.setName(signUpRequest.getName());
        user.setUsername(signUpRequest.getPhone());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new ValidationErrorException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }

    @GetMapping("/info")
    public ResponseEntity<?> getInfo(Principal principal) {
        Map<String, String> res = new HashMap<>();
        res.put("phone", principal.getName());
        LoginResponse<?> loginResponse = new LoginResponse<>();
        loginResponse.setData(res);
        return ResponseEntity.ok(loginResponse);
    }
}
