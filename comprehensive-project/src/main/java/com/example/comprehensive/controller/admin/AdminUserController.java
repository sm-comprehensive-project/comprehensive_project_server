package com.example.comprehensive.controller.admin;

import com.example.comprehensive.entity.User;
import com.example.comprehensive.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
public class AdminUserController {

    private final UserRepository userRepository;

    public AdminUserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 전체 회원 조회 + 이메일/닉네임 검색
    @GetMapping
    public List<User> getAllUsers(@RequestParam(required = false) String email,
            @RequestParam(required = false) String nickname) {
        if (email != null) {
            return userRepository.findByEmailContainingIgnoreCase(email);
        } else if (nickname != null) {
            return userRepository.findByNicknameContainingIgnoreCase(nickname);
        } else {
            return userRepository.findAll();
        }
    }

    // 회원 정보 수정 (닉네임 등)
    @PutMapping("/{id}")
    public User updateUser(@PathVariable String id, @RequestBody User updatedUser) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setNickname(updatedUser.getNickname());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("회원 정보를 찾을 수 없습니다."));
    }

    // 회원 삭제
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) {
        userRepository.deleteById(id);
    }

    // 회원 활동 이력 확인
    @GetMapping("/{id}/activity")
    public User getUserActivity(@PathVariable String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("회원 정보를 찾을 수 없습니다."));
    }
}
