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
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.psi.codeStyle.DisplayPriority;
import com.intellij.util.ObjectUtils;
import com.intellij.util.PlatformUtils;
import com.mallowigi.idea.annotators.JSAnnotator;
import com.mallowigi.idea.messages.MaterialThemeBundle;
import gnu.trove.THashMap;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.Collections;
import java.util.Map;

@SuppressWarnings({"DuplicateStringLiteralInspection",
  "ClassWithTooManyFields",
  "DialogTitleCapitalization"})
public final class JSColorSettings extends BaseColorSettings {
  @NotNull
  @NonNls
  private static final AttributesDescriptor[] JS_ATTRIBUTES;
  @NonNls
  static final Map<String, TextAttributesKey> JS_DESCRIPTORS = new THashMap<>();

  private static final TextAttributesKey JS_KEYWORD = JSAnnotator.JS_KEYWORD;
  private static final TextAttributesKey JS_NUMBER = JSAnnotator.JS_NUMBER;
  private static final TextAttributesKey VARIABLE = ObjectUtils.notNull(TextAttributesKey.find("JS.LOCAL_VARIABLE"),
    DefaultLanguageHighlighterColors.LOCAL_VARIABLE);
  private static final TextAttributesKey FUNCTION = JSAnnotator.FUNCTION;
  private static final TextAttributesKey THIS_SUPER = JSAnnotator.THIS_SUPER;
  private static final TextAttributesKey MODULE = JSAnnotator.MODULE;
  private static final TextAttributesKey CONSOLE = JSAnnotator.CONSOLE;
  private static final TextAttributesKey DEBUGGER = JSAnnotator.DEBUGGER;
  private static final TextAttributesKey NULL = JSAnnotator.NULL;
  private static final TextAttributesKey PRIMITIVE = JSAnnotator.PRIMITIVE;
  private static final TextAttributesKey VAL = JSAnnotator.VAL;
  private static final TextAttributesKey FUNCTION_NAME = JSAnnotator.FUNCTION;

  static {
    JS_ATTRIBUTES = new AttributesDescriptor[]{
      new AttributesDescriptor(MaterialThemeBundle.message("keywords.this.super"), THIS_SUPER),
      new AttributesDescriptor(MaterialThemeBundle.message("keywords.module.import.export.from"), MODULE),
      new AttributesDescriptor(MaterialThemeBundle.message("keywords.debugger"), DEBUGGER),
      new AttributesDescriptor(MaterialThemeBundle.message("keywords.null.undefined"), NULL),
      new AttributesDescriptor(MaterialThemeBundle.message("keywords.true.false"), PRIMITIVE),
      new AttributesDescriptor(MaterialThemeBundle.message("keywords.var.let.const"), VAL),
      new AttributesDescriptor(MaterialThemeBundle.message("keywords.var.console"), CONSOLE),
      new AttributesDescriptor(MaterialThemeBundle.message("keywords.function"), FUNCTION),
    };

    JS_DESCRIPTORS.putAll(createAdditionalHlAttrs());
  }

  @NotNull
  private static Map<String, TextAttributesKey> createAdditionalHlAttrs() {
    @NonNls final Map<String, TextAttributesKey> descriptors = new THashMap<>();
    descriptors.put("string", DefaultLanguageHighlighterColors.STRING);
    descriptors.put("keyword", JS_KEYWORD);
    descriptors.put("function", FUNCTION);
    descriptors.put("function_name", FUNCTION_NAME);
    descriptors.put("val", VAL);
    descriptors.put("local_variable", VARIABLE);

    descriptors.put("this", THIS_SUPER);
    descriptors.put("null", NULL);
    descriptors.put("primitive", PRIMITIVE);
    descriptors.put("debugger", DEBUGGER);
    descriptors.put("import", MODULE);
    descriptors.put("console", CONSOLE);
    descriptors.put("number", JS_NUMBER);
    descriptors.put("inst_field", DefaultLanguageHighlighterColors.INSTANCE_FIELD);

    return descriptors;
  }

  @NotNull
  @Override
  public Icon getIcon() {
    return AllIcons.FileTypes.JavaScript;
  }

  @NotNull
  @Override
  public SyntaxHighlighter getHighlighter() {
    @NonNls final Language lang = ObjectUtils.notNull(Language.findLanguageByID("JavaScript"), Language.ANY);
    return getSyntaxHighlighterWithFallback(lang);
  }

  @NonNls
  @NotNull
  @Override
  public String getDemoText() {
    return
      "<import>import</import> <local_variable>_</local_variable> <import>from</import> <string>'lodash'</string>;\n" +
        "\n" +
        "<function>function</function> <function_name>foo</function_name>() {\n" +
        "  <val>var</val> <local_variable>x</local_variable> = <number>10</number>;\n" +
        "  <this>this</this>.<inst_field>x</inst_field> = <null>null</null>;\n" +
        "  <keyword>if</keyword> (<local_variable>x</local_variable> === <null>undefined</null>) {\n" +
        "    <console>console</console>.<function>log</function>(<string>'foo'</string>);\n" +
        "    <debugger>debugger</debugger>;\n" +
        "    <keyword>return</keyword> <primitive>false</primitive>;\n" +
        "  }\n" +
        "  " +
        "<keyword>return</keyword> <primitive>true</primitive>;\n" +
        "}\n";
  }

  @NotNull
  @Override
  public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
    return Collections.unmodifiableMap(JS_DESCRIPTORS);
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
    return MaterialThemeBundle.message("JSColorPage.java.additions");
  }

  @NotNull
  @Override
  public DisplayPriority getPriority() {
    return PlatformUtils.isWebStorm() ? DisplayPriority.KEY_LANGUAGE_SETTINGS : DisplayPriority.LANGUAGE_SETTINGS;
  }
}
