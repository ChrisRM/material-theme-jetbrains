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

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.psi.PsiElement
import com.intellij.util.ObjectUtils

internal class TSAnnotator : JSAnnotator() {
  public override fun getKeywordKind(element: PsiElement): TextAttributesKey? {
    var kind = super.getKeywordKind(element)
    if (kind != null) {
      return kind
    }
    when (element.text) {
      "public", "protected", "private"                                    -> kind = PRIVATE
      "declare", "namespace"                                              -> kind = DECLARE
      "type", "alias", "interface"                                        -> kind = TYPE_ALIAS
      "any", "unknown", "never"                                           -> kind = ANY
      "keyof"                                                             -> kind = INLINE
      "enum"                                                              -> kind = ENUM
      "number", "string", "bigint", "boolean", "void", "object", "symbol" -> kind = PRIM_TYPE
    }
    return kind
  }

  companion object {
    @JvmField
    val KEYWORD = ObjectUtils.notNull(
      TextAttributesKey.find("TS.KEYWORD"),
      DefaultLanguageHighlighterColors.KEYWORD
    )

    @JvmField
    val NUMBER = ObjectUtils.notNull(
      TextAttributesKey.find("TS.NUMBER"),
      DefaultLanguageHighlighterColors.KEYWORD
    )

    @JvmField
    val PRIVATE = TextAttributesKey.createTextAttributesKey("TS.PRIVATE_PUBLIC", KEYWORD)

    @JvmField
    val DECLARE = TextAttributesKey.createTextAttributesKey("TS.DECLARE", KEYWORD)

    @JvmField
    val TYPE_ALIAS = TextAttributesKey.createTextAttributesKey("TS.TYPE_ALIAS", KEYWORD)

    @JvmField
    val ANY = TextAttributesKey.createTextAttributesKey("TS.ANY", KEYWORD)

    @JvmField
    val INLINE = TextAttributesKey.createTextAttributesKey("TS.INLINE", KEYWORD)

    @JvmField
    val ENUM = TextAttributesKey.createTextAttributesKey("TS.ENUM", KEYWORD)

    @JvmField
    val PRIM_TYPE = TextAttributesKey.createTextAttributesKey("TS.PRIM_TYPE", KEYWORD)
  }
}