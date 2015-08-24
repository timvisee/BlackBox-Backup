package com.timvisee.blackbox.volume.filesystem;

import com.timvisee.blackbox.util.PathUtils;
import com.timvisee.blackbox.volume.VolumeAdapter;
import com.timvisee.blackbox.volume.VolumeFile;
import com.timvisee.blackbox.volume.VolumeType;
import com.timvisee.yamlwrapper.configuration.ConfigurationSection;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class VolumeAdapterFilesystem extends VolumeAdapter {

    /** The root directory of this volume. */
    private File root;

    /**
     * Constructor.
     * Note: This constructor doesn't initialize the volume adapter. The .load() method should be executed afterwards.
     */
    public VolumeAdapterFilesystem(ConfigurationSection c) {
        // Load from configuration
        loadFromConfig(c);
    }

    /**
     * Constructor.
     *
     * @param root The root directory of this volume.
     */
    public VolumeAdapterFilesystem(File root) {
        // Set the root
        this.root = root;
    }

    @Override
    public VolumeType getVolumeType() {
        return VolumeType.FILESYSTEM_VOLUME;
    }

    @Override
    public boolean connect() {
        // TODO: Make sure the filesystem is available!
        return true;
    }

    @Override
    public boolean isConnected() {
        // TODO: Make sure the filesystem is available!
        return true;
    }

    @Override
    public boolean disconnect() {
        // TODO: Make sure the filesystem is available!
        return true;
    }

    @Override
    public String getFileSeparator() {
        return File.separator;
    }

    @Override
    public VolumeFile getRoot() {
        return this.getFile("");
    }

    @Override
    public String getName(VolumeFile file) {
        return getSystemFile(file).getName();
    }

    /**
     * Get a volume file.
     *
     * @param path The path of the file on the volume.
     *
     * @return The volume file.
     */
    public VolumeFile getFile(String path) {
        try {
            return new VolumeFile(this, path);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get the filesystem file from a volume file.
     *
     * @param path The volume file path.
     *
     * @return The filesystem file.
     */
    private File getSystemFile(String path) {
        return new File(this.root, path);
    }

    /**
     * Get the filesystem file from a volume file.
     *
     * @param file The volume file.
     *
     * @return The filesystem file.
     */
    private File getSystemFile(VolumeFile file) {
        return getSystemFile(file.getPath());
    }

    /**
     * Get the root directory as a Java File.
     *
     * @return Volume root.
     */
    public File getRootFile() {
        return this.root;
    }

    /**
     * List the files in a volume directory.
     *
     * @param dir Volume directory.
     *
     * @return Files in the volume directory, or null on failure.
     */
    public List<VolumeFile> list(VolumeFile dir) {
        // Create a list to put the files in
        List<VolumeFile> l = new ArrayList<>();

        // Get the system file
        File f = getSystemFile(dir.getPath());

        // Ensure the file is a directory, and ensure the directory exists, otherwise return null
        if(!f.isDirectory())
            return null;

        // List and process the files and directories
        try {
            //noinspection ConstantConditions
            for(File entry : f.listFiles())
                l.add(this.getFile(getRelativePath(getRootFile(), entry)));

        } catch(Exception e) {
            e.printStackTrace();
        }

        // Return the list of files
        return l;
    }

    public boolean isFile(VolumeFile file) {
        // Get the system file
        File systemFile = getSystemFile(file);

        // Check whether the file is a file, return the result
        return systemFile.isFile();
    }

    public boolean isDirectory(VolumeFile dir) {
        // Get the system file
        File systemFile = getSystemFile(dir);

        // Check whether the file is a directory, return the result
        return systemFile.isDirectory();
    }

    /**
     * Get the relative path from a base path and target path.
     *
     * @param base The base path.
     * @param target The target path.
     *
     * @return The relative path.
     */
    private String getRelativePath(File base, File target) {
        return PathUtils.getRelativePath(target.getAbsolutePath(), base.getAbsolutePath(), getFileSeparator());
    }

    @Override
    public boolean loadFromConfig(ConfigurationSection c) {
        /* // Get the basic volume properties
        int uid = c.getInt("uid", -1);
        boolean enabled = c.getBoolean("enabled", true);
        String name = c.getString("name", "Unnamed Volume");*/

        // Load the volume root, and make sure it's valid
        String rootPath = c.getString("root", "");
        if(rootPath.trim().length() == 0)
            return false;

        // TODO: Validate the path! (Whether it exists, and so on)

        // TODO: Validate the loaded root directory!

        // Parse and set the root directory
        this.root = new File(rootPath);
        return true;
    }

    @Override
    public boolean saveToConfig(ConfigurationSection c) {
        c.set("root", this.root.getAbsolutePath());
        return true;
    }
}
