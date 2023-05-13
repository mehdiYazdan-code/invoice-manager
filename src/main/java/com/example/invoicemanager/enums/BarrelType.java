package com.example.invoicemanager.enums;

public enum BarrelType {
    OPEN_LID_220_LITER("بشکه درب باز 220 لیتری"),
    OPEN_LID_230_LITER("بشکه درب باز 230 لیتری"),
    CLOSED_LID_220_LITER("بشکه درب بسته 220 لیتری"),
    CLOSED_LID_230_LITER("بشکه درب بسته 230 لیتری");

    private final String displayName;

    BarrelType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

