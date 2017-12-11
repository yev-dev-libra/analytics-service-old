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
@Table(name = "analytics_investment_style_parameter", schema = "analytics")
public class InvestmentStyleParameter implements Serializable, Comparable<InvestmentStyleParameter> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "investment_style_id")
	private InvestmentStyle investmentStyle;

	@Embedded
	private Priority priority;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "parameter_id", nullable = true)
	private Parameter parameter;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "data_source_id", nullable = true)
	private DataSource dataSource;

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

	public Parameter getParameter() {
		return parameter;
	}

	public void setParameter(Parameter parameter) {
		this.parameter = parameter;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public int compareTo(InvestmentStyleParameter o) {
		return this.getPriority().compareTo(o.getPriority());
	}

	@Override
	public String toString() {
		return "InvestmentStyleParameter [id=" + id + ", investmentStyle=" + investmentStyle + ", priority=" + priority
				+ ", property=" + parameter + "]";
	}

}
