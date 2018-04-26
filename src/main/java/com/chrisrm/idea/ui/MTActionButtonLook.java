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

import com.intellij.openapi.actionSystem.ActionButtonComponent;
import com.intellij.openapi.actionSystem.impl.IdeaActionButtonLook;
import com.intellij.util.ui.JBInsets;
import com.intellij.util.ui.JBUI;

import javax.swing.*;
import java.awt.*;

public class MTActionButtonLook extends IdeaActionButtonLook {
  @Override
  public void paintBackground(final Graphics g, final JComponent component, final int state) {
    if (state != ActionButtonComponent.NORMAL) {
      final Rectangle rect = new Rectangle(component.getSize());
      JBInsets.removeFrom(rect, component.getInsets());
      paintBackground(g, rect, state);
    }
  }

  protected static void paintBackground(final Graphics g, final Rectangle rect, final int state) {
    final Graphics2D g2 = (Graphics2D) g.create();
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
    g2.translate(rect.x, rect.y);
    final Color color = UIManager.getColor("Focus.color");
    g2.setColor(color);

    try {
      if (state == ActionButtonComponent.PUSHED) {
        if (rect.width > 28) {
          g2.fill3DRect(0, 0, rect.width, rect.height, true);
        } else {
          g2.fillOval(0, 0, rect.height - JBUI.scale(1), rect.height - JBUI.scale(1));
        }
      }
    } finally {
      g2.dispose();
    }
  }

  @Override
  public void paintBorder(final Graphics g, final JComponent component, final int state) {
    final Graphics2D g2 = (Graphics2D) g.create();
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);

    final Rectangle rect = new Rectangle(component.getSize());
    JBInsets.removeFrom(rect, component.getInsets());

    final Color color = UIManager.getColor("Focus.color");
    g2.translate(rect.x, rect.y);
    g2.setColor(color);

    try {
      if (state == ActionButtonComponent.POPPED) {
        if (rect.width > 28) {
          g2.fill3DRect(0, 0, rect.width, rect.height, true);
        } else {
          g2.fillOval(0, 0, rect.height - JBUI.scale(1), rect.height - JBUI.scale(1));
        }
      }
    } finally {
      g2.dispose();
    }
  }
}
