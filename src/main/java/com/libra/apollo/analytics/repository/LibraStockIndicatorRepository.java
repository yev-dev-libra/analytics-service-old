package com.libra.apollo.analytics.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.libra.apollo.analytics.entity.LibraStockIndicator;

@Repository
public interface LibraStockIndicatorRepository extends BaseRepository<LibraStockIndicator, Long>, JpaSpecificationExecutor<LibraStockIndicator>, LibraStockIndicatorRepositoryCustom {
	
	public List<LibraStockIndicator> findByStampDate(Date date);
	public List<LibraStockIndicator> findByStockId(Long stockId);
	public List<LibraStockIndicator> findByStockIdAndStampDate(Long stockId,Date date );
	public List<LibraStockIndicator> findByStockIdIn(List<Long> stockIdList);
	public List<LibraStockIndicator> findByStockIdInAndStampDate(List<Long> stockIdList,Date date );
	public Page<LibraStockIndicator> findByStockId(Long stockId, Pageable pageable);
	
	@Query("select max(stampDate) from LibraStockIndicator")
	public Date maxDate();
		
}
