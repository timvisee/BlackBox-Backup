package com.timvisee.blackbox.util;

import java.io.File;

public class DirectoryUtils {
	
	// TODO: Windows, use global data storage location
	// TODO: Return proper locations for Linux, Mac OS X and other file systems
	
	public static File getAppDataDirectory() {
        String homeDir = System.getProperty("user.home", ".");
        File workingDir;

        switch (Platform.getPlatform()) {
        case WINDOWS:
            String applicationData = System.getenv("APPDATA");
            if (applicationData != null)
                workingDir = new File(applicationData);
            else
                workingDir = new File(homeDir);
            break;
            
        case LINUX:
        case SOLARIS:
            workingDir = new File(homeDir);
            break;
            
        case MAC_OS_X:
            workingDir = new File(homeDir);
            break;
            
        default:
            workingDir = new File(homeDir);
        }
        
        return workingDir;
    }
	
	public enum Platform {
		WINDOWS,
		MAC_OS_X,
		SOLARIS,
		LINUX,
		UNKNOWN;
		
		public static Platform getPlatform() {
	        String osName = System.getProperty("os.name").toLowerCase();
	        
	        if (osName.contains("win"))
	            return Platform.WINDOWS;
	        
	        if (osName.contains("mac"))
	            return Platform.MAC_OS_X;
	        
	        if (osName.contains("solaris") || osName.contains("sunos"))
	            return Platform.SOLARIS;
	        
	        if (osName.contains("linux") || osName.contains("unix"))
	            return Platform.LINUX;
	        
	        return Platform.UNKNOWN;
	    }
	}
}
