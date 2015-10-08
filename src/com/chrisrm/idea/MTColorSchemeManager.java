package com.chrisrm.idea;

import com.intellij.openapi.editor.colors.EditorColorsScheme;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

public interface MTColorSchemeManager {
    EditorColorsScheme getGlobalColorScheme();
    EditorColorsScheme getScheme(String scheme);

    void setGlobalScheme(@Nullable EditorColorsScheme var1);

    void setUiProperty(String propertyName, Color color);
}
