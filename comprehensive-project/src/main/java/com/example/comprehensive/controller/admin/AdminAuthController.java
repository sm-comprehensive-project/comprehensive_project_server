package com.example.comprehensive.controller.admin;

import com.example.comprehensive.entity.Admin;
import com.example.comprehensive.repository.AdminRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class AdminAuthController {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminAuthController(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // ✅ 관리자 회원가입 (테스트용)
    @PostMapping("/register")
    public Admin register(@RequestBody Admin admin) {
        Optional<Admin> existing = adminRepository.findByUsername(admin.getUsername());
        if (existing.isPresent()) {
            throw new RuntimeException("이미 존재하는 관리자입니다.");
        }

        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        admin.setRole("ROLE_ADMIN"); // 권한 설정
        return adminRepository.save(admin);
    }

    // ✅ 로그인 성공 시 관리자 정보 확인용 (실제 인증은 Spring Security가 처리)
    @PostMapping("/login")
    public Admin login(@RequestParam String username, @RequestParam String password) {
        Admin admin = adminRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 관리자입니다."));

        if (!passwordEncoder.matches(password, admin.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        return admin;
    }
}
