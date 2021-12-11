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
import com.mallowigi.idea.annotators.PHPAnnotator
import com.mallowigi.idea.messages.LanguageAdditionsBundle.message
import gnu.trove.THashMap
import java.util.Collections
import javax.swing.Icon

/**
 * PHP Additions Color settings
 *
 */
class PHPColorSettings : BaseColorSettings() {
  override fun getIcon(): Icon = AllIcons.FileTypes.JavaScript

  override fun getHighlighter(): SyntaxHighlighter {
    val lang = Language.findLanguageByID("PHP") ?: Language.ANY
    return getSyntaxHighlighterWithFallback(lang)
  }

  override fun getDemoText(): String = message("PHPColorPage.demoText")

  override fun getAdditionalHighlightingTagToDescriptorMap(): Map<String, TextAttributesKey> =
    Collections.unmodifiableMap(PHP_DESCRIPTORS)

  override fun getAttributeDescriptors(): Array<AttributesDescriptor> = PHP_ATTRIBUTES

  override fun getColorDescriptors(): Array<ColorDescriptor> = ColorDescriptor.EMPTY_ARRAY

  override fun getDisplayName(): String = message("PHPColorPage.php.additions")

  @Suppress("UnstableApiUsage")
  override fun getPriority(): DisplayPriority =
    if (PlatformUtils.isPhpStorm()) DisplayPriority.KEY_LANGUAGE_SETTINGS else DisplayPriority.LANGUAGE_SETTINGS

  companion object {
    private val PHP_ATTRIBUTES: Array<AttributesDescriptor>
    private val PHP_DESCRIPTORS: MutableMap<String, TextAttributesKey> = THashMap()
    private val PHP_KEYWORD = TextAttributesKey.find("PHP_KEYWORD")
    private val VARIABLE = TextAttributesKey.find("PHP_VAR")
    private val CLASS = TextAttributesKey.find("PHP_CLASS")
    private val NUMBER = TextAttributesKey.find("PHP_NUMBER")
    private val CONSTANT = TextAttributesKey.find("PHP_CONSTANT")
    private val FN = TextAttributesKey.find("PHP_FUNCTION_CALL")
    private val FUNCTION = PHPAnnotator.FUNCTION
    private val THIS_SELF = PHPAnnotator.THIS_SELF
    private val MODIFIER = PHPAnnotator.MODIFIER
    private val STATIC_FINAL = PHPAnnotator.STATIC_FINAL
    private val USE_NAMESPACE = PHPAnnotator.USE_NAMESPACE
    private val NULL = PHPAnnotator.NULL
    private val PRIMITIVE = PHPAnnotator.PRIMITIVE
    private val EXIT = PHPAnnotator.EXIT
    private val ECHO = PHPAnnotator.ECHO

    init {
      PHP_ATTRIBUTES = arrayOf(
        AttributesDescriptor(message("php.keywords.function"), FUNCTION),
        AttributesDescriptor(message("php.keywords.self"), THIS_SELF),
        AttributesDescriptor(message("php.keywords.private.public.protected"), MODIFIER),
        AttributesDescriptor(message("php.keywords.static.final"), STATIC_FINAL),
        AttributesDescriptor(message("php.keywords.use.namespace"), USE_NAMESPACE),
        AttributesDescriptor(message("php.keywords.true.false"), PRIMITIVE),
        AttributesDescriptor(message("php.keywords.null"), NULL),
        AttributesDescriptor(message("php.keywords.exit.die"), EXIT),
        AttributesDescriptor(message("php.keywords.echo"), ECHO)
      )
      PHP_DESCRIPTORS.putAll(createAdditionalHlAttrs())
    }

    @Suppress("HardCodedStringLiteral")
    private fun createAdditionalHlAttrs(): Map<String, TextAttributesKey> {
      val descriptors: MutableMap<String, TextAttributesKey> = THashMap()
      descriptors["string"] = DefaultLanguageHighlighterColors.STRING
      descriptors["keyword"] = PHP_KEYWORD
      descriptors["function"] = FUNCTION
      descriptors["class"] = CLASS
      descriptors["const"] = CONSTANT
      descriptors["num"] = NUMBER
      descriptors["var"] = VARIABLE
      descriptors["fn"] = FN
      descriptors["use"] = USE_NAMESPACE
      descriptors["static"] = STATIC_FINAL
      descriptors["modifier"] = MODIFIER
      descriptors["self"] = THIS_SELF
      descriptors["primitive"] = PRIMITIVE
      descriptors["null"] = NULL
      descriptors["echo"] = ECHO
      descriptors["exit"] = EXIT
      return descriptors
    }
  }
}
