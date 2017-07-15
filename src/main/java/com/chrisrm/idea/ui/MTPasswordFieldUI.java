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

import com.intellij.openapi.ui.GraphicsConfig;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.util.IconLoader;
import com.intellij.util.ui.JBInsets;
import com.intellij.util.ui.JBUI;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicPasswordFieldUI;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Konstantin Bulenkov
 */
public class MTPasswordFieldUI extends BasicPasswordFieldUI implements Condition {

  /**
   * The JComponent for this MTPasswordUI
   */
  private final JPasswordField passwordField;
  /**
   * Adapter for mouse clicks
   */
  private MyMouseAdapter myMouseAdapter;
  /**
   * Adapter for Field focus
   */
  private FocusAdapter myFocusAdapter;
  /**
   * Flag setting echoChar for this password field
   */
  private boolean echoCharIsSet = true;

  private MTPasswordFieldUI(final JPasswordField passwordField) {
    this.passwordField = passwordField;
  }

  @SuppressWarnings({"MethodOverridesStaticMethodOfSuperclass", "UnusedDeclaration"})
  public static ComponentUI createUI(final JComponent c) {
    return new MTPasswordFieldUI((JPasswordField) c);
  }

  @Override
  public void installListeners() {
    final MTPasswordFieldUI ui = this;
    myFocusAdapter = new MyFocusAdapter();
    passwordField.addFocusListener(myFocusAdapter);
    myMouseAdapter = new MyMouseAdapter(ui);
    passwordField.addMouseListener(myMouseAdapter);
  }

  @Override
  public boolean value(Object o) {
    if (o instanceof MouseEvent) {
      MouseEvent me = (MouseEvent) o;
      if (getActionUnder(me.getPoint()) != null) {
        if (me.getID() == MouseEvent.MOUSE_CLICKED) {
          //noinspection SSBasedInspection
          SwingUtilities.invokeLater(() -> {
            myMouseAdapter.mouseClicked(me);
          });
        }
        return true;
      }
    }
    return false;
  }

  @Override
  protected void uninstallListeners() {
    passwordField.removeFocusListener(myFocusAdapter);
    passwordField.removeMouseListener(myMouseAdapter);
  }

  @Override
  protected void paintBackground(Graphics graphics) {
    Graphics2D g = (Graphics2D) graphics;
    final JTextComponent c = getComponent();
    final Container parent = c.getParent();
    if (c.isOpaque() && parent != null) {
      g.setColor(parent.getBackground());
      g.fillRect(0, 0, c.getWidth(), c.getHeight());
    }
    final Border border = c.getBorder();
    if (border instanceof MTTextBorder) {
      if (c.isEnabled() && c.isEditable()) {
        g.setColor(c.getBackground());
      }
      final int width = c.getWidth();
      final int height = c.getHeight();
      final Insets i = border.getBorderInsets(c);
      if (c.hasFocus()) {
        final GraphicsConfig config = new GraphicsConfig(g);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);

        g.fillRoundRect(i.left - JBUI.scale(5),
            i.top - JBUI.scale(2),
            width - i.left - i.right + JBUI.scale(10),
            height - i.top
                - i.bottom + JBUI.scale(6),
            JBUI.scale(5), JBUI.scale(5));

        // Paint the preview icon
        Point p = getPreviewIconCoord();
        String path = echoCharIsSet ? "/icons/general/eye.png" : "/icons/general/eye-off.png";
        Icon searchIcon = IconLoader.findIcon(path, MTPasswordFieldUI.class, true);
        searchIcon.paintIcon(null, g, p.x, p.y);

        config.restore();
      } else {
        g.fillRect(i.left - JBUI.scale(5),
            i.top - JBUI.scale(2),
            width - i.left - i.right + JBUI.scale(12),
            height - i.top - i
                .bottom + JBUI.scale(6));
      }
    } else {
      super.paintBackground(g);
    }
  }

  /**
   * Return the action under the mouse location
   *
   * @param p coordinate of the mouse event location
   * @return the PasswordActions if the event is under the given offset
   */
  private PasswordActions getActionUnder(@NotNull Point p) {
    int off = JBUI.scale(8);
    Point point = new Point(p.x - off, p.y - off);
    if (point.distance(getPreviewIconCoord()) <= off) {
      return PasswordActions.PREVIEW;
    }
    return null;
  }

  private Rectangle getDrawingRect() {
    JComponent c = passwordField;
    final JBInsets i = JBInsets.create(c.getInsets());
    final int x = i.right - JBUI.scale(4) - JBUI.scale(16 * 2);
    final int y = i.top - JBUI.scale(3);
    final int w = c.getWidth() - i.width() + JBUI.scale(16 * 2 + 7 * 2 - 5);
    int h = c.getBounds().height - i.height() + JBUI.scale(4 * 2 - 3);
    if (h % 2 == 1) {
      h++;
    }
    return new Rectangle(x, y, w, h);
  }

  private Point getPreviewIconCoord() {
    final Rectangle r = getDrawingRect();
    return new Point(r.x + r.width - JBUI.scale(16), r.y + (r.height - JBUI.scale(16)) / 2);
  }

  public enum PasswordActions {
    PREVIEW
  }

  private class MyMouseAdapter extends MouseAdapter {
    private final MTPasswordFieldUI myUi;

    MyMouseAdapter(MTPasswordFieldUI ui) {
      myUi = ui;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
      final PasswordActions action = myUi.getActionUnder(e.getPoint());
      if (action != null) {
        switch (action) {
          case PREVIEW:
            if (echoCharIsSet) {
              passwordField.setEchoChar('\0');
              echoCharIsSet = false;
            } else {
              passwordField.setEchoChar((char) 0x2022);
              echoCharIsSet = true;
            }
            break;
        }
      }
      e.consume();
    }
  }

  private class MyFocusAdapter extends FocusAdapter {
    @Override
    public void focusGained(FocusEvent e) {
      passwordField.repaint();
    }

    @Override
    public void focusLost(FocusEvent e) {
      passwordField.repaint();
    }
  }
}
