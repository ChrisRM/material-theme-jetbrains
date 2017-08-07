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

package com.chrisrm.idea.utils;

import com.chrisrm.idea.MTConfig;
import com.google.common.collect.ImmutableMap;
import com.intellij.codeInsight.hint.ParameterInfoComponent;
import com.intellij.codeInsight.lookup.impl.LookupCellRenderer;
import com.intellij.icons.AllIcons;
import com.intellij.ide.ui.laf.darcula.DarculaUIUtil;
import com.intellij.lang.parameterInfo.ParameterInfoUIContextEx;
import com.intellij.notification.impl.NotificationsManagerImpl;
import com.intellij.openapi.options.newEditor.SettingsTreeView;
import com.intellij.openapi.ui.MessageType;
import com.intellij.openapi.wm.impl.status.MemoryUsagePanel;
import com.intellij.ui.CaptionPanel;
import com.intellij.ui.ColorUtil;
import com.intellij.ui.Gray;
import com.intellij.ui.JBColor;
import com.intellij.util.ObjectUtils;
import com.intellij.util.ui.UIUtil;
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
    } catch (final Exception e) {
      e.printStackTrace();
    }
  }

  static final class Patcher {
    static void patchTables() throws Exception {
      StaticPatcher.setFinalStatic(UIUtil.class, "DECORATED_ROW_BG_COLOR", UIManager.get("Table.stripedBackground"));
    }

    static void patchStatusBar() throws Exception {
      // Replace Gray with a clear and transparent color
      final Gray gray = Gray._85;
      final Color alphaGray = gray.withAlpha(1);
      StaticPatcher.setFinalStatic(Gray.class, "_85", alphaGray);
      StaticPatcher.setFinalStatic(Gray.class, "_40", alphaGray);
      StaticPatcher.setFinalStatic(Gray.class, "_145", alphaGray);
      StaticPatcher.setFinalStatic(Gray.class, "_255", alphaGray);
      StaticPatcher.setFinalStatic(Gray.class, "_201", alphaGray);

      // Quick info border
      StaticPatcher.setFinalStatic(Gray.class, "_90", gray.withAlpha(25));

      // tool window color
      final boolean dark = MTConfig.getInstance().getSelectedTheme().isDark();
      StaticPatcher.setFinalStatic(Gray.class, "_15", dark ? Gray._15.withAlpha(255) : Gray._200.withAlpha(15));
      // This thing doesnt work on compiled jars...
      final Class<?> clazz = Class.forName("com.intellij.openapi.wm.impl.status.StatusBarUI$BackgroundPainter");

      StaticPatcher.setFinalStatic(clazz, "BORDER_TOP_COLOR", UIManager.getColor("StatusBar.topColor").brighter().brighter());
      StaticPatcher.setFinalStatic(clazz, "BORDER2_TOP_COLOR", UIManager.getColor("StatusBar.topColor2"));
      StaticPatcher.setFinalStatic(clazz, "BORDER_BOTTOM_COLOR", UIManager.getColor("StatusBar.bottomColor"));
      StaticPatcher.setFinalStatic(SettingsTreeView.class, "FOREGROUND", UIManager.getColor("Tree.foreground"));

    }

    static void patchPanels() throws Exception {
      final Object color = UIManager.getColor("Panel.background");
      StaticPatcher.setFinalStatic(UIUtil.class, "CONTRAST_BORDER_COLOR", color);
      StaticPatcher.setFinalStatic(UIUtil.class, "BORDER_COLOR", color);
      StaticPatcher.setFinalStatic(UIUtil.class, "AQUA_SEPARATOR_FOREGROUND_COLOR", color);

      final Color accentColor = ColorUtil.fromHex(MTConfig.getInstance().getAccentColor());
      StaticPatcher.setFinalStatic(DarculaUIUtil.class, "g", accentColor);
      StaticPatcher.setFinalStatic(DarculaUIUtil.class, "h", accentColor);
    }

    static void patchMemoryIndicator() throws Exception {
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

      final Color backgroundSelectedColor = UIManager.getColor("Autocomplete.selectionbackground");

      final Field[] fields = LookupCellRenderer.class.getDeclaredFields();
      final Object[] objects = Arrays.stream(fields)
                                     .filter(f -> f.getType().equals(Color.class))
                                     .toArray();

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
      final Color notifBg = UIManager.getColor("Notifications.background");
      final Color notifBorder = UIManager.getColor("Notifications.borderColor");
      if (notifBg == null || notifBorder == null) {
        return;
      }

      final Color bgColor = new JBColor(notifBg, notifBg);
      final Color borderColor = new JBColor(notifBorder, notifBorder);

      StaticPatcher.setFinalStatic(NotificationsManagerImpl.class, "FILL_COLOR", bgColor);
      StaticPatcher.setFinalStatic(NotificationsManagerImpl.class, "BORDER_COLOR", borderColor);

      replaceToolBalloons();
    }

    private static void replaceToolBalloons() throws Exception {
      final Constructor<MessageType> declaredConstructor = MessageType.class.getDeclaredConstructor(Icon.class, Color.class, Color.class);
      declaredConstructor.setAccessible(true);
      final Color errorBackground = UIManager.getColor("Notifications.errorBackground");
      final Color warnBackground = UIManager.getColor("Notifications.warnBackground");
      final Color infoBackground = UIManager.getColor("Notifications.infoBackground");


      final MessageType errorType = declaredConstructor.newInstance(
          AllIcons.General.NotificationError,
          errorBackground,
          errorBackground);

      final MessageType warnType = declaredConstructor.newInstance(
          AllIcons.General.NotificationWarning,
          warnBackground,
          warnBackground);
      final MessageType infoType = declaredConstructor.newInstance(
          AllIcons.General.NotificationInfo,
          infoBackground,
          infoBackground);


      StaticPatcher.setFinalStatic(MessageType.class, "ERROR", errorType);
      StaticPatcher.setFinalStatic(MessageType.class, "INFO", infoType);
      StaticPatcher.setFinalStatic(MessageType.class, "WARNING", warnType);
    }

    private static void patchDialogs() throws Exception {
      Color color = UIManager.getColor("Dialog.titleColor");
      if (color == null) {
        color = Gray._55;
      }

      StaticPatcher.setFinalStatic(CaptionPanel.class, "CNT_ACTIVE_BORDER_COLOR", new JBColor(color, color));
      StaticPatcher.setFinalStatic(CaptionPanel.class, "BND_ACTIVE_COLOR", new JBColor(color, color));
      StaticPatcher.setFinalStatic(CaptionPanel.class, "CNT_ACTIVE_COLOR", new JBColor(color, color));
    }

    static void patchScrollbars() throws Exception {
      final boolean isThemedScrollbars = MTConfig.getInstance().isThemedScrollbars();
      if (!isThemedScrollbars) {
        return;
      }

      final Class<?> scrollPainterClass = Class.forName("com.intellij.ui.components.ScrollPainter");
      StaticPatcher.setFinalStatic(scrollPainterClass, "x0D", UIManager.getColor("ScrollBar.thumb"));
      StaticPatcher.setFinalStatic(scrollPainterClass, "xA6", UIManager.getColor("ScrollBar.thumb"));

      // Set transparency in windows and linux
      final Gray gray = Gray.xA6;
      final Color alphaGray = gray.withAlpha(60);
      StaticPatcher.setFinalStatic(Gray.class, "xA6", alphaGray);
      StaticPatcher.setFinalStatic(Gray.class, "x00", alphaGray);

    }

    public static void patchVCS() throws Exception {
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

      Color accentColor = ColorUtil.fromHex(MTConfig.getInstance().getAccentColor());
      Color mergeCommitsColor = new JBColor(accentColor, accentColor);
      StaticPatcher.setFinalStatic((Field) objects2[0], mergeCommitsColor);

    }
  }

}
