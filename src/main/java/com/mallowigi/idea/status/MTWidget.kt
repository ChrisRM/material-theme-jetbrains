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

package com.mallowigi.idea.status

import com.intellij.openapi.Disposable
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.options.ShowSettingsUtil
import com.intellij.openapi.ui.popup.Balloon
import com.intellij.openapi.util.Disposer
import com.intellij.ui.ColorUtil
import com.intellij.ui.GotItTooltip
import com.intellij.util.ui.BaseButtonBehavior
import com.intellij.util.ui.ImageUtil
import com.intellij.util.ui.JBUI
import com.intellij.util.ui.StartupUiUtil
import com.intellij.util.ui.TimedDeadzone
import com.intellij.util.ui.UIUtil
import com.mallowigi.idea.config.application.MTConfig
import com.mallowigi.idea.messages.MaterialThemeBundle
import com.mallowigi.idea.utils.MTUI
import com.mallowigi.idea.utils.MTUiUtils
import java.awt.Dimension
import java.awt.Font
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.GraphicsEnvironment
import java.awt.Image
import java.awt.event.MouseEvent
import java.awt.font.TextAttribute
import java.awt.image.BufferedImage
import java.text.AttributedString
import javax.swing.JButton

/**
 * Material Theme widget
 *
 */
class MTWidget : JButton(), Disposable {
  private val mtConfig: MTConfig = MTConfig.getInstance()
  private val widgetFont = getWidgetFont()

  /**
   * Cache image of the widget
   */
  var myBufferedImage: Image? = null

  init {
    object : BaseButtonBehavior(this, TimedDeadzone.NULL) {
      override fun execute(e: MouseEvent) =
        ShowSettingsUtil.getInstance()
          .showSettingsDialog(null, MaterialThemeBundle.message("mt.settings.titles.materialTheme"))
    }.setActionTrigger(MouseEvent.MOUSE_PRESSED)
    font = widgetFont
    putClientProperty(MTUI.Button.noBorder, true)

    if (MTUiUtils.SHOW_GOT_IT_TOOLTIP) {
      showGotItTooltip()
    }
  }

  /**
   * Dispose
   *
   */
  override fun dispose(): Unit = Disposer.dispose(this)

  /**
   * Show got it tooltip
   *
   */
  private fun showGotItTooltip() {
    val message = MaterialThemeBundle.message("mt.settings.titles.materialTheme")
    val gotIt = GotItTooltip(
      "NewFeaturesWidget",
      MaterialThemeBundle.message("gotIt.newFeatures.widget"),
      this
    )
      .withHeader(MaterialThemeBundle.message("gotIt.newFeatures.title"))
      .withPosition(Balloon.Position.above)
      .withLink(MaterialThemeBundle.message("show.me")) {
        ShowSettingsUtil.getInstance().showSettingsDialog(null, message)
      }
    if (gotIt.canShow()) {
      ApplicationManager.getApplication().invokeLater { gotIt.show(this, GotItTooltip.TOP_MIDDLE) }
    }
  }

  /**
   * Paint component
   *
   */
  public override fun paintComponent(g: Graphics) {
    val themeName = mtConfig.selectedTheme.theme.themeName
    if (themeName.isEmpty()) return

    val accentColor = ColorUtil.fromHex(mtConfig.accentColor)
    val accentDiameter = JBUI.scale(STATUS_HEIGHT - 2)

    if (myBufferedImage == null) {
      val size = size
      val arcs = Dimension(RADIUS, RADIUS)
      myBufferedImage = ImageUtil.createImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB)

      val g2 = (myBufferedImage ?: return).graphics.create() as Graphics2D
      val fontMetrics = g.fontMetrics

      g2.setRenderingHints(MTUiUtils.getHints())
      val nameWidth = fontMetrics.charsWidth(themeName.toCharArray(), 0, themeName.length)
      val nameHeight = fontMetrics.ascent
      val attributedString = AttributedString(themeName).also {
        it.addAttribute(TextAttribute.FAMILY, font.family)
        it.addAttribute(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD)
        it.addAttribute(TextAttribute.SIZE, DEFAULT_FONT_SIZE)
      }

      // background
      g2.color = mtConfig.selectedTheme.theme.contrastColor
      g2.fillRoundRect(
        /* x = */ 0,
        /* y = */ 0,
        /* width = */ size.width + accentDiameter - JBUI.scale(arcs.width),
        /* height = */ JBUI.scale(STATUS_HEIGHT),
        /* arcWidth = */ arcs.width,
        /* arcHeight = */ arcs.height
      )

      // label
      g2.color = UIUtil.getLabelForeground()
      g2.font = font
      g2.drawString(
        /* iterator = */ attributedString.iterator,
        /* x = */ (size.width - accentDiameter - nameWidth) / 2,
        /* y = */ nameHeight + (size.height - nameHeight) / 2 - JBUI.scale(1)
      )

      // Accent
      g2.color = accentColor
      g2.fillOval(size.width - JBUI.scale(STATUS_HEIGHT), JBUI.scale(1), accentDiameter, accentDiameter)
      g2.dispose()
    }

    StartupUiUtil.drawImage(
      /* g = */ g,
      /* image = */ myBufferedImage ?: return,
      /* x = */ 0,
      /* y = */ 0,
      /* observer = */ null
    )
  }

  /**
   * Widget's preferred size
   *
   */
  override fun getPreferredSize(): Dimension {
    val themeName = mtConfig.selectedTheme.themeName
    val width = getFontMetrics(widgetFont).charsWidth(
      /* data = */ themeName.toCharArray(),
      /* off = */ 0,
      /* len = */ themeName.length
    ) + 2 * STATUS_PADDING
    val accentDiameter = JBUI.scale(STATUS_HEIGHT)
    return Dimension(width + 2 * accentDiameter, accentDiameter)
  }

  /**
   * Minimum size
   *
   */
  override fun getMinimumSize(): Dimension = preferredSize

  /**
   * Maximum size
   *
   */
  override fun getMaximumSize(): Dimension = preferredSize

  /**
   * Set widget font to Roboto
   *
   */
  private fun getWidgetFont(): Font {
    val e = GraphicsEnvironment.getLocalGraphicsEnvironment()
    val fonts = e.allFonts

    for (font in fonts) {
      if (font.fontName == MTConfig.DEFAULT_FONT) {
        val attributes: MutableMap<TextAttribute, Any?> = emptyMap<TextAttribute, Any?>().toMutableMap()
        attributes[TextAttribute.WEIGHT] = TextAttribute.WEIGHT_BOLD
        attributes[TextAttribute.SIZE] = JBUI.scale(DEFAULT_FONT_SIZE)
        return font.deriveFont(attributes)
      }
    }
    return JBUI.Fonts.create(Font.DIALOG, DEFAULT_FONT_SIZE)
  }

  companion object {
    private val DEFAULT_FONT_SIZE = JBUI.scale(11)
    private val STATUS_PADDING = JBUI.scale(4)
    private val STATUS_HEIGHT = JBUI.scale(16)
    private val RADIUS = JBUI.scale(8)
  }
}
