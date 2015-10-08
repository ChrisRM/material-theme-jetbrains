package com.chrisrm.idea;

import com.intellij.ide.plugins.PluginManager;
import com.intellij.ide.util.PropertiesComponent;

public class MTDataLayer {

    private String prefix;

    public MTDataLayer() {

        try {
            this.prefix = PluginManager.getPluginByClassName(this.getClass().getName()).toString();
        } catch (NullPointerException e) {
        }

    }

    public String getValue(String key, String def) {
        return PropertiesComponent.getInstance().getValue(this.prefix + "." + key, def);
    }

    public void setValue(String key, String value) {
        PropertiesComponent.getInstance().setValue(this.prefix + "." + key, value);
    }

}
