package com.insurance.InsuranceApp.controller; // Updated package name

import com.insurance.InsuranceApp.payload.request.LoginRequest; // Updated import
import com.insurance.InsuranceApp.payload.request.RegisterRequest; // Updated import
import com.insurance.InsuranceApp.payload.request.PasswordResetRequest; // Updated import
import com.insurance.InsuranceApp.payload.request.VerifyResetTokenRequest; // Updated import
import com.insurance.InsuranceApp.payload.response.JwtResponse; // Updated import
import com.insurance.InsuranceApp.payload.response.MessageResponse; // Updated import
import com.insurance.InsuranceApp.services.AuthService; // Updated import
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
        JwtResponse jwtResponse = authService.authenticateUser(loginRequest);
        return ResponseEntity.ok(jwtResponse);
    }

    @PostMapping("/register")
    public ResponseEntity<MessageResponse> registerUser(@RequestBody RegisterRequest registerRequest) {
        MessageResponse messageResponse = authService.registerUser(registerRequest);
        if (messageResponse.getMessage().startsWith("Error:")) {
            return ResponseEntity.badRequest().body(messageResponse);
        }
        return ResponseEntity.ok(messageResponse);
    }

    @PostMapping("/request-password-reset")
    public ResponseEntity<MessageResponse> requestPasswordReset(@RequestBody PasswordResetRequest passwordResetRequest) {
        MessageResponse messageResponse = authService.requestPasswordReset(passwordResetRequest);
        if (messageResponse.getMessage().startsWith("Error sending")) {
             return ResponseEntity.status(500).body(messageResponse);
        }
        return ResponseEntity.ok(messageResponse);
    }

    @PostMapping("/reset-password")
    public ResponseEntity<MessageResponse> resetPassword(@RequestBody VerifyResetTokenRequest verifyResetTokenRequest) {
        MessageResponse messageResponse = authService.resetPassword(verifyResetTokenRequest);
        if (messageResponse.getMessage().startsWith("Invalid") || messageResponse.getMessage().startsWith("Reset token has expired")) {
            return ResponseEntity.badRequest().body(messageResponse);
        }
        return ResponseEntity.ok(messageResponse);
    }
}