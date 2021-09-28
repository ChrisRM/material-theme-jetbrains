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

package com.mallowigi.idea.themes;

import com.mallowigi.idea.themes.models.MTThemeable;
import com.mallowigi.idea.themes.themes.*;
import com.mallowigi.idea.visitors.MTMainProductLicenseChecker;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;
import java.util.stream.Collectors;

/**
 * Facade for accessing internal theme's methods.
 * Contains a list of predefined themes and will contain all bundled themes
 */
@SuppressWarnings({"OverlyCoupledClass",
  "SuspiciousGetterSetter",
  "ClassWithTooManyMethods"})
public enum MTThemes implements MTThemeFacade {
  OCEANIC("OCEANIC", new MTOceanicTheme(), false),
  DARKER("DARKER", new MTDarkerTheme(), false),
  LIGHTER("LIGHTER", new MTLighterTheme(), false),
  PALENIGHT("PALENIGHT", new MTPalenightTheme(), false),
  DEEPOCEAN("DEEPOCEAN", new MTDeepOceanTheme(), false),
  CUSTOM("CUSTOM", new MTCustomTheme(), true),
  LIGHT_CUSTOM("LIGHT_CUSTOM", new MTLightCustomTheme(), true),
  MONOKAI("MONOKAI", new MonokaiTheme(), false),
  ARC_DARK("ARC_DARK", new ArcDarkTheme(), false),
  ONE_DARK("ONE_DARK", new OneDarkTheme(), false),
  ONE_LIGHT("ONE_LIGHT", new OneLightTheme(), false),
  SOLARIZED_DARK("SOLARIZED_DARK", new SolarizedDarkTheme(), false),
  SOLARIZED_LIGHT("SOLARIZED_LIGHT", new SolarizedLightTheme(), false),
  DRACULA("DRACULA", new DraculaTheme(), false),
  GITHUB("GITHUB", new GithubTheme(), false),
  GITHUB_DARK("GITHUB_DARK", new GithubDarkTheme(), false),
  NIGHTOWL("NIGHT_OWL", new NightOwlTheme(), false),
  LIGHTOWL("LIGHT_OWL", new LightOwlTheme(), false),
  MOONLIGHT("MOONLIGHT", new MoonlightTheme(), false),
  NATIVE("NATIVE", new MTNativeTheme(), false);

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
  /**
   * Whether it is premium theme
   */
  private final boolean isPremium;

  /**
   * Represent a theme
   *
   * @param name      the theme name
   * @param mtTheme   the themeable
   * @param isPremium whether the theme is premium
   */
  MTThemes(@NonNls final String name, final MTThemeable mtTheme, final boolean isPremium) {
    this.name = name;
    this.mtTheme = mtTheme;
    this.isPremium = isPremium;
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
  public boolean isDark() {
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
    return name;
  }

  @Override
  public Icon getIcon() {
    return mtTheme.getIcon();
  }

  @Override
  public int getOrder() {
    return mtTheme.getOrder();
  }

  @Override
  public boolean isPremium() {
    return isPremium;
  }

  @Override
  public String toString() {
    return name;
  }

  @Override
  public boolean isCustom() {
    return mtTheme.isCustom();
  }

  @Override
  public void setThemeName(final String name) {
    mtTheme.setThemeName(name);
  }

  @Override
  public void setIsDark(final boolean isDark) {
    mtTheme.setIsDark(isDark);
  }

  @Override
  public boolean isNative() {
    return mtTheme.isNative();
  }

  @Override
  public void applyAccentMode() {
    mtTheme.applyAccentMode();
  }

  /**
   * Find for a native theme or a bundled theme by its id
   *
   * @param themeID The theme id
   */
  public static MTThemeFacade getThemeFor(final String themeID) {
    return THEMES_MAP.get(themeID);
  }

  public static MTThemeFacade installTheme(final MTThemeable theme) {
    if (getThemeFor(theme.getId()) == null) {
      addTheme(fromTheme(theme));
    }
    return getThemeFor(theme.getThemeId());
  }

  /**
   * Add a new theme to the list
   *
   * @param themesInterface the theme to add
   */
  private static void addTheme(final MTThemeFacade themesInterface) {
    if (!THEMES_MAP.containsKey(themesInterface.getThemeId())) {
      THEMES_MAP.put(themesInterface.getThemeId(), themesInterface);
    }
  }

  /**
   * Get the list of all themes (native + bundled)
   */
  @SuppressWarnings("UseOfObsoleteCollectionType")
  public static Vector<MTThemeFacade> getAllThemes() {
    final boolean isPremium = MTMainProductLicenseChecker.getInstance().isLicensed();
    return THEMES_MAP.values()
                     .stream()
                     .filter(mtThemeFacade -> isPremium || !mtThemeFacade.isPremium())
                     .sorted(Comparator.comparingInt(MTThemeFacade::getOrder))
                     .collect(Collectors.toCollection(Vector::new));
  }

  /**
   * Generate a themeFacade from a theme
   *
   * @param theme the theme
   */
  @SuppressWarnings({"OverlyComplexAnonymousInnerClass",
    "FeatureEnvy",
    "AnonymousInnerClassWithTooManyMethods"})
  private static MTThemeFacade fromTheme(final MTThemeable theme) {
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
      public boolean isDark() {
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

      @Override
      public Icon getIcon() {
        return theme.getIcon();
      }

      @Override
      public int getOrder() {
        return 100;
      }

      @Override
      public boolean isPremium() {
        return false;
      }

      @Override
      public boolean isCustom() {
        return false;
      }

      @Override
      public void setThemeName(final String name) {
        theme.setName(name);
      }

      @Override
      public void setIsDark(final boolean isDark) {
        theme.setIsDark(isDark);
      }

      @Override
      public void applyAccentMode() {
        theme.applyAccentMode();
      }
    };
  }

}
