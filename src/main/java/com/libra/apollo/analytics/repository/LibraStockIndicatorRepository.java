package com.libra.apollo.analytics.repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.libra.apollo.analytics.entity.LibraStockIndicator;
import com.libra.apollo.analytics.projection.MaxDateForStock;

@Repository
public interface LibraStockIndicatorRepository extends BaseRepository<LibraStockIndicator, Long>, JpaSpecificationExecutor<LibraStockIndicator>, LibraStockIndicatorRepositoryCustom {
	
	public List<LibraStockIndicator> findByStampDate(Date date);
	public List<LibraStockIndicator> findByStockId(Long stockId);
	public List<LibraStockIndicator> findByStockIdAndStampDate(Long stockId,Date date );
	public List<LibraStockIndicator> findByStockIdAndStampDateBetween(Long stockId,Date fromDate, Date toDate );
	public Page<LibraStockIndicator> findByStockId(Long stockId, Pageable pageable);
	public List<LibraStockIndicator> findByStockIdInAndStampDateEquals(List<Long> stockIdList,Date date);
	public List<LibraStockIndicator> findByStockIdInAndStampDateIsAfter(List<Long> stockIdList,Date date);
	public List<LibraStockIndicator> findByStockIdInAndStampDateEquals(List<Long> stockIdList,Date date,Pageable pageable);
	public List<LibraStockIndicator> findByStockIdInAndStampDateIsBetween(List<Long> stockIdList,Date fromDate, Date toDate );
	public List<LibraStockIndicator> findByStockIdInAndStampDateIsBetween(List<Long> stockIdList,Date fromDate, Date toDate, Pageable pageable );
	
	@Query("select max(stampDate) from LibraStockIndicator")
	public Date maxDate();
	
	@Query("select max(stampDate) from LibraStockIndicator WHERE stockId = :stockId")
	public Date maxDateForStock(@Param("stockId") Long stockId);
	
	@Query("select MAX(stampDate) from LibraStockIndicator WHERE stockId IN :stockIdList GROUP BY stockId ORDER BY MAX(stampDate)")
	public List<Date> maxDatePerStockIds(@Param("stockIdList") List<Long> stockIdList);
	
	@Query("select MAX(stampDate) as maxStampDate, stockId as stockId from LibraStockIndicator WHERE stockId IN :stockIdList GROUP BY stockId ORDER BY MAX(stampDate)")
	public List<MaxDateForStock> maxDateForStockAsProjection(@Param("stockIdList") Collection<Long> stockIdList);
	
	@Query("select MAX(stampDate) as maxStampDate, stockId as stockId from LibraStockIndicator WHERE stockId IN :stockIdList AND stampDate >= :greaterThanStampDate GROUP BY stockId ORDER BY MAX(stampDate)")
	public List<MaxDateForStock> maxDateForStockAsProjection(@Param("stockIdList") Collection<Long> stockIdList, @Param("greaterThanStampDate") Date greaterThanStampDate);
		
}
