package com.libra.apollo.analytics.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.libra.apollo.analytics.entity.LibraStockIndicator;

@Repository
@Transactional
public interface LibraStockIndicatorRepository extends BaseRepository<LibraStockIndicator, Long> {
	
	Optional<LibraStockIndicator> findByStampDate(Date date);
	List<LibraStockIndicator> findById(Long id, Sort sort);
	Page<LibraStockIndicator> findById(Long id, Pageable pageable);

}
