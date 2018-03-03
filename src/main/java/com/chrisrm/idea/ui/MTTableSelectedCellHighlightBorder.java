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
import com.intellij.ide.ui.laf.darcula.DarculaTableSelectedCellHighlightBorder;
import com.intellij.util.ObjectUtils;
import com.intellij.util.ui.JBUI;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.UIResource;
import java.awt.*;

/**
 * @author Konstantin Bulenkov
 */
public final class MTTableSelectedCellHighlightBorder extends DarculaTableSelectedCellHighlightBorder implements UIResource {
  public MTTableSelectedCellHighlightBorder() {
    outsideBorder = new LineBorder(getHighlightOuterColor(), 2);
    if (MTConfig.getInstance().isCompactTables()) {
      insideBorder = JBUI.Borders.empty(0, 3);
    } else {
      insideBorder = JBUI.Borders.empty(10, 2);
    }
  }

  private Color getShadowOuterColor() {
    return ObjectUtils.notNull(UIManager.getColor("Table.shadowOuter"), new Color(25, 32, 36));
  }

  private Color getShadowInnerColor() {
    return ObjectUtils.notNull(UIManager.getColor("Table.shadowInner"), new Color(32, 42, 46));
  }

  private Color getHighlightOuterColor() {
    return ObjectUtils.notNull(UIManager.getColor("Table.highlightOuter"), new Color(72, 92, 102));
  }

  protected Color getHighlightInnerColor() {
    return ObjectUtils.notNull(UIManager.getColor("Table.highlightInner"), new Color(62, 81, 89));
  }

  @Override
  public void paintBorder(final Component c, final Graphics g, final int x, final int y, final int width, final int height) {
    outsideBorder = new BevelBorder(BevelBorder.RAISED, getHighlightOuterColor(), getHighlightInnerColor(), getShadowOuterColor(),
        getShadowInnerColor());
    super.paintBorder(c, g, x, y, width, height);
  }
}
