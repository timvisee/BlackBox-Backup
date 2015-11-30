package com.timvisee.blackboxold;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Window;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.timvisee.blackboxold.BlackBox;
import com.timvisee.blackboxold.components.BBDialog;
import com.timvisee.blackboxold.components.ImagePanel;

public class AboutWindow extends BBDialog {

	private static final long serialVersionUID = -4529162597732570856L;

	private static final String WINDOW_NAME = "About";
	
	public AboutWindow(Window owner) {
		super(owner, WINDOW_NAME);
        
        // Set the modality type if an owner is set
        if(owner != null)
        	setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		
		// Set the default close operation
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setResizable(false);
        
		// Build the frame UI
		buildUI();
		
		// Set the window location
		setLocationRelativeTo(owner);
        
		// Make the window visible
		setVisible(true);
	}
	
	public void buildUI() {
		setLayout(new BorderLayout(0, 0));
		
		Image img = null;
		try {
            InputStream in = BlackBox.class.getResourceAsStream("/res/icon.png");
            if(in != null)
            	img = ImageIO.read(in);
        } catch (IOException e) { }
		
		JPanel iconBar = new JPanel();
		ImagePanel icon = new ImagePanel(img);
		icon.setPreferredSize(new Dimension(128, 128));
		iconBar.add(icon);
		
		JPanel main = new JPanel();
		main.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
		JLabel lbl = new JLabel("<html>" +
				"<b>" + BlackBox.APP_NAME + "</b><br>" +
				"Version " + BlackBox.APP_VERSION_NAME + " <span style=\"color: gray;\">(" + BlackBox.APP_VERSION_CODE + ")</span><br>" +
				"<br>" +
				"Created by Tim Vis�e<br>" +
				"<a href=\"http://timvisee.com/\">www.timvisee.com</a><br>" +
				"<br>" +
				"Copyright &copy; Tim Visee 2013. All rights reserved.");
		main.add(lbl);
		
		add(main);
		add(iconBar, BorderLayout.WEST);
		
		pack();
	}
}
