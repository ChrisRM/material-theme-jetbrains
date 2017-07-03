/*
 * Copyright 2000-2014 JetBrains s.r.o.
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

import com.intellij.openapi.ui.GraphicsConfig;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicTableHeaderUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * @author Konstantin Bulenkov
 */
public class MTTableHeaderUI extends BasicTableHeaderUI {

  @SuppressWarnings({"MethodOverridesStaticMethodOfSuperclass", "UnusedDeclaration"})
  public static ComponentUI createUI(JComponent c) {
    return new MTTableHeaderUI();
  }

  @Override
  public void paint(Graphics g2, JComponent c) {
    Color borderColor = UIManager.getColor("TableHeader.borderColor");

    final Graphics2D g = (Graphics2D) g2;
    final GraphicsConfig config = new GraphicsConfig(g);
    final Color bg = c.getBackground();
    g.setColor(bg);
    final int h = c.getHeight();
    final int w = c.getWidth();
    g.fillRect(0, 0, w, h);

    g.setColor(borderColor);
    g.drawLine(0, h - 1, w, h - 1);

    config.restore();

    super.paint(g, c);
  }

  //  @Override
  //  protected MouseInputListener createMouseInputListener() {
  //    return new MouseInputListener() {
  //      @Override
  //      public void mouseDragged(MouseEvent e) {
  //      }
  //
  //      @Override
  //      public void mouseMoved(MouseEvent e) {
  //
  //      }
  //
  //      private ColorCycle colorCycle = new ColorCycle(2, 20);
  //
  //      @Override
  //      public void mouseClicked(MouseEvent e) {
  //
  //      }
  //
  //      @Override
  //      public void mousePressed(MouseEvent e) {
  //        highlightButton(e);
  //      }
  //
  //      @Override
  //      public void mouseReleased(MouseEvent e) {
  //        removeHighlight(e);
  //      }
  //
  //      @Override
  //      public void mouseEntered(MouseEvent e) {
  //        highlightButton(e);
  //
  //      }
  //
  //      @Override
  //      public void mouseExited(MouseEvent e) {
  //        removeHighlight(e);
  //
  //      }
  //
  //      private void highlightButton(MouseEvent e) {
  //        colorCycle.stop();
  //
  //        Component component = e.getComponent();
  //        colorCycle.setC((JComponent) component);
  //        Color hoverColor = UIManager.getColor("Button.mt.selection.color1");
  //        Color preHoverColor = UIManager.getColor("Button.mt.selection.color2");
  //        Color textColor = UIManager.getColor("Button.mt.selectedButtonForeground");
  //
  //        component.setForeground(textColor);
  //        colorCycle.start(preHoverColor, hoverColor);
  //      }
  //
  //      private void removeHighlight(MouseEvent e) {
  //        colorCycle.stop();
  //
  //        Component component = e.getComponent();
  //        colorCycle.setC((JComponent) component);
  //        Color notHoverColor = UIManager.getColor("Button.mt.color1");
  //        Color preNotHoverColor = UIManager.getColor("Button.mt.color2");
  //        Color textColor = UIManager.getColor("Button.mt.foreground");
  //
  //        component.setForeground(textColor);
  //        colorCycle.start(preNotHoverColor, notHoverColor);
  //      }
  //    };
  //  }

  @Override
  protected void installDefaults() {
    super.installDefaults();
    header.setDefaultRenderer(new MTDefaultRenderer());
    header.setFont(header.getFont().deriveFont(Font.BOLD));
  }

  private class MTDefaultRenderer extends DefaultTableCellRenderer implements TableCellRenderer {

    private boolean isSelected;
    private boolean hasFocus;
    private int column;
    private boolean hasRollover;

    MTDefaultRenderer() {
      this.setHorizontalAlignment(SwingConstants.LEFT);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
      super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

      this.isSelected = isSelected;
      this.hasFocus = hasFocus;
      this.column = column;
      this.hasRollover = (column == getRolloverColumn());

      Border border;
      int contentTop = 16;
      int contentLeft = 5;
      int contentBottom = 16;
      int contentRight = 5;
      border = new EmptyBorder(contentTop, contentLeft, contentBottom, contentRight);

      setBorder(border);
      setFont(getFont().deriveFont(Font.BOLD));
      return this;
    }
  }
}
