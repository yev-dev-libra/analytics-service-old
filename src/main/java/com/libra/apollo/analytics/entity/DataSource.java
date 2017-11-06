package com.libra.apollo.analytics.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "data_source")
@Data
@EqualsAndHashCode(callSuper = true)
public class DataSource extends AutoGeneratedId {

	/**
	 * 
	 */
	private static final long serialVersionUID = -437609539372574160L;
	
	@Embedded
	private Definition definition;
	
	@Enumerated(EnumType.STRING)
	private DataSourceType dataSourceType;
	
	@Column
	private String urlBase;
	
	@Column
	private String clazzName;
	
	@OneToMany(mappedBy="dataSource")
	private List<InvestmentStyleConditionDataSource> investmentStyleConditions;
	
	
}
