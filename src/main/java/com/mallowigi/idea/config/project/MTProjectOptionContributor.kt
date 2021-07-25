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

import com.intellij.ide.ui.search.SearchableOptionContributor
import com.intellij.ide.ui.search.SearchableOptionProcessor
import com.intellij.openapi.options.Configurable
import com.mallowigi.idea.messages.MaterialThemeBundle
import java.util.Collections

/**
 * Provider for Searchable options
 */
class MTProjectOptionContributor : SearchableOptionContributor() {
  override fun processOptions(processor: SearchableOptionProcessor) {
    val configurable: Configurable = MTProjectConfigurable(null)
    val displayName = configurable.displayName
    val strings = Collections.unmodifiableList(
      listOf(
        //region List of Strings
        MaterialThemeBundle.message("MTProjectForm.activeTabHighlightCheckbox.text"),
        MaterialThemeBundle.message("MTProjectForm.isUpperCaseTabsCheckbox.text"),
        MaterialThemeBundle.message("MTProjectForm.isActiveBoldTabsCheckbox.text"),
        MaterialThemeBundle.message("MTProjectForm.positionLabel.text"),
        MaterialThemeBundle.message("MTProjectForm.tabPanel.tab.title"),
        MaterialThemeBundle.message("MTProjectForm.thicknessLabel.text"),
        MaterialThemeBundle.message("MTProjectForm.useProjectFrameCheckbox.text"),
        MaterialThemeBundle.message("MTProjectForm.showProjectTitleCheckbox.text"),
        MaterialThemeBundle.message("MTProjectForm.showIconCheckbox.text"),
        MaterialThemeBundle.message("MTProjectForm.useCustomTextCheckbox.text"),
        //endregion
      )
    )
    for (s in strings) {
      processor.addOptions(s ?: return, null, displayName, MTProjectConfigurable.ID, displayName, true)
    }
  }
}
