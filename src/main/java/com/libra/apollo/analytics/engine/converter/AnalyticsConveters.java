package com.libra.apollo.analytics.engine.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Tuple;

import com.libra.apollo.analytics.dto.PortfolioScreenerResultDTO;
import com.libra.apollo.analytics.engine.core.ValueDataFieldType;
import com.libra.apollo.analytics.engine.result.ScreenerResult;
import com.libra.apollo.analytics.entity.InvestmentStyle;

public class AnalyticsConveters {

	public static <F,T> Converter<List<Tuple>, List<Map<ValueDataFieldType,Object>>> fromTupleToValuesDataFieldMap(List<ValueDataFieldType> fields){
		return (from) -> {
			
			List<Map<ValueDataFieldType,Object>> values = new ArrayList<>();
			
			for (Tuple tuple : from) {
				
				Map<ValueDataFieldType,Object> valuesDataMap = new HashMap<>(fields.size());
				
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
	
	public static <F,T> Converter<ScreenerResult,PortfolioScreenerResultDTO > fromScreenerResultToDto(){
		return (from)-> {
			
			final PortfolioScreenerResultDTO dto = new PortfolioScreenerResultDTO();
			
			final List<ValueDataFieldType> requestedFields = from.getRequestedFields();
			final List<ValueDataFieldType> parameters = from.getParameters();
			final Collection<?> results = from.getResults();
			
			final List<Long> portfolioIds = from.getPortfolioIds();
			
			final String analyticsType = String.valueOf(from.getAnalyticsType());
			final String runType = String.valueOf(from.getRunType());
			final InvestmentStyle style = from.getInvestmentStyle();
			
			dto.setPortfolioIds(portfolioIds);
			dto.setAnalyticsType(analyticsType);
			dto.setInvestmentStyleId(style.getId());
			dto.setRunType(runType);
			dto.setInvestmentStyleName(style.getDefinition().getName());
			
			return dto;
		};
	}

}
