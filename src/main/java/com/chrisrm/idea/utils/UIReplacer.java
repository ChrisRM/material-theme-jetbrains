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
import com.google.common.collect.ImmutableMap;
import com.intellij.codeInsight.hint.ParameterInfoComponent;
import com.intellij.codeInsight.lookup.impl.LookupCellRenderer;
import com.intellij.icons.AllIcons;
import com.intellij.ide.ui.laf.darcula.DarculaUIUtil;
import com.intellij.lang.parameterInfo.ParameterInfoUIContextEx;
import com.intellij.notification.impl.NotificationsManagerImpl;
import com.intellij.openapi.actionSystem.impl.IdeaActionButtonLook;
import com.intellij.openapi.options.newEditor.SettingsTreeView;
import com.intellij.openapi.ui.MessageType;
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
import com.intellij.util.ui.RegionPainter;
import com.intellij.util.ui.UIUtil;
import com.intellij.vcs.log.VcsLogStandardColors;
import com.intellij.vcs.log.ui.highlighters.CurrentBranchHighlighter;
import com.intellij.vcs.log.ui.highlighters.MergeCommitsHighlighter;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;

public final class UIReplacer {

  private UIReplacer() {
  }

  public static void patchUI() {
    try {
      Patcher.patchTables();
      Patcher.patchStatusBar();
      Patcher.patchPanels();
      Patcher.patchMemoryIndicator();
      Patcher.patchQuickInfo();
      Patcher.patchAutocomplete();
      Patcher.patchNotifications();
      Patcher.patchScrollbars();
      Patcher.patchDialogs();
      Patcher.patchVCS();
      Patcher.patchSettings();
      Patcher.patchOtherStuff();
      Patcher.patchScopes();
    } catch (final Exception e) {
      e.printStackTrace();
    }
  }

  static final class Patcher {
    static void patchTables() throws Exception {
      if (MTConfig.getInstance().isMaterialTheme()) {
        StaticPatcher.setFinalStatic(UIUtil.class, "DECORATED_ROW_BG_COLOR", UIManager.get("Table.stripedBackground"));
      }
    }

    static void patchStatusBar() throws Exception {
      if (MTConfig.getInstance().isMaterialTheme()) {
        // Replace Gray with a clear and transparent color
        final Gray gray = Gray._85;
        final Color alphaGray = gray.withAlpha(1);
        StaticPatcher.setFinalStatic(Gray.class, "_85", alphaGray);
        StaticPatcher.setFinalStatic(Gray.class, "_40", alphaGray);
        StaticPatcher.setFinalStatic(Gray.class, "_145", alphaGray);
        //        StaticPatcher.setFinalStatic(Gray.class, "_255", alphaGray);
        StaticPatcher.setFinalStatic(Gray.class, "_201", alphaGray);
        StaticPatcher.setFinalStatic(Gray.class, "x39", gray.withAlpha(25));

        // Quick info border
        StaticPatcher.setFinalStatic(Gray.class, "_90", gray.withAlpha(25));

        // tool window color
        final boolean dark = MTConfig.getInstance().getSelectedTheme().getThemeIsDark();
        StaticPatcher.setFinalStatic(Gray.class, "_15", dark ? Gray._15.withAlpha(255) : Gray._200.withAlpha(15));
        // This thing doesnt work on compiled jars...
        final Class<?> clazz = Class.forName("com.intellij.openapi.wm.impl.status.StatusBarUI$BackgroundPainter");

        StaticPatcher.setFinalStatic(clazz, "BORDER_TOP_COLOR", UIManager.getColor("StatusBar.topColor").brighter().brighter());
        StaticPatcher.setFinalStatic(clazz, "BORDER2_TOP_COLOR", UIManager.getColor("StatusBar.topColor2"));
        StaticPatcher.setFinalStatic(clazz, "BORDER_BOTTOM_COLOR", UIManager.getColor("StatusBar.bottomColor"));
        StaticPatcher.setFinalStatic(SettingsTreeView.class, "FOREGROUND", UIManager.getColor("Tree.foreground"));
      }
    }

    static void patchPanels() throws Exception {
      if (MTConfig.getInstance().isMaterialTheme()) {
        final Color color = UIManager.getColor("Panel.background");
        final Color contrastColor = ObjectUtils.notNull(UIManager.getColor("material.contrast"), Gray._90);

        StaticPatcher.setFinalStatic(UIUtil.class, "CONTRAST_BORDER_COLOR", ColorUtil.withAlpha(color, .05));
        StaticPatcher.setFinalStatic(UIUtil.class, "BORDER_COLOR", color);
        StaticPatcher.setFinalStatic(UIUtil.class, "AQUA_SEPARATOR_FOREGROUND_COLOR", color);

        // Captions
        final Field[] captionFields = CaptionPanel.class.getDeclaredFields();
        final Object[] captionObjects = Arrays.stream(captionFields).filter(f -> f.getType().equals(Color.class)).toArray();
        final Object[] captionObjects2 = Arrays.stream(captionFields).filter(f -> f.getType().equals(JBColor.class)).toArray();

        // CNT_COLOR, BND_COLOR
        StaticPatcher.setFinalStatic((Field) captionObjects[0], contrastColor);
        StaticPatcher.setFinalStatic((Field) captionObjects[1], contrastColor);

        // TOP, BOTTOM FLICK ACTIVE AND PASSIVE
        final JBColor jbColor = new JBColor(color, color);
        StaticPatcher.setFinalStatic((Field) captionObjects2[0], jbColor);
        StaticPatcher.setFinalStatic((Field) captionObjects2[1], jbColor);
        StaticPatcher.setFinalStatic((Field) captionObjects2[2], jbColor);
        StaticPatcher.setFinalStatic((Field) captionObjects2[3], jbColor);

      }

      final Field[] fields = DarculaUIUtil.class.getDeclaredFields();
      final Object[] objects = Arrays.stream(fields)
                                     .filter(f -> f.getType().equals(Color.class))
                                     .toArray();
      final Color accentColor = ColorUtil.toAlpha(ColorUtil.fromHex(MTConfig.getInstance().getAccentColor()), 100);
      final JBColor accentJBColor = new JBColor(accentColor, accentColor);
      // REGULAR/GRAPHITE
      StaticPatcher.setFinalStatic((Field) objects[12], accentJBColor);
      StaticPatcher.setFinalStatic((Field) objects[13], accentJBColor);
      //      StaticPatcher.setFinalStatic((Field) objects[1], accentJBColor);

      // Action button
      final Field[] fields2 = IdeaActionButtonLook.class.getDeclaredFields();
      final Object[] objects2 = Arrays.stream(fields2)
                                      .filter(f -> f.getType().equals(Color.class))
                                      .toArray();

      StaticPatcher.setFinalStatic((Field) objects2[1], accentJBColor);
    }

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

    static void patchQuickInfo() throws Exception {
      final String accentColor = MTConfig.getInstance().getAccentColor();

      final Field[] fields = ParameterInfoComponent.class.getDeclaredFields();
      final Object[] objects = Arrays.stream(fields)
                                     .filter(f -> f.getType().equals(Map.class))
                                     .toArray();

      StaticPatcher.setFinalStatic((Field) objects[0], ImmutableMap.of(
          ParameterInfoUIContextEx.Flag.HIGHLIGHT, "b color=" + accentColor,
          ParameterInfoUIContextEx.Flag.DISABLE, "font color=gray",
          ParameterInfoUIContextEx.Flag.STRIKEOUT, "strike"));
    }

    static void patchAutocomplete() throws Exception {
      final String accentColor = MTConfig.getInstance().getAccentColor();
      final JBColor jbAccentColor = new JBColor(ColorUtil.fromHex(accentColor), ColorUtil.fromHex(accentColor));

      final Color defaultValue = UIUtil.getListSelectionBackground();
      final Color backgroundSelectedColor = ObjectUtils.notNull(UIManager.getColor("Autocomplete.selectionbackground"), defaultValue);
      final Color secondTextColor = ObjectUtils.notNull(UIManager.getColor("Menu.acceleratorForeground"), defaultValue);


      final Field[] fields = LookupCellRenderer.class.getDeclaredFields();
      final Object[] objects = Arrays.stream(fields)
                                     .filter(f -> f.getType().equals(Color.class))
                                     .toArray();

      StaticPatcher.setFinalStatic((Field) objects[2], secondTextColor);
      // SELECTED BACKGROUND COLOR
      StaticPatcher.setFinalStatic((Field) objects[3], backgroundSelectedColor);
      // SELECTED NON FOCUSED BACKGROUND COLOR
      StaticPatcher.setFinalStatic((Field) objects[4], backgroundSelectedColor);

      // Completion foreground color
      StaticPatcher.setFinalStatic((Field) objects[7], jbAccentColor);
      // Selected completion foregronud color
      StaticPatcher.setFinalStatic((Field) objects[8], jbAccentColor);
    }

    static void patchNotifications() throws Exception {
      if (!MTConfig.getInstance().isMaterialTheme()) {
        return;
      }

      final Color notifBg = ObjectUtils.notNull(UIManager.getColor("Notifications.background"), new ColorUIResource(0x323232));
      final Color notifBorder = ObjectUtils.notNull(UIManager.getColor("Notifications.borderColor"), new ColorUIResource(0x323232));

      final Color bgColor = new JBColor(notifBg, notifBg);
      final Color borderColor = new JBColor(notifBorder, notifBorder);

      StaticPatcher.setFinalStatic(NotificationsManagerImpl.class, "FILL_COLOR", bgColor);
      StaticPatcher.setFinalStatic(NotificationsManagerImpl.class, "BORDER_COLOR", borderColor);

      Patcher.replaceToolBalloons();
    }

    private static void replaceToolBalloons() throws Exception {
      if (!MTConfig.getInstance().isMaterialTheme()) {
        return;
      }

      final Constructor<MessageType> declaredConstructor = MessageType.class.getDeclaredConstructor(Icon.class, Color.class, Color.class);
      declaredConstructor.setAccessible(true);
      final Color errorBackground = ObjectUtils.notNull(UIManager.getColor("Notifications.errorBackground"), new ColorUIResource(0x323232));
      final Color warnBackground = ObjectUtils.notNull(UIManager.getColor("Notifications.warnBackground"), new ColorUIResource(0x323232));
      final Color infoBackground = ObjectUtils.notNull(UIManager.getColor("Notifications.infoBackground"), new ColorUIResource(0x323232));

      final JBColor errorBackgroundColor = new JBColor(errorBackground, errorBackground);
      final JBColor warnBackgroundColor = new JBColor(warnBackground, warnBackground);
      final JBColor infoBackgroundColor = new JBColor(infoBackground, infoBackground);

      final MessageType errorType = declaredConstructor.newInstance(
          AllIcons.General.NotificationError,
          errorBackgroundColor,
          errorBackgroundColor);

      final MessageType warnType = declaredConstructor.newInstance(
          AllIcons.General.NotificationWarning,
          warnBackgroundColor,
          warnBackgroundColor);
      final MessageType infoType = declaredConstructor.newInstance(
          AllIcons.General.NotificationInfo,
          infoBackgroundColor,
          infoBackgroundColor);

      StaticPatcher.setFinalStatic(MessageType.class, "ERROR", errorType);
      StaticPatcher.setFinalStatic(MessageType.class, "INFO", infoType);
      StaticPatcher.setFinalStatic(MessageType.class, "WARNING", warnType);
    }

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

    static void patchScrollbars() throws Exception {
      final boolean isTransparentScrollbars = MTConfig.getInstance().isThemedScrollbars();
      final boolean accentScrollbars = MTConfig.getInstance().isAccentScrollbars();
      final Color accent = ColorUtil.fromHex(MTConfig.getInstance().getAccentColor());
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

      if (accentScrollbars) {
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
    }

    public static void patchVCS() throws Exception {
      if (MTConfig.getInstance().isMaterialTheme()) {
        final Color color = ObjectUtils.notNull(UIManager.getColor("material.mergeCommits"), new ColorUIResource(0x00000000));
        final Color commitsColor = new JBColor(color, color);

        final Field[] fields = CurrentBranchHighlighter.class.getDeclaredFields();
        final Object[] objects = Arrays.stream(fields)
                                       .filter(f -> f.getType().equals(JBColor.class))
                                       .toArray();

        StaticPatcher.setFinalStatic((Field) objects[0], commitsColor);
      }

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

    public static void patchSettings() throws Exception {
      final Color accentColor = ColorUtil.fromHex(MTConfig.getInstance().getAccentColor());

      final Field[] fields = SettingsTreeView.class.getDeclaredFields();
      final Object[] objects = Arrays.stream(fields)
                                     .filter(f -> f.getType().equals(Color.class))
                                     .toArray();

      StaticPatcher.setFinalStatic((Field) objects[1], accentColor);
    }

    public static void patchOtherStuff() throws Exception {
      StaticPatcher.setFinalStatic(TabsUtil.class, "ACTIVE_TAB_UNDERLINE_HEIGHT", 0);
    }

    public static void patchScopes() throws Exception {
      final String disabled = MTConfig.getInstance().getSelectedTheme().getTheme().getDisabled();
      final JBColor disabledColor = new JBColor(ColorUtil.fromHex(disabled), ColorUtil.fromHex(disabled));

      final Map<String, Color> ourDefaultColors = ContainerUtil.<String, Color>immutableMapBuilder()
          .put("Blue", new JBColor(new Color(0x82AAFF), new Color(0x6182B8)))
          .put("Green", new JBColor(new Color(0xC3E88D), new Color(0x91B859)))
          .put("Orange", new JBColor(new Color(0xF78C6C), new Color(0xF76D47)))
          .put("Rose", new JBColor(new Color(0xFF5370), new Color(0xE53935)))
          .put("Violet", new JBColor(new Color(0xC792EA), new Color(0x7C4DFF)))
          .put("Yellow", new JBColor(new Color(0xFFCB6B), new Color(0xFFB62C)))
          .put("ffffe4", disabledColor)
          .put("494539", disabledColor)
          .build();

      StaticPatcher.setFinalStatic(FileColorManagerImpl.class, "ourDefaultColors", ourDefaultColors);
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
