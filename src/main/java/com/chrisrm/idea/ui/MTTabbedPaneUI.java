/*
 *  The MIT License (MIT)
 *
 *  Copyright (c) 2018 Chris Magnussen and Elior Boukhobza
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 *
 */

package com.chrisrm.idea.ui;

import com.chrisrm.idea.MTConfig;
import com.intellij.ide.ui.laf.darcula.ui.DarculaTabbedPaneUI;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.UIUtilities;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.text.View;
import java.awt.*;

import static com.intellij.util.ui.JBUI.CurrentTheme.TabbedPane.DISABLED_SELECTED_COLOR;

public final class MTTabbedPaneUI extends DarculaTabbedPaneUI {

  private final MTConfig config = MTConfig.getInstance();

  @SuppressWarnings({"MethodOverridesStaticMethodOfSuperclass",
      "unused"})
  public static ComponentUI createUI(final JComponent c) {
    return new MTTabbedPaneUI();
  }

  @Override
  protected void installDefaults() {
    super.installDefaults();
  }

  @SuppressWarnings("Duplicates")
  @Override
  protected void paintTabBackground(final Graphics g,
                                    final int tabPlacement,
                                    final int tabIndex,
                                    final int x,
                                    final int y,
                                    final int w,
                                    final int h,
                                    final boolean isSelected) {
  }

  @Override
  protected void paintText(final Graphics g,
                           final int tabPlacement,
                           final Font font,
                           final FontMetrics metrics,
                           final int tabIndex,
                           final String title,
                           final Rectangle textRect,
                           final boolean isSelected) {

    final View v = getTextViewForTab(tabIndex);
    final int mnemIndex = tabPane.getDisplayedMnemonicIndexAt(tabIndex);
    final String textToPrint = config.isUpperCaseTabs() ? title.toUpperCase() : title;
    final int textWidth = metrics.stringWidth(textToPrint);
    final int x = (int) ((textRect.getWidth() - textWidth) / 2);
    final int y = textRect.y + metrics.getAscent();

    g.setFont(font.deriveFont(Font.BOLD));
    g.setColor(getTabForeground(tabIndex, v));

    UIUtilities.drawStringUnderlineCharAt(tabPane, g, textToPrint, mnemIndex, x, y);
  }

  private Color getTabForeground(final int tabIndex, final View v) {
    // tab disabled
    final boolean isEnabled = v != null || tabPane.isEnabled() && tabPane.isEnabledAt(tabIndex);
    return isEnabled ? UIManager.getColor("TabbedPane.foreground") : UIManager.getColor("TabbedPane.disabledForeground");
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

  /**
   * Get the selected tab color according to the settings
   */
  private Color getIndicatorColor() {
    final Color accentColor = UIManager.getColor("TabbedPane.focusColor");
    final Color customColor = config.getHighlightColor();

    if (!tabPane.isEnabled()) {
      return DISABLED_SELECTED_COLOR;
    }
    return config.isHighlightColorEnabled() ? customColor : accentColor;
  }
}
