package com.libra.apollo.analytics.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;


@Entity
@Table(name="investment_style", schema="analytics")
@Data
public class InvestmentStyle {

	
	@NotNull
	@Column
	private String name;
	
	@NotNull
	@Column
	private String description;
	
	@NotNull
	@Column
	private Integer priority;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private InvestmentStyle parentInvestmentStyle;
	
	
}
