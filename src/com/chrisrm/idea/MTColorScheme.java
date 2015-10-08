package com.chrisrm.idea;

import com.chrisrm.idea.actions.MTSwitchTheme;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MTColorScheme implements ApplicationComponent {

    public MTColorScheme() {
    }

    @Override
    public void initComponent() {
        new MTSwitchTheme().setTheme(new MTDataLayer().getValue("theme", "default"));
    }

    public void disposeComponent() {
        // TODO: insert component disposal logic here
    }

    @NotNull
    public String getComponentName() {
        return "MTColorScheme";
    }
}
