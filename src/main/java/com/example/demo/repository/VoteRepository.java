package com.example.demo.repository;

import com.example.demo.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends MongoRepository<Post, Long> {
}
