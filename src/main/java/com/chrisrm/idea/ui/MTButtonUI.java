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

import com.chrisrm.idea.utils.ColorCycle;
import com.intellij.icons.AllIcons;
import com.intellij.ide.ui.laf.darcula.DarculaLaf;
import com.intellij.ide.ui.laf.darcula.ui.DarculaButtonUI;
import com.intellij.openapi.ui.GraphicsConfig;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.util.ObjectUtils;
import com.intellij.util.ui.GraphicsUtil;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.UIUtil;
import sun.swing.SwingUtilities2;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicButtonListener;
import java.awt.*;
import java.awt.event.MouseEvent;

public class MTButtonUI extends DarculaButtonUI {
  @SuppressWarnings("MethodOverridesStaticMethodOfSuperclass")
  public static ComponentUI createUI(final JComponent c) {
    return new MTButtonUI();
  }

  public static boolean isHelpButton(final JComponent button) {
    return (SystemInfo.isMac || UIUtil.isUnderDarcula() || UIUtil.isUnderWin10LookAndFeel())
        && button instanceof JButton
        && "help".equals(button.getClientProperty("JButton.buttonType"));
  }

  @Override
  public final void update(final Graphics g, final JComponent c) {
    super.update(g, c);
  }

  /**
   * Install defaults and set font to bold + 13px
   *
   * @param b
   */
  @Override
  protected void installDefaults(final AbstractButton b) {
    super.installDefaults(b);
    final Color background = ObjectUtils.notNull(UIManager.getColor("Button.mt.background"), new ColorUIResource(0x3C3F41));
    b.setBackground(background);
    b.setFont(b.getFont().deriveFont(Font.BOLD, JBUI.scale(13.0f)));
  }

  /**
   * Create mouse listeners to simulate an highlighting
   * TODO maybe one day I'll do a riddle
   *
   * @param b
   * @return
   */
  @Override
  protected BasicButtonListener createButtonListener(final AbstractButton b) {
    return new BasicButtonListener(b) {

      private final ColorCycle colorCycle = new ColorCycle(2, 20);

      @Override
      public void mouseEntered(final MouseEvent e) {
        highlightButton(e);
      }

      @Override
      public void mouseExited(final MouseEvent e) {
        removeHighlight(e);
      }

      @Override
      public void mousePressed(final MouseEvent e) {
        highlightButton(e);
        super.mousePressed(e);
      }

      @Override
      public void mouseReleased(final MouseEvent e) {
        removeHighlight(e);
        super.mouseReleased(e);
      }

      private void highlightButton(final MouseEvent e) {
        colorCycle.stop();

        final Component component = e.getComponent();
        colorCycle.setC((JComponent) component);
        final Color hoverColor = ObjectUtils.notNull(UIManager.getColor("Button.mt.selection.color1"), new ColorUIResource(0x384f6b));
        final Color preHoverColor = ObjectUtils.notNull(UIManager.getColor("Button.mt.selection.color2"), new ColorUIResource(0x233143));
        final Color textColor = ObjectUtils.notNull(UIManager.getColor("Button.mt.selectedButtonForeground"),
            new ColorUIResource(0xbbbbbb));

        component.setForeground(textColor);
        colorCycle.start(preHoverColor, hoverColor);
      }

      private void removeHighlight(final MouseEvent e) {
        colorCycle.stop();

        final Component component = e.getComponent();
        colorCycle.setC((JComponent) component);
        final Color notHoverColor = ObjectUtils.notNull(UIManager.getColor("Button.mt.color1"), new ColorUIResource(0x555a5c));
        final Color preNotHoverColor = ObjectUtils.notNull(UIManager.getColor("Button.mt.color2"), new ColorUIResource(0x414648));
        final Color textColor = ObjectUtils.notNull(UIManager.getColor("Button.mt.foreground"), new ColorUIResource(0xbbbbbb));

        component.setForeground(textColor);
        colorCycle.start(preNotHoverColor, notHoverColor);
      }
    };
  }

  /**
   * Paints additional buttons decorations
   *
   * @param g Graphics
   * @param c button component
   * @return <code>true</code> if it is allowed to continue painting,
   * <code>false</code> if painting should be stopped
   */
  protected boolean paintDecorations(final Graphics2D g, final JComponent c) {
    final int w = c.getWidth();
    final int h = c.getHeight();
    final Color background = c.getBackground();
    final Color buttonColor1 = ObjectUtils.notNull(UIManager.getColor("Button.darcula.color1"), new ColorUIResource(0x555a5c));
    final Color buttonColor2 = ObjectUtils.notNull(UIManager.getColor("Button.darcula.color2"), new ColorUIResource(0x414648));
    final Color primaryButtonColor = ObjectUtils.notNull(UIManager.getColor("Button.darcula.selection.color1"), new ColorUIResource
        (0x384f6b));
    final Color focusedButtonColor = ObjectUtils.notNull(UIManager.getColor("Button.darcula.selection.color2"), new ColorUIResource
        (0x233143));

    if (isHelpButton(c)) {
      g.setPaint(UIUtil.getGradientPaint(0, 0, buttonColor1, 0, h, buttonColor2));
      final int off = JBUI.scale(22);
      final int x = (w - off) / 2;
      final int y = (h - off) / 2;
      g.fillOval(x, y, off, off);
      AllIcons.Actions.Help.paintIcon(c, g, x + JBUI.scale(3), y + JBUI.scale(3));

      // Remove decorations
      ((JButton) c).setBorderPainted(false);
      ((JButton) c).setFocusPainted(false);
      ((JButton) c).setContentAreaFilled(false);

      return false;
    } else {
      final Border border = c.getBorder();
      final GraphicsConfig config = GraphicsUtil.setupAAPainting(g);
      if (c.isEnabled() && border != null) {
        final int xOff = 0;
        final int yOff = 0;

        if (c.hasFocus()) {
          g.setPaint(UIUtil.getGradientPaint(0, 0, focusedButtonColor, 0, h, focusedButtonColor));
        } else if (isDefaultButton(c)) {
          g.setPaint(UIUtil.getGradientPaint(0, 0, primaryButtonColor, 0, h, primaryButtonColor));
        } else {
          g.setPaint(UIUtil.getGradientPaint(0, 0, background, 0, h, background));
        }
        final int rad = JBUI.scale(3);
        g.fillRoundRect(xOff, yOff, w, h, rad, rad);
      }
      config.restore();
      return true;
    }

  }

  /**
   * Paint the text of the button
   *
   * @param g
   * @param c
   * @param textRect
   * @param text
   */
  protected void paintText(final Graphics g, final JComponent c, final Rectangle textRect, final String text) {
    if (isHelpButton(c)) {
      return;
    }

    final AbstractButton button = (AbstractButton) c;
    final ButtonModel model = button.getModel();
    Color fg = button.getForeground();
    if (fg instanceof UIResource && button.isSelected()) {
      fg = ObjectUtils.notNull(UIManager.getColor("Button.mt.selectedButtonForeground"), new ColorUIResource(0xbbbbbb));
    }
    g.setColor(fg);

    final FontMetrics metrics = SwingUtilities2.getFontMetrics(c, g);
    final int mnemonicIndex = DarculaLaf.isAltPressed() ? button.getDisplayedMnemonicIndex() : -1;
    if (model.isEnabled()) {
      SwingUtilities2.drawStringUnderlineCharAt(c, g, text, mnemonicIndex,
          textRect.x + getTextShiftOffset(),
          textRect.y + metrics.getAscent() + getTextShiftOffset());
    } else {
      paintDisabledText(g, text, c, textRect, metrics);
    }
  }

}
