import { RequestMetric } from "./metrics/request-metric";
import { SearchMetric } from "./metrics/search-metric";

export interface ReportServerMetrics {
    requestMetrics:RequestMetric[],
    searchMetrics:SearchMetric[]
}