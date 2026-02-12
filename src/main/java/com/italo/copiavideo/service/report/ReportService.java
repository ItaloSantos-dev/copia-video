package com.italo.copiavideo.service.report;

import com.italo.copiavideo.DTO.response.ReportDTO;
import com.italo.copiavideo.model.report.RequestMetric;
import com.italo.copiavideo.model.report.SearchMetrics;
import com.italo.copiavideo.service.report.metrics.RequestMetricsService;
import com.italo.copiavideo.service.report.metrics.SearchMetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private SearchMetricsService searchMetricsService;

    @Autowired
    private RequestMetricsService requestMetricsService;


    public ReportDTO getReport(LocalDate initialDate, LocalDate finalDate){
        System.out.println(initialDate + " e "+ finalDate);
        List<SearchMetrics> searchMetrics = this.searchMetricsService.getAllSearchMetricsBetweenDay(initialDate, finalDate);
        List<RequestMetric> requestMetrics = this.requestMetricsService.getAllRequestMetricsBetweenDay(initialDate, finalDate);

        return new ReportDTO(requestMetrics, searchMetrics);

    }
}
