package com.libra.apollo.analytics.repository;

import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.libra.apollo.analytics.entity.AnalyticsView;

@Repository
@Transactional
public interface AnalyticsTemplateRepository extends JpaRepository<AnalyticsView, Long> {

}
