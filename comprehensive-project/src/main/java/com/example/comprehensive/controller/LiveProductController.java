package com.example.comprehensive.controller;

import com.example.comprehensive.dto.LiveProductDTO;
import com.example.comprehensive.service.LiveProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/damoa/live")
public class LiveProductController {

    private final LiveProductService service;

    public LiveProductController(LiveProductService service) {
        this.service = service;
    }

    @GetMapping("/{liveId}")
    public LiveProductDTO getProduct(@PathVariable String liveId) {
        return service.getProductByLiveId(liveId)
                .orElseThrow(() -> new RuntimeException("Live ID not found: " + liveId));
    }

    @GetMapping
    public List<LiveProductDTO> getAllProducts() {
        return service.getAllLiveProducts();
    }
}
