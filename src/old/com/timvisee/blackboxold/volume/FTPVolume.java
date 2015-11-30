package com.timvisee.blackboxold.volume;

import com.timvisee.blackboxold.volume.Volume;
import com.timvisee.blackboxold.volume.filesystem.VolumeFile;
import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;

import java.io.IOException;

import com.timvisee.yamlwrapper.configuration.ConfigurationSection;

public class FTPVolume extends Volume {

	/** @var host Volume host */
	private String host;
	/** @var port Volume port */
	private int port;
	/** @var user Volume user */
	private String user;
	/** @var pass Volume password */
	private String pass;
	/** @var dir Volume root directory */
	private String root;
	/** @var con Volume connection */
	private FTPClient con;

	/**
	 * Constructor
	 * @param uid Unique ID
	 * @param enabled True if enabled
	 * @param name Volume name
	 * @param root Volume root directory
	 */
	public FTPVolume(int uid, boolean enabled, String name, String root) {
		// Construct parent
		super(uid, enabled, name);
		
		// Store directory
		this.root = root;
	}
	
	/**
	 * Constructor
	 * @param uid Unique volume ID
	 * @param enabled True if enabled
	 * @param name Volume name
	 * @param host Volume host
	 * @param port Volume port
	 * @param user Volume user
	 * @param pass Volume pass
	 * @param root Volume root directory
	 */
	public FTPVolume(int uid, boolean enabled, String name, String host, int port, String user, String pass, String root) {
		// Construct the parent
		super(uid, enabled, name);
		
		// Store the other params
		this.host = host;
		this.port = port;
		this.user = user;
		this.pass = pass;
		this.root = root;
	}

	/**
	 * Connect to the volume
	 * @return True if successfully connected, false otherwise
	 */
	public boolean connect() {
		return connect(false);
	}
	
	/**
	 * Connect to the volume
	 * @param recon True to force to reconnect
	 * @return True if successfully connected, false otherwise
	 */
	public boolean connect(boolean recon) {
		try {
			// Check whether a connecion was made already
			if(this.con.isConnected() && this.con.isAuthenticated()) {
				// Check whether we should reconnect, if not, return true
				if(!recon)
					return true;
				else
					this.con.disconnect(true);
			}
		} catch (IllegalStateException | IOException | FTPIllegalReplyException | FTPException e) {
			e.printStackTrace();
		}
		
		try {
			// Reconnect to the server
			if(recon || !this.con.isConnected()) {
				// Reinitialize the connection
				this.con = new FTPClient();
				
				// Connect to the server
				if(this.port <= 0)
					this.con.connect(this.host);
				else
					this.con.connect(this.host, this.port);
			}
			
			// Login onto the server if we're not authenticated already
			if(!this.con.isAuthenticated()) {
				// Login onto the server
				this.con.login(this.user, this.pass);
	
				// Set the proper root directory
				this.con.changeDirectory(this.root);
			}
			
			// Return true if the connection was successful
			return (this.con.isConnected() && this.con.isAuthenticated());
			
		} catch(FTPException | IllegalStateException | IOException | FTPIllegalReplyException ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Check whether we're connected to the volume
	 * @return True if we're connected, false otherwise
	 */
	public boolean isConnected() {
		return con.isConnected();
	}
	
	/**
	 * Disconnect from the volume
	 * @return True if succeed, false otherwise
	 */
	public boolean disconnect() {
		try {
			// Disconnect from the FTP server and return true
			con.disconnect(true);
			return true;
			
		} catch (IllegalStateException | IOException | FTPIllegalReplyException | FTPException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Get the FTP connection
	 * @return FTP connection
	 */
	public FTPClient getConnection() {
		return this.con;
	}
	
	/**
	 * Get the root file
	 * @return Root file
	 */
	public VolumeFile getRoot() {
		return new VolumeFile(this, this.root);
	}
	
	/**
	 * Get the root file path
	 * @return Root  file path
	 */
	public String getRootPath() {
		return this.root;
	}
	
	/**
	 * Set the root file path
	 * @param root Root file path
	 */
	public void setRootPath(String root) {
		this.root = root;
	}
	
	/**
	 * Get the volume type
	 * @return Volume type
	 */
	@Override
	protected VolumeType getVolumeType() {
		return VolumeType.SYSTEM_VOLUME;
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
		c.set("host", this.host);
		c.set("port", this.port);
		c.set("user", this.user);
		c.set("pass", this.pass);
		c.set("root", this.root);
		
		// Everything seems to be fine, return true
		return true;
	}
	
	/**
	 * Clone the volume
	 * @return Cloned volume
	 */
	@Override
	public FTPVolume clone() {
		return new FTPVolume(this.uid, this.enabled, this.name, this.root);
	}
}
