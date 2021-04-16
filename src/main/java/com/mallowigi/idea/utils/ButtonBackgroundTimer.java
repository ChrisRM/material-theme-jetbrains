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

package com.mallowigi.idea.utils;

import com.intellij.util.ui.TimerUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayDeque;

public final class ButtonBackgroundTimer {
  private final int fps;

  @SuppressWarnings("OverlyLongLambda")
  public ButtonBackgroundTimer(final int fps) {
    this.fps = fps;
  }

  private static ActionListener getActionListener(final Timer timer, final Component component, final ArrayDeque<? extends Color> colors) {
    return e -> {
      final Color color = colors.poll();
      if (color == null) {
        timer.stop();
        return;
      }

      if (component != null) {
        component.setBackground(color);
        component.repaint();
      }
    };
  }

  public void start(final String name, final Component component, final ArrayDeque<Color> colors) {
    final Timer timer = TimerUtil.createNamedTimer(name, 1000 / fps);
    timer.addActionListener(getActionListener(timer, component, colors));
    timer.start();
  }

}
