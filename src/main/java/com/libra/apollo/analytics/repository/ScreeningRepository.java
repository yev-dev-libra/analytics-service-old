package com.libra.apollo.analytics.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.libra.apollo.analytics.entity.LibraStockIndicator;
import com.libra.apollo.analytics.projection.ScreeningResult;

@Repository
public interface ScreeningRepository extends BaseRepository<LibraStockIndicator, Long>, JpaSpecificationExecutor<LibraStockIndicator>, ScreeningRepositoryCustom {

	public List<ScreeningResult> findByStampDate(Date date);
	public List<ScreeningResult> findByStockId(Long stockId);
	public List<ScreeningResult> findByStockIdAndStampDate(Long stockId,Date date );
	public List<ScreeningResult> findByStockIdIn(List<Long> stockIdList);
	public List<ScreeningResult> findByStockIdInAndStampDate(List<Long> stockIdList,Date date );
	
}
