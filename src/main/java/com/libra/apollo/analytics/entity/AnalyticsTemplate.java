package com.libra.apollo.analytics.entity;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.SortComparator;

import com.libra.apollo.analytics.comparator.DefaultInvestmentSyleComparator;

import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Entity
@Table(name="analytics_template", schema="analytics")
@Data
@EqualsAndHashCode(callSuper=true)
public class AnalyticsTemplate extends AutoGeneratedId {

	@Embedded
	private SortDirection sortDirection;
	
	@OneToMany(mappedBy="template")
	@SortComparator(DefaultInvestmentSyleComparator.class)
	private SortedSet<InvestmentStyle> style = new TreeSet<>();
	
	@ManyToOne
	private ApolloAnalytics analytics;
	
}