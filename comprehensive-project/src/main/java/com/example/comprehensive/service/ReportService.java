package com.example.comprehensive.service;

import com.example.comprehensive.entity.Report;
import com.example.comprehensive.repository.ReportRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReportService {

    private final ReportRepository repository;

    public ReportService(ReportRepository repository) {
        this.repository = repository;
    }

    // 전체 신고 목록 조회
    public List<Report> getAllReports() {
        return repository.findAll();
    }

    // 신고 등록
    public Report submitReport(Report report) {
        report.setReportedAt(LocalDateTime.now());
        report.setHandled(false);
        report.setStatus("PENDING");
        return repository.save(report);
    }

    // 신고 상태 처리 (Handled 처리)
    public Report markHandled(String id) {
        Report report = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("신고 내역을 찾을 수 없습니다."));
        report.setHandled(true);
        report.setStatus("HANDLED");
        return repository.save(report);
    }

    // 상태 직접 수정 (예: "REJECTED", "PENDING")
    public Report updateReportStatus(String id, String status) {
        Report report = repository.findById(id).orElseThrow();
        report.setStatus(status);
        return repository.save(report);
    }
}
