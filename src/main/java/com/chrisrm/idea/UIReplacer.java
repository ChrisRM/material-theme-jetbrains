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

package com.chrisrm.idea;

import com.chrisrm.idea.ui.MTActionButtonLook;
import com.chrisrm.idea.ui.MTNavBarUI;
import com.chrisrm.idea.utils.StaticPatcher;
import com.intellij.codeInsight.lookup.impl.LookupCellRenderer;
import com.intellij.ide.actions.Switcher;
import com.intellij.ide.navigationToolbar.ui.NavBarUIManager;
import com.intellij.ide.plugins.PluginManagerConfigurableNew;
import com.intellij.openapi.actionSystem.ex.ActionButtonLook;
import com.intellij.openapi.options.newEditor.SettingsTreeView;
import com.intellij.openapi.wm.impl.status.MemoryUsagePanel;
import com.intellij.ui.CaptionPanel;
import com.intellij.ui.ColorUtil;
import com.intellij.ui.Gray;
import com.intellij.ui.JBColor;
import com.intellij.ui.tabs.FileColorManagerImpl;
import com.intellij.ui.tabs.TabsUtil;
import com.intellij.util.ObjectUtils;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.JBValue;
import com.intellij.util.ui.UIUtil;
import com.intellij.vcs.log.VcsLogStandardColors;
import com.intellij.vcs.log.ui.highlighters.CurrentBranchHighlighter;
import com.intellij.vcs.log.ui.highlighters.MergeCommitsHighlighter;

import javax.swing.*;
import javax.swing.plaf.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;

@SuppressWarnings("FeatureEnvy")
public enum UIReplacer {
  DEFAULT;

  public static void patchUI() {
    try {
      patchTabs();
      //      patchTables();
      patchGrays();
      patchMemoryIndicator();
      //      patchAutocomplete();
      //      patchScrollbars();
      patchDialogs();
      //      patchVCS();
      patchSettings();
      patchScopes();
      patchNavBar();
      patchIdeaActionButton();
      patchOnMouseOver();
      //      patchPluginPage();
    } catch (final IllegalAccessException | NoSuchFieldException e) {
      e.printStackTrace();
    }
  }

  private static void patchOnMouseOver() throws NoSuchFieldException, IllegalAccessException {
    StaticPatcher.setFinalStatic(Switcher.class, "ON_MOUSE_OVER_BG_COLOR", UIUtil.getListSelectionBackground(true));
  }

  /**
   * Set the color of even rows in tables
   */
  @Deprecated
  static void patchTables() throws NoSuchFieldException, IllegalAccessException {
    if (MTConfig.getInstance().isMaterialTheme()) {
      StaticPatcher.setFinalStatic(UIUtil.class, "DECORATED_ROW_BG_COLOR", UIManager.get("Table.stripeColor"));
      StaticPatcher.setFinalStatic(UIUtil.class, "UNFOCUSED_SELECTION_COLOR", UIManager.get("Table.stripeColor"));
    }
  }

  static void patchGrays() throws NoSuchFieldException, IllegalAccessException {
    if (MTConfig.getInstance().isMaterialTheme()) {
      // Replace Gray with a clear and transparent color
      final Gray gray = Gray._85;
      final Color alphaGray = gray.withAlpha(1);
      StaticPatcher.setFinalStatic(Gray.class, "_85", alphaGray);
      StaticPatcher.setFinalStatic(Gray.class, "_40", alphaGray);
      StaticPatcher.setFinalStatic(Gray.class, "_145", alphaGray);
      StaticPatcher.setFinalStatic(Gray.class, "_201", alphaGray);

      // Quick info border
      StaticPatcher.setFinalStatic(Gray.class, "_90", gray.withAlpha(25));

      // tool window color
      final boolean dark = MTConfig.getInstance().getSelectedTheme().isDark();
      StaticPatcher.setFinalStatic(Gray.class, "_15", dark ? Gray._15.withAlpha(255) : Gray._200.withAlpha(15));
    }
  }

  /**
   * Theme the memory indicator
   */
  static void patchMemoryIndicator() throws NoSuchFieldException, IllegalAccessException {
    if (MTConfig.getInstance().isMaterialTheme()) {
      final Object usedColor = UIManager.getColor("MemoryIndicator.usedColor");
      final Object unusedColor = UIManager.getColor("MemoryIndicator.unusedColor");
      if (usedColor == null || unusedColor == null) {
        return;
      }

      StaticPatcher.setFinalStatic(MemoryUsagePanel.class, "USED_COLOR", usedColor);
      StaticPatcher.setFinalStatic(MemoryUsagePanel.class, "UNUSED_COLOR", unusedColor);

      final Field[] fields = MemoryUsagePanel.class.getDeclaredFields();
      final Object[] objects = Arrays.stream(fields)
          .filter(field -> field.getType().equals(Color.class))
          .toArray();
      StaticPatcher.setFinalStatic((Field) objects[0], usedColor);
      StaticPatcher.setFinalStatic((Field) objects[1], unusedColor);
    }
  }

  /**
   * Patch the autocomplete color with the accent color
   */
  @Deprecated
  static void patchAutocomplete() throws NoSuchFieldException, IllegalAccessException {
    if (!MTConfig.getInstance().isMaterialTheme()) {
      return;
    }
    final String accentColor = MTConfig.getInstance().getAccentColor();
    final JBColor jbAccentColor = new JBColor(ColorUtil.fromHex(accentColor), ColorUtil.fromHex(accentColor));

    final Color defaultValue = UIUtil.getListSelectionBackground();
    final Color backgroundSelectedColor = ObjectUtils.notNull(UIManager.getColor("Autocomplete.selectionBackground"), defaultValue);
    final Color backgroundUnfocusedColor = ObjectUtils.notNull(UIManager.getColor("Autocomplete.selectionUnfocus"), defaultValue);

    final Color secondTextColor = ObjectUtils.notNull(UIManager.getColor("Menu.acceleratorForeground"), defaultValue);

    final Field[] fields = LookupCellRenderer.class.getDeclaredFields();
    final Object[] objects = Arrays.stream(fields)
        .filter(field -> field.getType().equals(Color.class))
        .toArray();

    StaticPatcher.setFinalStatic((Field) objects[2], secondTextColor);
    // SELECTED BACKGROUND COLOR
    StaticPatcher.setFinalStatic((Field) objects[3], backgroundSelectedColor);
    // SELECTED NON FOCUSED BACKGROUND COLOR
    StaticPatcher.setFinalStatic((Field) objects[4], backgroundUnfocusedColor);

    // Completion foreground color
    StaticPatcher.setFinalStatic((Field) objects[7], jbAccentColor);
    // Selected completion foregronud color
    StaticPatcher.setFinalStatic((Field) objects[8], jbAccentColor);
  }

  /**
   * Patch dialog headers
   */
  static void patchDialogs() throws NoSuchFieldException, IllegalAccessException {
    if (!MTConfig.getInstance().isMaterialTheme()) {
      return;
    }

    Color color = UIManager.getColor("Dialog.titleColor");
    if (color == null) {
      color = Gray._55;
    }

    StaticPatcher.setFinalStatic(CaptionPanel.class, "CNT_ACTIVE_BORDER_COLOR", new JBColor(color, color));
    StaticPatcher.setFinalStatic(CaptionPanel.class, "BND_ACTIVE_COLOR", new JBColor(color, color));
    StaticPatcher.setFinalStatic(CaptionPanel.class, "CNT_ACTIVE_COLOR", new JBColor(color, color));
  }

  /**
   * Theme up tags and lines of the VCS log
   *
   * @deprecated Remove in 2019.1
   */
  @Deprecated
  public static void patchVCS() throws NoSuchFieldException, IllegalAccessException {
    if (MTConfig.getInstance().isMaterialTheme()) {
      final Color color = ObjectUtils.notNull(UIManager.getColor("material.mergeCommits"), new ColorUIResource(0x00000000));
      final Color commitsColor = new JBColor(color, color);

      final Field[] fields = CurrentBranchHighlighter.class.getDeclaredFields();
      final Object[] objects = Arrays.stream(fields)
          .filter(field -> field.getType().equals(JBColor.class))
          .toArray();

      StaticPatcher.setFinalStatic((Field) objects[0], commitsColor);

      final Field[] fields2 = MergeCommitsHighlighter.class.getDeclaredFields();
      final Object[] objects2 = Arrays.stream(fields2)
          .filter(field -> field.getType().equals(JBColor.class))
          .toArray();

      final Color accentColor = ColorUtil.fromHex(MTConfig.getInstance().getAccentColor());
      final Color mergeCommitsColor = new JBColor(accentColor, accentColor);
      StaticPatcher.setFinalStatic((Field) objects2[0], mergeCommitsColor);

      final Color branchColor = ObjectUtils.notNull(UIManager.getColor("material.branchColor"), new ColorUIResource(0x9f79b5));
      final Color tagColor = ObjectUtils.notNull(UIManager.getColor("material.tagColor"), new ColorUIResource(0x7a7a7a));

      StaticPatcher.setFinalStatic(VcsLogStandardColors.Refs.class, "BRANCH", accentColor);
      StaticPatcher.setFinalStatic(VcsLogStandardColors.Refs.class, "BRANCH_REF", branchColor);
      StaticPatcher.setFinalStatic(VcsLogStandardColors.Refs.class, "TAG", tagColor);
    }
  }

  /**
   * Set active settings page to accent color
   */
  public static void patchSettings() throws NoSuchFieldException, IllegalAccessException {
    if (!MTConfig.getInstance().isMaterialTheme()) {
      return;
    }
    final Color accentColor = ColorUtil.fromHex(MTConfig.getInstance().getAccentColor());

    final Field[] fields = SettingsTreeView.class.getDeclaredFields();
    final Object[] objects = Arrays.stream(fields)
        .filter(field -> field.getType().equals(Color.class))
        .toArray();

    StaticPatcher.setFinalStatic((Field) objects[1], accentColor);
  }

  /**
   * Very clever way to theme excluded files color
   */
  public static void patchScopes() throws NoSuchFieldException, IllegalAccessException {
    if (!MTConfig.getInstance().isMaterialTheme()) {
      return;
    }

    final Color disabledColor = MTConfig.getInstance().getSelectedTheme().getTheme().getExcludedColor();

    final Map<String, Color> ourDefaultColors = ContainerUtil.<String, Color>immutableMapBuilder()
        .put("Blue", new JBColor(new Color(0x82AAFF), new Color(0x2E425F))) //NON-NLS
        .put("Green", new JBColor(new Color(0xC3E88D), new Color(0x4B602F)))//NON-NLS
        .put("Orange", new JBColor(new Color(0xF78C6C), new Color(0x904028)))//NON-NLS
        .put("Rose", new JBColor(new Color(0xFF5370), new Color(0x5F1818)))//NON-NLS
        .put("Violet", new JBColor(new Color(0xC792EA), new Color(0x2F235F)))//NON-NLS
        .put("Yellow", new JBColor(new Color(0xFFCB6B), new Color(0x885522)))//NON-NLS
        .put("Theme", disabledColor)//NON-NLS
        .build();

    final Field[] fields = FileColorManagerImpl.class.getDeclaredFields();
    final Object[] objects = Arrays.stream(fields)
        .filter(field -> field.getType().equals(Map.class))
        .toArray();

    StaticPatcher.setFinalStatic((Field) objects[0], ourDefaultColors);
  }

  /**
   * Replace NavBar with MTNavBar
   */
  public static void patchNavBar() throws NoSuchFieldException, IllegalAccessException {
    if (MTConfig.getInstance().isMaterialDesign()) {
      StaticPatcher.setFinalStatic(NavBarUIManager.class, "DARCULA", new MTNavBarUI());
      StaticPatcher.setFinalStatic(NavBarUIManager.class, "COMMON", new MTNavBarUI());
    }
  }

  /**
   * Replace IdeaActionButton with MTIdeaActionButton
   */
  public static void patchIdeaActionButton() throws NoSuchFieldException, IllegalAccessException {
    if (MTConfig.getInstance().isMaterialDesign()) {
      StaticPatcher.setFinalStatic(ActionButtonLook.class, "SYSTEM_LOOK", new MTActionButtonLook());
    }
  }

  /**
   * Patch some colors about the plugin page
   */
  @Deprecated
  public static void patchPluginPage() throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException {
    if (!MTConfig.getInstance().isMaterialTheme()) {
      return;
    }

    StaticPatcher.setFinalStatic(PluginManagerConfigurableNew.class, "MAIN_BG_COLOR", UIUtil.getPanelBackground());

    final Class<?> cellPluginComponentCls = Class.forName("com.intellij.ide.plugins.newui.CellPluginComponent");
    StaticPatcher.setFinalStatic(cellPluginComponentCls, "HOVER_COLOR", UIUtil.getTableSelectionBackground());
    StaticPatcher.setFinalStatic(cellPluginComponentCls, "GRAY_COLOR", UIUtil.getLabelForeground());
  }

  /**
   * New implementation for tabs height
   */
  public static void patchTabs() throws NoSuchFieldException, IllegalAccessException {
    final int baseHeight = JBUI.scale(6);
    final int tabsHeight = MTConfig.getInstance().getTabsHeight() / 2 - baseHeight;
    StaticPatcher.setFinalStatic(TabsUtil.class, "TAB_VERTICAL_PADDING", new JBValue.Float(tabsHeight));
  }
}

