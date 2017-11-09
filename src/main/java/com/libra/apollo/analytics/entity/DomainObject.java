package com.libra.apollo.analytics.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;

@SuppressWarnings("serial")
@MappedSuperclass
public abstract class DomainObject<ID extends Serializable> implements Serializable {

	public abstract ID getId();
	
	@Version
	@Column(nullable = false)
	@JsonIgnore
	protected int version = 0;
	
	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date createdOn = new Date();
//	private LocalDateTime createdOn = LocalDateTime.now();
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdatedOn  = new Date();
//	private LocalDateTime lastUpdatedOn  = LocalDateTime.now();


	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getLastUpdatedOn() {
		return lastUpdatedOn;
	}

	public void setLastUpdatedOn(Date lastUpdatedOn) {
		this.lastUpdatedOn = lastUpdatedOn;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean equals(Object other) {
		if (this == other) return true;
		if (other == null || !(other instanceof DomainObject)) return false;
		DomainObject that = (DomainObject) other;
		return new EqualsBuilder().append(getId(), that.getId()).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + " [id=" + getId() + "]";
	}
	
}
