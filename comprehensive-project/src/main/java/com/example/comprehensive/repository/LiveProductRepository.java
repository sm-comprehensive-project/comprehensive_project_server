package com.example.comprehensive.repository;

import com.example.comprehensive.entity.LiveProduct;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface LiveProductRepository extends MongoRepository<LiveProduct, String> {
    Optional<LiveProduct> findByLiveId(String liveId);

    List<LiveProductProjection> findAllBy();
}
