/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015-2021 Elior "Mallowigi" Boukhobza
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

package com.mallowigi.idea.lafs;

import com.intellij.ide.ui.laf.IdeaBlueMetalTheme;
import com.intellij.ide.ui.laf.UIThemeBasedLookAndFeelInfo;
import com.intellij.ide.ui.laf.darcula.DarculaLaf;
import com.intellij.util.xmlb.annotations.Transient;
import com.mallowigi.idea.themes.models.MTThemeable;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.plaf.metal.DefaultMetalTheme;

/**
 * Look and Feel class for Dark Material Themes
 *
 * @author helio
 * Created on 2018-10-29
 */
@SuppressWarnings("SerializableHasSerializationMethods")
public final class MTNativeLaf extends DarculaLaf {

  @NotNull
  private final MTThemeable theme;
  @Transient
  private final UIManager.LookAndFeelInfo currentLookAndFeel;

  /**
   * Represents a Material Dark Look And Feel
   *
   * @param theme           of type MTThemeable
   * @param lookAndFeelInfo current look and feel
   */
  public MTNativeLaf(@NotNull final MTThemeable theme, final UIManager.LookAndFeelInfo lookAndFeelInfo) {
    this.theme = theme;
    currentLookAndFeel = lookAndFeelInfo;
  }

  /**
   * Installs and returns the defaults for dark lafs
   *
   * @return the defaults (type UIDefaults) of this MTDarkLaf object.
   */
  @Override
  public UIDefaults getDefaults() {
    final UIDefaults defaults = super.getDefaults();

    MTLafInstaller.installDefaults(defaults);
    // Install material defaults
    MTLafInstaller.installMTDefaults(defaults);

    return defaults;
  }

  @Override
  protected void loadDefaults(final UIDefaults defaults) {
    MTLafInstaller.loadDefaults(defaults);
    ((UIThemeBasedLookAndFeelInfo) currentLookAndFeel).installTheme(defaults, true);
  }

  @NotNull
  @Override
  public String getName() {
    if (theme.isDark()) {
      return super.getName();
    } else {
      return "IntelliJ";
    }
  }

  @NotNull
  @Override
  protected String getPrefix() {
    if (theme.isDark()) {
      return super.getPrefix();
    } else {
      return "intellijlaf";
    }
  }

  @Override
  protected DefaultMetalTheme createMetalTheme() {
    if (theme.isDark()) {
      return super.createMetalTheme();
    } else {
      return new IdeaBlueMetalTheme();
    }
  }
}
