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

package com.mallowigi.idea.utils;

import com.intellij.ide.ui.laf.darcula.DarculaLaf;
import com.intellij.ui.ColorUtil;
import com.intellij.ui.scale.JBUIScale;
import com.intellij.util.ui.UIUtil;
import com.mallowigi.idea.MTThemeManager;
import com.mallowigi.idea.config.application.MTConfig;
import com.mallowigi.idea.themes.models.MTThemeable;
import org.jetbrains.annotations.NonNls;
import sun.awt.AppContext;

import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import java.lang.reflect.Field;
import java.net.URL;

import static com.mallowigi.idea.utils.MTUiUtils.DARCULA;

// Note: cannot convert to kotlin
public enum MTStyledKitPatcher {
  ;

  public static final String STYLED_EDITOR_KIT = "StyledEditorKit.JBDefaultStyle";

  /**
   * Patch the Styled Editor Kit for the doc comments
   */
  public static void patchStyledEditorKit() {
    final MTThemeable selectedTheme = MTConfig.getInstance().getSelectedTheme().getTheme();
    final String retinaSuffix = JBUIScale.isUsrHiDPI() ? MTThemeManager.RETINA : MTThemeManager.NON_RETINA;

    // Load css
    final URL url = selectedTheme.getClass().getResource(selectedTheme.getThemeId() + retinaSuffix);
    if (url == null) {
      return;
    }

    final UIDefaults defaults = UIManager.getLookAndFeelDefaults();
    StyleSheet styleSheet = UIUtil.loadStyleSheet(url);
    if (styleSheet == null) {
      final URL fallbackUrl = DarculaLaf.class.getResource(DARCULA + retinaSuffix);
      styleSheet = UIUtil.loadStyleSheet(fallbackUrl);
    }

    // Add custom accent color
    assert styleSheet != null;
    final String accentColor = ColorUtil.toHex(MTUI.Panel.getLinkForeground());

    @NonNls final String css = "a, address, b { color: #%s; }";
    styleSheet.addRule(String.format(css, accentColor));
    UIManager.put(STYLED_EDITOR_KIT, styleSheet);
    defaults.put(STYLED_EDITOR_KIT, styleSheet);

    try {
      final Field keyField = HTMLEditorKit.class.getDeclaredField("DEFAULT_STYLES_KEY");
      keyField.setAccessible(true);
      AppContext.getAppContext().put(keyField.get(null), styleSheet);
    } catch (final IllegalAccessException | NoSuchFieldException e) {
      // do nothing
    }
  }
}
