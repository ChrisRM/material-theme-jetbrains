package com.chrisrm.idea.icons;

import java.io.Serializable;

public abstract class Association implements Serializable {

    private String name;
    private String icon;

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract boolean matches(FileInfo file);
}
