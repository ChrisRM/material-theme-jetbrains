package com.chrisrm.idea;

import com.intellij.ui.ColorUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.io.InputStream;
import java.util.Properties;

public enum MTTheme {
  DARKER("mt.darker", "Material Theme - Darker", true),
  DEFAULT("mt.default", "Material Theme - Default", true),
  PALENIGHT("mt.palenight", "Material Theme - Palenight", true),
  LIGHTER("mt.lighter", "Material Theme - Lighter", false);

  private final String id;
  private final String editorColorsScheme;
  private final boolean dark;
  @Nullable
  public Properties properties;

  MTTheme(@NotNull String id, @NotNull String editorColorsScheme, boolean dark) {
    this.id = id;
    this.editorColorsScheme = editorColorsScheme;
    this.dark = dark;
  }

  public int getTreeIndent() {
    Properties properties = getProperties();
    return Integer.parseInt(properties.getProperty("Tree.rightChildIndent"));
  }

  /**
   * Get background color custom property
   */
  @NotNull
  public Color getBackgroundColor() {
    Properties properties = getProperties();
    return ColorUtil.fromHex("#" + properties.getProperty("material.tab.backgroundColor"));
  }

  /**
   * Get border color custom property
   */
  @NotNull
  public Color getBorderColor() {
    Properties properties = getProperties();
    return ColorUtil.fromHex("#" + properties.getProperty("material.tab.borderColor"));
  }

  /**
   * Get border thickness custom property
   */
  public int getBorderThickness() {
    Properties properties = getProperties();
    return Integer.parseInt(properties.getProperty("material.tab.borderThickness"));
  }

  /**
   * Get contrast color custom property
   */
  @NotNull
  public Color getContrastColor() {
    Properties properties = getProperties();
    return ColorUtil.fromHex(properties.getProperty("material.contrast"));
  }

  @NotNull
  public Color getDisabledColor() {
    Properties properties = getProperties();
    return ColorUtil.fromHex(properties.getProperty("material.disabled"));
  }

  @NotNull
  public String getEditorColorsScheme() {
    return editorColorsScheme;
  }

  public boolean isDark() {
    return dark;
  }

  @NotNull
  public String getId() {
    return id;
  }

  /**
   * Retrieve current theme properties
   */
  private Properties getProperties() {
    if (this.properties == null) {
      this.properties = new Properties();
      InputStream stream = this.getClass().getResourceAsStream(this.id + ".properties");
      try {
        this.properties.load(stream);
        stream.close();
      }
      catch (Exception e) {
        e.printStackTrace();
      }
    }

    return this.properties;
  }

}
