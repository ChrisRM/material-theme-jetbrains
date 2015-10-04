package com.chrisrm.idea;

import com.intellij.ide.ui.UISettings;
import com.intellij.openapi.actionSystem.impl.ActionToolbarImpl;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.util.IconLoader;
import com.intellij.ui.JBColor;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class MTColorScheme implements ApplicationComponent {

    private final MTColorSchemeManager colorSchemeManager = new MTColorSchemeManagerImpl();

    public MTColorScheme() {
    }

    @Override
    public void initComponent() {
        try {
            UIManager.setLookAndFeel(new MTLaf());
            JBColor.setDark(true);
            IconLoader.setUseDarkIcons(true);
            System.err.print("Working fine!");
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        final EditorColorsScheme scheme = EditorColorsManager.getInstance().getScheme("Material Theme - Default");
        if (scheme != null) {
            EditorColorsManager.getInstance().setGlobalScheme(scheme);
        }


        UISettings.getInstance().fireUISettingsChanged();
        ActionToolbarImpl.updateAllToolbarsImmediately();

        applyColorSchemeColorsToTree();
    }

    private void applyColorSchemeColorsToTree() {
        final EditorColorsScheme globalScheme = colorSchemeManager.getGlobalColorScheme();


        colorSchemeManager.setUiProperty("Viewport.foreground", globalScheme.getDefaultForeground());
        colorSchemeManager.setUiProperty("Viewport.background", globalScheme.getDefaultBackground());

        colorSchemeManager.setUiProperty("Tree.textForeground", globalScheme.getDefaultForeground());
        colorSchemeManager.setUiProperty("Tree.textBackground", globalScheme.getDefaultBackground());

        colorSchemeManager.setUiProperty("Tree.foreground", globalScheme.getDefaultForeground());
        colorSchemeManager.setUiProperty("Tree.background", globalScheme.getDefaultBackground());
    }

    public void disposeComponent() {
        // TODO: insert component disposal logic here
    }

    @NotNull
    public String getComponentName() {
        return "MTColorScheme";
    }
}
