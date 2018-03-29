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

import com.intellij.ide.navigationToolbar.NavBarItem;
import com.intellij.ide.navigationToolbar.NavBarPanel;
import com.intellij.ide.navigationToolbar.ui.CommonNavBarUI;
import com.intellij.ide.ui.UISettings;
import com.intellij.util.ui.JBInsets;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.UIUtil;
import gnu.trove.THashMap;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Konstantin Bulenkov
 */
public class MTNavBarUI extends CommonNavBarUI {

  private static final Map<NavBarItem, Map<ImageType, BufferedImage>> CACHE = new THashMap<>();

  private enum ImageType {
    INACTIVE,
    NEXT_ACTIVE,
    ACTIVE,
    INACTIVE_FLOATING,
    NEXT_ACTIVE_FLOATING,
    ACTIVE_FLOATING,
    INACTIVE_NO_TOOLBAR,
    NEXT_ACTIVE_NO_TOOLBAR,
    ACTIVE_NO_TOOLBAR
  }

  @Override
  public void doPaintNavBarPanel(final Graphics2D g, final Rectangle r, final boolean mainToolbarVisible, final boolean undocked) {
  }

  @Override
  public JBInsets getElementPadding() {
    return JBUI.insets(5, 3, 5, 10);
  }

  @Override
  public void doPaintNavBarItem(final Graphics2D g, final NavBarItem item, final NavBarPanel navbar) {
    final boolean floating = navbar.isInFloatingMode();
    final boolean toolbarVisible = UISettings.getInstance().getShowMainToolbar();
    final boolean selected = item.isSelected() && item.isFocused();
    final boolean nextSelected = item.isNextSelected() && navbar.isFocused();

    // Determine the type of breadcrumb to draw
    final ImageType type;
    if (floating) {
      type = selected ? ImageType.ACTIVE_FLOATING : nextSelected ? ImageType.NEXT_ACTIVE_FLOATING : ImageType.INACTIVE_FLOATING;
    } else {
      if (toolbarVisible) {
        type = selected ? ImageType.ACTIVE : nextSelected ? ImageType.NEXT_ACTIVE : ImageType.INACTIVE;
      } else {
        type = selected ? ImageType.ACTIVE_NO_TOOLBAR : nextSelected ? ImageType.NEXT_ACTIVE_NO_TOOLBAR : ImageType.INACTIVE_NO_TOOLBAR;
      }
    }

    final Map<ImageType, BufferedImage> cached = CACHE.computeIfAbsent(item, k -> new HashMap<>());

    // Draw or use cache
    final BufferedImage image = cached.computeIfAbsent(type, k -> drawToBuffer(item, floating, toolbarVisible, selected, navbar));
    UIUtil.drawImage(g, image, 0, 0, null);

    final Icon icon = item.getIcon();
    final int offset = item.isFirstElement() ? getFirstElementLeftOffset() : 0;
    final int iconOffset = getElementPadding().left + offset;
    icon.paintIcon(item, g, iconOffset, (item.getHeight() - icon.getIconHeight()) / 2);
    final int textOffset = icon.getIconWidth() + getElementPadding().width() + offset;
    item.doPaintText(g, textOffset);
  }

  private static BufferedImage drawToBuffer(final NavBarItem item,
                                            final boolean floating,
                                            final boolean toolbarVisible,
                                            final boolean selected,
                                            final NavBarPanel navbar) {
    final int w = item.getWidth();
    final int h = item.getHeight();
    final int offset = (w - getDecorationOffset());
    final int arrowXBegin = (w - (getDecorationOffset() / 2));
    final int arrowYBegin = getDecorationHOffset();
    final int arrowHeight = (h - 2 * getDecorationHOffset());
    final int h2 = h / 2;

    final Color highlightColor = UIManager.getColor("Focus.color");
    final Color arrowColor = UIManager.getColor("MenuBar.foreground");

    // The image we will build
    final BufferedImage result = UIUtil.createImage(w, h, BufferedImage.TYPE_INT_ARGB);
    final Graphics2D g2 = result.createGraphics();
    g2.setStroke(new BasicStroke(1f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND));
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    // Create the inner and outer shapes for the navbar item
    final Path2D.Double shape = new Path2D.Double();
    shape.moveTo(0, 0);
    shape.lineTo(offset, 0);
    shape.lineTo(w, h2);
    shape.lineTo(offset, h);
    shape.lineTo(0, h);
    shape.closePath();

    final Path2D.Double endShape = new Path2D.Double();
    endShape.moveTo(offset, 0);
    endShape.lineTo(w, 0);
    endShape.lineTo(w, h);
    endShape.lineTo(offset, h);
    endShape.lineTo(w, h2);
    endShape.closePath();

    // Colorify the shape with the panel background
    g2.setPaint(UIUtil.getPanelBackground());
    g2.fill(shape);
    g2.fill(endShape);

    // If navigation item is selected, colorify with list background color and draw arrow in halo color
    if (selected) {
      final Path2D.Double focusShape = new Path2D.Double();
      focusShape.moveTo(0, 1);
      focusShape.lineTo(offset, 1);
      focusShape.lineTo(w - 1, h2);
      focusShape.lineTo(offset, h - 1);
      focusShape.lineTo(0, h - 1);

      g2.setColor(highlightColor);
      if (floating && item.isLastElement()) {
        g2.fillRect(0, 0, w, h);
      } else {
        g2.fill(shape);
        g2.draw(focusShape);
      }
    }

    // Now go to the previous item and paint the end part as if it was part of the current item
    if (item.isNextSelected() && navbar.isFocused()) {
      g2.setColor(highlightColor);
      g2.fill(endShape);

      final Path2D.Double endFocusShape = new Path2D.Double();
      endFocusShape.moveTo(w, 1);
      endFocusShape.lineTo(offset, 1);
      endFocusShape.lineTo(w - 1, h2);
      endFocusShape.lineTo(offset, h - 1);
      endFocusShape.lineTo(w, h - 1);

      g2.setColor(highlightColor);
      g2.draw(endFocusShape);
    }

    // Now draw the arrow
    g2.translate(arrowXBegin, arrowYBegin);
    final int off = (getDecorationOffset() / 2) - 1;

    if (!floating || !item.isLastElement()) {
      drawArrow(g2, selected, arrowColor, highlightColor, off, arrowHeight);
    }

    g2.dispose();
    return result;
  }

  private static int getDecorationOffset() {
    return JBUI.scale(14);
  }

  private static int getDecorationHOffset() {
    return JBUI.scale(9);
  }

  private static int getFirstElementLeftOffset() {
    return JBUI.scale(6);
  }

  private static void drawArrow(final Graphics2D g2d,
                                final boolean isFocused,
                                final Color arrowColor,
                                final Color selectedArrowColor,
                                final int arrowWidth,
                                final int arrowHeight) {
    final int xEnd = arrowWidth - 1;

    g2d.setColor(arrowColor);
    g2d.drawLine(2, 0, xEnd, arrowHeight / 2);
    g2d.drawLine(xEnd, arrowHeight / 2, 2, arrowHeight);

    g2d.translate(-1, 0);
    g2d.drawLine(2, 0, xEnd, arrowHeight / 2);
    g2d.drawLine(xEnd, arrowHeight / 2, 2, arrowHeight);
  }
}
