package com.libra.apollo.analytics.rules;

import com.libra.apollo.analytics.entity.Property;

public interface Visitor {

	public void visit(Property property);
}
