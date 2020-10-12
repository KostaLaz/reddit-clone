package com.example.demo.repository;

import com.example.demo.model.Subreddit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubredditRepository extends MongoRepository<Subreddit, String> {
}
