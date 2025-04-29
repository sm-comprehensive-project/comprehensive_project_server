package com.example.comprehensive.controller;

import com.example.comprehensive.dto.ScheduleDTO;
import com.example.comprehensive.service.ScheduleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/damoa/schedule")
public class ScheduleController {

    private final ScheduleService service;

    public ScheduleController(ScheduleService service) {
        this.service = service;
    }

    // 🔵 카카오 스케줄 조회
    @GetMapping("/kakao")
    public List<ScheduleDTO> getKakaoSchedules() {
        return service.getKakaoSchedules();
    }

    // 🟢 네이버 스케줄 조회
    @GetMapping("/naver")
    public List<ScheduleDTO> getNaverSchedules() {
        return service.getNaverSchedules();
    }

    // 🟣 카카오+네이버 스케줄 통합 조회
    @GetMapping("/all")
    public List<ScheduleDTO> getAllSchedules() {
        return service.getAllSchedules();
    }
}
