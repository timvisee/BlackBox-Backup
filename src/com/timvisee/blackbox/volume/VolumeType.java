package com.timvisee.blackbox.volume;

public enum VolumeType {

    FILESYSTEM_VOLUME(1, "Filesystem Volume"),
    FTP_VOLUME(2, "FTP Volume");

    /** Volume type ID. */
    private final int typeId;
    /** Volume name. */
    private final String name;

    /**
     * Constructor
     * @param typeId Volume type ID
     */
    VolumeType(int typeId, String name) {
        this.typeId = typeId;
        this.name = name;
    }

    /**
     * Get the type ID
     * @return Type ID
     */
    public int getTypeId() {
        return this.typeId;
    }

    /**
     * Get the name of a Volume type
     * @return Volume type name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the volume type based on a type ID. The 'unknown' type will be returned if no match was found.
     * Note: Heavy method!
     * @param typeId Type ID to find a match for
     * @return Volume type, or unknown volume type if no match was found
     */
    public static VolumeType fromTypeId(int typeId) {
        // Loop through each volume type and try to find a match
        for(VolumeType t : values())
            if(t.getTypeId() == typeId)
                return t;

        // Couldn't find proper volume type, return null
        return null;
    }

    /**
     * Get the proper name of a volume type
     * @return Proper volume type name
     */
    @Override
    public String toString() {
        return this.getName();
    }
}
