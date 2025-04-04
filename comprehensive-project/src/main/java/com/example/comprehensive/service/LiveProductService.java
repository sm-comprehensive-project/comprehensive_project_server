package com.example.comprehensive.service;

import com.example.comprehensive.dto.*;
import com.example.comprehensive.entity.*;
import com.example.comprehensive.repository.LiveProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LiveProductService {

    private final LiveProductRepository repository;

    public LiveProductService(LiveProductRepository repository) {
        this.repository = repository;
    }

    // ✅ 단일 조회
    public Optional<LiveProductDTO> getProductByLiveId(String liveId) {
        return repository.findByLiveId(liveId).map(this::convertToDTO);
    }

    // ✅ 전체 조건 필터링
    public List<LiveProductDTO> getFilteredLiveProducts(String platform, Boolean isLive, String search,
            String category) {
        List<LiveProduct> all = repository.findAll();

        return all.stream()
                .filter(p -> platform == null || p.getPlatform().equalsIgnoreCase(platform))
                .filter(p -> isLive == null || p.isLive() == isLive)
                .filter(p -> {
                    if (search == null || search.isBlank())
                        return true;

                    boolean inTitle = p.getTitle() != null && p.getTitle().contains(search);
                    boolean inProductName = p.getProducts() != null && p.getProducts().stream()
                            .anyMatch(prod -> prod.getName() != null && prod.getName().contains(search));

                    return inTitle || inProductName;
                })
                .filter(p -> {
                    if (category == null || category.isBlank())
                        return true;

                    return p.getProducts() != null && p.getProducts().stream()
                            .anyMatch(prod -> prod.getCategory() != null
                                    && prod.getCategory().equalsIgnoreCase(category));
                })
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // ✅ 전체 목록 (필터 없음)
    public List<LiveProductDTO> getAllLiveProducts() {
        return repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // ✅ Entity → DTO 변환
    private LiveProductDTO convertToDTO(LiveProduct entity) {
        LiveProductDTO dto = new LiveProductDTO();
        dto.setLiveId(entity.getLiveId());
        dto.setLive(entity.isLive());
        dto.setLastUpdated(entity.getLastUpdated());
        dto.setLiveUrl(entity.getLiveUrl());
        dto.setPlatform(entity.getPlatform());
        dto.setThumbnail(entity.getThumbnail());
        dto.setTitle(entity.getTitle());

        // 판매자 정보
        SellerInfoDTO sellerDTO = new SellerInfoDTO();
        if (entity.getSellerInfo() != null) {
            sellerDTO.setName(entity.getSellerInfo().getName());
            sellerDTO.setUrl(entity.getSellerInfo().getUrl());
            sellerDTO.setImage(entity.getSellerInfo().getImage());
        }
        dto.setSellerInfo(sellerDTO);

        // 상품 목록
        List<ProductDTO> productDTOs = entity.getProducts().stream().map(p -> {
            ProductDTO d = new ProductDTO();
            d.setName(p.getName());
            d.setImage(p.getImage());
            d.setLink(p.getLink());
            d.setPrice(p.getPrice());
            d.setPrice_origin(p.getPrice_origin());
            d.setDiscountRate(p.getDiscountRate());
            d.setCategory(p.getCategory() != null ? p.getCategory() : "카테고리");
            return d;
        }).collect(Collectors.toList());

        dto.setProducts(productDTOs);
        return dto;
    }
}
