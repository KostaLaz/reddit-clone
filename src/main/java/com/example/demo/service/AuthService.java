package com.example.demo.service;

import com.example.demo.dto.RegisterRequest;
import com.example.demo.exception.SpringRedditException;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
     void signup(RegisterRequest registerRequest);
}
