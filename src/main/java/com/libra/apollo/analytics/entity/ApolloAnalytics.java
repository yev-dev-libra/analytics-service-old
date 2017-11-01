package com.libra.apollo.analytics.entity;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.hibernate.annotations.SortNatural;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "apollo_analytics", schema="analytics", uniqueConstraints = @UniqueConstraint(columnNames = {"code", "analytics_type"}))
@SuppressWarnings("serial")
@Data 
@EqualsAndHashCode(callSuper=true)
public class ApolloAnalytics extends AutoGeneratedId implements Comparable<ApolloAnalytics> {
	
	@Enumerated(EnumType.STRING)
	@Column(name="analytics_type", nullable=false)
	private AnalyticsType type;
	
	@NotNull
	@Column(name="code",nullable=false)
	private String code;
	
	@NotNull
	@Column(name="name",nullable=false)
	private String name;
	
	@Column(name="description",nullable=true)
	private String desc;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private RunType runType;
	
	@OneToMany(mappedBy="analytics", cascade=CascadeType.ALL, fetch=FetchType.LAZY )
	@SortNatural
	private SortedSet<AnalyticsTemplate> template = new TreeSet<>();

	@Override
	public int compareTo(ApolloAnalytics o) {
		return new CompareToBuilder().append(this.getType(), o.getType()).append(this.getName(), o.getName()).toComparison();
	}
	
}
