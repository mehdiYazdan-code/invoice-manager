package com.example.invoicemanager.enums;

public enum ContractType {
    MAIN("Main Contract"),
    ADDENDUM("Addendum");

    private final String displayName;

    ContractType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

