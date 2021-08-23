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

package com.mallowigi.idea.integrations

import com.intellij.ide.ui.LafManager
import com.intellij.ide.ui.LafManagerListener
import com.intellij.openapi.editor.colors.EditorColors
import com.intellij.openapi.editor.colors.EditorColors.IDENTIFIER_UNDER_CARET_ATTRIBUTES
import com.intellij.openapi.editor.colors.EditorColorsManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity
import com.mallowigi.idea.MTThemeManager
import org.intellij.plugins.xpathView.Config
import org.intellij.plugins.xpathView.XPathAppComponent

/**
 * Support for XPath
 *
 * @constructor Create empty X path listener
 */
class XPathListener : LafManagerListener, StartupActivity {
  override fun lookAndFeelChanged(source: LafManager): Unit = installXPathSearchColors()

  private fun installXPathSearchColors() {
    val currentLookAndFeel = LafManager.getInstance().currentLookAndFeel
    val xpathConfig = XPathAppComponent.getInstance().config
    if (MTThemeManager.isMaterialTheme(currentLookAndFeel)) {
      val schemeForCurrentUITheme = EditorColorsManager.getInstance().schemeForCurrentUITheme
      // install themed search results text attributes.
      val textSearchResultAttributes = schemeForCurrentUITheme.getAttributes(EditorColors.TEXT_SEARCH_RESULT_ATTRIBUTES)
      xpathConfig.attributes.backgroundColor = textSearchResultAttributes.backgroundColor
      xpathConfig.attributes.foregroundColor = textSearchResultAttributes.foregroundColor
      xpathConfig.contextAttributes.backgroundColor =
        schemeForCurrentUITheme.getAttributes(IDENTIFIER_UNDER_CARET_ATTRIBUTES).backgroundColor
    } else {
      // reset to defaults
      val config = Config()
      xpathConfig.attributes.backgroundColor = config.attributes.backgroundColor
      xpathConfig.attributes.foregroundColor = config.attributes.foregroundColor

      xpathConfig.contextAttributes.backgroundColor = config.contextAttributes.backgroundColor
      xpathConfig.contextAttributes.foregroundColor = config.contextAttributes.foregroundColor
    }
  }

  override fun runActivity(project: Project): Unit = installXPathSearchColors()
}
