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

package com.mallowigi.idea.themes

import com.intellij.openapi.application.ApplicationManager
import com.mallowigi.idea.license.MTMainProductLicenseChecker
import com.mallowigi.idea.themes.models.MTThemeable
import java.util.Vector
import javax.swing.Icon

/**
 * Material Theme Collection — Collection containing the pre-bundled and downloaded themes
 *
 */
object MTThemeCollection {
  private val themesMap: MutableMap<String, MTThemeFacade> = emptyMap<String, MTThemeFacade>().toMutableMap()

  init {
    for (theme in MTTheme.values()) {
      themesMap[theme.themeId] = theme
    }
  }

  /**
   * Instance
   */
  val instance: MTThemeCollection
    get() = ApplicationManager.getApplication().getService(MTThemeCollection::class.java)

  /**
   * Find for a native theme or a bundled theme by its id
   *
   * @param themeID The theme id
   */
  @JvmStatic
  fun getThemeFor(themeID: String): MTThemeFacade? = themesMap[themeID]

  /**
   * Install a new theme to the list (including native themes)
   *
   * @param theme the theme
   * @return the theme facade
   */
  @JvmStatic
  fun installTheme(theme: MTThemeable): MTThemeFacade? {
    if (getThemeFor(theme.themeId) == null) {
      addTheme(fromTheme(theme))
    }
    return getThemeFor(theme.themeId)
  }

  /**
   * Add a new theme to the list
   *
   * @param themesInterface the theme to add
   */
  private fun addTheme(themesInterface: MTThemeFacade) {
    if (!themesMap.containsKey(themesInterface.themeId)) {
      themesMap[themesInterface.themeId] = themesInterface
    }
  }

  /**
   * Get the list of all themes (native + bundled)
   */
  @JvmStatic
  fun getAllThemes(): Vector<MTThemeFacade> {
    val isPremium = MTMainProductLicenseChecker.instance.isLicensed

    return themesMap.values
      .filter { isPremium || !it.isPremium }
      .sortedBy { it.order }
      .toCollection(Vector())
  }

  /**
   * Converts a [MTThemeable] to a [MTTheme]
   *
   * @param theme the themeable (such as NativeTheme)
   * @return the [MTTheme]
   */
  private fun fromTheme(theme: MTThemeable): MTThemeFacade {
    return object : MTThemeFacade {
      override val themeColorScheme: String?
        get() = theme.themeColorScheme

      override val theme: MTThemeable
        get() = theme

      override val isDark: Boolean
        get() = theme.isThemeDark

      override val name: String
        get() = theme.themeName

      override val themeName: String
        get() = theme.themeName

      override val themeId: String
        get() = theme.themeId

      override val icon: Icon?
        get() = theme.icon

      override val order: Int
        get() = theme.order

      override val isPremium: Boolean
        get() = true

      override val isCustom: Boolean
        get() = false
    }
  }
}
