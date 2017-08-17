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

import com.chrisrm.idea.themes.LafTheme;
import com.intellij.openapi.util.IconLoader;
import com.intellij.ui.JBColor;
import com.intellij.util.ObjectUtils;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;

public class MTTheme implements LafTheme {
  private final String id;
  private final String editorColorsScheme;
  private final boolean dark;

  protected MTTheme(@NotNull final String id, @NotNull final String editorColorsScheme, final boolean dark) {
    this.id = id;
    this.editorColorsScheme = editorColorsScheme;
    this.dark = dark;
  }

  /**
   * Get disabled color
   *
   * @return
   */
  @Override
  public String getDisabled() {
    return null;
  }

  @Override
  public final void activate() {
    try {
      UIManager.setLookAndFeel(new MTLaf(this));
      JBColor.setDark(this.isDark());
      IconLoader.setUseDarkIcons(this.isDark());

      //      applyContrast(false);
      //      applyCompactSidebar(false);
      //      applyCustomTreeIndent();
      //      applyAccents(false);
      //      setBoldTabs();
    } catch (final UnsupportedLookAndFeelException e) {
      e.printStackTrace();
    }
  }

  /**
   * Get tree indent
   *
   * @return
   */
  public int getTreeIndent() {
    return ObjectUtils.notNull(UIManager.getInt("Tree.rightChildIndent"), 6);
  }

  /**
   * Get background color custom property
   */
  @NotNull
  public Color getBackgroundColor() {
    return ObjectUtils.notNull(UIManager.getColor("material.tab.backgroundColor"), new ColorUIResource(0x263238));
  }

  /**
   * Get border color custom property
   */
  @NotNull
  public Color getBorderColor() {
    return ObjectUtils.notNull(UIManager.getColor("material.tab.borderColor"), new ColorUIResource(0x80cbc4));
  }

  /**
   * Get border thickness custom property
   */
  public int getBorderThickness() {
    return ObjectUtils.notNull(UIManager.getInt("material.tab.borderThickness"), 2);
  }

  /**
   * Get contrast color custom property
   */
  @NotNull
  public Color getContrastColor() {
    return ObjectUtils.notNull(UIManager.getColor("material.contrast"), new ColorUIResource(0x1E272C));
  }

  @NotNull
  public final String getEditorColorsScheme() {
    return editorColorsScheme;
  }

  public final boolean isDark() {
    return dark;
  }

  @NotNull
  public final String getId() {
    return id;
  }

}
