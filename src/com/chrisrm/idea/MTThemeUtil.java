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
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public final class MTThemeUtil {
    private MTThemeUtil() {
        // prevent outside instantiation
    }

    public static void setTheme(@NotNull MTTheme theme) {
        try {
            UIManager.setLookAndFeel(new MTLaf(theme));
            JBColor.setDark(useDarkTheme(theme));
            IconLoader.setUseDarkIcons(useDarkTheme(theme));

            setThemeSetting(theme);
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        List<String> Schemes = Arrays.asList("Material Theme - Darker", "Material Theme - Default", "Material Theme - Lighter");
        String currentScheme = EditorColorsManager.getInstance().getGlobalScheme().getName();

        String makeActiveScheme = !Schemes.contains(currentScheme) ?
                currentScheme : "Material Theme - " + theme.getDisplayName();

        final EditorColorsScheme scheme = EditorColorsManager.getInstance().getScheme(makeActiveScheme);
        if (scheme != null) {
            EditorColorsManager.getInstance().setGlobalScheme(scheme);
        }

        UISettings.getInstance().fireUISettingsChanged();
        ActionToolbarImpl.updateAllToolbarsImmediately();
    }

    private static boolean useDarkTheme(MTTheme theme) {
        switch (theme) {
            case DARKER:
            case DEFAULT:
                return true;
            default:
                return false;
        }
    }

    @NotNull
    public static MTTheme getThemeSetting() {
        String name = PropertiesComponent.getInstance().getValue(getSettingsPrefix() + ".theme");
        MTTheme theme = MTTheme.valueOfIgnoreCase(name);
        return theme == null ? MTTheme.DEFAULT : theme;
    }

    private static void setThemeSetting(@NotNull MTTheme theme) {
        PropertiesComponent.getInstance().setValue(getSettingsPrefix() + ".theme", theme.name());
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
