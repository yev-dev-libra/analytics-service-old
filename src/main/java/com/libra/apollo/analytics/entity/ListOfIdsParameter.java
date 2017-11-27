package com.libra.apollo.analytics.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.springframework.data.jpa.domain.Specification;

import com.libra.apollo.analytics.specification.ValueParameter;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue(value = "LIST_IDS_PARAMETER")
public class ListOfIdsParameter extends Parameter {

	@Column(name = "id_value", nullable = true)
	private Long idValue;
	
	@OneToMany(mappedBy="listOfIdsParameter")
	private List<IdParameter> ids;

	public Object getValue() {
		return ids;
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
		return super.getOperand().queryByIds(getFieldType(), this);

	}

	@Override
	public <T> Specification<T> getSpecification() {
		return super.getOperand().queryByIds(getFieldType(), this);
	}

}
