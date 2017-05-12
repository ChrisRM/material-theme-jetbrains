package com.chrisrm.idea.actions;

import com.chrisrm.idea.MTTheme;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;


public class MTDefaultTheme extends AnAction {
    public static String BACKGROUND = "263238"; // 38, 50, 56
    public static String FOREGROUND = "B0BEC5"; // 176, 190, 197
    public static String CARET = "FFCC00"; // 255, 204, 0
    public static String BORDER = "222D33"; // 34, 45, 51
    public static String TEXT = "607D8B"; // 96, 125, 139
    public static String SELECTION_BACKGROUND = "546E7A"; // 84, 110, 122
    public static String SELECTION_FOREGROUND = "FFFFFF";
    public static String LABEL = "B0BEC5"; // 176, 190, 197
    public static String SUB_LABEL = "546E7A"; // 84, 110, 122
    public static String DISABLED = "415967"; // 65, 89, 103

    public static String SIDEBAR_HEADING = "CFD8DC"; // 207, 216, 220
    public static String STATUS_LABEL = "78909C"; // 120, 144, 156
    public static String INPUT_BORDER = "37474F"; //55, 71, 79

    public static String BUTTON_BACKGROUND = "2C3C41"; // 44, 60, 65
    public static String BUTTON_FOREGROUND = "607D8B";// 96, 125, 139
    public static String BUTTON_SELECTED = "314549"; // 49, 69, 73

    public static String ACCENT_COLOR = "80CBC4"; // 128, 203, 196

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        MTTheme.DEFAULT.activate();
    }
}
