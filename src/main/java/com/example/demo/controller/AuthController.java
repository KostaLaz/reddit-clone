package com.example.demo.controller;

import com.example.demo.dto.AuthenticationResponse;
import com.example.demo.dto.LoginRequestDto;
import com.example.demo.dto.RegisterRequestDto;
import com.example.demo.exception.SpringRedditException;
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
    private final LoginRequestDto loginRequestDto;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequestDto registerRequestDto){
        authService.signup(registerRequestDto);
        return new ResponseEntity<>("User registration successful", HttpStatus.OK);
    }

    @GetMapping("/accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token){
        authService.verfyAccount(token);
        return new ResponseEntity<>("Account activated successfully", HttpStatus.OK);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequestDto loginRequestDto) throws SpringRedditException {
        return authService.login(loginRequestDto);
    }
}
