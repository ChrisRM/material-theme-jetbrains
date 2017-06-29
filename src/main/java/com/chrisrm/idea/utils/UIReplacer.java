package com.chrisrm.idea.utils;

import com.chrisrm.idea.MTConfig;
import com.google.common.collect.ImmutableMap;
import com.intellij.codeInsight.hint.ParameterInfoComponent;
import com.intellij.codeInsight.lookup.impl.LookupCellRenderer;
import com.intellij.lang.parameterInfo.ParameterInfoUIContextEx;
import com.intellij.notification.impl.NotificationsManagerImpl;
import com.intellij.openapi.wm.impl.status.MemoryUsagePanel;
import com.intellij.ui.ColorUtil;
import com.intellij.ui.Gray;
import com.intellij.ui.JBColor;
import com.intellij.ui.tabs.TabsUtil;
import com.intellij.util.ui.UIUtil;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;

public class UIReplacer {
  public static void patchUI() {
    try {
      Patcher.patchTabs();
      Patcher.patchTables();
      Patcher.patchStatusBar();
      Patcher.patchPanels();
      Patcher.patchMemoryIndicator();
      Patcher.patchQuickInfo();
      Patcher.patchAutocomplete();
      Patcher.patchNotifications();
      Patcher.patchScrollbars();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  static class Patcher {
    static void patchTables() throws Exception {
      StaticPatcher.setFinalStatic(UIUtil.class, "DECORATED_ROW_BG_COLOR", UIManager.get("Table.stripedBackground"));
    }

    static void patchStatusBar() throws Exception {
      // Replace Gray with a clear and transparent color
      Gray gray = Gray._85;
      Color alphaGray = gray.withAlpha(1);
      StaticPatcher.setFinalStatic(Gray.class, "_85", alphaGray);
      StaticPatcher.setFinalStatic(Gray.class, "_145", alphaGray);
      StaticPatcher.setFinalStatic(Gray.class, "_255", alphaGray);

      // Quick info border
      StaticPatcher.setFinalStatic(Gray.class, "_90", gray.withAlpha(25));

      // This thing doesnt work on compiled jars...
      Class<?> clazz = Class.forName("com.intellij.openapi.wm.impl.status.StatusBarUI$BackgroundPainter");

      StaticPatcher.setFinalStatic(clazz, "BORDER_TOP_COLOR", UIManager.getColor("StatusBar.topColor").brighter().brighter());
      StaticPatcher.setFinalStatic(clazz, "BORDER2_TOP_COLOR", UIManager.getColor("StatusBar.topColor2"));
      StaticPatcher.setFinalStatic(clazz, "BORDER_BOTTOM_COLOR", UIManager.getColor("StatusBar.bottomColor"));
    }

    static void patchPanels() throws Exception {
      Object color = UIManager.getColor("Panel.background");
      StaticPatcher.setFinalStatic(UIUtil.class, "CONTRAST_BORDER_COLOR", color);
      StaticPatcher.setFinalStatic(UIUtil.class, "BORDER_COLOR", color);
      StaticPatcher.setFinalStatic(UIUtil.class, "AQUA_SEPARATOR_FOREGROUND_COLOR", color);
    }

    static void patchMemoryIndicator() throws Exception {
      Object usedColor = UIManager.getColor("MemoryIndicator.usedColor");
      Object unusedColor = UIManager.getColor("MemoryIndicator.unusedColor");
      if (usedColor == null || unusedColor == null) {
        return;
      }

      StaticPatcher.setFinalStatic(MemoryUsagePanel.class, "USED_COLOR", usedColor);
      StaticPatcher.setFinalStatic(MemoryUsagePanel.class, "UNUSED_COLOR", unusedColor);

      Field[] fields = MemoryUsagePanel.class.getDeclaredFields();
      Object[] objects = Arrays.stream(fields)
          .filter(f -> f.getType().equals(Color.class))
          .toArray();
      StaticPatcher.setFinalStatic((Field) objects[0], usedColor);
      StaticPatcher.setFinalStatic((Field) objects[1], unusedColor);
    }

    static void patchQuickInfo() throws Exception {
      String accentColor = MTConfig.getInstance().getAccentColor();

      Field[] fields = ParameterInfoComponent.class.getDeclaredFields();
      Object[] objects = Arrays.stream(fields)
          .filter(f -> f.getType().equals(Map.class))
          .toArray();

      StaticPatcher.setFinalStatic((Field) objects[0], ImmutableMap.of(
          ParameterInfoUIContextEx.Flag.HIGHLIGHT, "b color=" + accentColor,
          ParameterInfoUIContextEx.Flag.DISABLE, "font color=gray",
          ParameterInfoUIContextEx.Flag.STRIKEOUT, "strike"));
    }

    static void patchAutocomplete() throws Exception {
      String accentColor = MTConfig.getInstance().getAccentColor();
      JBColor jbAccentColor = new JBColor(ColorUtil.fromHex(accentColor), ColorUtil.fromHex(accentColor));

      Color backgroundSelectedColor = UIManager.getColor("Menu.selectionBackground");

      Field[] fields = LookupCellRenderer.class.getDeclaredFields();
      Object[] objects = Arrays.stream(fields)
          .filter(f -> f.getType().equals(Color.class))
          .toArray();

      StaticPatcher.setFinalStatic((Field) objects[3], backgroundSelectedColor);
      StaticPatcher.setFinalStatic((Field) objects[4], backgroundSelectedColor);

      StaticPatcher.setFinalStatic((Field) objects[7], jbAccentColor);
      StaticPatcher.setFinalStatic((Field) objects[8], jbAccentColor);
    }

    static void patchNotifications() throws Exception {
      Color notifBg = UIManager.getColor("Notifications.background");
      Color notifBorder = UIManager.getColor("Notifications.borderColor");
      if (notifBg == null || notifBorder == null) {
        return;
      }

      Color bgColor = new JBColor(notifBg, notifBg);
      Color borderColor = new JBColor(notifBorder, notifBorder);

      StaticPatcher.setFinalStatic(NotificationsManagerImpl.class, "FILL_COLOR", bgColor);
      StaticPatcher.setFinalStatic(NotificationsManagerImpl.class, "BORDER_COLOR", borderColor);
    }

    static void patchTabs() throws Exception {
      StaticPatcher.setFinalStatic(TabsUtil.class, "TAB_VERTICAL_PADDING", 8);
      StaticPatcher.setFinalStatic(TabsUtil.class, "TABS_BORDER", 2);

      StaticPatcher.setFinalStatic(TabsUtil.class, "ACTIVE_TAB_UNDERLINE_HEIGHT", 8);
    }

    static void patchScrollbars() throws Exception {
      boolean isThemedScrollbars = MTConfig.getInstance().isThemedScrollbars();
      if (!isThemedScrollbars) {
        return;
      }

      Class<?> scrollPainterClass = Class.forName("com.intellij.ui.components.ScrollPainter");
      StaticPatcher.setFinalStatic(scrollPainterClass, "xA6", UIManager.getColor("MenuItem.selectionBackground"));

      // Set transparency in windows and linux
      Gray gray = Gray.xA6;
      Color alphaGray = gray.withAlpha(60);
      StaticPatcher.setFinalStatic(Gray.class, "xA6", alphaGray);
    }
  }
}
