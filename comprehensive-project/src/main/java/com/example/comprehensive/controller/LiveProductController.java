package com.example.comprehensive.controller;

import com.example.comprehensive.dto.LiveProductDTO;
import com.example.comprehensive.repository.LiveProductProjection;
import com.example.comprehensive.service.LiveProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/damoa/live")
public class LiveProductController {

    private final LiveProductService service;

    public LiveProductController(LiveProductService service) {
        this.service = service;
    }

    // 상세 조회 예시:
    // http://localhost:8080/damoa/live/41550
    @GetMapping("/{liveId}")
    public LiveProductDTO getProduct(@PathVariable String liveId) {
        return service.getProductByLiveId(liveId)
                .orElseThrow(() -> new RuntimeException("Live ID not found: " + liveId));
    }

    // 전체 + 조건 필터링 예시
    // http://localhost:8080/damoa/live
    // http://localhost:8080/damoa/live?platform=kakao
    // http://localhost:8080/damoa/live?isLive=true
    // http://localhost:8080/damoa/live?search=사과
    // http://localhost:8080/damoa/live?category=과일
    // http://localhost:8080/damoa/live?platform=kakao&isLive=true&search=사과&category=과일
    @GetMapping
    public List<LiveProductDTO> getFiltered(
            @RequestParam(required = false) String platform,
            @RequestParam(required = false) Boolean isLive,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String category) {
        return service.getFilteredLiveProducts(platform, isLive, search, category);
    }

    // 현재 진행 중인 방송만 예시
    // http://localhost:8080/damoa/live/live-only
    @GetMapping("/live-only")
    public List<LiveProductDTO> getLiveOnly() {
        return service.getFilteredLiveProducts(null, true, null, null);
    }

    // 카테고리별 반환 예시
    // http://localhost:8080/damoa/live/by-category/과일
    @GetMapping("/by-category/{category}")
    public List<LiveProductDTO> getByCategory(@PathVariable String category) {
        return service.getFilteredLiveProducts(null, null, null, category);
    }

    // 플랫폼별 반환 예시
    // http://localhost:8080/damoa/live/by-platform/kakao
    @GetMapping("/by-platform/{platform}")
    public List<LiveProductDTO> getByPlatform(@PathVariable String platform) {
        return service.getFilteredLiveProducts(platform, null, null, null);
    }

    // 방송 요약 목록 조회 API
    // http://localhost:8080/damoa/live/summary
    @GetMapping("/summary")
    public List<LiveProductProjection> getLiveSummaryList() {
        return service.getAllLiveProductSummary();
    }
}
