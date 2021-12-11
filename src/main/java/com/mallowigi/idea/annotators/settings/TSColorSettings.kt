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
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.mallowigi.idea.annotators.TSAnnotator
import com.mallowigi.idea.messages.LanguageAdditionsBundle.message
import gnu.trove.THashMap
import java.util.Collections
import javax.swing.Icon

/**
 * TypeScript Additions Color settings
 *
 */
class TSColorSettings : JSColorSettings() {
  override fun getIcon(): Icon = AllIcons.FileTypes.Any_type

  override fun getHighlighter(): SyntaxHighlighter {
    val lang = Language.findLanguageByID("TypeScript") ?: Language.ANY
    return getSyntaxHighlighterWithFallback(lang)
  }

  override fun getDemoText(): String = message("TSColorPage.demoText")

  override fun getAdditionalHighlightingTagToDescriptorMap(): Map<String, TextAttributesKey> =
    Collections.unmodifiableMap(TS_DESCRIPTORS)

  override fun getAttributeDescriptors(): Array<AttributesDescriptor> = TS_ATTRIBUTES

  override fun getDisplayName(): String = message("TSColorPage.ts.additions")

  companion object {
    private val TS_ATTRIBUTES: Array<AttributesDescriptor>
    private val TS_DESCRIPTORS: MutableMap<String, TextAttributesKey> = THashMap()
    private val PRIVATE = TSAnnotator.PRIVATE
    private val DECLARE = TSAnnotator.DECLARE
    private val TYPE_ALIAS = TSAnnotator.TYPE_ALIAS
    private val ANY = TSAnnotator.ANY
    private val INLINE = TSAnnotator.INLINE
    private val ENUM = TSAnnotator.ENUM
    private val PRIM_TYPE = TSAnnotator.PRIM_TYPE

    init {
      TS_ATTRIBUTES = arrayOf(
        AttributesDescriptor(message("keywords.visit.js"), TextAttributesKey.createTextAttributesKey("")),
        AttributesDescriptor(message("keywords.private.public.protected"), PRIVATE),
        AttributesDescriptor(message("keywords.declare"), DECLARE),
        AttributesDescriptor(message("keywords.type.alias"), TYPE_ALIAS),
        AttributesDescriptor(message("keywords.ts.inline"), INLINE),
        AttributesDescriptor(message("keywords.enum"), ENUM),
        AttributesDescriptor(message("types.any.unknown"), ANY),
        AttributesDescriptor(message("types.primitives"), PRIM_TYPE)
      )
      TS_DESCRIPTORS.putAll(createAdditionalHlAttrs())
      TS_DESCRIPTORS.putAll(JS_DESCRIPTORS)
    }

    @Suppress("HardCodedStringLiteral")
    private fun createAdditionalHlAttrs(): Map<String, TextAttributesKey> {
      val descriptors: MutableMap<String, TextAttributesKey> = THashMap()
      descriptors["private"] = PRIVATE
      descriptors["declare"] = DECLARE
      descriptors["type"] = TYPE_ALIAS
      descriptors["any"] = ANY
      descriptors["inline2"] = INLINE
      descriptors["enum"] = ENUM
      descriptors["primtype"] = PRIM_TYPE
      return descriptors
    }
  }
}
