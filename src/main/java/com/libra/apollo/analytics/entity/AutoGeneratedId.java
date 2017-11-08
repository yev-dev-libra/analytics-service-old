package com.libra.apollo.analytics.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@SuppressWarnings("serial")
public class AutoGeneratedId extends DomainObject<Long> {

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO) 
	protected Long id;
	
	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

}
