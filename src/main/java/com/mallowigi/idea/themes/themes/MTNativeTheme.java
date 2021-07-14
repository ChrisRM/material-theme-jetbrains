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
import com.mallowigi.idea.MTConfig;
import com.mallowigi.idea.lafs.MTDarculaLaf;
import com.mallowigi.idea.lafs.MTNativeLaf;
import com.mallowigi.idea.themes.MTAccentMode;
import com.mallowigi.idea.themes.lists.ContrastResources;
import com.mallowigi.idea.utils.MTColorUtils;
import com.mallowigi.idea.utils.MTUI;
import com.mallowigi.idea.utils.MTUiUtils;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;

@SuppressWarnings("DesignForExtension")
public class MTNativeTheme extends MTAbstractTheme {

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
  public void applyContrast(final boolean apply) {
    final boolean dark = isDark();
    for (final String resource : ContrastResources.CONTRASTED_RESOURCES) {
      final Color color = UIManager.getLookAndFeelDefaults().getColor(resource);

      if (color != null) {
        UIManager.put(resource, apply ?
                                MTColorUtils.contrastifyBackground(dark, new ColorUIResource(color), false) :
                                color);
      } else {
        UIManager.put(resource, apply ?
                                MTUI.Panel.getContrastBackground() :
                                MTUI.Panel.getBackground());
      }
    }
  }

  @Override
  public boolean isNative() {
    return true;
  }

  @Override
  protected @Nullable String getBackgroundImage() {
    return null;
  }

  @Override
  public void applyAccentMode() {
    final MTConfig mtConfig = MTConfig.getInstance();
    final Color accentColor = ColorUtil.fromHex(mtConfig.getAccentColor());
    final Color darkerAccentColor = ColorUtil.darker(accentColor, 2);
    final Color accentColorTransparent = ColorUtil.withAlpha(accentColor, 0.5);
    final Color secondAccentColor = ColorUtil.fromHex(mtConfig.getSecondAccentColor());
    final boolean accentMode = mtConfig.isAccentMode();

    // Add accent resources
    MTUiUtils.buildAccentResources(MTAccentMode.ACCENT_EXTRA_RESOURCES, accentColor, accentMode);
    MTUiUtils.buildAccentResources(MTAccentMode.DARKER_ACCENT_RESOURCES, darkerAccentColor, accentMode);
    MTUiUtils.buildAccentResources(MTAccentMode.ACCENT_TRANSPARENT_EXTRA_RESOURCES, accentColorTransparent, accentMode);
    // Add new selection color resources
    MTUiUtils.buildAccentResources(MTAccentMode.SELECTION_RESOURCES, MTAccentMode.getSelectionColor(), accentMode);
    MTUiUtils.buildAccentResources(MTAccentMode.SECOND_ACCENT_RESOURCES, secondAccentColor, accentMode);

  }
}
