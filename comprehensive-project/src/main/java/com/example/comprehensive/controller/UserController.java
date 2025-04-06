package com.example.comprehensive.controller;

import com.example.comprehensive.dto.UserDTO;
import com.example.comprehensive.entity.User;
import com.example.comprehensive.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    /**
     * 회원가입
     * Method: POST
     * URL: http://localhost:8080/api/user/register
     * Body (raw JSON):
     * {
     * "email": "a@a.com",
     * "password": "1234",
     * "nickname": "나형진"
     * }
     */
    @PostMapping("/register")
    public UserDTO register(@RequestBody User user) {
        return new UserDTO(service.register(user));
    }

    /**
     * 로그인 (인증 검증용)
     * Method: POST
     * URL: http://localhost:8080/api/user/login?email=a@a.com&password=1234
     * ⚠️ 반환값은 토큰 아님 (단순 로그인 확인용)
     */
    @PostMapping("/login")
    public UserDTO login(@RequestParam String email, @RequestParam String password) {
        return new UserDTO(service.login(email, password));
    }

    /**
     * 내 정보 조회
     * Method: GET
     * URL: http://localhost:8080/api/user/me?email=a@a.com
     * Authorization: Basic Auth 필요 (Username: a@a.com / Password: 1234)
     */
    @GetMapping("/me")
    public UserDTO me(@RequestParam String email) {
        return new UserDTO(service.getUser(email)
                .orElseThrow(() -> new RuntimeException("사용자 정보를 찾을 수 없습니다.")));
    }

    /**
     * 찜 추가
     * Method: POST
     * URL: http://localhost:8080/api/user/like/12345?email=a@a.com
     * Authorization: Basic Auth 필요
     */
    @PostMapping("/like/{liveId}")
    public UserDTO like(@RequestParam String email, @PathVariable String liveId) {
        return new UserDTO(service.likeLive(email, liveId));
    }

    /**
     * 찜 제거
     * Method: DELETE
     * URL: http://localhost:8080/api/user/like/12345?email=a@a.com
     * Authorization: Basic Auth 필요
     */
    @DeleteMapping("/like/{liveId}")
    public UserDTO unlike(@RequestParam String email, @PathVariable String liveId) {
        return new UserDTO(service.unlikeLive(email, liveId));
    }

    /**
     * 최근 시청 추가
     * Method: POST
     * URL: http://localhost:8080/api/user/recent/12345?email=a@a.com
     * Authorization: Basic Auth 필요
     */
    @PostMapping("/recent/{liveId}")
    public void addRecent(@RequestParam String email, @PathVariable String liveId) {
        service.addRecentWatch(email, liveId);
    }
}
