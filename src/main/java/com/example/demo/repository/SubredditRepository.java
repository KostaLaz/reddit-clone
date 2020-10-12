package com.example.demo.repository;

import com.example.demo.model.Subreddit;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface SubredditRepository extends MongoRepository<Subreddit, String> {
}
