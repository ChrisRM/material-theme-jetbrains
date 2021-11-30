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

import com.intellij.ui.ColorUtil;
import com.mallowigi.idea.utils.ButtonBackgroundTimer;
import org.jetbrains.annotations.NonNls;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.plaf.basic.BasicButtonListener;
import java.awt.*;
import java.util.ArrayDeque;

@SuppressWarnings({"DuplicatedCode",
  "ParameterNameDiffersFromOverriddenParameter"})
final class MTButtonHighlighter extends BasicButtonListener {
  private static final int ANIM_STEPS = 5;
  public static final double BALANCE = 0.2F;
  private static final @NonNls ButtonBackgroundTimer HL_BUTTON_BACKGROUND_TIMER = new ButtonBackgroundTimer(20);
  private boolean rollover = false;

  private final AbstractButton button;

  MTButtonHighlighter(final AbstractButton button) {
    super(button);
    this.button = button;
  }

  @Override
  public void stateChanged(final ChangeEvent e) {
    if (e == null) {
      return;
    }
    final ButtonModel model = button.getModel();
    if (model.isRollover() != rollover) {
      rollover = model.isRollover();

      if (rollover) {
        highlightButton(button);
      } else {
        removeHighlight(button);
      }
    }
  }

  @SuppressWarnings("FeatureEnvy")
  private static void highlightButton(final AbstractButton e) {
    final JButton jButton = (JButton) e;
    final Color hoverColor = jButton.isDefaultButton() ? MTButtonUI.primaryButtonHoverColor() : MTButtonUI.buttonHoverColor();
    final Color preHoverColor = jButton.isDefaultButton() ? MTButtonUI.primaryButtonBg() : MTButtonUI.buttonBg();

    final ArrayDeque<Color> colors = new ArrayDeque<>(ANIM_STEPS);
    for (int i = 0; i < ANIM_STEPS; i++) {
      colors.add(ColorUtil.mix(preHoverColor, hoverColor, i * BALANCE));
    }

    final Color textColor = MTButtonUI.selectedButtonFg();
    e.setForeground(textColor);
    HL_BUTTON_BACKGROUND_TIMER.start("Highlight", e, colors);
  }

  @SuppressWarnings("FeatureEnvy")
  private static void removeHighlight(final AbstractButton e) {
    final JButton jButton = (JButton) e;
    final Color hoverColor = jButton.isDefaultButton() ? MTButtonUI.primaryButtonHoverColor() : MTButtonUI.buttonHoverColor();
    final Color preHoverColor = jButton.isDefaultButton() ? MTButtonUI.primaryButtonBg() : MTButtonUI.buttonBg();

    final ArrayDeque<Color> colors = new ArrayDeque<>(ANIM_STEPS);
    for (int i = 0; i < ANIM_STEPS; i++) {
      colors.addFirst(ColorUtil.mix(preHoverColor, hoverColor, i * BALANCE));
    }

    final Color textColor = MTButtonUI.buttonFg();
    e.setForeground(textColor);
    HL_BUTTON_BACKGROUND_TIMER.start("Remove Highlight", e, colors);
  }

}
