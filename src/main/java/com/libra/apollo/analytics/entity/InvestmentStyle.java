package com.libra.apollo.analytics.entity;

import java.util.ArrayList;
import java.util.List;
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

import org.hibernate.annotations.SortComparator;
import org.hibernate.annotations.SortNatural;

import com.libra.apollo.analytics.comparator.DefaultInvestmentStyleConditionComparator;

import lombok.Data;
import lombok.EqualsAndHashCode;


@SuppressWarnings("serial")
@Entity
@Table(name="investment_style")
@Data
@EqualsAndHashCode(callSuper=true)
public class InvestmentStyle extends AutoGeneratedId implements Comparable<InvestmentStyle> {

	@Column(nullable = false)
	private String code;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String description;
	
	@Embedded
	private Priority priority;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="template_id", referencedColumnName="id",nullable=true)
	private AnalyticsTemplate template;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="parent_id", referencedColumnName="id",nullable=true)
	private InvestmentStyle parent;
	
	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL,orphanRemoval=true)
	@SortNatural
	private SortedSet<InvestmentStyle> children = new TreeSet<>();
	
	@OneToMany(mappedBy="investmentStyle", cascade = CascadeType.ALL,orphanRemoval = true )
	@SortComparator(DefaultInvestmentStyleConditionComparator.class)
	private SortedSet<InvestmentStyleCondition> investmentStyleConditions = new TreeSet<>();

	//Reversing into desc order, so that an object with the lowest priority comes first
	@Override
	public int compareTo(InvestmentStyle o) {
		return o.getPriority().compareTo(this.getPriority());
	}
	
	
}
