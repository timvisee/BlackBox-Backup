package com.timvisee.blackbox;

import com.timvisee.blackbox.util.WindowUtils;
import com.timvisee.blackbox.volume.Volume;
import com.timvisee.blackbox.volume.VolumeFile;
import com.timvisee.blackbox.volume.VolumeManager;

import java.io.File;
import java.util.Random;

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

        // Prepare the application data directory
        try {
            prepareAppDirectory();
        } catch(Exception e) {
            e.printStackTrace();
            return;
        }

        // Try to set the UI look and feel of the program to the system's default
        WindowUtils.useNativeLookAndFeel();

        // Load all volumes
        Core.volumeManager.load();

        // Initialization succeed, show a status message with the initialization duration
        long duration = System.currentTimeMillis() - start;
        System.out.println("Initialized successfully, took " + String.valueOf(duration) + "ms. Cave Johnson here");

        // Run the test method, remove this after debugging
        // TODO: Remove after debugging!
        test();
    }

    /**
     * Prepare and/or validate the application data directory.
     *
     * @throws Exception Throws on failure.
     */
    public static void prepareAppDirectory() throws Exception {
        long start = System.currentTimeMillis();

        // Show a status message
        System.out.println("Validating application data directory...");

        // Only prepare if the application data directory doesn't exist
        File appData = BlackBox.getDirectory();
        File appDataData = BlackBox.getDataDirectory();
        if(appData.exists() && appDataData.exists()) {
            System.out.println("Application data directory validated, took " + (System.currentTimeMillis() - start) + " ms.");
            return;
        }

        System.out.println("Application data directory doesn't exist, creating it now...");

        // Create the application data directory
        if(!appData.mkdirs())
            throw new Exception("Failed to create application data directory!");

        // Create the application data, data directory
        if(!appDataData.mkdirs())
            throw new Exception("Failed to create application data directory!");

        // Show a status message
        System.out.println("Application data directory created, took " + (System.currentTimeMillis() - start) + " ms.");
    }





    // TODO: Remove after debugging!
    public static void test() {
        System.out.println("\n\nTEST CODE:");
        Volume testVolume = Core.volumeManager.getVolumes().get(0);
        System.out.println("Contents in " + testVolume.getName() + "'s root:");
        listDir(testVolume.getRoot(), 0);
        System.out.println("END RESULTS: Files: " + files + "   /// Folders: " + folders);
    }

    public static int files = 0;
    public static int folders = 0;

    // TODO: Remove after debugging!
    public static void listDir(VolumeFile dir, int level) {
        // Build the prefix
        StringBuilder prefix = new StringBuilder();
        for(int i = 0; i < level; i++)
            prefix.append("  ");

        //System.out.println("Contents of '" + dir.getPath() + "':");
        for(VolumeFile f : dir.list()) {
            boolean isDir = f.isDirectory();
            //System.out.println(prefix.toString() + " - " + f.getName() + (isDir ? ":" : ""));

            if(isDir)
                folders++;
            else
                files++;

            if(isDir) {
                listDir(f, level + 1);

                if((new Random()).nextInt(10000) == 0)
                    System.out.println("Files: " + files + "   /// Folders: " + folders);
            }
        }
    }
}
