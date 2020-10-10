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

package com.mallowigi.idea.actions;

import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.actionSystem.ToggleAction;
import com.intellij.openapi.actionSystem.Toggleable;
import com.intellij.ui.LayeredIcon;
import com.intellij.util.IconUtil;
import com.intellij.util.ObjectUtils;
import com.intellij.util.ui.GraphicsUtil;
import com.intellij.util.ui.JBUI;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("NoopMethodInAbstractClass")
public abstract class MTToggleAction extends ToggleAction {
  @Override
  public void update(@NotNull final AnActionEvent e) {
    boolean selected = isSelected(e);
    Presentation presentation = e.getPresentation();
    Icon icon = presentation.getIcon();
    Toggleable.setSelected(presentation, selected);

    Icon fallbackIcon = selectedFallbackIcon(icon);
    Icon actionButtonIcon = ObjectUtils.notNull(UIManager.getIcon("ActionButton.backgroundIcon"), fallbackIcon);

    // Recreate the action button look
    if (selected) {
      e.getPresentation().setIcon(new LayeredIcon(actionButtonIcon, regularIcon(icon)));
    } else {
      e.getPresentation().setIcon(regularIcon(icon));
    }
  }

  @NotNull
  private Icon regularIcon(final Icon icon) {
    return IconUtil.toSize(icon, JBUI.scale(18), JBUI.scale(18));
  }

  private Icon selectedFallbackIcon(Icon icon) {
    return new Icon() {
      @Override
      public void paintIcon(final Component c, final Graphics g, final int x, final int y) {
        Graphics g2d = g.create();

        try {
          GraphicsUtil.setupAAPainting(g2d);
          g2d.setColor(JBUI.CurrentTheme.ActionButton.pressedBackground());
          g2d.fillRoundRect(0, 0, getIconWidth(), getIconHeight(), 4, 4);
        } finally {
          g2d.dispose();
        }
      }

      @Override
      public int getIconWidth() {
        return icon != null ? icon.getIconWidth() : JBUI.scale(18);
      }

      @Override
      public int getIconHeight() {
        return icon != null ? icon.getIconHeight() : JBUI.scale(18);
      }
    };
  }
}
