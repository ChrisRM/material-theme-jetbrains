package com.chrisrm.idea;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public enum MTTheme {
    DARKER("mt.darker", "Darker"),
    DEFAULT("mt.default", "Default"),
    LIGHTER("mt.lighter", "Lighter");

    private String id;
    private String displayName;

    MTTheme(@NotNull String id, @NotNull String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    @NotNull
    public String getId() {
        return id;
    }

    @NotNull
    public String getDisplayName() {
        return displayName;
    }

    @Nullable
    public static MTTheme valueOfIgnoreCase(@Nullable String name) {
        for (MTTheme theme : MTTheme.values()) {
            if (theme.name().equalsIgnoreCase(name)) {
                return theme;
            }
        }
        return null;
    }
}
