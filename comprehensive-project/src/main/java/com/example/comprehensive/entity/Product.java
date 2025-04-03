package com.example.comprehensive.entity;

import lombok.Data;

@Data
public class Product {
    private String name;
    private String image;
    private String link;
    private int price;
    private int price_origin;
    private int discountRate;
    private String category;
}
