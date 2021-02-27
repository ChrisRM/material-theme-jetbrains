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

import com.intellij.ui.ColorUtil;
import com.intellij.util.SVGLoader;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.plaf.ColorUIResource;
import java.awt.*;

@SuppressWarnings({"UnstableApiUsage",
  "DuplicateStringLiteralInspection",
  "SyntheticAccessorCall"})
public class MTAccentColorPatcher implements SVGLoader.SvgElementColorPatcherProvider {
  private static final MTConfig CONFIG = MTConfig.getInstance();
  @NonNls
  private static @Nullable Color accentColor = getAccentColor();
  private static ColorUIResource themeColor = getThemeColor();

  private static ColorUIResource getAccentColor() {
    return new ColorUIResource(ColorUtil.fromHex(CONFIG.getAccentColor()));
  }

  private static ColorUIResource getThemeColor() {
    return new ColorUIResource(CONFIG.getSelectedTheme().getTheme().getPrimaryColor());
  }

  MTAccentColorPatcher() {
    refreshColors();
  }

  private static void refreshColors() {
    accentColor = getAccentColor();
    themeColor = getThemeColor();
  }

  public static void refreshAccentColor(final ColorUIResource tint) {
    accentColor = tint;
  }

  public static void refreshThemeColor(final ColorUIResource theme) {
    themeColor = theme;
  }

  @SuppressWarnings({"AnonymousInnerClassMayBeStatic",
    "OverlyComplexAnonymousInnerClass"})
  @NotNull
  @Override
  @ApiStatus.Internal
  public final SVGLoader.SvgElementColorPatcher forPath(@Nullable final String path) {
    return new SVGLoader.SvgElementColorPatcher() {
      @Override
      public void patchColors(@NonNls final Element svg) {
        // Specifically target other props than AtomMaterial Plugin
        @NonNls final String accentProp = svg.getAttribute("accent");
        @NonNls final String themeProp = svg.getAttribute("theme");

        final String accentHexColor = getColorHex(accentColor);
        final String themeHexColor = getColorHex(themeColor);

        if ("true".equals(accentProp) || "fill".equals(accentProp)) {
          svg.setAttribute("fill", "#" + accentHexColor);
        } else if ("stroke".equals(accentProp)) {
          svg.setAttribute("stroke", "#" + accentHexColor);
        } else if ("true".equals(themeProp) || "fill".equals(themeProp)) {
          svg.setAttribute("fill", "#" + themeHexColor);
        } else if ("stroke".equals(themeProp)) {
          svg.setAttribute("stroke", "#" + themeHexColor);
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

      @Override
      public byte @Nullable [] digest() {
        return null;
      }
    };
  }

  @SuppressWarnings("MethodOnlyUsedFromInnerClass")
  private static String getColorHex(final Color color) {
    return ColorUtil.toHex(color);
  }

}
