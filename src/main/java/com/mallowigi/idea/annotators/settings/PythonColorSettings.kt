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
package com.mallowigi.idea.annotators.settings

import com.intellij.icons.AllIcons
import com.intellij.lang.Language
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.psi.codeStyle.DisplayPriority
import com.intellij.util.PlatformUtils
import com.mallowigi.idea.annotators.PythonAnnotator
import com.mallowigi.idea.messages.LanguageAdditionsBundle.message
import gnu.trove.THashMap
import java.util.Collections
import javax.swing.Icon

/**
 * PHP Additions Color settings
 *
 */
class PythonColorSettings : BaseColorSettings() {
  override fun getIcon(): Icon = AllIcons.FileTypes.JavaScript

  override fun getHighlighter(): SyntaxHighlighter {
    val lang = Language.findLanguageByID("Python") ?: Language.ANY
    return getSyntaxHighlighterWithFallback(lang)
  }

  override fun getDemoText(): String = message("PythonColorPage.demoText")

  override fun getAdditionalHighlightingTagToDescriptorMap(): Map<String, TextAttributesKey> =
    Collections.unmodifiableMap(PYTHON_DESCRIPTORS)

  override fun getAttributeDescriptors(): Array<AttributesDescriptor> = PYTHON_ATTRIBUTES

  override fun getColorDescriptors(): Array<ColorDescriptor> = ColorDescriptor.EMPTY_ARRAY

  override fun getDisplayName(): String = message("PythonColorPage.python.additions")

  @Suppress("UnstableApiUsage")
  override fun getPriority(): DisplayPriority =
    if (PlatformUtils.isPyCharm()) DisplayPriority.KEY_LANGUAGE_SETTINGS else DisplayPriority.LANGUAGE_SETTINGS

  companion object {
    private val PYTHON_ATTRIBUTES: Array<AttributesDescriptor>
    private val PYTHON_DESCRIPTORS: MutableMap<String, TextAttributesKey> = THashMap()

    private val PY_KEYWORD = TextAttributesKey.find("PY.KEYWORD")
    private val PY_SELF = TextAttributesKey.find("PY.SELF_PARAMETER")
    private val PY_SPECIAL = TextAttributesKey.find("PY.PREDEFINED_USAGE")

    private val AS = PythonAnnotator.AS
    private val ASYNC = PythonAnnotator.ASYNC
    private val CLASS = PythonAnnotator.CLASS
    private val DEF = PythonAnnotator.DEF
    private val IF_ELSE = PythonAnnotator.IF_ELSE
    private val IMPORT = PythonAnnotator.IMPORT
    private val NONE = PythonAnnotator.NONE
    private val PRINT = PythonAnnotator.PRINT
    private val RAISE = PythonAnnotator.RAISE
    private val RETURN = PythonAnnotator.RETURN
    private val TRUE_FALSE = PythonAnnotator.TRUE_FALSE
    private val TRY = PythonAnnotator.TRY
    private val WITH = PythonAnnotator.WITH

    init {
      @Suppress("DialogTitleCapitalization")
      PYTHON_ATTRIBUTES = arrayOf(
        AttributesDescriptor(message("python.keywords.None"), NONE),
        AttributesDescriptor(message("python.keywords.True.False"), TRUE_FALSE),
        AttributesDescriptor(message("keywords.as"), AS),
        AttributesDescriptor(message("keywords.async"), ASYNC),
        AttributesDescriptor(message("keywords.class"), CLASS),
        AttributesDescriptor(message("keywords.def"), DEF),
        AttributesDescriptor(message("keywords.if.else"), IF_ELSE),
        AttributesDescriptor(message("keywords.import.from"), IMPORT),
        AttributesDescriptor(message("python.keywords.print"), PRINT),
        AttributesDescriptor(message("keywords.raise"), RAISE),
        AttributesDescriptor(message("python.keywords.try.except"), TRY),
        AttributesDescriptor(message("keywords.with"), WITH),
        AttributesDescriptor(message("keywords.yield"), RETURN),
      )
      PYTHON_DESCRIPTORS.putAll(createAdditionalHlAttrs())
    }

    @Suppress("HardCodedStringLiteral")
    private fun createAdditionalHlAttrs(): Map<String, TextAttributesKey> {
      val descriptors: MutableMap<String, TextAttributesKey> = THashMap()
      descriptors["string"] = DefaultLanguageHighlighterColors.STRING
      descriptors["class_name"] = DefaultLanguageHighlighterColors.CLASS_NAME
      descriptors["method"] = DefaultLanguageHighlighterColors.FUNCTION_CALL
      descriptors["number"] = DefaultLanguageHighlighterColors.NUMBER
      descriptors["oper"] = DefaultLanguageHighlighterColors.OPERATION_SIGN
      descriptors["special"] = PY_SPECIAL
      descriptors["self"] = PY_SELF
      descriptors["keyword"] = PY_KEYWORD
      descriptors["as"] = AS
      descriptors["class"] = CLASS
      descriptors["def"] = DEF
      descriptors["if_else"] = IF_ELSE
      descriptors["import"] = IMPORT
      descriptors["none"] = NONE
      descriptors["print"] = PRINT
      descriptors["raise"] = RAISE
      descriptors["return"] = RETURN
      descriptors["true_false"] = TRUE_FALSE
      descriptors["try"] = TRY
      descriptors["with"] = WITH

      return descriptors
    }
  }
}
