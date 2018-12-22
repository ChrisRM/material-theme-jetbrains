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

package com.chrisrm.idea.utils;

import com.intellij.util.Function;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("OverlyLongLambda")
public final class HighlightAnimation {
  private final Timer timer;
  private int index;
  private Function<? super Integer, Integer> lambda;

  public HighlightAnimation(final int steps, final int fps, final Rectangle rect) {

    timer = new Timer(1000 / fps, e -> {
      index++;
      if (index > steps) {
        index = 0;
        stop();
      }

      if (index != 0) {
        final int width = (rect.width / steps) * index;
        lambda.fun(width);
      }
    });
  }

  public void start(final Function<? super Integer, Integer> cb) {
    lambda = cb;
    timer.start();
  }

  private void stop() {
    timer.stop();
  }
}
