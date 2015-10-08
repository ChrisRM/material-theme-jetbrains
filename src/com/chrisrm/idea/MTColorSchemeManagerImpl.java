package com.chrisrm.idea;

import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class MTColorSchemeManagerImpl implements MTColorSchemeManager {

    @Override
    public EditorColorsScheme getGlobalColorScheme() {
        return EditorColorsManager.getInstance().getGlobalScheme();
    }

    @Override
    public EditorColorsScheme getScheme(String scheme) {
        return EditorColorsManager.getInstance().getScheme(scheme);
    }

    @Override
    public void setGlobalScheme(@Nullable EditorColorsScheme var1) {
        EditorColorsManager.getInstance().setGlobalScheme(var1);
    }

    @Override
    public void setUiProperty(final String propertyName, final Color color) {
        UIManager.put(propertyName, color);
    }
}
