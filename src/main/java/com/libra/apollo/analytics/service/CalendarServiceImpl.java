package com.libra.apollo.analytics.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Multimap;
import com.libra.apollo.analytics.projection.MaxDateForStock;
import com.libra.apollo.analytics.repository.StockIndicatorRepository;
import com.libra.apollo.analytics.util.HashMultimapCollector;

@Service
public class CalendarServiceImpl implements CalendarService {

	
	@Autowired
	private StockIndicatorRepository stockIndicatorRepository; 

	@Override
	public Date getMaxStampDateForStock(Long stockId) {
		return stockIndicatorRepository.maxDateForStock(stockId);
	}

	@Override
	public Map<Date,Set<Long>> getMaxStampDateForStockIds(Collection<Long> stockIds, Date greaterThanStampDate) {
		
		List<MaxDateForStock> projection = stockIndicatorRepository.maxDateForStockAsProjection(stockIds, greaterThanStampDate );
		
		Map<Date,Set<Long>> maxDateStock = projection.stream().collect(Collectors.groupingBy(MaxDateForStock::getMaxStampDate,Collectors.mapping(MaxDateForStock::getStockId,Collectors.toSet())));
				
		return maxDateStock;
	}

	@Override
	public Map<Long,Date> getStockDateForStockIdAndDate(Collection<Long> stockIds, Date greaterThanStampDate) {
		
		List<MaxDateForStock> projection = stockIndicatorRepository.maxDateForStockAsProjection(stockIds, greaterThanStampDate );
		
		Map<Long,Date> stockDate = projection.stream().collect(Collectors.toMap(MaxDateForStock::getStockId, MaxDateForStock::getMaxStampDate));
		
		return stockDate;
	}
	
	@Override
	public Map<Date, Long> getDateStockForStockIdAndDate(Collection<Long> stockIds, Date greaterThanStampDate) {
		List<MaxDateForStock> projection = stockIndicatorRepository.maxDateForStockAsProjection(stockIds, greaterThanStampDate );
		
		Map<Date,Long> stockDate = projection.stream().collect(Collectors.toMap(MaxDateForStock::getMaxStampDate, MaxDateForStock::getStockId));
		
		return stockDate;
	}
	
	@Override
	public Date getMaxStampDateForStockIndicators() { 
		return stockIndicatorRepository.maxDate();
	}

	@Override
	public Multimap<Date, Long> getMaxStampDateForStockIdsAsMultimap(Collection<Long> stockIds, Date greaterThanStampDate) {
		
		List<MaxDateForStock> projection = stockIndicatorRepository.maxDateForStockAsProjection(stockIds, greaterThanStampDate );
		
		Multimap<Date, Long> multimap = projection.stream().collect(HashMultimapCollector.toMultimap(MaxDateForStock::getMaxStampDate,MaxDateForStock::getStockId));
		
		return multimap;
	}


	
	
	
}
