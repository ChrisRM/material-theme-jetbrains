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
package com.mallowigi.idea.lafs

import com.intellij.ide.ui.UITheme
import com.intellij.ide.ui.laf.darcula.DarculaLaf
import com.intellij.util.ui.UIUtil
import java.io.IOException
import java.util.function.Function
import javax.swing.UIDefaults
import javax.swing.plaf.ColorUIResource

/**
 * Look and Feel class for Darcula
 *
 */
@Suppress("MagicNumber")
class MTDarculaLaf : DarculaLaf() {
  /**
   * Installs and returns the defaults for darcula specifically
   *
   */
  override fun getDefaults(): UIDefaults {
    val defaults = super.getDefaults()
    MTLafInstaller.installDefaults(defaults)
    // Install darcula defaults
    installDarculaDefaults(defaults)
    // Install material defaults
    MTLafInstaller.installMTDefaults(defaults)
    return defaults
  }

  override fun loadDefaults(defaults: UIDefaults) {
    try {
      DarculaLaf::class.java.getResourceAsStream("$prefix.theme.json").use { stream ->
        assert(stream != null)
        val theme = UITheme.loadFromJson(stream, "Darcula", javaClass.classLoader, Function.identity())
        theme.applyProperties(defaults)
      }
    } catch (e: IOException) {
      log(e)
    }
  }

  /**
   * Install additional Darcula defaults
   *
   * @param defaults of type UIDefaults
   */
  private fun installDarculaDefaults(defaults: UIDefaults) {
    defaults["darcula.primary"] = ColorUIResource(0x3c3f41)
    defaults["darcula.contrastColor"] = ColorUIResource(0x262626)
    defaults["grayFilter"] = UIUtil.GrayFilter(-100, -100, 100)
    defaults["text.grayFilter"] = UIUtil.GrayFilter(-15, -10, 100)
  }

}
