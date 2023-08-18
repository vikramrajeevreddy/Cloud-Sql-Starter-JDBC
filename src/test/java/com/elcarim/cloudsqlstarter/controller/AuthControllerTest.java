package com.elcarim.cloudsqlstarter.controller;

import com.elcarim.cloudsqlstarter.model.AuthenticationResponse;
import com.elcarim.cloudsqlstarter.model.LoginRequest;
import com.elcarim.cloudsqlstarter.model.User;
import com.elcarim.cloudsqlstarter.service.UserService;
import com.elcarim.cloudsqlstarter.util.JwtUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@MockitoSettings(strictness = Strictness.LENIENT)
    public class AuthControllerTest {

        @InjectMocks
        private AuthController authController;

        @Mock
        private UserService userService;
//
//        @Mock
//        private JwtUtil jwtUtil;
//
//        @Mock
//        private AuthenticationManager authenticationManager;

        // Other test setup and methods...
//        @Test
//        public void testLoginUser_Success() {
//            // Arrange
//            String email = "test@example.com";
//            String password = "testPassword";
//            String token = "testToken";
//            String refreshToken = "testRefreshToken";
//
//            LoginRequest loginRequest;
//            loginRequest = new LoginRequest(email, password);
//
//            User user = new User();
//            user.setEmail(email);
//            user.setPassword(password);
//
//            Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
//
//            when(userService.getUserByEmail(email)).thenReturn(user);
//            when(jwtUtil.generateToken(email)).thenReturn(token);
//            when(jwtUtil.createRefreshToken(email)).thenReturn(refreshToken);
//            when(authenticationManager.authenticate(any(Authentication.class))).thenReturn(authentication);
//
//            // Act
//            ResponseEntity<?> response = authController.loginUser(loginRequest);
//
//            // Assert
//            assertNotEquals(HttpStatus.OK, response.getStatusCode());
//            assertFalse(response.getBody() instanceof AuthenticationResponse);
//
//            AuthenticationResponse responseBody = (AuthenticationResponse) response.getBody();
//            assertEquals(token, responseBody.getJwt());
//            assertEquals(refreshToken, responseBody.getRefreshToken());
//        }

        @Test
        public void testLoginUser_UserNotFound() {
            // Arrange
            String email = "test@example.com";
            String password = "testPassword";

            LoginRequest loginRequest = new LoginRequest(email, password);

            when(userService.getUserByEmail(email)).thenReturn(null);

            // Act
            ResponseEntity<?> response = authController.loginUser(loginRequest);

            // Assert
            assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
            assertEquals("Authentication failed", response.getBody());
        }

    }

