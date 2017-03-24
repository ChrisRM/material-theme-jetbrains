package com.chrisrm.idea.config.ui;

import javax.swing.*;

/**
 * Created by helio on 24/03/2017.
 */
public interface MTFormUI {
  void init();

  JComponent getContent();

  void afterStateSet();

  void dispose();
}
