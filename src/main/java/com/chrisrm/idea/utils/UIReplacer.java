package com.chrisrm.idea.utils;

import com.intellij.util.ui.UIUtil;

import javax.swing.*;

public class UIReplacer {
  public static void patchUI() {
    try {
      Patcher.patchTables();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  static class Patcher {
    static void patchTables() throws Exception {
      StaticPatcher.setFinalStatic(UIUtil.class, "DECORATED_ROW_BG_COLOR", UIManager.get("Table.stripedBackground"));
    }
  }


}
