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

    public Optional<LiveProductDTO> getProductByLiveId(String liveId) {
        return repository.findByLiveId(liveId).map(this::convertToDTO);
    }

    public List<LiveProductDTO> getAllLiveProducts() {
        return repository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private LiveProductDTO convertToDTO(LiveProduct entity) {
        LiveProductDTO dto = new LiveProductDTO();
        dto.setLiveId(entity.getLiveId());
        dto.setLive(entity.isLive());
        dto.setLastUpdated(entity.getLastUpdated());
        dto.setLiveUrl(entity.getLiveUrl());
        dto.setPlatform(entity.getPlatform());
        dto.setThumbnail(entity.getThumbnail());
        dto.setTitle(entity.getTitle());

        // Seller Info
        SellerInfoDTO sellerDTO = new SellerInfoDTO();
        if (entity.getSellerInfo() != null) {
            sellerDTO.setName(entity.getSellerInfo().getName());
            sellerDTO.setUrl(entity.getSellerInfo().getUrl());
            sellerDTO.setImage(entity.getSellerInfo().getImage());
        }
        dto.setSellerInfo(sellerDTO);

        // Product List
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
