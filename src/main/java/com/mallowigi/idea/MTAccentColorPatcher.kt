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
package com.mallowigi.idea

import com.intellij.ui.ColorUtil
import com.intellij.util.SVGLoader.SvgElementColorPatcher
import com.intellij.util.SVGLoader.SvgElementColorPatcherProvider
import com.mallowigi.idea.config.application.MTConfig
import org.jetbrains.annotations.ApiStatus
import org.w3c.dom.Element
import javax.swing.plaf.ColorUIResource

/**
 * Patch icons with accent color
 *
 */
@Suppress("UnstableApiUsage", "HardCodedStringLiteral")
class MTAccentColorPatcher internal constructor() : SvgElementColorPatcherProvider {
  init {
    refreshColors()
  }

  @Suppress("StringLiteralDuplication")
  @ApiStatus.Internal
  override fun forPath(path: String?): SvgElementColorPatcher {
    return object : SvgElementColorPatcher {
      override fun patchColors(svg: Element) {
        // Specifically target other props than AtomMaterial Plugin
        val accentProp = svg.getAttribute("accent")
        val themeProp = svg.getAttribute("theme")
        val accentHexColor = ColorUtil.toHex(accentColor)
        val themeHexColor = ColorUtil.toHex(themeColor)

        when {
          "true" == accentProp || "fill" == accentProp -> svg.setAttribute("fill", "#$accentHexColor")
          "stroke" == accentProp                       -> svg.setAttribute("stroke", "#$accentHexColor")
          "true" == themeProp || "fill" == themeProp   -> svg.setAttribute("fill", "#$themeHexColor")
          "stroke" == themeProp                        -> svg.setAttribute("stroke", "#$themeHexColor")
        }

        val nodes = svg.childNodes
        val length = nodes.length
        for (i in 0 until length) {
          val item = nodes.item(i)
          if (item is Element) patchColors(item)
        }
      }

      override fun digest(): ByteArray? = null
    }
  }

  companion object {
    private val CONFIG = MTConfig.getInstance()
    private var accentColor: ColorUIResource = getAccentColor()
    private var themeColor: ColorUIResource = getThemeColor()

    private fun getAccentColor(): ColorUIResource = ColorUIResource(ColorUtil.fromHex(CONFIG.accentColor))

    private fun getThemeColor(): ColorUIResource = ColorUIResource(CONFIG.selectedTheme.theme.primaryColor)

    private fun refreshColors() {
      accentColor = getAccentColor()
      themeColor = getThemeColor()
    }
  }
}
