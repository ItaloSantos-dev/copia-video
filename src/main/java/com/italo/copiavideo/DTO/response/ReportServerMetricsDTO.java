package com.italo.copiavideo.DTO.response;

import com.italo.copiavideo.model.report.RequestMetric;
import com.italo.copiavideo.model.report.SearchMetric;

import java.util.List;

public record ReportServerMetricsDTO(List<RequestMetric> requestMetrics, List<SearchMetric> searchMetrics) {
}
