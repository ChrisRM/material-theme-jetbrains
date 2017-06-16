package com.chrisrm.idea.actions;

import com.chrisrm.idea.MTTheme;
import com.chrisrm.idea.themes.MTThemeManager;
import com.intellij.openapi.actionSystem.AnActionEvent;

public class MTLighterTheme extends MTAbstractTheme {
  public static String BACKGROUND = "FAFAFA"; // 250, 250, 250
  public static String FOREGROUND = "A7ADB0"; // 167, 173, 176
  public static String CARET = "FFCC00"; // 255, 204, 0
  public static String BORDER = "E6E6E6"; // 230, 230, 230
  public static String TEXT = "A7ADB0"; // 167, 173, 176
  public static String SELECTION_BACKGROUND = "546E7A"; // 84, 110, 122
  public static String SELECTION_FOREGROUND = "FFFFFF";
  public static String LABEL = "546E7A";// 84, 110, 122
  public static String SUB_LABEL = "B0BEC5"; // 176, 190, 197
  public static String DISABLED = "eae8e8";

  public static String SIDEBAR_HEADING = "CFD8DC"; // 207, 216, 220
  public static String STATUS_LABEL = "90A4AE"; // 144, 164, 174
  public static String INPUT_BORDER = "CFD8DC"; // 207, 216, 220

  public static String BUTTON_BACKGROUND = "EAF3F2"; // 234, 243, 242
  public static String BUTTON_FOREGROUND = "676E95"; // 103, 110, 149
  public static String BUTTON_SELECTED = "CCEAE7"; // 204, 234, 231

  public static String ACCENT_COLOR = "80CBC4"; // 128, 203, 196

  @Override
  public void actionPerformed(AnActionEvent anActionEvent) {
    MTThemeManager.getInstance().activate(MTTheme.LIGHTER);
  }
}
