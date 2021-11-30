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

import com.intellij.icons.AllIcons;
import com.intellij.ide.ui.laf.darcula.DarculaLaf;
import com.intellij.ide.ui.laf.darcula.ui.DarculaButtonUI;
import com.intellij.openapi.actionSystem.ex.ComboBoxAction;
import com.intellij.openapi.actionSystem.impl.segmentedActionBar.SegmentedActionToolbarComponent;
import com.intellij.openapi.ui.GraphicsConfig;
import com.intellij.ui.ColorUtil;
import com.intellij.ui.JBColor;
import com.intellij.ui.scale.JBUIScale;
import com.intellij.util.ui.GraphicsUtil;
import com.intellij.util.ui.JBInsets;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.UIUtil;
import com.intellij.util.ui.UIUtilities;
import com.mallowigi.idea.config.application.MTConfig;
import com.mallowigi.idea.utils.MTUI;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import sun.swing.SwingUtilities2;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicButtonListener;
import java.awt.*;
import java.util.Locale;

@SuppressWarnings({"NonThreadSafeLazyInitialization",
  "StaticVariableMayNotBeInitialized",
  "StaticVariableUsedBeforeInitialization",
  "WeakerAccess",
  "StaticMethodOnlyUsedInOneClass",
  "MagicNumber",
  "NegativelyNamedBooleanVariable",
  "ParameterNameDiffersFromOverriddenParameter",
  "StandardVariableNames",
  "DuplicatedCode"})
public final class MTButtonUI extends DarculaButtonUI {
  private static final int HELP_BUTTON_DIAMETER = JBUI.scale(22);
  private static final int MINIMUM_BUTTON_WIDTH = JBUI.scale(64);
  private static final int HORIZONTAL_PADDING = JBUI.scale(20);
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
  private boolean isNotThemed = true;

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

  @NotNull
  static Color buttonBg() {
    if (buttonBg == null) {
      buttonBg = MTUI.Button.getBackgroundColor();
      if (MTConfig.getInstance().isBorderedButtons()) {
        buttonBg = MTUI.Panel.getBackground();
      }
    }
    return buttonBg;
  }

  @NotNull
  static Color buttonFg() {
    if (buttonFg == null) {
      buttonFg = MTUI.Button.getForegroundColor();
      if (MTConfig.getInstance().isBorderedButtons()) {
        buttonFg = MTUI.Panel.getAccentColor();
      }
    }
    return buttonFg;
  }

  @NotNull
  static Color primaryButtonBg() {
    if (primaryButtonBg == null) {
      primaryButtonBg = MTUI.Button.getPrimaryBackgroundColor();
      if (MTConfig.getInstance().isBorderedButtons()) {
        primaryButtonBg = MTUI.Panel.getBackground();
      }
    }
    return primaryButtonBg;
  }

  private static Color primaryButtonFg() {
    if (primaryButtonFg == null) {
      primaryButtonFg = MTUI.Button.getPrimaryForegroundColor();
      if (MTConfig.getInstance().isBorderedButtons()) {
        primaryButtonFg = MTUI.Panel.getAccentColor();
      }
    }
    return primaryButtonFg;
  }

  @NotNull
  private static Color selectedButtonBg() {
    if (selectedButtonBg == null) {
      selectedButtonBg = MTUI.Button.getSelectedBackgroundColor();
      if (MTConfig.getInstance().isBorderedButtons()) {
        selectedButtonBg = MTUI.Panel.getBackground();
      }
    }
    return selectedButtonBg;
  }

  @NotNull
  static Color selectedButtonFg() {
    if (selectedButtonFg == null) {
      selectedButtonFg = MTUI.Button.getSelectedForegroundColor();
      if (MTConfig.getInstance().isBorderedButtons()) {
        selectedButtonFg = MTUI.Panel.getAccentColor();
      }
    }
    return selectedButtonFg;
  }

  @NotNull
  static Color primaryButtonHoverColor() {
    if (primaryButtonHover == null) {
      final Color color = primaryButtonBg();
      primaryButtonHover = new JBColor(ColorUtil.darker(color, 2), ColorUtil.brighter(color, 2));
      if (MTConfig.getInstance().isBorderedButtons()) {
        primaryButtonHover = ColorUtil.mix(MTUI.Panel.getBackground(), MTUI.Panel.getAccentColor(), 0.25);
      }
    }
    return primaryButtonHover;
  }

  @NotNull
  static Color buttonHoverColor() {
    if (buttonHover == null) {
      final Color color = selectedButtonBg();
      buttonHover = new JBColor(ColorUtil.darker(color, 2), ColorUtil.brighter(color, 2));
      if (MTConfig.getInstance().isBorderedButtons()) {
        buttonHover = ColorUtil.mix(MTUI.Panel.getBackground(), MTUI.Panel.getAccentColor(), 0.25);
      }
    }
    return buttonHover;
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
    button.setBackground(MTUI.Panel.getBackground());

    return false;
  }

  /**
   * Install defaults and set font to bold + 13px
   */
  @Override
  public void installDefaults(final AbstractButton button) {
    super.installDefaults(button);
    isNotThemed = true;
    button.setRolloverEnabled(true);

    if (MTConfig.getInstance().isUpperCaseButtons()) {
      button.setFont(button.getFont().deriveFont(Font.BOLD, JBUIScale.scale(12.0F)));
    } else {
      button.setFont(button.getFont().deriveFont(Font.BOLD, JBUIScale.scale(13.0F)));
    }
  }

  @Override
  protected int textIconGap() {
    return JBUI.scale(24);
  }

  /**
   * Paints additional buttons decorations
   *
   * @param g Graphics
   * @param c button component
   * @return {@code true} if it is allowed to continue painting,
   * {@code false} if painting should be stopped
   */
  @SuppressWarnings({"MethodWithMultipleReturnPoints",
    "java:S1142"})
  @Override
  protected boolean paintDecorations(final Graphics2D g, final JComponent c) {
    if (!((AbstractButton) c).isContentAreaFilled()) {
      return true;
    }

    final int w = c.getWidth();
    final int h = c.getHeight();
    final Color background = c.getBackground();
    // Need to set the background because it is not set at installDefaults
    if (isNotThemed && isDefaultButton(c)) {
      c.setBackground(primaryButtonBg());
      isNotThemed = false;
    }

    if (SegmentedActionToolbarComponent.Companion.isCustomBar(c)) {
      return SegmentedActionToolbarComponent.Companion.paintButtonDecorations(g, c, buttonBg());
    }

    final Rectangle r = new Rectangle(c.getSize());
    JBInsets.removeFrom(r, isSmallVariant(c) ? c.getInsets() : JBUI.insets(1));

    final Paint overriddenColor = (Paint) c.getClientProperty("JButton.backgroundColor");
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

      if (overriddenColor != null) {
        g.setPaint(overriddenColor);
      }

      final int rad = JBUI.scale(3);
      g.fillRoundRect(xOff, yOff, w, h, rad, rad);
      config.restore();
      return true;
    }
  }

  @SuppressWarnings("MethodOverridesStaticMethodOfSuperclass")
  public static boolean isSmallVariant(final Component c) {
    if (!(c instanceof AbstractButton)) {
      return false;
    }

    final AbstractButton b = (AbstractButton) c;
    final boolean smallVariant = b.getClientProperty("ActionToolbar.smallVariant") == Boolean.TRUE;
    final ComboBoxAction a = (ComboBoxAction) b.getClientProperty("styleCombo");

    return smallVariant || (a != null && a.isSmallVariant());
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
    final Color overriddenColor = (Color) button.getClientProperty("JButton.textColor");

    Color fg = isDefaultButton(c) ? primaryButtonFg() : buttonFg();

    if ((fg instanceof UIResource && button.isSelected()) || model.isRollover()) {
      fg = selectedButtonFg();
    }
    g.setColor(overriddenColor != null ? overriddenColor : fg);

    final FontMetrics metrics = UIUtilities.getFontMetrics(c, g);
    final String textToPrint = MTConfig.getInstance().isUpperCaseButtons() ? text.toUpperCase(Locale.ENGLISH) : text;
    final int textWidth = metrics.stringWidth(textToPrint);

    final int x = (c.getWidth() - getTextShiftOffset() - textWidth) / 2;
    final int y = textRect.y + metrics.getAscent();

    final int mnemonicIndex = DarculaLaf.isAltPressed() ? button.getDisplayedMnemonicIndex() : -1;
    if (model.isEnabled()) {
      UIUtilities.drawStringUnderlineCharAt(c, g, textToPrint, mnemonicIndex, x, y);
    } else {
      paintDisabledText(g, text, c, textRect, metrics);
    }
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
      final int width = isComboAction(c) ?
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

  /**
   * Create mouse listeners to simulate an highlighting
   */
  @Override
  protected BasicButtonListener createButtonListener(final AbstractButton b) {
    return new MTButtonHighlighter(b);
  }

  @Override
  protected void paintIcon(final Graphics g, final JComponent c, final Rectangle iconRect) {
    final Rectangle newIconRect = new Rectangle(iconRect.getBounds());
    super.paintIcon(g, c, newIconRect);
  }

}
