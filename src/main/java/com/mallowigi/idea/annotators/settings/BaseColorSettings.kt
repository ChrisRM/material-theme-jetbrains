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

import com.intellij.lang.Language
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import com.intellij.psi.codeStyle.DisplayPriority
import com.intellij.psi.codeStyle.DisplayPrioritySortable
import javax.swing.Icon

/**
 * Base color settings
 *
 */
abstract class BaseColorSettings : ColorSettingsPage, DisplayPrioritySortable {
  /**
   * Get syntax highlighter with fallback
   *
   * @param lang the current language
   * @return the syntax highlighter for the language, or the any language highlighter
   */
  fun getSyntaxHighlighterWithFallback(lang: Language): SyntaxHighlighter {
    return SyntaxHighlighterFactory.getSyntaxHighlighter(lang, null, null)
      ?: return SyntaxHighlighterFactory.getSyntaxHighlighter(Language.ANY, null, null)
  }

  /**
   * Icon (unused)
   *
   */
  abstract override fun getIcon(): Icon

  /**
   * Returns the highlighter
   *
   */
  abstract override fun getHighlighter(): SyntaxHighlighter

  /**
   * The demo text to display
   *
   */
  abstract override fun getDemoText(): String

  /**
   * Additional highlighting tags to highlight the demo text
   *
   */
  abstract override fun getAdditionalHighlightingTagToDescriptorMap(): Map<String, TextAttributesKey>

  /**
   * The language additions attributes
   *
   */
  abstract override fun getAttributeDescriptors(): Array<AttributesDescriptor>

  /**
   * Color descriptors are empty
   *
   */
  abstract override fun getColorDescriptors(): Array<ColorDescriptor>

  /**
   * Display name
   *
   */
  abstract override fun getDisplayName(): String

  /**
   * Displays the page on the top on WebStorm
   *
   */
  @Suppress("UnstableApiUsage")
  abstract override fun getPriority(): DisplayPriority
}
