package com.libra.apollo.analytics.entity;

import java.util.Collections;
import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import org.springframework.data.jpa.domain.Specification;

import com.libra.apollo.analytics.specification.ValueParameter;

@SuppressWarnings("serial")
@Entity
@DiscriminatorValue(value = "LIST_IDS_PARAMETER")
public class ListOfIdsParameter extends Parameter {

	//TODO: define @ElementCollection
	private List<Long> ids;

	public Object getValue() {
		return ids;
	}

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = Collections.unmodifiableList(ids);
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
