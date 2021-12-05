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
package com.mallowigi.idea.notifications

import com.intellij.ide.BrowserUtil
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.fileEditor.impl.HTMLEditorProvider
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.ui.jcef.JBCefApp
import com.intellij.util.Urls.newFromEncoded
import com.mallowigi.idea.config.application.MTConfig
import com.mallowigi.idea.messages.MaterialThemeBundle
import com.mallowigi.idea.utils.MTUiUtils
import org.jetbrains.annotations.Contract

/**
 * Show the whats new notification.
 *
 */
class MTWhatsNewAction : AnAction(), DumbAware {
  /**
   * Action when clicking on the link
   *
   */
  override fun actionPerformed(e: AnActionEvent) {
    val whatsNewUrl = WHATS_NEW_URL
    val project = e.project

    if (project != null && JBCefApp.isSupported() && shouldShow()) {
      openWhatsNewFile(project, whatsNewUrl, null)
    } else {
      BrowserUtil.browse(whatsNewUrl)
    }
  }

  /**
   * Show notification according to the configuration
   *
   */
  override fun update(e: AnActionEvent) {
    val available = shouldShow()
    e.presentation.isEnabledAndVisible = available

    if (available) {
      e.presentation.text = MaterialThemeBundle.message("whats.new.action.title")
      e.presentation.description =
        MaterialThemeBundle.message("whats.new.action.custom.description", MTUiUtils.getVersion())
    }
  }

  companion object {
    /**
     * What's New URL
     */
    const val WHATS_NEW_URL: String = "https://www.material-theme.com/docs/what-s-new/"

    /**
     * Show the notification when the version is updated
     *
     */
    private fun shouldShow(): Boolean = MTUiUtils.getVersion() != MTConfig.getInstance().version

    /**
     * Open what's new file in the embedded browser
     *
     * @param project project to show the notification in
     * @param url url to open
     * @param content content to show (optional0
     */
    @Suppress("HardCodedStringLiteral")
    @JvmStatic
    @Contract("_, null, null -> fail")
    fun openWhatsNewFile(project: Project, url: String?, content: String?) {
      require(!(url == null && content == null))

      val title = MaterialThemeBundle.message("whats.new.action.title")

      if (!JBCefApp.isSupported()) {
        MTNotifications.showUpdate(project)
      } else if (url != null) {
        val themeId = MTConfig.getInstance().selectedTheme.themeId
        val embeddedUrl = newFromEncoded(url).addParameters(mapOf("theme" to themeId))
        val finalUrl = embeddedUrl.toExternalForm()
        val timeoutContent: String? = null

        HTMLEditorProvider.openEditor(project, title, finalUrl, timeoutContent)
      } else {
        HTMLEditorProvider.openEditor(project, title, content ?: return)
      }
    }
  }
}
