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
package com.mallowigi.idea.config.project

import com.intellij.ide.ui.OptionsSearchTopHitProvider.ProjectLevelProvider
import com.intellij.ide.ui.PublicMethodBasedOptionDescription
import com.intellij.ide.ui.search.BooleanOptionDescription
import com.intellij.ide.ui.search.OptionDescription
import com.intellij.openapi.project.Project
import com.mallowigi.idea.messages.MaterialThemeBundle
import java.util.Collections
import java.util.function.Supplier

class MTProjectConfigTopHitProvider : ProjectLevelProvider {
  override fun getId(): String = "mtProjectConfig"

  override fun getOptions(project: Project): Collection<OptionDescription> {
    return Collections.unmodifiableCollection(
      listOf(
        option(project, MaterialThemeBundle.message("MTProjectForm.isActiveCheckbox.text"), "isActive", "setIsActive"),
        option(
          project,
          MaterialThemeBundle.message("MTProjectForm.activeTabHighlightCheckbox.text"),
          "isHighlightColorEnabled",
          "setHighlightColorEnabled"
        ),
        option(
          project,
          MaterialThemeBundle.message("MTProjectForm.isActiveBoldTabsCheckbox.text"),
          "isActiveBoldTab",
          "setIsActiveBoldTab"
        ),
        option(
          project,
          MaterialThemeBundle.message("MTProjectForm.isUpperCaseTabsCheckbox.text"),
          "isUpperCaseTabs",
          "setUpperCaseTabs"
        ),
        option(
          project,
          MaterialThemeBundle.message("MTProjectForm.showIconCheckbox.text"),
          "isUseProjectIcon",
          "setUseProjectIcon"
        ),
        option(
          project,
          MaterialThemeBundle.message("MTProjectForm.showProjectTitleCheckbox.text"),
          "isUseProjectTitle",
          "setUseProjectTitle"
        ),
        option(
          project,
          MaterialThemeBundle.message("MTProjectForm.useCustomTextCheckbox.text"),
          "isUseCustomTitle",
          "setUseCustomTitle"
        ),
        option(
          project,
          MaterialThemeBundle.message("MTProjectForm.useProjectFrameCheckbox.text"),
          "isUseProjectFrame",
          "setUseProjectFrame"
        ),
      )
    )
  }

  companion object {
    private fun option(
      project: Project,
      option: String?,
      getter: String,
      setter: String,
    ): BooleanOptionDescription {
      return object : PublicMethodBasedOptionDescription(
        MaterialThemeBundle.message("option.prefix.project") + option,
        MTProjectConfigurable.ID,
        getter,
        setter,
        Supplier { MTProjectConfig.getInstance(project) }
      ) {
        override fun getInstance(): MTProjectConfig = MTProjectConfig.getInstance(project)

        override fun fireUpdated() = MTProjectConfig.getInstance(project).fireChanged()
      }
    }
  }
}
