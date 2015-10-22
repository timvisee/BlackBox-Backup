package com.timvisee.blackbox.volume.ftp;

import com.timvisee.blackbox.util.PathUtils;
import com.timvisee.blackbox.volume.VolumeAdapter;
import com.timvisee.blackbox.volume.VolumeFile;
import com.timvisee.blackbox.volume.VolumeType;
import com.timvisee.yamlwrapper.configuration.ConfigurationSection;
import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VolumeAdapterFTP extends VolumeAdapter {

    /** FTP volume host. */
    private String host;
    /** FTP volume host port. */
    private int port;
    /** FTP volume user. */
    private String user;
    /** FTP volume password. */
    private String pass;
    /** The root directory of this volume. */
    private String root;
    /** FTP connection instance. */
    private FTPClient connection;

    // TODO: Create this constructor!
    /**
     * Constructor.
     * Note: This constructor doesn't initialize the volume adapter. The .load() method should be executed afterwards.
     */
    public VolumeAdapterFTP(ConfigurationSection c) {
        // Load from configuration
        loadFromConfig(c);
    }

    /**
     * Constructor.
     *
     * @param host Host.
     * @param port Host port.
     * @param user User.
     * @param pass Password.
     * @param root Root directory.
     */
    public VolumeAdapterFTP(String host, int port, String user, String pass, String root) {
        this.host = host;
        this.port = port;
        this.user = user;
        this.pass = pass;
        this.root = root;
    }

    /**
     * Get the FTP host.
     *
     * @return Host.
     */
    public String getHost() {
        return this.host;
    }

    /**
     * Get the FTP host port.
     *
     * @return Host port.
     */
    public int getPort() {
        return this.port;
    }

    /**
     * Get the FTP user.
     *
     * @return User.
     */
    public String getUser() {
        return this.user;
    }

    /**
     * Get the FTP password.
     *
     * @return Password.
     */
    public String getPassword() {
        return this.pass;
    }

    /**
     * Get the FTP root path.
     *
     * @return Root path.
     */
    public String getRootPath() {
        return this.root;
    }

    @Override
    public VolumeType getVolumeType() {
        return VolumeType.FTP_VOLUME;
    }

    @Override
    public boolean connect() {
        // TODO: Should we set the connection instance somewhere before?
        // TODO: What should we do with this recon variable?
        boolean recon = false;

        try {
            // Check whether a connection was made already
            if(this.connection.isConnected() && this.connection.isAuthenticated()) {
                // Check whether we should reconnect, if not, return true
                if(!recon)
                    return true;
                else
                    this.connection.disconnect(true);
            }

        } catch (IllegalStateException | IOException | FTPIllegalReplyException | FTPException e) {
            e.printStackTrace();
        }

        try {
            // Reconnect to the server
            if(recon || !this.connection.isConnected()) {
                // Reinitialize the connection
                this.connection = new FTPClient();

                // Connect to the server
                if(this.port <= 0)
                    this.connection.connect(this.host);
                else
                    this.connection.connect(this.host, this.port);
            }

            // Login onto the server if we're not authenticated already
            if(!this.connection.isAuthenticated()) {
                // Login onto the server
                this.connection.login(this.user, this.pass);

                // Set the proper root directory
                this.connection.changeDirectory(this.root);
            }

            // Return true if the connection was successful
            return (this.connection.isConnected() && this.connection.isAuthenticated());

        } catch(FTPException | IllegalStateException | IOException | FTPIllegalReplyException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean isConnected() {
        // Make sure the connection instance is set
        if(this.connection == null)
            return false;

        // Make sure the FTP volume is connected, return the results
        // TODO: Should we also check if the connection is authenticated?
        return this.connection.isConnected() && this.connection.isAuthenticated();
    }

    @Override
    public boolean disconnect() {
        // Make sure a connection instance is set
        if(this.connection == null)
            return true;

        // Try to disconnect
        try {
            // Disconnect, return the result if succeed
            this.connection.disconnect(true);
            return true;

        } catch(IllegalStateException | IOException | FTPIllegalReplyException | FTPException e) {
            // Failed to disconnect, print the stack trace and return the result
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String getFileSeparator() {
        // TODO: Is this separator correct, for all systems?
        return "/";
    }

    @Override
    public VolumeFile getRoot() {
        return this.getFile("");
    }

    @Override
    public String getName(VolumeFile file) {
        // TODO: Is this correct?
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
