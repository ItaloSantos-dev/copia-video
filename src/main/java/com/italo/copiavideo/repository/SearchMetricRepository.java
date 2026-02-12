package com.italo.copiavideo.repository;

import com.italo.copiavideo.model.report.SearchMetrics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface SearchMetricRepository extends JpaRepository<SearchMetrics, UUID> {
    SearchMetrics findBySearchAndDate(String search, LocalDate date);
}
