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

package com.mallowigi.idea.config;

import com.google.common.collect.Sets;
import com.intellij.openapi.editor.colors.ColorKey;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.PlainSyntaxHighlighter;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import com.intellij.openapi.util.SystemInfoRt;
import com.intellij.psi.codeStyle.DisplayPriority;
import com.intellij.psi.codeStyle.DisplayPrioritySortable;
import com.intellij.util.ArrayUtil;
import com.mallowigi.idea.messages.MaterialThemeBundle;
import com.mallowigi.idea.themes.lists.AccentResources;
import com.mallowigi.idea.themes.lists.MTThemeResources;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@SuppressWarnings("ObjectAllocationInLoop")
public final class MTScrollbarsPage implements ColorSettingsPage, DisplayPrioritySortable {
  private static final ColorDescriptor[] DESCRIPTORS;

  static {
    final Collection<ColorDescriptor> colorDescriptors = new ArrayList<>(8);

    final Sets.SetView<String> thumbSets = Sets.union(
      AccentResources.SCROLLBAR_HOVER_RESOURCES,
      AccentResources.SCROLLBAR_RESOURCES);

    final Sets.SetView<String> allSets = Sets.union(
      thumbSets,
      MTThemeResources.SCROLLBAR_RESOURCES);

    for (@NonNls final String resource : allSets) {
      if (resource.contains("Mac.")) {
        if (SystemInfoRt.isMac) {
          colorDescriptors.add(new ColorDescriptor(
            MaterialThemeBundle.message("mac.material.scrollbars." + resource),
            ColorKey.find(resource),
            ColorDescriptor.Kind.BACKGROUND));
        }
      } else {
        if (!SystemInfoRt.isMac) {
          colorDescriptors.add(new ColorDescriptor(
            MaterialThemeBundle.message("material.scrollbars." + resource),
            ColorKey.find(resource),
            ColorDescriptor.Kind.BACKGROUND));
        }
      }
    }
    DESCRIPTORS = ArrayUtil.toObjectArray(colorDescriptors, ColorDescriptor.class);
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

  @NonNls
  @NotNull
  @Override
  public String getDisplayName() {
    return MaterialThemeBundle.message("MTScrollbars.title");
  }

  @Override
  public DisplayPriority getPriority() {
    return DisplayPriority.COMMON_SETTINGS;
  }

  @Nullable
  @Override
  public Icon getIcon() {
    return null;
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
