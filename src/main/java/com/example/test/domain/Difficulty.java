package com.example.test.domain;

public enum Difficulty {
    HIGH("greu"),
    MEDIUM("mediu"),
    LOW("mica");

    private final String displayName;

    Difficulty(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
