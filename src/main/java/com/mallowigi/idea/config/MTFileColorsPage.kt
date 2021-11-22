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
package com.mallowigi.idea.config

import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.PlainSyntaxHighlighter
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import com.intellij.openapi.vcs.FileStatusFactory
import com.intellij.psi.codeStyle.DisplayPriority
import com.intellij.psi.codeStyle.DisplayPrioritySortable
import com.intellij.util.ArrayUtil
import com.mallowigi.idea.messages.MaterialThemeBundle.message
import com.mallowigi.idea.schemes.MTFileColors.getColorKey
import javax.swing.Icon

class MTFileColorsPage : ColorSettingsPage, DisplayPrioritySortable {
  override fun getAttributeDescriptors(): Array<AttributesDescriptor> = ATTRIBUTES_DESCRIPTORS.clone()

  override fun getColorDescriptors(): Array<ColorDescriptor> = DESCRIPTORS

  override fun getDisplayName(): String = message("MTFileColors.colors.page.name")

  override fun getPriority(): DisplayPriority = DisplayPriority.COMMON_SETTINGS

  override fun getIcon(): Icon? = null

  override fun getHighlighter(): SyntaxHighlighter = PlainSyntaxHighlighter()

  override fun getDemoText(): String = " "

  override fun getAdditionalHighlightingTagToDescriptorMap(): Map<String, TextAttributesKey>? = null

  companion object {
    val DIRECTORIES: TextAttributesKey =
      TextAttributesKey.createTextAttributesKey("MT_DIRECTORIES", HighlighterColors.TEXT)

    private val DESCRIPTORS: Array<ColorDescriptor>

    private val ATTRIBUTES_DESCRIPTORS =
      arrayOf(AttributesDescriptor(message("material.file.directories"), DIRECTORIES))

    init {
      val allFileStatuses = FileStatusFactory.getInstance().allFileStatuses
      val colorDescriptors: MutableSet<ColorDescriptor> = mutableSetOf()
      allFileStatuses.forEach {
        colorDescriptors.add(ColorDescriptor(it.text,
                                             getColorKey(it)!!,
                                             ColorDescriptor.Kind.FOREGROUND))
      }

      DESCRIPTORS = ArrayUtil.toObjectArray(colorDescriptors, ColorDescriptor::class.java)
    }
  }
}
