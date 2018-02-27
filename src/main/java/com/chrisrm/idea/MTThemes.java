/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Chris Magnussen and Elior Boukhobza
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

package com.chrisrm.idea;

import com.chrisrm.idea.themes.*;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

/**
 * Facade for accessing internal theme's methods.
 * Contains a list of predefined themes and will contain all bundled themes
 */
public enum MTThemes implements MTThemeFacade {
  OCEANIC("OCEANIC", new MTOceanicTheme()),
  DARKER("DARKER", new MTDarkerTheme()),
  LIGHTER("LIGHTER", new MTLighterTheme()),
  PALENIGHT("PALENIGHT", new MTPalenightTheme()),
  CUSTOM("CUSTOM", new MTCustomTheme()),
  LIGHT_CUSTOM("LIGHT_CUSTOM", new MTLightCustomTheme()),
  MONOKAI("MONOKAI", new MonokaiTheme()),
  ARC_DARK("ARC_DARK", new ArcDarkTheme()),
  ONE_DARK("ONE_DARK", new OneDarkTheme()),

  EXTERNAL("EXTERNAL", new MTCustomTheme());

  private static final Map<String, MTThemeFacade> THEMES_MAP = new TreeMap<>();

  static {
    for (final MTThemeFacade theme : values()) {
      THEMES_MAP.put(theme.getName(), theme);
    }
  }

  /**
   * The name of the theme (uppercase)
   */
  private final String name;
  /**
   * The instance of MTThemeable
   */
  private final transient MTThemeable mtTheme;

  MTThemes(final String name, final MTAbstractTheme mtTheme) {
    this.name = name;
    this.mtTheme = mtTheme;
  }

  @NotNull
  @Override
  public String getThemeColorScheme() {
    return mtTheme.getEditorColorsScheme();
  }

  @NotNull
  @Override
  public MTThemeable getTheme() {
    return mtTheme;
  }

  @Override
  public boolean getThemeIsDark() {
    return mtTheme.isDark();
  }

  @NotNull
  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getThemeName() {
    return mtTheme.getName();
  }

  @NotNull
  @Override
  public String getThemeId() {
    return getName();
  }

  /**
   * Find for a native theme or a bundled theme by its id
   *
   * @param themeID
   */
  public static MTThemeFacade getThemeFor(final String themeID) {
    return THEMES_MAP.get(themeID);
  }

  /**
   * Add a new theme to the enum
   *
   * @param themesInterface
   */
  public static MTThemeFacade addTheme(final MTThemeFacade themesInterface) {
    if (!THEMES_MAP.containsKey(themesInterface.getThemeId())) {
      THEMES_MAP.put(themesInterface.getThemeId(), themesInterface);
    }
    return themesInterface;
  }

  /**
   * Get the list of all themes (native + bundled)
   */
  public static Collection<MTThemeFacade> getAllThemes() {
    return THEMES_MAP.values();
  }

  /**
   * Generate a themeFacade from a theme
   *
   * @param theme
   */
  public static MTThemeFacade fromTheme(final MTThemeable theme) {
    return new MTThemeFacade() {
      @NotNull
      @Override
      public String getThemeColorScheme() {
        return theme.getEditorColorsScheme();
      }

      @NotNull
      @Override
      public MTThemeable getTheme() {
        return theme;
      }

      @Override
      public boolean getThemeIsDark() {
        return theme.isDark();
      }

      @NotNull
      @Override
      public String getName() {
        return theme.getId();
      }

      @Override
      public String getThemeName() {
        return theme.getName();
      }

      @NotNull
      @Override
      public String getThemeId() {
        return theme.getThemeId();
      }
    };
  }

  @Override
  public String toString() {
    return name;
  }

  public boolean isCustom() {
    return mtTheme.isCustom();
  }
}
