package com.libra.apollo.analytics.entity;

import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.SortNatural;

@SuppressWarnings("serial")
@Entity
@Table(name = "analytics_view", schema="analytics")
public class AnalyticsView extends AutoGeneratedId implements Comparable<AnalyticsView> {

	@Embedded
	private Definition definition;

	@OneToMany(mappedBy = "view")
	@SortNatural
	private SortedSet<InvestmentStyle> investmentStyles = new TreeSet<>();

	@ManyToOne
	@JoinColumn(name = "analytics_id", referencedColumnName = "id")
	private ApolloAnalytics analytics;

	@Embedded
	private Priority priority;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id", referencedColumnName = "id", nullable = true)
	private AnalyticsView parent;

	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
	@SortNatural
	private SortedSet<AnalyticsView> children = new TreeSet<>();

	@Override
	public int compareTo(AnalyticsView o) {
		return o.getPriority().compareTo(this.getPriority());
	}

	public Definition getDefinition() {
		return definition;
	}

	public void setDefinition(Definition definition) {
		this.definition = definition;
	}

	public ApolloAnalytics getAnalytics() {
		return analytics;
	}

	public void setAnalytics(ApolloAnalytics analytics) {
		this.analytics = analytics;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public AnalyticsView getParent() {
		return parent;
	}

	public void setParent(AnalyticsView parent) {
		this.parent = parent;
	}

	public SortedSet<AnalyticsView> getChildren() {
		return children;
	}

	public void setChildren(SortedSet<AnalyticsView> children) {
		this.children = children;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((definition == null) ? 0 : definition.hashCode());
		result = prime * result + ((priority == null) ? 0 : priority.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		AnalyticsView other = (AnalyticsView) obj;
		if (definition == null) {
			if (other.definition != null)
				return false;
		} else if (!definition.equals(other.definition))
			return false;
		if (priority == null) {
			if (other.priority != null)
				return false;
		} else if (!priority.equals(other.priority))
			return false;
		return true;
	}
	
	
	
	

}
