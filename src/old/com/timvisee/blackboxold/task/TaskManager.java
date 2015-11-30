package com.timvisee.blackboxold.task;

import java.io.File;

import com.timvisee.blackboxold.BlackBox;

public class TaskManager {

	/**
	 * Get the data file to store the tasks in
	 * @return Data file
	 */
	public File getDataFile() {
		return new File(BlackBox.getDataDirectory(), "tasks/tasks.dat");
	}
}
