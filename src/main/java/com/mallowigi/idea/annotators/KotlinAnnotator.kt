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

internal class KotlinAnnotator : BaseAnnotator() {
  override fun getKeywordKind(element: PsiElement): TextAttributesKey? {
    var kind: TextAttributesKey? = null
    when (element.text) {
      "private", "public", "protected", "internal" -> kind = MODIFIER
      "sealed", "open", "override"                 -> kind = SEALED_OVERRIDE
      "object", "companion"                        -> kind = COMPANION
      "data"                                       -> kind = DATA
      "operator", "infix"                          -> kind = OP_INFIX
      "this", "super"                              -> kind = THIS_SUPER
      "null", "Unit"                               -> kind = NULL_UNIT
      "import", "package"                          -> kind = IMPORT_PACKAGE
      "true", "false"                              -> kind = PRIMITIVE
      else                                         -> {}
    }
    return kind
  }

  companion object {
    private val KOTLIN_KEYWORD = ObjectUtils.notNull(
      TextAttributesKey.find("KOTLIN_KEYWORD"),
      DefaultLanguageHighlighterColors.KEYWORD
    )
    private val KOTLIN_NUMBER = ObjectUtils.notNull(
      TextAttributesKey.find("KOTLIN_NUMBER"),
      DefaultLanguageHighlighterColors.KEYWORD
    )

    @JvmField
    val MODIFIER = TextAttributesKey.createTextAttributesKey("KOTLIN.MODIFIER", JavaAnnotator.MODIFIER)

    @JvmField
    val COMPANION = TextAttributesKey.createTextAttributesKey(
      "KOTLIN.COMPANION",
      JavaAnnotator.STATIC_FINAL
    )

    @JvmField
    val DATA = TextAttributesKey.createTextAttributesKey("KOTLIN.DATA", JavaAnnotator.STATIC_FINAL)

    @JvmField
    val OP_INFIX = TextAttributesKey.createTextAttributesKey("KOTLIN.OP_INFIX", JavaAnnotator.STATIC_FINAL)

    @JvmField
    val THIS_SUPER = TextAttributesKey.createTextAttributesKey(
      "KOTLIN.THIS_SUPER",
      JavaAnnotator.THIS_SUPER
    )

    @JvmField
    val NULL_UNIT = TextAttributesKey.createTextAttributesKey("KOTLIN.NULL_UNIT", JavaAnnotator.PRIMITIVE)

    @JvmField
    val IMPORT_PACKAGE = TextAttributesKey.createTextAttributesKey(
      "KOTLIN.IMPORT_PACKAGE",
      JavaAnnotator.IMPORT_PACKAGE
    )

    @JvmField
    val SEALED_OVERRIDE = TextAttributesKey.createTextAttributesKey(
      "KOTLIN.SEALED_OVERRIDE",
      JavaAnnotator.STATIC_FINAL
    )

    @JvmField
    val PRIMITIVE = TextAttributesKey.createTextAttributesKey("KOTLIN.PRIMITIVE", JavaAnnotator.PRIMITIVE)
  }
}
