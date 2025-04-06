package com.example.comprehensive.controller;

import com.example.comprehensive.entity.Report;
import com.example.comprehensive.service.ReportService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserReportController {

    private final ReportService reportService;

    public UserReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    // ✅ 사용자 신고 등록
    @PostMapping("/report")
    public Report reportContent(@RequestBody Report report) {
        return reportService.submitReport(report);
    }
}
