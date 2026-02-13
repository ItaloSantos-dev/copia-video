package com.italo.copiavideo.controller;

import com.italo.copiavideo.DTO.response.ReportServerMetricsDTO;
import com.italo.copiavideo.DTO.response.ReportUserMetricsDTO;
import com.italo.copiavideo.service.report.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private ReportService reportService;

    @GetMapping("/report/server-metrics")
    public ResponseEntity<ReportServerMetricsDTO> getReportServerMetrics(@RequestParam LocalDate di, LocalDate df){
        ReportServerMetricsDTO response = this.reportService.getReport(di, df);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/report/user-metrics")
    public ResponseEntity<ReportUserMetricsDTO> getReportUserMetrics(){
        ReportUserMetricsDTO response = this.reportService.getReportUser();
        return ResponseEntity.ok(response);
    }
}
