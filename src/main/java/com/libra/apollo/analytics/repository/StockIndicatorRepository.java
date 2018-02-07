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

import com.libra.apollo.analytics.entity.StockIndicator;
import com.libra.apollo.analytics.projection.ApolloIndicators;
import com.libra.apollo.analytics.projection.MaxDateForStock;

@Repository
public interface StockIndicatorRepository extends BaseRepository<StockIndicator, Long>, JpaSpecificationExecutor<StockIndicator>, StockIndicatorRepositoryCustom {
	
	public List<StockIndicator> findByStampDate(Date date);
	public List<StockIndicator> findByStockId(Long stockId);
	public List<StockIndicator> findByStockIdAndStampDate(Long stockId,Date date );
	public List<StockIndicator> findByStockIdAndStampDateBetween(Long stockId,Date fromDate, Date toDate );
	public Page<StockIndicator> findByStockId(Long stockId, Pageable pageable);
	public List<StockIndicator> findByStockIdInAndStampDateEquals(List<Long> stockIdList,Date date);
	public List<StockIndicator> findByStockIdInAndStampDateIsAfter(List<Long> stockIdList,Date date);
	public List<StockIndicator> findByStockIdInAndStampDateEquals(List<Long> stockIdList,Date date,Pageable pageable);
	public List<StockIndicator> findByStockIdInAndStampDateIsBetween(List<Long> stockIdList,Date fromDate, Date toDate );
	public List<StockIndicator> findByStockIdInAndStampDateIsBetween(List<Long> stockIdList,Date fromDate, Date toDate, Pageable pageable );
	
	
	@Query("select max(stampDate) from StockIndicator")
	public Date maxDate();
	
	@Query("select max(stampDate) from StockIndicator WHERE stockId = :stockId")
	public Date maxDateForStock(@Param("stockId") Long stockId);
	
	@Query("select MAX(stampDate) from StockIndicator WHERE stockId IN :stockIdList GROUP BY stockId ORDER BY MAX(stampDate)")
	public List<Date> maxDatePerStockIds(@Param("stockIdList") List<Long> stockIdList);
	
	@Query("select MAX(stampDate) as maxStampDate, stockId as stockId from StockIndicator WHERE stockId IN :stockIdList GROUP BY stockId ORDER BY MAX(stampDate)")
	public List<MaxDateForStock> maxDateForStockAsProjection(@Param("stockIdList") Collection<Long> stockIdList);
	
	@Query("select MAX(stampDate) as maxStampDate, stockId as stockId from StockIndicator WHERE stockId IN :stockIdList AND stampDate >= :greaterThanStampDate GROUP BY stockId ORDER BY MAX(stampDate)")
	public List<MaxDateForStock> maxDateForStockAsProjection(@Param("stockIdList") Collection<Long> stockIdList, @Param("greaterThanStampDate") Date greaterThanStampDate);
//	
//	@Query("SELECT sp.stockId, sp.stampDate, sp.openPrice , sp.highPrice , sp.lowPrice , sp.closePrice , sp.volume , sp.closePrice1D , sp.closePrice5D , sp.closePrice1m , sp.closePrice3M, si.priceChange1m, si.discountPremiumToFairValue , si.netDiscountMedianFairValue , si.fairValueChange1m , si.expectedReturn2m , si.discountPremiumToIntrinsicValue , si.intrinsicValueChange3m , si.fairValueChange3m , si.intrinsicValueChange1m , si.pctInFairValueRange "
//			+ " FROM StockPrice sp INNER JOIN StockIndicator si ON sp.stockId = si.stockId WHERE sp.stockId = :stockId AND sp.stampDate BETWEEN :fromDate AND :toDate")
//	public List<ApolloIndicators> findApolloIndicatorsByStockIdInAndStampDateIsBetween(@Param("stockId") Long stockId, @Param("fromDate") Date fromDate, @Param("toDate") Date toDate );
//	
//	@Query("SELECT sp.stockId, sp.stampDate, sp.openPrice , sp.highPrice , sp.lowPrice , sp.closePrice , sp.volume , sp.closePrice1D , sp.closePrice5D , sp.closePrice3M, si.priceChange1m, si.discountPremiumToFairValue , si.netDiscountMedianFairValue , si.fairValueChange1m , si.expectedReturn2m , si.discountPremiumToIntrinsicValue , si.intrinsicValueChange3m , si.fairValueChange3m , si.intrinsicValueChange1m , si.pctInFairValueRange "
//			+ " FROM StockPrice sp INNER JOIN StockIndicator si ON sp.stockId = si.stockId WHERE sp.stockId IN :stockIdList AND sp.stampDate BETWEEN :fromDate AND :toDate")
//	public List<ApolloIndicators> findApolloIndicatorsByStockIdsInAndStampDateIsBetween(@Param("stockIdList") Collection<Long> stockIdList, @Param("fromDate") Date fromDate, @Param("toDate") Date toDate );
}
