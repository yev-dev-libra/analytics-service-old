package com.libra.apollo.analytics.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libra.apollo.analytics.projection.MaxDateForStock;
import com.libra.apollo.analytics.repository.StockIndicatorRepository;

@Service
public class CalendarServiceImpl implements CalendarService {

	
	@Autowired
	private StockIndicatorRepository StockIndicatorRepository; 

	@Override
	public Date getMaxStampDateForStock(Long stockId) {
		return StockIndicatorRepository.maxDateForStock(stockId);
	}

	@Override
	public Map<Date,Set<Long>> getMaxStampDateForStockIds(Collection<Long> stockIds, Date greaterThanStampDate) {
		
		List<MaxDateForStock> projection = StockIndicatorRepository.maxDateForStockAsProjection(stockIds, greaterThanStampDate );
		
		Map<Date,Set<Long>> maxDateStock = projection.stream().collect(Collectors.groupingBy(MaxDateForStock::getMaxStampDate,Collectors.mapping(MaxDateForStock::getStockId,Collectors.toSet())));
				
		return maxDateStock;
	}

	@Override
	public Map<Long,Date> getStockDateForStockIdAndDate(Collection<Long> stockIds, Date greaterThanStampDate) {
		
		List<MaxDateForStock> projection = StockIndicatorRepository.maxDateForStockAsProjection(stockIds, greaterThanStampDate );
		
		Map<Long,Date> stockDate = projection.stream().collect(Collectors.toMap(MaxDateForStock::getStockId, MaxDateForStock::getMaxStampDate));
		
		return stockDate;
	}
	
	@Override
	public Map<Date, Long> getDateStockForStockIdAndDate(Collection<Long> stockIds, Date greaterThanStampDate) {
		List<MaxDateForStock> projection = StockIndicatorRepository.maxDateForStockAsProjection(stockIds, greaterThanStampDate );
		
		Map<Date,Long> stockDate = projection.stream().collect(Collectors.toMap(MaxDateForStock::getMaxStampDate, MaxDateForStock::getStockId));
		
		return stockDate;
	}
	
	@Override
	public Date getMaxStampDateForStockIndicators() { 
		return StockIndicatorRepository.maxDate();
	}


	
	
	
}
