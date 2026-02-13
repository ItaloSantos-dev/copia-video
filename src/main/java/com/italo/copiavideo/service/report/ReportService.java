package com.italo.copiavideo.service.report;

import com.italo.copiavideo.DTO.internal.UserWithAmountIdeasDTO;
import com.italo.copiavideo.DTO.response.ReportServerMetricsDTO;
import com.italo.copiavideo.DTO.response.ReportUserMetricsDTO;
import com.italo.copiavideo.DTO.response.UserDTO;
import com.italo.copiavideo.model.User;
import com.italo.copiavideo.model.report.RequestMetric;
import com.italo.copiavideo.model.report.SearchMetric;
import com.italo.copiavideo.service.UserService;
import com.italo.copiavideo.service.report.metrics.RequestMetricsService;
import com.italo.copiavideo.service.report.metrics.SearchMetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private SearchMetricsService searchMetricsService;

    @Autowired
    private UserService userService;

    @Autowired
    private RequestMetricsService requestMetricsService;
    
    public ReportUserMetricsDTO getReportUser(){
        List<User> lastUserstemp = this.userService.getLastedCreateusers();

        List<UserDTO> lastUsers = lastUserstemp.stream().map(user ->
                        new UserDTO(user.getId(), user.getName(), user.getEmail(), user.getRole(), user.getIdeas().size(), user.getCreated_at())).toList();

        List<UserWithAmountIdeasDTO> usersTemp = this.userService.getUsersWithMostAmountIdeas();

        List<UserDTO> usersWithMostAmountIdeas = usersTemp.stream().map(user ->
                        new UserDTO(user.user().getId(), user.user().getName(), user.user().getEmail(), user.user().getRole(), user.totalIdeas(), user.user().getCreated_at())).toList();

        return new ReportUserMetricsDTO(lastUsers, usersWithMostAmountIdeas);
    }

    public ReportServerMetricsDTO getReportServer(LocalDate initialDate, LocalDate finalDate){
        System.out.println(initialDate + " e "+ finalDate);
        List<SearchMetric> searchMetrics = this.searchMetricsService.getAllSearchMetricsBetweenDay(initialDate, finalDate);
        List<RequestMetric> requestMetrics = this.requestMetricsService.getAllRequestMetricsBetweenDay(initialDate, finalDate);

        return new ReportServerMetricsDTO(requestMetrics, searchMetrics);

    }
}
