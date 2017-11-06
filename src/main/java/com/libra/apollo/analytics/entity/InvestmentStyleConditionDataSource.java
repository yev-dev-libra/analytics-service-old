package com.libra.apollo.analytics.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "investment_style_condition_datasource")
@Data
@EqualsAndHashCode(callSuper = true)
public class InvestmentStyleConditionDataSource extends ProvidedId<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4703126140160197310L;

	
	@Embedded
	private Definition definition;

}
