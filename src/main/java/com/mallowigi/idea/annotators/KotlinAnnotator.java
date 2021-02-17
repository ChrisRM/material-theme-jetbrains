
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
  "HardCodedStringLiteral",
  "SwitchStatementWithTooManyBranches",
  "ClassWithTooManyFields"})
public final class KotlinAnnotator extends BaseAnnotator {

  private static final TextAttributesKey KOTLIN_KEYWORD = ObjectUtils.notNull(TextAttributesKey.find("KOTLIN_KEYWORD"),
    DefaultLanguageHighlighterColors.KEYWORD);
  private static final TextAttributesKey KOTLIN_NUMBER = ObjectUtils.notNull(TextAttributesKey.find("KOTLIN_NUMBER"),
    DefaultLanguageHighlighterColors.KEYWORD);
  public static final TextAttributesKey MODIFIER = TextAttributesKey.createTextAttributesKey("KOTLIN.MODIFIER", JavaAnnotator.MODIFIER);
  public static final TextAttributesKey COMPANION = TextAttributesKey.createTextAttributesKey("KOTLIN.COMPANION",
    JavaAnnotator.STATIC_FINAL);
  public static final TextAttributesKey DATA = TextAttributesKey.createTextAttributesKey("KOTLIN.DATA", JavaAnnotator.STATIC_FINAL);
  public static final TextAttributesKey OP_INFIX = TextAttributesKey.createTextAttributesKey("KOTLIN.OP_INFIX", JavaAnnotator.STATIC_FINAL);
  public static final TextAttributesKey THIS_SUPER = TextAttributesKey.createTextAttributesKey("KOTLIN.THIS_SUPER",
    JavaAnnotator.THIS_SUPER);
  public static final TextAttributesKey NULL_UNIT = TextAttributesKey.createTextAttributesKey("KOTLIN.NULL_UNIT", JavaAnnotator.PRIMITIVE);
  public static final TextAttributesKey IMPORT_PACKAGE = TextAttributesKey.createTextAttributesKey("KOTLIN.IMPORT_PACKAGE",
    JavaAnnotator.IMPORT_PACKAGE);
  public static final TextAttributesKey SEALED_OVERRIDE = TextAttributesKey.createTextAttributesKey("KOTLIN.SEALED_OVERRIDE",
    JavaAnnotator.STATIC_FINAL);
  public static final TextAttributesKey PRIMITIVE = TextAttributesKey.createTextAttributesKey("KOTLIN.PRIMITIVE", JavaAnnotator.PRIMITIVE);

  @SuppressWarnings({"OverlyComplexMethod",
    "OverlyLongMethod"})
  @Override
  protected TextAttributesKey getKeywordKind(@NotNull final PsiElement element) {
    TextAttributesKey kind = null;
    switch (element.getText()) {
      case "private":
      case "public":
      case "protected":
      case "internal":
        kind = MODIFIER;
        break;
      case "sealed":
      case "open":
      case "override":
        kind = SEALED_OVERRIDE;
        break;
      case "object":
      case "companion":
        kind = COMPANION;
        break;
      case "data":
        kind = DATA;
        break;
      case "operator":
      case "infix":
        kind = OP_INFIX;
        break;
      case "this":
      case "super":
        kind = THIS_SUPER;
        break;
      case "null":
      case "Unit":
        kind = NULL_UNIT;
        break;
      case "import":
      case "package":
        kind = IMPORT_PACKAGE;
        break;
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
