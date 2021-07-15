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

import com.intellij.openapi.Disposable;
import com.intellij.openapi.options.ex.GlassPanel;
import com.intellij.openapi.ui.AbstractPainter;
import com.intellij.openapi.wm.IdeGlassPaneUtil;

import javax.swing.*;
import java.awt.*;

public class OverlayPainter extends AbstractPainter {
  private final GlassPanel myGlassPanel;
  private final JComponent component;
  private final Disposable overlayDisposable;
  boolean myVisible;

  public OverlayPainter(final JComponent component, final Disposable overlayDisposable) {
    this.component = component;
    this.overlayDisposable = overlayDisposable;
    myGlassPanel = new GlassPanel(this.component);
    IdeGlassPaneUtil.installPainter(this.component, this, this.overlayDisposable);

  }

  @Override
  public boolean needsRepaint() {
    return true;
  }

  @Override
  public void executePaint(final Component component, final Graphics2D g) {
    //    if (myVisible && myGlassPanel.isVisible()) {
    myGlassPanel.paintSpotlight(g, this.component);
    //    }
  }

  void addSpotlight(final Component event) {
    myGlassPanel.clear();
    myGlassPanel.addSpotlight((JComponent) event);
  }
}
