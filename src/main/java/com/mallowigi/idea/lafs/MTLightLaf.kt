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

import com.intellij.ide.ui.laf.IntelliJLaf
import com.intellij.util.ui.UIUtil
import com.intellij.util.xmlb.annotations.Transient
import com.mallowigi.idea.messages.MaterialThemeBundle.message
import com.mallowigi.idea.themes.models.MTThemeable
import javax.swing.UIDefaults
import javax.swing.plaf.ColorUIResource

/**
 * Look and Feel class for Material Light themes
 *
 */
@Suppress("MagicNumber")
class MTLightLaf(theme: MTThemeable) : IntelliJLaf() {
  /**
   * Service to install properties in UIManager
   */
  @Transient
  private val mtLafInstaller: MTLafInstaller

  /**
   * Represents a Material Light look and feel
   *
   * @param theme of type MTThemeable
   */
  init {
    mtLafInstaller = MTLafInstaller(theme)
  }

  /**
   * Install and returns the defaults for light lafs
   *
   * @return the defaults (type UIDefaults) of this MTLightLaf object.
   */
  override fun getDefaults(): UIDefaults {
    val defaults = super.getDefaults()
    MTLafInstaller.installDefaults(defaults)
    // Install light defaults
    installLightDefaults(defaults)
    // Install material defaults
    MTLafInstaller.installMTDefaults(defaults)
    return defaults
  }

  override fun getDescription(): String = message("themes.light.material")

  override fun getPrefix(): String = mtLafInstaller.prefix

  override fun loadDefaults(defaults: UIDefaults): Unit = MTLafInstaller.loadDefaults(defaults)

  /**
   * Install additional light theme defaults
   *
   * @param defaults of type UIDefaults
   */
  private fun installLightDefaults(defaults: UIDefaults) {
    defaults["intellijlaf.primary"] = ColorUIResource(0xe8e8e8)
    defaults["intellijlaf.contrastColor"] = ColorUIResource(0xEEEEEE)
    defaults["grayFilter"] = UIUtil.GrayFilter(80, -35, 100)
    defaults["text.grayFilter"] = UIUtil.GrayFilter(20, 0, 100)
  }
}
