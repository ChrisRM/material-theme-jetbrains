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
package com.mallowigi.idea.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.Messages
import com.mallowigi.idea.MTAnalytics
import com.mallowigi.idea.MTThemeManager
import com.mallowigi.idea.messages.MaterialThemeBundle.message
import com.mallowigi.idea.notifications.MTNotifications

/**
 * Resets the color scheme
 *
 */
class MTResetColorScheme : AnAction() {
  /**
   * Action performed
   *
   */
  override fun actionPerformed(e: AnActionEvent) {
    if (Messages.showOkCancelDialog(
        /* message = */ message("action.MTResetColorScheme.explanation"),
        /* title = */ message("action.MTResetColorScheme.text"),
        /* okText = */ message("common.ok"),
        /* cancelText = */ message("common.cancel"),
        /* icon = */ Messages.getQuestionIcon()
      ) == Messages.OK
    ) {
      MTThemeManager.resetColorScheme()

      MTAnalytics.instance.track(MTAnalytics.RESET_COLOR_SCHEME)
      MTNotifications.showSimple(e.project ?: return, message("MTResetColorScheme.notification"))
    }
  }

  /**
   * Is dumb aware
   *
   */
  override fun isDumbAware(): Boolean = true
}
