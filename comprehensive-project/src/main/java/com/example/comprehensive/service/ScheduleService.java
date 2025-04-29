package com.example.comprehensive.service;

import com.example.comprehensive.dto.ScheduleDTO;
import com.example.comprehensive.entity.KakaoSchedule;
import com.example.comprehensive.entity.NaverSchedule;
import com.example.comprehensive.repository.KakaoScheduleRepository;
import com.example.comprehensive.repository.NaverScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

@Service
public class ScheduleService {

    private final KakaoScheduleRepository kakaoRepository;
    private final NaverScheduleRepository naverRepository;

    public ScheduleService(KakaoScheduleRepository kakaoRepository, NaverScheduleRepository naverRepository) {
        this.kakaoRepository = kakaoRepository;
        this.naverRepository = naverRepository;
    }

    // 🔵 카카오 스케줄 가져오기
    public List<ScheduleDTO> getKakaoSchedules() {
        return kakaoRepository.findAll().stream()
                .map(this::convertKakaoToDTO)
                .collect(Collectors.toList());
    }

    // 🟢 네이버 스케줄 가져오기
    public List<ScheduleDTO> getNaverSchedules() {
        return naverRepository.findAll().stream()
                .map(this::convertNaverToDTO)
                .collect(Collectors.toList());
    }

    // 🟣 둘 다 합쳐서 가져오기
    public List<ScheduleDTO> getAllSchedules() {
        List<ScheduleDTO> all = new ArrayList<>();
        all.addAll(getKakaoSchedules());
        all.addAll(getNaverSchedules());
        return all;
    }

    // 🔵 Kakao Entity → DTO 변환
    private ScheduleDTO convertKakaoToDTO(KakaoSchedule schedule) {
        ScheduleDTO dto = new ScheduleDTO();
        dto.setId(schedule.getId());
        dto.setLiveUrl(schedule.getLiveUrl());
        dto.setChannelUrl(schedule.getChannelUrl());
        dto.setTitle(schedule.getTitle());
        dto.setThumbnail(schedule.getThumbnail());
        dto.setSeller(schedule.getSeller());
        dto.setPlatform(schedule.getPlatform());
        dto.setDates(schedule.getDates());
        return dto;
    }

    // 🟢 Naver Entity → DTO 변환
    private ScheduleDTO convertNaverToDTO(NaverSchedule schedule) {
        ScheduleDTO dto = new ScheduleDTO();
        dto.setId(schedule.getId());
        dto.setLiveUrl(schedule.getLiveUrl());
        dto.setChannelUrl(schedule.getChannelUrl());
        dto.setTitle(schedule.getTitle());
        dto.setThumbnail(schedule.getThumbnail());
        dto.setSeller(schedule.getSeller());
        dto.setPlatform(schedule.getPlatform());
        dto.setDates(schedule.getDates());
        return dto;
    }
}
