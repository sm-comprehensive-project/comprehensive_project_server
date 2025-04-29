package com.example.comprehensive.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class LiveProductDTO {
    private String liveId;
    private boolean live;
    private LocalDateTime lastUpdated;
    private String liveUrl;
    private String platform;
    private List<ProductDTO> products;
    private SellerInfoDTO sellerInfo;
    private String thumbnail;
    private String title;
}
