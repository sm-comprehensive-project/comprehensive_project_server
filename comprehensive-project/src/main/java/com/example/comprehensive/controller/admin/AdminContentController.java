package com.example.comprehensive.controller.admin;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.comprehensive.dto.LiveProductDTO;
import com.example.comprehensive.entity.Product;
import com.example.comprehensive.service.LiveProductService;

@RestController
@RequestMapping("/admin/content")
public class AdminContentController {

    private final LiveProductService liveProductService;

    public AdminContentController(LiveProductService liveProductService) {
        this.liveProductService = liveProductService;
    }

    // 상품 수정
    @PutMapping("/{liveId}/products")
    public LiveProductDTO updateProduct(
            @PathVariable String liveId,
            @RequestParam String productName,
            @RequestBody Product updatedProduct) {
        return liveProductService.updateProduct(liveId, productName, updatedProduct);
    }

    // 상품 삭제
    @DeleteMapping("/{liveId}/products")
    public LiveProductDTO deleteProduct(
            @PathVariable String liveId,
            @RequestParam String productName) {
        return liveProductService.deleteProduct(liveId, productName);
    }

}
