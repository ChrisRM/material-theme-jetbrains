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

package com.chrisrm.idea.themes;

import com.chrisrm.idea.MTAbstractTheme;
import org.jetbrains.annotations.NotNull;

import javax.swing.plaf.ColorUIResource;
import java.awt.*;

public final class MTLighterTheme extends MTAbstractTheme {
  @NotNull
  @Override
  public String getThemeId() {
    return "mt.lighter";
  }

  @Override
  public ColorUIResource getBackgroundColorString() {
    return new ColorUIResource(0xFAFAFA);
  }

  @Override
  public ColorUIResource getForegroundColorString() {
    return new ColorUIResource(0x546E7A);
  }

  @Override
  public ColorUIResource getTextColorString() {
    return new ColorUIResource(0x94A7B0);
  }

  @Override
  public ColorUIResource getSelectionBackgroundColorString() {
    return new ColorUIResource(new Color(0x4080CBC4, true));
  }

  @Override
  public ColorUIResource getSelectionForegroundColorString() {
    return new ColorUIResource(0x546e7a);
  }

  @Override
  public ColorUIResource getButtonColorString() {
    return new ColorUIResource(0xF3F4F5);
  }

  @Override
  public ColorUIResource getSecondaryBackgroundColorString() {
    return new ColorUIResource(0xeae8e8);
  }

  @Override
  public ColorUIResource getDisabledColorString() {
    return new ColorUIResource(0xD2D4D5);
  }

  @Override
  public ColorUIResource getContrastColorString() {
    return new ColorUIResource(0xF4F4F4);
  }

  @Override
  public ColorUIResource getTableSelectedColorString() {
    return new ColorUIResource(0xD2D4D5);
  }

  @Override
  public ColorUIResource getSecondBorderColorString() {
    return new ColorUIResource(0xd3e1e8);
  }

  @Override
  public ColorUIResource getHighlightColorString() {
    return new ColorUIResource(0xD2D4D5);
  }

  @Override
  public ColorUIResource getTreeSelectionColorString() {
    return new ColorUIResource(new Color(0x50546E7A));
  }

  @Override
  public ColorUIResource getNotificationsColorString() {
    return new ColorUIResource(0xb0bec5);
  }

  @Override
  public ColorUIResource getAccentColorString() {
    return new ColorUIResource(0x80CBC4);
  }

  @Override
  public ColorUIResource getExcludedColorString() {
    return new ColorUIResource(0xeae8e8);
  }
}
