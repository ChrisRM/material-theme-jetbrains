/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Chris Magnussen and Elior Boukhobza
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

package com.chrisrm.idea.icons.tinted;

import com.chrisrm.idea.MTConfig;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.util.IconLoader;
import com.intellij.ui.ColorUtil;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public final class TintedIconsService {

  public static final String[] TINTED_ICONS = new String[]{
      "/icons/actions/closeHovered.svg",
      "/icons/actions/closeNewHovered.svg",
      "/icons/general/expandAllHover.svg",
      "/icons/general/expandComponentHover.svg",
      "/icons/general/collapseAllHover.svg",
      "/icons/general/collapseComponentHover.svg",
      "/icons/general/copyHovered.svg",
      "/icons/general/gearHover.svg",
      "/icons/general/gearPlainHover.svg",
      "/icons/general/hideDownHover.svg",
      "/icons/general/hideDownPartHover.svg",
      "/icons/general/hideLeftHover.svg",
      "/icons/general/hideLeftPartHover.svg",
      "/icons/general/hideRightHover.svg",
      "/icons/general/hideRightPartHover.svg",
      "/icons/general/inline_edit_hovered.svg",
      "/icons/general/inspectionsError.svg",
      "/icons/general/locateHover.svg",
      "/icons/general/modified.svg",
      "/icons/general/openDiskHover.svg",
      "/icons/general/projectConfigurable.svg",
      "/icons/ide/rating.svg",
      "/icons/ide/rating1.svg",
      "/icons/ide/rating2.svg",
      "/icons/ide/rating3.svg",
      "/icons/ide/rating4.svg",
      "/icons/mac/tree_white_down_arrow_selected.svg",
      "/icons/mac/tree_white_right_arrow_selected.svg",
      "/icons/mac/darcula/tree_white_down_arrow_selected.svg",
      "/icons/mac/darcula/tree_white_right_arrow_selected.svg",
      "/icons/mac/plusminus/plus_selected.svg",
      "/icons/mac/plusminus/minus_selected.svg",
      "/icons/nodes/pinToolWindow.svg",
      "/icons/nodes/tabPin.svg",
      "/icons/process/step_1.svg",
      "/icons/process/step_2.svg",
      "/icons/process/step_3.svg",
      "/icons/process/step_4.svg",
      "/icons/process/step_5.svg",
      "/icons/process/step_6.svg",
      "/icons/process/step_7.svg",
      "/icons/process/step_8.svg",
      "/icons/process/step_9.svg",
      "/icons/process/step_10.svg",
      "/icons/process/step_11.svg",
      "/icons/process/step_12.svg",
      "/icons/process/step_mask.svg",
      "/icons/process/step_passive.svg",
      "/icons/process/big/step_passive.svg",
  };
  private static final List<String> MY_TINTED_ICONS = Arrays.asList(TintedIconsService.TINTED_ICONS);

  public static final String[] THEMED_ICONS = {
      "/icons/nodes/folder.svg",
      "/icons/nodes/TreeClosed.svg",
      "/icons/nodes/folderClosed.svg",
      "/icons/nodes/folderOpen.svg",
      "/icons/plugins/datagrip/objectGroup.svg",
      "/icons/plugins/datagrip/table.svg",
      "/icons/mac/tree_white_down_arrow.svg",
      "/icons/mac/tree_white_right_arrow.svg",
      "/icons/mac/darcula/tree_white_down_arrow.svg",
      "/icons/mac/darcula/tree_white_right_arrow.svg",
      "/icons/mac/plusminus/plus.svg",
      "/icons/mac/plusminus/minus.svg"};
  private static final List<String> MY_THEMED_ICONS = Arrays.asList(THEMED_ICONS);

  public static TintedIconsService getInstance() {
    return ServiceManager.getService(TintedIconsService.class);
  }

  private TintedIconsService() {
  }

  @NotNull
  public static Icon getIcon(final String newPath) {
    return getIcon(newPath, MTConfig.getInstance().getAccentColor());
  }

  @NotNull
  public static Icon getIcon(final String newPath, final String accentColor) {
    if (newPath == null) {
      return IconLoader.getTransparentIcon(AllIcons.FileTypes.Any_type, 0);
    } else if (MY_TINTED_ICONS.contains(newPath)) {
      return new TintedIcon(IconLoader.getIcon(newPath), ColorUtil.fromHex(accentColor), newPath);
    } else if (MY_THEMED_ICONS.contains(newPath)) {
      final Color folderColor = MTConfig.getInstance().getSelectedTheme().getTheme().getPrimaryColor();
      return new TintedIcon(IconLoader.getIcon(newPath), folderColor, newPath);
    }
    return IconLoader.getIcon(newPath);
  }

  @NotNull
  public static Icon getAccentIcon(@NotNull final String newPath) {
    final String accentColor = MTConfig.getInstance().getAccentColor();
    return new TintedIcon(IconLoader.getIcon(newPath), ColorUtil.fromHex(accentColor), newPath);
  }

  @NotNull
  public static Icon getThemedIcon(@NotNull final String newPath) {
    final Color folderColor = MTConfig.getInstance().getSelectedTheme().getTheme().getPrimaryColor();
    return new TintedIcon(IconLoader.getIcon(newPath), folderColor, newPath);
  }

  @NotNull
  public static Icon getTintedIcon(@NotNull final String newPath, final Color color) {
    return new TintedIcon(IconLoader.getIcon(newPath), color, newPath);
  }

  @NotNull
  public static Icon getTintedIcon(@NotNull final String newPath, final String color) {
    return new TintedIcon(IconLoader.getIcon(newPath), ColorUtil.fromHex(color), newPath);
  }

  @NotNull
  public static Icon getTransparentIcon() {
    return IconLoader.getTransparentIcon(AllIcons.FileTypes.Any_type, 0);
  }
}
