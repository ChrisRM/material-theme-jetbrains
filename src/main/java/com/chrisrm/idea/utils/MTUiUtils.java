/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Chris Magnussen and Elior Boukhobza
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
import com.intellij.ide.plugins.IdeaPluginDescriptor;
import com.intellij.ide.plugins.PluginManager;
import com.intellij.ide.ui.LafManager;
import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.impl.ApplicationImpl;
import com.intellij.openapi.extensions.PluginId;
import com.intellij.util.ObjectUtils;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.UIUtil;
import org.jetbrains.annotations.NotNull;

import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public final class MTUiUtils {
  public static final int PADDING = 4;
  public static final int HEIGHT = 16;
  public static final String MATERIAL_FONT = "Roboto";
  private static RenderingHints hints;

  private MTUiUtils() {

  }

  static {
    MTUiUtils.setHints(new RenderingHints(RenderingHints.KEY_ALPHA_INTERPOLATION,
        RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED));
    MTUiUtils.getHints().put(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
    MTUiUtils.getHints().put(RenderingHints.KEY_RENDERING,
        RenderingHints.VALUE_RENDER_SPEED);
    MTUiUtils.getHints().put(RenderingHints.KEY_TEXT_ANTIALIASING,
        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    MTUiUtils.getHints().put(RenderingHints.KEY_FRACTIONALMETRICS,
        RenderingHints.VALUE_FRACTIONALMETRICS_ON);
  }

  public static Font findFont(final String name) {
    for (final Font font : GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts()) {
      if (font.getFamily().equals(name)) {
        return font;
      }
    }
    return null;
  }

  public static Color lightOrDark(final ColorUIResource darkColor, final ColorUIResource lightColor) {
    return UIUtil.isUnderDarcula() ? darkColor : lightColor;
  }


  public static Color lightOrDark(final Color darkColor, final Color lightColor) {
    return UIUtil.isUnderDarcula() ? darkColor : lightColor;
  }

  public static Color getColor(final Color mtColor, @NotNull final Color darculaColor, @NotNull final Color intellijColor) {
    final Color defaultColor = UIUtil.isUnderDarcula() ? darculaColor : intellijColor;
    if (MTConfig.getInstance().isMaterialTheme()) {
      return ObjectUtils.notNull(mtColor, defaultColor);
    }
    return defaultColor;
  }

  public static boolean isDarcula() {
    return LafManager.getInstance().getCurrentLookAndFeel().equals("Darcula");
  }

  public static Font getWidgetFont() {
    final GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
    final Font[] fonts = e.getAllFonts();
    for (final Font f : fonts) {
      if (Objects.equals(f.getFontName(), MATERIAL_FONT)) {

        final Map<TextAttribute, Object> attributes = new HashMap<>();

        attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
        attributes.put(TextAttribute.SIZE, JBUI.scale(11));

        return f.deriveFont(attributes);
      }
    }
    return JBUI.Fonts.label(12);
  }

  /**
   * Restart the IDE :-)
   */
  public static void restartIde() {
    final Application application = ApplicationManager.getApplication();
    if (application instanceof ApplicationImpl) {
      ((ApplicationImpl) application).restart(true);
    } else {
      application.restart();
    }
  }

  public static RenderingHints getHints() {
    return hints;
  }

  public static void setHints(final RenderingHints hints) {
    MTUiUtils.hints = hints;
  }

  public static String getVersion() {
    return getPlugin().getVersion();
  }

  private static String getPluginId() {
    final Map<String, PluginId> registeredIds = PluginId.getRegisteredIds();
    final Optional<Map.Entry<String, PluginId>> pluginIdEntry = registeredIds.entrySet()
                                                                             .stream()
                                                                             .filter(e -> e.getKey().contains("MaterialThemeUI"))
                                                                             .findFirst();

    return pluginIdEntry.isPresent() ? String.valueOf(pluginIdEntry.get().getValue()) : null;
  }

  private static IdeaPluginDescriptor getPlugin() {
    //    return PluginManager.getPlugin(PluginId.getId(getPluginId()));
    return PluginManager.getPlugin(PluginId.getId("com.chrisrm.idea.MaterialThemeUI"));
  }
}
