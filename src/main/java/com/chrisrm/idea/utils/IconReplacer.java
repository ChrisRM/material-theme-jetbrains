/*
 *  The MIT License (MIT)
 *
 *  Copyright (c) 2018 Chris Magnussen and Elior Boukhobza
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 *
 */

package com.chrisrm.idea.utils;

import com.chrisrm.idea.MTConfig;
import com.chrisrm.idea.icons.tinted.TintedIcon;
import com.chrisrm.idea.icons.tinted.TintedIconsService;
import com.intellij.openapi.util.IconLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageFilter;
import java.awt.image.RGBImageFilter;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URL;

public final class IconReplacer {
  private IconReplacer() {
    // prevent outside instantiation
  }

  public static void replaceIcons(final Class iconsClass, final String iconsRootPath, final String removedPath) {
    final String accentColor = MTConfig.getInstance().getAccentColor();
    // Iterate all fields (which hold icon locations) and patch them if necessary
    for (final Field field : iconsClass.getDeclaredFields()) {
      if (Modifier.isStatic(field.getModifiers())) {
        try {
          // Object should be some kind of javax.swing.Icon
          final Object value = field.get(null);
          final Class byClass = value.getClass();

          if (byClass.getName().endsWith("$ByClass")) {
            StaticPatcher.setFieldValue(value, "myCallerClass", IconReplacer.class);
            StaticPatcher.setFieldValue(value, "myWasComputed", Boolean.FALSE);
            StaticPatcher.setFieldValue(value, "myIcon", null);
          } else if (byClass.getName().endsWith("$CachedImageIcon")) {
            final String newPath = patchUrlIfNeeded(value, iconsRootPath, removedPath);
            if (newPath != null) {
              final Icon newIcon = TintedIconsService.getIcon(newPath, accentColor);
              StaticPatcher.setFinalStatic(field, newIcon);
            }
          } else if (byClass.getName().endsWith("TintedIcon")) {
            final Icon newIcon = TintedIconsService.getIcon(((TintedIcon) value).getPath(), accentColor);
            StaticPatcher.setFinalStatic(field, newIcon);
          }
        } catch (final Exception e) {
          // suppress
          //          e.printStackTrace();
        }
      }
    }

    // Recurse into nested classes
    for (final Class subClass : iconsClass.getDeclaredClasses()) {
      replaceIcons(subClass, iconsRootPath, removedPath);
    }
  }

  public static void replaceIcons(final Class iconsClass, final String iconsRootPath) {
    replaceIcons(iconsClass, iconsRootPath, "/icons/");
  }

  public static void applyFilter() {
    final boolean monochromeIcons = MTConfig.getInstance().isMonochromeIcons();
    if (monochromeIcons) {
      final Color primaryColor = MTConfig.getInstance().getSelectedTheme().getTheme().getPrimaryColor();
      IconLoader.setFilter(new ColorizeFilter(primaryColor));
    } else {
      IconLoader.setFilter(new ImageFilter());
    }
  }

  private static String patchUrlIfNeeded(final Object icon, final String iconsRootPath, final String removedPath) {
    try {
      final Field urlField = icon.getClass().getDeclaredField("myUrl");
      final Field iconField = icon.getClass().getDeclaredField("myRealIcon");
      urlField.setAccessible(true);
      iconField.setAccessible(true);

      final Object url = urlField.get(icon);
      if (url instanceof URL) {
        String path = ((URL) url).getPath();
        if (path != null && path.contains("!")) {
          path = path.substring(path.lastIndexOf(33) + 1);

          if (iconsRootPath.contains("plugins")) {
            path = path.replace(removedPath, "");
            //            path = path.substring(path.lastIndexOf('/') + 1);
          }

          path = iconsRootPath + path;
        }

        final URL newUrl = IconReplacer.class.getResource(path);
        if (newUrl != null && path != null) {
          iconField.set(icon, null);
          urlField.set(icon, newUrl);
          return path;
        }
        return null;
      }
    } catch (final Exception e) {
      e.printStackTrace();
    }

    return iconsRootPath;
  }

  private static class ColorizeFilter extends RGBImageFilter {
    private final Color primaryColor;

    ColorizeFilter(final Color primaryColor) {
      this.primaryColor = primaryColor;
    }

    @Override
    public int filterRGB(final int x, final int y, final int pARGB) {
      final float[] myBase = Color.RGBtoHSB(primaryColor.getRed(), primaryColor.getGreen(), primaryColor.getBlue(), null);
      // Get color components
      final int r = pARGB >> 16 & 0xFF;
      final int g = pARGB >> 8 & 0xFF;
      final int b = pARGB & 0xFF;
      final float[] hsb = new float[3];
      Color.RGBtoHSB(r, g, b, hsb);
      final int color = Color.HSBtoRGB(myBase[0], myBase[1] * hsb[1], myBase[2] * hsb[2]);
      return (pARGB & 0xFF000000) | color & 0x00FFFFFF;
    }
  }
}
