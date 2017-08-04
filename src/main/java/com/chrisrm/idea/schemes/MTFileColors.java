package com.chrisrm.idea.schemes;

import com.intellij.openapi.editor.colors.ColorKey;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.vcs.FileStatus;
import com.intellij.openapi.vcs.FileStatusFactory;
import com.intellij.ui.ColorUtil;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public final class MTFileColors {
  public static final ColorKey NOT_CHANGED_IMMEDIATE = ColorKey.createColorKey("MT_NOT_CHANGED_IMMEDIATE", ColorUtil.fromHex("#80CBC4"));
  public static final ColorKey NOT_CHANGED_RECURSIVE = ColorKey.createColorKey("MT_NOT_CHANGED_RECURSIVE", ColorUtil.fromHex("#80CBC4"));
  public static final ColorKey DELETED = ColorKey.createColorKey("MT_DELETED", ColorUtil.fromHex("#F77669"));
  public static final ColorKey MODIFIED = ColorKey.createColorKey("MT_MODIFIED", ColorUtil.fromHex("#80CBC4"));
  public static final ColorKey ADDED = ColorKey.createColorKey("MT_ADDED", ColorUtil.fromHex("#C3E887"));
  public static final ColorKey MERGE = ColorKey.createColorKey("MT_MERGE", ColorUtil.fromHex("#C792EA"));
  public static final ColorKey UNKNOWN = ColorKey.createColorKey("MT_UNKNOWN", ColorUtil.fromHex("#F77669"));
  public static final ColorKey IGNORED = ColorKey.createColorKey("MT_IGNORED", ColorUtil.fromHex("#B0BEC5"));
  public static final ColorKey HIJACKED = ColorKey.createColorKey("MT_HIJACKED", ColorUtil.fromHex("#FFCB6B"));
  public static final ColorKey MERGED_WITH_CONFLICTS = ColorKey.createColorKey("MT_MERGED_WITH_CONFLICTS", ColorUtil.fromHex("#BC3F3C"));
  public static final ColorKey MERGED_WITH_BOTH_CONFLICTS = ColorKey.createColorKey("MT_MERGED_WITH_BOTH_CONFLICTS", ColorUtil.fromHex
      ("#BC3F3C"));
  public static final ColorKey MERGED_WITH_PROPERTY_CONFLICTS = ColorKey.createColorKey("MT_MERGED_WITH_PROPERTY_CONFLICTS", ColorUtil
      .fromHex("#BC3F3C"));
  public static final ColorKey DELETED_FROM_FS = ColorKey.createColorKey("MT_DELETED_FROM_FS", ColorUtil.fromHex("#626669"));
  public static final ColorKey SWITCHED = ColorKey.createColorKey("MT_SWITCHED", ColorUtil.fromHex("#F77669"));
  public static final ColorKey OBSOLETE = ColorKey.createColorKey("MT_OBSOLETE", ColorUtil.fromHex("#FFCB6B"));
  public static final ColorKey SUPPRESSED = ColorKey.createColorKey("MT_SUPPRESSED", ColorUtil.fromHex("#3C3F41"));

  public static final HashMap<FileStatus, ColorKey> FILE_STATUS_COLOR_MAP;

  static {
    FILE_STATUS_COLOR_MAP = new HashMap<>(18);
    // Load all registered file statuses and read their colors from the properties
    final FileStatus[] allFileStatuses = FileStatusFactory.getInstance().getAllFileStatuses();
    for (final FileStatus allFileStatus : allFileStatuses) {
      final Color originalColor = allFileStatus.getColor();
      final Color property = UIManager.getColor("material.file." + allFileStatus.getId().toLowerCase());

      Color color = property == null ? originalColor : property;

      if (color == null) {
        color = EditorColorsManager.getInstance().getGlobalScheme().getDefaultForeground();
      }
      // Add to the map
      FILE_STATUS_COLOR_MAP.put(allFileStatus, ColorKey.createColorKey("MT_" + allFileStatus.getId(), color));
    }

  }

  private MTFileColors() {
  }

  public static Color get(final FileStatus status) {
    final EditorColorsScheme globalScheme = EditorColorsManager.getInstance().getGlobalScheme();

    //    if (status == FileStatus.MODIFIED) {
    //      return ColorUtil.fromHex(MTConfig.getInstance().getAccentColor());
    //    }

    final ColorKey colorKey = MTFileColors.FILE_STATUS_COLOR_MAP.get(status);
    if (colorKey != null) {
      return globalScheme.getColor(colorKey);
    }

    return globalScheme.getDefaultForeground();
  }

  public static ColorKey getColorKey(final FileStatus status) {
    return MTFileColors.FILE_STATUS_COLOR_MAP.get(status);
  }
}
