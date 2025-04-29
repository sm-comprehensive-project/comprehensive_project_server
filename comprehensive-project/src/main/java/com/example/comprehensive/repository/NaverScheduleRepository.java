package com.example.comprehensive.repository;

import com.example.comprehensive.entity.NaverSchedule;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NaverScheduleRepository extends MongoRepository<NaverSchedule, String> {
}
