package com.libra.apollo.analytics.entity;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.SortNatural;

import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Entity
@Table(name = "analytics_template")
@Data
@EqualsAndHashCode(callSuper = true)
public class AnalyticsTemplate extends AutoGeneratedId {

	@Embedded
	private Definition definition;

	@OneToMany(mappedBy = "template")
	@SortNatural
	private SortedSet<InvestmentStyle> investmentStyles = new TreeSet<>();

	@ManyToOne
	@JoinColumn(name = "analytics_id", referencedColumnName = "id")
	private ApolloAnalytics analytics;

	@Embedded
	private Priority priority;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id", referencedColumnName = "id", nullable = true)
	private AnalyticsTemplate parent;

	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
	@SortNatural
	private SortedSet<AnalyticsTemplate> children = new TreeSet<>();

}
