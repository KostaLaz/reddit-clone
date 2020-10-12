package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Vote")
@Builder
public class Vote {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long voteId;
    private VoteType voteType;
    @NotNull
    private Post post;
    private User user;
}
