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

package com.chrisrm.idea.annotators;

import com.intellij.icons.AllIcons;
import com.intellij.lang.Language;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import com.intellij.psi.codeStyle.DisplayPriority;
import com.intellij.psi.codeStyle.DisplayPrioritySortable;
import com.intellij.util.ObjectUtils;
import com.intellij.util.PlatformUtils;
import gnu.trove.THashMap;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

public class MTJSColorSettings implements ColorSettingsPage, DisplayPrioritySortable {
  private static final AttributesDescriptor[] ATTRS;
  @NonNls
  private static final Map<String, TextAttributesKey> ADDITIONAL_HIGHLIGHT_DESCRIPTORS;

  private static final TextAttributesKey JSKEYWORD = ObjectUtils.notNull(TextAttributesKey.find("JS.KEYWORD"),
                                                                         DefaultLanguageHighlighterColors.KEYWORD);
  private static final TextAttributesKey FUNCTION = ObjectUtils.notNull(TextAttributesKey.find("JS.FUNCTION"),
                                                                        DefaultLanguageHighlighterColors.KEYWORD);
  private static final TextAttributesKey THIS_SUPER = ObjectUtils.notNull(TextAttributesKey.find("JS.THIS_SUPER"),
                                                                          DefaultLanguageHighlighterColors.KEYWORD);
  private static final TextAttributesKey MODULE = ObjectUtils.notNull(TextAttributesKey.find("JS.MODULE_KEYWORD"),
                                                                      DefaultLanguageHighlighterColors.KEYWORD);
  private static final TextAttributesKey DEBUGGER = ObjectUtils.notNull(TextAttributesKey.find("JS.DEBUGGER_STMT"),
                                                                        DefaultLanguageHighlighterColors.KEYWORD);
  private static final TextAttributesKey NULL = ObjectUtils.notNull(TextAttributesKey.find("JS.NULL_UNDEFINED"),
                                                                    DefaultLanguageHighlighterColors.KEYWORD);
  private static final TextAttributesKey VAL = ObjectUtils.notNull(TextAttributesKey.find("JS.VAR_DEF"),
                                                                   DefaultLanguageHighlighterColors.KEYWORD);
  private static final TextAttributesKey FUNCTION_NAME = ObjectUtils.notNull(TextAttributesKey.find("JS.GLOBAL_FUNCTION"),
                                                                             DefaultLanguageHighlighterColors.FUNCTION_DECLARATION);
  private static final TextAttributesKey VARIABLE = ObjectUtils.notNull(TextAttributesKey.find("JS.LOCAL_VARIABLE"),
                                                                        DefaultLanguageHighlighterColors.LOCAL_VARIABLE);

  static {
    ATTRS = new AttributesDescriptor[] {
        new AttributesDescriptor("Keywords: this, super", THIS_SUPER),
        new AttributesDescriptor("Keywords: module, import, export, from", MODULE),
        new AttributesDescriptor("Keywords: debugger", DEBUGGER),
        new AttributesDescriptor("Keywords: null, undefined", NULL),
        new AttributesDescriptor("Keywords: var, let, const", VAL),
        new AttributesDescriptor("Keywords: function", FUNCTION),
    };

    ADDITIONAL_HIGHLIGHT_DESCRIPTORS = new THashMap<>();
    ADDITIONAL_HIGHLIGHT_DESCRIPTORS.put("keyword", JSKEYWORD);
    ADDITIONAL_HIGHLIGHT_DESCRIPTORS.put("function", FUNCTION);
    ADDITIONAL_HIGHLIGHT_DESCRIPTORS.put("function_name", FUNCTION_NAME);
    ADDITIONAL_HIGHLIGHT_DESCRIPTORS.put("val", VAL);
    ADDITIONAL_HIGHLIGHT_DESCRIPTORS.put("local_variable", VARIABLE);
    ADDITIONAL_HIGHLIGHT_DESCRIPTORS.put("this", THIS_SUPER);
    ADDITIONAL_HIGHLIGHT_DESCRIPTORS.put("null", NULL);
    ADDITIONAL_HIGHLIGHT_DESCRIPTORS.put("debugger", DEBUGGER);
    ADDITIONAL_HIGHLIGHT_DESCRIPTORS.put("import", MODULE);
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
    final SyntaxHighlighter syntaxHighlighter = SyntaxHighlighterFactory.getSyntaxHighlighter(lang, null, null);
    if (syntaxHighlighter == null) {
      return SyntaxHighlighterFactory.getSyntaxHighlighter(Language.ANY, null, null);
    }
    return syntaxHighlighter;
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
    return ADDITIONAL_HIGHLIGHT_DESCRIPTORS;
  }

  @NotNull
  @Override
  public AttributesDescriptor[] getAttributeDescriptors() {
    return ATTRS;
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
