package com.chrisrm.idea.plugins;

import com.chrisrm.idea.ui.MTButtonUI;
import com.chrisrm.idea.utils.IconReplacer;
import com.intellij.ide.ui.LafManager;
import com.intellij.ide.ui.LafManagerListener;
import com.intellij.ide.ui.laf.LafManagerImpl;
import com.intellij.openapi.components.ApplicationComponent;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class MTPluginPHP implements ApplicationComponent {

    public MTPluginPHP (LafManagerImpl lafManager) {
        lafManager.addLafManagerListener(lafManager1 -> MTPluginPHP.this.updateManager());
    }

    private void updateManager() {
        UIManager.put("ButtonUI", MTButtonUI.class.getName());
        UIManager.getDefaults().put(MTButtonUI.class.getName(), MTButtonUI.class);
    }

    public void initComponent() {
        try {
            Class<?> iconsClass = Class.forName("com.jetbrains.php.PhpIcons", false, getClass().getClassLoader());
            IconReplacer.replaceIcons(iconsClass, "/icons/plugins/php/");
        } catch (ClassNotFoundException e) {
            // Suppress
        }

        this.updateManager();
    }

    public void disposeComponent() {
        // TODO: insert component disposal logic here
    }

    @NotNull
    public String getComponentName() {
        return "MTPluginPHP";
    }
}
