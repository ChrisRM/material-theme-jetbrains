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
import com.mallowigi.idea.annotators.PHPAnnotator;
import com.mallowigi.idea.messages.LanguageAdditionsBundle;
import gnu.trove.THashMap;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.Collections;
import java.util.Map;

@SuppressWarnings({"ClassWithTooManyFields",
  "DialogTitleCapitalization"})
public final class PHPColorSettings extends BaseColorSettings {
  @NonNls
  private static final AttributesDescriptor[] PHP_ATTRIBUTES;
  @NonNls
  private static final Map<String, TextAttributesKey> PHP_DESCRIPTORS = new THashMap<>();

  @SuppressWarnings("DuplicateStringLiteralInspection")
  private static final TextAttributesKey PHPKEYWORD = ObjectUtils.notNull(TextAttributesKey.find("PHP_KEYWORD"),
    DefaultLanguageHighlighterColors.KEYWORD);
  private static final TextAttributesKey VARIABLE = ObjectUtils.notNull(TextAttributesKey.find("PHP_VAR"),
    DefaultLanguageHighlighterColors.LOCAL_VARIABLE);
  private static final TextAttributesKey CLASS = ObjectUtils.notNull(TextAttributesKey.find("PHP_CLASS"),
    DefaultLanguageHighlighterColors.CLASS_NAME);
  private static final TextAttributesKey NUMBER = ObjectUtils.notNull(TextAttributesKey.find("PHP_NUMBER"),
    DefaultLanguageHighlighterColors.NUMBER);
  private static final TextAttributesKey CONSTANT = ObjectUtils.notNull(TextAttributesKey.find("PHP_CONSTANT"),
    DefaultLanguageHighlighterColors.CONSTANT);
  private static final TextAttributesKey FN = ObjectUtils.notNull(TextAttributesKey.find("PHP_FUNCTION_CALL"),
    DefaultLanguageHighlighterColors.FUNCTION_CALL);

  private static final TextAttributesKey FUNCTION = PHPAnnotator.FUNCTION;
  private static final TextAttributesKey THIS_SELF = PHPAnnotator.THIS_SELF;
  private static final TextAttributesKey MODIFIER = PHPAnnotator.MODIFIER;
  private static final TextAttributesKey STATIC_FINAL = PHPAnnotator.STATIC_FINAL;
  private static final TextAttributesKey USE_NAMESPACE = PHPAnnotator.USE_NAMESPACE;
  private static final TextAttributesKey NULL = PHPAnnotator.NULL;
  private static final TextAttributesKey PRIMITIVE = PHPAnnotator.PRIMITIVE;
  private static final TextAttributesKey EXIT = PHPAnnotator.EXIT;
  private static final TextAttributesKey ECHO = PHPAnnotator.ECHO;

  static {
    PHP_ATTRIBUTES = new AttributesDescriptor[]{
      new AttributesDescriptor(LanguageAdditionsBundle.message("php.keywords.function"), FUNCTION),
      new AttributesDescriptor(LanguageAdditionsBundle.message("php.keywords.self"), THIS_SELF),
      new AttributesDescriptor(LanguageAdditionsBundle.message("php.keywords.private.public.protected"), MODIFIER),
      new AttributesDescriptor(LanguageAdditionsBundle.message("php.keywords.static.final"), STATIC_FINAL),
      new AttributesDescriptor(LanguageAdditionsBundle.message("php.keywords.use.namespace"), USE_NAMESPACE),
      new AttributesDescriptor(LanguageAdditionsBundle.message("php.keywords.true.false"), PRIMITIVE),
      new AttributesDescriptor(LanguageAdditionsBundle.message("php.keywords.null"), NULL),
      new AttributesDescriptor(LanguageAdditionsBundle.message("php.keywords.exit.die"), EXIT),
      new AttributesDescriptor(LanguageAdditionsBundle.message("php.keywords.echo"), ECHO),
    };

    PHP_DESCRIPTORS.putAll(createAdditionalHlAttrs());
  }

  @SuppressWarnings({"HardCodedStringLiteral",
    "DuplicateStringLiteralInspection"})
  private static Map<String, TextAttributesKey> createAdditionalHlAttrs() {
    final Map<String, TextAttributesKey> descriptors = new THashMap<>();
    descriptors.put("string", DefaultLanguageHighlighterColors.STRING);
    descriptors.put("keyword", PHPKEYWORD);
    descriptors.put("function", FUNCTION);
    descriptors.put("class", CLASS);
    descriptors.put("const", CONSTANT);
    descriptors.put("num", NUMBER);
    descriptors.put("var", VARIABLE);
    descriptors.put("fn", FN);

    descriptors.put("use", USE_NAMESPACE);
    descriptors.put("static", STATIC_FINAL);
    descriptors.put("modifier", MODIFIER);
    descriptors.put("self", THIS_SELF);
    descriptors.put("primitive", PRIMITIVE);
    descriptors.put("null", NULL);
    descriptors.put("echo", ECHO);
    descriptors.put("exit", EXIT);

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
    @NonNls final Language lang = ObjectUtils.notNull(Language.findLanguageByID("PHP"), Language.ANY);
    return getSyntaxHighlighterWithFallback(lang);
  }

  @NonNls
  @NotNull
  @Override
  public String getDemoText() {
    return LanguageAdditionsBundle.message("PHPColorPage.demoText");
  }

  @NotNull
  @Override
  public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
    return Collections.unmodifiableMap(PHP_DESCRIPTORS);
  }

  @NotNull
  @Override
  public AttributesDescriptor[] getAttributeDescriptors() {
    return PHP_ATTRIBUTES;
  }

  @NotNull
  @Override
  public ColorDescriptor[] getColorDescriptors() {
    return ColorDescriptor.EMPTY_ARRAY;
  }

  @NotNull
  @Override
  public String getDisplayName() {
    return LanguageAdditionsBundle.message("PHPColorPage.php.additions");
  }

  @Override
  public DisplayPriority getPriority() {
    return PlatformUtils.isPhpStorm() ? DisplayPriority.KEY_LANGUAGE_SETTINGS : DisplayPriority.LANGUAGE_SETTINGS;
  }
}
