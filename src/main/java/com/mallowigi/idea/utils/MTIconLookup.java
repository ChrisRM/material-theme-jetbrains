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

import com.intellij.icons.AllIcons;
import com.intellij.openapi.util.IconLoader;
import com.intellij.util.ObjectUtils;

import javax.swing.*;
import java.text.MessageFormat;

public class MTIconLookup {
  private static final String ICONS_DIR_PREFIX = "/icons/mt/";

  public static Icon getIcon(final String name) {
    return getIcon(name, false, false, true, false, false);
  }

  public static Icon getIcon(final String name,
                             final boolean selected,
                             final boolean focused,
                             final boolean enabled) {
    return getIcon(name, selected, focused, enabled, false);
  }

  public static Icon getIcon(final String name,
                             final boolean selected,
                             final boolean focused,
                             final boolean enabled,
                             final boolean editable) {
    return getIcon(name, selected, focused, enabled, editable, false);
  }

  public static Icon getIcon(final String name,
                             final boolean selected,
                             final boolean focused,
                             final boolean enabled,
                             final boolean editable,
                             final boolean pressed) {
    return findIcon(name, selected, focused, enabled, editable, pressed);
  }

  private static Icon findIcon(final String name,
                               final boolean selected,
                               final boolean focused,
                               final boolean enabled,
                               final boolean editable,
                               final boolean pressed) {

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

    final String path = MessageFormat.format("{0}{1}.svg", ICONS_DIR_PREFIX, key);

    return ObjectUtils.notNull(IconLoader.findIcon(path, MTIconLookup.class, true, true), AllIcons.Actions.Stub);
  }

  public static Icon getDisabledIcon(final String name) {
    return getIcon(name, false, false, false, false, false);
  }

  public static Icon getSelectedIcon(final String name) {
    return getIcon(name, true, false, false, true, false);
  }

}

