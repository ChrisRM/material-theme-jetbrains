/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015-2020 Elior "Mallowigi" Boukhobza
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

package com.mallowigi.idea.utils;

import com.intellij.ide.plugins.IdeaPluginDescriptor;
import com.intellij.ide.plugins.PluginManager;
import com.intellij.ide.plugins.PluginManagerCore;
import com.intellij.ide.ui.LafManager;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationListener;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ModalityState;
import com.intellij.openapi.application.ex.ApplicationEx;
import com.intellij.openapi.application.impl.ApplicationImpl;
import com.intellij.openapi.extensions.PluginId;
import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.wm.impl.IdeBackgroundUtil;
import com.intellij.ui.ColorUtil;
import com.intellij.ui.JBColor;
import com.intellij.util.ObjectUtils;
import com.intellij.util.ui.UIUtil;
import com.mallowigi.idea.MTThemeManager;
import com.mallowigi.idea.messages.MaterialThemeBundle;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

/**
 * All kinds of utils and constants
 */
@SuppressWarnings({"unused",
                    "StaticMethodOnlyUsedInOneClass",
                    "ClassWithTooManyMethods"})
public enum MTUiUtils {
  DEFAULT;

  @NonNls
  public static final String MATERIAL_FONT = MTThemeManager.DEFAULT_FONT;
  @NonNls
  public static final String HELP_PREFIX = "com.mallowigi.idea.help";
  @NonNls
  public static final String DOCS_URL = "https://www.material-theme.com/";
  @NonNls
  public static final String PLUGIN_ID = "com.mallowigi.idea.MaterialThemeUI";
  @NonNls
  public static final String APPEARANCE_SECTION = "Appearance";
  @NonNls
  public static final String DARCULA = "Darcula";
  @NonNls
  public static final String PLUGIN_NAME = "MaterialThemeUI";
  private static final RenderingHints RENDERING_HINTS;
  @NonNls
  public static final String NOTO_SANS = "Noto Sans";

  static {
    RENDERING_HINTS = new RenderingHints(RenderingHints.KEY_ALPHA_INTERPOLATION,
                                         RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);
    RENDERING_HINTS.put(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);
    RENDERING_HINTS.put(RenderingHints.KEY_RENDERING,
                        RenderingHints.VALUE_RENDER_SPEED);
    RENDERING_HINTS.put(RenderingHints.KEY_TEXT_ANTIALIASING,
                        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    RENDERING_HINTS.put(RenderingHints.KEY_FRACTIONALMETRICS,
                        RenderingHints.VALUE_FRACTIONALMETRICS_ON);
  }

  public static Map getHints() {
    return Collections.unmodifiableMap(RENDERING_HINTS);
  }

  /**
   * Find a font
   *
   * @param name font name
   * @return font if found
   */
  @Nullable
  public static Font findFont(@NonNls final String name) {
    for (final Font font : GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts()) {
      if (font.getFamily().equals(name)) {
        return font;
      }
    }
    return null;
  }

  /**
   * Return a color between dark and light colors according to the current look and feel
   *
   * @param darkColor  the color to return if dark
   * @param lightColor the color to return if light
   * @return the color
   */
  @SuppressWarnings("OverloadedMethodsWithSameNumberOfParameters")
  public static Color lightOrDark(final ColorUIResource darkColor, final ColorUIResource lightColor) {
    return UIUtil.isUnderDarcula() ? darkColor : lightColor;
  }

  /**
   * Return a color between dark and light colors according to the current look and feel
   *
   * @param darkColor  the color to return if dark
   * @param lightColor the color to return if light
   * @return the color
   */
  @SuppressWarnings("OverloadedMethodsWithSameNumberOfParameters")
  public static Color lightOrDark(final Color darkColor, final Color lightColor) {
    return UIUtil.isUnderDarcula() ? darkColor : lightColor;
  }

  /**
   * Brightens a color by tones if the current laf is dark, otherwise darkens it
   *
   * @param color the color to brighten/darken
   * @param tones number of tones to darken
   * @return new color
   */
  public static Color brighter(final Color color, final int tones) {
    return UIUtil.isUnderDarcula() ? ColorUtil.brighter(color, tones) : ColorUtil.darker(color, tones);
  }

  /**
   * Darkens a color by tones if the current laf is dark, otherwise brightens it
   *
   * @param color the color to brighten/darken
   * @param tones number of tones to darken
   * @return new color
   */
  public static Color darker(final Color color, final int tones) {
    return UIUtil.isUnderDarcula() ? ColorUtil.darker(color, tones) : ColorUtil.brighter(color, tones);
  }

  /**
   * Returns a color according to specific conditions:
   * If Material Theme is enabled, returns the mtColor
   * Otherwise if LAF is dark returns the darkColor
   * Otherwise if LAF is light returns the lightColor
   *
   * @param mtColor    material theme color
   * @param darkColor  darcula color
   * @param lightColor light color
   * @return color
   */
  public static Color getColor(final Color mtColor, @NotNull final Color darkColor, @NotNull final Color lightColor) {
    final Color defaultColor = UIUtil.isUnderDarcula() ? darkColor : lightColor;
    return ObjectUtils.notNull(mtColor, defaultColor);
  }

  /**
   * Checks if current LAF is Darcula
   *
   * @return true if darcula
   */
  public static boolean isDarcula() {
    return Objects.equals(Objects.requireNonNull(LafManager.getInstance().getCurrentLookAndFeel()).toString(), DARCULA);
  }

  /**
   * Restarts the IDE :-)
   */
  public static void restartIde() {
    final Application application = ApplicationManager.getApplication();
    if (application instanceof ApplicationImpl) {
      ((ApplicationEx) application).restart(true);
    }
    else {
      application.restart();
    }
  }

  /**
   * Get plugin version
   *
   * @return Plugin version
   */
  public static String getVersion() {
    final IdeaPluginDescriptor plugin = getPlugin();
    if (plugin != null) {
      return plugin.getVersion();
    }
    return MaterialThemeBundle.message("plugin.version");
  }

  /**
   * Plugin name
   *
   * @return plugin name
   */
  public static Object getPluginName() {
    return MaterialThemeBundle.message("plugin.name");
  }

  /**
   * Get plugin descriptor
   *
   * @return the plugin
   */
  private static IdeaPluginDescriptor getPlugin() {
    return PluginManagerCore.getPlugin(PluginId.getId(PLUGIN_ID));
  }

  public static boolean hasAtomPluginInstalled() {
    return PluginManager.isPluginInstalled(PluginId.getId(MaterialThemeBundle.message("atom.pluginid")));
  }

  /**
   * Convert a color to dword
   *
   * @param color color
   * @return dword
   */
  public static int colorToDword(final Color color) {
    final Color result = new Color(color.getBlue(), color.getGreen(), color.getRed());
    return result.getRGB();
  }

  /**
   * Convert a dword to color
   *
   * @param dword dword
   * @return color
   */
  public static Color dwordToColor(final int dword) {
    final Color color = new Color(dword);
    return new Color(color.getBlue(), color.getGreen(), color.getRed());
  }

  public static NotificationListener.Adapter openAppearanceSettings(final Project project) {
    return new NotificationListener.Adapter() {
      @Override
      protected void hyperlinkActivated(@NotNull final Notification notification, @NotNull final HyperlinkEvent e) {
        ApplicationManager.getApplication().invokeLater(() -> ShowSettingsUtil.getInstance().showSettingsDialog(
          project,
          APPEARANCE_SECTION), ModalityState.NON_MODAL);
      }
    };
  }

  /**
   * Iterate over theme resources and fill up the UIManager
   */
  public static void buildResources(final Iterable<String> resources, final Color color) {
    for (final String resource : resources) {
      UIManager.getDefaults().put(resource, color);
    }
  }

  /**
   * Build accent resources and put them in the UI Manager
   */
  public static void buildAccentResources(final Iterable<String> resources, final Color color, final boolean isAccentMode) {
    for (final String resource : resources) {
      if (isAccentMode) {
        UIManager.put(resource, color);
      }
      else {
        final Color defaultColor = UIManager.getLookAndFeelDefaults().getColor(resource);
        if (defaultColor != null) {
          UIManager.put(resource, defaultColor);
        }
      }
    }
  }

  public static <T extends Enum<T>> String parseEnumValue(final Object value, final T defaultValue) {
    if (value instanceof String) {
      @NonNls final String name = StringUtil.toUpperCase((String) value);
      for (final T t : ((Class<T>) defaultValue.getClass()).getEnumConstants()) {
        if (t.name().equals(name)) {
          return StringUtil.toLowerCase(value.toString());
        }
      }
    }
    return StringUtil.toLowerCase(defaultValue.name());
  }

  /**
   * Returns whether the user has a frame wallpaper set
   */
  public static boolean hasFrameWallpaper() {
    return PropertiesComponent.getInstance().getValue("old.mt." + IdeBackgroundUtil.FRAME_PROP) != null ||
      PropertiesComponent.getInstance().getValue(IdeBackgroundUtil.FRAME_PROP) != null;
  }

  /**
   * Returns whether the value is between min and max
   *
   * @param value value to check
   * @param min   min bound
   * @param max   max bound
   */
  public static int valueInRange(final int value, final int min, final int max) {
    return Integer.min(max, Integer.max(value, min));
  }

  public static JBColor toJBColor(final Color color) {
    return new JBColor(color, color);
  }

  /**
   * Generate a random color
   */
  @SuppressWarnings("UnsecureRandomNumberGeneration")
  public static Color getRandomColor() {
    final Random random = new Random();
    final float hue = random.nextFloat();
    // Saturation between 0.1 and 0.3
    final float saturation = (random.nextInt(2000) + 1000) / 10000.0f;
    final float luminance = 0.4f;
    return Color.getHSBColor(hue, saturation, luminance);
  }

  /**
   * Converts a string to a color
   */
  public static int stringToARGB(final CharSequence charSequence) {
    return hashCode(charSequence);
  }

  private static int hashCode(final CharSequence charSequence) {
    int hash = 0;
    final int length = charSequence.length();
    for (int i = 0; i < length; i++) {
      hash = (int) charSequence.charAt(i) + ((hash << 5) - hash);
    }
    return hash;
  }
}
