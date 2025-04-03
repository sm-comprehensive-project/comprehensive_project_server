package com.example.comprehensive.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private String name;
    private String image;
    private String link;
    private int price;
    private int price_origin;
    private int discountRate;
    private String category;

    public String getCategory() {
        return category != null ? category : "카테고리";
    }
}
