package com.libra.apollo.analytics.repository;

import org.springframework.stereotype.Repository;

import com.libra.apollo.analytics.entity.QueryParameter;

@Repository
public interface QueryParameterRepositoryCustom {

	public void saveBigDecimalQueryParameter(QueryParameter parameter);

	public void saveDoubleQueryParameter(QueryParameter parameter);

	public void saveDataQueryParameter(QueryParameter parameter);
}
