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

import com.intellij.ide.ui.laf.darcula.ui.DarculaProgressBarUI;
import com.mallowigi.idea.utils.MTUI;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import java.awt.*;

/**
 * @author Konstantin Bulenkov
 */
public final class MTProgressBarUI extends DarculaProgressBarUI {

  private static final Color TRACK_COLOR = MTUI.ProgressBar.getTrackColor();
  private static final Color PROGRESS_COLOR = MTUI.ProgressBar.getProgressColor();
  private static final Color INDETERMINATE_START_COLOR = MTUI.ProgressBar.getIndeterminateStartColor();
  private static final Color INDETERMINATE_END_COLOR = MTUI.ProgressBar.getIndeterminateEndColor();

  @SuppressWarnings({"MethodOverridesStaticMethodOfSuperclass",
    "unused"})
  public static ComponentUI createUI(final JComponent component) {
    return new MTProgressBarUI();
  }

  @Override
  protected Color getRemainderColor() {
    return TRACK_COLOR;
  }

  @Override
  protected Color getStartColor() {
    return INDETERMINATE_START_COLOR;
  }

  @Override
  protected Color getEndColor() {
    return INDETERMINATE_END_COLOR;
  }

  @Override
  protected Color getFinishedColor() {
    return PROGRESS_COLOR;
  }
}
