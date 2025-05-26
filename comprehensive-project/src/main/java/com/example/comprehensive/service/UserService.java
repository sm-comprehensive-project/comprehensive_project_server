package com.example.comprehensive.service;

import com.example.comprehensive.dto.LiveProductDTO;
import com.example.comprehensive.dto.ScoredLiveProductDTO;
import com.example.comprehensive.entity.User;
import com.example.comprehensive.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final LiveProductService liveProductService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder,
            LiveProductService liveProductService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.liveProductService = liveProductService;
    }

    public User register(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("이미 존재하는 이메일입니다.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User login(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }
        return user;
    }

    public Optional<User> getUser(String email) {
        return userRepository.findByEmail(email);
    }

    public User updateUser(User updatedUser) {
        User user = userRepository.findByEmail(updatedUser.getEmail())
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        if (updatedUser.getNickname() != null)
            user.setNickname(updatedUser.getNickname());
        if (updatedUser.getAddress() != null)
            user.setAddress(updatedUser.getAddress());
        if (updatedUser.getGender() != null)
            user.setGender(updatedUser.getGender());
        if (updatedUser.getBirthDate() != null)
            user.setBirthDate(updatedUser.getBirthDate());

        return userRepository.save(user);
    }

    public void deleteUser(String email) {
        userRepository.deleteByEmail(email);
    }

    public User likeLive(String email, String liveId) {
        User user = userRepository.findByEmail(email).orElseThrow();
        if (!user.getLikedLiveIds().contains(liveId)) {
            user.getLikedLiveIds().add(liveId);
        }
        return userRepository.save(user);
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
        if (user.getRecentWatchedIds().size() > 20)
            user.getRecentWatchedIds().remove(20);
        user.getWatchedHistory().add(liveId);
        userRepository.save(user);
    }

    public void addClickedItem(String email, String itemId) {
        User user = userRepository.findByEmail(email).orElseThrow();
        user.getClickedItems().add(itemId);
        userRepository.save(user);
    }

    public void addInterestedCategory(String email, String category) {
        User user = userRepository.findByEmail(email).orElseThrow();
        if (!user.getInterestedCategories().contains(category)) {
            user.getInterestedCategories().add(category);
        }
        userRepository.save(user);
    }

    public void removeInterestedCategory(String email, String category) {
        User user = userRepository.findByEmail(email).orElseThrow();
        user.getInterestedCategories().remove(category);
        userRepository.save(user);
    }

    public void addSearchHistory(String email, String keyword) {
        User user = userRepository.findByEmail(email).orElseThrow();
        user.getSearchHistory().add(keyword);
        userRepository.save(user);
    }

    public List<ScoredLiveProductDTO> getRecommendedLiveProducts(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다: " + email));

        List<User.Recommendation> recommendations = user.getRecommendations();

        return recommendations.stream()
                .map(rec -> {
                    Optional<LiveProductDTO> dto = liveProductService.getProductByObjectId(rec.getLiveId());

                    if (dto.isPresent()) {
                    } else {
                    }

                    return dto.map(product -> new ScoredLiveProductDTO(product, rec.getScore())).orElse(null);
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public Optional<ScoredLiveProductDTO> getTopRecommendedLiveProduct(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다: " + email));

        return user.getRecommendations().stream()
                .max(Comparator.comparingDouble(User.Recommendation::getScore)) // 최고 점수 추천 선택
                .map(topRec -> {
                    Optional<LiveProductDTO> dto = liveProductService.getProductByObjectId(topRec.getLiveId());
                    return dto.map(product -> new ScoredLiveProductDTO(product, topRec.getScore()))
                            .orElse(null);
                });
    }

    public List<LiveProductDTO> getLikedLiveProducts(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다: " + email));

        List<String> likedIds = user.getLikedLiveIds();

        return liveProductService.getProductsByObjectIds(likedIds);
    }

}
