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

import com.intellij.openapi.util.IconLoader;
import com.intellij.util.ui.UIUtil;

import javax.swing.*;
import java.util.HashMap;

@Deprecated
public final class IconCache {
  private static final HashMap<String, Icon> CACHE = new HashMap<>();

  public static Icon getIcon(final String name, final boolean editable, final boolean selected, final boolean focused,
                             final boolean enabled, final boolean pressed) {
    String key = name;
    if (editable) {
      key += "Editable";
    }
    if (selected) {
      key += "Selected";
    }

    if (pressed) {
      key += "Pressed";
    } else if (focused) {
      key += "Focused";
    } else if (!enabled) {
      key += "Disabled";
    }

    String dir = "";

    // For Mac blue theme and other LAFs use default directory icons
    if (UIUtil.isUnderDefaultMacTheme()) {
      dir = "";
    } else if (UIUtil.isUnderWin10LookAndFeel()) {
      dir = "win10/";
    } else if (UIUtil.isUnderDarcula()) {
      dir = "darcula/";
    } else if (UIUtil.isUnderIntelliJLaF()) {
      dir = "intellij/";
    }

    key = dir + key;

    Icon icon = CACHE.get(key);
    if (icon == null) {
      icon = IconLoader.findIcon("/com/intellij/ide/ui/laf/icons/" + key + ".png", IconCache.class, true);
      CACHE.put(key, icon);
    }
    return icon;
  }

  public static Icon getIcon(final String name, final boolean editable, final boolean selected, final boolean focused,
                             final boolean enabled) {
    return getIcon(name, editable, selected, focused, enabled, false);
  }

  public static Icon getIcon(final String name, final boolean selected, final boolean focused, final boolean enabled) {
    return getIcon(name, false, selected, focused, enabled);
  }

  public static Icon getIcon(final String name, final boolean selected, final boolean focused) {
    return getIcon(name, false, selected, focused, true);
  }

  public static Icon getIcon(final String name) {
    return getIcon(name, false, false, false, true);
  }
}
