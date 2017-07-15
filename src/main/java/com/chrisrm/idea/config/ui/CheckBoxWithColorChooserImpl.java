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

package com.chrisrm.idea.config.ui;

import com.intellij.ui.CheckBoxWithColorChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

/**
 * Created by helio on 24/03/2017.
 */
public class CheckBoxWithColorChooserImpl extends CheckBoxWithColorChooser {
  private MouseListener[] disabledListeners;

  public CheckBoxWithColorChooserImpl(String text) {
    super(text);
  }

  @Override
  public void setEnabled(boolean enabled) {
    super.setEnabled(enabled);

    // Disallow color choosing if disabled
    synchronized (getTreeLock()) {
      for (Component component : getComponents()) {
        component.setEnabled(enabled);
        if (component instanceof JButton) {
          if (enabled) {
            if (disabledListeners != null) {
              for (MouseListener listener : disabledListeners) {
                component.addMouseListener(listener);
              }
            }
            disabledListeners = null;
          } else {
            disabledListeners = component.getMouseListeners();
            for (MouseListener listener : disabledListeners) {
              component.removeMouseListener(listener);
            }
          }
        }
      }
    }
  }

  public void dispose() {
    disabledListeners = null;
  }
}
