package com.chrisrm.idea.schemes;

import com.chrisrm.idea.MTConfig;
import com.intellij.openapi.vcs.FileStatus;
import com.intellij.ui.ColorUtil;

import java.awt.*;
import java.util.HashMap;

public class MTFileColors {
  private static final Color NOT_CHANGED_IMMEDIATE = ColorUtil.fromHex("#80CBC4");
  private static final Color NOT_CHANGED_RECURSIVE = ColorUtil.fromHex("#80CBC4");
  private static final Color DELETED = ColorUtil.fromHex("#F77669");
  private static final Color MODIFIED = ColorUtil.fromHex("#80CBC4");
  private static final Color ADDED = ColorUtil.fromHex("#C3E887");
  private static final Color MERGE = ColorUtil.fromHex("#C792EA");
  private static final Color UNKNOWN = ColorUtil.fromHex("#F77669");
  private static final Color IGNORED = ColorUtil.fromHex("#B0BEC5");
  private static final Color HIJACKED = ColorUtil.fromHex("#FFCB6B");
  private static final Color MERGED_WITH_CONFLICTS = ColorUtil.fromHex("#BC3F3C");
  private static final Color MERGED_WITH_BOTH_CONFLICTS = ColorUtil.fromHex("#BC3F3C");
  private static final Color MERGED_WITH_PROPERTY_CONFLICTS = ColorUtil.fromHex("#BC3F3C");
  private static final Color DELETED_FROM_FS = ColorUtil.fromHex("#626669");
  private static final Color SWITCHED = ColorUtil.fromHex("#F77669");
  private static final Color OBSOLETE = ColorUtil.fromHex("#FFCB6B");
  private static final Color SUPPRESSED = ColorUtil.fromHex("#3C3F41");

  public static final HashMap<FileStatus, Color> fileStatusColorMap;

  static {
    fileStatusColorMap = new HashMap<>(18);
    // TODO move into a properties file ?
    fileStatusColorMap.put(FileStatus.NOT_CHANGED_IMMEDIATE, MTFileColors.NOT_CHANGED_IMMEDIATE);
    fileStatusColorMap.put(FileStatus.NOT_CHANGED_RECURSIVE, MTFileColors.NOT_CHANGED_RECURSIVE);
    fileStatusColorMap.put(FileStatus.DELETED, MTFileColors.DELETED);
    fileStatusColorMap.put(FileStatus.MODIFIED, MTFileColors.MODIFIED);
    fileStatusColorMap.put(FileStatus.ADDED, MTFileColors.ADDED);
    fileStatusColorMap.put(FileStatus.MERGE, MTFileColors.MERGE);
    fileStatusColorMap.put(FileStatus.UNKNOWN, MTFileColors.UNKNOWN);
    fileStatusColorMap.put(FileStatus.IGNORED, MTFileColors.IGNORED);
    fileStatusColorMap.put(FileStatus.HIJACKED, MTFileColors.HIJACKED);
    fileStatusColorMap.put(FileStatus.MERGED_WITH_CONFLICTS, MTFileColors.MERGED_WITH_CONFLICTS);
    fileStatusColorMap.put(FileStatus.MERGED_WITH_BOTH_CONFLICTS, MTFileColors.MERGED_WITH_BOTH_CONFLICTS);
    fileStatusColorMap.put(FileStatus.MERGED_WITH_PROPERTY_CONFLICTS, MTFileColors.MERGED_WITH_PROPERTY_CONFLICTS);
    fileStatusColorMap.put(FileStatus.DELETED_FROM_FS, MTFileColors.DELETED_FROM_FS);
    fileStatusColorMap.put(FileStatus.SWITCHED, MTFileColors.SWITCHED);
    fileStatusColorMap.put(FileStatus.OBSOLETE, MTFileColors.OBSOLETE);
    fileStatusColorMap.put(FileStatus.SUPPRESSED, MTFileColors.SUPPRESSED);
  }

  public static Color get(FileStatus status) {
    if (status == FileStatus.MODIFIED) {
      return ColorUtil.fromHex(MTConfig.getInstance().getAccentColor());
    }
    return MTFileColors.fileStatusColorMap.get(status);
  }
}
