package com.libra.apollo.analytics.engine.command;

public interface Command {

	public Command next();
	public void execute();
}
