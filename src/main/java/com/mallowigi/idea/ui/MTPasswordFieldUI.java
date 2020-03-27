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
package com.mallowigi.idea.ui;

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
@SuppressWarnings("WeakerAccess")
public final class MTPasswordFieldUI extends BasicPasswordFieldUI implements Condition {

  /**
   * The actual password field
   */
  private final JPasswordField passwordField;
  /**
   * Adapter for mouse clicks
   */
  private ShowHidePasswordAdapter showHidePasswordAdapter;
  /**
   * Adapter for Field focus
   */
  private FocusAdapter myFocusAdapter;
  /**
   * Whether the echo char (bullet) is set
   */
  private boolean echoCharIsSet = true;

  private MTPasswordFieldUI(final JPasswordField passwordField) {
    this.passwordField = passwordField;
  }

  @SuppressWarnings({"MethodOverridesStaticMethodOfSuperclass",
    "unused"})
  public static ComponentUI createUI(final JComponent component) {
    return new MTPasswordFieldUI((JPasswordField) component);
  }

  /**
   * The JComponent for this MTPasswordUI
   */
  public JPasswordField getPasswordField() {
    return passwordField;
  }

  /**
   * Flag setting echoChar for this password field
   */
  public boolean isEchoCharIsSet() {
    return echoCharIsSet;
  }

  @Override
  public void installListeners() {
    final MTPasswordFieldUI ui = this;
    // Add listeners to show/hide the password
    myFocusAdapter = new RepaintOnFocusAdapter();
    passwordField.addFocusListener(myFocusAdapter);
    showHidePasswordAdapter = new ShowHidePasswordAdapter(ui);
    passwordField.addMouseListener(showHidePasswordAdapter);
  }

  @Override
  protected void uninstallListeners() {
    passwordField.removeFocusListener(myFocusAdapter);
    passwordField.removeMouseListener(showHidePasswordAdapter);
  }

  @Override
  protected void paintBackground(final Graphics g) {
    final Graphics2D g2d = (Graphics2D) g;
    final JTextComponent component = getComponent();
    final Container parent = component.getParent();

    if (component.isOpaque() && parent != null) {
      g2d.setColor(parent.getBackground());
      g2d.fillRect(0, 0, component.getWidth(), component.getHeight());
    }

    final Border border = component.getBorder();
    if (border instanceof MTTextBorder) {
      if (component.isEnabled() && component.isEditable()) {
        g2d.setColor(component.getBackground());
      }
      final int width = component.getWidth();
      final int height = component.getHeight();
      final Insets insets = border.getBorderInsets(component);

      // Show the eye on focus and set the input to accent color
      if (component.hasFocus()) {
        final GraphicsConfig config = new GraphicsConfig(g2d);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);

        // Paint the field
        g2d.fillRoundRect(insets.left - JBUI.scale(5),
          insets.top - JBUI.scale(2),
          width - insets.left - insets.right + JBUI.scale(10),
          height - insets.top - insets.bottom + JBUI.scale(6),
          JBUI.scale(5),
          JBUI.scale(5));

        // Paint the preview icon
        final Point p = getPreviewIconCoordinates();
        final Icon searchIcon = echoCharIsSet ? IconLoader.findIcon("/icons/mt/eye.svg") : IconLoader.findIcon("/icons/mt/eyeOff.svg");
        searchIcon.paintIcon(null, g2d, p.x, p.y);

        config.restore();
      } else {
        g2d.fillRect(insets.left - JBUI.scale(5),
          insets.top - JBUI.scale(2),
          width - insets.left - insets.right + JBUI.scale(12),
          height - insets.top - insets.bottom + JBUI.scale(6));
      }
    } else {
      super.paintBackground(g2d);
    }
  }

  @Override
  public boolean value(final Object t) {
    if (t instanceof MouseEvent) {
      final MouseEvent me = (MouseEvent) t;
      // If clicked on the eye, execute the eye action
      if (getActionUnder(me.getPoint()) != null) {
        if (me.getID() == MouseEvent.MOUSE_CLICKED) {
          SwingUtilities.invokeLater(() -> showHidePasswordAdapter.mouseClicked(me));
        }
        return true;
      }
    }
    return false;
  }

  /**
   * Return the action under the mouse location
   *
   * @param p coordinate of the mouse event location
   * @return the PasswordActions if the event is under the given offset
   */
  PasswordActions getActionUnder(@NotNull final Point p) {
    final int off = JBUI.scale(8);
    final Point point = new Point(p.x - off, p.y - off);
    if (point.distance(getPreviewIconCoordinates()) <= off) {
      return PasswordActions.PREVIEW;
    }
    return null;
  }

  void toggleEchoChar() {
    echoCharIsSet = !echoCharIsSet;
  }

  private Rectangle getDrawingRect() {
    final JComponent component = passwordField;
    final JBInsets insets = JBInsets.create(component.getInsets());
    final int x = insets.right - JBUI.scale(4) - JBUI.scale(16 * 2);
    final int y = insets.top - JBUI.scale(3);
    final int w = component.getWidth() - insets.width() + JBUI.scale(16 * 2 + 7 * 2 - 5);
    int h = component.getBounds().height - insets.height() + JBUI.scale(4 * 2 - 3);
    if (h % 2 == 1) {
      h++;
    }
    return new Rectangle(x, y, w, h);
  }

  /**
   * Return the coordinates of the eye
   */
  private Point getPreviewIconCoordinates() {
    final Rectangle r = getDrawingRect();
    return new Point(r.x + r.width - JBUI.scale(20), r.y + (r.height - JBUI.scale(16)) / 2);
  }

  /**
   * Type of additional actions (currently only show/hide password)
   */
  public enum PasswordActions {
    PREVIEW
  }

  private final class ShowHidePasswordAdapter extends MouseAdapter {
    private static final char SHOW_VALUE = '\0';
    private final MTPasswordFieldUI passwordFieldUI;

    @SuppressWarnings("WeakerAccess")
    ShowHidePasswordAdapter(final MTPasswordFieldUI ui) {
      passwordFieldUI = ui;
    }

    @Override
    public void mouseClicked(final MouseEvent e) {
      final PasswordActions action = passwordFieldUI.getActionUnder(e.getPoint());

      //noinspection VariableNotUsedInsideIf
      if (action != null) {
        // Remove the echo char (bullet)
        if (isEchoCharIsSet()) {
          getPasswordField().setEchoChar(SHOW_VALUE);
        } else {
          getPasswordField().setEchoChar((char) 0x2022);
        }
        toggleEchoChar();
      }
      e.consume();
    }
  }

  private final class RepaintOnFocusAdapter extends FocusAdapter {
    @Override
    public void focusGained(final FocusEvent e) {
      getPasswordField().repaint();
    }

    @Override
    public void focusLost(final FocusEvent e) {
      getPasswordField().repaint();
    }
  }
}
