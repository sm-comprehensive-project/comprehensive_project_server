package com.example.comprehensive.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoredLiveProductDTO {
    private LiveProductDTO liveProduct;
    private double score;
}