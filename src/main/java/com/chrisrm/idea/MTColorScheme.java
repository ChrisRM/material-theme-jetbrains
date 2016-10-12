package com.chrisrm.idea;

import com.intellij.openapi.components.ApplicationComponent;
import org.jetbrains.annotations.NotNull;

public class MTColorScheme implements ApplicationComponent {

    @Override
    public void initComponent() {
        MTTheme.getCurrentPreference().activate();
    }

    public void disposeComponent() {
        // TODO: insert component disposal logic here
    }

    @NotNull
    public String getComponentName() {
        return "MTColorScheme";
    }
}
