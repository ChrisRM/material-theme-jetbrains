/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 Chris Magnussen and Elior Boukhobza
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

package com.chrisrm.idea.utils;

import com.chrisrm.idea.MTConfig;
import com.chrisrm.idea.messages.MaterialThemeBundle;
import com.intellij.ide.plugins.IdeaPluginDescriptor;
import com.intellij.ide.plugins.PluginManager;
import com.intellij.ide.ui.LafManager;
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
import com.intellij.ui.ColorUtil;
import com.intellij.util.ObjectUtils;
import com.intellij.util.ui.UIUtil;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * All kinds of utils and constants
 */
@SuppressWarnings({"unused",
    "StaticMethodOnlyUsedInOneClass"})
public enum MTUiUtils {
  DEFAULT;

  public static final String MATERIAL_FONT = "Roboto";
  public static final String HELP_PREFIX = "com.chrisrm.idea.help";
  public static final String DOCS_URL = "https://www.material-theme.com/";
  public static final String PLUGIN_ID = "com.chrisrm.idea.MaterialThemeUI";
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
    if (MTConfig.getInstance().isMaterialTheme()) {
      return ObjectUtils.notNull(mtColor, defaultColor);
    }
    return defaultColor;
  }

  /**
   * Checks if current LAF is Darcula
   *
   * @return true if darcula
   */
  public static boolean isDarcula() {
    //noinspection ConstantConditions
    return Objects.equals(LafManager.getInstance().getCurrentLookAndFeel().toString(), DARCULA);
  }

  /**
   * Restarts the IDE :-)
   */
  public static void restartIde() {
    final Application application = ApplicationManager.getApplication();
    if (application instanceof ApplicationImpl) {
      ((ApplicationEx) application).restart(true);
    } else {
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
    return "1.3.0";
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
   * Get the plugin ID
   *
   * @return plugin id
   */
  private static String getPluginId() {
    final Map<String, PluginId> registeredIds = PluginId.getRegisteredIds();
    final Optional<Map.Entry<String, PluginId>> pluginIdEntry = registeredIds.entrySet()
                                                                             .stream()
                                                                             .filter(e -> e.getKey().contains(PLUGIN_NAME))
                                                                             .findFirst();

    return pluginIdEntry.map(stringPluginIdEntry -> String.valueOf(stringPluginIdEntry.getValue())).orElse(null);
  }

  /**
   * Get plugin descriptor
   *
   * @return the plugin
   */
  private static IdeaPluginDescriptor getPlugin() {
    return PluginManager.getPlugin(PluginId.getId(PLUGIN_ID));
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
}
