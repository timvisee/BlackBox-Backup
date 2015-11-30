package com.timvisee.blackboxold;

import java.awt.AWTException;
import java.awt.Frame;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.timvisee.blackboxold.BlackBox;
import com.timvisee.blackboxold.util.WindowUtils;
import com.timvisee.blackboxold.volume.VolumeManager;

public class Core {

	private static VolumeManager vm;
	
	private static TrayIcon trayIcon;
	
	private static MainWindow mf;
	
	/**
	 * Constructor
	 */
	public Core() {
		// Initialize
		init();
	}
	
	/**
	 * Initialize
	 */
	private static void init() {
		// Show a status message
		System.out.println("Initializing...");
		
		// Try to set the LAF of the program to the system's default
		WindowUtils.useNativeLookAndFeel();
		
		// Initialize and show a loading frame
		ProgressDialog lf = new ProgressDialog(null, BlackBox.APP_NAME, "Initializing...");
		lf.setVisible(true);
		
		// Get the data directory
		File dataDir = BlackBox.getDataDirectory();
		
		// Make sure the data directory is available
		if(!dataDir.isDirectory()) {
			// Create data directory
			lf.setStatus("Creating data directory...");
			
			dataDir.mkdirs();
			// TODO: Create default files here!
		}
		
		// Initialize and set up the volumes manager
		lf.setStatus("Loading volumes data...");
		setUpVolumesManager();
		
		// Create the system tray icon
		lf.setStatus("Creating system tray icon...");
		createSystemTrayIcon();
		
		
		
		
		
		/*lf.setStatus("Connecting to data server...");
		
		FTPClient c = new FTPClient();
		try {
			c.connect("JARVIS");
			lf.setStatus("Logging in...");
			c.login("BackupManagerUser", "tjvb179");

			lf.setStatus("Navigating to directory...");
			c.changeDirectory("BackupManagerDir/Test");

			lf.setStatus("Downloading file...");
			c.download(
					"/BackupManagerDir/Test/RawData/Tim/RawData - Documents/ISO's en programma's/Camtasia Studio 7.0.0 + Serials & Keygen - DivXNL-team/Camtasia Studio 7.0.0.zip",
					new File("C:\\Users\\Tim\\Documents\\BackupManager Test Directory\\TestFile.zip"));
			lf.setStatus("File downloaded!");
			
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FTPIllegalReplyException e) {
			e.printStackTrace();
		} catch (FTPException e) {
			e.printStackTrace();
		} catch (FTPDataTransferException e) {
			e.printStackTrace();
		} catch (FTPAbortedException e) {
			e.printStackTrace();
		}*/
		
		
		
		
		
		
		
		// Successfully initialized, show a status message and dispose the loading frame
		lf.setStatus("Successfully initialized!");
		System.out.println("Initialized! Cave Johnson here!");
		lf.dispose();
		
		// Show the main frame
		showMainFrame();
	}
	
	/**
	 * Show the main frame
	 */
	public static void showMainFrame() {
		// Create and start a thread to run the MainFrame in
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				// Refocus the window if it's already shown
				if(Core.mf != null) {
					if(Core.mf.isVisible()) {
						Core.mf.setState(Frame.NORMAL);
						Core.mf.requestFocus();
						return;
					}
				}
				
				// Instantiate a new main frame
				Core.mf = new MainWindow();
			}
		});
		t.start();
	}
	
	/**
	 * Initialize and set up the volumes manager
	 */
	private static void setUpVolumesManager() {
		Core.vm = new VolumeManager(true);
	}
	
	/**
	 * Get the volumes manager instance
	 * @return Volumes manager instance
	 */
	public static VolumeManager getVolumesManager() {
		return Core.vm;
	}
	
	/**
	 * Create the system tray icon
	 */
	private static void createSystemTrayIcon() {
		// Check the SystemTray is supported
        if(!SystemTray.isSupported()) {
            System.out.println("Unable to create system tray icon, because it's not supported on this device!");
            return;
        }
        
        // Define the popup menu, system tray and system tray instance
        final PopupMenu popup = new PopupMenu();
        TrayIcon trayIcon = null;
		try {
			InputStream in = BlackBox.class.getResourceAsStream("/res/icon_menubar.png");
			if(in != null) {
				Image i = ImageIO.read(in);
				trayIcon = new TrayIcon(i);
				trayIcon.setImageAutoSize(true);
				trayIcon.setToolTip(BlackBox.APP_NAME + " " + BlackBox.APP_VERSION_NAME);
			}
		} catch (IOException e1) { }
        final SystemTray tray = SystemTray.getSystemTray();
       
        // Create a pop-up menu components
        MenuItem openItem = new MenuItem("Show " + BlackBox.APP_NAME);
        openItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Show the main screen
				showMainFrame();
			}
		});
        popup.add(openItem);
        MenuItem prefsItem = new MenuItem("Preferences");
        prefsItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Show the preferences frame
				new PreferencesWindow(mf);
			}
		});
        popup.add(prefsItem);
        popup.addSeparator();
        MenuItem aboutItem = new MenuItem("About");
        aboutItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Show the about frame
				new AboutWindow(mf);
			}
		});
        popup.add(aboutItem);
        popup.addSeparator();
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Exit the application
				System.exit(0);
			}
		});
        popup.add(exitItem);
       
        // Set the popup menu of the system tray icon
        trayIcon.setPopupMenu(popup);
        
        // Add the system tray icon to the system tray
        try {
            tray.add(trayIcon);
            
        } catch (AWTException e) {
            System.out.println("Failed creating SystemTray icon!");
        }
        
        Core.trayIcon = trayIcon;
	}
	
	/**
	 * Show a system tray message
	 * @param title Message title
	 * @param msg Message text
	 * @param msgType Message type
	 * @return False if failed to show message, true otherwise
	 */
	public static boolean showTrayIconMessage(String title, String msg, MessageType msgType) {
		// Make sure the tray icon instance is not null
		if(trayIcon == null)
			return false;
		
		// Show the message and return true
		trayIcon.displayMessage(title, msg, msgType);
		return true;
	}
}
