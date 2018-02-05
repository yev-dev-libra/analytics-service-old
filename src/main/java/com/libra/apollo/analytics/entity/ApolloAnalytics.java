package com.libra.apollo.analytics.entity;

import java.util.SortedSet;
import java.util.TreeSet;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Index;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NamedEntityGraphs;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang.builder.CompareToBuilder;
import org.hibernate.annotations.SortNatural;
import com.fasterxml.jackson.annotation.JsonView;
import com.libra.apollo.analytics.entity.jsonview.ConfigurationJsonView;

@Entity
@Table(name = "analytics", schema="analytics", indexes = { @Index(name = "type_idx", columnList = "analytics_type"),
		@Index(name = "run_type_idx", columnList = "run_type"), }

)
@NamedEntityGraphs({
	@NamedEntityGraph(name = ApolloAnalytics.SHALLOW_GRAPH_NAME,
			attributeNodes = {
					@NamedAttributeNode("views"),
					}
	),

	@NamedEntityGraph(name = ApolloAnalytics.DEEP_GRAPH_NAME,
			attributeNodes = {
					@NamedAttributeNode("views"),
					}
	
			),
})
@SuppressWarnings("serial")
public class ApolloAnalytics extends AutoGeneratedId implements Comparable<ApolloAnalytics> {

	public static final String SHALLOW_GRAPH_NAME = "APOLLO_ANALYTICS_SHALLOW_GRAPH";
	public static final String DEEP_GRAPH_NAME = "APOLLO_ANALYTICS_DEEP_GRAPH";
	
	@Embedded
	private Definition definition;

	@Enumerated(EnumType.STRING)
	@Column(name = "analytics_type", nullable = false)
	private AnalyticsType type;
	
	@Column(name="run_type")
	@NotNull
	@Enumerated(EnumType.STRING)
	private RunType runType;

	@OneToMany(mappedBy = "analytics", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@SortNatural
	private SortedSet<AnalyticsView> views = new TreeSet<>();

	@OneToMany(mappedBy = "analytics", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@SortNatural
	@OrderBy( value = "priority DESC")
	private SortedSet<InvestmentStyle> investmentStyles = new TreeSet<>();


	public Definition getDefinition() {
		return definition;
	}

	public void setDefinition(Definition definition) {
		this.definition = definition;
	}

	public AnalyticsType getType() {
		return type;
	}

	public void setType(AnalyticsType type) {
		this.type = type;
	}

	public RunType getRunType() {
		return runType;
	}

	public void setRunType(RunType runType) {
		this.runType = runType;
	}


	public SortedSet<AnalyticsView> getViews() {
		return views;
	}

	public void setViews(SortedSet<AnalyticsView> views) {
		this.views = views;
	}


	public SortedSet<InvestmentStyle> getInvestmentStyles() {
		return investmentStyles;
	}

	public void setInvestmentStyles(SortedSet<InvestmentStyle> investmentStyles) {
		this.investmentStyles = investmentStyles;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((definition == null) ? 0 : definition.hashCode());
		result = prime * result + ((runType == null) ? 0 : runType.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		ApolloAnalytics other = (ApolloAnalytics) obj;
		if (definition == null) {
			if (other.definition != null)
				return false;
		} else if (!definition.equals(other.definition))
			return false;
		if (runType != other.runType)
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public int compareTo(ApolloAnalytics o) {
		return new CompareToBuilder().append(this.getType(), o.getType())
				.append(this.getDefinition().getName(), o.getDefinition().getName()).toComparison();
	}
	
	
	//----------- Defining Views for JSON serialization ----------------//

	@Transient
	@JsonView(ConfigurationJsonView.Public.class)
	public Long getId() {
		return super.getId();
		
	}
	
	@Transient
	@JsonView(ConfigurationJsonView.Public.class)
	public String getName() {
		return this.getDefinition().getName();
	}
	
	@JsonView(ConfigurationJsonView.Public.class)
	@Transient
	public String getDescription() {
		return this.getDefinition().getDesc();
	}
	
	@JsonView(ConfigurationJsonView.PublicExtended.class)
	@Transient
	public SortedSet<AnalyticsView> getAnalyticsViews(){
		return this.getViews();
	}

}
