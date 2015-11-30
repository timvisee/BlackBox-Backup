package com.timvisee.blackbox.volume;

import com.timvisee.blackbox.BlackBox;
import com.timvisee.yamlwrapper.configuration.ConfigurationSection;
import com.timvisee.yamlwrapper.configuration.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VolumeManager {

    /** List of loaded volumes. */
    private List<Volume> volumes = new ArrayList<>();

    /**
     * Constructor.
     * The volumes will be loaded automatically from the configuration.
     */
    public VolumeManager() {
        // Execute the main constructor
        this(true);
    }

    /**
     * Constructor.
     *
     * @param load True to load the volumes, false if not.
     */
    public VolumeManager(boolean load) {
        // Load the volumes if set
        if(load)
            load();
    }

    /**
     * Constructor.
     *
     * @param volumes The list of volumes.
     */
    public VolumeManager(List<Volume> volumes) {
        // Construct the volume manager without loading anything
        this(false);

        // Store the volumes list
        this.volumes = volumes;
    }

    /**
     * Constructor.
     *
     * @param volumeManager Volume manager, to copy the volumes from.
     */
    public VolumeManager(VolumeManager volumeManager) {
        // Construct the volume manager without loading anything
        this(false);

        // Copy the volumes
        copy(volumeManager, true, true);
    }

    /**
     * Add a volume.
     *
     * @param v Volume.
     */
    public void addVolume(Volume v) {
        // TODO: Make sure the volume isn't a duplicate
        this.volumes.add(v);
    }

    /**
     * Get all loaded volumes.
     *
     * @return Loaded volumes.
     */
    public List<Volume> getVolumes() {
        return this.volumes;
    }

    /**
     * Get the number of loaded volumes.
     *
     * @return Number of loaded volumes.
     */
    public int getVolumesCount() {
        return this.volumes.size();
    }

    /**
     * Set the loaded volumes.
     *
     * @param volumes The volumes.
     */
    public void setVolumes(List<Volume> volumes) {
        setVolumes(volumes, false);
    }

    /**
     * Set the loaded volumes.
     *
     * @param volumes The volumes.
     * @param clone True to clone each volume instance, false if not.
     *
     * @return The number of volumes.
     */
    public int setVolumes(List<Volume> volumes, boolean clone) {
        // Set the volumes, clone them if set
        if(clone)
            this.volumes = cloneVolumeList(volumes);
        else
            this.volumes = volumes;

        // Return the number of volumes
        return getVolumesCount();
    }

    /**
     * Get a new UID, based on the volumes currently loaded.
     *
     * @return A new UID.
     */
    // TODO: Return just a random integer!
    public int getNewUID() {
        // Store the current highest UID
        int highestUid = -1;

        // Get the current highest UID
        for(Volume v : this.volumes)
            if(v.getUID() > highestUid || highestUid < 0)
                highestUid = v.getUID();

        // Return an unique ID
        return highestUid + 1;
    }

    /**
     * Clear the current list of loaded volumes
     * @return Amount of cleared volumes
     */
    public int clear() {
        // Get the amount of volumes available
        int c = getVolumesCount();

        // Clear the list of volumes
        this.volumes.clear();

        // Return the cleared volumes count
        return c;
    }

    /**
     * Get the data file to store the volumes in
     * @return Data file
     */
    public File getDataFile() {
        // TODO: Use a better method here!
        return new File(BlackBox.getDataDirectory(), "volumes/volumes.dat");
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
                System.out.println("Unable to load volumes data, the data file doesn't exist!");
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
                System.out.println("An error occurred while loading volumes data!");
            return false;
        }

        // Make sure the 'volumes' section exists
        if(!c.isConfigurationSection("volumes")) {
            if(showMsg)
                System.out.println("An error occurred while loading volumes data, invalid file!");
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
            Volume v = null;
            try {
                v = Volume.load(entrySect);
            } catch(Exception e) {
                e.printStackTrace();
            }

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
        this.volumes = buff;

        // Calculate the load duration
        long duration = System.currentTimeMillis() - start;

        // Show a status message
        if(showMsg)
            System.out.println("Loaded " + getVolumesCount() + " volumes successfully, took " + String.valueOf(duration) + "ms.");

        // Everything seems to be fine, return true
        return true;
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

        // Show a status message
        if(showMsg)
            System.out.println("Saving volumes data...");

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
        for(Volume v : this.volumes) {
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
        try {
            c.save(f);
        } catch (IOException e) {
            System.out.println("An error occurred while saving volumes data!");
            e.printStackTrace();
            return false;
        }

        // Calculate the save duration
        long duration = System.currentTimeMillis() - start;

        // Show a status message
        if(showMsg)
            System.out.println("Volumes data saved, took " + String.valueOf(duration) + "ms.");

        // Return the result
        return succeed;
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
            this.volumes.addAll(vm.getVolumes());
        else
            this.volumes.addAll(cloneVolumeList(vm.getVolumes()));
    }

    /**
     * Clone the volume manager instance
     * @return Cloned volume manager instance
     */
    // TODO: Make a proper clone method.
    public VolumeManager clone() {
        return new VolumeManager(this);
    }

    /**
     * Clone a volume list.
     * Note: This method is expensive with huge lists.
     *
     * @param volumes List of volumes to clone.
     *
     * @return Cloned volumes list.
     */
    public static List<Volume> cloneVolumeList(List<Volume> volumes) {
        // Define the cloned list
        List<Volume> clonedVolumes = new ArrayList<>();

        // Clone each volume and add it to the new list
        for(Volume entry : volumes)
            try {
                clonedVolumes.add(entry.clone());
            } catch(CloneNotSupportedException e) {
                e.printStackTrace();
            }

        // Return the cloned list
        return clonedVolumes;
    }
}
