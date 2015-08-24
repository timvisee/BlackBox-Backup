package com.timvisee.blackbox;

import com.timvisee.blackbox.util.WindowUtils;
import com.timvisee.blackbox.volume.VolumeManager;

public class Core {

    /** Volume manager. */
    private static VolumeManager volumeManager = new VolumeManager(false);

    /**
     * Constructor.
     */
    public Core() {
        // Initialize
        init();
    }

    /**
     * Initialize.
     */
    private static void init() {
        long start = System.currentTimeMillis();

        // Show a status message
        System.out.println("Initializing...");

        // Try to set the UI look and feel of the program to the system's default
        WindowUtils.useNativeLookAndFeel();

        // Load all volumes
        Core.volumeManager.load();

        // Initialization succeed, show a status message with the initialization duration
        long duration = System.currentTimeMillis() - start;
        System.out.println("Initialized successfully, took " + String.valueOf(duration) + "ms. Cave Johnson here");
    }
}
