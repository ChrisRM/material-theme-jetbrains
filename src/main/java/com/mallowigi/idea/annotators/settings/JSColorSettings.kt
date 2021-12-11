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
import com.mallowigi.idea.annotators.JSAnnotator
import com.mallowigi.idea.messages.LanguageAdditionsBundle.message
import gnu.trove.THashMap
import java.util.Collections
import javax.swing.Icon

/**
 * JavaScript Additions color settings
 *
 */
open class JSColorSettings : BaseColorSettings() {
  override fun getIcon(): Icon = AllIcons.FileTypes.JavaScript

  override fun getHighlighter(): SyntaxHighlighter {
    val lang = Language.findLanguageByID("JavaScript") ?: Language.ANY
    return getSyntaxHighlighterWithFallback(lang)
  }

  override fun getDemoText(): String = message("JSColorPage.demoText")

  override fun getAdditionalHighlightingTagToDescriptorMap(): Map<String, TextAttributesKey> =
    Collections.unmodifiableMap(JS_DESCRIPTORS)

  override fun getAttributeDescriptors(): Array<AttributesDescriptor> = JS_ATTRIBUTES

  override fun getColorDescriptors(): Array<ColorDescriptor> = ColorDescriptor.EMPTY_ARRAY

  override fun getDisplayName(): String = message("JSColorPage.java.additions")

  @Suppress("UnstableApiUsage")
  override fun getPriority(): DisplayPriority =
    if (PlatformUtils.isWebStorm()) DisplayPriority.KEY_LANGUAGE_SETTINGS else DisplayPriority.LANGUAGE_SETTINGS

  companion object {
    private val JS_ATTRIBUTES: Array<AttributesDescriptor>

    @JvmField
    val JS_DESCRIPTORS: MutableMap<String, TextAttributesKey> = THashMap()

    private val JS_KEYWORD = JSAnnotator.JS_KEYWORD
    private val JS_NUMBER = JSAnnotator.JS_NUMBER
    private val VARIABLE = TextAttributesKey.find("JS.LOCAL_VARIABLE")

    private val FUNCTION = JSAnnotator.FUNCTION
    private val THIS_SUPER = JSAnnotator.THIS_SUPER
    private val MODULE = JSAnnotator.MODULE_KEYWORD
    private val CONSOLE = JSAnnotator.CONSOLE
    private val DEBUGGER = JSAnnotator.DEBUGGER_STMT
    private val NULL = JSAnnotator.NULL
    private val PRIMITIVE = JSAnnotator.PRIMITIVE
    private val VAL = JSAnnotator.VAL
    private val CLASS = JSAnnotator.CLASS_EXTENDS
    private val FUNCTION_NAME = DefaultLanguageHighlighterColors.FUNCTION_CALL
    private val YIELD = JSAnnotator.YIELD
    private val ASYNC = JSAnnotator.ASYNC_AWAIT
    private val TRY_CATCH = JSAnnotator.TRY_CATCH
    private val INLINE = JSAnnotator.INLINE
    private val NEW = JSAnnotator.NEW
    private val PROTOTYPE = JSAnnotator.PROTOTYPE
    private val CONSTRUCTOR = JSAnnotator.CONSTRUCTOR
    private val IF_ELSE = JSAnnotator.IF_ELSE
    private val GET_SET = JSAnnotator.GET_SET

    init {
      JS_ATTRIBUTES = arrayOf(
        AttributesDescriptor(message("keywords.this.super"), THIS_SUPER),
        AttributesDescriptor(message("keywords.module.import.export.from"), MODULE),
        AttributesDescriptor(message("keywords.debugger"), DEBUGGER),
        AttributesDescriptor(message("keywords.null.undefined"), NULL),
        AttributesDescriptor(message("keywords.true.false"), PRIMITIVE),
        AttributesDescriptor(message("keywords.var.let.const"), VAL),
        AttributesDescriptor(message("keywords.if.else"), IF_ELSE),
        AttributesDescriptor(message("keywords.class.extends"), CLASS),
        AttributesDescriptor(message("keywords.function"), FUNCTION),
        AttributesDescriptor(message("keywords.get.set"), GET_SET),
        AttributesDescriptor(message("keywords.inline"), INLINE),
        AttributesDescriptor(message("keywords.yield"), YIELD),
        AttributesDescriptor(message("keywords.new"), NEW),
        AttributesDescriptor(message("keywords.async"), ASYNC),
        AttributesDescriptor(message("keywords.try.catch"), TRY_CATCH),
        AttributesDescriptor(message("keywords.constructor"), CONSTRUCTOR),
        AttributesDescriptor(message("globals.var.console"), CONSOLE),
        AttributesDescriptor(message("globals.prototype"), PROTOTYPE)
      )

      JS_DESCRIPTORS.putAll(createAdditionalHlAttrs())
    }

    /**
     * Create additional hl attrs to be highlighted in the example demo text
     *
     */
    @Suppress("HardCodedStringLiteral")
    private fun createAdditionalHlAttrs(): Map<String, TextAttributesKey> {
      val descriptors: MutableMap<String, TextAttributesKey> = THashMap()
      descriptors["string"] = DefaultLanguageHighlighterColors.STRING
      descriptors["keyword"] = JS_KEYWORD
      descriptors["function"] = FUNCTION
      descriptors["function_name"] = FUNCTION_NAME
      descriptors["val"] = VAL
      descriptors["local_variable"] = VARIABLE
      descriptors["class"] = CLASS
      descriptors["class_name"] = DefaultLanguageHighlighterColors.CLASS_NAME
      descriptors["interface_name"] = DefaultLanguageHighlighterColors.INTERFACE_NAME
      descriptors["this"] = THIS_SUPER
      descriptors["null"] = NULL
      descriptors["primitive"] = PRIMITIVE
      descriptors["debugger"] = DEBUGGER
      descriptors["import"] = MODULE
      descriptors["console"] = CONSOLE
      descriptors["number"] = JS_NUMBER
      descriptors["inst_field"] = DefaultLanguageHighlighterColors.INSTANCE_FIELD
      descriptors["yield"] = YIELD
      descriptors["new"] = NEW
      descriptors["throw"] = NEW
      descriptors["async"] = ASYNC
      descriptors["try"] = TRY_CATCH
      descriptors["inline"] = INLINE
      descriptors["prototype"] = PROTOTYPE
      descriptors["constructor"] = CONSTRUCTOR
      descriptors["if_else"] = IF_ELSE
      descriptors["getter"] = GET_SET
      return descriptors
    }
  }
}
