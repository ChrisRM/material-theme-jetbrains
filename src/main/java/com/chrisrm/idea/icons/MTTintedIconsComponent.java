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

package com.chrisrm.idea.icons;

import com.chrisrm.idea.MTConfig;
import com.chrisrm.idea.listeners.AccentsListener;
import com.chrisrm.idea.listeners.MTTopics;
import com.chrisrm.idea.listeners.ThemeListener;
import com.chrisrm.idea.themes.MTThemeFacade;
import com.chrisrm.idea.utils.MTAccents;
import com.chrisrm.idea.utils.MTUI;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.BaseComponent;
import com.intellij.ui.ColorUtil;
import com.intellij.util.SVGLoader;
import com.intellij.util.messages.MessageBusConnection;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.awt.*;

/**
 * Apply a tint to the icons. This is used either for accented icons and themed icons.
 */
public final class MTTintedIconsComponent implements BaseComponent {
  private TintedColorPatcher colorPatcher;
  private MessageBusConnection connect;

  @Override
  public void initComponent() {
    colorPatcher = new TintedColorPatcher();
    SVGLoader.setColorPatcher(colorPatcher);

    // Listen for changes on the settings
    connect = ApplicationManager.getApplication().getMessageBus().connect();
    connect.subscribe(MTTopics.ACCENTS, new AccentsListener() {
      @Override
      public void accentChanged(@NotNull final Color accentColor) {
        SVGLoader.setColorPatcher(null);
        SVGLoader.setColorPatcher(getColorPatcher());
        TintedColorPatcher.refreshAccentColor(accentColor);
      }
    });

    connect.subscribe(MTTopics.THEMES, new ThemeListener() {
      @Override
      public void themeChanged(@NotNull final MTThemeFacade theme) {
        SVGLoader.setColorPatcher(null);
        SVGLoader.setColorPatcher(getColorPatcher());
        TintedColorPatcher.refreshThemeColor(theme);
      }
    });
  }

  @Override
  public void disposeComponent() {
    connect.disconnect();
  }

  @NonNls
  @Override
  @NotNull
  public String getComponentName() {
    return "MTTintedIconsComponent";
  }

  @SuppressWarnings("WeakerAccess")
  TintedColorPatcher getColorPatcher() {
    return colorPatcher;
  }

  private static final class TintedColorPatcher implements SVGLoader.SvgColorPatcher {
    @NonNls
    private static String accentColor = MTAccents.TURQUOISE.getHexColor();
    @NonNls
    private static String themedColor = MTAccents.OCEANIC.getHexColor();

    private static final MTConfig CONFIG = MTConfig.getInstance();

    private TintedColorPatcher() {
      refreshColors();
    }

    static void refreshAccentColor(final Color accentColor) {
      TintedColorPatcher.accentColor = ColorUtil.toHex(accentColor);
    }

    static void refreshThemeColor(final MTThemeFacade theme) {
      themedColor = ColorUtil.toHex(theme.getTheme().getPrimaryColor());
    }

    private static void refreshColors() {
      accentColor = ColorUtil.toHex(MTUI.TabbedPane.getHighlightColor());
      themedColor = ColorUtil.toHex(CONFIG.getSelectedTheme().getTheme().getPrimaryColor());
    }

    @SuppressWarnings({"IfStatementWithTooManyBranches",
        "DuplicateStringLiteralInspection"})
    @Override
    public void patchColors(@NonNls final Element svg) {
      @NonNls final String tint = svg.getAttribute("tint");
      @NonNls final String themed = svg.getAttribute("themed");

      if ("true".equals(tint) || "fill".equals(tint)) {
        svg.setAttribute("fill", "#" + accentColor);
      } else if ("stroke".equals(tint)) {
        svg.setAttribute("stroke", "#" + accentColor);
      } else if ("true".equals(themed) || "fill".equals(themed)) {
        svg.setAttribute("fill", "#" + themedColor);
      } else if ("stroke".equals(themed)) {
        svg.setAttribute("stroke", "#" + themedColor);
      }

      final NodeList nodes = svg.getChildNodes();
      final int length = nodes.getLength();
      for (int i = 0; i < length; i++) {
        final Node item = nodes.item(i);
        if (item instanceof Element) {
          patchColors((Element) item);
        }
      }
    }
  }
}
