package com.timvisee.blackboxold.volume;

import java.io.File;

import com.timvisee.blackbox.volume.Volume;
import com.timvisee.blackbox.volume.filesystem.VolumeFile;
import com.timvisee.yamlwrapper.configuration.ConfigurationSection;

public class SystemVolume extends Volume {

	/** @var dir Volume directory */
	private File dir;

	/**
	 * Constructor
	 * @param uid Unique ID
	 * @param enabled True if enabled
	 * @param name Volume name
	 * @param dir Volume directory
	 */
	public SystemVolume(int uid, boolean enabled, String name, File dir) {
		// Construct parent
		super(uid, enabled, name);
		
		// Store directory
		this.dir = dir;
	}
	
	/**
	 * Get the volume directory
	 * @return Current volume directory
	 */
	public File getDirectory() {
		return this.dir;
	}
	
	/**
	 * Set the volume directory
	 * @param dir New volume directory
	 */
	public void setDirectory(File dir) {
		this.dir = dir;
	}

	/**
	 * Get the volume type
	 * @return Volume type
	 */
	@Override
	protected VolumeType getVolumeType() {
		return VolumeType.SYSTEM_VOLUME;
	}

    /**
     * Connect to the volume
     *
     * @return True if succeed, false otherwise
     */
    @Override
    public boolean connect() {
        return false;
    }

    /**
     * Check whether a connection is active to the volume
     *
     * @return True if there's an active connection to the volume
     */
    @Override
    public boolean isConnected() {
        return false;
    }

    /**
     * Disconnect from the volume
     *
     * @return True if succeed, false otherwise
     */
    @Override
    public boolean disconnect() {
        return false;
    }

    /**
     * Get the root of the volume
     *
     * @return Root of the volume
     */
    @Override
    public VolumeFile getRoot() {
        return null;
    }

    @Override
	public boolean save(ConfigurationSection c) {
		// Make sure the configuration section isn't null
		if(c == null)
			return false;
		
		// Store the volume
		c.set("type", getVolumeType().getTypeId());
		c.set("uid", getUID());
		c.set("enabled", isEnabled());
		c.set("name", getName());
		c.set("dir", this.dir.getAbsolutePath());
		
		// Everything seems to be fine, return true
		return true;
	}
	
	/**
	 * Clone the volume
	 * @return Cloned volume
	 */
	@Override
	public SystemVolume clone() {
		return new SystemVolume(uid, enabled, name, dir);
	}
}
