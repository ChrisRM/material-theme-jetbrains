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

import com.intellij.ide.ui.laf.UIThemeBasedLookAndFeelInfo
import com.intellij.ide.ui.laf.darcula.DarculaLaf
import com.intellij.util.xmlb.annotations.Transient
import com.mallowigi.idea.themes.models.MTThemeable
import javax.swing.UIDefaults
import javax.swing.UIManager

/**
 * Look and Feel class for Native Themes
 */
class MTNativeLaf(
  private val theme: MTThemeable,
  @field:Transient private val currentLookAndFeel: UIManager.LookAndFeelInfo,
) : DarculaLaf() {
  /**
   * Installs and returns the defaults for dark lafs
   *
   * @return the defaults (type UIDefaults) of this MTDarkLaf object.
   */
  override fun getDefaults(): UIDefaults {
    val defaults = super.getDefaults()
    MTLafInstaller.installDefaults(defaults)
    // Install material defaults
    MTLafInstaller.installMTDefaults(defaults)
    return defaults
  }

  /**
   * Override Load defaults to load Material Design defaults
   *
   */
  override fun loadDefaults(defaults: UIDefaults) {
    MTLafInstaller.loadDefaults(defaults)
    (currentLookAndFeel as UIThemeBasedLookAndFeelInfo).installTheme(defaults, true)
  }

}
