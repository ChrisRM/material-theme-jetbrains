/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Chris Magnussen and Elior Boukhobza
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

package com.chrisrm.idea.annotators.settings;

import com.chrisrm.idea.annotators.TSAnnotator;
import com.intellij.icons.AllIcons;
import com.intellij.lang.Language;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.psi.codeStyle.DisplayPriority;
import com.intellij.util.ObjectUtils;
import com.intellij.util.PlatformUtils;
import gnu.trove.THashMap;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

public final class TSColorSettings extends BaseColorSettings {
  @NonNls
  private static final AttributesDescriptor[] TS_ATTRIBUTES;
  @NonNls
  private static final Map<String, TextAttributesKey> TS_DESCRIPTORS = new THashMap<>();

  private static final TextAttributesKey PRIVATE = TSAnnotator.PRIVATE;

  static {
    TS_ATTRIBUTES = new AttributesDescriptor[]{
        new AttributesDescriptor("Keywords: private, public, protected", PRIVATE),
    };

    TS_DESCRIPTORS.putAll(createAdditionalHlAttrs());
    TS_DESCRIPTORS.putAll(JSColorSettings.JS_DESCRIPTORS);
  }

  private static Map<String, TextAttributesKey> createAdditionalHlAttrs() {
    final Map<String, TextAttributesKey> descriptors = new THashMap<>();
    descriptors.put("private", PRIVATE);
    descriptors.put("class", DefaultLanguageHighlighterColors.CLASS_NAME);

    return descriptors;
  }

  @Nullable
  @Override
  public Icon getIcon() {
    return AllIcons.FileTypes.TypeScript;
  }

  @NotNull
  @Override
  public SyntaxHighlighter getHighlighter() {
    final Language lang = ObjectUtils.notNull(Language.findLanguageByID("TypeScript"), Language.ANY);
    return getSyntaxHighlighterWithFallback(lang);
  }

  @NotNull
  @Override
  public String getDemoText() {
    return "<import>import</import> {_} <import>from</import> 'lodash';\n\n" +
        "<keyword>class</keyword> <class>MyType</class> <keyword>extends</keyword> <class>AbstractClass</class> {\n" +
        "  <private>private</private> <local_variable>field</local_variable>: <class>String</class>;\n" +
        "  <private>protected</private> <local_variable>protect</local_variable>: <class>Number</class>;\n" +
        "  <private>public</private> <local_variable>num</local_variable> = 10;\n" +
        "}";
  }

  @Nullable
  @Override
  public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
    return TS_DESCRIPTORS;
  }

  @NotNull
  @Override
  public AttributesDescriptor[] getAttributeDescriptors() {
    return TS_ATTRIBUTES;
  }

  @NotNull
  @Override
  public ColorDescriptor[] getColorDescriptors() {
    return ColorDescriptor.EMPTY_ARRAY;
  }

  @NotNull
  @Override
  public String getDisplayName() {
    return "TypeScript Additions";
  }

  @Override
  public DisplayPriority getPriority() {
    return PlatformUtils.isWebStorm() ? DisplayPriority.KEY_LANGUAGE_SETTINGS : DisplayPriority.LANGUAGE_SETTINGS;
  }
}
