package com.timvisee.blackboxold;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Window;

import javax.swing.JFrame;

import com.timvisee.blackboxold.components.BBDialog;

public class PreferencesWindow extends BBDialog {
	
	private static final long serialVersionUID = 6474208512476994660L;

	private static final String WINDOW_NAME = "Preferences";

	public PreferencesWindow(Window owner) {
		super(owner, WINDOW_NAME);
        
        // Set the modality type if an owner is set
        if(owner != null)
        	setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		
		// Set the default close operation
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //setResizable(false);
        
		// Build the frame UI
		buildUI();
		
		// Set the window location
		setLocationRelativeTo(owner);
        
		// Make the window visible
		setVisible(true);
	}
	
	public void buildUI() {
		setLayout(new BorderLayout(0, 0));
	}
}
