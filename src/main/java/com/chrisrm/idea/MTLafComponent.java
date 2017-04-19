package com.chrisrm.idea;

import com.chrisrm.idea.ui.MTButtonUI;
import com.intellij.ide.ui.LafManager;
import com.intellij.openapi.components.ApplicationComponent;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class MTLafComponent implements ApplicationComponent {

    public MTLafComponent(LafManager lafManager) {
        lafManager.addLafManagerListener(source -> installTheme());
    }

    @Override
    public void initComponent() {
        installTheme();
    }

    @Override
    public void disposeComponent() {

    }

    @NotNull
    @Override
    public String getComponentName() {
        return this.getClass().getName();
    }

    private void installTheme() {
        UIManager.put("ButtonUI", MTButtonUI.class.getName());
        UIManager.getDefaults().put(MTButtonUI.class.getName(), MTButtonUI.class);
    }
}
