/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 Chris Magnussen and Elior Boukhobza
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

import com.intellij.icons.AllIcons;
import com.intellij.ide.ui.laf.darcula.DarculaLaf;
import com.intellij.ide.ui.laf.darcula.ui.DarculaButtonUI;
import com.intellij.openapi.ui.GraphicsConfig;
import com.intellij.ui.ColorUtil;
import com.intellij.ui.JBColor;
import com.intellij.ui.scale.JBUIScale;
import com.intellij.util.ui.GraphicsUtil;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.UIUtil;
import com.mallowigi.idea.MTConfig;
import com.mallowigi.idea.utils.ColorCycle;
import com.mallowigi.idea.utils.MTUI;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sun.swing.SwingUtilities2;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicButtonListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Locale;

@SuppressWarnings({"NonThreadSafeLazyInitialization",
  "StaticVariableMayNotBeInitialized",
  "StaticVariableUsedBeforeInitialization",
  "WeakerAccess",
  "StaticMethodOnlyUsedInOneClass",
  "StandardVariableNames",
  "MagicNumber"})
public final class MTButtonUI extends DarculaButtonUI {
  public static final int ICON_MIN_PADDING = JBUI.scale(6);
  private boolean isNotThemed = true;
  @Nullable
  private static Color primaryButtonBg;
  @Nullable
  private static Color primaryButtonFg;
  @Nullable
  private static Color primaryButtonHover;
  @Nullable
  private static Color buttonHover;
  @Nullable
  private static Color selectedButtonFg;
  @Nullable
  private static Color selectedButtonBg;
  @Nullable
  private static Color buttonFg;
  @Nullable
  private static Color buttonBg;

  private static final int HELP_BUTTON_DIAMETER = JBUI.scale(22);
  private static final int MINIMUM_BUTTON_WIDTH = JBUI.scale(64);
  private static final int HORIZONTAL_PADDING = JBUI.scale(20);

  @SuppressWarnings({"MethodOverridesStaticMethodOfSuperclass",
    "unused"})
  public static ComponentUI createUI(final JComponent component) {
    return new MTButtonUI();
  }

  public static void resetCache() {
    selectedButtonFg = null;
    selectedButtonBg = null;
    buttonBg = null;
    buttonFg = null;
    primaryButtonFg = null;
    primaryButtonBg = null;
    primaryButtonHover = null;
  }

  /**
   * Create mouse listeners to simulate an highlighting
   */
  @Override
  protected BasicButtonListener createButtonListener(final AbstractButton b) {
    return new ButtonHighlighter(b);
  }

  @Override
  protected int textIconGap() {
    return JBUI.scale(24);
  }

  /**
   * Install defaults and set font to bold + 13px
   */
  @Override
  public void installDefaults(final AbstractButton b) {
    super.installDefaults(b);
    //    b.setBackground(isDefaultButton(b) ? primaryButtonBg() : buttonBg());
    isNotThemed = true;
    b.setRolloverEnabled(true);

    if (MTConfig.getInstance().isUpperCaseButtons()) {
      b.setFont(b.getFont().deriveFont(Font.BOLD, JBUIScale.scale(12.0f)));
    } else {
      b.setFont(b.getFont().deriveFont(Font.BOLD, JBUIScale.scale(13.0f)));
    }
  }

  @NotNull
  static Color buttonBg() {
    if (buttonBg == null) {
      buttonBg = MTUI.Button.getBackgroundColor();
    }
    return buttonBg;
  }

  @NotNull
  static Color buttonFg() {
    if (buttonFg == null) {
      buttonFg = MTUI.Button.getForegroundColor();
    }
    return buttonFg;
  }

  @NotNull
  static Color primaryButtonBg() {
    if (primaryButtonBg == null) {
      primaryButtonBg = MTUI.Button.getPrimaryBackgroundColor();
    }
    return primaryButtonBg;
  }

  private static Color primaryButtonFg() {
    if (primaryButtonFg == null) {
      primaryButtonFg = MTUI.Button.getPrimaryForegroundColor();
    }
    return primaryButtonFg;
  }

  @NotNull
  private static Color selectedButtonBg() {
    if (selectedButtonBg == null) {
      selectedButtonBg = MTUI.Button.getSelectedBackgroundColor();
    }
    return selectedButtonBg;
  }

  @NotNull
  static Color selectedButtonFg() {
    if (selectedButtonFg == null) {
      selectedButtonFg = MTUI.Button.getSelectedForegroundColor();
    }
    return selectedButtonFg;
  }

  @NotNull
  static Color primaryButtonHoverColor() {
    if (primaryButtonHover == null) {
      final Color color = primaryButtonBg();
      primaryButtonHover = new JBColor(ColorUtil.darker(color, 2), ColorUtil.brighter(color, 2));
    }
    return primaryButtonHover;
  }

  @NotNull
  static Color buttonHoverColor() {
    if (buttonHover == null) {
      final Color color = selectedButtonBg();
      buttonHover = new JBColor(ColorUtil.darker(color, 2), ColorUtil.brighter(color, 2));
    }
    return buttonHover;
  }

  /**
   * Paints additional buttons decorations
   *
   * @param g Graphics
   * @param c button component
   * @return {@code true} if it is allowed to continue painting,
   * {@code false} if painting should be stopped
   */
  @Override
  protected boolean paintDecorations(final Graphics2D g, final JComponent c) {
    final int w = c.getWidth();
    final int h = c.getHeight();
    final Color background = c.getBackground();
    // Need to set the background because it is not set at installDefaults
    if (isDefaultButton(c) && isNotThemed) {
      c.setBackground(primaryButtonBg());
      if (c.isFocusable()) {
        isNotThemed = false;
      }
    }

    final Color backgroundColor = buttonBg();
    final Color focusedColor = primaryButtonHoverColor();

    if (UIUtil.isHelpButton(c)) {
      return paintHelpIcon(g, c, w, h, backgroundColor);
    } else {
      final GraphicsConfig config = GraphicsUtil.setupAAPainting(g);
      final int xOff = 0;
      final int yOff = 0;

      if (c.hasFocus()) {
        g.setPaint(focusedColor);
      } else {
        g.setPaint(background);
      }
      final int rad = JBUI.scale(3);
      g.fillRoundRect(xOff, yOff, w, h, rad, rad);
      config.restore();
      return true;
    }
  }

  @SuppressWarnings({"BooleanMethodNameMustStartWithQuestion",
    "SameReturnValue"})
  private static boolean paintHelpIcon(final Graphics2D g, final JComponent component, final int w, final int h, final Color buttonColor1) {
    g.setPaint(UIUtil.getGradientPaint(0, 0, buttonColor1, 0, h, buttonColor1));
    final int off = JBUI.scale(22);
    final int x = (w - off) / 2;
    final int y = (h - off) / 2;
    g.fillOval(x, y, off, off);
    AllIcons.Actions.Help.paintIcon(component, g, x + JBUI.scale(3), y + JBUI.scale(3));

    // Remove decorations
    final AbstractButton button = (AbstractButton) component;
    button.setBorderPainted(false);
    button.setFocusPainted(false);
    button.setContentAreaFilled(false);

    return false;
  }

  /**
   * Paint the text of the button
   */
  @Override
  protected void paintText(final Graphics g, final JComponent c, final Rectangle textRect, final String text) {
    if (UIUtil.isHelpButton(c)) {
      return;
    }

    final AbstractButton button = (AbstractButton) c;
    final ButtonModel model = button.getModel();
    Color fg = isDefaultButton(c) ? primaryButtonFg() : buttonFg();

    if (fg instanceof UIResource && button.isSelected()) {
      fg = selectedButtonFg();
    } else if (model.isRollover()) {
      fg = selectedButtonFg();
    }
    g.setColor(fg);

    final FontMetrics metrics = SwingUtilities2.getFontMetrics(c, g);
    final String textToPrint = MTConfig.getInstance().isUpperCaseButtons() ? text.toUpperCase(Locale.ENGLISH) : text;
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

  @Override
  protected void paintIcon(final Graphics g, final JComponent c, final Rectangle iconRect) {
    final Rectangle newIconRect = new Rectangle(iconRect.getBounds());
    newIconRect.x = Math.min(iconRect.x, ICON_MIN_PADDING);
    super.paintIcon(g, c, newIconRect);
  }

  /**
   * Paint disabled text
   */
  @Override
  protected void paintDisabledText(final Graphics g,
                                   final String text,
                                   final JComponent c,
                                   final Rectangle textRect,
                                   final FontMetrics metrics) {
    final String textToPrint = MTConfig.getInstance().isUpperCaseButtons() ? text.toUpperCase(Locale.ENGLISH) : text;
    final int x = (c.getWidth() - getTextShiftOffset() - metrics.stringWidth(textToPrint)) / 2;

    g.setColor(MTUI.Button.getDisabledShadowColor());
    SwingUtilities2.drawStringUnderlineCharAt(c, g, textToPrint, -1, x + 1, textRect.y + metrics.getAscent() + 1);

    g.setColor(MTUI.Button.getDisabledColor());
    SwingUtilities2.drawStringUnderlineCharAt(c, g, textToPrint, -1, x, textRect.y + metrics.getAscent());
  }

  @Override
  protected Dimension getDarculaButtonSize(final JComponent c, final Dimension prefSize) {
    final Insets insets = c.getInsets();
    if (UIUtil.isHelpButton(c) || isSquare(c)) {
      final int helpDiam = HELP_BUTTON_DIAMETER;
      return new Dimension(
        Math.max(prefSize.width, helpDiam + insets.left + insets.right),
        Math.max(prefSize.height, helpDiam + insets.top + insets.bottom)
      );
    } else {
      final int width = getComboAction(c) != null ?
                        prefSize.width :
                        Math.max(
                          (HORIZONTAL_PADDING << 1) + prefSize.width,
                          MINIMUM_BUTTON_WIDTH + insets.left + insets.right
                        );
      final int height = Math.max(
        prefSize.height, getMinimumHeight() + insets.top + insets.bottom
      );

      return new Dimension(width, height);
    }
  }

  private static final class ButtonHighlighter extends BasicButtonListener {

    private final ColorCycle colorCycle;
    private final AbstractButton button;

    ButtonHighlighter(final AbstractButton button) {
      super(button);
      this.button = button;
      colorCycle = new ColorCycle(5, 20);
    }

    @Override
    public void mouseEntered(final MouseEvent e) {
      if (button instanceof BasicArrowButton) {
        return;
      }
      highlightButton(e);
      super.mouseEntered(e);
    }

    @Override
    public void mouseExited(final MouseEvent e) {
      if (button instanceof BasicArrowButton) {
        return;
      }
      removeHighlight(e);
      super.mouseExited(e);
    }

    @Override
    public void mousePressed(final MouseEvent e) {
      if (button instanceof BasicArrowButton) {
        super.mousePressed(e);
        return;
      }
      highlightButton(e);
      super.mousePressed(e);
    }

    @Override
    public void mouseReleased(final MouseEvent e) {
      if (button instanceof BasicArrowButton) {
        super.mouseReleased(e);
        return;
      }
      removeHighlight(e);
      super.mouseReleased(e);
    }

    @SuppressWarnings({"FeatureEnvy",
      "MagicNumber"})
    private void highlightButton(final MouseEvent e) {
      colorCycle.stop();

      final Component component = e.getComponent();
      final JButton jButton = (JButton) component;
      colorCycle.setComponent((JComponent) component);

      final Color hoverColor = jButton.isDefaultButton() ? primaryButtonHoverColor() : buttonHoverColor();
      final Color preHoverColor = jButton.isDefaultButton() ? primaryButtonBg() : buttonBg();
      final Color textColor = selectedButtonFg();

      component.setForeground(textColor);
      final Color[] colors = new Color[5];
      for (int i = 0; i < 5; i++) {
        colors[i] = ColorUtil.mix(preHoverColor, hoverColor, i * 0.2);
      }

      colorCycle.start(colors);
    }

    @SuppressWarnings({"FeatureEnvy",
      "MagicNumber"})
    private void removeHighlight(final MouseEvent e) {
      colorCycle.stop();

      final Component component = e.getComponent();
      final JButton jButton = (JButton) component;
      colorCycle.setComponent((JComponent) component);

      final Color notHoverColor = jButton.isDefaultButton() ? primaryButtonHoverColor() : buttonHoverColor();
      final Color preNotHoverColor = jButton.isDefaultButton() ? primaryButtonBg() : buttonBg();
      final Color textColor = buttonFg();

      component.setForeground(textColor);
      final Color[] colors = new Color[5];
      for (int i = 0; i < 5; i++) {
        colors[i] = ColorUtil.mix(notHoverColor, preNotHoverColor, i * 0.2);
      }
      colorCycle.start(colors);
    }
  }
}
