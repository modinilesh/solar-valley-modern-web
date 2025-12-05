package com.solarvalley.webapp.model.enums;

/**
 * Source from where the lead originated
 */
public enum LeadSource {
    CONTACT_FORM("Contact Form"),
    QUOTE_REQUEST("Quote Request"),
    EXIT_POPUP("Exit Popup"),
    WHATSAPP("WhatsApp"),
    CALL("Phone Call");

    private final String displayName;

    LeadSource(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
