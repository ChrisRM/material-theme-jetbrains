/*
 * Copyright 2000-2015 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.chrisrm.idea.ui;

import com.intellij.ide.ui.laf.darcula.DarculaUIUtil;
import com.intellij.ide.ui.laf.darcula.ui.DarculaTextBorder;
import com.intellij.ide.ui.laf.darcula.ui.TextFieldWithPopupHandlerUI;
import com.intellij.openapi.util.registry.Registry;
import com.intellij.ui.ColorPanel;
import com.intellij.util.ui.JBUI;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.UIResource;
import javax.swing.text.JTextComponent;
import java.awt.*;

/**
 * @author Konstantin Bulenkov
 */
public class MTTextBorder extends DarculaTextBorder implements Border, UIResource {
  private static Color getBorderColor(boolean enabled) {
    return enabled ? UIManager.getColor("TextField.separatorColor") : UIManager.getColor("TextField.separatorColorDisabled");
  }

  @Override
  public Insets getBorderInsets(Component c) {
    int vOffset = TextFieldWithPopupHandlerUI.isSearchField(c) ? 6 : 4;
    if (TextFieldWithPopupHandlerUI.isSearchFieldWithHistoryPopup(c)) {
      return JBUI.insets(vOffset, 7 + 16 + 3, vOffset, 7 + 16).asUIResource();
    } else if (TextFieldWithPopupHandlerUI.isSearchField(c)) {
      return JBUI.insets(vOffset, 4 + 16 + 3, vOffset, 7 + 16).asUIResource();
    } else if (c instanceof JTextField && c.getParent() instanceof ColorPanel) {
      return JBUI.insets(3, 3, 2, 2).asUIResource();
    } else {
        return JBUI.insets(vOffset, 3, vOffset, 3).asUIResource();
    }
  }

  @Override
  public boolean isBorderOpaque() {
    return false;
  }

  @Override
  public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
    //    if (MTTextFieldUI.isSearchField(c)) {
    //      return;
    //    }

    Graphics2D g2 = (Graphics2D) g.create();
    try {
      g2.translate(x, y);

      Object eop = ((JComponent) c).getClientProperty("JComponent.error.outline");
      if (Registry.is("ide.inplace.errors.outline") && Boolean.parseBoolean(String.valueOf(eop))) {
        DarculaUIUtil.paintErrorBorder(g2, width, height, 0, true, c.hasFocus());
      } else if (c.hasFocus()) {
        g2.setColor(getSelectedBorderColor());
        g2.fillRect(JBUI.scale(1), height - JBUI.scale(2), width - JBUI.scale(2), JBUI.scale(2));
      } else {
        boolean editable = !(c instanceof JTextComponent) || ((JTextComponent) c).isEditable();
        g2.setColor(getBorderColor(editable));
        g2.fillRect(JBUI.scale(1), height - JBUI.scale(1), width - JBUI.scale(2), JBUI.scale(2));
      }
    }
    finally {
      g2.dispose();
    }
  }

  private Color getSelectedBorderColor() {
    return UIManager.getColor("TextField.selectedSeparatorColor");
  }
}
