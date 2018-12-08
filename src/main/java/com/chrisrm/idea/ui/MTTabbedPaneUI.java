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

package com.chrisrm.idea.ui;

import com.chrisrm.idea.MTConfig;
import com.chrisrm.idea.utils.MTUI;
import com.intellij.ide.ui.laf.darcula.ui.DarculaTabbedPaneUI;
import com.intellij.util.ui.JBUI;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import java.awt.*;

public final class MTTabbedPaneUI extends DarculaTabbedPaneUI {

  private final MTConfig config = MTConfig.getInstance();

  @SuppressWarnings({"MethodOverridesStaticMethodOfSuperclass",
      "unused"})
  public static ComponentUI createUI(final JComponent component) {
    return new MTTabbedPaneUI();
  }

  private static Color getTabForeground(final boolean isSelected) {
    return isSelected ? MTUI.TabbedPane.getSelectedForeground() : MTUI.TabbedPane.getForeground();
  }

  @SuppressWarnings("SwitchStatement")
  @Override
  protected void paintTabBorder(final Graphics g,
                                final int tabPlacement,
                                final int tabIndex,
                                final int x,
                                final int y,
                                final int w,
                                final int h,
                                final boolean isSelected) {
    final int highlightThickness = JBUI.scale(config.getHighlightThickness());

    if (isSelected) {
      g.setColor(getIndicatorColor());

      final int offset;
      switch (tabPlacement) {
        case LEFT:
          offset = highlightThickness;
          g.fillRect(x + w - offset, y, highlightThickness, h);
          break;
        case RIGHT:
          g.fillRect(x, y, highlightThickness, h);
          break;
        case BOTTOM:
          g.fillRect(x, y, w, highlightThickness);
          break;
        case TOP:
        default:
          offset = highlightThickness;
          g.fillRect(x, y + h - offset, w, highlightThickness);
          break;
      }
    }
  }

  @SuppressWarnings("ProhibitedExceptionCaught")
  @Override
  protected void layoutLabel(final int tabPlacement,
                             final FontMetrics metrics,
                             final int tabIndex,
                             final String title,
                             final Icon icon,
                             final Rectangle tabRect,
                             final Rectangle iconRect,
                             final Rectangle textRect,
                             final boolean isSelected) {
    super.layoutLabel(tabPlacement, metrics, tabIndex, title, icon, tabRect, iconRect, textRect, isSelected);

    try {
      final JLabel tabLabel = (JLabel) tabPane.getTabComponentAt(tabIndex);
      if (tabLabel == null) {
        return;
      }
      // Set selected tab foreground
      tabLabel.setForeground(getTabForeground(isSelected));
      // Set tabs uppercase
      setTabTitle(tabLabel, title);
    } catch (final IndexOutOfBoundsException ignored) {
    }
  }

  /**
   * Set the tab title case according to settings (uppercase tabs)
   */
  private void setTabTitle(final JLabel tabLabel, final String title) {
    final boolean upperCaseTabs = config.isUpperCaseTabs();
    if (upperCaseTabs) {
      final String newTitle = title.toUpperCase();
      tabLabel.setFont(tabLabel.getFont().deriveFont(Font.BOLD));
      tabLabel.setText(newTitle);
    }
  }

  /**
   * Get the selected tab color according to the settings
   */
  private Color getIndicatorColor() {
    final Color accentColor = MTUI.TabbedPane.getHighlightColor();
    final Color customColor = config.getHighlightColor();

    if (!tabPane.isEnabled()) {
      return JBUI.CurrentTheme.TabbedPane.DISABLED_SELECTED_COLOR;
    }
    return config.isHighlightColorEnabled() ? customColor : accentColor;
  }

  @Override
  protected int calculateTabHeight(final int tabPlacement, final int tabIndex, final int fontHeight) {
    return JBUI.scale(config.getTabsHeight() + 6);
  }
}
