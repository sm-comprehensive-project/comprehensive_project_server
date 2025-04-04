package com.example.comprehensive.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "kakao_product")
public class LiveProduct {

    @Id
    private String id;

    private String liveId;

    @Field("isLive")
    private boolean live;

    private LocalDateTime lastUpdated;
    private String liveUrl;
    private String platform;
    private List<Product> products;
    private SellerInfo sellerInfo;
    private String thumbnail;
    private String title;
}
