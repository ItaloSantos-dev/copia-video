package com.italo.copiavideo.infra.interceptors;

import com.italo.copiavideo.service.report.metrics.RequestMetricsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RequestMetricsInterceptor implements HandlerInterceptor {
    @Autowired
    private RequestMetricsService requestMetricsService;

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exception){
        String route = request.getRequestURI();
        String method = request.getMethod();

        this.requestMetricsService.saveTheRequest(route, method);
    }
}
