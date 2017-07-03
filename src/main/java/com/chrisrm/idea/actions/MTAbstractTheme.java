package com.chrisrm.idea.actions;

import com.chrisrm.idea.MTConfig;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

public abstract class MTAbstractTheme extends AnAction {
  public static String BACKGROUND = ""; // 38, 50, 56
  public static String FOREGROUND = ""; // 176, 190, 197
  public static String CARET = ""; // 255, 204, 0
  public static String BORDER = ""; // 34, 45, 51
  public static String TEXT = ""; // 96, 125, 139
  public static String SELECTION_BACKGROUND = ""; // 84, 110, 122
  public static String SELECTION_FOREGROUND = "";
  public static String LABEL = ""; // 176, 190, 197
  public static String SUB_LABEL = ""; // 84, 110, 122
  public static String DISABLED = ""; // 65, 89, 103
  public static String SIDEBAR_HEADING = ""; // 207, 216, 220
  public static String STATUS_LABEL = ""; // 120, 144, 156
  public static String INPUT_BORDER = ""; //55, 71, 79
  public static String BUTTON_BACKGROUND = ""; // 44, 60, 65
  public static String BUTTON_FOREGROUND = "";// 96, 125, 139
  public static String BUTTON_SELECTED = ""; // 49, 69, 73
  public static String ACCENT_COLOR = ""; // 128, 203, 196

  @Override
  public abstract void actionPerformed(AnActionEvent anActionEvent);

  @Override
  public void update(AnActionEvent e) {
    e.getPresentation().setEnabled(MTConfig.getInstance().isMaterialTheme());
  }
}
