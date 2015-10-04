package com.chrisrm.idea;

import com.intellij.openapi.components.ApplicationComponent;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class MTApplicationComponent implements ApplicationComponent {

    public MTApplicationComponent() {
    }

    public void initComponent() {
        System.err.println(UIManager.getLookAndFeel());
    }

    public void disposeComponent() {
        // TODO: insert component disposal logic here
    }

    @NotNull
    public String getComponentName() {
        return "MTApplicationComponent";
    }
}
