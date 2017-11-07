package com.libra.apollo.analytics.entity;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@SuppressWarnings("serial")
@Entity
@Table(name = "investment_style_condition")
@Data
public class InvestmentStyleCondition implements Serializable {

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO) 
	protected Long id;
	
	@ManyToOne
	@JoinColumn(name = "investment_style_id")
	private InvestmentStyle investmentStyle;

	@Embedded
	private Priority priority;

	@ManyToOne(optional = true)
	@JoinColumn(name = "condition_id", nullable = true)
	private Condition condition;
}
