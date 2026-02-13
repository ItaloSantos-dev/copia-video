package com.italo.copiavideo.repository;

import com.italo.copiavideo.model.report.SearchMetric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface SearchMetricRepository extends JpaRepository<SearchMetric, UUID> {
    SearchMetric findBySearchAndDate(String search, LocalDate date);

    List<SearchMetric> findAllSearchsByDateBetween(LocalDate initialDate, LocalDate finalDate);
}
