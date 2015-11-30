package com.timvisee.blackbox.components;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class ImagePanel extends JPanel {
	
	private static final long serialVersionUID = 4944844453827485298L;
	
	private Image image;
    private boolean tile;

    public ImagePanel(Image image) {
        this.image = image;
        this.tile = false;
    };

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (tile) {
            int iw = image.getWidth(this);
            int ih = image.getHeight(this);
            if (iw > 0 && ih > 0) {
                for (int x = 0; x < getWidth(); x += iw) {
                    for (int y = 0; y < getHeight(); y += ih) {
                        g.drawImage(image, x, y, iw, ih, this);
                    }
                }
            }
        } else {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }
}