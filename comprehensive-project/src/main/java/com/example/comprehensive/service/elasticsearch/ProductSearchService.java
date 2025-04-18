package com.example.comprehensive.service.elasticsearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.comprehensive.document.KakaoProductDocument;
import com.example.comprehensive.document.NaverProductDocument;
import com.example.comprehensive.repository.elasticsearch.KakaoProductRepository;
import com.example.comprehensive.repository.elasticsearch.NaverProductRepository;

import java.util.List;

@Service
public class ProductSearchService {
    
    private final KakaoProductRepository kakaoProductRepository;
    private final NaverProductRepository naverProductRepository;
    
    @Autowired
    public ProductSearchService(KakaoProductRepository kakaoProductRepository, 
                               NaverProductRepository naverProductRepository) {
        this.kakaoProductRepository = kakaoProductRepository;
        this.naverProductRepository = naverProductRepository;
    }
    
    // 키워드로 카카오 제품 검색
    public List<KakaoProductDocument> searchKakaoProductsByKeyword(String keyword) {
        return kakaoProductRepository.findByTitleContaining(keyword);
    }
    
    // 키워드로 네이버 제품 검색
    public List<NaverProductDocument> searchNaverProductsByKeyword(String keyword) {
        return naverProductRepository.findByTitleContaining(keyword);
    }
    
    // 라이브 중인 모든 카카오 제품 검색
    public List<KakaoProductDocument> findAllLiveKakaoProducts() {
        return kakaoProductRepository.findByIsLiveTrue();
    }
    
    // 라이브 중인 모든 네이버 제품 검색
    public List<NaverProductDocument> findAllLiveNaverProducts() {
        return naverProductRepository.findByIsLiveTrue();
    }
    
}