/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Chris Magnussen and Elior Boukhobza
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 *
 */

package com.chrisrm.idea.ui;

import com.intellij.openapi.ui.GraphicsConfig;
import com.intellij.util.ui.GraphicsUtil;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author Konstantin Bulenkov
 */
public class MTInternalFrameUI extends BasicInternalFrameUI {
  private MTInternalFrameUI(JInternalFrame b) {
    super(b);
  }


  @SuppressWarnings({"MethodOverridesStaticMethodOfSuperclass", "UnusedDeclaration"})
  public static ComponentUI createUI(JComponent c) {
    return new MTInternalFrameUI((JInternalFrame) c);
  }

  @Override
  protected JComponent createNorthPane(JInternalFrame w) {
    this.titlePane = new BasicInternalFrameTitlePane(w) {
      @Override
      protected void installDefaults() {
        super.installDefaults();

        closeIcon = new CloseIcon();
        maxIcon = new MaximizeIcon();
        minIcon = new MinimizeIcon();
        iconIcon = new IconifyIcon();

        selectedTitleColor = UIManager.getColor("InternalFrameTitlePane.material.selected.backgroundColor");
        selectedTextColor = UIManager.getColor("darcula.textForeground");
        notSelectedTitleColor = UIManager.getColor("InternalFrameTitlePane.material.backgroundColor");
        notSelectedTextColor = UIManager.getColor("darcula.textForeground");
      }

      @Override
      protected void createButtons() {
        super.createButtons();

        MouseListener listener = new MouseAdapter() {
          @Override
          public void mouseEntered(MouseEvent e) {
            Icon icon = ((JButton) e.getComponent()).getIcon();
            if (icon instanceof FrameIcon) {
              Color c = ((FrameIcon) icon).getColor();
              ((FrameIcon) icon).setColor(c.brighter());
              e.getComponent().repaint();
            }
          }

          @Override
          public void mouseExited(MouseEvent e) {
            Icon icon = ((JButton) e.getComponent()).getIcon();
            if (icon instanceof FrameIcon) {
              ((FrameIcon) icon).setColor(UIManager.getColor("InternalFrameTitlePane.material.buttonColor"));
              e.getComponent().repaint();
            }
          }
        };

        closeButton.setBorder(null);
        closeButton.setOpaque(false);
        closeButton.addMouseListener(listener);

        maxButton.setBorder(null);
        maxButton.setOpaque(false);
        maxButton.addMouseListener(listener);

        iconButton.setBorder(null);
        iconButton.setOpaque(false);
        iconButton.addMouseListener(listener);
      }

      @Override
      protected void paintBorder(Graphics g) {
        int w = getWidth();
        int h = getHeight();

        Color top = UIManager.getColor("InternalFrameTitlePane.material.borderColorTop");
        Color left = UIManager.getColor("InternalFrameTitlePane.material.borderColorLeft");
        Color bottom = UIManager.getColor("InternalFrameTitlePane.material.borderColorBottom");
        if (frame.isSelected()) {
          top = UIManager.getColor("InternalFrameTitlePane.material.selected.borderColorTop");
          left = UIManager.getColor("InternalFrameTitlePane.material.selected.borderColorLeft");
          bottom = UIManager.getColor("InternalFrameTitlePane.material.selected.borderColorBottom");
        }

        g.setColor(top);
        g.drawLine(2, 0, w, 0);
        g.setColor(left);
        g.drawLine(0, 1, 0, h);
        g.setColor(bottom);
        g.drawLine(2, h, w, h);
      }
    };

    this.titlePane.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 0));
    return this.titlePane;
  }

  private static abstract class FrameIcon implements Icon {
    private Color mColor;

    public FrameIcon(Color c) {
      mColor = c;
    }

    public Color getColor() {
      return mColor;
    }

    public void setColor(Color c) {
      mColor = c;
    }

    @Override
    public int getIconWidth() {
      return 16;
    }

    @Override
    public int getIconHeight() {
      return 16;
    }
  }

  private static class CloseIcon extends FrameIcon {
    public CloseIcon() {
      this(UIManager.getColor("InternalFrameTitlePane.material.buttonColor"));
    }

    public CloseIcon(Color c) {
      super(c);
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
      Graphics2D g2 = (Graphics2D) g;
      GraphicsConfig config = GraphicsUtil.setupAAPainting(g2);
      g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
      g2.setStroke(new BasicStroke(1.5f));
      g2.setPaint(getColor());
      g.drawLine(5, 4, 11, 10);
      g.drawLine(11, 4, 5, 10);
      config.restore();
    }
  }

  private static class MaximizeIcon extends FrameIcon {
    public MaximizeIcon() {
      this(UIManager.getColor("InternalFrameTitlePane.material.buttonColor"));
    }

    public MaximizeIcon(Color c) {
      super(c);
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
      Graphics2D g2 = (Graphics2D) g;
      com.intellij.openapi.ui.GraphicsConfig config = GraphicsUtil.setupAAPainting(g2);
      g2.setStroke(new BasicStroke(2f));
      g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
      g2.setPaint(getColor());
      g2.setStroke(new BasicStroke(2f));
      g2.drawRect(3, 3, 10, 9);
      config.restore();
    }
  }

  @Override
  protected void installDefaults() {
    super.installDefaults();

  }

  private static class MinimizeIcon extends FrameIcon {
    public MinimizeIcon() {
      this(UIManager.getColor("InternalFrameTitlePane.material.buttonColor"));
    }

    public MinimizeIcon(Color c) {
      super(c);
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
      Graphics2D g2 = (Graphics2D) g;
      GraphicsConfig config = GraphicsUtil.setupAAPainting(g2);
      g2.setStroke(new BasicStroke(2f));
      g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
      g2.setPaint(getColor());
      g2.setStroke(new BasicStroke(2f));
      g2.drawRect(1, 5, 8, 8);
      g2.drawLine(4, 2, 12, 2);
      g2.drawLine(12, 3, 12, 10);
      config.restore();
    }
  }

  private static class IconifyIcon extends FrameIcon {
    public IconifyIcon() {
      this(UIManager.getColor("InternalFrameTitlePane.material.buttonColor"));
    }

    public IconifyIcon(Color c) {
      super(c);
    }

    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
      Graphics2D g2 = (Graphics2D) g;
      GraphicsConfig config = GraphicsUtil.setupAAPainting(g2);
      g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
      g2.setPaint(getColor());
      g2.setStroke(new BasicStroke(2f));
      g2.drawLine(4, 12, 12, 12);
      config.restore();
    }
  }
}
