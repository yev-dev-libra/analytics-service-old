package com.libra.apollo.analytics.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "investment_style_datasource")
@Data
@EqualsAndHashCode(callSuper = true)
public class InvestmentStyleDataSource extends ProvidedId<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4703126140160197310L;

	@ManyToOne
	@JoinColumn(name="data_source_id", nullable=false)
	private DataSource dataSource;
	
	@ManyToOne
	@JoinColumn(name="investment_style_id", nullable=false)
	private InvestmentStyle investmentStyleCondition;

}
