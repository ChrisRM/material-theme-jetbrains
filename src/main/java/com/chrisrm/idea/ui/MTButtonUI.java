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

package com.chrisrm.idea.ui;

import com.chrisrm.idea.MTConfig;
import com.chrisrm.idea.utils.ColorCycle;
import com.chrisrm.idea.utils.MTUiUtils;
import com.intellij.icons.AllIcons;
import com.intellij.ide.ui.laf.darcula.DarculaLaf;
import com.intellij.ide.ui.laf.darcula.ui.DarculaButtonUI;
import com.intellij.openapi.ui.GraphicsConfig;
import com.intellij.ui.ColorUtil;
import com.intellij.ui.JBColor;
import com.intellij.util.ObjectUtils;
import com.intellij.util.ui.GraphicsUtil;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.UIUtil;
import org.jetbrains.annotations.NotNull;
import sun.swing.SwingUtilities2;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicButtonListener;
import java.awt.*;
import java.awt.event.MouseEvent;

public class MTButtonUI extends DarculaButtonUI {
  private boolean themed;

  public static ComponentUI createUI(final JComponent c) {
    return new MTButtonUI();
  }

  public static boolean isHelpButton(final JComponent button) {
    return button instanceof JButton && "help".equals(button.getClientProperty("JButton.buttonType"));
  }

  public static boolean isDefaultButton(final Component c) {
    return c instanceof JButton && ((JButton) c).isDefaultButton();
  }

  public boolean isThemed() {
    return themed;
  }

  public void setThemed(final boolean themed) {
    this.themed = themed;
  }

  /**
   * Create mouse listeners to simulate an highlighting
   * TODO maybe one day I'll do a riddle
   *
   * @param b
   */
  @Override
  protected BasicButtonListener createButtonListener(final AbstractButton b) {
    return new BasicButtonListener(b) {

      private final ColorCycle colorCycle = new ColorCycle(5, 20);
      private final ColorCycle selectColorCycle = new ColorCycle(5, 20);


      @Override
      public void mouseEntered(final MouseEvent e) {
        if (b instanceof BasicArrowButton) {
          return;
        }
        highlightButton(e);
        super.mouseEntered(e);
      }

      @Override
      public void mouseExited(final MouseEvent e) {
        if (b instanceof BasicArrowButton) {
          return;
        }
        removeHighlight(e);
        super.mouseExited(e);
      }

      @Override
      public void mousePressed(final MouseEvent e) {
        if (b instanceof BasicArrowButton) {
          super.mousePressed(e);
          return;
        }
        highlightButton(e);
        super.mousePressed(e);
      }

      @Override
      public void mouseReleased(final MouseEvent e) {
        if (b instanceof BasicArrowButton) {
          super.mouseReleased(e);
          return;
        }
        removeHighlight(e);
        super.mouseReleased(e);
      }

      private void highlightButton(final MouseEvent e) {
        colorCycle.stop();

        final Component component = e.getComponent();
        final JButton b = (JButton) component;
        colorCycle.setC((JComponent) component);
        selectColorCycle.setC((JComponent) component);

        final Color hoverColor = b.isDefaultButton() ? buttonSelectColor3() : buttonSelectColor1();
        final Color preHoverColor = b.isDefaultButton() ? buttonSelectColor1() : buttonBackground();
        final Color textColor = buttonSelectFg();

        component.setForeground(textColor);
        final Color[] colors = new Color[5];
        for (int i = 0; i < 5; i++) {
          colors[i] = ColorUtil.mix(preHoverColor, hoverColor, i * 0.2);
        }

        if (b.isDefaultButton()) {
          selectColorCycle.start(colors);
        } else {
          colorCycle.start(colors);
        }
      }

      private void removeHighlight(final MouseEvent e) {
        colorCycle.stop();
        selectColorCycle.stop();

        final Component component = e.getComponent();
        final JButton b = (JButton) component;
        colorCycle.setC((JComponent) component);
        selectColorCycle.setC((JComponent) component);

        final Color notHoverColor = b.isDefaultButton() ? buttonSelectColor3() : buttonSelectColor1();
        final Color preNotHoverColor = b.isDefaultButton() ? buttonSelectColor1() : buttonBackground();
        final Color textColor = buttonFg();

        component.setForeground(textColor);
        final Color[] colors = new Color[5];
        for (int i = 0; i < 5; i++) {
          colors[i] = ColorUtil.mix(notHoverColor, preNotHoverColor, i * 0.2);
        }
        if (b.isDefaultButton()) {
          selectColorCycle.start(colors);
        } else {
          colorCycle.start(colors);
        }
      }
    };
  }

  /**
   * Install defaults and set font to bold + 13px
   *
   * @param b
   */
  @Override
  protected void installDefaults(final AbstractButton b) {
    super.installDefaults(b);
    final Color background = isDefaultButton(b) ? buttonSelectColor1() : buttonBackground();
    b.setBackground(background);
    themed = false;

    if (MTConfig.getInstance().isUpperCaseButtons()) {
      b.setFont(b.getFont().deriveFont(Font.BOLD, JBUI.scale(12.0f)));
    } else {
      b.setFont(b.getFont().deriveFont(Font.BOLD, JBUI.scale(13.0f)));
    }
  }

  @NotNull
  private Color buttonBackground() {
    return MTUiUtils.getColor(UIManager.getColor("Button.mt.background"),
        ObjectUtils.notNull(UIManager.getColor("Button.darcula.color1"), new ColorUIResource(0x555a5c)),
        ObjectUtils.notNull(UIManager.getColor("Button.darcula.color1"), new ColorUIResource(0xeeeeee)));
  }

  @NotNull
  private Color buttonColor1() {
    return MTUiUtils.getColor(UIManager.getColor("Button.mt.color1"),
        ObjectUtils.notNull(UIManager.getColor("Button.darcula.color1"), new ColorUIResource(0x555a5c)),
        ObjectUtils.notNull(UIManager.getColor("Button.darcula.color1"), new ColorUIResource(0xeeeeee)));
  }

  @NotNull
  private Color buttonFg() {
    return MTUiUtils.getColor(UIManager.getColor("Button.mt.foreground"),
        ObjectUtils.notNull(UIManager.getColor("Button.darcula.foreground"), new ColorUIResource(0xbbbbbb)),
        ObjectUtils.notNull(UIManager.getColor("Button.darcula.foreground"), new ColorUIResource(0x000000)));
  }

  private Color buttonPrimaryFg() {
    return ColorUtil.brighter(MTUiUtils.getColor(UIManager.getColor("Button.mt.foreground"),
        ObjectUtils.notNull(UIManager.getColor("Button.darcula.foreground"),
            new ColorUIResource(0xbbbbbb)),
        ObjectUtils.notNull(UIManager.getColor("Button.darcula.foreground"),
            new ColorUIResource(0x000000))), 2);
  }

  @NotNull
  private Color buttonSelectFg() {
    return MTUiUtils.getColor(UIManager.getColor("Button.mt.selectedForeground"),
        ObjectUtils.notNull(UIManager.getColor("Button.darcula.selectedButtonForeground"),
            new ColorUIResource(0xbbbbbb)),
        ObjectUtils.notNull(UIManager.getColor("Button.darcula.selectedButtonForeground"),
            new ColorUIResource(0xf0f0f0)));
  }

  @NotNull
  private Color buttonSelectColor1() {
    return MTUiUtils.getColor(UIManager.getColor("Button.mt.selection.color1"),
        ObjectUtils.notNull(UIManager.getColor("Button.darcula.selection.color1"), new ColorUIResource(0x384f6b)),
        ObjectUtils.notNull(UIManager.getColor("Button.darcula.selection.color1"), new ColorUIResource(0x4985e4)));
  }

  @NotNull
  private Color buttonSelectColor2() {
    final Color color = MTUiUtils.getColor(UIManager.getColor("Button.mt.selection.color1"),
        ObjectUtils.notNull(UIManager.getColor("Button.darcula.selection.color1"),
            new ColorUIResource(0x233143)),
        ObjectUtils.notNull(UIManager.getColor("Button.darcula.selection.color1"),
            new ColorUIResource(0x4074c9)));
    return ColorUtil.darker(color, 2);
  }

  @NotNull
  private Color buttonSelectColor3() {
    final Color color = MTUiUtils.getColor(UIManager.getColor("Button.mt.selection.color1"),
        ObjectUtils.notNull(UIManager.getColor("Button.darcula.selection.color1"),
            new ColorUIResource(0x233143)),
        ObjectUtils.notNull(UIManager.getColor("Button.darcula.selection.color1"),
            new ColorUIResource(0x4074c9)));
    return new JBColor(ColorUtil.darker(color, 1), ColorUtil.brighter(color, 2));
  }


  /**
   * Paints additional buttons decorations
   *
   * @param g Graphics
   * @param c button component
   * @return <code>true</code> if it is allowed to continue painting,
   * <code>false</code> if painting should be stopped
   */
  @Override
  protected boolean paintDecorations(final Graphics2D g, final JComponent c) {
    final int w = c.getWidth();
    final int h = c.getHeight();
    final Color background = c.getBackground();
    // Need to set the background because it is not set at installDefaults
    if (isDefaultButton(c) && !isThemed()) {
      c.setBackground(buttonSelectColor1());
      if (c.isFocusable()) {
        setThemed(true);
      }
    }

    final Color buttonColor1 = buttonBackground();
    final Color primaryButtonColor = buttonSelectColor1();

    if (MTButtonUI.isHelpButton(c)) {
      g.setPaint(UIUtil.getGradientPaint(0, 0, buttonColor1, 0, h, buttonColor1));
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
      if (border != null) {
        final int xOff = 0;
        final int yOff = 0;

        if (c.hasFocus()) {
          g.setPaint(UIUtil.getGradientPaint(0, 0, primaryButtonColor, 0, h, primaryButtonColor));
        } else {
          g.setPaint(background);
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
  @Override
  protected void paintText(final Graphics g, final JComponent c, final Rectangle textRect, final String text) {
    if (MTButtonUI.isHelpButton(c)) {
      return;
    }

    final AbstractButton button = (AbstractButton) c;
    final ButtonModel model = button.getModel();
    Color fg = isDefaultButton(c) ? buttonPrimaryFg() : buttonFg();

    if (fg instanceof UIResource && button.isSelected()) {
      fg = ObjectUtils.notNull(UIManager.getColor("Button.mt.selectedButtonForeground"), new ColorUIResource(0xbbbbbb));
    }
    g.setColor(fg);

    final FontMetrics metrics = SwingUtilities2.getFontMetrics(c, g);
    final String textToPrint = MTConfig.getInstance().isUpperCaseButtons() ? text.toUpperCase() : text;
    final int textWidth = metrics.stringWidth(textToPrint);

    final int x = (c.getWidth() - getTextShiftOffset() - textWidth) / 2;
    final int y = textRect.y + metrics.getAscent();

    final int mnemonicIndex = DarculaLaf.isAltPressed() ? button.getDisplayedMnemonicIndex() : -1;
    if (model.isEnabled()) {
      SwingUtilities2.drawStringUnderlineCharAt(c, g, textToPrint, mnemonicIndex, x, y);
    } else {
      paintDisabledText(g, text, c, textRect, metrics);
    }
  }

  /**
   * Paint disabled text
   *
   * @param g
   * @param text
   * @param c
   * @param textRect
   * @param metrics
   */
  @Override
  protected void paintDisabledText(final Graphics g,
                                   final String text,
                                   final JComponent c,
                                   final Rectangle textRect,
                                   final FontMetrics metrics) {
    final String textToPrint = MTConfig.getInstance().isUpperCaseButtons() ? text.toUpperCase() : text;
    final int x = (c.getWidth() - getTextShiftOffset() - metrics.stringWidth(textToPrint)) / 2;

    g.setColor(UIManager.getColor("Button.darcula.disabledText.shadow"));
    SwingUtilities2.drawStringUnderlineCharAt(c, g, text.toUpperCase(), -1, x + 1, textRect.y + metrics.getAscent() + 1);

    g.setColor(UIManager.getColor("Button.disabledText"));
    SwingUtilities2.drawStringUnderlineCharAt(c, g, text.toUpperCase(), -1, x, textRect.y + metrics.getAscent());
  }
}
