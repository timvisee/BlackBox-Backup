package com.timvisee.blackboxold.volume;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Window;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.timvisee.blackboxold.components.BBDialog;

public class VolumeTypeDialog extends BBDialog {
	
	private static final long serialVersionUID = 1L;
	
	private static final String WINDOW_NAME = "Choose a volume type..." ;
	
	protected List<VolumeType> allowedTypes = new ArrayList<VolumeType>();
	
	protected JButton createBtn;
	protected List<JRadioButton> typeBtns = new ArrayList<JRadioButton>();
	
	public VolumeTypeDialog(Window owner) {
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
		
		// TODO: Set whether the frame is resizable, or does the frame support resizing?
		
		// Make the frame's location relative to the owner frame
		setLocationRelativeTo(owner);
		
		setResizable(false);
							
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
        gbc.insets = new Insets(0, 0, 8, 0);
        wrapper.add(new JLabel("Choose the volume type you want to create:"), gbc);

        gbc.gridy++;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        wrapper.add(getTypeSelectionPanel(), gbc);
        
        gbc.gridy++;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1;
        gbc.insets = new Insets(12, 0, 0, 0);
        this.createBtn = new JButton("Create");
        wrapper.add(this.createBtn, gbc);

		add(wrapper);
		
		// Pack the UI components
		pack();
	}
	
	public JPanel getTypeSelectionPanel() {
		this.allowedTypes.clear();
		this.allowedTypes.add(VolumeType.SYSTEM_VOLUME);
		this.allowedTypes.add(VolumeType.FTP_VOLUME);
		
		this.typeBtns.clear();
		
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(allowedTypes.size(), 1));

		ButtonGroup group = new ButtonGroup();
		
		for(int i = 0; i < allowedTypes.size(); i++) {
			VolumeType vt = allowedTypes.get(i);
			
			JRadioButton rb = new JRadioButton(vt.getName());
			this.typeBtns.add(rb);
			if(i == 0)
				rb.setSelected(true);
			
			p.add(rb);
			group.add(rb);
		}
		
		return p;
	}
	
	public static VolumeType chooseType(Window owner) {
		VolumeTypeDialog vtd = new VolumeTypeDialog(owner);
		
		return VolumeType.UNKNOWN_VOLUME;
	}
}
