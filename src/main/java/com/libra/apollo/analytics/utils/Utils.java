package com.libra.apollo.analytics.utils;

import java.io.Serializable;
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

	public static List<List<Object>> fromTupleToList(List<Tuple> tuples, List<ValueDataFieldType> fields) { 
		
		List<List<Object>> values = new ArrayList<>(tuples.size());

		for (Tuple tuple : tuples) {

			List<? extends Serializable> value = new ArrayList<>();

			for (ValueDataFieldType field : fields) { //TODO: populate notification is something hasn't been matched
				String fieldName = field.getFieldName();
				Serializable o = (Serializable) tuple.get(fieldName);
			}

		}

		return values;
	}
}
