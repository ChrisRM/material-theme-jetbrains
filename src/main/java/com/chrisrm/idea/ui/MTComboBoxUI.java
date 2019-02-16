/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2019 Chris Magnussen and Elior Boukhobza
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

import com.chrisrm.idea.MTConfig;
import com.chrisrm.idea.utils.MTUI;
import com.intellij.ide.ui.laf.darcula.DarculaUIUtil;
import com.intellij.ide.ui.laf.darcula.ui.DarculaComboBoxUI;
import com.intellij.ui.ColorUtil;
import com.intellij.ui.SimpleColoredComponent;
import com.intellij.util.ObjectUtils;
import com.intellij.util.ui.JBEmptyBorder;
import com.intellij.util.ui.JBInsets;
import com.intellij.util.ui.JBUI;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxEditor;
import javax.swing.plaf.basic.ComboPopup;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

/**
 * @author Konstantin Bulenkov
 */
public final class MTComboBoxUI extends DarculaComboBoxUI {

  private Insets myPadding;
  private final MTConfig config = MTConfig.getInstance();

  private MTComboBoxUI(final JComboBox comboBox) {
    //noinspection AssignmentToSuperclassField
    this.comboBox = comboBox;
  }

  @SuppressWarnings({"MethodOverridesStaticMethodOfSuperclass",
      "unused"})
  public static ComponentUI createUI(final JComponent component) {
    return new MTComboBoxUI(((JComboBox) component));
  }

  private void paintFocusBorder(final Graphics2D g, final Shape border) {
    g.setPaint(getSelectedBorderColor());
    g.fill(border);
  }

  private boolean isCompact() {
    return config.isCompactDropdowns();
  }

  @SuppressWarnings("WeakerAccess")
  int getBoxHeight() {
    return isCompact() ? JBUI.scale(20) : JBUI.scale(24);
  }

  @Override
  protected void installDefaults() {
    super.installDefaults();
    comboBox.setBorder(this);
    myPadding = getPadding();
  }

  @Override
  protected void uninstallDefaults() {
    super.uninstallDefaults();
    comboBox.setBorder(null);
  }

  @Override
  protected ComboPopup createPopup() {
    return new MTComboPopup(this, comboBox);
  }

  @Override
  protected JButton createArrowButton() {
    if (!config.isMaterialDesign()) {
      return super.createArrowButton();
    }

    final Color bg = comboBox.getBackground();
    final Color fg = comboBox.getForeground();
    final JButton button = new BasicArrowButton(SwingConstants.SOUTH, bg, fg, fg, fg) {

      @Override
      public void paint(final Graphics g) {
        final Graphics2D g2 = (Graphics2D) g.create();
        final Rectangle r = new Rectangle(getSize());
        JBInsets.removeFrom(r, JBUI.insets(1, 0, 1, 1));

        try {
          g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
          g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
          g2.translate(r.x, r.y);

          final float bw = DarculaUIUtil.BW.getFloat();
          final float lw = DarculaUIUtil.LW.getFloat();
          final float arc = DarculaUIUtil.COMPONENT_ARC.getFloat() - bw - lw;

          final Path2D innerShape = new Path2D.Float();
          innerShape.moveTo(lw, bw + lw);
          innerShape.lineTo(r.width - bw - lw - arc, bw + lw);
          innerShape.quadTo(r.width - bw - lw, bw + lw, r.width - bw - lw, bw + lw + arc);
          innerShape.lineTo(r.width - bw - lw, r.height - bw - lw - arc);
          innerShape.quadTo(r.width - bw - lw, r.height - bw - lw, r.width - bw - lw - arc, r.height - bw - lw);
          innerShape.lineTo(lw, r.height - bw - lw);
          innerShape.closePath();

          g2.setColor(MTUI.ComboBox.getArrowButtonBackgroundColor(comboBox.isEnabled()));
          g2.fill(innerShape);

          g2.setColor(comboBox.getForeground());
          g2.fill(MTUI.ComboBox.getArrowShape(this));
        } finally {
          g2.dispose();
        }
      }

      @Override
      public Dimension getPreferredSize() {
        final Insets insets = comboBox.getInsets();
        final int height = getBoxHeight() + insets.top + insets.bottom;
        return new Dimension(JBUI.scale(23) + insets.left, height);
      }
    };
    button.setBorder(JBUI.Borders.empty());
    button.setOpaque(false);
    return button;
  }

  @SuppressWarnings("MethodWithMoreThanThreeNegations")
  @Override
  public void paint(final Graphics g, final JComponent c) {
    if (!config.isMaterialDesign()) {
      super.paint(g, c);
      return;
    }

    final Container parent = c.getParent();
    if (parent != null) {
      g.setColor(DarculaUIUtil.isTableCellEditor(c) && editor != null ? editor.getBackground() : parent.getBackground());
      g.fillRect(0, 0, c.getWidth(), c.getHeight());
    }
    final Graphics2D g2 = (Graphics2D) g.create();
    final Rectangle r = new Rectangle(c.getSize());
    JBInsets.removeFrom(r, JBUI.insets(1));

    try {
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
      g2.translate(r.x, r.y);

      final float bw = DarculaUIUtil.BW.getFloat();

      final boolean editable = comboBox.isEnabled() && editor != null && comboBox.isEditable();
      g2.setColor(editable ? editor.getBackground() : comboBox.isEnabled() ? comboBox.getBackground() :
                                                      MTUI.ComboBox.getNonEditableBackground());
      g2.fill(new Rectangle2D.Float(r.x, r.y, r.width, r.height - bw));
    } finally {
      g2.dispose();
    }

    if (!comboBox.isEditable()) {
      checkFocus();
      paintCurrentValueBackground(g, r, hasFocus);
      paintCurrentValue(g, rectangleForCurrentValue(), hasFocus);
    }
  }

  @SuppressWarnings({"OverlyComplexMethod",
      "OverlyLongMethod"})
  @Override
  public void paintCurrentValue(final Graphics g, final Rectangle bounds, final boolean hasFocus) {
    final ListCellRenderer renderer = comboBox.getRenderer();
    final Component cellRendererComponent = renderer.getListCellRendererComponent(listBox, comboBox.getSelectedItem(), -1, false, false);

    // Set cell font and bg;
    cellRendererComponent.setFont(comboBox.getFont());
    cellRendererComponent.setBackground(comboBox.isEnabled() ? comboBox.getBackground() : MTUI.ComboBox.getNonEditableBackground());
    final Rectangle r = new Rectangle(bounds);
    final JComponent jc = (JComponent) cellRendererComponent;

    // Set border according to setting
    final JBEmptyBorder border = getCellBorder();
    jc.setBorder(border);

    if (hasFocus && !isPopupVisible(comboBox)) {
      cellRendererComponent.setForeground(listBox.getForeground());
    } else {
      if (comboBox.isEnabled()) {
        cellRendererComponent.setForeground(comboBox.getForeground());
      } else {
        cellRendererComponent.setForeground(MTUI.ComboBox.getDisabledForeground());
      }
    }
    // paint selection in table-cell-editor mode correctly
    final boolean changeOpaque = DarculaUIUtil.isTableCellEditor(comboBox) && cellRendererComponent.isOpaque();
    if (changeOpaque) {
      ((JComponent) cellRendererComponent).setOpaque(false);
    }

    boolean shouldValidate = false;
    if (cellRendererComponent instanceof JPanel) {
      shouldValidate = true;
    }

    ((JComponent) cellRendererComponent).setBorder(getCellBorder());

    Insets iPad = null;
    if (cellRendererComponent instanceof SimpleColoredComponent) {
      final SimpleColoredComponent scc = (SimpleColoredComponent) cellRendererComponent;
      iPad = scc.getIpad();
      scc.setIpad(JBUI.emptyInsets());
    }

    currentValuePane.paintComponent(g, cellRendererComponent, comboBox, r.x, r.y, r.width, r.height, shouldValidate);
    // return opaque for combobox popup items painting
    if (changeOpaque) {
      ((JComponent) cellRendererComponent).setOpaque(true);
    }

    if (cellRendererComponent instanceof SimpleColoredComponent) {
      final SimpleColoredComponent scc = (SimpleColoredComponent) cellRendererComponent;
      scc.setIpad(iPad);
    }
  }

  /**
   * Create editable comboboxes
   */
  @Override
  protected ComboBoxEditor createEditor() {
    return new BasicComboBoxEditor.UIResource() {
      @Override
      protected JTextField createEditorComponent() {
        //noinspection InnerClassTooDeeplyNested
        return new JTextField() {
          @Override
          public Color getBackground() {
            return getComboBackground();
          }
        };
      }
    };
  }

  @Override
  public void paintBorder(final Component c, final Graphics g, final int x, final int y, final int width, final int height) {
    if (!(c instanceof JComponent)) {
      return;
    }
    if (!config.isMaterialDesign()) {
      super.paintBorder(c, g, x, y, width, height);
      return;
    }

    final Graphics2D g2 = (Graphics2D) g.create();
    try {
      checkFocus();
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);

      final Rectangle r = new Rectangle(x, y, width, height);
      JBInsets.removeFrom(r, JBUI.insets(1));
      g2.translate(x, y);

      final float bw = DarculaUIUtil.BW.getFloat();

      final Object op = comboBox.getClientProperty("JComponent.outline");
      if (op != null) {
        DarculaUIUtil.paintOutlineBorder(g2, width, height, 0, true, hasFocus, DarculaUIUtil.Outline.valueOf(op.toString()));
      } else {
        final Path2D border = new Path2D.Float(Path2D.WIND_EVEN_ODD);

        if (c.isEnabled()) {
          g2.setColor(getBorderColor());
          border.append(new Rectangle2D.Float(r.x, r.height - bw, r.width, bw), false);
          g2.fill(border);
        } else {
          g2.setColor(getBorderColor());
          g2.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0, new float[]{1,
              2}, 0));
          g2.draw(new Line2D.Double(r.x, r.height - 1, r.x + r.width, r.height - 1));
        }

        if (hasFocus) {
          paintFocusBorder(g2, border);
        }
      }
    } finally {
      g2.dispose();
    }
  }

  @Override
  public Insets getBorderInsets(final Component c) {
    return ObjectUtils.notNull(myPadding, getPadding());
  }

  @Override
  protected Insets getInsets() {
    return JBUI.insets(2).asUIResource();
  }

  @NotNull
  private Insets getPadding() {
    if (config.isCompactDropdowns()) {
      return JBUI.insets(2, 2);
    }
    return JBUI.insets(7, 2);
  }

  @NotNull
  private JBEmptyBorder getCellBorder() {
    return isCompact() ? JBUI.Borders.empty(2, 1) : JBUI.Borders.empty(10, 4);
  }

  private Color getSelectedBorderColor() {
    final Color defaultValue = ColorUtil.fromHex(config.getAccentColor());
    return ObjectUtils.notNull(MTUI.TextField.getSelectedBorderColor(), defaultValue);
  }

  @SuppressWarnings({"WeakerAccess",
      "FeatureEnvy",
      "MethodWithMultipleReturnPoints"})
  Color getComboBackground() {
    if (comboBox != null) {
      if (comboBox.isEnabled() && comboBox.isEditable()) {
        return MTUI.ComboBox.getEnabledBackground();
      } else if (!comboBox.isEnabled()) {
        return MTUI.ComboBox.getDisabledBackground();
      }
    }
    return MTUI.ComboBox.getFallbackBackground();
  }

  private Color getBorderColor() {
    final boolean isEnabled = comboBox != null && comboBox.isEnabled();
    return MTUI.TextField.getBorderColor(isEnabled);
  }
}
