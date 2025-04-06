package com.example.comprehensive.controller.admin;

import com.example.comprehensive.entity.Report;
import com.example.comprehensive.service.ReportService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/reports")
public class AdminReportController {

    private final ReportService reportService;

    public AdminReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    // ✅ 모든 신고된 콘텐츠 목록
    @GetMapping
    public List<Report> getAllReports() {
        return reportService.getAllReports();
    }

    // ✅ 특정 신고 처리 완료
    @PutMapping("/{id}/handle")
    public Report handleReport(@PathVariable String id) {
        return reportService.markHandled(id);
    }
}
