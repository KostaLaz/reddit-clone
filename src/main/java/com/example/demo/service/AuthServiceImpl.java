package com.example.demo.service;

import com.example.demo.dto.RegisterRequest;
import com.example.demo.exception.SpringRedditException;
import com.example.demo.model.NotificationEmail;
import com.example.demo.model.User;
import com.example.demo.model.VerificationToken;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VerificationTokenRepository;
import jdk.jshell.spi.SPIResolutionException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class AuthServiceImpl{

    private final MailService mailService;
    private final VerificationTokenRepository verificationTokenRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signup(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreated(Instant.now());
        user.setEnabled(false);

        userRepository.save(user);

        String token = generateVerificationToken(user);
        try {
            mailService.sendEmail(new NotificationEmail("Please Activate your Account",
                    user.getEmail(), "Thank you for signing up to Spring Reddit, " +
                    "please click on the below url to activate your account : " +
                    "http://localhost:8080/api/auth/accountVerification/" + token));
        } catch (SpringRedditException e) {
            e.printStackTrace();
        }
    }

    private String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);

        verificationTokenRepository.save(verificationToken);
        return token;
    }

    public void verfyAccount(String token) {
        try {
            Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
            verificationToken.orElseThrow(() -> new SpringRedditException("Invalid token"));
            fetchUserAndEnable(verificationToken.get());
        }catch (SpringRedditException e){
            e.printStackTrace();
        }
    }

    private void fetchUserAndEnable(VerificationToken verificationToken) {
        try {
            String username = verificationToken.getUser().getUsername();
           User user = userRepository.findByUsername(username).orElseThrow(() -> new SpringRedditException("user with name " + username + " not found."));
            user.setEnabled(true);
        }catch(SpringRedditException e){
            e.printStackTrace();
        }
        }
}