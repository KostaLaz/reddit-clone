package com.example.demo.controller;

import com.example.demo.dto.RegisterRequest;
import com.example.demo.service.AuthServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthServiceImpl authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest){
        authService.signup(registerRequest);
        return new ResponseEntity<>("User registration successfull", HttpStatus.OK);
    }

    @GetMapping("/accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token){
        authService.verfyAccount(token);
        return new ResponseEntity<>("Account activated successfully", HttpStatus.OK);
    }
}
