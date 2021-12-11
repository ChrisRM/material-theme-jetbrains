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

internal class PHPAnnotator : BaseAnnotator() {
  override fun getKeywordKind(element: PsiElement): TextAttributesKey? {
    var kind: TextAttributesKey? = null
    when (element.text) {
      "private", "public", "protected" -> kind = MODIFIER
      "static", "final"                -> kind = STATIC_FINAL
      "self"                           -> kind = THIS_SELF
      "use", "namespace"               -> kind = USE_NAMESPACE
      "true", "false"                  -> kind = PRIMITIVE
      "null"                           -> kind = NULL
      "exit", "die"                    -> kind = EXIT
      "function"                       -> kind = FUNCTION
      "echo"                           -> kind = ECHO
    }
    return kind
  }

  companion object {
    val PHP_KEYWORD = TextAttributesKey.find("PHP_KEYWORD")
    val PHP_FUNCTION = TextAttributesKey.find("PHP_FUNCTION_CALL")
    val PHP_NUMBER = TextAttributesKey.find("PHP_NUMBER")

    @JvmField
    val MODIFIER = TextAttributesKey.createTextAttributesKey("PHP.MODIFIER", PHP_KEYWORD)

    @JvmField
    val STATIC_FINAL = TextAttributesKey.createTextAttributesKey("PHP.STATIC_FINAL", PHP_KEYWORD)

    @JvmField
    val THIS_SELF = TextAttributesKey.createTextAttributesKey("PHP.THIS_SELF", PHP_KEYWORD)

    @JvmField
    val USE_NAMESPACE = TextAttributesKey.createTextAttributesKey("PHP.USE_NAMESPACE", PHP_KEYWORD)

    @JvmField
    val FUNCTION = TextAttributesKey.createTextAttributesKey("PHP.FUNCTION", PHP_KEYWORD)

    @JvmField
    val PRIMITIVE = TextAttributesKey.createTextAttributesKey("PHP.PRIMITIVE", PHP_NUMBER)

    @JvmField
    val NULL = TextAttributesKey.createTextAttributesKey("PHP.NULL", PHP_NUMBER)

    @JvmField
    val EXIT = TextAttributesKey.createTextAttributesKey("PHP.EXIT", PHP_FUNCTION)

    @JvmField
    val ECHO = TextAttributesKey.createTextAttributesKey("PHP.ECHO", PHP_FUNCTION)
  }
}
