package com.example.demo.repository;

import com.example.demo.model.VerificationToken;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface VerificationTokenRepository extends MongoRepository<VerificationToken, String> {
}
