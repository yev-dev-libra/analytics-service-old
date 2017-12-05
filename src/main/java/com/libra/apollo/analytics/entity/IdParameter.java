package com.libra.apollo.analytics.entity;

import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.jpa.domain.Specification;

import com.libra.apollo.analytics.entity.enums.OperandId;
import com.libra.apollo.analytics.specification.ValueParameter;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue(value = "ID_PARAMETER")
public class IdParameter extends Parameter {

	@Column(name = "operand", nullable = true)
	@Enumerated(EnumType.STRING)
	private OperandId operand;

	@Column(name = "id_value", nullable = true)
	private Long idValue;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "list_of_id_parameter", referencedColumnName = "id", nullable = true)
	private ListOfIdsParameter listOfIdsParameter;

	@Override
	public Optional<Object> getValue() {
		return Optional.ofNullable(idValue);
	}

	public OperandId getOperand() {
		return operand;
	}

	public void setOperand(OperandId operand) {
		this.operand = operand;
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
		return this.getOperand().query(getFieldType(), this);

	}


}
