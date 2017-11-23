package com.libra.apollo.analytics.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.springframework.data.jpa.domain.Specification;

import com.libra.apollo.analytics.specification.ValueParameter;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue(value="DATE_PARAMETER")
public class DateParameter extends Parameter {

	@Column(name = "date_value", nullable=true)
	private Date dateValue;

	@Override
	public Object getValue() {
		return dateValue;
	}

	public Date getDateValue() {
		return dateValue;
	}

	public void setDateValue(Date dateValue) {
		this.dateValue = dateValue;
	}

	@Override
	public int compareTo(ValueParameter o) {
		return this.getDateValue().compareTo(((DateParameter)o).getDateValue());
	}


	@Override
	public <T> Specification<T> getSpecification(ValueParameter parameter) {
		return super.getOperand().queryByDate(getFieldType());
		
	}

	@Override
	public <T> Specification<T> getSpecification() {
		return super.getOperand().queryByDate(getFieldType(), this);
	}


	

	
}
