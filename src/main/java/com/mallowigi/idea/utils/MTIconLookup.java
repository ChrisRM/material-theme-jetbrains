package com.mallowigi.idea.utils;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.util.IconLoader;
import com.intellij.util.ObjectUtils;

import javax.swing.*;

import java.text.MessageFormat;

public class MTIconLookup {
  private static String ICONS_DIR_PREFIX = "/icons/mt/";

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

