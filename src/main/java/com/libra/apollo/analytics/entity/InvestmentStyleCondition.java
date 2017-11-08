package com.libra.apollo.analytics.entity;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "investment_style_condition")
public class InvestmentStyleCondition implements Serializable, Comparable<InvestmentStyleCondition> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "investment_style_id")
	private InvestmentStyle investmentStyle;

	@Embedded
	private Priority priority;

	@ManyToOne(optional = true)
	@JoinColumn(name = "condition_id", nullable = true)
	private Condition condition;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public InvestmentStyle getInvestmentStyle() {
		return investmentStyle;
	}

	public void setInvestmentStyle(InvestmentStyle investmentStyle) {
		this.investmentStyle = investmentStyle;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	@Override
	public int compareTo(InvestmentStyleCondition o) {
		return this.getPriority().compareTo(o.getPriority());
	}

	public InvestmentStyleCondition(Long id, Priority priority) {
		super();
		this.id = id;
		this.priority = priority;
	}
	
	
}
