package com.libra.apollo.analytics.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Tuple;

import com.libra.apollo.analytics.engine.core.ValueDataFieldType;

public class Utils {

	public static List<Map<ValueDataFieldType,Object>> fromTupleToValuesDataFieldMap(List<Tuple> tuples, List<ValueDataFieldType> fields){
		List<Map<ValueDataFieldType,Object>> values = new ArrayList<>();
		
		for (Tuple tuple : tuples) {
			
			Map<ValueDataFieldType,Object> valuesDataMap = new HashMap<>(fields.size());
			
			for(ValueDataFieldType field : fields ) {
				String fieldName = field.getFieldName();
				valuesDataMap.put(field, tuple.get(fieldName));
			}
			
			values.add(valuesDataMap);
		}
		
		return values;
	}

	public static Iterable<List<Object>> fromTupleToList(List<Tuple> tuples, List<ValueDataFieldType> fields) { 
		
		List<List<Object>> values = new ArrayList<>(tuples.size());

		for (Tuple tuple : tuples) {

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
	}
}
