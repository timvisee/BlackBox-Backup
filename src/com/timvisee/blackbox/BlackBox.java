package com.timvisee.blackbox;

import java.io.File;

import com.timvisee.blackbox.util.DirectoryUtils;

public class BlackBox {

    /** Application name. */
    public static final String APP_NAME = "BlackBox Backup";
    /** Application version name. */
	public static final String APP_VERSION_NAME = "0.1";
    /** Application version number. */
	public static final int APP_VERSION_CODE = 1;
    /** Application copyright notice. */
    public static final String APP_COPYRIGHT = "Copyright (c) Tim Visee 2015. All rights reserved.";

    /** The application data directory name. */
	public static final String APPDATA_DIR_NAME = "BlackBox";

    /** The Core instance of the application */
	private static Core core;
	
	/**
	 * Main method, called on start up.
     *
	 * @param args Array of start up arguments.
	 */
	public static void main(String[] args) {
        // Show the application name, version and copyright notice
        System.out.println(APP_NAME + " v" + APP_VERSION_NAME + " (" + APP_VERSION_CODE + ").\n" + APP_COPYRIGHT + "\n");

		// Initialize the core
		if(BlackBox.core == null)
			BlackBox.core = new Core();
	}
	
	/**
	 * Get the application data directory.
     *
	 * @return The application data directory.
	 */
	public static File getDirectory() {
		return new File(DirectoryUtils.getAppDataDirectory(), APPDATA_DIR_NAME);
	}
	
	/**
	 * Get the application data, data directory.
     *
	 * @return The application data, data directory.
	 */
	public static File getDataDirectory() {
		return new File(getDirectory(), "data");
	}
}
