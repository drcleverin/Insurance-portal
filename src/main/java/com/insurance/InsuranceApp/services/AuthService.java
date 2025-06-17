package com.insurance.InsuranceApp.services;

import com.insurance.InsuranceApp.model.PasswordResetToken;
import com.insurance.InsuranceApp.model.Role;
import com.insurance.InsuranceApp.model.User;
import com.insurance.InsuranceApp.payload.request.LoginRequest;
import com.insurance.InsuranceApp.payload.request.RegisterRequest;
import com.insurance.InsuranceApp.payload.request.PasswordResetRequest;
import com.insurance.InsuranceApp.payload.request.VerifyResetTokenRequest;
import com.insurance.InsuranceApp.payload.response.JwtResponse;
import com.insurance.InsuranceApp.payload.response.MessageResponse;
import com.insurance.InsuranceApp.repository.PasswordResetTokenRepository;
import com.insurance.InsuranceApp.repository.RoleRepository;
import com.insurance.InsuranceApp.repository.UserRepository;
import com.insurance.InsuranceApp.security.jwt.JwtUtil;

import jakarta.transaction.Transactional; // Use this or org.springframework.transaction.annotation.Transactional

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;
    private final EmailService emailService;
    private final PasswordResetTokenRepository passwordResetTokenRepository;

    public AuthService(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder, JwtUtil jwtUtil, EmailService emailService, PasswordResetTokenRepository passwordResetTokenRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtUtil = jwtUtil;
        this.emailService = emailService;
        this.passwordResetTokenRepository = passwordResetTokenRepository;
    }

    public JwtResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateJwtToken(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("Error: User not found."));

        user.setLastLogin(new Timestamp(System.currentTimeMillis()));
        userRepository.save(user);

        String role = user.getRole().getName().toUpperCase();
        return new JwtResponse(jwt, "Bearer", user.getId(), user.getUsername(), user.getEmail(), role);
    }

    public MessageResponse registerUser(RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            return new MessageResponse("Error: Username is already taken!");
        }

        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            return new MessageResponse("Error: Email is already in use!");
        }

        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(encoder.encode(registerRequest.getPassword()));
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));

        Optional<Role> roleOptional = roleRepository.findByName(registerRequest.getRole().toLowerCase());

        if (roleOptional.isEmpty()) {
            return new MessageResponse("Error: Role not found!");
        }
        user.setRole(roleOptional.get());
        userRepository.save(user);

        return new MessageResponse("User registered successfully!");
    }

    @Transactional // This annotation is correct and should remain
    public MessageResponse requestPasswordReset(PasswordResetRequest passwordResetRequest) {
        Optional<User> userOptional = userRepository.findByEmail(passwordResetRequest.getEmail());

        if (userOptional.isEmpty()) {
            // For security, always respond with a generic message even if email isn't found
            return new MessageResponse("If your email is registered, a password reset link has been sent.");
        }

        User user = userOptional.get();

        // **REMOVED: passwordResetTokenRepository.deleteByUser(user);**
        // Instead, we find and update if exists, or create new

        // 1. Generate new token details
        String token = UUID.randomUUID().toString();
        LocalDateTime expiryDate = LocalDateTime.now().plusHours(24);

        // 2. Try to find an existing token for this user (with pessimistic lock)
        Optional<PasswordResetToken> existingTokenOptional = passwordResetTokenRepository.findByUser(user);

        PasswordResetToken resetToken;
        if (existingTokenOptional.isPresent()) {
            // 3. If a token exists, update its properties
            resetToken = existingTokenOptional.get();
            resetToken.setToken(token); // Update the token string
            resetToken.setExpiryDate(expiryDate); // Update the expiry date
        } else {
            // 4. If no token exists, create a new one
            resetToken = new PasswordResetToken(token, user, expiryDate);
        }

        // 5. Save the token (Spring Data JPA will either INSERT new or UPDATE existing based on ID)
        passwordResetTokenRepository.save(resetToken);

        // Send email
        String resetLink = "http://localhost:5173/reset-password?token=" + token; // Frontend URL
        String emailSubject = "Password Reset Request for Your Insurance App Account";
        String emailText = "Dear " + user.getUsername() + ",\n\n"
                            + "We received a request to reset your password for your account associated with " + user.getEmail() + ".\n"
                            + "To reset your password, please click on the link below:\n"
                            + resetLink + "\n\n"
                            + "This link will expire in 24 hours.\n"
                            + "If you did not request a password reset, please ignore this email.\n\n"
                            + "Sincerely,\nYour Insurance App Team";
        try {
            emailService.sendEmail(user.getEmail(), emailSubject, emailText);
            return new MessageResponse("Password reset link sent to your email.");
        } catch (Exception e) {
            // Log the full exception for debugging, but return a generic message to the client
            e.printStackTrace(); // Keep this for server-side logging
            return new MessageResponse("Error sending password reset email. Please try again later.");
        }
    }

    // This method needs to be transactional as well because it performs delete and save operations.
    // If it's called from a transactional service method, that's fine.
    // If it's called directly from a controller, you'd need @Transactional here too.
    @Transactional
    public MessageResponse resetPassword(VerifyResetTokenRequest verifyResetTokenRequest) {
        Optional<PasswordResetToken> tokenOptional = passwordResetTokenRepository.findByToken(verifyResetTokenRequest.getToken());

        if (tokenOptional.isEmpty()) {
            return new MessageResponse("Invalid or expired reset token.");
        }

        PasswordResetToken resetToken = tokenOptional.get();

        if (resetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            passwordResetTokenRepository.delete(resetToken); // Invalidate expired token
            return new MessageResponse("Reset token has expired. Please request a new one.");
        }

        User user = resetToken.getUser();
        user.setPassword(encoder.encode(verifyResetTokenRequest.getNewPassword()));
        userRepository.save(user); // Update user's password

        passwordResetTokenRepository.delete(resetToken); // Invalidate the token after successful use

        return new MessageResponse("Password has been reset successfully!");
    }
}