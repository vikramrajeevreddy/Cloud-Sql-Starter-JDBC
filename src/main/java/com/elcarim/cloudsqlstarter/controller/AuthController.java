package com.elcarim.cloudsqlstarter.controller;

import com.elcarim.cloudsqlstarter.model.AuthenticationResponse;
import com.elcarim.cloudsqlstarter.model.LoginRequest;
import com.elcarim.cloudsqlstarter.model.User;
import com.elcarim.cloudsqlstarter.service.CustomUserDetailsService;
import com.elcarim.cloudsqlstarter.service.UserService;
import com.elcarim.cloudsqlstarter.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("http://localhost:3000")
public class AuthController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;

//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody User user) {
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
//            );
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Invalid email or password");
//        }
//
//        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
//        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
//
//        //return ResponseEntity.ok(new AuthenticationResponse(jwt));
//        return ResponseEntity.ok(jwt);
//
//    }
@PostMapping("/login")
public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
    try {
        System.out.println(loginRequest);
        // Authenticating user
        //Authentication authentication = authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        User user = userService.getUserByEmail(loginRequest.getEmail());
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        if (!loginRequest.getPassword().matches(user.getPassword())) {
            throw new BadCredentialsException("Incorrect password");
        }

        // Generate JWT token
        final String token = jwtUtil.generateToken(loginRequest.getEmail());
        // Generate Refresh token
        final String refreshToken = jwtUtil.createRefreshToken(loginRequest.getEmail());

        // Return token (You can include refresh token and expiry details here)
        return ResponseEntity.ok(new AuthenticationResponse(token, refreshToken));
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
    }
}

    private Authentication authenticate(String email, String password) {
        // Code to authenticate the user with the provided email and password
        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
    }
}
