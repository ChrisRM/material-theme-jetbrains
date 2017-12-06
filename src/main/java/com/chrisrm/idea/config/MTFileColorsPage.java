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

package com.chrisrm.idea.config;

import com.chrisrm.idea.messages.MaterialThemeBundle;
import com.chrisrm.idea.schemes.MTFileColors;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.colors.impl.AbstractColorsScheme;
import com.intellij.openapi.editor.colors.impl.EditorColorsManagerImpl;
import com.intellij.openapi.fileTypes.PlainSyntaxHighlighter;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.vcs.FileStatus;
import com.intellij.openapi.vcs.FileStatusFactory;
import com.intellij.openapi.vcs.FileStatusManager;
import com.intellij.psi.codeStyle.DisplayPriority;
import com.intellij.psi.codeStyle.DisplayPrioritySortable;
import com.intellij.util.ArrayUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.chrisrm.idea.schemes.MTFileColors.initFileColors;

public final class MTFileColorsPage implements ColorSettingsPage, DisplayPrioritySortable {
  private static final ColorDescriptor[] DESCRIPTORS;

  static {
    initFileColors();

    final FileStatus[] allFileStatuses = FileStatusFactory.getInstance().getAllFileStatuses();
    final List<ColorDescriptor> colorDescriptors = new ArrayList<>(allFileStatuses.length);

    for (final FileStatus allFileStatus : allFileStatuses) {
      // mt color descriptors
      colorDescriptors.add(new ColorDescriptor(allFileStatus.getText(),
          MTFileColors.getColorKey(allFileStatus),
          ColorDescriptor.Kind.FOREGROUND));
    }
    DESCRIPTORS = ArrayUtil.toObjectArray(colorDescriptors, ColorDescriptor.class);


  }

  @NotNull
  private static EditorColorsScheme getCurrentSchemeForCurrentUITheme() {
    return EditorColorsManager.getInstance().getSchemeForCurrentUITheme();
  }

  public static void apply() {
    final EditorColorsScheme defaultScheme = getCurrentSchemeForCurrentUITheme();
    final EditorColorsScheme globalScheme = EditorColorsManagerImpl.getInstance().getGlobalScheme();
    for (final ColorDescriptor descriptor : DESCRIPTORS) {
      defaultScheme.setColor(descriptor.getKey(), globalScheme.getColor(descriptor.getKey()));
    }
    ((AbstractColorsScheme) defaultScheme).setSaveNeeded(true);


    for (final Project project : ProjectManager.getInstance().getOpenProjects()) {
      FileStatusManager.getInstance(project).fileStatusesChanged();
    }
  }

  @NotNull
  @Override
  public AttributesDescriptor[] getAttributeDescriptors() {
    return new AttributesDescriptor[0];
  }

  @NotNull
  @Override
  public ColorDescriptor[] getColorDescriptors() {
    return DESCRIPTORS;
  }

  @NotNull
  @Override
  public String getDisplayName() {
    return MaterialThemeBundle.message("MTFileColors.colors.page.name");
  }

  @Override
  public DisplayPriority getPriority() {
    return DisplayPriority.COMMON_SETTINGS;
  }

  @Override
  public Icon getIcon() {
    return IconLoader.getIcon("/icons/actions/material-theme.png");
  }

  @NotNull
  @Override
  public SyntaxHighlighter getHighlighter() {
    return new PlainSyntaxHighlighter();
  }

  @NotNull
  @Override
  public String getDemoText() {
    return " ";
  }

  @Nullable
  @Override
  public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
    return null;
  }
}
