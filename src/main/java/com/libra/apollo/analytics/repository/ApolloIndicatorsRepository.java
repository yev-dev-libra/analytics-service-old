package com.libra.apollo.analytics.repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.libra.apollo.analytics.projection.ApolloIndicators;


public interface ApolloIndicatorsRepository {

	public List<ApolloIndicators> findApolloIndicatorsByStockIdAndBetweenStampDates(@Param("stockId") Long stockId, @Param("fromDate") Date fromDate, @Param("toDate") Date toDate );
	
	public List<ApolloIndicators> findApolloIndicatorsByStockIdsAndBetweenStampDates(@Param("stockIdList") Collection<Long> stockIdList, @Param("fromDate") Date fromDate, @Param("toDate") Date toDate );
}
