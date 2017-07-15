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

import com.intellij.openapi.util.IconLoader;

import javax.swing.*;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.URL;

public class IconReplacer {
  private IconReplacer() {
    // prevent outside instantiation
  }

  public static void replaceIcons(Class iconsClass, String iconsRootPath) {
    // Iterate all fields (which hold icon locations) and patch them if necessary
    for (Field field : iconsClass.getDeclaredFields()) {
      if (Modifier.isStatic(field.getModifiers())) {
        try {
          // Object should be some kind of javax.swing.Icon
          Object value = field.get(null);
          Class byClass = value.getClass();

          if (byClass.getName().endsWith("$ByClass")) {
            StaticPatcher.setFieldValue(value, "myCallerClass", IconReplacer.class);
            StaticPatcher.setFieldValue(value, "myWasComputed", Boolean.FALSE);
            StaticPatcher.setFieldValue(value, "myIcon", null);
          } else if (byClass.getName().endsWith("$CachedImageIcon")) {
            String newPath = patchUrlIfNeeded(value, iconsRootPath);
            if (newPath != null) {
              Icon newIcon = IconLoader.getIcon(newPath);
              StaticPatcher.setFinalStatic(field, newIcon);
            }
          }
        }
        catch (IllegalAccessException e) {
          // Suppress
        }
        catch (Exception e) {
          // suppress
        }
      }
    }

    // Recurse into nested classes
    for (Class subClass : iconsClass.getDeclaredClasses()) {
      replaceIcons(subClass, iconsRootPath);
    }

  }

  private static String patchUrlIfNeeded(Object icon, String iconsRootPath) {
    try {
      Field urlField = icon.getClass().getDeclaredField("myUrl");
      Field iconField = icon.getClass().getDeclaredField("myRealIcon");
      urlField.setAccessible(true);
      iconField.setAccessible(true);

      Object url = urlField.get(icon);
      if (url instanceof URL) {
        String path = ((URL) url).getPath();
        if (path != null && path.contains("!")) {
          path = path.substring(path.lastIndexOf(33) + 1);

          if (iconsRootPath.contains("plugins")) {
            path = path.replace("/icons/", "");
            path = path.substring(path.lastIndexOf('/') + 1);
          }

          path = iconsRootPath + path;
        }

        URL newUrl = IconReplacer.class.getResource(path);
        if (newUrl != null && path != null) {
          iconField.set(icon, null);
          urlField.set(icon, newUrl);
          return path;
        }
        return null;

      }
    }
    catch (Exception e) {
      e.printStackTrace();
    }

    return iconsRootPath;
  }

}
