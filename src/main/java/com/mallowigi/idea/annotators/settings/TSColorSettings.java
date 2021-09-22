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

package com.mallowigi.idea.annotators.settings;

import com.intellij.icons.AllIcons;
import com.intellij.lang.Language;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.util.ObjectUtils;
import com.mallowigi.idea.annotators.TSAnnotator;
import com.mallowigi.idea.messages.LanguageAdditionsBundle;
import gnu.trove.THashMap;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.Collections;
import java.util.Map;

@SuppressWarnings({"DuplicateStringLiteralInspection",
  "HardCodedStringLiteral"
})
public final class TSColorSettings extends JSColorSettings {
  @NotNull
  @NonNls
  private static final AttributesDescriptor[] TS_ATTRIBUTES;
  @NonNls
  private static final Map<String, TextAttributesKey> TS_DESCRIPTORS = new THashMap<>();

  private static final TextAttributesKey TS_KEYWORD = TSAnnotator.KEYWORD;
  private static final TextAttributesKey TS_NUMBER = TSAnnotator.NUMBER;

  private static final TextAttributesKey PRIVATE = TSAnnotator.PRIVATE;
  private static final TextAttributesKey DECLARE = TSAnnotator.DECLARE;
  private static final TextAttributesKey TYPE_ALIAS = TSAnnotator.TYPE_ALIAS;
  private static final TextAttributesKey ANY = TSAnnotator.ANY;
  private static final TextAttributesKey INLINE = TSAnnotator.INLINE;
  private static final TextAttributesKey ENUM = TSAnnotator.ENUM;
  private static final TextAttributesKey PRIM_TYPE = TSAnnotator.PRIM_TYPE;

  static {
    TS_ATTRIBUTES = new AttributesDescriptor[]{
      new AttributesDescriptor(LanguageAdditionsBundle.message("keywords.visit.js"), TextAttributesKey.createTextAttributesKey("")),
      new AttributesDescriptor(LanguageAdditionsBundle.message("keywords.private.public.protected"), PRIVATE),
      new AttributesDescriptor(LanguageAdditionsBundle.message("keywords.declare"), DECLARE),
      new AttributesDescriptor(LanguageAdditionsBundle.message("keywords.type.alias"), TYPE_ALIAS),
      new AttributesDescriptor(LanguageAdditionsBundle.message("keywords.ts.inline"), INLINE),
      new AttributesDescriptor(LanguageAdditionsBundle.message("keywords.enum"), ENUM),
      new AttributesDescriptor(LanguageAdditionsBundle.message("types.any.unknown"), ANY),
      new AttributesDescriptor(LanguageAdditionsBundle.message("types.primitives"), PRIM_TYPE),

    };

    TS_DESCRIPTORS.putAll(createAdditionalHlAttrs());
    TS_DESCRIPTORS.putAll(JSColorSettings.JS_DESCRIPTORS);
  }

  @NotNull
  private static Map<String, TextAttributesKey> createAdditionalHlAttrs() {
    final Map<String, TextAttributesKey> descriptors = new THashMap<>();
    descriptors.put("private", PRIVATE);
    descriptors.put("declare", DECLARE);
    descriptors.put("type", TYPE_ALIAS);
    descriptors.put("any", ANY);
    descriptors.put("inline2", INLINE);
    descriptors.put("enum", ENUM);
    descriptors.put("primtype", PRIM_TYPE);

    return descriptors;
  }

  @NotNull
  @Override
  public Icon getIcon() {
    return AllIcons.FileTypes.Any_type;
  }

  @NotNull
  @Override
  public SyntaxHighlighter getHighlighter() {
    final Language lang = ObjectUtils.notNull(Language.findLanguageByID("TypeScript"), Language.ANY);
    return getSyntaxHighlighterWithFallback(lang);
  }

  @NonNls
  @NotNull
  @Override
  public String getDemoText() {
    return LanguageAdditionsBundle.message("TSColorPage.demoText");
  }

  @NotNull
  @Override
  public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
    return Collections.unmodifiableMap(TS_DESCRIPTORS);
  }

  @NotNull
  @Override
  public AttributesDescriptor[] getAttributeDescriptors() {
    return TS_ATTRIBUTES;
  }

  @NotNull
  @Override
  public String getDisplayName() {
    return LanguageAdditionsBundle.message("TSColorPage.ts.additions");
  }

}
