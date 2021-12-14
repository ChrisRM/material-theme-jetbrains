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
@file:Suppress("HardCodedStringLiteral", "RegExpRedundantEscape", "MagicNumber")

package com.mallowigi.idea.projectframe

import com.intellij.ide.RecentProjectsManagerBase
import com.intellij.openapi.fileEditor.ex.FileEditorManagerEx
import com.intellij.openapi.project.Project
import com.intellij.openapi.roots.ProjectRootManager
import com.intellij.ui.ColorUtil
import com.intellij.util.ui.JBUI
import com.mallowigi.idea.config.application.MTConfig
import com.mallowigi.idea.utils.MTUI
import com.mallowigi.idea.utils.MTUiUtils
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.Rectangle
import java.awt.RenderingHints
import javax.swing.JPanel
import kotlin.math.max

internal class MTProjectTitlePanel(private val myProject: Project) : JPanel(BorderLayout()) {
  private val projectPattern = Regex("\\{project\\}")
  private val modulePattern = Regex("\\{module\\}")
  private val filePattern = Regex("\\{file\\}")

  /**
   * Getter for the stripe color
   */
  private val stripeColor: Color
    get() {
      val projectConfig = MTUiUtils.getProjectConfigIfEnabled(myProject)
      if (projectConfig != null && projectConfig.isUseProjectFrame) return projectConfig.projectFrameColor

      val projectColor = Color(MTUiUtils.stringToARGB(myProject.name))
      return ColorUtil.withAlpha(MTUiUtils.darker(projectColor, 2), 0.5)
    }

  /**
   * The project frame text to draw
   */
  private val projectFrameText: String
    get() {
      val projectConfig = MTUiUtils.getProjectConfigIfEnabled(myProject)
      val mtConfig = MTConfig.getInstance()

      val textToDraw = when {
        projectConfig != null && projectConfig.isUseCustomTitle -> projectConfig.customTitle
        mtConfig.isUseCustomTitle                               -> mtConfig.customTitle
        else                                                    -> MTConfig.DEFAULT_TITLE
      }
      return replacePatterns(textToDraw)
    }

  /**
   * Draw project text and icon
   *
   * @param g
   * @param rect
   * @param projectText
   */
  @Suppress("MagicNumber")
  private fun drawProjectTextAndIcon(g: Graphics2D, rect: Rectangle, projectText: String) {
    val fm = g.getFontMetrics(g.font)
    val textWidth = fm.stringWidth(projectText) - 1
    val x = max(rect.x, rect.x + (rect.width - textWidth) / 2)
    val y = max(rect.y, rect.y + rect.height / 2 + fm.ascent * 2 / 5)
    val padding = JBUI.scale(4)
    val oldClip = g.clip

    // Draw icon
    if (shouldPaintIcon()) {
      val recentProjectsManage: RecentProjectsManagerBase = RecentProjectsManagerBase.instanceEx
      val recentIcon = recentProjectsManage.getProjectIcon(myProject.basePath ?: return, false)
      recentIcon.paintIcon(this, g, x - recentIcon.iconWidth - padding * 2, JBUI.scale(padding) / 2)
    }

    if (shouldDrawText()) {
      g.color = MTUI.Panel.background
      g.fillRoundRect(x - padding, padding, textWidth + padding * 2, rect.height - padding * 2, padding, padding)
      g.color = MTUI.Panel.foreground
      g.drawString(projectText, x, y)
      g.clip = oldClip
    }
  }

  /**
   * Paints the stripe and it's text/icon
   *
   */
  public override fun paintComponent(g: Graphics) {
    super.paintComponent(g)

    val graphics = g.create() as Graphics2D
    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
    graphics.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE)

    try {
      // paint stripe
      val headerRectangle = Rectangle(0, 0, width, JBUI.insetsTop(24).top)
      graphics.color = stripeColor
      graphics.fill(headerRectangle)
      graphics.font = MTUI.Panel.font

      // Draw the text according to the settings
      drawProjectTextAndIcon(graphics, headerRectangle, projectFrameText)
    } finally {
      graphics.dispose()
    }
  }

  /**
   * Checks whether the text should be drawn, from the configs
   *
   */
  private fun shouldDrawText(): Boolean {
    val projectConfig = MTUiUtils.getProjectConfigIfEnabled(myProject)
    return projectConfig?.isUseProjectTitle ?: MTConfig.getInstance().isUseProjectTitle
  }

  /**
   * Checks whether the icon should be paint, from the configs
   *
   */
  private fun shouldPaintIcon(): Boolean {
    val projectConfig = MTUiUtils.getProjectConfigIfEnabled(myProject)
    return projectConfig?.isUseProjectIcon ?: MTConfig.getInstance().isUseProjectIcon
  }

  /**
   * Replace patterns with their config value
   *
   * @param textToDraw text to replace
   * @return text replaced
   */
  private fun replacePatterns(textToDraw: String): String {
    var result = projectPattern.replace(textToDraw, myProject.name)

    if (textToDraw.contains("{module}")) result = replaceModulePattern(result)
    if (textToDraw.contains("{file}")) result = replaceFilePattern(result)
    return result
  }

  /**
   * Replace module pattern with current module if possible
   *
   * @param textToDraw text to replace
   * @return the replaced text
   */
  private fun replaceModulePattern(textToDraw: String): String {
    var newResult = textToDraw
    val virtualFile = FileEditorManagerEx.getInstanceEx(myProject).currentFile
    if (virtualFile != null) {
      val moduleForFile = ProjectRootManager.getInstance(myProject).fileIndex.getModuleForFile(virtualFile)
      if (moduleForFile != null) {
        newResult = modulePattern.replace(newResult, moduleForFile.name)
      }
    }
    return newResult
  }

  /**
   * Replace file pattern with current file if possible
   *
   * @param textToDraw text to replace
   * @return the replaced text
   */
  private fun replaceFilePattern(textToDraw: String): String {
    var newResult = textToDraw
    val virtualFile = FileEditorManagerEx.getInstanceEx(myProject).currentFile
    if (virtualFile != null) {
      newResult = filePattern.replace(newResult, virtualFile.name)
    }
    return newResult
  }

  override fun updateUI() {
    super.updateUI()
    isOpaque = false
  }
}
