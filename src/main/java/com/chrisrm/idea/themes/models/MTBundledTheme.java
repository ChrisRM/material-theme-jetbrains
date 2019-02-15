/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 Chris Magnussen and Elior Boukhobza
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

package com.chrisrm.idea.themes.models;

import com.chrisrm.idea.themes.models.parsers.MTBundledThemeParser;
import com.chrisrm.idea.themes.themes.MTAbstractTheme;
import com.intellij.util.xmlb.annotations.Property;
import com.intellij.util.xmlb.annotations.Tag;
import org.jetbrains.annotations.NotNull;

import javax.swing.plaf.ColorUIResource;
import java.util.List;

public abstract class MTBundledTheme extends MTAbstractTheme {
  @Tag
  protected List<? extends MTThemeColor> colors;
  @Property
  protected String themeId;

  /**
   * The theme ID
   */
  @NotNull
  @Override
  public abstract String getThemeId();

  /**
   * Add a big number for the order so it appears at the end
   */
  @Override
  public abstract int getOrder();

  /**
   * The theme parser, according to the bridge design pattern every subclass must define the parser
   */
  public abstract MTBundledThemeParser getThemeParser();

  //region Getters

  /**
   * Get and parse the accent color
   */
  @Override
  public final ColorUIResource getAccentColorResource() {
    return getThemeParser().getAccentColorString();
  }

  /**
   * Get and parse the excluded color
   */
  @Override
  public final ColorUIResource getExcludedColorResource() {
    return getThemeParser().getExcludedColorString();
  }

  @Override
  public final ColorUIResource getBackgroundColorResource() {
    return getThemeParser().getBackgroundColorString();
  }

  @Override
  public final ColorUIResource getForegroundColorResource() {
    return getThemeParser().getForegroundColorString();
  }

  @Override
  public final ColorUIResource getTextColorResource() {
    return getThemeParser().getTextColorString();
  }

  @Override
  public final ColorUIResource getSelectionBackgroundColorResource() {
    return getThemeParser().getSelectionBackgroundColorString();
  }

  @Override
  public final ColorUIResource getSelectionForegroundColorResource() {
    return getThemeParser().getSelectionForegroundColorString();
  }

  @Override
  public final ColorUIResource getButtonColorResource() {
    return getThemeParser().getButtonColorString();
  }

  @Override
  public final ColorUIResource getSecondaryBackgroundColorResource() {
    return getThemeParser().getSecondaryBackgroundColorString();
  }

  @Override
  public final ColorUIResource getDisabledColorResource() {
    return getThemeParser().getDisabledColorString();
  }

  @Override
  public final ColorUIResource getContrastColorResource() {
    return getThemeParser().getContrastColorString();
  }

  @Override
  public final ColorUIResource getTableSelectedColorResource() {
    return getThemeParser().getTableSelectedColorString();
  }

  @Override
  public final ColorUIResource getSecondBorderColorResource() {
    return getThemeParser().getSecondBorderColorString();
  }

  @Override
  public final ColorUIResource getHighlightColorResource() {
    return getThemeParser().getHighlightColorString();
  }

  @Override
  public final ColorUIResource getTreeSelectionColorResource() {
    return getThemeParser().getTreeSelectionColorString();
  }

  @Override
  public final ColorUIResource getNotificationsColorResource() {
    return getThemeParser().getNotificationsColorString();
  }

  //endregion
}
