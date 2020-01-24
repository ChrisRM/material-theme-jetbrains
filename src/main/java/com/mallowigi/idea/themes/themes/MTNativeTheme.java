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

import com.mallowigi.idea.lafs.MTLafInstaller;
import com.mallowigi.idea.utils.MTUI;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.util.Map;

@SuppressWarnings("DesignForExtension")
public class MTNativeTheme extends MTAbstractTheme {

  /**
   * Set to dark
   */
  @SuppressWarnings("DesignForExtension")
  @Override
  public boolean isThemeDark() {
    return true;
  }

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
    return new ColorUIResource(MTUI.Tabs.getUnderlineColor());
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
  public final void setLookAndFeel() {
    final MTLafInstaller mtLafInstaller = new MTLafInstaller(this);
    final UIDefaults defaults = UIManager.getDefaults();
    MTLafInstaller.installDefaults(defaults);
    // Install material defaults
    MTLafInstaller.installMTDefaults(defaults);

    for (final Map.Entry<Object, Object> uiDefault : defaults.entrySet()) {
      UIManager.getLookAndFeelDefaults().put(uiDefault.getKey(), uiDefault.getValue());
    }
  }

  //  @Override
  //  public final void applyContrast(final boolean apply) {
  //    //    for (final String resource : ContrastResources.CONTRASTED_RESOURCES) {
  //    //      final Color contrastedColor = apply ? this.getContrastColor() : this.getBackgroundColor();
  //    //      UIManager.getLookAndFeelDefaults().putIfAbsent(resource, contrastedColor);
  //    //    }
  //  }

  @Override
  protected String getBackgroundImage() {
    return null;
  }
}
