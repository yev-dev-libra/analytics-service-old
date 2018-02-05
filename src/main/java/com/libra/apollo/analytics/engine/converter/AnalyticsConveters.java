package com.libra.apollo.analytics.engine.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.persistence.Tuple;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.libra.apollo.analytics.dto.PortfolioScreenerResultDTO;
import com.libra.apollo.analytics.dto.PortfolioScreenerResultDTO.PortfolioStockValues;
import com.libra.apollo.analytics.engine.context.AnalyticsContext;
import com.libra.apollo.analytics.engine.context.PortfolioScreenerContext;
import com.libra.apollo.analytics.engine.core.ValueDataFieldType;
import com.libra.apollo.analytics.engine.result.ScreenerResult;
import com.libra.apollo.analytics.entity.AnalyticsType;
import com.libra.apollo.analytics.entity.InvestmentStyle;

public class AnalyticsConveters {

	public static <F,T> Converter<List<Tuple>, List<EnumMap<ValueDataFieldType,?>>> fromTupleToValuesDataFieldMap(List<ValueDataFieldType> fields){
		return (from) -> {
			
			List<EnumMap<ValueDataFieldType,?>> values = new ArrayList<>();
			
			for (Tuple tuple : from) {
				
				EnumMap<ValueDataFieldType,Object> valuesDataMap = new EnumMap<>(ValueDataFieldType.class);
				
				for(ValueDataFieldType field : fields ) {
					String fieldName = field.getFieldName();
					valuesDataMap.put(field, tuple.get(fieldName));
				}
				values.add(valuesDataMap);
			}
			return values;
		};
		
	}
	
	public static <F,T> Converter<List<Tuple>, Collection<List<?>>> fromTupleToList(List<ValueDataFieldType> fields){
		
		return (from) -> {
			
			List<List<?>> values = new ArrayList<>(from.size());

			for (Tuple tuple : from) {

				List<Object> value = new ArrayList<>(fields.size());

				for (ValueDataFieldType field : fields) { 
					
					Object extractedValue = null;
					
					String fieldName = field.getFieldName();
					Class<?> clazz = field.getClazz();
					
					if(clazz != null) {
						extractedValue = tuple.get(fieldName,clazz);
					}
					else {
						extractedValue =  tuple.get(fieldName);
					}
					
					value.add(extractedValue);
				}
				
				values.add(value);
			}

			return values;
			
		};
	}
	
	/*
	 * If you have to maintain this code and I am not around... please don't swear too much. Complexity comes with flexibility. 
	 * This is part of the business requirements. When the definitions are too vague and you have to come up when the specifications yourself!
	 * P.S. Good Luck!:)
	 */
	public static <F,T> Converter<AnalyticsContext,PortfolioScreenerResultDTO > fromScreenerResultToDto(){
		return (from)-> {
			Preconditions.checkArgument(from instanceof PortfolioScreenerContext, "Passed argument should be of type PortfolioScreenerContext");
			
			final PortfolioScreenerContext context = (PortfolioScreenerContext) from;
			final ScreenerResult result = context.getResult();
			final Map<Long,Collection<Long>> stockPortfolios = context.getStockPortfolios();
			final List<Long> portfolioIds = result.getPortfolioIds();
			final InvestmentStyle style = result.getInvestmentStyle();
			
			final List<ValueDataFieldType> requestedFields = result.getRequestedFields();
			final List<ValueDataFieldType> parameters = result.getParameters();
			final AnalyticsType analyticsType = result.getAnalyticsType();
			final String investmentStyleName = style.getDefinition().getName();
			final Long investmentStyleId = style.getId();

			List<EnumMap<ValueDataFieldType,?>> results = result.getResultValues(); 
			
			final PortfolioScreenerResultDTO dto = new PortfolioScreenerResultDTO();
			dto.setPortfolioIds(portfolioIds);
			dto.setInvestmentStyleId(investmentStyleId);
			dto.setInvestmentStyleName(investmentStyleName);
			dto.setAnalyticsType(analyticsType);
			dto.setRunType(result.getRunType());
			dto.setParameters(parameters);
			dto.setRequestedFields(requestedFields);
			
			Map<Long, List<PortfolioStockValues>> resultValues = Maps.newHashMapWithExpectedSize(portfolioIds.size()); //Allocating the size 
			
			
			for(EnumMap<ValueDataFieldType,?> value : results) {
				
				Long stockId = (Long)value.get(ValueDataFieldType.STOCK_ID);
				
				final PortfolioStockValues portfolioStockValues = new PortfolioStockValues(stockId, value);

				final Collection<Long> portfolioIdsForThisStock = stockPortfolios.get(stockId);
				
				for(Long portfolioId : portfolioIdsForThisStock) {
					
					List<PortfolioStockValues> values = null;

					if(resultValues.containsKey(portfolioId)) {
						values = resultValues.get(portfolioId);
					}
					else {
						values = new LinkedList<>();
					}

					values.add(portfolioStockValues);
					
					resultValues.put(portfolioId, values);
				}
			}
			
			dto.setPortfolios(resultValues);
			
			
			return dto;
		};
	}
	

}
