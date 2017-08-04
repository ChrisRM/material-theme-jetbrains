package com.chrisrm.idea.schemes;

import com.chrisrm.idea.messages.FileColorsBundle;
import com.intellij.openapi.editor.colors.ColorKey;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.vcs.FileStatus;
import com.intellij.openapi.vcs.FileStatusFactory;
import com.intellij.ui.ColorUtil;

import java.awt.*;
import java.util.HashMap;
import java.util.Objects;

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
      // 1. Get the original file color
      final Color originalColor = allFileStatus.getColor();
      if (originalColor != null) {
        // 2. if there is an original file color
        final String originalColorString = ColorUtil.toHex(originalColor);
        // 2a. Get custom file color from the bundle, or default to original file color
        final String property = FileColorsBundle.messageOrDefault("material.file." + allFileStatus.getId().toLowerCase(),
            originalColorString);
        final Color color = ColorUtil.fromHex(property == null ? originalColorString : property);

        // 2b. Set in the map the custom/default file color
        FILE_STATUS_COLOR_MAP.put(allFileStatus, ColorKey.createColorKey("MT_" + allFileStatus.getId(), color));
      } else {
        // 3. If there is no default file color
        // 3a. Get custom file color from the bundle
        final String property = FileColorsBundle.messageOrDefault("material.file." + allFileStatus.getId().toLowerCase(), "-1");
        // If not found do not add the color to the map
        if (Objects.equals(property, "-1")) {
          continue;
        }

        // 3b. add custom color to the map
        final Color color = ColorUtil.fromHex(property);
        FILE_STATUS_COLOR_MAP.put(allFileStatus, ColorKey.createColorKey("MT_" + allFileStatus.getId(), color));
      }
    }

  }

  private MTFileColors() {
  }

  public static Color get(final FileStatus status) {
    final EditorColorsScheme globalScheme = EditorColorsManager.getInstance().getGlobalScheme();

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
