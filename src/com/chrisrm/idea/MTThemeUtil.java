package com.chrisrm.idea;

import com.intellij.ide.ui.UISettings;
import com.intellij.openapi.actionSystem.impl.ActionToolbarImpl;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
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

            new MTDataLayer().setValue("theme", theme);
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

}
