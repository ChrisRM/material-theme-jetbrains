package com.chrisrm.idea.config.ui;

import com.intellij.ui.CheckBoxWithColorChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

/**
 * Created by helio on 24/03/2017.
 */
public class CheckBoxWithColorChooserImpl extends CheckBoxWithColorChooser {
  private MouseListener[] disabledListeners;

  public CheckBoxWithColorChooserImpl(String text) {
    super(text);
  }

  @Override
  public void setEnabled(boolean enabled) {
    super.setEnabled(enabled);

    // Disallow color choosing if disabled
    synchronized (getTreeLock()) {
      for (Component component : getComponents()) {
        component.setEnabled(enabled);
        if (component instanceof JButton) {
          if (enabled) {
            if (disabledListeners != null) {
              for (MouseListener listener : disabledListeners) {
                component.addMouseListener(listener);
              }
            }
            disabledListeners = null;
          }
          else {
            disabledListeners = component.getMouseListeners();
            for (MouseListener listener : disabledListeners) {
              component.removeMouseListener(listener);
            }
          }
        }
      }
    }
  }

  public void dispose() {
    disabledListeners = null;
  }
}
