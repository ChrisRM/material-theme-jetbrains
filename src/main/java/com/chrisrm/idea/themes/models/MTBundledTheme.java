/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Chris Magnussen and Elior Boukhobza
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

import com.chrisrm.idea.MTAbstractTheme;
import com.chrisrm.idea.themes.models.parsers.MTBundledThemeParser;
import com.chrisrm.idea.utils.MTColorUtils;
import org.jetbrains.annotations.NotNull;

import javax.swing.plaf.ColorUIResource;
import java.io.Serializable;

public abstract class MTBundledTheme extends MTAbstractTheme implements Serializable {

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
   * The accent color hex
   */
  public abstract String getAccentColorHex();

  /**
   * The excluded color hex
   */
  public abstract String getExcludedColorHex();

  /**
   * Get and parse the accent color
   */
  @Override
  public final ColorUIResource getAccentColorString() {
    return MTColorUtils.parseColor(getAccentColorHex());
  }

  /**
   * Get and parse the excluded color
   */
  @Override
  public final ColorUIResource getExcludedColorString() {
    return MTColorUtils.parseColor(getExcludedColorHex());
  }

  /**
   * The theme parser, according to the bridge design pattern every subclass must define the parser
   */
  public abstract MTBundledThemeParser getThemeParser();

  //region Colors
  @Override
  public final ColorUIResource getBackgroundColorString() {
    return getThemeParser().getBackgroundColorString();
  }

  @Override
  public final ColorUIResource getForegroundColorString() {
    return getThemeParser().getForegroundColorString();
  }

  @Override
  public final ColorUIResource getTextColorString() {
    return getThemeParser().getTextColorString();
  }

  @Override
  public final ColorUIResource getSelectionBackgroundColorString() {
    return getThemeParser().getSelectionBackgroundColorString();
  }

  @Override
  public final ColorUIResource getSelectionForegroundColorString() {
    return getThemeParser().getSelectionForegroundColorString();
  }

  @Override
  public final ColorUIResource getButtonColorString() {
    return getThemeParser().getButtonColorString();
  }

  @Override
  public final ColorUIResource getSecondaryBackgroundColorString() {
    return getThemeParser().getSecondaryBackgroundColorString();
  }

  @Override
  public final ColorUIResource getDisabledColorString() {
    return getThemeParser().getDisabledColorString();
  }

  @Override
  public final ColorUIResource getContrastColorString() {
    return getThemeParser().getContrastColorString();
  }

  @Override
  public final ColorUIResource getTableSelectedColorString() {
    return getThemeParser().getTableSelectedColorString();
  }

  @Override
  public final ColorUIResource getSecondBorderColorString() {
    return getThemeParser().getSecondBorderColorString();
  }

  @Override
  public final ColorUIResource getHighlightColorString() {
    return getThemeParser().getHighlightColorString();
  }

  @Override
  public final ColorUIResource getTreeSelectionColorString() {
    return getThemeParser().getTreeSelectionColorString();
  }

  @Override
  public final ColorUIResource getNotificationsColorString() {
    return getThemeParser().getNotificationsColorString();
  }

}
