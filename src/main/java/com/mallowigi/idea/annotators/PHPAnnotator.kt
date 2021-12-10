
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

package com.mallowigi.idea.annotators;

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.psi.PsiElement;
import com.intellij.util.ObjectUtils;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings({"DuplicateStringLiteralInspection",
  "SwitchStatement",
  "ClassWithTooManyFields",
  "SwitchStatementWithTooManyBranches"})
public final class PHPAnnotator extends BaseAnnotator {
  public static final TextAttributesKey PHP_KEYWORD = ObjectUtils.notNull(TextAttributesKey.find("PHP_KEYWORD"),
    DefaultLanguageHighlighterColors.KEYWORD);
  public static final TextAttributesKey PHP_FUNCTION = ObjectUtils.notNull(TextAttributesKey.find("PHP_FUNCTION_CALL"),
    DefaultLanguageHighlighterColors.KEYWORD);
  public static final TextAttributesKey PHP_NUMBER = ObjectUtils.notNull(TextAttributesKey.find("PHP_NUMBER"),
    DefaultLanguageHighlighterColors.KEYWORD);
  public static final TextAttributesKey MODIFIER = TextAttributesKey.createTextAttributesKey("PHP.MODIFIER", PHP_KEYWORD);
  public static final TextAttributesKey STATIC_FINAL = TextAttributesKey.createTextAttributesKey("PHP.STATIC_FINAL", PHP_KEYWORD);
  public static final TextAttributesKey THIS_SELF = TextAttributesKey.createTextAttributesKey("PHP.THIS_SELF", PHP_KEYWORD);
  public static final TextAttributesKey USE_NAMESPACE = TextAttributesKey.createTextAttributesKey("PHP.USE_NAMESPACE", PHP_KEYWORD);
  public static final TextAttributesKey FUNCTION = TextAttributesKey.createTextAttributesKey("PHP.FUNCTION", PHP_KEYWORD);
  public static final TextAttributesKey PRIMITIVE = TextAttributesKey.createTextAttributesKey("PHP.PRIMITIVE", PHP_NUMBER);
  public static final TextAttributesKey NULL = TextAttributesKey.createTextAttributesKey("PHP.NULL", PHP_NUMBER);
  public static final TextAttributesKey EXIT = TextAttributesKey.createTextAttributesKey("PHP.EXIT", PHP_FUNCTION);
  public static final TextAttributesKey ECHO = TextAttributesKey.createTextAttributesKey("PHP.ECHO", PHP_FUNCTION);

  @SuppressWarnings("OverlyLongMethod")
  @Override
  protected TextAttributesKey getKeywordKind(@NotNull final PsiElement element) {
    TextAttributesKey kind = null;
    switch (element.getText()) {
      case "private":
      case "public":
      case "protected":
        kind = MODIFIER;
        break;
      case "static":
      case "final":
        kind = STATIC_FINAL;
        break;
      case "self":
        kind = THIS_SELF;
        break;
      case "use":
      case "namespace":
        kind = USE_NAMESPACE;
        break;
      case "true":
      case "false":
        kind = PRIMITIVE;
        break;
      case "null":
        kind = NULL;
        break;
      case "exit":
      case "die":
        kind = EXIT;
        break;
      case "function":
        kind = FUNCTION;
        break;
      case "echo":
        kind = ECHO;
        break;
      default:
        break;
    }
    return kind;
  }
}
