package com.chrisrm.idea;

import com.intellij.openapi.components.ApplicationComponent;
import org.jetbrains.annotations.NotNull;

public class MTColorScheme implements ApplicationComponent {

    public MTColorScheme() {
    }

    @Override
    public void initComponent() {
        MTThemeUtil.setTheme(new MTDataLayer().getValue("theme", "default"));
    }

    public void disposeComponent() {
        // TODO: insert component disposal logic here
    }

    @NotNull
    public String getComponentName() {
        return "MTColorScheme";
    }
}
