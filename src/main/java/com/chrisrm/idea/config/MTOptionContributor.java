/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Chris Magnussen and Elior Boukhobza
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

package com.chrisrm.idea.config;

import com.chrisrm.idea.messages.MaterialThemeBundle;
import com.intellij.ide.ui.search.SearchableOptionContributor;
import com.intellij.ide.ui.search.SearchableOptionProcessor;
import org.jetbrains.annotations.NotNull;

/**
 * Provider for Searchable options
 */
public final class MTOptionContributor extends SearchableOptionContributor {
  @Override
  public void processOptions(@NotNull final SearchableOptionProcessor processor) {
    final MTConfigurable configurable = new MTConfigurable();
    final String displayName = configurable.getDisplayName();

    processor.addOptions(MaterialThemeBundle.message("mt.activetab"), null, displayName, MTConfigurable.ID,
        displayName, true);
    processor.addOptions(MaterialThemeBundle.message("mt.contrast"), null, displayName, MTConfigurable.ID,
        displayName, true);
    processor.addOptions(MaterialThemeBundle.message("mt.materialdesign"), null, displayName, MTConfigurable.ID,
        displayName, true);
    processor.addOptions(MaterialThemeBundle.message("mt.boldtabs"), null, displayName, MTConfigurable.ID,
        displayName, true);
    processor.addOptions(MaterialThemeBundle.message("MTForm.isUpperCaseTabsCheckbox.text"), null, displayName, MTConfigurable.ID,
        displayName, true);
    processor.addOptions(MaterialThemeBundle.message("MTForm.tabHeight"), null, displayName, MTConfigurable.ID,
        displayName, true);
    processor.addOptions(MaterialThemeBundle.message("mt.iswallpaperset"), null, displayName, MTConfigurable.ID,
        displayName, true);
    processor.addOptions(MaterialThemeBundle.message("MTForm.customTreeIndentCheckbox.text"), null, displayName, MTConfigurable.ID,
        displayName, true);
    processor.addOptions(MaterialThemeBundle.message("MTForm.isMaterialIconsCheckbox.text"), null, displayName, MTConfigurable.ID,
        displayName, true);
    processor.addOptions(MaterialThemeBundle.message("MTForm.projectViewDecorators"), null, displayName, MTConfigurable.ID,
        displayName, true);
    processor.addOptions(MaterialThemeBundle.message("MTForm.hideFileIcons"), null, displayName, MTConfigurable.ID,
        displayName, true);
    processor.addOptions(MaterialThemeBundle.message("MTForm.isCompactSidebarCheckbox.text"), null, displayName, MTConfigurable.ID,
        displayName, true);
    processor.addOptions(MaterialThemeBundle.message("MTForm.isCompactStatusbarCheckbox.text"), null, displayName, MTConfigurable.ID,
        displayName, true);
    processor.addOptions(MaterialThemeBundle.message("MTForm.themeStatus"), null, displayName, MTConfigurable.ID,
        displayName, true);
    processor.addOptions(MaterialThemeBundle.message("MTForm.materialThemeCheckbox.text"), null, displayName, MTConfigurable.ID,
        displayName, true);
    processor.addOptions(MaterialThemeBundle.message("MTForm.themedScrollbarsCheckbox.text"), null, displayName, MTConfigurable.ID,
        displayName, true);
    processor.addOptions(MaterialThemeBundle.message("MTForm.accentScrollbarsCheckbox.text"), null, displayName, MTConfigurable.ID,
        displayName, true);
  }
}
