package com.example.comprehensive.service;

import com.example.comprehensive.entity.User;
import com.example.comprehensive.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("이미 존재하는 이메일입니다.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setLikedLiveIds(new ArrayList<>());
        user.setRecentWatchedIds(new ArrayList<>());
        user.setWatchedHistory(new ArrayList<>());

        return userRepository.save(user);
    }

    public Optional<User> getUser(String email) {
        return userRepository.findByEmail(email);
    }

    public User likeLive(String email, String liveId) {
        User user = userRepository.findByEmail(email).orElseThrow();
        if (!user.getLikedLiveIds().contains(liveId)) {
            user.getLikedLiveIds().add(liveId);
            userRepository.save(user);
        }
        return user;
    }

    public User unlikeLive(String email, String liveId) {
        User user = userRepository.findByEmail(email).orElseThrow();
        user.getLikedLiveIds().remove(liveId);
        return userRepository.save(user);
    }

    public void addRecentWatch(String email, String liveId) {
        User user = userRepository.findByEmail(email).orElseThrow();
        user.getRecentWatchedIds().remove(liveId);
        user.getRecentWatchedIds().add(0, liveId);
        if (user.getRecentWatchedIds().size() > 20) {
            user.getRecentWatchedIds().remove(20);
        }
        user.getWatchedHistory().add(liveId);
        userRepository.save(user);
    }

    public User login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        return user;
    }

}
