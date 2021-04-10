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

@SuppressWarnings({"SwitchStatement",
  "HardCodedStringLiteral",
  "DuplicateStringLiteralInspection",
  "ClassWithTooManyFields",
  "SwitchStatementWithTooManyBranches"})
public final class TSAnnotator extends BaseAnnotator {
  public static final TextAttributesKey KEYWORD = ObjectUtils.notNull(TextAttributesKey.find("TS.KEYWORD"),
    DefaultLanguageHighlighterColors.KEYWORD);
  public static final TextAttributesKey NUMBER = ObjectUtils.notNull(TextAttributesKey.find("TS.NUMBER"),
    DefaultLanguageHighlighterColors.KEYWORD);
  public static final TextAttributesKey THIS_SUPER = TextAttributesKey.createTextAttributesKey("TS.THIS_SUPER", KEYWORD);
  public static final TextAttributesKey MODULE = TextAttributesKey.createTextAttributesKey("TS.MODULE_KEYWORD", KEYWORD);
  public static final TextAttributesKey DEBUGGER = TextAttributesKey.createTextAttributesKey("TS.DEBUGGER_STMT", KEYWORD);
  public static final TextAttributesKey CONSOLE = TextAttributesKey.createTextAttributesKey("TS.CONSOLE", KEYWORD);
  public static final TextAttributesKey NULL = TextAttributesKey.createTextAttributesKey("TS.NULL_UNDEFINED", NUMBER);
  public static final TextAttributesKey VAL = TextAttributesKey.createTextAttributesKey("TS.VAR_DEF", KEYWORD);
  public static final TextAttributesKey FUNCTION = TextAttributesKey.createTextAttributesKey("TS.FUNCTION", KEYWORD);
  public static final TextAttributesKey PRIVATE = TextAttributesKey.createTextAttributesKey("TS.PRIVATE_PUBLIC", KEYWORD);
  public static final TextAttributesKey DECLARE = TextAttributesKey.createTextAttributesKey("TS.DECLARE", KEYWORD);
  public static final TextAttributesKey TYPE_ALIAS = TextAttributesKey.createTextAttributesKey("TS.TYPE_ALIAS", KEYWORD);
  public static final TextAttributesKey PRIMITIVE = TextAttributesKey.createTextAttributesKey("TS.PRIMITIVE", NUMBER);
  public static final TextAttributesKey CLASS = TextAttributesKey.createTextAttributesKey("TS.CLASS_EXTENDS", KEYWORD);

  @Nullable
  @Override
  public TextAttributesKey getKeywordKind(@NotNull final PsiElement element) {
    TextAttributesKey kind = null;
    switch (element.getText()) {
      case "this":
      case "super":
        kind = THIS_SUPER;
        break;
      case "export":
      case "import":
      case "require":
      case "from":
      case "default":
      case "module":
        kind = MODULE;
        break;
      case "debugger":
        kind = DEBUGGER;
        break;
      case "null":
      case "undefined":
        kind = NULL;
        break;
      case "true":
      case "false":
        kind = PRIMITIVE;
        break;
      case "var":
      case "let":
      case "const":
        kind = VAL;
        break;
      case "function":
      case "static":
      case "get":
      case "set":
        kind = FUNCTION;
        break;
      case "class":
      case "extends":
      case "implements":
        kind = CLASS;
        break;
      case "console":
        kind = CONSOLE;
        break;
      case "public":
      case "protected":
      case "private":
        kind = PRIVATE;
        break;
      case "declare":
        kind = DECLARE;
        break;
      case "type":
      case "alias":
        kind = TYPE_ALIAS;
        break;
      default:
        break;
    }
    return kind;
  }
}
