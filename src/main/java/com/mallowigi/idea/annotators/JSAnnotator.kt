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
package com.mallowigi.idea.annotators

import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiComment
import com.intellij.psi.PsiElement
import com.intellij.psi.impl.source.tree.LeafPsiElement
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.util.ObjectUtils
import com.mallowigi.idea.config.application.MTConfig

@Suppress("KDocMissingDocumentation")
internal open class JSAnnotator : BaseAnnotator() {
  override fun visitElement(element: PsiElement) {
    assert(myHolder != null)
    val kind = getKeywordKind(element) ?: return

    if (PsiTreeUtil.getParentOfType(element, PsiComment::class.java) != null) return

    if (element !is LeafPsiElement) return

    val textRange = element.getTextRange()
    val range = TextRange(textRange.startOffset, textRange.endOffset)
    val enforcedLanguageAdditions = MTConfig.getInstance().isEnforcedLanguageAdditions
    val highlightSeverity =
      if (enforcedLanguageAdditions) HighlightSeverity.WEAK_WARNING else HighlightSeverity.INFORMATION

    (myHolder ?: return).newSilentAnnotation(highlightSeverity).needsUpdateOnTyping(false).range(range)
      .textAttributes(kind).create()
  }

  @Suppress("ComplexMethod")
  override fun getKeywordKind(element: PsiElement): TextAttributesKey? {
    var kind: TextAttributesKey? = null
    when (element.text) {
      "this", "super"                                            -> kind = THIS_SUPER
      "if", "else", "for", "while", "do"                         -> kind = IF_ELSE
      "constructor"                                              -> kind = CONSTRUCTOR
      "return", "yield"                                          -> kind = YIELD
      "new", "throw"                                             -> kind = NEW
      "async", "await"                                           -> kind = ASYNC_AWAIT
      "try", "catch", "finally"                                  -> kind = TRY_CATCH
      "export", "import", "require", "from", "default", "module" -> kind = MODULE_KEYWORD
      "debugger"                                                 -> kind = DEBUGGER_STMT
      "prototype"                                                -> kind = PROTOTYPE
      "null", "undefined", "NaN"                                 -> kind = NULL
      "true", "false"                                            -> kind = PRIMITIVE
      "var", "let", "const"                                      -> kind = VAL
      "function", "static"                                       -> kind = FUNCTION
      "get", "set"                                               -> kind = GET_SET
      "abstract", "class", "extends", "implements"               -> kind = CLASS_EXTENDS
      "console", "window", "document", "global"                  -> kind = CONSOLE
      "in", "of", "as", "instanceof", "typeof"                   -> kind = INLINE
    }
    return kind
  }

  companion object {
    @JvmField
    val JS_KEYWORD: TextAttributesKey =
      ObjectUtils.notNull(TextAttributesKey.find("JS.KEYWORD"), DefaultLanguageHighlighterColors.KEYWORD)

    @JvmField
    val JS_NUMBER: TextAttributesKey =
      ObjectUtils.notNull(TextAttributesKey.find("JS.NUMBER"), DefaultLanguageHighlighterColors.KEYWORD)

    @JvmField
    val THIS_SUPER: TextAttributesKey = TextAttributesKey.createTextAttributesKey("JS.THIS_SUPER", JS_KEYWORD)

    @JvmField
    val MODULE_KEYWORD: TextAttributesKey = TextAttributesKey.createTextAttributesKey("JS.MODULE_KEYWORD", JS_KEYWORD)

    @JvmField
    val DEBUGGER_STMT: TextAttributesKey = TextAttributesKey.createTextAttributesKey("JS.DEBUGGER_STMT", JS_KEYWORD)

    @JvmField
    val CONSOLE: TextAttributesKey = TextAttributesKey.createTextAttributesKey("JS.CONSOLE", JS_KEYWORD)

    @JvmField
    val NULL: TextAttributesKey = TextAttributesKey.createTextAttributesKey("JS.NULL_UNDEFINED", JS_NUMBER)

    @JvmField
    val VAL: TextAttributesKey = TextAttributesKey.createTextAttributesKey("JS.VAR_DEF", JS_KEYWORD)

    @JvmField
    val FUNCTION: TextAttributesKey = TextAttributesKey.createTextAttributesKey("JS.FUNCTION", JS_KEYWORD)

    @JvmField
    val PRIMITIVE: TextAttributesKey = TextAttributesKey.createTextAttributesKey("JS.PRIMITIVE", JS_NUMBER)

    @JvmField
    val CLASS_EXTENDS: TextAttributesKey = TextAttributesKey.createTextAttributesKey("JS.CLASS_EXTENDS", JS_KEYWORD)

    @JvmField
    val YIELD: TextAttributesKey = TextAttributesKey.createTextAttributesKey("JS.YIELD", JS_KEYWORD)

    @JvmField
    val ASYNC_AWAIT: TextAttributesKey = TextAttributesKey.createTextAttributesKey("JS.ASYNC_AWAIT", JS_KEYWORD)

    @JvmField
    val TRY_CATCH: TextAttributesKey = TextAttributesKey.createTextAttributesKey("JS.TRY_CATCH", JS_KEYWORD)

    @JvmField
    val INLINE: TextAttributesKey = TextAttributesKey.createTextAttributesKey("JS.INLINE", JS_KEYWORD)

    @JvmField
    val NEW: TextAttributesKey = TextAttributesKey.createTextAttributesKey("JS.NEW", JS_KEYWORD)

    @JvmField
    val PROTOTYPE: TextAttributesKey = TextAttributesKey.createTextAttributesKey("JS.PROTOTYPE", JS_KEYWORD)

    @JvmField
    val CONSTRUCTOR: TextAttributesKey = TextAttributesKey.createTextAttributesKey("JS.CONSTRUCTOR", JS_KEYWORD)

    @JvmField
    val IF_ELSE: TextAttributesKey = TextAttributesKey.createTextAttributesKey("JS.IF_ELSE", JS_KEYWORD)

    @JvmField
    val GET_SET: TextAttributesKey = TextAttributesKey.createTextAttributesKey("JS.GET_SET", JS_KEYWORD)
  }
}
