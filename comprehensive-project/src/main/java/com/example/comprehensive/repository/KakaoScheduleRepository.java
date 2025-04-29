package com.example.comprehensive.repository;

import com.example.comprehensive.entity.KakaoSchedule;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface KakaoScheduleRepository extends MongoRepository<KakaoSchedule, String> {
}
