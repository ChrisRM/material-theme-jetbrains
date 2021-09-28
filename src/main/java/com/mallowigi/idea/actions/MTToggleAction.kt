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

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.ToggleAction
import com.intellij.openapi.actionSystem.Toggleable
import com.intellij.ui.LayeredIcon
import com.intellij.util.IconUtil
import com.intellij.util.ObjectUtils
import com.intellij.util.ui.GraphicsUtil
import com.intellij.util.ui.JBUI
import com.mallowigi.idea.license.MTMainProductLicenseChecker
import com.mallowigi.idea.messages.MaterialThemeBundle.message
import com.mallowigi.idea.notifications.MTNotifications.showSimple
import java.awt.Component
import java.awt.Graphics
import java.text.MessageFormat
import javax.swing.Icon
import javax.swing.UIManager

abstract class MTToggleAction : ToggleAction() {
  override fun update(e: AnActionEvent) {
    val selected = isSelected(e)
    val presentation = e.presentation
    val icon = presentation.icon

    Toggleable.setSelected(presentation, selected)
    val fallbackIcon = selectedFallbackIcon(icon)
    val actionButtonIcon = ObjectUtils.notNull(UIManager.getIcon("ActionButton.backgroundIcon"), fallbackIcon)

    // Recreate the action button look
    when {
      selected -> e.presentation.icon = LayeredIcon(actionButtonIcon, regularIcon(icon))
      else     -> e.presentation.icon = regularIcon(icon)
    }
    checkLicense(e)
  }

  override fun setSelected(e: AnActionEvent, state: Boolean) {
    val notificationMessage = e.presentation.text
    val restText = message(if (state) "action.toggle.enabled" else "action.toggle.disabled")
    showSimple(e.project!!, MessageFormat.format("<b>{0}</b> {1}", notificationMessage, restText))
  }

  protected open fun checkLicense(e: AnActionEvent) {
    e.presentation.isEnabled = MTMainProductLicenseChecker.instance.isLicensed
  }

  private fun regularIcon(icon: Icon): Icon = IconUtil.toSize(icon, JBUI.scale(18), JBUI.scale(18))

  private fun selectedFallbackIcon(icon: Icon?): Icon = object : Icon {
    override fun paintIcon(component: Component, g: Graphics, x: Int, y: Int) {
      val g2d = g.create()
      try {
        GraphicsUtil.setupAAPainting(g2d)
        g2d.color = JBUI.CurrentTheme.ActionButton.pressedBackground()
        g2d.fillRoundRect(0, 0, iconWidth, iconHeight, 4, 4)
      } finally {
        g2d.dispose()
      }
    }

    override fun getIconWidth(): Int = icon?.iconWidth ?: JBUI.scale(18)

    override fun getIconHeight(): Int = icon?.iconHeight ?: JBUI.scale(18)
  }
}
