package com.italo.copiavideo.controller;

import com.italo.copiavideo.DTO.response.ReportDTO;
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
    public ResponseEntity<ReportDTO> getReportServerMetrics(@RequestParam LocalDate di, LocalDate df){
        ReportDTO response = this.reportService.getReport(di, df);
        return ResponseEntity.ok(response);
    }
}
