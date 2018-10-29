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

package com.chrisrm.idea.utils;

import com.chrisrm.idea.MTConfig;
import com.chrisrm.idea.ui.MTActionButtonLook;
import com.chrisrm.idea.ui.MTNavBarUI;
import com.intellij.codeInsight.lookup.impl.LookupCellRenderer;
import com.intellij.ide.navigationToolbar.ui.NavBarUIManager;
import com.intellij.ide.plugins.PluginManagerConfigurableNew;
import com.intellij.openapi.actionSystem.ex.ActionButtonLook;
import com.intellij.openapi.options.newEditor.SettingsTreeView;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.openapi.util.registry.Registry;
import com.intellij.openapi.wm.impl.status.MemoryUsagePanel;
import com.intellij.ui.CaptionPanel;
import com.intellij.ui.ColorUtil;
import com.intellij.ui.Gray;
import com.intellij.ui.JBColor;
import com.intellij.ui.paint.RectanglePainter;
import com.intellij.ui.tabs.FileColorManagerImpl;
import com.intellij.ui.tabs.TabsUtil;
import com.intellij.util.ObjectUtils;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.ui.JBValue;
import com.intellij.util.ui.RegionPainter;
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

public final class UIReplacer {

  private UIReplacer() {
  }

  public static void patchUI() {
    try {
      Patcher.patchTabs();
      Patcher.patchTables();
      Patcher.patchGrays();
      Patcher.patchMemoryIndicator();
      Patcher.patchAutocomplete();
      Patcher.patchScrollbars();
      Patcher.patchDialogs();
      Patcher.patchVCS();
      Patcher.patchSettings();
      Patcher.patchScopes();
      Patcher.patchNavBar();
      Patcher.patchIdeaActionButton();
      Patcher.patchPluginPage();
    } catch (final Exception e) {
      e.printStackTrace();
    }
  }

  static final class Patcher {
    /**
     * Set the color of even rows in tables
     */
    static void patchTables() throws Exception {
      if (MTConfig.getInstance().isMaterialTheme()) {
        StaticPatcher.setFinalStatic(UIUtil.class, "DECORATED_ROW_BG_COLOR", UIManager.get("Table.stripeColor"));
        StaticPatcher.setFinalStatic(UIUtil.class, "UNFOCUSED_SELECTION_COLOR", UIManager.get("Table.stripeColor"));
      }
    }

    static void patchGrays() throws Exception {
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
        final boolean dark = MTConfig.getInstance().getSelectedTheme().getThemeIsDark();
        StaticPatcher.setFinalStatic(Gray.class, "_15", dark ? Gray._15.withAlpha(255) : Gray._200.withAlpha(15));
      }
    }

    /**
     * Theme the memory indicator
     */
    static void patchMemoryIndicator() throws Exception {
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
            .filter(f -> f.getType().equals(Color.class))
            .toArray();
        StaticPatcher.setFinalStatic((Field) objects[0], usedColor);
        StaticPatcher.setFinalStatic((Field) objects[1], unusedColor);
      }
    }

    /**
     * Patch the autocomplete color with the accent color
     */
    static void patchAutocomplete() throws Exception {
      if (!MTConfig.getInstance().isMaterialTheme()) {
        return;
      }
      final String accentColor = MTConfig.getInstance().getAccentColor();
      final JBColor jbAccentColor = new JBColor(ColorUtil.fromHex(accentColor), ColorUtil.fromHex(accentColor));

      final Color defaultValue = UIUtil.getListSelectionBackground();
      final Color backgroundSelectedColor = ObjectUtils.notNull(UIManager.getColor("Autocomplete.selectionBackground"), defaultValue);
      final Color backgroundUnfocusedSelectedColor = ObjectUtils.notNull(UIManager.getColor("Autocomplete.selectionUnfocus"), defaultValue);

      final Color secondTextColor = ObjectUtils.notNull(UIManager.getColor("Menu.acceleratorForeground"), defaultValue);

      final Field[] fields = LookupCellRenderer.class.getDeclaredFields();
      final Object[] objects = Arrays.stream(fields)
          .filter(f -> f.getType().equals(Color.class))
          .toArray();

      StaticPatcher.setFinalStatic((Field) objects[2], secondTextColor);
      // SELECTED BACKGROUND COLOR
      StaticPatcher.setFinalStatic((Field) objects[3], backgroundSelectedColor);
      // SELECTED NON FOCUSED BACKGROUND COLOR
      StaticPatcher.setFinalStatic((Field) objects[4], backgroundUnfocusedSelectedColor);

      // Completion foreground color
      StaticPatcher.setFinalStatic((Field) objects[7], jbAccentColor);
      // Selected completion foregronud color
      StaticPatcher.setFinalStatic((Field) objects[8], jbAccentColor);
    }

    /**
     * Patch dialog headers
     */
    private static void patchDialogs() throws Exception {
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
     * Theme scrollbars
     */
    static void patchScrollbars() throws Exception {
      final boolean isTransparentScrollbars = MTConfig.getInstance().isThemedScrollbars();
      final boolean accentScrollbars = MTConfig.getInstance().isAccentScrollbars();
      final Class<?> scrollPainterClass = Class.forName("com.intellij.ui.components.ScrollPainter");

      if (isTransparentScrollbars) {
        final Color transparentColor = UIManager.getColor("ScrollBar.thumb");

        StaticPatcher.setFinalStatic(scrollPainterClass, "x0D", transparentColor);
        StaticPatcher.setFinalStatic(scrollPainterClass, "xA6", transparentColor);

        // Set transparency in windows and linux
        final Gray gray = Gray.xA6;
        final Color alphaGray = gray.withAlpha(60);
        StaticPatcher.setFinalStatic(Gray.class, "xA6", alphaGray);
        StaticPatcher.setFinalStatic(Gray.class, "x00", alphaGray);

        // Transparency in mac
        StaticPatcher.setFinalStatic(Gray.class, "x80", alphaGray);
        StaticPatcher.setFinalStatic(Gray.class, "x26", alphaGray);

        // only work from 2018.1
        if (SystemInfo.isMac) {
          // Control the base opacity and the delta opacity
          Registry.get("mac.editor.thumb.default.alpha.base").setValue(0);
          Registry.get("mac.editor.thumb.default.alpha.delta").setValue(102);
          Registry.get("mac.editor.thumb.darcula.alpha.base").setValue(0);
          Registry.get("mac.editor.thumb.darcula.alpha.delta").setValue(102);

          // control the difference between active and idle
          Registry.get("mac.editor.thumb.default.fill.min").setValue(102);
          Registry.get("mac.editor.thumb.default.fill.max").setValue(150);
          Registry.get("mac.editor.thumb.darcula.fill.min").setValue(102);
          Registry.get("mac.editor.thumb.darcula.fill.max").setValue(163);
        } else {
          Registry.get("win.editor.thumb.default.alpha.base").setValue(0);
          Registry.get("win.editor.thumb.default.alpha.delta").setValue(102);
          Registry.get("win.editor.thumb.darcula.alpha.base").setValue(0);
          Registry.get("win.editor.thumb.darcula.alpha.delta").setValue(102);

          Registry.get("win.editor.thumb.default.fill.min").setValue(102);
          Registry.get("win.editor.thumb.default.fill.max").setValue(150);
          Registry.get("win.editor.thumb.darcula.fill.min").setValue(102);
          Registry.get("win.editor.thumb.darcula.fill.max").setValue(150);
        }
      } else {
        // only work from 2018.1
        if (SystemInfo.isMac) {
          Registry.get("mac.editor.thumb.default.alpha.base").setValue(102);
          Registry.get("mac.editor.thumb.default.alpha.delta").setValue(120);
          Registry.get("mac.editor.thumb.darcula.alpha.base").setValue(128);
          Registry.get("mac.editor.thumb.darcula.alpha.delta").setValue(127);

          Registry.get("mac.editor.thumb.default.fill.min").setValue(90);
          Registry.get("mac.editor.thumb.default.fill.max").setValue(50);
          Registry.get("mac.editor.thumb.darcula.fill.min").setValue(133);
          Registry.get("mac.editor.thumb.darcula.fill.max").setValue(150);
        } else {
          Registry.get("win.editor.thumb.default.alpha.base").setValue(120);
          Registry.get("win.editor.thumb.default.alpha.delta").setValue(135);
          Registry.get("win.editor.thumb.darcula.alpha.base").setValue(128);
          Registry.get("win.editor.thumb.darcula.alpha.delta").setValue(127);

          Registry.get("win.editor.thumb.default.fill.min").setValue(193);
          Registry.get("win.editor.thumb.default.fill.max").setValue(163);
          Registry.get("win.editor.thumb.darcula.fill.min").setValue(133);
          Registry.get("win.editor.thumb.darcula.fill.max").setValue(150);
        }
      }

      final Color accent;
      if (accentScrollbars) {
        accent = ColorUtil.fromHex(MTConfig.getInstance().getAccentColor());
      } else {
        accent = Gray.xA6;
      }

      final MyScrollPainter myScrollPainter = new MyScrollPainter(2, .28f, .27f, accent, accent);
      final Class<?> scrollPainterClass1 = Class.forName("com.intellij.ui.components.ScrollPainter$Thumb");
      final Class<?> scrollPainterClass2 = Class.forName("com.intellij.ui.components.ScrollPainter$EditorThumb");
      final Class<?> scrollPainterClass3 = Class.forName("com.intellij.ui.components.ScrollPainter$EditorThumb$Mac");

      StaticPatcher.setFinalStatic(scrollPainterClass, "x0D", accent);
      StaticPatcher.setFinalStatic(scrollPainterClass, "xA6", accent);

      StaticPatcher.setFinalStatic(scrollPainterClass1, "DARCULA", myScrollPainter);
      StaticPatcher.setFinalStatic(scrollPainterClass1, "DEFAULT", myScrollPainter);

      StaticPatcher.setFinalStatic(scrollPainterClass2, "DARCULA", myScrollPainter);
      StaticPatcher.setFinalStatic(scrollPainterClass2, "DEFAULT", myScrollPainter);

      StaticPatcher.setFinalStatic(scrollPainterClass3, "DARCULA", myScrollPainter);
      StaticPatcher.setFinalStatic(scrollPainterClass3, "DEFAULT", myScrollPainter);
    }

    /**
     * Theme up tags and lines of the VCS log
     */
    public static void patchVCS() throws Exception {
      if (MTConfig.getInstance().isMaterialTheme()) {
        final Color color = ObjectUtils.notNull(UIManager.getColor("material.mergeCommits"), new ColorUIResource(0x00000000));
        final Color commitsColor = new JBColor(color, color);

        final Field[] fields = CurrentBranchHighlighter.class.getDeclaredFields();
        final Object[] objects = Arrays.stream(fields)
            .filter(f -> f.getType().equals(JBColor.class))
            .toArray();

        StaticPatcher.setFinalStatic((Field) objects[0], commitsColor);

        final Field[] fields2 = MergeCommitsHighlighter.class.getDeclaredFields();
        final Object[] objects2 = Arrays.stream(fields2)
            .filter(f -> f.getType().equals(JBColor.class))
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
    public static void patchSettings() throws Exception {
      if (!MTConfig.getInstance().isMaterialTheme()) {
        return;
      }
      final Color accentColor = ColorUtil.fromHex(MTConfig.getInstance().getAccentColor());

      final Field[] fields = SettingsTreeView.class.getDeclaredFields();
      final Object[] objects = Arrays.stream(fields)
          .filter(f -> f.getType().equals(Color.class))
          .toArray();

      StaticPatcher.setFinalStatic((Field) objects[1], accentColor);
    }

    /**
     * Very clever way to theme excluded files color
     */
    public static void patchScopes() throws Exception {
      if (!MTConfig.getInstance().isMaterialTheme()) {
        return;
      }

      final String disabled = MTConfig.getInstance().getSelectedTheme().getTheme().getExcludedColor();
      final JBColor disabledColor = new JBColor(ColorUtil.fromHex(disabled), ColorUtil.fromHex(disabled));

      final Map<String, Color> ourDefaultColors = ContainerUtil.<String, Color>immutableMapBuilder()
          .put("Blue", new JBColor(new Color(0x82AAFF), new Color(0x2E425F)))
          .put("Green", new JBColor(new Color(0xC3E88D), new Color(0x4B602F)))
          .put("Orange", new JBColor(new Color(0xF78C6C), new Color(0x904028)))
          .put("Rose", new JBColor(new Color(0xFF5370), new Color(0x5F1818)))
          .put("Violet", new JBColor(new Color(0xC792EA), new Color(0x2F235F)))
          .put("Yellow", new JBColor(new Color(0xFFCB6B), new Color(0x885522)))
          .put("Theme", disabledColor)
          .build();

      final Field[] fields = FileColorManagerImpl.class.getDeclaredFields();
      final Object[] objects = Arrays.stream(fields)
          .filter(f -> f.getType().equals(Map.class))
          .toArray();

      StaticPatcher.setFinalStatic((Field) objects[0], ourDefaultColors);
    }

    /**
     * Replace NavBar with MTNavBar
     */
    public static void patchNavBar() throws Exception {
      if (MTConfig.getInstance().getIsMaterialDesign()) {
        StaticPatcher.setFinalStatic(NavBarUIManager.class, "DARCULA", new MTNavBarUI());
        StaticPatcher.setFinalStatic(NavBarUIManager.class, "COMMON", new MTNavBarUI());
      }
    }

    /**
     * Replace IdeaActionButton with MTIdeaActionButton
     */
    public static void patchIdeaActionButton() throws Exception {
      if (MTConfig.getInstance().getIsMaterialDesign()) {
        StaticPatcher.setFinalStatic(ActionButtonLook.class, "SYSTEM_LOOK", new MTActionButtonLook());
      }
    }

    /**
     * Patch some colors about the plugin page
     */
    public static void patchPluginPage() throws Exception {
      if (!MTConfig.getInstance().isMaterialTheme()) {
        return;
      }

      StaticPatcher.setFinalStatic(PluginManagerConfigurableNew.class, "MAIN_BG_COLOR", UIUtil.getPanelBackground());

      final Class<?> CellPluginComponentCls = Class.forName("com.intellij.ide.plugins.newui.CellPluginComponent");
      StaticPatcher.setFinalStatic(CellPluginComponentCls, "HOVER_COLOR", UIUtil.getTableSelectionBackground());
      StaticPatcher.setFinalStatic(CellPluginComponentCls, "GRAY_COLOR", UIUtil.getLabelForeground());
    }

    /**
     * New implementation for tabs height
     */
    public static void patchTabs() throws Exception {
      final int tabsHeight = MTConfig.getInstance().getTabsHeight() / 2;
      StaticPatcher.setFinalStatic(TabsUtil.class, "TAB_VERTICAL_PADDING", new JBValue.Float(tabsHeight));
    }
  }

  static class MyScrollPainter extends RegionPainter.Alpha {
    private final int myOffset;
    private final float myAlphaBase;
    private final float myAlphaDelta;
    private final Color myFillColor;
    private final Color myDrawColor;

    MyScrollPainter(final int offset, final float base, final float delta, final Color fill, final Color draw) {
      myOffset = offset;
      myAlphaBase = base;
      myAlphaDelta = delta;
      myFillColor = fill;
      myDrawColor = draw;
    }

    @Override
    protected float getAlpha(final Float value) {
      return value != null ? myAlphaBase + myAlphaDelta * value : 0;
    }

    @Override
    protected void paint(final Graphics2D g, final int newX, final int newY, final int newWidth, final int newHeight) {
      int x = newX,
          y = newY,
          width = newWidth,
          height = newHeight;

      if (myOffset > 0) {
        x += myOffset;
        y += myOffset;
        width -= myOffset + myOffset;
        height -= myOffset + myOffset;
      }
      if (width > 0 && height > 0) {
        if (myFillColor != null) {
          g.setColor(myFillColor);
          fill(g, x, y, width, height, myDrawColor != null);
        }
        if (myDrawColor != null) {
          g.setColor(myDrawColor);
          draw(g, x, y, width, height);
        }
      }
    }

    protected void fill(final Graphics2D g, final int x, final int y, final int width, final int height, final boolean border) {
      if (border) {
        g.fillRect(x + 1, y + 1, width - 2, height - 2);
      } else {
        g.fillRect(x, y, width, height);
      }
    }

    protected void draw(final Graphics2D g, final int x, final int y, final int width, final int height) {
      RectanglePainter.DRAW.paint(g, x, y, width, height, Math.min(width, height));
    }
  }
}
