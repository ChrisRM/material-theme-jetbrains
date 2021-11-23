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
import com.intellij.openapi.wm.CustomStatusBarWidget
import com.intellij.openapi.wm.StatusBar
import com.intellij.ui.ColorUtil
import com.intellij.ui.GotItTooltip
import com.intellij.util.ui.BaseButtonBehavior
import com.intellij.util.ui.ImageUtil
import com.intellij.util.ui.JBUI
import com.intellij.util.ui.StartupUiUtil
import com.intellij.util.ui.TimedDeadzone
import com.intellij.util.ui.UIUtil
import com.mallowigi.idea.config.application.MTConfig
import com.mallowigi.idea.listeners.AccentsListener
import com.mallowigi.idea.listeners.ConfigNotifier
import com.mallowigi.idea.listeners.MTTopics
import com.mallowigi.idea.listeners.ThemeListener
import com.mallowigi.idea.messages.MaterialThemeBundle.message
import com.mallowigi.idea.utils.MTUI
import com.mallowigi.idea.utils.MTUiUtils
import org.jetbrains.annotations.NonNls
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
import javax.swing.JComponent

internal class MTStatusWidget : CustomStatusBarWidget {
  private val mtWidget: MTWidget = MTWidget()
  private val connect = ApplicationManager.getApplication().messageBus.connect(this)

  override fun ID(): @NonNls String = "MTStatusBarWidget"

  override fun install(statusBar: StatusBar) {
    connect.subscribe(MTTopics.THEMES, ThemeListener { refresh() })
    connect.subscribe(MTTopics.ACCENTS, AccentsListener { refresh() })
    connect.subscribe(MTTopics.CONFIG, object : ConfigNotifier {
      override fun configChanged(mtConfig: MTConfig) = refresh()
    })
  }

  override fun dispose() {
    Disposer.dispose(this)
    myBufferedImage = null
    connect.disconnect()
  }

  private fun refresh() {
    myBufferedImage = null
    mtWidget.isVisible = true
    mtWidget.repaint()
    mtWidget.updateUI()
    mtWidget.toolTipText = MTConfig.getInstance().tooltip
  }

  override fun getComponent(): JComponent = mtWidget

  private class MTWidget : JButton(), Disposable {
    private val mtConfig: MTConfig = MTConfig.getInstance()
    private val widgetFont = getWidgetFont()

    init {
      object : BaseButtonBehavior(this, TimedDeadzone.NULL) {
        override fun execute(e: MouseEvent) {
          ShowSettingsUtil.getInstance().showSettingsDialog(null, MT_SETTINGS_PAGE)
        }
      }.setActionTrigger(MouseEvent.MOUSE_PRESSED)
      font = widgetFont
      putClientProperty(MTUI.Button.NO_BORDER, true)

      if (MTUiUtils.SHOW_GOT_IT_TOOLTIP) {
        showGotItTooltip()
      }
    }

    override fun dispose() {
      // do nothing
    }

    private fun showGotItTooltip() {
      val gotIt = GotItTooltip("NewFeaturesWidget",
                               message("gotIt.newFeatures.widget"),
                               this)
        .withHeader(message("gotIt.newFeatures.title"))
        .withPosition(Balloon.Position.above)
        .withLink("Show me!", Runnable { ShowSettingsUtil.getInstance().showSettingsDialog(null, MT_SETTINGS_PAGE) })
      if (gotIt.canShow()) {
        ApplicationManager.getApplication().invokeLater { gotIt.show(this, GotItTooltip.TOP_MIDDLE) }
      }
    }

    public override fun paintComponent(g: Graphics) {
      val themeName = mtConfig.selectedTheme.theme.name
      if (themeName.isEmpty()) {
        return
      }

      val accentColor = ColorUtil.fromHex(mtConfig.accentColor)
      val accentDiameter = JBUI.scale(STATUS_HEIGHT - 2)

      if (myBufferedImage == null) {
        val size = size
        val arcs = Dimension(8, 8)
        myBufferedImage = ImageUtil.createImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB)
        val g2 = myBufferedImage!!.graphics.create() as Graphics2D
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
        g2.fillRoundRect(0,
                         0,
                         size.width + accentDiameter - JBUI.scale(arcs.width),
                         JBUI.scale(STATUS_HEIGHT),
                         arcs.width,
                         arcs.height)

        // label
        g2.color = UIUtil.getLabelForeground()
        g2.font = font
        g2.drawString(attributedString.iterator, (size.width - accentDiameter - nameWidth) / 2,
                      nameHeight + (size.height - nameHeight) / 2 - JBUI.scale(1))

        // Accent
        g2.color = accentColor
        g2.fillOval(size.width - JBUI.scale(STATUS_HEIGHT), JBUI.scale(1), accentDiameter, accentDiameter)
        g2.dispose()
      }
      StartupUiUtil.drawImage(g, myBufferedImage!!, 0, 0, null)
    }

    override fun getPreferredSize(): Dimension {
      val themeName = mtConfig.selectedTheme.themeName!!
      val width = getFontMetrics(widgetFont).charsWidth(themeName.toCharArray(), 0,
                                                        themeName.length) + 2 * STATUS_PADDING
      val accentDiameter = JBUI.scale(STATUS_HEIGHT)
      return Dimension(width + accentDiameter, accentDiameter)
    }

    override fun getMinimumSize(): Dimension = preferredSize

    override fun getMaximumSize(): Dimension = preferredSize

    companion object {
      private val DEFAULT_FONT_SIZE = JBUI.scale(11)
      private const val STATUS_PADDING = 4
      private const val STATUS_HEIGHT = 16

      private fun getWidgetFont(): Font {
        val e = GraphicsEnvironment.getLocalGraphicsEnvironment()
        val fonts = e.allFonts
        for (font in fonts) {
          if (font.fontName == MTConfig.DEFAULT_FONT) {
            val attributes: MutableMap<TextAttribute, Any?> = HashMap(10)
            attributes[TextAttribute.WEIGHT] = TextAttribute.WEIGHT_BOLD
            attributes[TextAttribute.SIZE] = JBUI.scale(DEFAULT_FONT_SIZE)
            return font.deriveFont(attributes)
          }
        }
        return JBUI.Fonts.create(Font.DIALOG, 12)
      }
    }
  }

  companion object {
    private val MT_SETTINGS_PAGE = message("mt.settings.titles.materialTheme")
    private var myBufferedImage: Image? = null
  }
}
