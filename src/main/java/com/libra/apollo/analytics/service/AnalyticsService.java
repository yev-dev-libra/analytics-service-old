package com.libra.apollo.analytics.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Tuple;

import com.libra.apollo.analytics.engine.core.ValueDataFieldType;
import com.libra.apollo.analytics.entity.QueryParameter;

public interface AnalyticsService {
	
	public List<Tuple> getScreeningResults(final Collection<Long> instrumentIds, final List<QueryParameter> queryParams,  List<ValueDataFieldType> requestedFields, final Date runDate );
	public List<Tuple> getScreeningResults(final Collection<Long> instrumentIds, final List<QueryParameter> queryParams,  List<ValueDataFieldType> requestedFields);

}
