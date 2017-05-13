package com.chrisrm.idea.actions;

import com.chrisrm.idea.MTTheme;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

public class MTPalenightTheme extends AnAction {
    public static String BACKGROUND = "292D3E"; // 41, 45, 62
    public static String FOREGROUND = "B0BEC5"; // 176, 190, 197
    public static String CARET = "FFCC00"; // 255, 204, 0
    public static String BORDER = "202226"; // 32, 34, 38
    public static String TEXT = "676E95";  // 103, 110, 149
    public static String SELECTION_BACKGROUND = "676E95"; // 103, 110, 149
    public static String SELECTION_FOREGROUND = "FFFFFF";
    public static String LABEL = "A6ACCD";// 166, 172, 205
    public static String SUB_LABEL = "676E95";  // 103, 110, 149
    public static String DISABLED = "4E5579";

    public static String SIDEBAR_HEADING = "CFD8DC"; // 207, 216, 220
    public static String STATUS_LABEL = "676E95"; // 103, 110, 149
    public static String INPUT_BORDER = "373B4D"; // 55, 59, 77

    public static String BUTTON_BACKGROUND = "2D3144";
    public static String BUTTON_FOREGROUND = "676E95"; // 103, 110, 149
    public static String BUTTON_SELECTED = "32374D"; // 50, 55, 77

    public static String ACCENT_COLOR = "80CBC4"; // 128, 203, 196
    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        MTTheme.PALENIGHT.activate();
    }
}
