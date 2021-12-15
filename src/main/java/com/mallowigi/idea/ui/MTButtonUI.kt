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
package com.mallowigi.idea.ui

import com.intellij.icons.AllIcons
import com.intellij.ide.ui.laf.darcula.DarculaLaf
import com.intellij.ide.ui.laf.darcula.ui.DarculaButtonUI
import com.intellij.openapi.actionSystem.ex.ComboBoxAction
import com.intellij.openapi.actionSystem.impl.segmentedActionBar.SegmentedActionToolbarComponent.Companion.isCustomBar
import com.intellij.openapi.actionSystem.impl.segmentedActionBar.SegmentedActionToolbarComponent.Companion.paintButtonDecorations
import com.intellij.ui.ColorUtil
import com.intellij.ui.JBColor
import com.intellij.ui.scale.JBUIScale
import com.intellij.util.ui.GraphicsUtil
import com.intellij.util.ui.JBInsets
import com.intellij.util.ui.JBUI
import com.intellij.util.ui.UIUtil
import com.intellij.util.ui.UIUtilities
import com.mallowigi.idea.config.application.MTConfig
import com.mallowigi.idea.utils.MTUI
import java.awt.Color
import java.awt.Component
import java.awt.Dimension
import java.awt.Font
import java.awt.FontMetrics
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.Paint
import java.awt.Rectangle
import javax.swing.AbstractButton
import javax.swing.JComponent
import javax.swing.plaf.UIResource
import kotlin.math.max

/**
 * Material Buttons
 *
 */
class MTButtonUI : DarculaButtonUI() {
  private var isNotThemed = true

  /**
   * Install defaults and set font to bold + 13px
   */
  @Suppress("MagicNumber")
  override fun installDefaults(button: AbstractButton) {
    super.installDefaults(button)
    isNotThemed = true
    button.isRolloverEnabled = true

    button.font = when {
      MTConfig.getInstance().isUpperCaseButtons -> button.font.deriveFont(Font.BOLD, JBUIScale.scale(12.0F))
      else                                      -> button.font.deriveFont(Font.BOLD, JBUIScale.scale(13.0F))
    }
  }

  /**
   * Gap between text and icon
   *
   */
  override fun textIconGap(): Int = JBUI.scale(TEXT_ICON_GAP)

  /**
   * Paints additional buttons decorations
   *
   * @param g Graphics
   * @param c button component
   * @return `true` if it is allowed to continue painting,
   * `false` if painting should be stopped
   */
  override fun paintDecorations(g: Graphics2D, c: JComponent): Boolean {
    if (!(c as AbstractButton).isContentAreaFilled) return true

    val w = c.getWidth()
    val h = c.getHeight()
    val background = c.getBackground()

    if (isCustomBar(c)) return paintButtonDecorations(g, c, buttonBg())

    // Need to set the background because it is not set at installDefaults
    if (isNotThemed && isDefaultButton(c)) {
      c.setBackground(primaryButtonBg())
      isNotThemed = false
    }

    val r = Rectangle(c.getSize())
    JBInsets.removeFrom(r, if (isSmallVariant(c)) c.getInsets() else JBUI.insets(1))

    val overriddenColor = c.getClientProperty("JButton.backgroundColor") as? Paint
    val backgroundColor = buttonBg()
    val focusedColor = primaryButtonHoverColor()

    return when {
      UIUtil.isHelpButton(c) -> paintHelpIcon(g, c, w, h, backgroundColor)
      else                   -> {
        val config = GraphicsUtil.setupAAPainting(g)
        val xOff = 0
        val yOff = 0

        when {
          c.hasFocus() -> g.paint = focusedColor
          else         -> g.paint = background
        }
        // override color if needed
        if (overriddenColor != null) g.paint = overriddenColor

        val rad = JBUI.scale(BUTTON_RADIUS)
        g.fillRoundRect(xOff, yOff, w, h, rad, rad)
        config.restore()
        true
      }
    }
  }

  /**
   * Paint the text of the button
   */
  override fun paintText(g: Graphics, c: JComponent, textRect: Rectangle, text: String) {
    if (UIUtil.isHelpButton(c)) return

    val button = c as AbstractButton
    val model = button.model

    // Determine the button foreground
    val overriddenColor = button.getClientProperty("JButton.textColor") as? Color
    var fg = buttonForeground(c)

    // if button is selected or hovered
    if (fg is UIResource && button.isSelected || model.isRollover) fg = selectedButtonFg()
    // sets the color
    g.color = overriddenColor ?: fg

    // Set the text to uppercase if needed
    val metrics = UIUtilities.getFontMetrics(c, g)
    val textToPrint = if (MTConfig.getInstance().isUpperCaseButtons) text.uppercase() else text

    val textWidth = metrics.stringWidth(textToPrint)
    val x = (c.getWidth() - textShiftOffset - textWidth) / 2
    val y = textRect.y + metrics.ascent
    val mnemonicIndex = if (DarculaLaf.isAltPressed()) button.displayedMnemonicIndex else -1

    if (model.isEnabled) {
      UIUtilities.drawStringUnderlineCharAt(c, g, textToPrint, mnemonicIndex, x, y)
    } else {
      paintDisabledText(g, text, c, textRect, metrics)
    }
  }

  /**
   * Button foreground
   *
   */
  private fun buttonForeground(c: JComponent) = if (isDefaultButton(c)) primaryButtonFg() else buttonFg()

  /**
   * Paint disabled text
   */
  override fun paintDisabledText(
    g: Graphics,
    text: String,
    c: JComponent,
    textRect: Rectangle,
    metrics: FontMetrics,
  ) {
    val textToPrint = if (MTConfig.getInstance().isUpperCaseButtons) text.uppercase() else text
    val x = (c.width - textShiftOffset - metrics.stringWidth(textToPrint)) / 2

    // draw text with shadow
    g.color = MTUI.Button.disabledShadowColor
    UIUtilities.drawStringUnderlineCharAt(c, g, textToPrint, -1, x + 1, textRect.y + metrics.ascent + 1)
    g.color = MTUI.Button.disabledColor
    UIUtilities.drawStringUnderlineCharAt(c, g, textToPrint, -1, x, textRect.y + metrics.ascent)
  }

  /**
   * Compute button size
   *
   * @param c the component
   * @param prefSize the preferred size
   * @return the size
   */
  override fun getDarculaButtonSize(c: JComponent, prefSize: Dimension): Dimension {
    val insets = c.insets

    // If help or square button
    return when {
      UIUtil.isHelpButton(c) || isSquare(c) -> {
        val helpDiam = HELP_BUTTON_DIAMETER
        Dimension(
          max(prefSize.width, helpDiam + insets.left + insets.right),
          max(prefSize.height, helpDiam + insets.top + insets.bottom)
        )
      }
      else                                  -> {
        val width = when {
          isComboAction(c) -> prefSize.width
          else             -> max(
            (HORIZONTAL_PADDING shl 1) + prefSize.width,
            MINIMUM_BUTTON_WIDTH + insets.left + insets.right
          )
        }
        val height = max(prefSize.height, minimumHeight + insets.top + insets.bottom)
        Dimension(width, height)
      }
    }
  }

  /**
   * Create mouse listeners to simulate an highlighting
   */
  override fun createButtonListener(b: AbstractButton): MTButtonHighlighter = MTButtonHighlighter(b)

  /**
   * Paint icon
   *
   */
  override fun paintIcon(g: Graphics, c: JComponent, iconRect: Rectangle) {
    val newIconRect = Rectangle(iconRect.bounds)
    super.paintIcon(g, c, newIconRect)
  }

  companion object {
    private val HELP_BUTTON_DIAMETER = JBUI.scale(22)
    private val MINIMUM_BUTTON_WIDTH = JBUI.scale(64)
    private val HORIZONTAL_PADDING = JBUI.scale(20)
    private const val TEXT_ICON_GAP = 24
    private const val BUTTON_RADIUS = 3

    private var primaryButtonBg: Color? = null
    private var primaryButtonFg: Color? = null
    private var primaryButtonHover: Color? = null
    private var buttonHover: Color? = null
    private var selectedButtonFg: Color? = null
    private var selectedButtonBg: Color? = null
    private var buttonFg: Color? = null
    private var buttonBg: Color? = null

    /**
     * Create Material Button
     *
     */
    @Suppress("UNUSED_PARAMETER", "HardCodedStringLiteral")
    @JvmStatic
    fun createUI(component: JComponent): MTButtonUI = MTButtonUI()

    /**
     * Reset cached values
     *
     */
    fun resetCache() {
      selectedButtonFg = null
      selectedButtonBg = null
      buttonBg = null
      buttonFg = null
      buttonHover = null
      primaryButtonFg = null
      primaryButtonBg = null
      primaryButtonHover = null
    }

    /**
     * Buttons background
     *
     */
    fun buttonBg(): Color {
      if (buttonBg == null) {
        buttonBg = if (MTConfig.getInstance().isBorderedButtons) MTUI.Panel.background else MTUI.Button.backgroundColor
      }
      return buttonBg!!
    }

    /**
     * Buttons foreground
     */
    fun buttonFg(): Color {
      if (buttonFg == null) {
        buttonFg = if (MTConfig.getInstance().isBorderedButtons) MTUI.Panel.accentColor else MTUI.Button.foregroundColor
      }
      return buttonFg!!
    }

    /**
     * Primary buttons background
     */
    fun primaryButtonBg(): Color {
      if (primaryButtonBg == null) {
        primaryButtonBg =
          if (MTConfig.getInstance().isBorderedButtons) MTUI.Panel.background else MTUI.Button.primaryBackgroundColor
      }
      return primaryButtonBg!!
    }

    /**
     * Primary buttons foreground
     *
     */
    private fun primaryButtonFg(): Color? {
      if (primaryButtonFg == null) {
        primaryButtonFg =
          if (MTConfig.getInstance().isBorderedButtons) MTUI.Panel.accentColor else MTUI.Button.primaryForegroundColor
      }
      return primaryButtonFg
    }

    /**
     * Selected buttons background
     *
     */
    private fun selectedButtonBg(): Color {
      if (selectedButtonBg == null) {
        selectedButtonBg =
          if (MTConfig.getInstance().isBorderedButtons) MTUI.Panel.background else MTUI.Button.selectedBackgroundColor
      }
      return selectedButtonBg!!
    }

    /**
     * Selected buttons foreground
     */
    fun selectedButtonFg(): Color {
      if (selectedButtonFg == null) {
        selectedButtonFg =
          if (MTConfig.getInstance().isBorderedButtons) MTUI.Panel.accentColor else MTUI.Button.selectedForegroundColor
      }
      return selectedButtonFg!!
    }

    /**
     * Primary buttons hover
     */
    @Suppress("MagicNumber")
    fun primaryButtonHoverColor(): Color {
      if (primaryButtonHover == null) {
        val color = primaryButtonBg()
        primaryButtonHover = JBColor(ColorUtil.darker(color, 2), ColorUtil.brighter(color, 2))
        if (MTConfig.getInstance().isBorderedButtons) {
          primaryButtonHover = ColorUtil.mix(MTUI.Panel.background, MTUI.Panel.accentColor, 0.25)
        }
      }
      return primaryButtonHover!!
    }

    /**
     * Buttons hover
     */
    @Suppress("MagicNumber")
    fun buttonHoverColor(): Color {
      if (buttonHover == null) {
        val color = selectedButtonBg()
        buttonHover = JBColor(ColorUtil.darker(color, 2), ColorUtil.brighter(color, 2))
        if (MTConfig.getInstance().isBorderedButtons) {
          buttonHover = ColorUtil.mix(MTUI.Panel.background, MTUI.Panel.accentColor, 0.25)
        }
      }
      return buttonHover!!
    }

    @Suppress("MagicNumber")
    private fun paintHelpIcon(g: Graphics2D, component: JComponent, w: Int, h: Int, buttonColor1: Color): Boolean {
      g.paint = UIUtil.getGradientPaint(0f, 0f, buttonColor1, 0f, h.toFloat(), buttonColor1)
      val off = JBUI.scale(22)
      val x = (w - off) / 2
      val y = (h - off) / 2
      g.fillOval(x, y, off, off)
      AllIcons.Actions.Help.paintIcon(component, g, x + JBUI.scale(3), y + JBUI.scale(3))

      // Remove decorations
      val button = component as AbstractButton
      button.isBorderPainted = false
      button.isFocusPainted = false
      button.background = MTUI.Panel.background
      return false
    }

    /**
     * Small variant?
     */
    fun isSmallVariant(c: Component?): Boolean {
      if (c !is AbstractButton) return false

      val smallVariant = c.getClientProperty("ActionToolbar.smallVariant") == java.lang.Boolean.TRUE
      val a = c.getClientProperty("styleCombo") as? ComboBoxAction
      return smallVariant || a != null && a.isSmallVariant
    }
  }
}
