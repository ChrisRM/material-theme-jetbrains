/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Chris Magnussen and Elior Boukhobza
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 *
 */

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
  private Properties properties;

  MTTheme(@NotNull final String id, @NotNull final String editorColorsScheme, final boolean dark) {
    this.id = id;
    this.editorColorsScheme = editorColorsScheme;
    this.dark = dark;
  }

  public int getTreeIndent() {
    final Properties properties = getProperties();
    return Integer.parseInt(properties.getProperty("Tree.rightChildIndent"));
  }

  /**
   * Get background color custom property
   */
  @NotNull
  public Color getBackgroundColor() {
    final Properties properties = getProperties();
    return ColorUtil.fromHex("#" + properties.getProperty("material.tab.backgroundColor"));
  }

  /**
   * Get border color custom property
   */
  @NotNull
  public Color getBorderColor() {
    final Properties properties = getProperties();
    return ColorUtil.fromHex("#" + properties.getProperty("material.tab.borderColor"));
  }

  /**
   * Get border thickness custom property
   */
  public int getBorderThickness() {
    final Properties properties = getProperties();
    return Integer.parseInt(properties.getProperty("material.tab.borderThickness"));
  }

  /**
   * Get contrast color custom property
   */
  @NotNull
  public Color getContrastColor() {
    final Properties properties = getProperties();
    return ColorUtil.fromHex(properties.getProperty("material.contrast"));
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
      final InputStream stream = this.getClass().getResourceAsStream(this.id + ".properties");
      try {
        this.properties.load(stream);
        stream.close();
      } catch (final Exception e) {
        e.printStackTrace();
      }
    }

    return this.properties;
  }

}
