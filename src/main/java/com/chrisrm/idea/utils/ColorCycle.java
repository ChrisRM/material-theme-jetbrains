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

package com.chrisrm.idea.utils;

import javax.swing.*;
import java.awt.*;

public final class ColorCycle {
  private final Timer timer;
  private Color[] colors;
  private int index;

  private JComponent c;

  public ColorCycle(final int steps, final int fps) {
    timer = new Timer(1000 / fps, e -> {
      index++;
      if (index > steps) {
        index = 0;
        this.stop();
      }

      if (index != 0) {
        c.setBackground(this.colors[index - 1]);
        c.repaint();
      }
    });
  }

  public void setC(final JComponent c) {
    this.c = c;
  }

  public void start(final Color... colors) {
    this.colors = colors;
    timer.start();
  }

  public void stop() {
    timer.stop();
  }
}
