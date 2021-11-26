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
package com.mallowigi.idea.schemes

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.editor.colors.ColorKey
import com.intellij.openapi.editor.colors.EditorColorsListener
import com.intellij.openapi.editor.colors.EditorColorsManager
import com.intellij.openapi.editor.colors.EditorColorsScheme
import com.intellij.openapi.editor.colors.impl.AbstractColorsScheme
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.vcs.FileStatus
import com.intellij.openapi.vcs.FileStatusFactory
import com.intellij.openapi.vcs.FileStatusManager
import com.intellij.ui.ColorUtil
import com.mallowigi.idea.config.MTFileColorsPage
import com.mallowigi.idea.config.application.MTConfig
import com.mallowigi.idea.messages.MaterialThemeBundle.messageOrDefault
import org.jetbrains.annotations.NonNls

object MTFileColors {
  @NonNls
  private val MT_PREFIX: String = "MT_FILESTATUS_"

  private val COLOR_KEYS = HashMap<FileStatus, ColorKey>(18)

  private val currentSchemeForCurrentUITheme: EditorColorsScheme
    get() = EditorColorsManager.getInstance().schemeForCurrentUITheme

  private fun apply() {
    if (MTConfig.getInstance().isFileStatusColorsEnabled) applyFileStatuses()
    applyStyleDirectories()
  }

  private fun applyFileStatuses() {
    val defaultScheme = currentSchemeForCurrentUITheme
    val allFileStatuses = FileStatusFactory.getInstance().allFileStatuses

    for (fileStatus in allFileStatuses) {
      val mtColorKey = getColorKey(fileStatus)
      if (mtColorKey != null) {
        val color = defaultScheme.getColor(mtColorKey)
        if (color != null) {
          defaultScheme.setColor(fileStatus.colorKey, color)
        }
      }
    }

    (defaultScheme as AbstractColorsScheme).setSaveNeeded(true)

    for (project in ProjectManager.getInstance().openProjects) {
      FileStatusManager.getInstance(project).fileStatusesChanged()
    }
  }

  private fun applyStyleDirectories() {
    if (!MTConfig.getInstance().isStyledDirectories) return

    val defaultScheme = currentSchemeForCurrentUITheme
    val globalScheme = EditorColorsManager.getInstance().globalScheme

    defaultScheme.setAttributes(MTFileColorsPage.DIRECTORIES, globalScheme.getAttributes(MTFileColorsPage.DIRECTORIES))
    (defaultScheme as AbstractColorsScheme).setSaveNeeded(true)

    for (project in ProjectManager.getInstance().openProjects) {
      FileStatusManager.getInstance(project).fileStatusesChanged()
    }
  }

  private fun initFileColors() {
    // Load all registered file statuses and read their colors from the properties
    val allFileStatuses = FileStatusFactory.getInstance().allFileStatuses
    for (allFileStatus in allFileStatuses) {
      // 1. Get the original file color
      val originalColor = allFileStatus.color
      if (originalColor != null) {
        // 2. if there is an original file color
        val originalColorString = ColorUtil.toHex(originalColor)
        // 2a. Get custom file color from the bundle, or default to original file color
        val property = messageOrDefault(
          "material.file." + allFileStatus.id.lowercase(),
          originalColorString
        )
        val color = ColorUtil.fromHex(property)

        // 2b. Set in the map the custom/default file color
        COLOR_KEYS[allFileStatus] = ColorKey.createColorKey(MT_PREFIX + allFileStatus.id, color)
      } else {
        // 3. If there is no default file color
        // 3a. Get custom file color from the bundle
        val property = messageOrDefault("material.file." + allFileStatus.id.lowercase(), "-1")
        // If not found do not add the color to the map
        if (property == "-1") {
          COLOR_KEYS[allFileStatus] = ColorKey.createColorKey(MT_PREFIX + allFileStatus.id)
          continue
        }

        // 3b. add custom color to the map
        val color = ColorUtil.fromHex(property)
        COLOR_KEYS[allFileStatus] = ColorKey.createColorKey(MT_PREFIX + allFileStatus.id, color)
      }
    }
  }

  @JvmStatic
  fun getColorKey(status: FileStatus): ColorKey? = COLOR_KEYS[status]

  init {
    initFileColors()

    // Listen for color scheme changes and update the file colors
    ApplicationManager.getApplication().messageBus.connect()
      .subscribe(EditorColorsManager.TOPIC, EditorColorsListener { apply() })

    apply()
  }
}
