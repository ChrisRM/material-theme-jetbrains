package com.chrisrm.idea.ui;

import com.intellij.openapi.components.ServiceManager;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;

public class MTTextFieldFactory {
  public static MTTextFieldFactory getInstance() {
    return ServiceManager.getService(MTTextFieldFactory.class);
  }

  public static ComponentUI newInstance(JComponent c) {
    return new MTTextFieldUI(c);
  }
}
