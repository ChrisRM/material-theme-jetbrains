package com.chrisrm.idea.utils;

import com.intellij.ui.Gray;
import com.intellij.util.ui.UIUtil;

import javax.swing.*;

public class UIReplacer {
    public static void patchUI() {
        try {
            Patcher.patchTables();
            Patcher.patchStatusBar();
            Patcher.patchPanels();
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
            StaticPatcher.setFinalStatic(Gray.class, "_85", gray.withAlpha(1));
            StaticPatcher.setFinalStatic(Gray.class, "_145", gray.withAlpha(1));
            StaticPatcher.setFinalStatic(Gray.class, "_255", gray.withAlpha(1));

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
    }


}
