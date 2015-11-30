package com.timvisee.blackbox.task;

public class Task {
	
	private String name;
	
	public Task(String name) {
		this.name = name;
	}

	/**
	 * Get the name of the task
	 * @return Task name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name of the task
	 * @param Name of the task
	 */
	public void setName(String name) {
		this.name = name;
	}
}
