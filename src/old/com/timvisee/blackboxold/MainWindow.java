package com.timvisee.blackboxold;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTree;

import com.timvisee.blackboxold.BlackBox;
import com.timvisee.blackboxold.components.BBFrame;
import com.timvisee.blackboxold.volume.VolumeManagerDialog;
import com.timvisee.blackboxold.volume.VolumeTypeDialog;

public class MainWindow extends BBFrame {
	
	private static final long serialVersionUID = 9004155337255238481L;
	
	/**
	 * Constructor
	 * Constructs the main frame
	 */
	public MainWindow() {
		// Construct the super class and set the window title
		super(BlackBox.APP_NAME + " " + BlackBox.APP_VERSION_NAME);
		
		new VolumeTypeDialog(this);
		
		// Build the frame UI
		buildUI();
		
		// Set the peferred frame size
		setPreferredSize(new Dimension(640, 480));
		
		// Pack the frame contents
		pack();
		
		// Set the window location
		setLocationRelativeTo(null);
		setLocationByPlatform(true);
		
		// Set the default close operation
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
        // Add a window listener
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent evt) {
            	Core.showTrayIconMessage(
        				BlackBox.APP_NAME + " still running!",
        				BlackBox.APP_NAME + " is still running. You can access " + BlackBox.APP_NAME + " again using the system tray icon.",
        				MessageType.INFO
        				);
            }
        });
        
		// Make the window visible
		setVisible(true);
	}
	
	/**
	 * Build the UI for the frame
	 */
	public void buildUI() {
		// Set the layout
		setLayout(new BorderLayout(0, 0));

		// Build the menu bar
		buildMenuBar();
		
		// Create a tabbed pane to put all tab pages in
		JTabbedPane tabs = new JTabbedPane(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabs.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		// Add the tabs
		tabs.add("Dashboard", getDashboardPanel());
		tabs.add("Backups", getBackupsPanel());
		tabs.add("Scheduled Backups", getScheduledBackupsPanel());
		
		// Add the tab control to the frame
		add(tabs);
	}
	
	/**
	 * Get the dashboard panel
	 * @return Dashboard panel
	 */
	public JPanel getDashboardPanel() {
		JPanel p = new JPanel();
		p.setBackground(Color.WHITE);
		
		return p;
	}
	
	/**
	 * Get the backups panel
	 * @return Backups panel
	 */
	public JPanel getBackupsPanel() {
		// Create the backups panel
		JPanel backupsPanel = new JPanel();
		backupsPanel.setBackground(Color.WHITE);
		backupsPanel.setLayout(new GridLayout(1, 1, 0, 0));
		backupsPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
		final JTree backupsTree = new JTree();
		JScrollPane backupsTreePane = new JScrollPane(backupsTree);
		backupsTreePane.setPreferredSize(new Dimension(400, 350));
		backupsPanel.add(backupsTreePane);
		
		// Return the backups panel
		return backupsPanel;
	}
	
	/**
	 * Get the scheduled backups panel
	 * @return Scheduled backups panel
	 */
	public JPanel getScheduledBackupsPanel() {
		// Create the scheduled backups panel
		JPanel scheduledBackupsPanel = new JPanel();
		scheduledBackupsPanel.setBackground(Color.WHITE);
		scheduledBackupsPanel.setLayout(new GridLayout(1, 1, 0, 0));
		scheduledBackupsPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
		final JTree scheduledBackupsTree = new JTree();
		JScrollPane scheduledBackupsTreePane = new JScrollPane(scheduledBackupsTree);
		scheduledBackupsTreePane.setPreferredSize(new Dimension(400, 350));
		scheduledBackupsPanel.add(scheduledBackupsTreePane);
		
		// Return the scheduled backups panel
		return scheduledBackupsPanel;
	}
	
	/**
	 * Build the menu bar
	 */
	public void buildMenuBar() {
		// Get a final instance of this frame
		final JFrame self = this;
		
		// Get the MenuBar instance
		MenuBar menuBar = new MenuBar();
	
		// Define the File menu and add some items
		Menu fileMenu = new Menu("File");
		MenuItem prefsItem = new MenuItem("Preferences");
		prefsItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Show an 'about' message
				new PreferencesWindow(self);
			}
		});
		fileMenu.add(prefsItem);
		fileMenu.addSeparator();
		MenuItem hideItem = new MenuItem("Hide");
		hideItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Dispose the frame
				self.dispose();
				
				// TODO: Create function for this stuff bellow...
				// Show a warning message
				Core.showTrayIconMessage(
        				BlackBox.APP_NAME + " still running!",
        				BlackBox.APP_NAME + " is still running. You may access Backup Manager Server again using the tray icon.",
        				MessageType.INFO
        				);
			}
		});
		fileMenu.add(hideItem);
		MenuItem exitItem = new MenuItem("Exit");
		exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Exit the application
				System.exit(0);
			}
		});
		fileMenu.add(exitItem);
	
		// Define the File menu and add some items
		Menu volumesMenu = new Menu("Volumes");
		MenuItem volumesItem = new MenuItem("Manage Volumes");
		volumesItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Show an 'about' message
				new VolumeManagerDialog(self);
			}
		});
		volumesMenu.add(volumesItem);
		volumesMenu.addSeparator();
		MenuItem createVolumeItem = new MenuItem("Create Volume");
		createVolumeItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		volumesMenu.add(createVolumeItem);
		
		// Define the help menu and add some items
		Menu helpMenu = new Menu("Help");
		MenuItem aboutItem = new MenuItem("About");
		aboutItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Show an 'about' message
				new AboutWindow(self);
			}
		});
		helpMenu.add(aboutItem);
		
		// Add the menu items to the menu bar
		menuBar.add(fileMenu);
		menuBar.add(volumesMenu);
		menuBar.add(helpMenu);
		
		// Set/update the menu bar
		setMenuBar(menuBar);
	}
	
	/*public static int num = 0;
	
	private DefaultMutableTreeNode processHierarchy(File root) {
		num++;
		
		if(num > 6)
			return new DefaultMutableTreeNode(root.getName());
		
		DefaultMutableTreeNode node = new DefaultMutableTreeNode(root.getAbsolutePath());
		
		for(File f : root.listFiles()) {
			DefaultMutableTreeNode child = new DefaultMutableTreeNode(f.getName());
			Random rand = new Random();
			if(f.isDirectory()) {
				System.out.println("Listing: " + f.getAbsolutePath());
				child.add(processHierarchy(f));
			}
			node.add(child);
		}
		
		return node;
	}*/
}
