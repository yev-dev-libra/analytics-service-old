package com.libra.apollo.analytics.entity;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@SuppressWarnings("serial")
@Entity
@Table(name="investment_style_criterias", schema="analytics")
@Data
public class InvestmentStyleCriteria implements Serializable {

	public enum InvestmentStyleCriteriaType{ PARAMETER,OPERATOR}
	
	@Enumerated(EnumType.STRING)
	private InvestmentStyleCriteriaType type;
	
	@Id
	@ManyToOne
	@JoinColumn(name="investment_style_id")
	private InvestmentStyle investmentStyle;
	
	
	@Enumerated(EnumType.STRING)
	private Operation operator;
	
	@Embedded
	private Priority priority;
	
	@ManyToOne(optional=true)
	@JoinColumn(name="criteria_id", nullable=true)
	private Criteria criteria;
}
