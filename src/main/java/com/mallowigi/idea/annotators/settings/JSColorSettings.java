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
import com.mallowigi.idea.messages.LanguageAdditionsBundle;
import gnu.trove.THashMap;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.Collections;
import java.util.Map;

@SuppressWarnings({"DuplicateStringLiteralInspection",
  "ClassWithTooManyFields"
  ,
  "DesignForExtension"})
public class JSColorSettings extends BaseColorSettings {
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
  private static final TextAttributesKey MODULE = JSAnnotator.MODULE_KEYWORD;
  private static final TextAttributesKey CONSOLE = JSAnnotator.CONSOLE;
  private static final TextAttributesKey DEBUGGER = JSAnnotator.DEBUGGER_STMT;
  private static final TextAttributesKey NULL = JSAnnotator.NULL;
  private static final TextAttributesKey PRIMITIVE = JSAnnotator.PRIMITIVE;
  private static final TextAttributesKey VAL = JSAnnotator.VAL;
  private static final TextAttributesKey CLASS = JSAnnotator.CLASS_EXTENDS;
  private static final TextAttributesKey FUNCTION_NAME = DefaultLanguageHighlighterColors.FUNCTION_CALL;
  private static final TextAttributesKey YIELD = JSAnnotator.YIELD;
  private static final TextAttributesKey ASYNC = JSAnnotator.ASYNC_AWAIT;
  private static final TextAttributesKey TRY_CATCH = JSAnnotator.TRY_CATCH;
  private static final TextAttributesKey INLINE = JSAnnotator.INLINE;
  private static final TextAttributesKey NEW = JSAnnotator.NEW;
  private static final TextAttributesKey PROTOTYPE = JSAnnotator.PROTOTYPE;
  private static final TextAttributesKey CONSTRUCTOR = JSAnnotator.CONSTRUCTOR;
  private static final TextAttributesKey IF_ELSE = JSAnnotator.IF_ELSE;
  private static final TextAttributesKey GET_SET = JSAnnotator.GET_SET;

  static {
    JS_ATTRIBUTES = new AttributesDescriptor[]{
      new AttributesDescriptor(LanguageAdditionsBundle.message("keywords.this.super"), THIS_SUPER),
      new AttributesDescriptor(LanguageAdditionsBundle.message("keywords.module.import.export.from"), MODULE),
      new AttributesDescriptor(LanguageAdditionsBundle.message("keywords.debugger"), DEBUGGER),
      new AttributesDescriptor(LanguageAdditionsBundle.message("keywords.null.undefined"), NULL),
      new AttributesDescriptor(LanguageAdditionsBundle.message("keywords.true.false"), PRIMITIVE),
      new AttributesDescriptor(LanguageAdditionsBundle.message("keywords.var.let.const"), VAL),
      new AttributesDescriptor(LanguageAdditionsBundle.message("keywords.if.else"), IF_ELSE),
      new AttributesDescriptor(LanguageAdditionsBundle.message("keywords.class.extends"), CLASS),
      new AttributesDescriptor(LanguageAdditionsBundle.message("keywords.function"), FUNCTION),
      new AttributesDescriptor(LanguageAdditionsBundle.message("keywords.get.set"), GET_SET),
      new AttributesDescriptor(LanguageAdditionsBundle.message("keywords.inline"), INLINE),
      new AttributesDescriptor(LanguageAdditionsBundle.message("keywords.yield"), YIELD),
      new AttributesDescriptor(LanguageAdditionsBundle.message("keywords.new"), NEW),
      new AttributesDescriptor(LanguageAdditionsBundle.message("keywords.async"), ASYNC),
      new AttributesDescriptor(LanguageAdditionsBundle.message("keywords.try.catch"), TRY_CATCH),
      new AttributesDescriptor(LanguageAdditionsBundle.message("keywords.constructor"), CONSTRUCTOR),
      new AttributesDescriptor(LanguageAdditionsBundle.message("globals.var.console"), CONSOLE),
      new AttributesDescriptor(LanguageAdditionsBundle.message("globals.prototype"), PROTOTYPE),
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
    descriptors.put("class", CLASS);
    descriptors.put("class_name", DefaultLanguageHighlighterColors.CLASS_NAME);
    descriptors.put("interface_name", DefaultLanguageHighlighterColors.INTERFACE_NAME);

    descriptors.put("this", THIS_SUPER);
    descriptors.put("null", NULL);
    descriptors.put("primitive", PRIMITIVE);
    descriptors.put("debugger", DEBUGGER);
    descriptors.put("import", MODULE);
    descriptors.put("console", CONSOLE);
    descriptors.put("number", JS_NUMBER);
    descriptors.put("inst_field", DefaultLanguageHighlighterColors.INSTANCE_FIELD);

    descriptors.put("yield", YIELD);
    descriptors.put("new", NEW);
    descriptors.put("throw", NEW);
    descriptors.put("async", ASYNC);
    descriptors.put("try", TRY_CATCH);
    descriptors.put("inline", INLINE);
    descriptors.put("prototype", PROTOTYPE);
    descriptors.put("constructor", CONSTRUCTOR);
    descriptors.put("if_else", IF_ELSE);
    descriptors.put("getter", GET_SET);

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
    return LanguageAdditionsBundle.message("JSColorPage.demoText");
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
  public final ColorDescriptor[] getColorDescriptors() {
    return ColorDescriptor.EMPTY_ARRAY;
  }

  @NotNull
  @Override
  public String getDisplayName() {
    return LanguageAdditionsBundle.message("JSColorPage.java.additions");
  }

  @NotNull
  @Override
  public final DisplayPriority getPriority() {
    return PlatformUtils.isWebStorm() ? DisplayPriority.KEY_LANGUAGE_SETTINGS : DisplayPriority.LANGUAGE_SETTINGS;
  }
}
