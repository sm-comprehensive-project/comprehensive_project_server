package com.example.comprehensive.controller;

import com.example.comprehensive.dto.UserDTO;
import com.example.comprehensive.entity.User;
import com.example.comprehensive.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    // 회원가입
    // http://localhost:8080/api/user/register
    // Body (JSON):
    // {
    // "email": "a@a.com",
    // "password": "1234",
    // "nickname": "나형진"
    // }
    @PostMapping("/register")
    public UserDTO register(@RequestBody User user) {
        return new UserDTO(service.register(user));
    }

    // 로그인
    // http://localhost:8080/api/user/login?email=a@a.com&password=1234
    @PostMapping("/login")
    public UserDTO login(@RequestParam String email, @RequestParam String password) {
        return new UserDTO(service.login(email, password));
    }

    // 내 정보
    // http://localhost:8080/api/user/me?email=a@a.com
    @GetMapping("/me")
    public UserDTO me(@RequestParam String email) {
        return new UserDTO(service.getUser(email).orElseThrow(() -> new RuntimeException("User not found")));
    }

    // 찜 추가
    // http://localhost:8080/api/user/like/40975?email=a@a.com
    @PostMapping("/like/{liveId}")
    public UserDTO like(@RequestParam String email, @PathVariable String liveId) {
        return new UserDTO(service.likeLive(email, liveId));
    }

    // 찜 제거
    // http://localhost:8080/api/user/like/40975?email=a@a.com
    @DeleteMapping("/like/{liveId}")
    public UserDTO unlike(@RequestParam String email, @PathVariable String liveId) {
        return new UserDTO(service.unlikeLive(email, liveId));
    }

    // 최근 시청 추가
    // http://localhost:8080/api/user/recent/40975?email=a@a.com
    @PostMapping("/recent/{liveId}")
    public void addRecent(@RequestParam String email, @PathVariable String liveId) {
        service.addRecentWatch(email, liveId);
    }
}
