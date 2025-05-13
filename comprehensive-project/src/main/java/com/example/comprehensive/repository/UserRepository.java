package com.example.comprehensive.repository;

import com.example.comprehensive.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
    List<User> findByEmailContainingIgnoreCase(String email);
    List<User> findByNicknameContainingIgnoreCase(String nickname);
    void deleteByEmail(String email);
}
