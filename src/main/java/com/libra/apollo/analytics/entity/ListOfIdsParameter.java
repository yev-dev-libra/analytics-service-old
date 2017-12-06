package com.libra.apollo.analytics.entity;

import java.util.List;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;

import org.springframework.data.jpa.domain.Specification;

import com.libra.apollo.analytics.entity.enums.OperandListOfIds;
import com.libra.apollo.analytics.specification.ValueParameter;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue(value = "LIST_IDS_PARAMETER")
public class ListOfIdsParameter extends QueryParameter {

	@Column(name = "operand", nullable = true)
	@Enumerated(EnumType.STRING)
	private OperandListOfIds operand;

	@Column(name = "id_value", nullable = true)
	private Long idValue;

	@OneToMany(mappedBy = "listOfIdsParameter")
	private List<IdParameter> ids;

	public OperandListOfIds getOperand() {
		return operand;
	}

	public void setOperand(OperandListOfIds operand) {
		this.operand = operand;
	}

	public Long getIdValue() {
		return idValue;
	}

	public void setIdValue(Long idValue) {
		this.idValue = idValue;
	}

	public Optional<Object> getValue() {
		return Optional.ofNullable(ids);
	}

	public List<IdParameter> getIds() {
		return ids;
	}

	public void setIds(List<IdParameter> ids) {
		this.ids = ids;
	}

	@Override
	public int compareTo(ValueParameter o) {
		return 0;
	}

	@Override
	public <T> Specification<T> getSpecification(ValueParameter parameter) {
		return this.getOperand().query(getFieldType(), this);

	}

}
