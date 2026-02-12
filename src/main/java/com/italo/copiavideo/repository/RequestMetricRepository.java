package com.italo.copiavideo.repository;

import com.italo.copiavideo.model.report.RequestMetric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface RequestMetricRepository extends JpaRepository<RequestMetric, UUID> {

    RequestMetric findByRouteAndMethodAndDate(String route, String method, LocalDate date);
}
