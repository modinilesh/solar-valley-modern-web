package com.solarvalley.webapp.model.enums;

/**
 * Category of project for portfolio
 */
public enum ProjectCategory {
    RESIDENTIAL("Residential"),
    COMMERCIAL("Commercial"),
    INDUSTRIAL("Industrial");

    private final String displayName;

    ProjectCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
