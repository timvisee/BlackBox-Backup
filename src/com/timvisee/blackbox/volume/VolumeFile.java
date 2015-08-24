package com.timvisee.blackbox.volume;

import java.util.List;

public class VolumeFile {
	
	/** Volume adapter instance. */
    private VolumeAdapter adapter;
	/** Volume path. */
	private String path;
	
	/**
	 * Constructor.
     *
	 * @param adapter Volume adapter instance.
	 * @param path Path
	 */
	public VolumeFile(VolumeAdapter adapter, String path) throws Exception {
		this(adapter, path, null);
	}

    /**
     * Constructor.
     *
     * @param adapter Volume adapter
     */
    public VolumeFile(VolumeAdapter adapter, String path, String child) throws Exception {
        // Set the adapter instance
        // TODO: Use proper error handling!
        if(!setAdapter(adapter))
            throw new Exception("Invalid volume adapter instance!");

        // Set the volume file path
        // TODO: Use proper error handling!
        if(!setPath(path, child))
            throw new Exception("Invalid volume file path!");
    }

    /**
     * Get the volume adapter instance.
     *
     * @return Volume adapter instance.
     */
    public VolumeAdapter getAdapter() {
        return this.adapter;
    }

    /**
     * Set the volume adapter instance.
     *
     * @param adapter Volume adapter instance.
     *
     * @return True on success, false on failure.
     */
    protected boolean setAdapter(VolumeAdapter adapter) {
        // Make sure the adapter is valid
        if(adapter == null)
            return false;

        // Set the adapter instance, return the result
        this.adapter = adapter;
        return true;
    }

    /**
     * Get the volume file path represented as a string.
     *
     * @return Volume file path.
     */
    public String getPath() {
        return this.path;
    }

    public String getName() {
        return this.adapter.getName(this);
    }

    /**
     * Set the volume file path represented as a string.
     *
     * @param path The volume file path.
     * @param child The volume file child path.
     *
     * @return True on success, false on failure.
     */
    protected boolean setPath(String path, String child) {
        // Set the path
        this.path = path;

        // Make sure the child has been set for the following operations
        if(child == null)
            return true;
        if(child.trim().length() == 0)
            return true;

        // Combine the path with the child path, return the result
        this.path = path + this.adapter.getFileSeparator() + child;
        return true;
    }

	/**
	 * Get a list of files and directories inside the current directory.
	 * Note: The current file has to be a directory
     *
	 * @return List of files and directories inside the current directory
	 */
	public List<VolumeFile> list() {
        // Make sure the volume is connected
        if(!getAdapter().isConnected())
            if(!getAdapter().connect())
                return null;
		
		// List and return the files
		return getAdapter().list(this);
	}

    /**
     * Check whether this is an existing file.
     *
     * @return True if this is an existing file.
     */
    public boolean isFile() {
        return getAdapter().isFile(this);
    }

    /**
     * Check whether this is an existing directory.
     *
     * @return True if this is an existing directory.
     */
    public boolean isDirectory() {
        return getAdapter().isDirectory(this);
    }
}
