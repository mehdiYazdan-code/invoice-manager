package com.example.invoicemanager.enums;

public enum InvoiceStatus {
    PENDING("Pending"),
    APPROVED("Approved"),
    CANCELED("Canceled");

    private final String displayName;

    InvoiceStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

