package com.libra.apollo.analytics.engine;

import java.sql.Date;

import org.junit.Test;

import com.libra.apollo.analytics.engine.ValueDate.OperandDate;
import com.libra.apollo.analytics.entity.BigDecimalParameter;

public class PipelineTest {

	@Test
	public void testAnalyticsPipeline() {
		
		Date date = null;
		//Build a base request
		Value whereClauseValue = new ValueDate(date, OperandDate.EQUAL);
		
		//Build the main request
		
		Value andClauseValue = new BigDecimalParameter();
	}
}
