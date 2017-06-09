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

public class MTConfigTopHitProvider extends OptionsTopHitProvider {

  private static final Collection<OptionDescription> ourOptions = Collections.unmodifiableCollection(Arrays.asList(
      option(messageIde("mt.activetab"), "getHighlightColorEnabled", "setHighlightColorEnabled"),
      option(messageIde("mt.contrast"), "getIsContrastMode", "setIsContrastMode"),
      option(messageIde("mt.materialdesign"), "getIsMaterialDesign", "setIsMaterialDesign"),
      option(messageIde("mt.boldtabs"), "getIsBoldTabs", "setIsBoldTabs"),
      option(messageIde("mt.iswallpaperset"), "isWallpaperSet", "setIsWallpaperSet"),

      option(messageIde("MTForm.customTreeIndentCheckbox.text"), "isCustomTreeIndent", "setIsCustomTreeIndent"),

      option(messageIde("MTForm.isMaterialIconsCheckbox.text"), "isUseMaterialIcons", "setUseMaterialIcons"),
      option(messageIde("MTForm.projectViewDecorators"), "isUseProjectViewDecorators", "setUseProjectViewDecorators"),
      option(messageIde("MTForm.hideFileIcons"), "getHideFileIcons", "setHideFileIcons"),
      option(messageIde("MTForm.isCompactSidebarCheckbox.text"), "isCompactSidebar", "setCompactSidebar"),
      option(messageIde("MTForm.isCompactStatusbarCheckbox.text"), "isCompactStatusBar", "setIsCompactStatusBar"),
      option(messageIde("MTForm.themeStatus"), "isStatusBarTheme", "setIsStatusBarTheme"),
      option(messageIde("MTForm.materialThemeCheckbox.text"), "isMaterialTheme", "setIsMaterialTheme"),

      option(messageIde("MTForm.themedScrollbarsCheckbox.text"), "isThemedScrollbars", "setThemedScrollbars")

  ));

  static String messageIde(String property) {
    return StringUtil.stripHtml(MaterialThemeBundle.message(property), false);
  }

  static BooleanOptionDescription option(String option, String getter, String setter) {
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
  public Collection<OptionDescription> getOptions(@Nullable Project project) {
    return ourOptions;
  }

  @Override
  public String getId() {
    return "mtconfig";
  }
}
