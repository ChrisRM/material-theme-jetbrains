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
package com.chrisrm.idea.ui;

import com.intellij.ide.ui.laf.darcula.ui.TextFieldWithPopupHandlerUI;
import com.intellij.openapi.ui.GraphicsConfig;
import com.intellij.openapi.util.IconLoader;
import com.intellij.util.ui.JBInsets;
import com.intellij.util.ui.JBUI;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.*;
import javax.swing.text.*;
import java.awt.*;

/**
 * @author Konstantin Bulenkov
 */
public final class MTTextFieldUI extends TextFieldWithPopupHandlerUI {

  public MTTextFieldUI(final JTextField textField) {
    super(textField);
  }

  public MTTextFieldUI(final JComponent c) {
    this((JTextField) c);
  }

  @SuppressWarnings("MethodOverridesStaticMethodOfSuperclass")
  public static ComponentUI createUI(final JComponent c) {
    return new MTTextFieldUI((JTextField) c);
  }

  @Override
  protected SearchAction getActionUnder(@NotNull final Point p) {
    int off = JBUI.scale(8);
    Point point = new Point(p.x - off, p.y - off);
    return point.distance(getSearchIconCoord()) <= off
           ? SearchAction.POPUP
           : hasText() && point.distance(getClearIconCoord()) <= off
             ? SearchAction.CLEAR
             : null;
  }

  @Override
  protected void showSearchPopup() {
    final Object value = myTextField.getClientProperty("JTextField.Search.FindPopup");
    final JTextComponent editor = getComponent();
    if (editor != null && value instanceof JPopupMenu) {
      final JPopupMenu popup = (JPopupMenu) value;
      popup.show(editor, getSearchIconCoord().x, editor.getHeight());
    }
  }

  protected Rectangle getDrawingRect() {
    final JTextComponent c = myTextField;
    final JBInsets i = JBInsets.create(c.getInsets());
    final int x = i.right - JBUI.scale(4) - JBUI.scale(16);
    final int y = i.top - JBUI.scale(3);
    final int w = c.getWidth() - i.width() + JBUI.scale(16 * 2 + 7 * 2 - 5);
    int h = c.getBounds().height - i.height() + JBUI.scale(4 * 2 - 3);
    if (h % 2 == 1) {
      h++;
    }
    return new Rectangle(x, y, w, h);
  }

  protected Point getSearchIconCoord() {
    final Rectangle r = getDrawingRect();
    return new Point(r.x + JBUI.scale(3), r.y + (r.height - JBUI.scale(16)) / 2 + JBUI.scale(1));
  }

  protected Point getClearIconCoord() {
    final Rectangle r = getDrawingRect();
    return new Point(r.x + r.width - JBUI.scale(16) - JBUI.scale(2), r.y + (r.height - JBUI.scale(16)) / 2);
  }

  @Override
  protected void paintBackground(final Graphics graphics) {
    Graphics2D g = (Graphics2D) graphics;
    final JTextComponent c = getComponent();
    final Container parent = c.getParent();
    final Rectangle r = getDrawingRect();
    if (c.isOpaque() && parent != null) {
      g.setColor(parent.getBackground());
      g.fillRect(0, 0, c.getWidth(), c.getHeight());
    }
    final GraphicsConfig config = new GraphicsConfig(g);
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);

    final Border border = c.getBorder();
    if (isSearchField(c)) {
      paintSearchField(g, c, r, border);
    } else if (border instanceof MTTextBorder) {
      paintDarculaBackground(g, c, border);
    } else {
      super.paintBackground(g);
    }
    config.restore();
  }

  protected void paintDarculaBackground(final Graphics2D g, final JTextComponent c, final Border border) {
    if (c.isEnabled() && c.isEditable()) {
      g.setColor(c.getBackground());
    }
    final int width = c.getWidth();
    final int height = c.getHeight();
    final Insets i = border.getBorderInsets(c);

    try {
      if (!icons.isEmpty()) {
        for (IconHolder holder : icons.values()) {
          int space = holder.bounds.width + holder.extension.getIconGap();
          if (holder.extension.isIconBeforeText()) {
            i.left -= space;
          } else {
            i.right -= space;
          }
        }
      }
    } catch (NoSuchFieldError e) {
      ;
    }

    if (c.hasFocus()) {
      g.fillRoundRect(i.left - JBUI.scale(5), i.top - JBUI.scale(2), width - i.right - i.left + JBUI.scale(10), height - i.top - i
          .bottom + JBUI.scale(6), JBUI.scale(5), JBUI.scale(5));
    } else {
      g.fillRect(i.left - JBUI.scale(5), i.top - JBUI.scale(2), width - i.right - i.left + JBUI.scale(10), height - i.top - i
          .bottom + JBUI.scale(8));
    }
  }

  protected void paintSearchField(final Graphics2D g, final JTextComponent c, final Rectangle r, final Border border) {
    if (c.isEnabled() && c.isEditable()) {
      g.setColor(c.getBackground());
    }
    Point p = getSearchIconCoord();
    Icon searchIcon = myTextField.getClientProperty("JTextField.Search.FindPopup") instanceof JPopupMenu ?
                      UIManager.getIcon("TextField.darcula.searchWithHistory.icon") :
                      UIManager.getIcon("TextField.darcula.search.icon");
    if (searchIcon == null) {
      searchIcon = IconLoader.findIcon("/com/intellij/ide/ui/laf/icons/search.png", MTTextFieldUI.class, true);
    }

    searchIcon.paintIcon(null, g, p.x, p.y);
    if (hasText()) {
      p = getClearIconCoord();
      Icon clearIcon = UIManager.getIcon("TextField.darcula.clear.icon");
      if (clearIcon == null) {
        clearIcon = IconLoader.findIcon("/com/intellij/ide/ui/laf/icons/clear.png", MTTextFieldUI.class, true);
      }
      clearIcon.paintIcon(null, g, p.x, p.y);
    }
  }

  @Override
  protected void paintSafely(final Graphics g) {
    paintBackground(g);
    super.paintSafely(g);
  }
}
