package com.example.comprehensive.controller.elasticsearch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.comprehensive.document.KakaoProductDocument;
import com.example.comprehensive.document.NaverProductDocument;
import com.example.comprehensive.service.elasticsearch.ProductSearchService;

import java.util.*;

@RestController
@RequestMapping("/api/search")
public class ProductSearchController {

    private static final Logger logger = LoggerFactory.getLogger(ProductSearchService.class);
    private final ProductSearchService productSearchService;

    @Autowired
    public ProductSearchController(ProductSearchService productSearchService) {
        this.productSearchService = productSearchService;
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Hello World");
    }

    @GetMapping("/kakao")
    public ResponseEntity<List<KakaoProductDocument>> searchKakaoProducts(@RequestParam String keyword) {
        logger.info("Received keyword: {}", keyword);
        List<KakaoProductDocument> results = productSearchService.searchKakaoProductsByKeyword(keyword);
        logger.info("Search results count: {}", results.size());
        return ResponseEntity.ok(results);
    }

    @GetMapping("/naver")
    public ResponseEntity<List<NaverProductDocument>> searchNaverProducts(
            @RequestParam String keyword) {
        List<NaverProductDocument> results = productSearchService.searchNaverProductsByKeyword(keyword);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/kakao/live")
    public ResponseEntity<List<KakaoProductDocument>> getLiveKakaoProducts() {
        List<KakaoProductDocument> results = productSearchService.findAllLiveKakaoProducts();
        return ResponseEntity.ok(results);
    }

    @GetMapping("/naver/live")
    public ResponseEntity<List<NaverProductDocument>> getLiveNaverProducts() {
        List<NaverProductDocument> results = productSearchService.findAllLiveNaverProducts();
        return ResponseEntity.ok(results);
    }

    // 통합 검색 API (카카오와 네이버 제품을 모두 검색)
    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> searchAllProducts(
            @RequestParam String keyword) {
        List<KakaoProductDocument> kakaoResults = productSearchService.searchKakaoProductsByKeyword(keyword);
        List<NaverProductDocument> naverResults = productSearchService.searchNaverProductsByKeyword(keyword);

        Map<String, Object> response = new HashMap<>();
        response.put("kakao", kakaoResults);
        response.put("naver", naverResults);

        return ResponseEntity.ok(response);
    }
}