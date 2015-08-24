package com.timvisee.blackbox.volume;

import com.timvisee.yamlwrapper.configuration.ConfigurationSection;

public class Volume {

	/** The unique volume ID. */
	protected int uid;
    /** Volume adapter instance. */
    protected VolumeAdapter adapter;
	/** Defines whether the volume is enabled or not. */
	protected boolean enabled;
	/** Name of the volume. */
	protected String name;

	/**
	 * Constructor.
     *
	 * @param uid Unique volume ID.
     * @param enabled True if enabled, false if not.
     * @param name The volume name.
     * @param adapter Volume adapter instance.
	 */
	public Volume(int uid, boolean enabled, String name, VolumeAdapter adapter) throws Exception {
        // Set the UID
		setUID(uid);
        setEnabled(enabled);
        setName(name);

        // Set the volume adapter
        // TODO: Better error handling!
        if(!setAdapter(adapter))
            throw new Exception("Invalid volume adapter specified!");
	}

    /**
     * @return the uid
     */
    public int getUID() {
        return uid;
    }

    /**
     * @param uid the uid to set
     */
    private void setUID(int uid) {
        this.uid = uid;
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
     * Check whether the volume is enabled.
     *
	 * @return True if the volume is enabled, false otherwise.
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
     * Set whether the volume is enabled.
     *
	 * @param enabled True if the volume is enabled, false if the volume is disabled.
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
     * Get the name of the volume.
     *
	 * @return The name of the volume.
	 */
	public String getName() {
		return name;
	}

	/**
     * Set the name of the volume.
     *
	 * @param name The name of the volume.
	 */
	public void setName(String name) {
		this.name = name;
	}

    /**
     * Connect to the volume.
     *
     * @return True on success, false on failure.
     */
	public boolean connect() {
        return this.adapter.connect();
    }
	
	/**
	 * Check whether we're connected to the volume.
     *
     * @return True if we're connected, false otherwise.
	 */
	public boolean isConnected() {
        return this.adapter.isConnected();
    }
	
	/**
	 * Disconnect from the volume.
     *
     * @return True on success, false on failure. True will also be returned if the volume wasn't connected.
	 */
	public boolean disconnect() {
        return this.adapter.disconnect();
    }

    // TODO: getRoot() method returning a VolumeFile instance

    /**
     * Load a volume adapter from a configuration section.
     *
     * @param c Configuration section.
     *
     * @return Volume adapter, or null if failed.
     */
    public static Volume load(ConfigurationSection c) throws Exception {
        // TODO: Log error messages on load failures!

        // Make sure the configuration section isn't null
        if(c == null)
            return null;

        // Load the volume adapter, and make sure it's valid
        VolumeAdapter adapter = VolumeAdapter.loadAdapter(c);
        if(adapter == null)
            return null;

		// Get the basic volume properties
		int uid = c.getInt("uid", -1);
		boolean enabled = c.getBoolean("enabled", true);
		String name = c.getString("name", "Unnamed Volume");

        // Construct and return the volume
        return new Volume(uid, enabled, name, adapter);
    }

    // TODO: This method has not been tested yet!
    public boolean save(ConfigurationSection c) {
        // Store the volume properties
        c.set("type", this.getAdapter().getVolumeType().getTypeId());
        c.set("uid", this.uid);
        c.set("enabled", this.enabled);
        c.set("name", this.name);

        // Create a data section for the volume adapter
        ConfigurationSection dataSection = c.createSection("data");
        this.adapter.saveToConfig(dataSection);
        return true;
    }

    /**
     * Clone the volume.
     *
     * @return Cloned instance.
     */
    public Volume clone() throws CloneNotSupportedException {
        return (Volume) super.clone();
    }
}
