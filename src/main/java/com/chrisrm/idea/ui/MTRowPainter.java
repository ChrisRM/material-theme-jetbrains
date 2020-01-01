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

package com.chrisrm.idea.ui;

import com.chrisrm.idea.utils.MTUI;
import com.intellij.ide.ui.UISettings;
import com.intellij.ui.paint.LinePainter2D;
import com.intellij.ui.paint.PaintUtil;
import com.intellij.ui.scale.JBUIScale;
import com.intellij.ui.tree.ui.Control;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class MTRowPainter implements Control.Painter {

  @Override
  public int getRendererOffset(@NotNull final Control control, final int depth, final boolean leaf) {
    if (depth < 0) {
      return -1; // do not paint row
    }
    if (depth == 0) {
      return 0;
    }
    final int controlWidth = control.getWidth();
    final int left = getLeftIndent(controlWidth / 2);
    final int right = getRightIndent();
    int offset = getLeafIndent();

    if (offset < 0) {
      offset = Math.max(controlWidth + left - controlWidth / 2 + JBUIScale.scale(2), left + right);
    }
    return depth > 1 ? (depth - 1) * (left + right) + offset : offset;
  }

  private static int getLeafIndent() {
    return -1;
  }

  private static int getRightIndent() {
    return Math.max(0, UIManager.getInt("Tree.rightChildIndent"));
  }

  private static int getLeftIndent(final int min) {
    return Math.max(min, UIManager.getInt("Tree.leftChildIndent"));
  }

  @Override
  public int getControlOffset(@NotNull final Control control, final int depth, final boolean leaf) {
    if (depth <= 0 || leaf) {
      return -1; // do not paint control
    }
    final int controlWidth = control.getWidth();
    final int left = getLeftIndent(controlWidth / 2);
    final int offset = left - controlWidth / 2;
    return depth > 1 ? (depth - 1) * (left + getRightIndent()) + offset : offset;
  }

  @Override
  public void paint(@NotNull final Component c,
                    @NotNull final Graphics g,
                    final int x,
                    final int y,
                    final int width,
                    final int height,
                    @NotNull final Control control,
                    final int depth,
                    final boolean leaf,
                    final boolean expanded,
                    final boolean selected) {
    // List indicators
    if (selected) {
      MTUI.List.getListFocusedSelectionPainter().paintBorder(c, g, x, y, width, height);
    }

    if (depth <= 0) {
      return; // do not paint
    }

    // Should we paint indent lines?
    final boolean paintLines = shouldPaintLines();
    if (!paintLines && leaf) {
      return; // nothing to paint
    }

    // Compute the position of the paint lines
    final int controlWidth = control.getWidth();
    final int left = getLeftIndent(controlWidth / 2);
    final int indent = left + getRightIndent();
    int lineX = x + left - controlWidth / 2;
    final int controlX = !leaf && depth > 1 ? (depth - 1) * indent + lineX : lineX;
    int d = depth;

    // paint the lines
    if (paintLines && (depth > 1 || (!leaf && expanded))) {
      g.setColor(MTUI.Tree.getSelectionBackground());
      while (--d > 0) {
        paintLine(g, lineX, y, controlWidth, height);
        lineX += indent;
      }
      if (!leaf && expanded) {
        final int offset = (height - control.getHeight()) / 2;
        if (offset > 0) {
          paintLine(g, lineX, y + height - offset, controlWidth, offset);
        }
      }
    }

    if (leaf) {
      return; // do not paint control for a leaf node
    }
    control.paint(c, g, controlX, y, controlWidth, height, expanded, selected);
  }

  /**
   * Paint indent line
   */
  private static void paintLine(@NotNull final Graphics g, final int x, final int y, final int width, final int height) {
    if (g instanceof Graphics2D) {
      final Graphics2D g2d = (Graphics2D) g;
      final double dx = x + width / 2.0 - PaintUtil.devPixel(g2d);
      LinePainter2D.paint(g2d, dx, y, dx, y + height, LinePainter2D.StrokeType.CENTERED, 1, RenderingHints.VALUE_ANTIALIAS_ON);
    } else {
      final int newX = x + width / 2;
      g.drawLine(newX, y, x, y + height);
    }
  }

  static boolean shouldPaintLines() {
    if (UIManager.getBoolean("Tree.paintLines")) {
      return true;
    }
    final UISettings settings = UISettings.getInstanceOrNull();
    return settings != null && settings.getShowTreeIndentGuides();
  }
}
