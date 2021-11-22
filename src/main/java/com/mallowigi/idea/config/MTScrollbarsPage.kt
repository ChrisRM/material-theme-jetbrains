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

import com.google.common.collect.Sets
import com.intellij.openapi.editor.colors.ColorKey
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.PlainSyntaxHighlighter
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import com.intellij.openapi.util.SystemInfoRt
import com.intellij.psi.codeStyle.DisplayPriority
import com.intellij.psi.codeStyle.DisplayPrioritySortable
import com.intellij.util.ArrayUtil
import com.mallowigi.idea.messages.MaterialThemeBundle.message
import com.mallowigi.idea.themes.lists.AccentResources.SCROLLBAR_HOVER_RESOURCES
import com.mallowigi.idea.themes.lists.AccentResources.SCROLLBAR_RESOURCES
import com.mallowigi.idea.themes.lists.MTThemeResources
import org.jetbrains.annotations.NonNls
import javax.swing.Icon

class MTScrollbarsPage : ColorSettingsPage, DisplayPrioritySortable {
  override fun getAttributeDescriptors(): Array<AttributesDescriptor?> = arrayOfNulls(0)

  override fun getColorDescriptors(): Array<ColorDescriptor> = DESCRIPTORS

  override fun getDisplayName(): @NonNls String = message("MTScrollbars.title")

  override fun getPriority(): DisplayPriority = DisplayPriority.COMMON_SETTINGS

  override fun getIcon(): Icon? = null

  override fun getHighlighter(): SyntaxHighlighter = PlainSyntaxHighlighter()

  override fun getDemoText(): String = " "

  override fun getAdditionalHighlightingTagToDescriptorMap(): Map<String, TextAttributesKey>? = null

  companion object {
    private val DESCRIPTORS: Array<ColorDescriptor>

    init {
      val colorDescriptors: MutableSet<ColorDescriptor> = mutableSetOf()
      val thumbnailResources = Sets.union(SCROLLBAR_HOVER_RESOURCES, SCROLLBAR_RESOURCES)
      val allResources = Sets.union(thumbnailResources, MTThemeResources.SCROLLBAR_RESOURCES)
      val (macResources, nonMacResources) = allResources.partition { it.contains("Mac.") }

      if (SystemInfoRt.isMac) {
        macResources.forEach { addColorDescriptor(colorDescriptors, it) }
      } else {
        nonMacResources.forEach { addColorDescriptor(colorDescriptors, it) }
      }


      DESCRIPTORS = ArrayUtil.toObjectArray(colorDescriptors, ColorDescriptor::class.java)
    }

    private fun addColorDescriptor(colorDescriptors: MutableSet<ColorDescriptor>, resourceKey: String) {
      colorDescriptors.add(ColorDescriptor(
        message(resourceKey),
        ColorKey.find(resourceKey),
        ColorDescriptor.Kind.BACKGROUND))
    }
  }
}
