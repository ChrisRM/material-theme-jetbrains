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
package com.chrisrm.idea.utils;

import com.intellij.openapi.util.Disposer;
import com.intellij.util.ui.Animator;
import com.intellij.util.ui.GraphicsUtil;
import com.intellij.util.ui.UIUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.LinkedHashMap;
import java.util.Map;

public final class MTChangeLAFAnimator {
  private float myAlpha = 1;
  private final Map<JLayeredPane, JComponent> myMap;
  private final Animator myAnimator;

  public static MTChangeLAFAnimator showSnapshot() {
    return new MTChangeLAFAnimator();
  }

  private MTChangeLAFAnimator() {
    myAnimator = new Animator("ChangeLAF", 60, 800, false) {
      @Override
      public void resume() {
        doPaint();
        super.resume();
      }

      @Override
      public void paintNow(final int frame, final int totalFrames, final int cycle) {
        myAlpha = 1 - (float) (1 - Math.cos(Math.PI * frame / (float) totalFrames)) / 2;
        doPaint();
      }

      @Override
      protected void paintCycleEnd() {
        if (!isDisposed()) {
          Disposer.dispose(this);
        }
      }

      @Override
      public void dispose() {
        try {
          super.dispose();
          for (final Map.Entry<JLayeredPane, JComponent> entry : myMap.entrySet()) {
            final JLayeredPane layeredPane = entry.getKey();
            layeredPane.remove(entry.getValue());
            layeredPane.revalidate();
            layeredPane.repaint();
          }
        } finally {
          myMap.clear();
        }
      }
    };

    final Window[] windows = Window.getWindows();
    myMap = new LinkedHashMap<>();
    for (final Window window : windows) {
      if (window instanceof RootPaneContainer && window.isShowing()) {
        final Rectangle bounds = window.getBounds();
        final RootPaneContainer rootPaneContainer = (RootPaneContainer) window;
        final JLayeredPane layeredPane = rootPaneContainer.getLayeredPane();
        final BufferedImage image =
            UIUtil.createImage(window.getGraphicsConfiguration(), bounds.width, bounds.height, BufferedImage.TYPE_INT_ARGB);
        final Graphics imageGraphics = image.getGraphics();
        GraphicsUtil.setupAntialiasing(imageGraphics);
        ((RootPaneContainer) window).getRootPane().paint(imageGraphics);

        final JComponent imageLayer = new JComponent() {
          @Override
          public void updateUI() {
          }

          @Override
          public void paint(final Graphics g) {
            final Graphics graphics = g.create();
            ((Graphics2D) graphics).setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, myAlpha));
            UIUtil.drawImage(g, image, 0, 0, this);
          }

          @Override
          public Rectangle getBounds() {
            return layeredPane.getBounds();
          }
        };
        imageLayer.setSize(layeredPane.getSize());
        layeredPane.add(imageLayer, JLayeredPane.DRAG_LAYER);
        myMap.put(layeredPane, imageLayer);
      }
    }
    doPaint();
  }

  public void hideSnapshotWithAnimation() {
    myAnimator.resume();
  }

  private void doPaint() {
    for (final Map.Entry<JLayeredPane, JComponent> entry : myMap.entrySet()) {
      if (entry.getKey().isShowing()) {
        entry.getValue().revalidate();
        entry.getValue().repaint();
      }
    }
  }
}
