package com.chrisrm.idea;

import com.intellij.ide.plugins.PluginManager;
import com.intellij.ide.ui.UISettings;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.actionSystem.impl.ActionToolbarImpl;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.extensions.PluginId;
import com.intellij.openapi.util.IconLoader;
import com.intellij.ui.JBColor;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public final class MTThemeUtil {
    private MTThemeUtil() {
        // prevent outside instantiation
    }

    public static void setTheme(String theme) {
        try {
            UIManager.setLookAndFeel(new MTLaf(theme.toLowerCase()));
            JBColor.setDark(useDarkTheme(theme));
            IconLoader.setUseDarkIcons(useDarkTheme(theme));

            setThemeSetting(theme);
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        List<String> Schemes = Arrays.asList("Material Theme - Darker", "Material Theme - Default", "Material Theme - Lighter");
        String currentScheme = EditorColorsManager.getInstance().getGlobalScheme().getName();

        String makeActiveScheme = (!Schemes.contains(currentScheme)) ? currentScheme : "Material Theme - " + theme;

        final EditorColorsScheme scheme = EditorColorsManager.getInstance().getScheme(makeActiveScheme);
        if (scheme != null) {
            EditorColorsManager.getInstance().setGlobalScheme(scheme);
        }

        UISettings.getInstance().fireUISettingsChanged();
        ActionToolbarImpl.updateAllToolbarsImmediately();
    }

    /**
     * TODO Make more dynamic
     *
     * @param theme
     * @return bool
     */
    private static boolean useDarkTheme(String theme) {
        return !theme.toLowerCase().equals("lighter");
    }

    public static String getThemeSetting() {
        return PropertiesComponent.getInstance().getValue(getSettingsPrefix() + ".theme", "Default");
    }

    private static void setThemeSetting(String theme) {
        PropertiesComponent.getInstance().setValue(getSettingsPrefix() + ".theme", theme);
    }

    /**
     * @deprecated if more settings are needed for this plugin, you should create a {@link
     * com.intellij.openapi.components.PersistentStateComponent} and store all the settings in a separate file without
     * the prefix on the property name.
     */
    @Deprecated
    private static String getSettingsPrefix() {
        PluginId pluginId = PluginManager.getPluginByClassName(MTThemeUtil.class.getName());
        return pluginId == null ? "com.chrisrm.idea.MaterialThemeUI" : pluginId.getIdString();
    }
}
