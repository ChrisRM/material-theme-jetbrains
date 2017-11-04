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

import com.chrisrm.idea.MTConfig;
import com.chrisrm.idea.messages.MaterialThemeBundle;
import com.intellij.ide.ui.OptionsTopHitProvider;
import com.intellij.ide.ui.PublicMethodBasedOptionDescription;
import com.intellij.ide.ui.search.BooleanOptionDescription;
import com.intellij.ide.ui.search.OptionDescription;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * Provide commands for Search Everything Top Hit commmands
 */
public final class MTConfigTopHitProvider extends OptionsTopHitProvider {

  private static final Collection<OptionDescription> OPTION_DESCRIPTIONS = Collections.unmodifiableCollection(Arrays.asList(
      option(messageIde("mt.contrast"), "getIsContrastMode", "setIsContrastMode"),
      option(messageIde("mt.materialdesign"), "getIsMaterialDesign", "setIsMaterialDesign"),
      option(messageIde("mt.boldtabs"), "getIsBoldTabs", "setIsBoldTabs"),
      option(messageIde("mt.iswallpaperset"), "isWallpaperSet", "setIsWallpaperSet"),
      option(messageIde("MTForm.isUpperCaseTabsCheckbox.text"), "isUpperCaseTabs", "setIsUpperCaseTabs"),


      option(messageIde("MTForm.customTreeIndentCheckbox.text"), "isCustomTreeIndent", "setIsCustomTreeIndent"),

      option(messageIde("MTForm.isMaterialIconsCheckbox.text"), "isUseMaterialIcons", "setUseMaterialIcons"),
      option(messageIde("MTForm.projectViewDecorators"), "isUseProjectViewDecorators", "setUseProjectViewDecorators"),
      option(messageIde("MTForm.hideFileIcons"), "getHideFileIcons", "setHideFileIcons"),
      option(messageIde("MTForm.isCompactSidebarCheckbox.text"), "isCompactSidebar", "setCompactSidebar"),
      option(messageIde("MTForm.isCompactStatusbarCheckbox.text"), "isCompactStatusBar", "setIsCompactStatusBar"),
      option(messageIde("MTForm.themeStatus"), "isStatusBarTheme", "setIsStatusBarTheme"),
      option(messageIde("MTForm.materialThemeCheckbox.text"), "isMaterialTheme", "setIsMaterialTheme"),

      option(messageIde("MTForm.themedScrollbarsCheckbox.text"), "isThemedScrollbars", "setThemedScrollbars"),
      option(messageIde("MTForm.accentScrollbarsCheckbox.text"), "isAccentScrollbars", "setAccentScrollbars")

  ));

  static String messageIde(final String property) {
    return StringUtil.stripHtml(MaterialThemeBundle.message(property), false);
  }

  static BooleanOptionDescription option(final String option, final String getter, final String setter) {
    return new PublicMethodBasedOptionDescription("Material Theme:" + option, "com.chrisrm.idea.config", getter, setter) {
      @Override
      public Object getInstance() {
        return MTConfig.getInstance();
      }

      @Override
      protected void fireUpdated() {
        MTConfig.getInstance().fireChanged();
      }
    };
  }

  @NotNull
  @Override
  public Collection<OptionDescription> getOptions(@Nullable final Project project) {
    return OPTION_DESCRIPTIONS;
  }

  @Override
  public String getId() {
    return "mtconfig";
  }
}
