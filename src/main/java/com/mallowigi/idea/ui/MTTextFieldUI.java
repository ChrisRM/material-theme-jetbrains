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
package com.mallowigi.idea.ui;

import com.intellij.ide.ui.laf.darcula.ui.TextFieldWithPopupHandlerUI;
import com.intellij.openapi.util.IconLoader;
import com.intellij.ui.scale.JBUIScale;
import com.intellij.util.ui.JBInsets;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.MacUIUtil;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.ComponentUI;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

import static com.intellij.ide.ui.laf.darcula.DarculaUIUtil.isCompact;
import static com.intellij.ide.ui.laf.darcula.DarculaUIUtil.isTableCellEditor;

public final class MTTextFieldUI extends TextFieldWithPopupHandlerUI {

  @SuppressWarnings({"MethodOverridesStaticMethodOfSuperclass",
    "unused"})
  public static ComponentUI createUI(final JComponent component) {
    return new MTTextFieldUI();
  }

  @Override
  protected void paintBackground(final Graphics g) {
    final Graphics2D g2d = (Graphics2D) g;
    final JTextComponent component = getComponent();
    if (component != null) {
      final Container parent = component.getParent();
      // Paint the same color as the parent
      if (component.isOpaque() && parent != null) {
        g2d.setColor(parent.getBackground());
        g2d.fillRect(0, 0, component.getWidth(), component.getHeight());
      }

      final Border border = component.getBorder();
      if (border instanceof MTTextBorder && !isTableCellEditor(component)) {
        paintFieldBackground(g2d, component);
      } else {
        // delegate to default behavior
        super.paintBackground(g2d);
      }
    }
  }

  private static void paintFieldBackground(final Graphics2D g, final JTextComponent component) {
    final Graphics2D g2 = (Graphics2D) g.create();
    final Rectangle r = new Rectangle(component.getSize());
    JBInsets.removeFrom(r, JBUI.insets(1));

    try {
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
        MacUIUtil.USE_QUARTZ ? RenderingHints.VALUE_STROKE_PURE : RenderingHints.VALUE_STROKE_NORMALIZE);

      g2.translate(r.x, r.y);

      if (component.isEnabled() && component.isEditable()) {
        final float height = JBUI.scale(2);
        final float arc = isSearchField(component) ? JBUIScale.scale(6.0f) : 0.0f;

        g2.setColor(component.getBackground());
        g2.fill(new RoundRectangle2D.Float(0.0f, (r.height * 2) - height, r.width * 2, height, arc, arc));
      }
    } finally {
      g2.dispose();
    }
  }

  @Override
  protected Icon getSearchIcon(final boolean hovered, final boolean clickable) {
    return clickable ?
           (hovered ?
            IconLoader.findIcon("/icons/mt/searchWithHistoryHovered.svg") :
            IconLoader.findIcon("/icons/mt/searchWithHistory.svg")
           ) :
           IconLoader.findIcon("/icons/mt/search.svg");
  }

  /**
   * @return a gap between the search icon and the editable area
   */
  @Override
  protected int getSearchIconGap() {
    return JBUI.scale(2);
  }

  @Nullable
  @Override
  protected Icon getClearIcon(final boolean hovered, final boolean clickable) {
    return clickable ? IconLoader.findIcon("/icons/mt/clear.svg") : null;
  }

  @Override
  protected int getClearIconPreferredSpace() {
    return super.getClearIconPreferredSpace() - getClearIconGap();
  }

  @Override
  protected int getClearIconGap() {
    return JBUI.scale(2);
  }

  @Override
  protected void paintSafely(final Graphics g) {
    paintBackground(g);
    super.paintSafely(g);
  }

  @Override
  protected Insets getDefaultMargins() {
    final Component component = getComponent();
    return isCompact(component) || isTableCellEditor(component) ? JBUI.insets(0, 3) : JBUI.insets(2, 2);
  }
}
