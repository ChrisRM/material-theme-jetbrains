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

package com.mallowigi.idea;

import com.intellij.ide.ui.laf.darcula.DarculaLaf;
import com.intellij.ui.ColorUtil;
import com.intellij.ui.scale.JBUIScale;
import com.intellij.util.ui.UIUtil;
import com.mallowigi.idea.config.application.MTConfig;
import com.mallowigi.idea.themes.models.MTThemeable;
import com.mallowigi.idea.utils.MTUI;
import org.jetbrains.annotations.NonNls;
import sun.awt.AppContext;

import javax.swing.*;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.StyleSheet;
import java.lang.reflect.Field;
import java.net.URL;

import static com.mallowigi.idea.utils.MTUiUtils.DARCULA;

public final class MTStyledKitPatcher {
  private MTStyledKitPatcher() {
  }

  @SuppressWarnings({"OverlyBroadCatchBlock",
    "java:S2221",
    "java:S1166",
    "java:S108"})
  public static void patchStyledEditorKit() {
    @NonNls final UIDefaults defaults = UIManager.getLookAndFeelDefaults();
    final MTThemeable selectedTheme = MTConfig.getInstance().getSelectedTheme().getTheme();

    // Load css
    final URL url = selectedTheme.getClass().getResource(selectedTheme.getId() + (JBUIScale.isUsrHiDPI() ? MTThemeManager.RETINA :
                                                                                  MTThemeManager.NON_RETINA));
    StyleSheet styleSheet = UIUtil.loadStyleSheet(url);
    if (styleSheet == null) {
      final URL fallbackUrl = DarculaLaf.class.getResource(DARCULA + (JBUIScale.isUsrHiDPI() ? MTThemeManager.RETINA :
                                                                      MTThemeManager.NON_RETINA));
      styleSheet = UIUtil.loadStyleSheet(fallbackUrl);
    }

    // Add custom accent color
    assert styleSheet != null;
    final String accentColor = ColorUtil.toHex(MTUI.Panel.getLinkForeground());

    @NonNls final String css = "a, address, b { color: #%s; }";
    styleSheet.addRule(String.format(css, accentColor));
    UIManager.put("StyledEditorKit.JBDefaultStyle", styleSheet);
    defaults.put("StyledEditorKit.JBDefaultStyle", styleSheet);

    try {
      final Field keyField = HTMLEditorKit.class.getDeclaredField("DEFAULT_STYLES_KEY");
      keyField.setAccessible(true);
      AppContext.getAppContext().put(keyField.get(null), styleSheet);
    } catch (final Exception ignored) {
    }
  }
}
