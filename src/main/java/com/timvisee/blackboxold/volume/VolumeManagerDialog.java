package com.timvisee.blackboxold.volume;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.timvisee.blackbox.BlackBox;
import com.timvisee.blackboxold.Core;
import com.timvisee.blackboxold.components.BBDialog;
import com.timvisee.blackboxold.util.ListUtils;

public class VolumeManagerDialog extends BBDialog {

	private static final long serialVersionUID = 1992097566106778878L;
	
	private static final String WINDOW_NAME = "Volumes" ;
	
	private VolumeManager vm;
	
	private JTable table;
	private JScrollPane tablePane;
	private JButton createBtn;
	private JButton editBtn;
	private JButton moveUpBtn;
	private JButton moveDownBtn;
	private JButton delBtn;
	private JButton saveBtn;
	private JButton cancelBtn;
	
	boolean changed = false;
	
	/**
	 * Constructor
	 */
	public VolumeManagerDialog(Window owner) {
		// Construct super class
		super(owner, WINDOW_NAME);
        
        // Set the modality type if an owner is set
        if(owner != null)
        	setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		
		// Set the default close operation
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        final VolumeManagerDialog self = this;
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
        });
        
        // Clone the volume manager instance
		vm = Core.getVolumesManager().clone();
		
		// Build the UI
		buildUI();
		
		setMinimumSize(new Dimension(300, 350));
		
		// TODO: Set whether the frame is resizable, or does the frame support resizing?
		
		// Make the frame's location relative to the owner frame
		setLocationRelativeTo(owner);
		
		// Show the frame
		setVisible(true);
	}
	
	/**
	 * Build the frame UI
	 */
	public void buildUI() {
		/// Set the layout
		setLayout(new BorderLayout(0, 0));
		
		table = new JTable(new VolumeManagerTableModel(this.vm));
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.getTableHeader().setReorderingAllowed(false);
		table.setShowGrid(false);
		tablePane = new JScrollPane(table);
		tablePane.setBackground(Color.WHITE);
		tablePane.getViewport().setBackground(Color.WHITE);
		tablePane.setPreferredSize(new Dimension(300, 400));
		
		table.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
			        public void valueChanged(ListSelectionEvent e) {
			            updateButtons();
			        }
			    });
		
		
		JPanel wrapper = new JPanel();
		wrapper.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
		wrapper.setLayout(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridheight = GridBagConstraints.REMAINDER;
        wrapper.add(tablePane, gbc);
        
        gbc.gridx++;
        gbc.weightx = 0;
        gbc.insets = new Insets(0, 12, 0, 0);
        wrapper.add(getButtonsPanel(), gbc);

		add(wrapper);
		
		
		
		// Add some menu bar options on systems with a menu bar available
		/*if(Platform.getPlatform().equals(Platform.MAC_OS_X)) {
			MenuBar menuBar = new MenuBar();
			Menu fileMenu = new Menu("File");
			MenuItem prefsItem = new MenuItem("Preferences");
			fileMenu.add(prefsItem);
			Menu helpMenu = new Menu("Help");
			MenuItem aboutItem = new MenuItem("About");
			helpMenu.add(aboutItem);
			menuBar.add(fileMenu);
			menuBar.add(helpMenu);
			setMenuBar(menuBar);
		}*/
		
		pack();
	}
	
	public JPanel getButtonsPanel() {
		JPanel pnl = new JPanel();
		//pnl.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		pnl.setLayout(new BorderLayout());
		
		JPanel btnsTopPanel = new JPanel();
		btnsTopPanel.setLayout(new GridLayout(5, 1, 8, 8));
		
        createBtn = new JButton("Create");
        createBtn.setPreferredSize(new Dimension(90, createBtn.getPreferredSize().height));
        editBtn = new JButton("Edit");
        editBtn.setPreferredSize(new Dimension(90, editBtn.getPreferredSize().height));
        moveUpBtn = new JButton("Move Up");
        moveUpBtn.setPreferredSize(new Dimension(90, moveUpBtn.getPreferredSize().height));
        moveDownBtn = new JButton("Move Down");
        moveDownBtn.setPreferredSize(new Dimension(90, moveDownBtn.getPreferredSize().height));
        delBtn = new JButton("Delete");
        delBtn.setPreferredSize(new Dimension(90, delBtn.getPreferredSize().height));
        btnsTopPanel.add(createBtn);
        btnsTopPanel.add(editBtn);
        btnsTopPanel.add(moveUpBtn);
        btnsTopPanel.add(moveDownBtn);
        btnsTopPanel.add(delBtn);
		
		JPanel btnsBottomPanel = new JPanel();
		btnsBottomPanel.setLayout(new GridLayout(2, 1, 8, 8));
		
        saveBtn = new JButton("Save");
        saveBtn.setPreferredSize(new Dimension(90, saveBtn.getPreferredSize().height));
        cancelBtn = new JButton("Cancel");
        cancelBtn.setPreferredSize(new Dimension(90, cancelBtn.getPreferredSize().height));
        btnsBottomPanel.add(saveBtn);
        btnsBottomPanel.add(cancelBtn);
        
        // Update the buttons
        updateButtons();
        
        final VolumeManagerDialog self = this;
        createBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	// TODO: Show the profile creation window
        		new VolumeTypeDialog(self);
                
                // Update the table
                table.updateUI();
                
                // Save the data
                changed = true;
            }
        });
        editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	// Make sure any row is selected
            	if(table.getSelectedRowCount() == 0)
            		return;
            	
            	// Get the index of the selected row
            	//int rowIndex = table.getSelectedRow();
            	
            	// Get the selected player
            	//Volume v = self.v.get(rowIndex);
            	
            	// TODO: Show the profile creation window
                
                // Update the table
                table.updateUI();
                
                // Save the data
                changed = true;
            }
        });
        moveUpBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	// Get the selected item and check if any item is selected
            	if(table.getSelectedRows().length <= 0)
            		return;
            	
            	// Make sure the first selected item is not the first item in the list
            	if(table.getSelectedRow() <= 0)
            		return;
            	
            	// Move each item one up
            	for(int i : table.getSelectedRows())
            		self.vm.setVolumes(ListUtils.moveItemUp(self.vm.getVolumes(), i));
            	
            	// Set the selected rows
            	if(table.getSelectedRows()[0] != 0)
            		table.setRowSelectionInterval(table.getSelectedRows()[0] - 1, table.getSelectedRows()[table.getSelectedRows().length-1] - 1);
                
            	// Update the table
                table.updateUI();
                
                // Save the data
                changed = true;
            }
        });
        moveDownBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	// Get the selected item and check if any item is selected
            	if(table.getSelectedRows().length <= 0)
            		return;
            	
            	// Make sure the last selected item is not the last item in the list
            	if(table.getSelectedRows()[table.getSelectedRowCount() - 1] >= table.getRowCount() - 1)
            		return;
            	
            	// Move each item one up
            	for(int i = table.getSelectedRows().length - 1; i >= 0; i--)
            		self.vm.setVolumes(ListUtils.moveItemDown(self.vm.getVolumes(), table.getSelectedRows()[i]));
            	
            	// Set the selected rows
            	if(table.getSelectedRows()[table.getSelectedRowCount() - 1] <  table.getRowCount() - 1)
            		table.setRowSelectionInterval(table.getSelectedRows()[0] + 1, table.getSelectedRows()[table.getSelectedRowCount() - 1] + 1);
                
                // Update the table
                table.updateUI();
                
                // Save the data
                changed = true;
            }
        });
        delBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	// Get the selected item and check if any item is selected
            	int rowsIndex[] = table.getSelectedRows();
            	if(rowsIndex.length == 0)
            		return;
            	
            	// Show a question box, to ask whether the user wants to delete the selected volumes
				int result = JOptionPane.showConfirmDialog(self, "Are you sure you want to delete " + ((rowsIndex.length == 1) ? "this volume" : "these volumes") + "?", BlackBox.APP_NAME, JOptionPane.YES_NO_OPTION);
				
				// Do not delete the selected volumes when the user didn't choose yes
				if(result != JOptionPane.YES_OPTION)
					return;
            	
            	// Remove all selected rows
            	for(int i = rowsIndex.length - 1; i >= 0; i--)
                	// Remove the row
            		self.vm.getVolumes().remove(rowsIndex[i]);
                
                // Update the table and the buttons
                table.updateUI();
                updateButtons();
                
                // Save the data
                changed = true;
            }
        });
        
        saveBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Save the volumes
				saveVolumes();
				
				// Dispose the frame
				self.dispose();
			}
        });
        cancelBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
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
        });

        pnl.add(btnsTopPanel, BorderLayout.NORTH);
        pnl.add(btnsBottomPanel, BorderLayout.SOUTH);
        pnl.setPreferredSize(new Dimension(90, tablePane.getPreferredSize().height));
        
        return pnl;
	}
	
	public void updateButtons() {
		int rows[] = this.table.getSelectedRows();
		
		if(rows.length == 0) {
			editBtn.setEnabled(false);
			delBtn.setEnabled(false);
			moveUpBtn.setEnabled(false);
			moveDownBtn.setEnabled(false);
			
		} else if(rows.length == 1) {
			editBtn.setEnabled(true);
			delBtn.setEnabled(true);
			moveUpBtn.setEnabled(!(rows[0] <= 0));
			moveDownBtn.setEnabled(!(rows[rows.length - 1] >= (this.table.getRowCount() - 1)));
			
		} else {
			editBtn.setEnabled(false);
			delBtn.setEnabled(true);
			moveUpBtn.setEnabled(!(rows[0] <= 0));
			moveDownBtn.setEnabled(!(rows[rows.length - 1] >= (this.table.getRowCount() - 1)));
		}
	}
	
	/**
	 * Save the list of volumes
	 * @return True if succeed, false if failed
	 */
	public boolean saveVolumes() {
		// Update the volumes in the volumes manager with the new ones
		Core.getVolumesManager().copy(this.vm, true, false);
		
		// Save the volumes and return the result
		return Core.getVolumesManager().save();
	}
}
