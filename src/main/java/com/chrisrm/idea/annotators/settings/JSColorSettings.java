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

import com.chrisrm.idea.annotators.JSAnnotator;
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

public final class JSColorSettings extends BaseColorSettings {
  @NonNls
  private static final AttributesDescriptor[] JS_ATTRIBUTES;
  @NonNls
  static final Map<String, TextAttributesKey> JS_DESCRIPTORS = new THashMap<>();

  private static final TextAttributesKey JSKEYWORD = ObjectUtils.notNull(TextAttributesKey.find("JS.KEYWORD"),
      DefaultLanguageHighlighterColors.KEYWORD);
  private static final TextAttributesKey VARIABLE = ObjectUtils.notNull(TextAttributesKey.find("JS.LOCAL_VARIABLE"),
      DefaultLanguageHighlighterColors.LOCAL_VARIABLE);
  private static final TextAttributesKey FUNCTION = JSAnnotator.FUNCTION;
  private static final TextAttributesKey THIS_SUPER = JSAnnotator.THIS_SUPER;
  private static final TextAttributesKey MODULE = JSAnnotator.MODULE;
  private static final TextAttributesKey DEBUGGER = JSAnnotator.DEBUGGER;
  private static final TextAttributesKey NULL = JSAnnotator.NULL;
  private static final TextAttributesKey VAL = JSAnnotator.VAL;
  private static final TextAttributesKey FUNCTION_NAME = JSAnnotator.FUNCTION;

  static {
    JS_ATTRIBUTES = new AttributesDescriptor[]{
        new AttributesDescriptor("Keywords: this, super", JSColorSettings.THIS_SUPER),
        new AttributesDescriptor("Keywords: module, import, export, from", JSColorSettings.MODULE),
        new AttributesDescriptor("Keywords: debugger", JSColorSettings.DEBUGGER),
        new AttributesDescriptor("Keywords: null, undefined", JSColorSettings.NULL),
        new AttributesDescriptor("Keywords: var, let, const", JSColorSettings.VAL),
        new AttributesDescriptor("Keywords: function", JSColorSettings.FUNCTION),
    };

    JSColorSettings.JS_DESCRIPTORS.putAll(JSColorSettings.createAdditionalHlAttrs());
  }

  private static Map<String, TextAttributesKey> createAdditionalHlAttrs() {
    final Map<String, TextAttributesKey> descriptors = new THashMap<>();
    descriptors.put("keyword", JSColorSettings.JSKEYWORD);
    descriptors.put("function", JSColorSettings.FUNCTION);
    descriptors.put("function_name", JSColorSettings.FUNCTION_NAME);
    descriptors.put("val", JSColorSettings.VAL);
    descriptors.put("local_variable", JSColorSettings.VARIABLE);
    descriptors.put("this", JSColorSettings.THIS_SUPER);
    descriptors.put("null", JSColorSettings.NULL);
    descriptors.put("debugger", JSColorSettings.DEBUGGER);
    descriptors.put("import", JSColorSettings.MODULE);

    return descriptors;
  }

  @Nullable
  @Override
  public Icon getIcon() {
    return AllIcons.FileTypes.JavaScript;
  }

  @NotNull
  @Override
  public SyntaxHighlighter getHighlighter() {
    final Language lang = ObjectUtils.notNull(Language.findLanguageByID("JavaScript"), Language.ANY);
    return getSyntaxHighlighterWithFallback(lang);
  }

  @NotNull
  @Override
  public String getDemoText() {
    return "<import>import</import> {_} <import>from</import> 'lodash';\n\n" +
        "<function>function</function> <function_name>foo</function_name>() {\n" +
        "  <val>var</val> <local_variable>x</local_variable> = 10;\n" +
        "  <this>this</this>.x = <null>null</null>;\n" +
        "  <keyword>if</keyword> (<local_variable>x</local_variable> === <null>undefined</null>) {\n" +
        "    <debugger>debugger</debugger>;\n" +
        "  }\n" +
        "}";
  }

  @Nullable
  @Override
  public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
    return JS_DESCRIPTORS;
  }

  @NotNull
  @Override
  public AttributesDescriptor[] getAttributeDescriptors() {
    return JS_ATTRIBUTES;
  }

  @NotNull
  @Override
  public ColorDescriptor[] getColorDescriptors() {
    return ColorDescriptor.EMPTY_ARRAY;
  }

  @NotNull
  @Override
  public String getDisplayName() {
    return "JavaScript Additions";
  }

  @Override
  public DisplayPriority getPriority() {
    return PlatformUtils.isWebStorm() ? DisplayPriority.KEY_LANGUAGE_SETTINGS : DisplayPriority.LANGUAGE_SETTINGS;
  }
}
