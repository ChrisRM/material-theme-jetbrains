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
import com.mallowigi.idea.themes.lists.AccentResources.scrollbarHoverResources
import com.mallowigi.idea.themes.lists.AccentResources.scrollbarResources
import com.mallowigi.idea.themes.lists.MTThemeResources
import javax.swing.Icon

/**
 * Color Scheme page for Scrollbars
 *
 */
class MTScrollbarsPage : ColorSettingsPage, DisplayPrioritySortable {
  /**
   * No attribute descriptors
   *
   */
  override fun getAttributeDescriptors(): Array<AttributesDescriptor?> = arrayOfNulls(0)

  /**
   * Color descriptors: the scrollbar properties
   *
   */
  override fun getColorDescriptors(): Array<ColorDescriptor> = DESCRIPTORS

  /**
   * Page name
   *
   */
  override fun getDisplayName(): String = message("MTScrollbars.title")

  /**
   * Put it with the common settings
   *
   */
  override fun getPriority(): DisplayPriority = DisplayPriority.COMMON_SETTINGS

  /**
   * No icon
   *
   */
  override fun getIcon(): Icon? = null

  /**
   * No syntax highlighter
   */
  override fun getHighlighter(): SyntaxHighlighter = PlainSyntaxHighlighter()

  /**
   * No demo text
   *
   */
  override fun getDemoText(): String = " "

  /**
   * No additional highlightings
   *
   */
  override fun getAdditionalHighlightingTagToDescriptorMap(): Map<String, TextAttributesKey>? = null

  companion object {
    private val DESCRIPTORS: Array<ColorDescriptor>

    init {
      val colorDescriptors: MutableSet<ColorDescriptor> = mutableSetOf()
      val thumbnailResources = Sets.union(scrollbarHoverResources, scrollbarResources)
      val allResources = Sets.union(thumbnailResources, MTThemeResources.scrollBarResources)
      val (macResources, nonMacResources) = allResources.partition { it.contains("Mac.") }

      if (SystemInfoRt.isMac) {
        macResources.forEach { addColorDescriptor(colorDescriptors, it) }
      } else {
        nonMacResources.forEach { addColorDescriptor(colorDescriptors, it) }
      }

      DESCRIPTORS = ArrayUtil.toObjectArray(colorDescriptors, ColorDescriptor::class.java)
    }

    private fun addColorDescriptor(colorDescriptors: MutableSet<ColorDescriptor>, resourceKey: String) {
      colorDescriptors.add(
        ColorDescriptor(
          message(resourceKey),
          ColorKey.find(resourceKey),
          ColorDescriptor.Kind.BACKGROUND
        )
      )
    }
  }
}
