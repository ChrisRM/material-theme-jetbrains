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

package com.mallowigi.idea.schemes;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.colors.ColorKey;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.editor.colors.impl.AbstractColorsScheme;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.vcs.FileStatus;
import com.intellij.openapi.vcs.FileStatusFactory;
import com.intellij.openapi.vcs.FileStatusManager;
import com.intellij.ui.ColorUtil;
import com.mallowigi.idea.MTConfig;
import com.mallowigi.idea.config.MTFileColorsPage;
import com.mallowigi.idea.messages.MaterialThemeBundle;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Objects;

@SuppressWarnings("ContinueStatement")
public enum MTFileColors {
  DEFAULT;
  @NonNls
  private static final String MT_PREFIX = "MT_FILESTATUS_";
  private static final HashMap<FileStatus, ColorKey> COLOR_KEYS = new HashMap<>(18);

  static {
    initFileColors();

    // Listen for color scheme changes and update the file colors
    ApplicationManager.getApplication().getMessageBus().connect()
                      .subscribe(EditorColorsManager.TOPIC, scheme -> apply());

    apply();
  }

  private static void apply() {
    if (MTConfig.getInstance().isFileStatusColorsEnabled()) {
      applyFileStatuses();
    }
    applyStyleDirectories();
  }

  @NotNull
  private static EditorColorsScheme getCurrentSchemeForCurrentUITheme() {
    return EditorColorsManager.getInstance().getSchemeForCurrentUITheme();
  }

  @SuppressWarnings("MethodWithMultipleLoops")
  private static void applyFileStatuses() {
    final EditorColorsScheme defaultScheme = getCurrentSchemeForCurrentUITheme();
    final FileStatus[] allFileStatuses = FileStatusFactory.getInstance().getAllFileStatuses();

    for (final FileStatus fileStatus : allFileStatuses) {
      final ColorKey mtColorKey = getColorKey(fileStatus);
      if (mtColorKey != null) {
        final Color color = defaultScheme.getColor(mtColorKey);
        if (color != null) {
          defaultScheme.setColor(fileStatus.getColorKey(), color);
        }
      }
    }
    ((AbstractColorsScheme) defaultScheme).setSaveNeeded(true);

    for (final Project project : ProjectManager.getInstance().getOpenProjects()) {
      FileStatusManager.getInstance(project).fileStatusesChanged();
    }
  }

  private static void applyStyleDirectories() {
    if (!MTConfig.getInstance().isStyledDirectories()) {
      return;
    }

    final EditorColorsScheme defaultScheme = getCurrentSchemeForCurrentUITheme();
    final EditorColorsScheme globalScheme = EditorColorsManager.getInstance().getGlobalScheme();

    defaultScheme.setAttributes(MTFileColorsPage.DIRECTORIES, globalScheme.getAttributes(MTFileColorsPage.DIRECTORIES));
    ((AbstractColorsScheme) defaultScheme).setSaveNeeded(true);

    for (final Project project : ProjectManager.getInstance().getOpenProjects()) {
      FileStatusManager.getInstance(project).fileStatusesChanged();
    }
  }

  @SuppressWarnings({"DuplicateStringLiteralInspection",
    "ObjectAllocationInLoop"})
  public static void initFileColors() {
    // Load all registered file statuses and read their colors from the properties
    final FileStatus[] allFileStatuses = FileStatusFactory.getInstance().getAllFileStatuses();
    for (final FileStatus allFileStatus : allFileStatuses) {
      // 1. Get the original file color
      final Color originalColor = allFileStatus.getColor();
      if (originalColor != null) {
        // 2. if there is an original file color
        final String originalColorString = ColorUtil.toHex(originalColor);
        // 2a. Get custom file color from the bundle, or default to original file color
        final String property =
          MaterialThemeBundle.INSTANCE.messageOrDefault("material.file." + allFileStatus.getId().toLowerCase(Locale.ENGLISH),
            originalColorString);
        final Color color = ColorUtil.fromHex(property == null ? originalColorString : property);

        // 2b. Set in the map the custom/default file color
        COLOR_KEYS.put(allFileStatus, ColorKey.createColorKey(MT_PREFIX + allFileStatus.getId(), color));
      } else {
        // 3. If there is no default file color
        // 3a. Get custom file color from the bundle
        final String property =
          MaterialThemeBundle.INSTANCE.messageOrDefault("material.file." + allFileStatus.getId().toLowerCase(Locale.ENGLISH), "-1");
        // If not found do not add the color to the map
        if (Objects.equals(property, "-1")) {
          COLOR_KEYS.put(allFileStatus, ColorKey.createColorKey(MT_PREFIX + allFileStatus.getId()));
          continue;
        }

        // 3b. add custom color to the map
        final Color color = ColorUtil.fromHex(property);
        COLOR_KEYS.put(allFileStatus, ColorKey.createColorKey(MT_PREFIX + allFileStatus.getId(), color));
      }
    }
  }

  public static ColorKey getColorKey(final FileStatus status) {
    return COLOR_KEYS.get(status);
  }
}
