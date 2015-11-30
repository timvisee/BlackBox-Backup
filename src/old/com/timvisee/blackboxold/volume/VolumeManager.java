package com.timvisee.blackboxold.volume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.timvisee.blackboxold.BlackBox;
import com.timvisee.blackboxold.volume.Volume;
import com.timvisee.blackboxold.util.ListUtils;
import com.timvisee.yamlwrapper.configuration.ConfigurationSection;
import com.timvisee.yamlwrapper.configuration.FileConfiguration;
import com.timvisee.yamlwrapper.configuration.YamlConfiguration;

public class VolumeManager {

	private List<Volume> v = new ArrayList<Volume>();
	
	/**
	 * Constructor. Automatically loads volumes
	 */
	public VolumeManager() {
		// Execute the main contructor
		this(true);
	}
	
	/**
	 * Constructor
	 * @param load True to automatically load volumes
	 */
	public VolumeManager(boolean load) {
		// Should the volumes be loaded
		if(load)
			load();
	}
	
	/**
	 * Constructor
	 * @param v List of volumes
	 */
	public VolumeManager(List<Volume> v) {
		// Execute the main constructor
		this(false);
		
		// Store the volumes list
		this.v = v;
	}
	
	/**
	 * Constructor
	 * @param vm Volume manager to copy the volumes from
	 */
	public VolumeManager(VolumeManager vm) {
		// Execute the main constructor
		this(false);
		
		// Copy the volumes
		copy(vm, true, true);
	}
	
	/**
	 * Add a new volume
	 * @param v Volume to add
	 */
	public void addVolume(Volume v) {
		this.v.add(v);
	}
	
	/**
	 * Get the list of volumes
	 * @return List of volumes
	 */
	public List<Volume> getVolumes() {
		return this.v;
	}
	
	/**
	 * Get the volumes count
	 * @return Volumes count
	 */
	public int getVolumesCount() {
		return this.v.size();
	}
	
	/**
	 * Set the list of volumes
	 * @param v New list of volumes
	 */
	public void setVolumes(List<Volume> v) {
		setVolumes(v, false);
	}
	
	/**
	 * Set the list of volumes
	 * @param v New list of volumes
	 * @param forceClone True to clone the list
	 */
	public void setVolumes(List<Volume> v, boolean forceClone) {
		if(forceClone)
			this.v = ListUtils.cloneVolumeList(v);
		else
			this.v = v;
	}
	
	/**
	 * Get an unique ID (Make sure all volumes are loaded)
	 * @return An unique ID
	 */
	public int getUID() {
		// Store the current highest UID
		int highestUid = -1;
		
		// Get the current highest UID
		for(Volume v : this.v)
			if(v.getUID() > highestUid || highestUid < 0)
				highestUid = v.getUID();
		
		// Return an unique ID
		return (highestUid + 1);
	}
	
	/**
	 * Clear the current list of loaded volumes
	 * @return Amount of cleared volumes
	 */
	public int clear() {
		// Get the amount of volumes available
		int c = getVolumesCount();
		
		// Clear the list of volumes
		this.v.clear();
		
		// Return the cleared volumes count
		return c;
	}
	
	/**
	 * Get the data file to store the volumes in
	 * @return Data file
	 */
	public File getDataFile() {
		return new File(BlackBox.getDataDirectory(), "volumes/volumes.dat");
	}
	
	/**
	 * Save all volumes
	 * @return True if succeed, false otherwise
	 */
	public boolean save() {
		return save(true);
	}
	
	/**
	 * Save all volumes
	 * @param showMsg True to show status messages
	 * @return True if succeed, false otherwise
	 */
	public boolean save(boolean showMsg) {
		return save(getDataFile(), showMsg);
	}
	
	/**
	 * Save all volumes
	 * @param f File to save the volumes to
	 * @return True if succeed, false otherwise
	 */
	public boolean save(File f) {
		return save(f, true);
	}
	
	/**
	 * Save all volumes
	 * @param f File to save the volumes to
	 * @param showMsg True to show status messages
	 * @return True if succeed, false otherwise
	 */
	public boolean save(File f, boolean showMsg) {
		long start = System.currentTimeMillis();
		
		// Make sure the file isn't null
		if(f == null) {
			if(showMsg)
				System.out.println("Unable to save volumes data, invalid file!");
			return false;
		}
		
		// TODO: Make sure f is a file (not a directory)
		
		// Check whether the file exists
		if(!f.exists() && showMsg)
			System.out.println("Volumes data file doesn't exist, creating a new file.");
		
		// Define a new configuration file
		YamlConfiguration c = new YamlConfiguration();
		
		// Create a section for the volumes
		ConfigurationSection volSect = c.createConfigurationSection("volumes");
		
		boolean succeed = true;
		
		// Store each volume in the configuration section
		int i = 0;
		for(Volume v : this.v) {
			// Create a configuration section to store the current volume in
			ConfigurationSection entrySect = volSect.createConfigurationSection(String.valueOf(i));
			
			// Increase the index
			i++;
			
			// Store the current volume, and check the result
			if(!v.save(entrySect)) {
				System.out.println("Failed to save volume (UID: " + String.valueOf(v.getUID()) + ")");
				succeed = false;
			}
		}
		
		// Append the version number to the file
		c.set("version", BlackBox.APP_VERSION_CODE);
		c.set("versionName", BlackBox.APP_VERSION_NAME);
		
		// TODO: Set the file header
		
		// Convert the file to a FileConfiguration and safe the file
        FileConfiguration fc = c;
        try {
                fc.save(f);
        } catch (IOException e) {
                System.out.println("An error occured while saving volumes data!");
                e.printStackTrace();
                return false;
        }
        
        // Calculate the save duration
        long duration = System.currentTimeMillis() - start;
		
        // Show a status message
        if(showMsg)
        	System.out.println("Volumes data saved, took " + String.valueOf(duration) + "ms!");
        
		// Return the result
		return succeed;
	}
	
	/**
	 * Load all volumes
	 * @return True if succeed, false otherwise
	 */
	public boolean load() {
		return load(true);
	}
	
	/**
	 * Load all volumes
	 * @param showMsg True to show status messages
	 * @return True if succeed, false otherwise
	 */
	public boolean load(boolean showMsg) {
		return load(getDataFile(), showMsg);
	}
	
	/**
	 * Load all volumes
	 * @param f File to load the volumes from
	 * @return True if succeed, false otherwise
	 */
	public boolean load(File f) {
		return load(f, true);
	}
	
	/**
	 * Load all volumes
	 * @param f File to load the volumes from
	 * @param showMsg True to show status messages
	 * @return True if succeed, false otherwise
	 */
	public boolean load(File f, boolean showMsg) {
		long start = System.currentTimeMillis();
		
		// Make sure the file isn't null
		if(f == null) {
			if(showMsg)
				System.out.println("Failed to load volumes data, invalid file!");
			return false;
		}
		
		// TODO: Make sure f is a file (not a directory)
		
		// Make sure the file exists
		if(!f.exists()) {
			if(showMsg)
				System.out.println("Failed to load volumes data, file doesn't exist!");
			return false;
		}
		
		// Show a status message
		if(showMsg)
			System.out.println("Loading volumes data...");
		
		// Load the volume data
		YamlConfiguration c = new YamlConfiguration();
		try {
			c.load(f);
		} catch(Exception ex) {
			if(showMsg)
				System.out.println("An error occured while loading volumes data!");
			return false;
		}
		
		// Make sure the 'volumes' section exists
		if(!c.isConfigurationSection("volumes")) {
			if(showMsg)
				System.out.println("An error occured while loading volumes data, invalid file!");
			return false;
		}
		
		// Get the volumes section
		ConfigurationSection volSect = c.getConfigurationSection("volumes");
		
		// Define a new list to store the volumes in
		List<Volume> buff = new ArrayList<Volume>();
		
		List<String> keys = volSect.getKeys("");
		for(String entry : keys) {
			// Get the configuration section of the current volume entry
			ConfigurationSection entrySect = volSect.getConfigurationSection(entry);
			
			// Make sure the section isn't null
			if(entrySect == null) {
				if(showMsg)
					System.out.println("Failed to load volume item!");
				continue;
			}
			
			// Load the volume
			Volume v = Volume.load(entrySect);
			
			// Make sure the current volume is loaded correctly
			if(v == null) {
				if(showMsg)
					System.out.println("Failed to load volume item!");
				continue;
			}
			
			// Add the loaded volume to the buffer
			buff.add(v);
		}
		
		// Clear the current list of volumes
		clear();
		
		// Store the new list
		this.v = buff;
		
		// Calculate the load duration
        long duration = System.currentTimeMillis() - start;
        
        // Show a status message
        if(showMsg)
        	System.out.println("Volumes successfully loaded, took " + String.valueOf(duration) + "ms!");
        
        // Everything seems to be fine, return true
        return true;
	}
	
	/**
	 * Copy all volumes from a different volume manager instance to this volume manager instance. Does not clone or clear previous volumes.
	 * @param vm Volume manager instance to copy the volumes from
	 */
	public void copy(VolumeManager vm) {
		copy(vm, false, false);
	}
	
	/**
	 * Copy all volumes from a different volume manager instance to this volume manager instance
	 * @param vm Volume manager instance to copy the volumes from
	 * @param replace True to replace all current volumes with these new ones being copied
	 * @param clone True to clone each volume that is being copied
	 */
	public void copy(VolumeManager vm, boolean replace, boolean clone) {
		// Should the volumes be replaced
		if(replace)
			clear();
		
		// Copy the volumes
		if(!clone)
			this.v.addAll(vm.getVolumes());
		
		else
			this.v.addAll(ListUtils.cloneVolumeList(vm.getVolumes()));
	}
	
	/**
	 * Clone the volume manager instance
	 * @return Cloned volume manager instance
	 */
	public VolumeManager clone() {
		return new VolumeManager(this);
	}
}