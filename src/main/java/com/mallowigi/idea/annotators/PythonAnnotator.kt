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

import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.psi.PsiElement

internal class PythonAnnotator : BaseAnnotator() {
  override fun getKeywordKind(element: PsiElement): TextAttributesKey? {
    var kind: TextAttributesKey? = null
    when (element.text) {
      "True", "False"                            -> kind = TRUE_FALSE
      "None"                                     -> kind = NONE
      "as"                                       -> kind = AS
      "async", "await"                           -> kind = ASYNC
      "class"                                    -> kind = CLASS
      "def"                                      -> kind = DEF
      "if", "elif", "else", "for", "while", "do" -> kind = IF_ELSE
      "import", "from"                           -> kind = IMPORT
      "print"                                    -> kind = PRINT
      "raise"                                    -> kind = RAISE
      "return", "yield"                          -> kind = RETURN
      "try", "except", "finally"                 -> kind = TRY
      "with"                                     -> kind = WITH
    }
    return kind
  }

  companion object {
    val PY_KEYWORD = TextAttributesKey.find("PY.KEYWORD")
    val PY_PRIMITIVE = TextAttributesKey.find("PY.NUMBER")
    val PY_FUNCTION = TextAttributesKey.find("PY.PREDEFINED_USAGE")

    @JvmField
    val DEF = TextAttributesKey.createTextAttributesKey("PY.DEF", PY_KEYWORD)

    @JvmField
    val AS = TextAttributesKey.createTextAttributesKey("PY.AS", PY_KEYWORD)

    @JvmField
    val TRY = TextAttributesKey.createTextAttributesKey("PY.TRY", PY_KEYWORD)

    @JvmField
    val CLASS = TextAttributesKey.createTextAttributesKey("PY.CLASS", PY_KEYWORD)

    @JvmField
    val IMPORT = TextAttributesKey.createTextAttributesKey("PY.IMPORT", PY_KEYWORD)

    @JvmField
    val IF_ELSE = TextAttributesKey.createTextAttributesKey("PY.IF_ELSE", PY_KEYWORD)

    @JvmField
    val RETURN = TextAttributesKey.createTextAttributesKey("PY.RETURN", PY_KEYWORD)

    @JvmField
    val RAISE = TextAttributesKey.createTextAttributesKey("PY.RAISE", PY_KEYWORD)

    @JvmField
    val ASYNC = TextAttributesKey.createTextAttributesKey("PY.ASYNC", PY_KEYWORD)

    @JvmField
    val PRINT = TextAttributesKey.createTextAttributesKey("PY.PRINT", PY_FUNCTION)

    @JvmField
    val WITH = TextAttributesKey.createTextAttributesKey("PY.WITH", PY_KEYWORD)

    @JvmField
    val TRUE_FALSE = TextAttributesKey.createTextAttributesKey("PY.TRUE_FALSE", PY_PRIMITIVE)

    @JvmField
    val NONE = TextAttributesKey.createTextAttributesKey("PY.NONE", PY_PRIMITIVE)
  }
}
