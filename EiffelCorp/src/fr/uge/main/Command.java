package fr.uge.main;

import java.util.Objects;

import fr.ifshare.IStore;

public class Command {
	private final String command;
	private final String pattern;
	private final String description;
	private final RmiMethods<IStore, String[]> action;
	
	public Command(String command, String pattern, String description, RmiMethods<IStore, String[]> action) {
		this.command = Objects.requireNonNull(command);
		this.pattern = Objects.requireNonNull(pattern);
		this.description = Objects.requireNonNull(description);
		this.action = action;
	}
	
	@Override
	public String toString() {
		return pattern + " : " + description;
	}
	
	public String getUsage() {
		return pattern;
	}
	
	public RmiMethods<IStore, String[]> getAction() {
		return action;
	}
}
