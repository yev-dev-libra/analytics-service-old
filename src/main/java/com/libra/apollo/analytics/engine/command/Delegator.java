package com.libra.apollo.analytics.engine.command;

import com.libra.apollo.analytics.engine.context.AnalyticsContext;

/**
 */
public interface Delegator extends Command {

	public void add(Command command);
	
	public Command next();
	
	public AnalyticsContext getContext();
}
