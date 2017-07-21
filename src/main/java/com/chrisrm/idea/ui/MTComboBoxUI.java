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

import com.intellij.ide.ui.laf.darcula.ui.DarculaComboBoxUI;
import com.intellij.openapi.ui.GraphicsConfig;
import com.intellij.ui.JBColor;
import com.intellij.util.ObjectUtils;
import com.intellij.util.ui.JBInsets;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.UIUtil;
import sun.swing.DefaultLookup;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.DimensionUIResource;
import javax.swing.plaf.InsetsUIResource;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

/**
 * @author Konstantin Bulenkov
 */
@SuppressWarnings("GtkPreferredJComboBoxRenderer")
public final class MTComboBoxUI extends DarculaComboBoxUI implements Border {
  private final JComboBox myComboBox;

  // Cached the size that the display needs to render the largest item
  private final Dimension myDisplaySizeCache = JBUI.emptySize();
  private Insets myPadding;

  public MTComboBoxUI(final JComboBox comboBox) {
    super(comboBox);
    myComboBox = comboBox;
    myComboBox.setBorder(this);
  }

  @SuppressWarnings("MethodOverridesStaticMethodOfSuperclass")
  public static ComponentUI createUI(final JComponent c) {
    return new MTComboBoxUI(((JComboBox) c));
  }

  protected static boolean isTableCellEditor(final JComponent c) {
    return Boolean.TRUE.equals(c.getClientProperty("JComboBox.isTableCellEditor")) || c.getParent() instanceof JTable;
  }

  protected static boolean hasFocus(final Component c) {
    final Component owner = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
    return owner != null && SwingUtilities.isDescendingFrom(owner, c);
  }

  @Override
  public void paint(final Graphics g, final JComponent c) {
    final Container parent = c.getParent();
    if (parent != null) {
      g.setColor(isTableCellEditor(c) && editor != null ? editor.getBackground() : parent.getBackground());
      g.fillRect(0, 0, c.getWidth(), c.getHeight());
    }
    final Rectangle r = rectangleForCurrentValue();
    if (!isTableCellEditor(c)) {
      paintBorder(c, g, 0, 0, c.getWidth(), c.getHeight());
      hasFocus = comboBox.hasFocus();
      paintCurrentValueBackground(g, r, hasFocus);
    }
    paintCurrentValue(g, r, hasFocus);
  }

  public void paintCurrentValue(final Graphics g, final Rectangle bounds, final boolean hasFocus) {
    final ListCellRenderer renderer = comboBox.getRenderer();
    final Component c;

    c = renderer.getListCellRendererComponent(listBox, comboBox.getSelectedItem(), -1, false, false);
    if (!hasFocus || isPopupVisible(comboBox)) {
      c.setBackground(UIManager.getColor("ComboBox.background"));
    }
    c.setFont(comboBox.getFont());
    if (hasFocus && !isPopupVisible(comboBox)) {
      c.setForeground(listBox.getForeground());
      c.setBackground(listBox.getBackground());
    } else {
      if (comboBox.isEnabled()) {
        c.setForeground(comboBox.getForeground());
        c.setBackground(comboBox.getBackground());
      } else {
        c.setForeground(DefaultLookup.getColor(
            comboBox, this, "ComboBox.disabledForeground", null));
        c.setBackground(DefaultLookup.getColor(
            comboBox, this, "ComboBox.disabledBackground", null));
      }
    }
    // paint selection in table-cell-editor mode correctly
    final boolean changeOpaque = c instanceof JComponent && isTableCellEditor(comboBox) && c.isOpaque();
    if (changeOpaque) {
      ((JComponent) c).setOpaque(false);
    }

    boolean shouldValidate = false;
    if (c instanceof JPanel) {
      shouldValidate = true;
    }

    final Rectangle r = new Rectangle(bounds);
    JBInsets.removeFrom(r, myPadding);

    currentValuePane.paintComponent(g, c, comboBox, r.x, r.y, r.width, r.height, shouldValidate);
    // return opaque for combobox popup items painting
    if (changeOpaque) {
      ((JComponent) c).setOpaque(true);
    }
  }

  @Override
  public void paintBorder(final Component c, final Graphics g2, final int x, final int y, final int width, final int height) {
    if (comboBox == null || arrowButton == null) {
      return; //NPE on LaF change
    }

    hasFocus = false;
    checkFocus();
    final Graphics2D g = (Graphics2D) g2.create();
    final Rectangle arrowButtonBounds = arrowButton.getBounds();
    final int xxx = arrowButtonBounds.x - JBUI.scale(5);
    final int h = height - JBUI.scale(2);
    final int w = width - JBUI.scale(2);

    final Shape clip = g.getClip();
    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
    if (hasFocus) {
      g.clipRect(JBUI.scale(2), JBUI.scale(2), comboBox.getWidth() - JBUI.scale(4), comboBox.getHeight() - JBUI.scale(4));
    }
    final Color background = editor != null && comboBox.isEditable()
                             ? editor.getBackground()
                             : UIManager.getColor("ComboBox.background");
    g.setColor(background);
    g.fillRect(x + JBUI.scale(1), y + JBUI.scale(1), w, h);
    g.setColor(getArrowButtonFillColor(arrowButton.getBackground()));
    g.fillRect(xxx, y + JBUI.scale(1), width - xxx, h);
    g.setColor(background);
    g.fillRect(xxx, y + JBUI.scale(1), JBUI.scale(5), h);

    g.setColor(background);
    final int off = hasFocus ? 1 : 0;
    g.drawLine(xxx + JBUI.scale(5), y + JBUI.scale(1) + off, xxx + JBUI.scale(5), height - JBUI.scale(2));

    final Rectangle r = rectangleForCurrentValue();
    paintCurrentValueBackground(g, r, hasFocus);
    paintCurrentValue(g, r, false);

    if (hasFocus) {
      g.setClip(clip);
      g.setColor(getSelectedBorderColor());
      g.fillRect(JBUI.scale(1), height - JBUI.scale(2), width - JBUI.scale(2), JBUI.scale(2));
    } else if (!comboBox.isEnabled()) {
      g.setColor(getBorderColor());
      g.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0, new float[]{1,
          2}, 0));
      g.draw(new Rectangle2D.Double(JBUI.scale(1), height - JBUI.scale(1), width - JBUI.scale(2), JBUI.scale(2)));
    } else {
      g.setColor(getBorderColor());
      g.fillRect(JBUI.scale(1), height - JBUI.scale(1), width - JBUI.scale(2), JBUI.scale(2));
    }

    g.dispose();
  }

  private Color getSelectedBorderColor() {
    return UIManager.getColor("TextField.selectedSeparatorColor");
  }

  @Override
  public Insets getBorderInsets(final Component c) {
    return new InsetsUIResource(8, 7, 8, 5);
  }

  @Override
  public boolean isBorderOpaque() {
    return false;
  }

  @Override
  protected void installDefaults() {
    super.installDefaults();
    myPadding = UIManager.getInsets("ComboBox.padding");
  }

  protected JButton createArrowButton() {
    final Color bg = myComboBox.getBackground();
    final Color fg = myComboBox.getForeground();
    final JButton button = new BasicArrowButton(SwingConstants.SOUTH, bg, fg, fg, fg) {

      @Override
      public void paint(final Graphics g2) {
        final Graphics2D g = (Graphics2D) g2;
        final GraphicsConfig config = new GraphicsConfig(g);

        final int w = getWidth();
        final int h = getHeight();
        if (!isTableCellEditor(myComboBox)) {
          g.setColor(getArrowButtonFillColor(UIUtil.getControlColor()));
          g.fillRect(0, 0, w, h);
        }
        g.setColor(comboBox.isEnabled() ? new JBColor(getForeground(), getForeground()) : new JBColor(getBorderColor(), getBorderColor()));
        config.setupRoundedBorderAntialiasing();
        final int tW = JBUI.scale(8);
        final int tH = JBUI.scale(6);
        final int xU = (w - tW) / 2;
        final int yU = (h - tH) / 2;
        g.translate(JBUI.scale(2), JBUI.scale(1));
        final Path2D.Double path = new Path2D.Double();
        path.moveTo(xU, yU);
        path.lineTo(xU + tW, yU);
        path.lineTo(xU + tW / 2, yU + tH);
        path.lineTo(xU, yU);
        //path.moveTo(xU + 1, yU + 2);
        //path.lineTo(3 * xU + 1, yU + 2);
        //path.lineTo(2 * xU + 1, 3 * yU);
        //path.lineTo(xU + 1, yU + 2);
        path.closePath();
        g.fill(path);
        g.translate(-JBUI.scale(2), -JBUI.scale(1));
        //        if (!isTableCellEditor(myComboBox)) {
        //          g.setColor(getArrowButtonFillColor(getBorderColor()));
        //          g.drawLine(0, -1, 0, h);
        //        }
        config.restore();
      }

      @Override
      public Dimension getPreferredSize() {
        int size = getFont().getSize() + 4;
        if (size % 2 == 1) {
          size++;
        }
        return new DimensionUIResource(size, size);
      }
    };
    button.setBorder(BorderFactory.createEmptyBorder());
    button.setOpaque(false);
    return button;
  }

  protected Color getArrowButtonFillColor(final Color defaultColor) {
    //    final Color color = myComboBox.hasFocus() ? UIManager.getColor("ComboBox.darcula.arrowFocusedFillColor")
    //                                              : UIManager.getColor("ComboBox.darcula.arrowFillColor");
    //    return color == null ? defaultColor : comboBox != null && !comboBox.isEnabled() ? new JBColor(getBorderColor(), UIUtil
    //        .getControlColor()) : color;
    return ObjectUtils.notNull(UIManager.getColor("ComboBox.arrowFillColor"), UIManager.getColor("control"));
  }

  @Override
  protected Insets getInsets() {
    return JBUI.insets(4, 7, 4, 5).asUIResource();
  }

  protected Dimension getDisplaySize() {
    Dimension display = new Dimension();

    ListCellRenderer renderer = comboBox.getRenderer();
    if (renderer == null) {
      renderer = new DefaultListCellRenderer();
    }

    boolean sameBaseline = true;

    final Object prototypeValue = comboBox.getPrototypeDisplayValue();
    if (prototypeValue != null) {
      display = getSizeForComponent(renderer.getListCellRendererComponent(listBox, prototypeValue, -1, false, false));
    } else {
      final ComboBoxModel model = comboBox.getModel();

      int baseline = -1;
      Dimension d;

      if (model.getSize() > 0) {
        for (int i = 0; i < model.getSize(); i++) {
          final Object value = model.getElementAt(i);
          final Component rendererComponent = renderer.getListCellRendererComponent(listBox, value, -1, false, false);
          d = getSizeForComponent(rendererComponent);
          if (sameBaseline && value != null && (!(value instanceof String) || !"".equals(value))) {
            final int newBaseline = rendererComponent.getBaseline(d.width, d.height);
            if (newBaseline == -1) {
              sameBaseline = false;
            } else if (baseline == -1) {
              baseline = newBaseline;
            } else if (baseline != newBaseline) {
              sameBaseline = false;
            }
          }
          display.width = Math.max(display.width, d.width);
          display.height = Math.max(display.height, d.height);
        }
      } else {
        display = getDefaultSize();
        if (comboBox.isEditable()) {
          display.width = JBUI.scale(100);
        }
      }
    }

    JBInsets.addTo(display, myPadding);

    myDisplaySizeCache.setSize(display.width, display.height);

    return display;
  }

  protected Dimension getSizeForComponent(final Component comp) {
    currentValuePane.add(comp);
    comp.setFont(comboBox.getFont());
    final Dimension d = comp.getPreferredSize();
    currentValuePane.remove(comp);
    return d;
  }

  private Color getBorderColor() {
    if (comboBox != null && myComboBox.isEnabled()) {
      return UIManager.getColor("TextField.separatorColor");
    }
    return UIManager.getColor("TextField.separatorColorDisabled");
  }
}
