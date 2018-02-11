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
	
	@Query("SELECT sp.stockId as stockId, "
			+ "sp.stampDate as stampDate, "
			+ "sp.openPrice as openPrice, "
			+ "sp.highPrice as highPrice , "
			+ "sp.lowPrice as lowPrice, "
			+ "sp.closePrice as closePrice, "
			+ "sp.volume as volume, "
			+ "sp.closePrice1D as closePrice1D, "
			+ "sp.closePrice5D as closePrice5D , "
			+ "sp.closePrice1m as closePrice1m , "
			+ "sp.closePrice3M as closePrice3M, "
			+ "si.priceChange1m as priceChange1m, "
			+ "si.fairValue as fairValue, "
			+ "si.discountPremiumToFairValue as discountPremiumToFairValue , "
			+ "si.medianDiscountToFairValue as medianDiscountToFairValue , "
			+ "si.starRating as starRating , "
			+ "si.intrinsicValue as  intrinsicValue ,"
			+ "si.netDiscountMedianFairValue as netDiscountMedianFairValue, "
			+ "si.fairValueChange1m as fairValueChange1m, "
			+ "si.expectedReturn2m as expectedReturn2m, "
			+ "si.discountPremiumToIntrinsicValue as discountPremiumToIntrinsicValue, "
			+ "si.intrinsicValueChange3m as intrinsicValueChange3m, "
			+ "si.fairValueChange3m as fairValueChange3m, "
			+ "si.intrinsicValueChange1m as intrinsicValueChange1m, "
			+ "si.pctInFairValueRange as pctInFairValueRange"
			+ " FROM StockPrice sp, StockIndicator si WHERE sp.stockId = si.stockId AND sp.stampDate = si.stampDate AND sp.stockId = :stockId AND sp.stampDate >= :fromDate AND sp.stampDate <= :toDate")
	public List<ApolloIndicators> findApolloIndicatorsByStockIdAndBetweenStampDates(@Param("stockId") Long stockId, @Param("fromDate") Date fromDate, @Param("toDate") Date toDate );
	
	@Query("SELECT sp.stockId as stockId, "
			+ "sp.stampDate as stampDate, "
			+ "sp.openPrice as openPrice, "
			+ "sp.highPrice as highPrice , "
			+ "sp.lowPrice as lowPrice, "
			+ "sp.closePrice as closePrice, "
			+ "sp.volume as volume, "
			+ "sp.closePrice1D as closePrice1D, "
			+ "sp.closePrice5D as closePrice5D , "
			+ "sp.closePrice1m as closePrice1m , "
			+ "sp.closePrice3M as closePrice3M, "
			+ "si.priceChange1m as priceChange1m, "
			+ "si.fairValue as fairValue, "
			+ "si.discountPremiumToFairValue as discountPremiumToFairValue , "
			+ "si.medianDiscountToFairValue as medianDiscountToFairValue , "
			+ "si.netDiscountMedianFairValue as netDiscountMedianFairValue, "
			+ "si.fairValueChange1m as fairValueChange1m, "
			+ "si.expectedReturn2m as expectedReturn2m, "
			+ "si.discountPremiumToIntrinsicValue as discountPremiumToIntrinsicValue, "
			+ "si.intrinsicValueChange3m as intrinsicValueChange3m, "
			+ "si.fairValueChange3m as fairValueChange3m, "
			+ "si.intrinsicValueChange1m as intrinsicValueChange1m, "
			+ "si.pctInFairValueRange as pctInFairValueRange"
			+ " FROM StockPrice sp, StockIndicator si WHERE sp.stockId = si.stockId AND sp.stampDate = si.stampDate AND sp.stockId IN :stockIdList AND sp.stampDate >= :fromDate AND sp.stampDate <= :toDate")
	public List<ApolloIndicators> findApolloIndicatorsByStockIdsAndBetweenStampDates(@Param("stockIdList") Collection<Long> stockIdList, @Param("fromDate") Date fromDate, @Param("toDate") Date toDate );
}
