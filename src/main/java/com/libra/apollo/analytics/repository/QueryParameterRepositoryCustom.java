package com.libra.apollo.analytics.repository;

import java.math.BigDecimal;

import org.springframework.stereotype.Repository;

import com.libra.apollo.analytics.entity.QueryParameter;

@Repository
public interface QueryParameterRepositoryCustom {

	public void saveBigDecimalQueryParameter(QueryParameter<BigDecimal> parameter);

	public void saveDoubleQueryParameter(QueryParameter<Double> parameter);

	public void saveDataQueryParameter(QueryParameter<Double> parameter);
}
