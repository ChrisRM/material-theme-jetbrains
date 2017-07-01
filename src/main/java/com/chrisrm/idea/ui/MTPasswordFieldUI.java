/*
 * Copyright 2000-2015 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
    installListeners();
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
            echoCharIsSet = !echoCharIsSet;
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

        g.fillRoundRect(i.left - JBUI.scale(5), i.top - JBUI.scale(2), width - i.left - i.right + JBUI.scale(10), height - i.top
            - i.bottom + JBUI.scale(6), JBUI.scale(5), JBUI.scale(5));

        // Paint the preview icon
        Point p = getPreviewIconCoord();
        Icon searchIcon = UIManager.getIcon("PasswordField.preview.icon");
        if (searchIcon == null) {
          searchIcon = IconLoader.findIcon("/icons/general/eye.png", MTPasswordFieldUI.class, true);
        }
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
      return echoCharIsSet ? PasswordActions.PREVIEW : PasswordActions.HIDE;
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
    PREVIEW,
    HIDE
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
            passwordField.setEchoChar('\0');
            break;
          case HIDE:
            passwordField.setEchoChar('*');
            break;
        }
        e.consume();
      }
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
