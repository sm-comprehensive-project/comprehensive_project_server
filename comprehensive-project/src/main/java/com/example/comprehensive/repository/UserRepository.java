package com.example.comprehensive.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.comprehensive.model.User;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    List<User> findByName(String name);
}