package com.solarvalley.webapp.model.enums;

/**
 * Type of media file (image or video)
 */
public enum MediaType {
    IMAGE("Image"),
    VIDEO("Video");

    private final String displayName;

    MediaType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
