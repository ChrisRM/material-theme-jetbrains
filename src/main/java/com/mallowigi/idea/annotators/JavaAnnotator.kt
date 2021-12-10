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
import org.jetbrains.annotations.Nullable;

@SuppressWarnings({"DuplicateStringLiteralInspection",
  "SwitchStatement"
})
public final class JavaAnnotator extends BaseAnnotator {

  public static final TextAttributesKey JAVA_KEYWORD = ObjectUtils.notNull(TextAttributesKey.find("JAVA_KEYWORD"),
    DefaultLanguageHighlighterColors.KEYWORD);
  private static final TextAttributesKey JAVA_NUMBER = ObjectUtils.notNull(TextAttributesKey.find("JAVA_NUMBER"),
    DefaultLanguageHighlighterColors.NUMBER);
  public static final TextAttributesKey MODIFIER = TextAttributesKey.createTextAttributesKey("JAVA.MODIFIER", JAVA_KEYWORD);
  public static final TextAttributesKey STATIC_FINAL = TextAttributesKey.createTextAttributesKey("JAVA.STATIC_FINAL", JAVA_KEYWORD);
  public static final TextAttributesKey THIS_SUPER = TextAttributesKey.createTextAttributesKey("JAVA.THIS_SUPER", JAVA_KEYWORD);
  public static final TextAttributesKey IMPORT_PACKAGE = TextAttributesKey.createTextAttributesKey("JAVA.IMPORT_PACKAGE", JAVA_KEYWORD);
  public static final TextAttributesKey PRIMITIVE = TextAttributesKey.createTextAttributesKey("JAVA.PRIMITIVE", JAVA_NUMBER);

  @SuppressWarnings("SwitchStatementWithTooManyBranches")
  @Nullable
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
      case "this":
      case "super":
        kind = THIS_SUPER;
        break;
      case "import":
      case "package":
        kind = IMPORT_PACKAGE;
        break;
      case "null":
      case "true":
      case "false":
        kind = PRIMITIVE;
        break;
      default:
        break;
    }
    return kind;
  }
}
