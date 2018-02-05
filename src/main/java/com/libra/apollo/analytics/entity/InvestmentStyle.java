package com.libra.apollo.analytics.entity;

import java.util.SortedSet;
import java.util.TreeSet;
import javax.persistence.*;
import org.hibernate.annotations.SortNatural;
import com.fasterxml.jackson.annotation.JsonView;
import com.libra.apollo.analytics.entity.jsonview.ConfigurationJsonView;

@SuppressWarnings("serial")
@Entity
@NamedEntityGraphs({
	@NamedEntityGraph(name = InvestmentStyle.SHALLOW_GRAPH_NAME,
			attributeNodes = {
					@NamedAttributeNode("view"),
					@NamedAttributeNode("analytics"),
					@NamedAttributeNode("investmentStyleParameters"),
					}
	),
	@NamedEntityGraph(name = InvestmentStyle.SHALLOW_GRAPH_NAME_WITH_CHILDREN,
	attributeNodes = {
			@NamedAttributeNode("view"),
			@NamedAttributeNode("analytics"),
			@NamedAttributeNode("investmentStyleParameters"),
			@NamedAttributeNode("parent"),
			@NamedAttributeNode("children"),
	}
			),
	@NamedEntityGraph(name = InvestmentStyle.DEEP_GRAPH_NAME,
			attributeNodes = {
					@NamedAttributeNode("view"),
					@NamedAttributeNode("analytics"),
					@NamedAttributeNode(value="investmentStyleParameters", subgraph = "parameterField")},
			subgraphs =  {
					@NamedSubgraph(name = "parameterField",
							attributeNodes = {
									@NamedAttributeNode("parameter")}),
					}
	
			),
	@NamedEntityGraph(name = InvestmentStyle.DEEP_GRAPH_NAME_WITH_CHILDREN,
	attributeNodes = {
			@NamedAttributeNode("view"),
			@NamedAttributeNode("analytics"),
			@NamedAttributeNode("parent"),
			@NamedAttributeNode(value="children", subgraph="childrenField"),
			@NamedAttributeNode(value="investmentStyleParameters", subgraph = "parameterField")},
	subgraphs =  {
			@NamedSubgraph(name = "childrenField",
					attributeNodes = {
							@NamedAttributeNode("children")}),
			@NamedSubgraph(name = "parameterField",
			attributeNodes = {
					@NamedAttributeNode("parameter")}),
	}
	
			)
})
@Table(name = "analytics_investment_style", schema="analytics"
// ,indexes = {
// @Index(name="parentInvestmentStyle_idx", columnList="parent_id")
// }
)
@Inheritance(strategy = InheritanceType.JOINED)
public class InvestmentStyle extends AutoGeneratedId implements Comparable<InvestmentStyle> {

	public static final String SHALLOW_GRAPH_NAME = "INVESTMENT_STYLE_SHALLOW_GRAPH";
	public static final String SHALLOW_GRAPH_NAME_WITH_CHILDREN = "SHALLOW_GRAPH_NAME_WITH_CHILDREN";
	public static final String DEEP_GRAPH_NAME = "INVESTMENT_STYLE_DEEP_GRAPH";
	public static final String DEEP_GRAPH_NAME_WITH_CHILDREN = "DEEP_GRAPH_NAME_WITH_CHILDREN";
	
	@Embedded
	private Definition definition;

	@Embedded
	private Priority priority;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "view_id", referencedColumnName = "id", nullable = true)
	private AnalyticsView view;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "analytics_id", referencedColumnName = "id", nullable = true)
	private ApolloAnalytics analytics;

	@OneToMany(mappedBy = "investmentStyle", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@SortNatural
	private SortedSet<InvestmentStyleParameter> investmentStyleParameters = new TreeSet<>();
	
	@OneToMany(mappedBy = "investmentStyle", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@SortNatural
	private SortedSet<InvestmentStyleParameter> investmentStyleFieldParameters = new TreeSet<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "parent_id", referencedColumnName = "id", nullable = true)
	private InvestmentStyle parent;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent", cascade = CascadeType.ALL, orphanRemoval = true)
	@SortNatural
	private SortedSet<InvestmentStyle> children = new TreeSet<>();
	
	public Definition getDefinition() {
		return definition;
	}

	public void setDefinition(Definition definition) {
		this.definition = definition;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public AnalyticsView getView() {
		return view;
	}

	public void setView(AnalyticsView view) {
		this.view = view;
	}

	public ApolloAnalytics getAnalytics() {
		return analytics;
	}

	public void setAnalytics(ApolloAnalytics analytics) {
		this.analytics = analytics;
	}


	public SortedSet<InvestmentStyleParameter> getInvestmentStyleParameters() {
		return investmentStyleParameters;
	}

	public void setInvestmentStyleParameters(SortedSet<InvestmentStyleParameter> investmentStyleParameters) {
		this.investmentStyleParameters = investmentStyleParameters;
	}
	
	public SortedSet<InvestmentStyleParameter> getInvestmentStyleFieldParameters() {
		return investmentStyleFieldParameters;
	}

	public void setInvestmentStyleFieldParameters(SortedSet<InvestmentStyleParameter> investmentStyleFieldParameters) {
		this.investmentStyleFieldParameters = investmentStyleFieldParameters;
	}

	public InvestmentStyle getParent() {
		return parent;
	}

	public void setParent(InvestmentStyle parent) {
		this.parent = parent;
	}

	public SortedSet<InvestmentStyle> getChildren() {
		return children;
	}

	public void setChildren(SortedSet<InvestmentStyle> children) {
		this.children = children;
	}

	// Reversing into desc order, so that an object with the lowest priority
	// comes first
	@Override
	public int compareTo(InvestmentStyle o) {
		int c1 = o.getPriority().compareTo(this.getPriority());
		
		if (c1 == 0) {
	        int c2 = o.getId().compareTo(this.getId());

	        return (c2 == 0) ? 1 : c2;

	    } else {
	        return c1;
	    }
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
		InvestmentStyle other = (InvestmentStyle) obj;
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

}
