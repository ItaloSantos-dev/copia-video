package com.italo.copiavideo.DTO.response;

import com.italo.copiavideo.model.report.RequestMetric;
import com.italo.copiavideo.model.report.SearchMetrics;

import java.util.List;

public record ReportServerMetricsDTO(List<RequestMetric> requestMetrics, List<SearchMetrics> searchMetrics) {
}
