package com.chrisrm.idea.themes;

import com.intellij.openapi.wm.impl.IdePanePanel;

import java.awt.*;

public class MTIdePanePanel extends IdePanePanel {
  public MTIdePanePanel(LayoutManager layout) {
    super(layout);
  }

  @Override
  public Color getBackground() {
    return Color.BLUE;
  }
}
