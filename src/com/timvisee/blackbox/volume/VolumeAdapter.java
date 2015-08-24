package com.timvisee.blackbox.volume;

import com.timvisee.blackbox.volume.filesystem.VolumeAdapterFilesystem;
import com.timvisee.yamlwrapper.configuration.ConfigurationSection;

import java.io.File;
import java.util.List;

public abstract class VolumeAdapter {

    /**
     * Get the volume type.
     *
     * @return Volume type.
     */
    public abstract VolumeType getVolumeType();

    /**
     * Connect to the volume.
     *
     * @return True on success, false on failure.
     */
    public abstract boolean connect();

    /**
     * Check whether the adapter is connected to the volume.
     *
     * @return True if the adapter is connected, false otherwise.
     */
    public abstract boolean isConnected();

    /**
     * Disconnect the adapter from the volume.
     *
     * @return True on success, false on failure. True will also be returned if the adapter isn't connected.
     */
    public abstract boolean disconnect();

    /**
     * Get the file separator character used on this volume.
     *
     * @return File separator character.
     */
    public abstract String getFileSeparator();

    /**
     * Get the root of the volume filesystem.
     *
     * @return The root of the volume filesystem.
     */
    public abstract VolumeFile getRoot();

    public abstract List<VolumeFile> list(VolumeFile dir);

    /**
     * Check whether a volume file is an existing file.
     *
     * @param file The volume file.
     *
     * @return True if the volume file is an existing file.
     */
    public abstract boolean isFile(VolumeFile file);

    /**
     * Check whether a volume file is an existing directory.
     *
     * @param dir The volume directory.
     *
     * @return True if the volume file is an existing directory.
     */
    public abstract boolean isDirectory(VolumeFile dir);

    /**
     * Load a volume adapter from a configuration section.
     *
     * @param c Configuration section.
     *
     * @return Volume adapter, or null if failed.
     */
    // TODO: Update this code!
    public static VolumeAdapter loadAdapter(ConfigurationSection c) throws Exception {
        // TODO: Log error messages on load failures!

        // Make sure the configuration section isn't null
        if(c == null)
            return null;

        // Make sure a volume type is supplied
        // TODO: Try to parse volumes without a supplied type to prevent possible errors
        if(!c.isInt("type"))
            return null;

        // Parse the volume type
        int typeId = c.getInt("type");
        VolumeType type = VolumeType.fromTypeId(typeId);

        // Make sure the volume type is valid
        if(type == null)
            return null;

        // Get the data section, and make sure it's valid
        ConfigurationSection dataSection = c.getSection("data");
        if(dataSection == null)
            return null;

        // Load the required data based on the
        switch(type) {
        case FILESYSTEM_VOLUME:
            return new VolumeAdapterFilesystem(dataSection);

        default:
            // Unable to load/parse volume, return null
            return null;
        }
    }

    /**
     * Load the volume properties from a configuration section.
     *
     * @param c Configuration section.
     *
     * @return True if succeed, false on failure.
     */
    public abstract boolean loadFromConfig(ConfigurationSection c);

    /**
     * Save the volume properties to the configuration section.
     *
     * @param c Configuration section.
     *
     * @return True on success, false on failure.
     */
    public abstract boolean saveToConfig(ConfigurationSection c);
}
