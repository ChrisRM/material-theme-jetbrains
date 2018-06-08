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

package com.chrisrm.idea.schemes;

import com.chrisrm.idea.MTConfig;
import com.chrisrm.idea.messages.FileColorsBundle;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.colors.ColorKey;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.editor.colors.impl.AbstractColorsScheme;
import com.intellij.openapi.editor.colors.impl.EditorColorsManagerImpl;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.vcs.FileStatus;
import com.intellij.openapi.vcs.FileStatusFactory;
import com.intellij.openapi.vcs.FileStatusManager;
import com.intellij.ui.ColorUtil;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.HashMap;
import java.util.Objects;

import static com.chrisrm.idea.config.MTFileColorsPage.DIRECTORIES;

public final class MTFileColors {
  public static final String MT_PREFIX = "FILESTATUS_";
  private static HashMap<FileStatus, ColorKey> fileStatusColorKeyHashMap;

  static {
    initFileColors();

    // Listen for color scheme changes and update the file colors
    ApplicationManager.getApplication().getMessageBus().connect()
                      .subscribe(EditorColorsManager.TOPIC, scheme -> apply());

    apply();
  }

  private static void apply() {
    applyFileStatuses();
    applyStyleDirectories();
  }

  @NotNull
  private static EditorColorsScheme getCurrentSchemeForCurrentUITheme() {
    return EditorColorsManager.getInstance().getSchemeForCurrentUITheme();
  }

  public static void applyFileStatuses() {
    if (!MTConfig.getInstance().isFileStatusColorsEnabled()) {
      return;
    }

    final EditorColorsScheme defaultScheme = getCurrentSchemeForCurrentUITheme();
    final EditorColorsScheme globalScheme = EditorColorsManagerImpl.getInstance().getGlobalScheme();
    final FileStatus[] allFileStatuses = FileStatusFactory.getInstance().getAllFileStatuses();

    for (final FileStatus allFileStatus : allFileStatuses) {
      defaultScheme.setColor(allFileStatus.getColorKey(), globalScheme.getColor(allFileStatus.getColorKey()));
    }
    ((AbstractColorsScheme) defaultScheme).setSaveNeeded(true);

    for (final Project project : ProjectManager.getInstance().getOpenProjects()) {
      FileStatusManager.getInstance(project).fileStatusesChanged();
    }
  }

  public static void applyStyleDirectories() {
    if (!MTConfig.getInstance().getIsBoldTabs()) {
      return;
    }

    final EditorColorsScheme defaultScheme = getCurrentSchemeForCurrentUITheme();
    final EditorColorsScheme globalScheme = EditorColorsManagerImpl.getInstance().getGlobalScheme();

    defaultScheme.setAttributes(DIRECTORIES, globalScheme.getAttributes(DIRECTORIES));
    ((AbstractColorsScheme) defaultScheme).setSaveNeeded(true);

    for (final Project project : ProjectManager.getInstance().getOpenProjects()) {
      FileStatusManager.getInstance(project).fileStatusesChanged();
    }
  }

  public static void initFileColors() {
    fileStatusColorKeyHashMap = new HashMap<>(18);
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
        fileStatusColorKeyHashMap.put(allFileStatus, ColorKey.createColorKey(MT_PREFIX + allFileStatus.getId(), color));
      } else {
        // 3. If there is no default file color
        // 3a. Get custom file color from the bundle
        final String property = FileColorsBundle.messageOrDefault("material.file." + allFileStatus.getId().toLowerCase(), "-1");
        // If not found do not add the color to the map
        if (Objects.equals(property, "-1")) {
          fileStatusColorKeyHashMap.put(allFileStatus, ColorKey.createColorKey(MT_PREFIX + allFileStatus.getId()));
          continue;
        }

        // 3b. add custom color to the map
        final Color color = ColorUtil.fromHex(property);
        fileStatusColorKeyHashMap.put(allFileStatus, ColorKey.createColorKey(MT_PREFIX + allFileStatus.getId(), color));
      }
    }
  }

  private MTFileColors() {
  }

  public static Color get(final FileStatus status) {
    final EditorColorsScheme globalScheme = EditorColorsManager.getInstance().getGlobalScheme();

    final ColorKey colorKey = MTFileColors.fileStatusColorKeyHashMap.get(status);
    if (colorKey != null) {
      return globalScheme.getColor(colorKey);
    }

    return globalScheme.getDefaultForeground();
  }

  public static ColorKey getColorKey(final FileStatus status) {
    return MTFileColors.fileStatusColorKeyHashMap.get(status);
  }
}
