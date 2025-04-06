package com.example.comprehensive.repository;

import com.example.comprehensive.entity.Report;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ReportRepository extends MongoRepository<Report, String> {
}
