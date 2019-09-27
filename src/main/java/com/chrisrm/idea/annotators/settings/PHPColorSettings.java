/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 Chris Magnussen and Elior Boukhobza
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

import com.chrisrm.idea.annotators.PHPAnnotator;
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
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.coverage.gnu.trove.THashMap;

import javax.swing.*;
import java.util.Map;

public final class PHPColorSettings extends BaseColorSettings {
  @NonNls
  static final AttributesDescriptor[] PHP_ATTRIBUTES;
  @NonNls
  static final Map<String, TextAttributesKey> PHP_DESCRIPTORS = new THashMap<>();

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

  static {
    PHP_ATTRIBUTES = new AttributesDescriptor[]{
        new AttributesDescriptor("Keywords: function", PHPColorSettings.FUNCTION),
        new AttributesDescriptor("Keywords: self", PHPColorSettings.THIS_SELF),
        new AttributesDescriptor("Keywords: private, public, protected", PHPColorSettings.MODIFIER),
        new AttributesDescriptor("Keywords: static, final", PHPColorSettings.STATIC_FINAL),
        new AttributesDescriptor("Keywords: use, namespace", PHPColorSettings.USE_NAMESPACE),
    };

    PHPColorSettings.PHP_DESCRIPTORS.putAll(PHPColorSettings.createAdditionalHlAttrs());
  }

  private static Map<String, TextAttributesKey> createAdditionalHlAttrs() {
    final Map<String, TextAttributesKey> descriptors = new THashMap<>();
    descriptors.put("keyword", PHPColorSettings.PHPKEYWORD);
    descriptors.put("function", PHPColorSettings.FUNCTION);
    descriptors.put("class", PHPColorSettings.CLASS);
    descriptors.put("const", PHPColorSettings.CONSTANT);
    descriptors.put("num", PHPColorSettings.NUMBER);
    descriptors.put("var", PHPColorSettings.VARIABLE);
    descriptors.put("fn", PHPColorSettings.FN);

    descriptors.put("use", PHPColorSettings.USE_NAMESPACE);
    descriptors.put("static", PHPColorSettings.STATIC_FINAL);
    descriptors.put("modifier", PHPColorSettings.MODIFIER);
    descriptors.put("self", PHPColorSettings.THIS_SELF);

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
    final Language lang = ObjectUtils.notNull(Language.findLanguageByID("PHP"), Language.ANY);
    return getSyntaxHighlighterWithFallback(lang);
  }

  @NotNull
  @Override
  public String getDemoText() {
    return
        "<use>namespace</use> Foo\\Bar\\Baz;\n" +
            "\n" +
            "<use>use</use> <class>SomeClass</class>" +
            "\n" +
            "<static>final</static> <keyword>class</keyword> <class>MyClass</class> <keyword>extends</keyword> " +
            "<class>MyOtherClass</class> {\n" +
            "    <modifier>public</modifier> <keyword>const</keyword> <var>SINGLE</var> = <num>1</num>;\n" +
            "    <modifier>private</modifier> <var>$variable</var>;\n" +
            "    <modifier>protected</modifier> <var>$arguments</var>;\n" +
            "}\n" +
            "\n" +
            "<modifier>public</modifier> <function>function</function> <fn>getVar</fn>() {\n" +
            "    <keyword>return</keyword> <self>self</self>::<var>variable</var>;\n" +
            "}";
  }

  @Nullable
  @Override
  public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
    return PHP_DESCRIPTORS;
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
    return "PHP Additions";
  }

  @Override
  public DisplayPriority getPriority() {
    return PlatformUtils.isPhpStorm() ? DisplayPriority.KEY_LANGUAGE_SETTINGS : DisplayPriority.LANGUAGE_SETTINGS;
  }
}
