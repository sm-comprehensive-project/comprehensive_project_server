package com.example.comprehensive.controller;

import com.example.comprehensive.dto.UserDTO;
import com.example.comprehensive.entity.User;
import com.example.comprehensive.service.UserService;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
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
     * Body (JSON):
     * {
     * "email": "test@example.com",
     * "password": "1234",
     * "nickname": "홍길동",
     * "loginType": "basic",
     * "address": "서울시 강남구",
     * "gender": "MALE",
     * "birthDate": "1990-01-01"
     * }
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            UserDTO dto = new UserDTO(service.register(user));
            return ResponseEntity.ok().body(Map.of("success", true, "message", "회원가입 성공", "user", dto));
        } catch (RuntimeException e) {
            if ("이미 존재하는 이메일입니다.".equals(e.getMessage())) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(Map.of("success", false, "message", e.getMessage()));
            }
            return ResponseEntity.badRequest()
                    .body(Map.of("success", false, "message", e.getMessage()));
        }

    }

    /**
     * 로그인
     * Method: POST
     * URL:
     * http://localhost:8080/api/user/login?email=test@example.com&password=1234
     * Body: 없음
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        try {
            UserDTO dto = new UserDTO(service.login(email, password));
            return ResponseEntity.ok().body(Map.of("success", true, "message", "로그인 성공", "user", dto));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("success", false, "message", e.getMessage()));
        }
    }

    /**
     * 내 정보 조회
     * Method: GET
     * URL: http://localhost:8080/api/user/me?email=test@example.com
     */
    @GetMapping("/me")
    public ResponseEntity<?> me(@RequestParam String email) {
        return service.getUser(email)
                .map(user -> ResponseEntity.ok().body(Map.of("success", true, "user", new UserDTO(user))))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("success", false, "message", "사용자 정보를 찾을 수 없습니다.")));
    }

    /**
     * 회원 정보 수정
     * Method: POST
     * URL: http://localhost:8080/api/user/update
     * Body (JSON):
     * {
     * "email": "test@example.com",
     * "nickname": "수정된 닉네임",
     * "address": "서울시 수정구",
     * "gender": "FEMALE",
     * "birthDate": "1995-12-25"
     * }
     */
    @PostMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody User updatedUser) {
        UserDTO updated = new UserDTO(service.updateUser(updatedUser));
        return ResponseEntity.ok().body(Map.of("success", true, "message", "회원 정보 수정 완료", "user", updated));
    }

    /**
     * 회원 탈퇴
     * Method: DELETE
     * URL: http://localhost:8080/api/user/delete?email=test@example.com
     */
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestParam String email) {
        service.deleteUser(email);
        return ResponseEntity.ok().body(Map.of("success", true, "message", "회원 탈퇴 완료"));
    }

    /**
     * 찜 추가
     * Method: POST
     * URL: http://localhost:8080/api/user/like/abc123?email=test@example.com
     */
    @PostMapping("/like/{liveId}")
    public ResponseEntity<?> like(@RequestParam String email, @PathVariable String liveId) {
        return ResponseEntity.ok().body(Map.of("success", true, "user", new UserDTO(service.likeLive(email, liveId))));
    }

    /**
     * 찜 제거
     * Method: DELETE
     * URL: http://localhost:8080/api/user/like/abc123?email=test@example.com
     */
    @DeleteMapping("/like/{liveId}")
    public ResponseEntity<?> unlike(@RequestParam String email, @PathVariable String liveId) {
        return ResponseEntity.ok()
                .body(Map.of("success", true, "user", new UserDTO(service.unlikeLive(email, liveId))));
    }

    /**
     * 최근 시청 추가
     * Method: POST
     * URL: http://localhost:8080/api/user/recent/abc123?email=test@example.com
     */
    @PostMapping("/recent/{liveId}")
    public ResponseEntity<?> addRecent(@RequestParam String email, @PathVariable String liveId) {
        service.addRecentWatch(email, liveId);
        return ResponseEntity.ok().body(Map.of("success", true, "message", "최근 시청 추가됨"));
    }

    /**
     * 클릭한 상품 추가
     * Method: POST
     * URL: http://localhost:8080/api/user/click
     * Body (JSON):
     * {
     * "email": "test@example.com",
     * "itemId": "item001"
     * }
     */
    @PostMapping("/click")
    public ResponseEntity<?> addClick(@RequestBody Map<String, String> request) {
        service.addClickedItem(request.get("email"), request.get("itemId"));
        return ResponseEntity.ok().body(Map.of("success", true, "message", "클릭 기록 추가됨"));
    }

    /**
     * 관심 카테고리 추가
     * Method: POST
     * URL: http://localhost:8080/api/user/category/add
     * Body (JSON):
     * {
     * "email": "test@example.com",
     * "category": "뷰티"
     * }
     */
    @PostMapping("/category/add")
    public ResponseEntity<?> addCategory(@RequestBody Map<String, String> request) {
        service.addInterestedCategory(request.get("email"), request.get("category"));
        return ResponseEntity.ok().body(Map.of("success", true, "message", "관심 카테고리 추가됨"));
    }

    /**
     * 관심 카테고리 제거
     * Method: POST
     * URL: http://localhost:8080/api/user/category/remove
     * Body (JSON):
     * {
     * "email": "test@example.com",
     * "category": "뷰티"
     * }
     */
    @PostMapping("/category/remove")
    public ResponseEntity<?> removeCategory(@RequestBody Map<String, String> request) {
        service.removeInterestedCategory(request.get("email"), request.get("category"));
        return ResponseEntity.ok().body(Map.of("success", true, "message", "관심 카테고리 제거됨"));
    }

    /**
     * 검색 기록 추가
     * Method: POST
     * URL: http://localhost:8080/api/user/search
     * Body (JSON):
     * {
     * "email": "test@example.com",
     * "keyword": "여름 원피스"
     * }
     */
    @PostMapping("/search")
    public ResponseEntity<?> addSearch(@RequestBody Map<String, String> request) {
        service.addSearchHistory(request.get("email"), request.get("keyword"));
        return ResponseEntity.ok().body(Map.of("success", true, "message", "검색 기록 추가됨"));
    }
}
