package com.timvisee.blackboxold.components;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;

import com.timvisee.blackboxold.util.WindowUtils;

public class BBFrame extends JFrame {
	
	private static final long serialVersionUID = 944665203994303247L;

	/**
     * Constructs a new frame that is initially invisible.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by <code>JComponent.getDefaultLocale</code>.
     *
     * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     * returns true.
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see Component#setSize
     * @see Component#setVisible
     * @see JComponent#getDefaultLocale
     */
    public BBFrame() throws HeadlessException {
        super();

		// Set the frame icon
		WindowUtils.setWindowIcon(this);
    }

    /**
     * Creates a <code>Frame</code> in the specified
     * <code>GraphicsConfiguration</code> of
     * a screen device and a blank title.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by <code>JComponent.getDefaultLocale</code>.
     *
     * @param gc the <code>GraphicsConfiguration</code> that is used
     *          to construct the new <code>Frame</code>;
     *          if <code>gc</code> is <code>null</code>, the system
     *          default <code>GraphicsConfiguration</code> is assumed
     * @exception IllegalArgumentException if <code>gc</code> is not from
     *          a screen device.  This exception is always thrown when
     *      GraphicsEnvironment.isHeadless() returns true.
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see JComponent#getDefaultLocale
     * @since     1.3
     */
    public BBFrame(GraphicsConfiguration gc) {
        super(gc);

		// Set the frame icon
		WindowUtils.setWindowIcon(this);
    }

    /**
     * Creates a new, initially invisible <code>Frame</code> with the
     * specified title.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by <code>JComponent.getDefaultLocale</code>.
     *
     * @param title the title for the frame
     * @exception HeadlessException if GraphicsEnvironment.isHeadless()
     * returns true.
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see Component#setSize
     * @see Component#setVisible
     * @see JComponent#getDefaultLocale
     */
    public BBFrame(String title) throws HeadlessException {
        super(title);

		// Set the frame icon
		WindowUtils.setWindowIcon(this);
    }

    /**
     * Creates a <code>JFrame</code> with the specified title and the
     * specified <code>GraphicsConfiguration</code> of a screen device.
     * <p>
     * This constructor sets the component's locale property to the value
     * returned by <code>JComponent.getDefaultLocale</code>.
     *
     * @param title the title to be displayed in the
     *          frame's border. A <code>null</code> value is treated as
     *          an empty string, "".
     * @param gc the <code>GraphicsConfiguration</code> that is used
     *          to construct the new <code>JFrame</code> with;
     *          if <code>gc</code> is <code>null</code>, the system
     *          default <code>GraphicsConfiguration</code> is assumed
     * @exception IllegalArgumentException if <code>gc</code> is not from
     *          a screen device.  This exception is always thrown when
     *      GraphicsEnvironment.isHeadless() returns true.
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see JComponent#getDefaultLocale
     * @since     1.3
     */
    public BBFrame(String title, GraphicsConfiguration gc) {
        super(title, gc);

		// Set the frame icon
		WindowUtils.setWindowIcon(this);
    }
}
