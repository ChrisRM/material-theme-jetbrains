/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 Chris Magnussen and Elior Boukhobza
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
import com.google.common.collect.Lists;
import com.intellij.ide.ui.search.SearchableOptionContributor;
import com.intellij.ide.ui.search.SearchableOptionProcessor;
import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.List;

/**
 * Provider for Searchable options
 */
@SuppressWarnings({"OverlyLongMethod",
    "FeatureEnvy"})
public final class MTOptionContributor extends SearchableOptionContributor {
  @Override
  public void processOptions(@NotNull final SearchableOptionProcessor processor) {
    final Configurable configurable = new MTConfigurable();
    final String displayName = configurable.getDisplayName();

    final List<String> strings = Collections.unmodifiableList((
        Lists.newArrayList(
            //region Strings
            MaterialThemeBundle.message("MTForm.accentScrollbarsCheckbox.text"),
            MaterialThemeBundle.message("MTForm.activeTabHighlightCheckbox.text"),
            MaterialThemeBundle.message("MTForm.arrowsStyleLabel.text"),
            MaterialThemeBundle.message("MTForm.codeAdditionsCheckBox.text"),
            MaterialThemeBundle.message("MTForm.compactDropdownsCheckbox.text"),
            MaterialThemeBundle.message("MTForm.compactPanel.tab.title"),
            MaterialThemeBundle.message("MTForm.componentDesc.textWithMnemonic"),
            MaterialThemeBundle.message("MTForm.componentsPanel.tab.title"),
            MaterialThemeBundle.message("MTForm.contrastCheckBox.text"),
            MaterialThemeBundle.message("MTForm.customAccentColorLabel.text"),
            MaterialThemeBundle.message("MTForm.customTreeIndentCheckbox.text"),
            MaterialThemeBundle.message("MTForm.decoratedFoldersCheckbox.text"),
            MaterialThemeBundle.message("MTForm.directoriesColorLink.text"),
            MaterialThemeBundle.message("MTForm.featuresDesc.textWithMnemonic"),
            MaterialThemeBundle.message("MTForm.featuresPanel.tab.title"),
            MaterialThemeBundle.message("MTForm.fileColorsCheckbox.text"),
            MaterialThemeBundle.message("MTForm.fileColorsLink.text"),
            MaterialThemeBundle.message("MTForm.fileStatusColorsLink.text"),
            MaterialThemeBundle.message("MTForm.fontSizeCheckbox.text"),
            MaterialThemeBundle.message("MTForm.hideFileIcons.text"),
            MaterialThemeBundle.message("MTForm.highContrastCheckbox.text"),
            MaterialThemeBundle.message("MTForm.iconsDesc.textWithMnemonic"),
            MaterialThemeBundle.message("MTForm.iconsPanel.tab.title"),
            MaterialThemeBundle.message("MTForm.indicatorThicknessLabel.text"),
            MaterialThemeBundle.message("MTForm.isCompactMenusCheckbox.text"),
            MaterialThemeBundle.message("MTForm.isCompactSidebarCheckbox.text"),
            MaterialThemeBundle.message("MTForm.isCompactStatusbarCheckbox.text"),
            MaterialThemeBundle.message("MTForm.isCompactTablesCheckbox.text"),
            MaterialThemeBundle.message("MTForm.isFileIconsCheckbox.text"),
            MaterialThemeBundle.message("MTForm.isMaterialDesignCheckbox.text"),
            MaterialThemeBundle.message("MTForm.isMaterialIconsCheckbox.text"),
            MaterialThemeBundle.message("MTForm.isUpperCaseTabsCheckbox.text"),
            MaterialThemeBundle.message("MTForm.leftLabel.text"),
            MaterialThemeBundle.message("MTForm.materialThemeCheckbox.text"),
            MaterialThemeBundle.message("MTForm.monochromeCheckbox.text"),
            MaterialThemeBundle.message("MTForm.opacityLabel.text"),
            MaterialThemeBundle.message("MTForm.otherTweaksPanel.tab.title"),
            MaterialThemeBundle.message("MTForm.overrideAccentCheckbox.text"),
            MaterialThemeBundle.message("MTForm.panelDesc.textWithMnemonic"),
            MaterialThemeBundle.message("MTForm.positionLabel.text"),
            MaterialThemeBundle.message("MTForm.projectViewDecoratorsCheckBox.text"),
            MaterialThemeBundle.message("MTForm.projectViewDesc.textWithMnemonic"),
            MaterialThemeBundle.message("MTForm.projectViewPanel.tab.title"),
            MaterialThemeBundle.message("MTForm.psiIconsCheckbox.text"),
            MaterialThemeBundle.message("MTForm.resetDefaultsButton.text"),
            MaterialThemeBundle.message("MTForm.rightLabel.text"),
            MaterialThemeBundle.message("MTForm.scrollbarsLink.text"),
            MaterialThemeBundle.message("MTForm.selectedIndicatorLabel.text"),
            MaterialThemeBundle.message("MTForm.selectedThemeLabel.text"),
            MaterialThemeBundle.message("MTForm.styledDirectoriesCheckbox.text"),
            MaterialThemeBundle.message("MTForm.tabFontSizeCheckbox.text"),
            MaterialThemeBundle.message("MTForm.tabHeight.text"),
            MaterialThemeBundle.message("MTForm.tabPanel.tab.title"),
            MaterialThemeBundle.message("MTForm.tabsDesc.textWithMnemonic"),
            MaterialThemeBundle.message("MTForm.tabShadowCheckbox.text"),
            MaterialThemeBundle.message("MTForm.themedScrollbarsCheckbox.text"),
            MaterialThemeBundle.message("MTForm.themedTitleBarCheckbox.text"),
            MaterialThemeBundle.message("MTForm.themeStatusBar.text"),
            MaterialThemeBundle.message("MTForm.thicknessLabel.text"),
            MaterialThemeBundle.message("MTForm.tweaksDesc.textWithMnemonic"),
            MaterialThemeBundle.message("MTForm.upperCaseButtonsCheckbox.text"),
            MaterialThemeBundle.message("MTForm.useMaterialFontCheckbox.text")
            //endregion
        )));

    for (final String s : strings) {
      processor.addOptions(s, null, displayName, MTConfigurable.ID, displayName, true);
    }
  }
}
