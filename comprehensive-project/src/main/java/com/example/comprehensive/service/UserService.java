package com.example.comprehensive.service;

import com.example.comprehensive.entity.User;
import com.example.comprehensive.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 회원가입
    public User register(User user) {
        Optional<User> exists = userRepository.findByEmail(user.getEmail());
        if (exists.isPresent()) {
            throw new RuntimeException("이미 가입된 이메일입니다.");
        }
        return userRepository.save(user);
    }

    // 로그인
    public User login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("이메일이 존재하지 않습니다."));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        return user;
    }

    // 내 정보
    public Optional<User> getUser(String email) {
        return userRepository.findByEmail(email);
    }

    // 찜 추가
    public User likeLive(String email, String liveId) {
        User user = userRepository.findByEmail(email).orElseThrow();
        if (!user.getLikedLiveIds().contains(liveId)) {
            user.getLikedLiveIds().add(liveId);
            userRepository.save(user);
        }
        return user;
    }

    // 찜 삭제
    public User unlikeLive(String email, String liveId) {
        User user = userRepository.findByEmail(email).orElseThrow();
        user.getLikedLiveIds().remove(liveId);
        return userRepository.save(user);
    }

    // 최근 시청 추가
    public void addRecentWatch(String email, String liveId) {
        User user = userRepository.findByEmail(email).orElseThrow();
        user.getRecentWatchedIds().remove(liveId);
        user.getRecentWatchedIds().add(0, liveId); // 가장 최근에 추가
        if (user.getRecentWatchedIds().size() > 20) {
            user.getRecentWatchedIds().remove(20); // 20개만 유지
        }
        user.getWatchedHistory().add(liveId);
        userRepository.save(user);
    }
}
