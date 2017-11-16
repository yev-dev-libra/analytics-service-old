package com.libra.apollo.analytics.repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.libra.apollo.analytics.entity.LibraStockIndicator;

@Repository
public interface LibraStockIndicatorRepositoryJpa extends BaseRepository<LibraStockIndicator, Long> {
	
	public List<LibraStockIndicator> findByStampDate(Date date);
	public List<LibraStockIndicator> findByStockId(Long stockId);
	public List<LibraStockIndicator> findByStockIdAndStampDate(Long stockId,Date date );
	public List<LibraStockIndicator> findByStockId(Long stockId, Sort sort);
	public Page<LibraStockIndicator> findByStockId(Long stockId, Pageable pageable);
	
		
}
