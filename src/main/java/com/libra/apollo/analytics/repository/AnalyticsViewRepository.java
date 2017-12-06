package com.libra.apollo.analytics.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libra.apollo.analytics.entity.AnalyticsView;

@Repository
public interface AnalyticsViewRepository extends JpaRepository<AnalyticsView, Long> {

}
