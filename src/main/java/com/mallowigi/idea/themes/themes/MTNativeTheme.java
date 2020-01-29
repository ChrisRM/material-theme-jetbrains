/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2020 Chris Magnussen and Elior Boukhobza
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

package com.mallowigi.idea.themes.themes;

import com.intellij.ide.ui.LafManager;
import com.intellij.ide.ui.laf.UIThemeBasedLookAndFeelInfo;
import com.intellij.ide.ui.laf.darcula.DarculaLookAndFeelInfo;
import com.intellij.ui.ColorUtil;
import com.mallowigi.idea.lafs.MTDarculaLaf;
import com.mallowigi.idea.lafs.MTNativeLaf;
import com.mallowigi.idea.utils.MTUI;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

@SuppressWarnings("DesignForExtension")
public class MTNativeTheme extends MTAbstractTheme {

  //  /**
  //   * Set to dark
  //   */
  //  @SuppressWarnings("DesignForExtension")
  //  @Override
  //  public boolean isThemeDark() {
  //    return true;
  //  }

  @Override
  public final ColorUIResource getBackgroundColorResource() {
    return new ColorUIResource(MTUI.Panel.getBackground());
  }

  @Override
  public final ColorUIResource getForegroundColorResource() {
    return new ColorUIResource(MTUI.Panel.getForeground());
  }

  @Override
  public final ColorUIResource getTextColorResource() {
    return new ColorUIResource(MTUI.Panel.getPrimaryForeground());
  }

  @Override
  public final ColorUIResource getSelectionBackgroundColorResource() {
    return new ColorUIResource(MTUI.Panel.getSelectionBackground());
  }

  @Override
  public final ColorUIResource getSelectionForegroundColorResource() {
    return new ColorUIResource(MTUI.Panel.getSelectionForeground());
  }

  @Override
  public final ColorUIResource getButtonColorResource() {
    return new ColorUIResource(MTUI.Button.getBackgroundColor());
  }

  @Override
  public final ColorUIResource getSecondaryBackgroundColorResource() {
    return new ColorUIResource(MTUI.Panel.getSecondaryBackground());
  }

  @Override
  public final ColorUIResource getDisabledColorResource() {
    return new ColorUIResource(MTUI.Button.getDisabledColor());
  }

  @Override
  public final ColorUIResource getContrastColorResource() {
    return new ColorUIResource(MTUI.Panel.getContrastBackground());
  }

  @Override
  public final ColorUIResource getTableSelectedColorResource() {
    return new ColorUIResource(MTUI.Table.getHighlightOuterColor());
  }

  @Override
  public final ColorUIResource getSecondBorderColorResource() {
    return new ColorUIResource(MTUI.Separator.getSeparatorColor());
  }

  @Override
  public final ColorUIResource getHighlightColorResource() {
    return new ColorUIResource(MTUI.Panel.getHighlightBackground());
  }

  @Override
  public final ColorUIResource getTreeSelectionColorResource() {
    return new ColorUIResource(MTUI.Tree.getSelectionBackground());
  }

  @Override
  public final ColorUIResource getNotificationsColorResource() {
    return new ColorUIResource(MTUI.Notification.getBackgroundColor());
  }

  @Override
  public final ColorUIResource getAccentColorResource() {
    return new ColorUIResource(ColorUtil.brighter(MTUI.Button.getPrimaryBackgroundColor(), 2));
  }

  @Override
  public final ColorUIResource getExcludedColorResource() {
    return new ColorUIResource(MTUI.Panel.getExcludedBackground());
  }

  @Override
  public final String getThemeId() {
    return "external";
  }

  @Override
  public final void setLookAndFeel() throws UnsupportedLookAndFeelException {
    final UIManager.LookAndFeelInfo currentLookAndFeel = LafManager.getInstance().getCurrentLookAndFeel();
    if (currentLookAndFeel == null) {
      super.setLookAndFeel();
      return;
    }

    if (currentLookAndFeel instanceof UIThemeBasedLookAndFeelInfo) {
      UIManager.setLookAndFeel(new MTNativeLaf(this, currentLookAndFeel));
    } else if (DarculaLookAndFeelInfo.CLASS_NAME.equals(currentLookAndFeel.getClassName())) {
      UIManager.setLookAndFeel(new MTDarculaLaf(this));
    }

  }

  @Override
  protected void buildAllResources() {

  }

  @Override
  protected String getBackgroundImage() {
    return null;
  }
}
