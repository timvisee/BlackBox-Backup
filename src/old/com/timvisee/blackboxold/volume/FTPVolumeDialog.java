package com.timvisee.blackboxold.volume;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import com.timvisee.blackboxold.components.BBDialog;

public class FTPVolumeDialog extends BBDialog {
	
	private static final long serialVersionUID = 1L;

	private JComboBox<VolumeType> volumeType;
	
	private static final String WINDOW_NAME = "Volume Properties" ;

	public FTPVolumeDialog(Window owner) {
		// Construct super class
		super(owner, WINDOW_NAME);
		
        // Set the modality type if an owner is set
        if(owner != null)
        	setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		
		// Set the default close operation
        //setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        /*final VolumeDialog self = this;
        addWindowListener(new WindowListener() {
			@Override
			public void windowActivated(WindowEvent e) { }

			@Override
			public void windowClosed(WindowEvent e) { }

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO: Put this stuff in a method
				
				// Dispose the frame if nothing has changed
				if(!changed) {
					self.dispose();
					return;
				}
				
				// Show a question box, and ask the user if he'd like to save the volumes
				int result = JOptionPane.showConfirmDialog(self, "Would you like to save the changes?", BlackBox.APP_NAME, JOptionPane.YES_NO_CANCEL_OPTION);
				
				// Do not save the volumes, dispose the window
				if(result  == JOptionPane.NO_OPTION)
					self.dispose();
				
				else if(result  == JOptionPane.YES_OPTION) {
					// Save the volumes
					saveVolumes();
					
					// Dispose the frame
					self.dispose();
				}			
			}

			@Override
			public void windowDeactivated(WindowEvent e) { }

			@Override
			public void windowDeiconified(WindowEvent e) { }

			@Override
			public void windowIconified(WindowEvent e) { }

			@Override
			public void windowOpened(WindowEvent e) { }
        });*/
        
        // Set the minimum size of the dialog
        //setMinimumSize(new Dimension(300, getMinimumSize().height));
        //setPreferredSize(new Dimension(300, getMinimumSize().height));
        
		// Build the UI
		buildUI();
		
		setMinimumSize(new Dimension(300, 400));
		
		// TODO: Set whether the frame is resizable, or does the frame support resizing?
		
		// Make the frame's location relative to the owner frame
		setLocationRelativeTo(owner);
							
		// Show the frame
		setVisible(true);
	}
	
	public void buildUI() {
		// Set the layout
		setLayout(new BorderLayout(0, 0));
		
		JPanel wrapper = new JPanel();
		wrapper.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
		wrapper.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        wrapper.add(getTypeSelectionPanel(), gbc);
        
        gbc.gridy++;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1;
        gbc.insets = new Insets(12, 0, 0, 0);
        wrapper.add(getVolumePropertiesPanel(), gbc);

		add(wrapper);
		
		// Pack the UI components
		pack();
	}
	
	public JPanel getTypeSelectionPanel() {
		JPanel p = new JPanel();
		
		GridBagConstraints field = new GridBagConstraints();
		field.fill = GridBagConstraints.HORIZONTAL;
		field.weightx = 1.0;
		field.gridwidth = GridBagConstraints.REMAINDER;
		field.insets = new Insets(2, 1, 2, 1);
		
		GridBagConstraints label = (GridBagConstraints) field.clone();
		label.weightx = 0.0;
		label.gridwidth = 1;
		label.insets = new Insets(1, 1, 1, 10);
		
		GridBagLayout layout = new GridBagLayout();
		p.setLayout(layout);
		
		JLabel typeLbl = new JLabel("Type:", SwingConstants.LEFT);
		
		List<VolumeType> allowedTypes = new ArrayList<VolumeType>();
		allowedTypes.add(VolumeType.SYSTEM_VOLUME);
		allowedTypes.add(VolumeType.FTP_VOLUME);
		this.volumeType = new JComboBox<VolumeType>(allowedTypes.toArray(new VolumeType[] {}));
		if(this.volumeType.getItemCount() > 0)
			this.volumeType.setSelectedIndex(0);

		typeLbl.setLabelFor(this.volumeType);
		layout.setConstraints(this.volumeType, field);
		
		p.add(typeLbl, label);
		p.add(this.volumeType, field);
		
		return p;
	}
	
	public JPanel getVolumePropertiesPanel() {
		JPanel p = new JPanel();
		p.setBorder(new TitledBorder("Properties"));
		
		return p;
	}
}
