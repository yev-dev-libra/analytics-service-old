package com.libra.apollo.analytics.engine.command;

import java.util.LinkedList;
import java.util.Queue;

import com.libra.apollo.analytics.engine.context.AnalyticsContext;
import com.netflix.servo.util.Preconditions;


/*
 * A wrapper to call all registered commands. Partially implements Chain of Responsibility pattern
 */
public class ScreenerDelegator implements Delegator {

	private final Queue<Command> commands;
	private final AnalyticsContext context;
	private Command next;
	
	public ScreenerDelegator(final AnalyticsContext context) {
		super();
		this.context = context;
		commands = new LinkedList<>();
	}

	@Override
	public void execute() {
		for(Command command : commands) {
			
			command.execute();
		}
	}

	@Override
	public void add(Command command) {
		Preconditions.checkArgument(command == null, "Passed parameter can not be null");
		commands.add(command);
		next = command;
		
	}

	@Override
	public AnalyticsContext getContext() {
		return context;
	}

	@Override
	public Command next() {
		return next;
	}

}
