package com.solarvalley.webapp.model.enums;

/**
 * Status of a lead in the sales pipeline
 */
public enum LeadStatus {
    NEW("New"),
    CONTACTED("Contacted"),
    IN_PROGRESS("In Progress"),
    CONVERTED("Converted"),
    CLOSED("Closed");

    private final String displayName;

    LeadStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
