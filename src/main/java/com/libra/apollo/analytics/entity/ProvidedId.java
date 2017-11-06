package com.libra.apollo.analytics.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class ProvidedId<ID extends Serializable> extends DomainObject<ID>  {

	@Id 
	protected ID id;

	@Override
	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return id.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		ProvidedId<?> other = (ProvidedId<?>) obj;
		return Objects.equals(this.id, other.id);
	}
	
}
