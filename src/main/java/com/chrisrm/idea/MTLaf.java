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

import com.chrisrm.idea.utils.PropertiesParser;
import com.intellij.ide.ui.laf.darcula.DarculaLaf;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.ui.ColorUtil;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public final class MTLaf extends DarculaLaf {

  private final MTTheme theme;

  public MTLaf(@NotNull final MTTheme theme) {
    super();
    this.theme = theme;
  }

  /**
   * Get Theme Prefix
   *
   * @return
   */
  @Override
  protected String getPrefix() {
    return theme.getId();
  }

  @Override
  protected void loadDefaults(final UIDefaults defaults) {
    final Properties properties = new Properties();
    final String osSuffix = SystemInfo.isMac ? "mac" : SystemInfo.isWindows ? "windows" : "linux";
    try {
      InputStream stream = getClass().getResourceAsStream(getPrefix() + ".properties");
      properties.load(stream);
      stream.close();

      stream = getClass().getResourceAsStream(getPrefix() + "_" + osSuffix + ".properties");
      properties.load(stream);
      stream.close();

      final HashMap<String, Object> darculaGlobalSettings = new HashMap<>();
      final String prefix = getPrefix() + ".";
      for (final String key : properties.stringPropertyNames()) {
        if (key.startsWith(prefix)) {
          final Object value = parseValue(key, properties.getProperty(key));
          final String darculaKey = key.substring(prefix.length());
          if (value == "system") {
            darculaGlobalSettings.remove(darculaKey);
          } else {
            darculaGlobalSettings.put(darculaKey, value);
          }
        }
      }

      // Replace global settings in custom themes
      final MTThemes selectedTheme = MTConfig.getInstance().getSelectedTheme();
      if (selectedTheme.isCustom()) {
        // todo replace other properties
        final Color backgroundColorString = ColorUtil.fromHex(selectedTheme.getTheme().getBackgroundColorString());
        final ColorUIResource backgroundColor = new ColorUIResource(backgroundColorString);
        darculaGlobalSettings.put("background", backgroundColor);
        darculaGlobalSettings.put("textBackground", backgroundColor);
        darculaGlobalSettings.put("inactiveBackground", backgroundColor);
      }

      for (final Object key : defaults.keySet()) {
        if (key instanceof String && ((String) key).contains(".")) {
          final String s = (String) key;
          final String darculaKey = s.substring(s.lastIndexOf('.') + 1);
          if (darculaGlobalSettings.containsKey(darculaKey)) {
            defaults.put(key, darculaGlobalSettings.get(darculaKey));
          }
        }
      }

      for (final String key : properties.stringPropertyNames()) {
        final String value = properties.getProperty(key);
        defaults.put(key, parseValue(key, value));
      }
    } catch (final IOException e) {
      log(e);
    }
  }

  /**
   * Parse properties value
   *
   * @param key
   * @param value
   * @return
   */
  @Override
  protected Object parseValue(final String key, @NotNull final String value) {
    return PropertiesParser.parseValue(key, value);
  }
}
