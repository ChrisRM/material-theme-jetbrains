/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015-2021 Elior "Mallowigi" Boukhobza
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

package com.mallowigi.idea.ui;

import com.intellij.openapi.ui.AbstractPainter;
import com.intellij.ui.JBColor;
import com.intellij.ui.components.panels.NonOpaquePanel;
import com.intellij.util.ui.GraphicsUtil;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class OverlayPainter extends AbstractPainter {
  private final Shape myBoundingBox;

  public OverlayPainter(final NonOpaquePanel component) {
    final Dimension size = component.getSize();
    final Rectangle r = new Rectangle(size);
    myBoundingBox = new Rectangle2D.Double(r.x, r.y, r.width, r.height);
  }

  @Override
  public boolean needsRepaint() {
    return myBoundingBox != null;
  }

  @Override
  public void executePaint(final Component component, final Graphics2D g) {
    if (myBoundingBox == null) {
      return;
    }
    GraphicsUtil.setupAAPainting(g);
    g.setColor(new JBColor(0x3d7dcc, 0x404a57));
    g.fill(myBoundingBox);
  }
}
