package com.timvisee.blackbox.util;

import java.io.File;
import java.io.IOException;

public class FileUtils {

	/**
	 * Check if a path is valid according to the OS rules without checking if the file or path exists
	 * @param path The path to check for
	 * @return True if valid
	 */
	public static boolean isValidPath(String path) {
		if(path.trim().equals(""))
			return false;
		
		File f = new File(path);
		try {
			f.getCanonicalPath();
			return true;
		} catch (IOException e) {
			return false;
		}
	}
}
