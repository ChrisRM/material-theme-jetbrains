package com.chrisrm.idea;

import com.intellij.openapi.components.ApplicationComponent;
import org.jetbrains.annotations.NotNull;

public class MTColorScheme implements ApplicationComponent {

    @Override
    public void initComponent() {
        MTThemeUtil.setTheme(MTThemeUtil.getThemeSetting());
    }

    public void disposeComponent() {
        // TODO: insert component disposal logic here
    }

    @NotNull
    public String getComponentName() {
        return "MTColorScheme";
    }
}
