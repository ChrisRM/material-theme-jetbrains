package com.chrisrm.idea.actions;

import com.chrisrm.idea.MTTheme;
import com.chrisrm.idea.themes.MTThemeManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

public class MTDarkerTheme extends AnAction {
  public static String BACKGROUND = "212121"; // 33, 33, 33
  public static String FOREGROUND = "B0BEC5"; // 176, 190, 197
  public static String CARET = "FFCC00"; // 255, 204, 0
  public static String BORDER = "1B1B1B"; // 27, 27, 27
  public static String TEXT = "616161"; // 97, 97, 97
  public static String SELECTION_BACKGROUND = "424242"; // 66, 66, 66
  public static String SELECTION_FOREGROUND = "FFFFFF";
  public static String LABEL = "B0BEC5"; // 176, 190, 197
  public static String SUB_LABEL = "616161"; // 97, 97, 97
  public static String DISABLED = "474747"; // 65, 89, 103

  public static String SIDEBAR_HEADING = "CFD8DC"; // 207, 216, 220
  public static String STATUS_LABEL = "616161"; // 97, 97, 97
  public static String INPUT_BORDER = "484848"; //72, 72, 72

  public static String BUTTON_BACKGROUND = "2B2B2B"; // 43, 43, 43
  public static String BUTTON_FOREGROUND = "616161";// 97, 97, 97
  public static String BUTTON_SELECTED = "383838"; // 56,56,56

  public static String ACCENT_COLOR = "80CBC4"; // 128, 203, 196

  @Override
  public void actionPerformed(AnActionEvent anActionEvent) {
    MTThemeManager.getInstance().activate(MTTheme.DARKER);
    //    MTTheme.DARKER.activate();
  }
}
