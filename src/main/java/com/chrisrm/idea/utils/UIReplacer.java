package com.chrisrm.idea.utils;

import com.chrisrm.idea.MTConfig;
import com.google.common.collect.ImmutableMap;
import com.intellij.codeInsight.hint.ParameterInfoComponent;
import com.intellij.codeInsight.lookup.impl.LookupCellRenderer;
import com.intellij.lang.parameterInfo.ParameterInfoUIContextEx;
import com.intellij.openapi.wm.impl.status.MemoryUsagePanel;
import com.intellij.ui.ColorUtil;
import com.intellij.ui.Gray;
import com.intellij.ui.JBColor;
import com.intellij.util.ui.UIUtil;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.Arrays;

public class UIReplacer {
  public static void patchUI() {
    try {
      Patcher.patchTables();
      Patcher.patchStatusBar();
      Patcher.patchPanels();
      Patcher.patchMemoryIndicator();
      Patcher.patchQuickInfo();
      Patcher.patchAutocomplete();
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

    public static void patchMemoryIndicator() throws Exception {
      Object usedColor = UIManager.getColor("MemoryIndicator.usedColor");
      Object unusedColor = UIManager.getColor("MemoryIndicator.unusedColor");
      StaticPatcher.setFinalStatic(MemoryUsagePanel.class, "USED_COLOR", usedColor);
      StaticPatcher.setFinalStatic(MemoryUsagePanel.class, "UNUSED_COLOR", unusedColor);

      Field[] fields = MemoryUsagePanel.class.getDeclaredFields();
      Object[] objects = Arrays.stream(fields)
                               .filter(f -> f.getType().equals(Color.class))
                               .toArray();
      StaticPatcher.setFinalStatic((Field) objects[0], usedColor);
      StaticPatcher.setFinalStatic((Field) objects[1], unusedColor);
    }

    public static void patchQuickInfo() throws Exception {
      String accentColor = MTConfig.getInstance().getAccentColor();
      StaticPatcher.setFinalStatic(ParameterInfoComponent.class, "FLAG_TO_TAG", ImmutableMap.of(
          ParameterInfoUIContextEx.Flag.HIGHLIGHT, "b color=" + accentColor,
          ParameterInfoUIContextEx.Flag.DISABLE, "font color=gray",
          ParameterInfoUIContextEx.Flag.STRIKEOUT, "strike"));
    }

    public static void patchAutocomplete() throws Exception {
      String accentColor = MTConfig.getInstance().getAccentColor();
      JBColor jbAccentColor = new JBColor(ColorUtil.fromHex(accentColor), ColorUtil.fromHex(accentColor));

      Color backgroundSelectedColor = UIManager.getColor("Menu.selectionBackground");
      StaticPatcher.setFinalStatic(LookupCellRenderer.class, "SELECTED_BACKGROUND_COLOR", backgroundSelectedColor);

      StaticPatcher.setFinalStatic(LookupCellRenderer.class, "PREFIX_FOREGROUND_COLOR", jbAccentColor);
      StaticPatcher.setFinalStatic(LookupCellRenderer.class, "SELECTED_PREFIX_FOREGROUND_COLOR", jbAccentColor);
    }
  }
}
