package com.chrisrm.idea.utils;

import com.intellij.ui.ColorUtil;
import com.intellij.ui.Gray;
import com.intellij.ui.JBColor;
import com.intellij.util.ui.UIUtil;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

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
            //        StaticPatcher.setFinalStatic(StatusBarUI.);
            Class<?> clazz = Class.forName("com.intellij.openapi.wm.impl.status.StatusBarUI$BackgroundPainter");
            Field border_top_color = clazz.getDeclaredField("BORDER_TOP_COLOR");
            Field border2_top_color = clazz.getDeclaredField("BORDER2_TOP_COLOR");
            Field border_bottom_color = clazz.getDeclaredField("BORDER_BOTTOM_COLOR");

            StaticPatcher.setFinalStatic(border_top_color, UIManager.getColor("StatusBar.topColor").brighter().brighter());
            StaticPatcher.setFinalStatic(border2_top_color, UIManager.getColor("StatusBar.topColor2"));
            StaticPatcher.setFinalStatic(border_bottom_color, UIManager.getColor("StatusBar.bottomColor"));

            Gray gray = Gray._85;
            StaticPatcher.setFinalStatic(Gray.class, "_85", gray.withAlpha(1));
        }

        static void patchPanels() throws Exception {
            Object color = UIManager.getColor("Panel.background");
            StaticPatcher.setFinalStatic(UIUtil.class, "CONTRAST_BORDER_COLOR", color);
            StaticPatcher.setFinalStatic(UIUtil.class, "BORDER_COLOR", color);
            StaticPatcher.setFinalStatic(UIUtil.class, "AQUA_SEPARATOR_FOREGROUND_COLOR", color);
        }
    }


}
