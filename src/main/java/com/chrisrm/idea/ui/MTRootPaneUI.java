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

package com.chrisrm.idea.ui;

import com.chrisrm.idea.MTConfig;
import com.intellij.ide.ui.laf.darcula.ui.DarculaRootPaneUI;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.util.registry.Registry;
import org.jetbrains.annotations.NonNls;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicRootPaneUI;

/**
 * Created by chris on 26/03/16.
 */
public final class MTRootPaneUI extends DarculaRootPaneUI {
  @NonNls
  private static final String WINDOW_DARK_APPEARANCE = "jetbrains.awt.windowDarkAppearance";
  @NonNls
  private static final String TRANSPARENT_TITLE_BAR_APPEARANCE = "jetbrains.awt.transparentTitleBarAppearance";

  @SuppressWarnings({"MethodOverridesStaticMethodOfSuperclass",
      "unused"})
  public static ComponentUI createUI(final JComponent component) {
    return hasCustomDecoration() ? new MTRootPaneUI() : createWindowsRootPaneUI();
  }

  private static boolean hasCustomDecoration() {
    return SystemInfo.isMac;
  }

  @SuppressWarnings("OverlyBroadCatchBlock")
  private static ComponentUI createWindowsRootPaneUI() {
    try {
      return (ComponentUI) Class.forName("com.sun.java.swing.plaf.windows.WindowsRootPaneUI").getConstructor().newInstance();
    } catch (final Exception e) {
      return new BasicRootPaneUI();
    }
  }

  @SuppressWarnings("FeatureEnvy")
  @Override
  public void installUI(final JComponent c) {
    super.installUI(c);
    final boolean themeIsDark = MTConfig.getInstance().getSelectedTheme().isDark();
    final boolean darkTitleBar = MTConfig.getInstance().isDarkTitleBar();
    final boolean allowDarkWindowDecorations = Registry.get("ide.mac.allowDarkWindowDecorations").asBoolean();

    if (SystemInfo.isMac || SystemInfo.isLinux) {
      if (darkTitleBar) {
        Registry.get("ide.mac.allowDarkWindowDecorations").setValue(themeIsDark);
        c.putClientProperty(WINDOW_DARK_APPEARANCE, themeIsDark);
        c.putClientProperty(TRANSPARENT_TITLE_BAR_APPEARANCE, true);
      } else {
        c.putClientProperty(WINDOW_DARK_APPEARANCE, themeIsDark && allowDarkWindowDecorations);
        c.putClientProperty(TRANSPARENT_TITLE_BAR_APPEARANCE, false);
      }
    }
  }
}
