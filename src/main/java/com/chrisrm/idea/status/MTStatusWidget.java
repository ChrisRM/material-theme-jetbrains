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

package com.chrisrm.idea.status;

import com.chrisrm.idea.MTConfig;
import com.chrisrm.idea.utils.MTUiUtils;
import com.intellij.openapi.wm.CustomStatusBarWidget;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.UIUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.text.AttributedString;

public final class MTStatusWidget extends JComponent implements CustomStatusBarWidget {
  public static final int DEFAULT_FONT_SIZE = JBUI.scale(11);
  private final MTConfig mtConfig;
  private Image myBufferedImage;

  MTStatusWidget() {
    mtConfig = MTConfig.getInstance();

    setOpaque(false);
    setFocusable(false);
    //    setBorder(StatusBarWidget.WidgetBorder.INSTANCE);
    repaint();
    updateUI();
  }


  @Override
  public JComponent getComponent() {
    return this;
  }

  @NotNull
  @Override
  public String ID() {
    return "MTStatusBarWidget";
  }

  @Nullable
  @Override
  public WidgetPresentation getPresentation(@NotNull final PlatformType platformType) {
    return null;
  }

  @Override
  public void install(@NotNull final StatusBar statusBar) {

  }

  @Override
  public void dispose() {

  }

  @Override
  public void updateUI() {
    super.updateUI();
    myBufferedImage = null;
    setFont(MTUiUtils.getWidgetFont());
  }

  @Override
  public void paintComponent(final Graphics g) {
    final String themeName = mtConfig.getSelectedTheme().getEditorColorsScheme();

    if (myBufferedImage == null) {
      final Dimension size = getSize();
      final Dimension arcs = new Dimension(8, 8);

      myBufferedImage = UIUtil.createImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
      final Graphics2D g2 = (Graphics2D) myBufferedImage.getGraphics().create();
      final FontMetrics fontMetrics = g.getFontMetrics();

      g2.setRenderingHints(MTUiUtils.getHints());

      final int nameWidth = fontMetrics.charsWidth(themeName.toCharArray(), 0, themeName.length());
      final int nameHeight = fontMetrics.getAscent();

      final AttributedString as = new AttributedString(themeName);

      as.addAttribute(TextAttribute.FAMILY, getFont().getFamily());
      as.addAttribute(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
      as.addAttribute(TextAttribute.SIZE, DEFAULT_FONT_SIZE);

      // background
      g2.setColor(mtConfig.getSelectedTheme().getContrastColor());
      g2.fillRoundRect(0, 0, size.width, MTUiUtils.HEIGHT, arcs.width, arcs.height);

      // label
      g2.setColor(UIUtil.getLabelForeground());
      g2.setFont(getFont());
      g2.drawString(as.getIterator(), (size.width - nameWidth) / 2, nameHeight + (size.height - nameHeight) / 2 - JBUI.scale(1));
      g2.dispose();

    }

    UIUtil.drawImage(g, myBufferedImage, 0, 0, null);
  }

  @Override
  public Dimension getPreferredSize() {
    final String themeName = mtConfig.getSelectedTheme().getEditorColorsScheme();
    final int width = getFontMetrics(MTUiUtils.getWidgetFont()).charsWidth(themeName.toCharArray(), 0,
        themeName.length()) + 2 * MTUiUtils.PADDING;
    return new Dimension(width, MTUiUtils.HEIGHT);
  }

  @Override
  public Dimension getMinimumSize() {
    return getPreferredSize();
  }

  @Override
  public Dimension getMaximumSize() {
    return getPreferredSize();
  }

  void refresh() {
    this.repaint();
    this.updateUI();
  }
}
