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

import com.mallowigi.idea.themes.models.MTThemeable
import com.mallowigi.idea.themes.themes.ArcDarkTheme
import com.mallowigi.idea.themes.themes.DraculaTheme
import com.mallowigi.idea.themes.themes.GithubDarkTheme
import com.mallowigi.idea.themes.themes.GithubTheme
import com.mallowigi.idea.themes.themes.LightOwlTheme
import com.mallowigi.idea.themes.themes.MTCustomTheme
import com.mallowigi.idea.themes.themes.MTDarkerTheme
import com.mallowigi.idea.themes.themes.MTDeepOceanTheme
import com.mallowigi.idea.themes.themes.MTLightCustomTheme
import com.mallowigi.idea.themes.themes.MTLighterTheme
import com.mallowigi.idea.themes.themes.MTNativeTheme
import com.mallowigi.idea.themes.themes.MTOceanicTheme
import com.mallowigi.idea.themes.themes.MTPalenightTheme
import com.mallowigi.idea.themes.themes.MonokaiTheme
import com.mallowigi.idea.themes.themes.MoonlightTheme
import com.mallowigi.idea.themes.themes.NightOwlTheme
import com.mallowigi.idea.themes.themes.OneDarkTheme
import com.mallowigi.idea.themes.themes.OneLightTheme
import com.mallowigi.idea.themes.themes.SolarizedDarkTheme
import com.mallowigi.idea.themes.themes.SolarizedLightTheme
import javax.swing.Icon

/**
 * List of available Material Themes
 *
 * @property theme — the theme as a [MTThemeable]
 * @property isPremium — whether the theme is a premium theme
 */
enum class MTTheme(
  override val theme: MTThemeable,
  override val isPremium: Boolean,
) : MTThemeFacade {
  /**
   * Oceanic Theme — A blue oceanic theme
   *
   */
  OCEANIC(MTOceanicTheme(), false),

  /**
   * Darker Theme — A black and dark theme
   *
   */
  DARKER(MTDarkerTheme(), false),

  /**
   * Lighter Theme — A white and light theme
   *
   */
  LIGHTER(MTLighterTheme(), false),

  /**
   * Palenight Theme — A purplish and indigo theme
   *
   */
  PALENIGHT(MTPalenightTheme(), false),

  /**
   * Deep Ocean Theme — A dark blue theme reminding the ocean abysses
   *
   */
  DEEPOCEAN(MTDeepOceanTheme(), false),

  /**
   * Placeholder for user dark custom themes
   *
   */
  CUSTOM(MTCustomTheme(), true),

  /**
   * Placeholder for user light custom themes
   *
   */
  LIGHT_CUSTOM(MTLightCustomTheme(), true),

  /**
   * Monokai Pro Theme
   *
   */
  MONOKAI(MonokaiTheme(), false),

  /**
   * Arc Dark Theme
   *
   */
  ARC_DARK(ArcDarkTheme(), false),

  /**
   * Atom One Dark Theme
   */
  ONE_DARK(OneDarkTheme(), false),

  /**
   * Atom One Light Theme
   */
  ONE_LIGHT(OneLightTheme(), false),

  /**
   * Solarized Dark Theme
   */
  SOLARIZED_DARK(SolarizedDarkTheme(), false),

  /**
   * Solarized Light Theme
   */
  SOLARIZED_LIGHT(SolarizedLightTheme(), false),

  /**
   * Dracula Theme
   */
  DRACULA(DraculaTheme(), false),

  /**
   * GitHub Theme
   */
  GITHUB(GithubTheme(), false),

  /**
   * GitHub Dark Theme
   */
  GITHUB_DARK(GithubDarkTheme(), false),

  /**
   * Night Owl Theme
   */
  NIGHTOWL(NightOwlTheme(), false),

  /**
   * Light Owl Theme
   */
  LIGHTOWL(LightOwlTheme(), false),

  /**
   * Moonlight Theme
   */
  MOONLIGHT(MoonlightTheme(), false),

  /**
   * Placeholder for external themes (marketplace)
   */
  NATIVE(MTNativeTheme(), false);

  override val themeName: String
    get() = theme.themeName

  override val themeColorScheme: String?
    get() = theme.themeColorScheme

  override val isDark: Boolean
    get() = theme.isThemeDark

  override val themeId: String
    get() = theme.themeId

  override val icon: Icon?
    get() = theme.icon

  override val order: Int
    get() = theme.order

  override val isCustom: Boolean
    get() = theme.isCustom

  override val isNative: Boolean
    get() = theme.isNative

  override fun toString(): String = themeName
}
