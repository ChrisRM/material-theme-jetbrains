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

import com.intellij.lang.annotation.HighlightSeverity;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import com.intellij.util.ObjectUtils;
import com.mallowigi.idea.MTConfig;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings({"DuplicateStringLiteralInspection",
  "SwitchStatement",
  "HardCodedStringLiteral",
  "SwitchStatementWithTooManyBranches",
  "OverlyLongMethod",
  "ClassWithTooManyFields",
  "OverlyComplexMethod",
  "DesignForExtension"})
public class JSAnnotator extends BaseAnnotator {

  public static final TextAttributesKey JS_KEYWORD = ObjectUtils.notNull(TextAttributesKey.find("JS.KEYWORD"),
    DefaultLanguageHighlighterColors.KEYWORD);
  public static final TextAttributesKey JS_NUMBER = ObjectUtils.notNull(TextAttributesKey.find("JS.NUMBER"),
    DefaultLanguageHighlighterColors.KEYWORD);
  public static final TextAttributesKey THIS_SUPER = TextAttributesKey.createTextAttributesKey("JS.THIS_SUPER", JS_KEYWORD);
  public static final TextAttributesKey MODULE_KEYWORD = TextAttributesKey.createTextAttributesKey("JS.MODULE_KEYWORD", JS_KEYWORD);
  public static final TextAttributesKey DEBUGGER_STMT = TextAttributesKey.createTextAttributesKey("JS.DEBUGGER_STMT", JS_KEYWORD);
  public static final TextAttributesKey CONSOLE = TextAttributesKey.createTextAttributesKey("JS.CONSOLE", JS_KEYWORD);
  public static final TextAttributesKey NULL = TextAttributesKey.createTextAttributesKey("JS.NULL_UNDEFINED", JS_NUMBER);
  public static final TextAttributesKey VAL = TextAttributesKey.createTextAttributesKey("JS.VAR_DEF", JS_KEYWORD);
  public static final TextAttributesKey FUNCTION = TextAttributesKey.createTextAttributesKey("JS.FUNCTION", JS_KEYWORD);
  public static final TextAttributesKey PRIMITIVE = TextAttributesKey.createTextAttributesKey("JS.PRIMITIVE", JS_NUMBER);
  public static final TextAttributesKey CLASS_EXTENDS = TextAttributesKey.createTextAttributesKey("JS.CLASS_EXTENDS", JS_KEYWORD);
  public static final TextAttributesKey YIELD = TextAttributesKey.createTextAttributesKey("JS.YIELD", JS_KEYWORD);
  public static final TextAttributesKey ASYNC_AWAIT = TextAttributesKey.createTextAttributesKey("JS.ASYNC_AWAIT", JS_KEYWORD);
  public static final TextAttributesKey TRY_CATCH = TextAttributesKey.createTextAttributesKey("JS.TRY_CATCH", JS_KEYWORD);
  public static final TextAttributesKey INLINE = TextAttributesKey.createTextAttributesKey("JS.INLINE", JS_KEYWORD);
  public static final TextAttributesKey NEW = TextAttributesKey.createTextAttributesKey("JS.NEW", JS_KEYWORD);
  public static final TextAttributesKey PROTOTYPE = TextAttributesKey.createTextAttributesKey("JS.PROTOTYPE", JS_KEYWORD);
  public static final TextAttributesKey CONSTRUCTOR = TextAttributesKey.createTextAttributesKey("JS.CONSTRUCTOR", JS_KEYWORD);
  public static final TextAttributesKey IF_ELSE = TextAttributesKey.createTextAttributesKey("JS.IF_ELSE", JS_KEYWORD);
  public static final TextAttributesKey GET_SET = TextAttributesKey.createTextAttributesKey("JS.GET_SET", JS_KEYWORD);

  @Override
  public final void visitElement(@NotNull final PsiElement element) {
    assert myHolder != null;
    final TextAttributesKey kind = getKeywordKind(element);
    if (kind == null) {
      return;
    }
    if (!(element instanceof LeafPsiElement)) {
      return;
    }

    //    final IElementType elementType = element.getNode().getElementType();
    //    if (!elementType.toString().contains("KEYWORD") && !elementType.toString().contains("LITERAL")) {
    //      return;
    //    }

    final TextRange textRange = element.getTextRange();
    final TextRange range = new TextRange(textRange.getStartOffset(), textRange.getEndOffset());

    final boolean enforcedLanguageAdditions = MTConfig.getInstance().isEnforcedLanguageAdditions();
    final HighlightSeverity highlightSeverity = enforcedLanguageAdditions ? HighlightSeverity.WEAK_WARNING : HighlightSeverity.INFORMATION;

    myHolder.newSilentAnnotation(highlightSeverity)
            .needsUpdateOnTyping(false)
            .range(range)
            .textAttributes(kind)
            .create();
  }

  @Nullable
  @Override
  protected TextAttributesKey getKeywordKind(@NotNull final PsiElement element) {
    TextAttributesKey kind = null;
    switch (element.getText()) {
      case "this":
      case "super":
        kind = THIS_SUPER;
        break;
      case "if":
      case "else":
      case "for":
      case "while":
      case "do":
        kind = IF_ELSE;
        break;
      case "constructor":
        kind = CONSTRUCTOR;
        break;
      case "return":
      case "yield":
        kind = YIELD;
        break;
      case "new":
      case "throw":
        kind = NEW;
        break;
      case "async":
      case "await":
        kind = ASYNC_AWAIT;
        break;
      case "try":
      case "catch":
      case "finally":
        kind = TRY_CATCH;
        break;
      case "export":
      case "import":
      case "require":
      case "from":
      case "default":
      case "module":
        kind = MODULE_KEYWORD;
        break;
      case "debugger":
        kind = DEBUGGER_STMT;
        break;
      case "prototype":
        kind = PROTOTYPE;
        break;
      case "null":
      case "undefined":
      case "NaN":
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
        kind = FUNCTION;
        break;
      case "get":
      case "set":
        kind = GET_SET;
        break;
      case "abstract":
      case "class":
      case "extends":
      case "implements":
        kind = CLASS_EXTENDS;
        break;
      case "console":
      case "window":
      case "document":
      case "global":
        kind = CONSOLE;
        break;
      case "in":
      case "of":
      case "as":
      case "instanceof":
      case "typeof":
        kind = INLINE;
        break;
      default:
        break;
    }
    return kind;
  }
}
