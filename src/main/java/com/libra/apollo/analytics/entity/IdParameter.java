package com.libra.apollo.analytics.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.springframework.data.jpa.domain.Specification;

import com.libra.apollo.analytics.specification.ValueParameter;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue(value = "ID_PARAMETER")
public class IdParameter extends Parameter {

	@Column(name = "id_value", nullable = true)
	private Long idValue;

	@Override
	public Object getValue() {
		return idValue;
	}

	public Long getIdValue() {
		return idValue;
	}

	public void setIdValue(Long idValue) {
		this.idValue = idValue;
	}

	@Override
	public int compareTo(ValueParameter o) {
		return this.getIdValue().compareTo(((IdParameter) o).getIdValue());
	}

	@Override
	public <T> Specification<T> getSpecification(ValueParameter parameter) {
		return super.getOperand().queryByBigDecimal(getFieldType(), this);

	}

	@Override
	public <T> Specification<T> getSpecification() {
		throw new UnsupportedOperationException();
	}

}
