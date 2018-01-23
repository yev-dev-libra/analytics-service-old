package com.libra.apollo.analytics.engine.converter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Tuple;

import com.libra.apollo.analytics.engine.core.ValueDataFieldType;

public class AnalyticsConveterConveter {

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
	

}
