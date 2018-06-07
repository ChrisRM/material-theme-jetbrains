/*
 *  The MIT License (MIT)
 *
 *  Copyright (c) 2018 Chris Magnussen and Elior Boukhobza
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 *
 */
package com.chrisrm.idea.ui;

import com.chrisrm.idea.MTConfig;
import com.chrisrm.idea.utils.MTUiUtils;
import com.intellij.ide.ui.laf.darcula.ui.DarculaComboBoxUI;
import com.intellij.openapi.ui.ErrorBorderCapable;
import com.intellij.ui.ColorUtil;
import com.intellij.ui.JBColor;
import com.intellij.ui.SimpleColoredComponent;
import com.intellij.util.ObjectUtils;
import com.intellij.util.ui.JBEmptyBorder;
import com.intellij.util.ui.JBInsets;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.UIUtil;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.*;
import javax.swing.plaf.basic.*;
import java.awt.*;
import java.awt.geom.*;

import static com.intellij.ide.ui.laf.darcula.DarculaUIUtil.*;

/**
 * @author Konstantin Bulenkov
 */
public final class MTComboBoxUI extends DarculaComboBoxUI implements Border, ErrorBorderCapable {

  private Insets myPadding;

  public MTComboBoxUI(final JComboBox c) {
    super();
    comboBox = c;
  }

  public static ComponentUI createUI(final JComponent c) {
    return new MTComboBoxUI(((JComboBox) c));
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

  @NotNull
  private Insets getPadding() {
    if (MTConfig.getInstance().isCompactDropdowns()) {
      return JBUI.insets(2, 2);
    }
    return JBUI.insets(7, 2);
  }

  @Override
  protected JButton createArrowButton() {
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

          final float bw = bw();
          final float lw = lw(g2);
          final float arc = arc() - bw - lw;

          final Path2D innerShape = new Path2D.Float();
          innerShape.moveTo(lw, bw + lw);
          innerShape.lineTo(r.width - bw - lw - arc, bw + lw);
          innerShape.quadTo(r.width - bw - lw, bw + lw, r.width - bw - lw, bw + lw + arc);
          innerShape.lineTo(r.width - bw - lw, r.height - bw - lw - arc);
          innerShape.quadTo(r.width - bw - lw, r.height - bw - lw, r.width - bw - lw - arc, r.height - bw - lw);
          innerShape.lineTo(lw, r.height - bw - lw);
          innerShape.closePath();

          g2.setColor(getArrowButtonBackgroundColor(comboBox.isEnabled()));
          g2.fill(innerShape);

          g2.setColor(comboBox.getForeground());
          g2.fill(getArrowShape(this));
        } finally {
          g2.dispose();
        }
      }

      @Override
      public Dimension getPreferredSize() {
        final Insets i = comboBox.getInsets();
        final int height = getBoxHeight() + i.top + i.bottom;
        return new Dimension(JBUI.scale(23) + i.left, height);
      }
    };
    button.setBorder(JBUI.Borders.empty());
    button.setOpaque(false);
    return button;
  }

  private int getBoxHeight() {
    return isCompact() ? JBUI.scale(20) : JBUI.scale(24);
  }

  static Shape getArrowShape(final Component button) {
    final Rectangle r = new Rectangle(button.getSize());
    JBInsets.removeFrom(r, JBUI.insets(1, 0, 1, 1));

    final int tW = JBUI.scale(8);
    final int tH = JBUI.scale(6);
    final int xU = (r.width - tW) / 2 - JBUI.scale(1);
    final int yU = (r.height - tH) / 2 + JBUI.scale(1);

    final Path2D path = new Path2D.Float();
    path.moveTo(xU, yU);
    path.lineTo(xU + tW, yU);
    path.lineTo(xU + tW / 2.0f, yU + tH);
    path.lineTo(xU, yU);
    path.closePath();
    return path;
  }

  @Override
  protected Insets getInsets() {
    return JBUI.insets(2).asUIResource();
  }

  @Override
  public void paint(final Graphics g, final JComponent c) {
    final Container parent = c.getParent();
    if (parent != null) {
      g.setColor(MTComboBoxUI.isTableCellEditor(c) && editor != null ? editor.getBackground() : parent.getBackground());
      g.fillRect(0, 0, c.getWidth(), c.getHeight());
    }
    final Graphics2D g2 = (Graphics2D) g.create();
    final Rectangle r = new Rectangle(c.getSize());
    JBInsets.removeFrom(r, JBUI.insets(1));

    try {
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
      g2.translate(r.x, r.y);

      final float bw = bw();
      final float arc = arc();

      final boolean editable = comboBox.isEnabled() && editor != null && comboBox.isEditable();
      g2.setColor(editable ? editor.getBackground() : comboBox.isEnabled() ? comboBox.getBackground() : getNonEditableBackground());
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

  private Color getNonEditableBackground() {
    return ObjectUtils.notNull(UIManager.getColor("ComboBox.darcula.nonEditableBackground"), new JBColor(0xfcfcfc, 0x3c3f41));
  }

  @Override
  public void paintCurrentValue(final Graphics g, final Rectangle bounds, final boolean hasFocus) {
    final ListCellRenderer renderer = comboBox.getRenderer();
    final Component c = renderer.getListCellRendererComponent(listBox, comboBox.getSelectedItem(), -1, false, false);

    // Set cell font and bg;
    c.setFont(comboBox.getFont());
    c.setBackground(comboBox.isEnabled() ? comboBox.getBackground() : getNonEditableBackground());
    final Rectangle r = new Rectangle(bounds);
    final JComponent jc = (JComponent) c;

    // Set border according to setting
    final JBEmptyBorder border = getCellBorder();
    jc.setBorder(border);

    if (hasFocus && !isPopupVisible(comboBox)) {
      c.setForeground(listBox.getForeground());
    } else {
      if (comboBox.isEnabled()) {
        c.setForeground(comboBox.getForeground());
      } else {
        c.setForeground(UIManager.getColor("ComboBox.disabledForeground", null));
      }
    }
    // paint selection in table-cell-editor mode correctly
    final boolean changeOpaque = MTComboBoxUI.isTableCellEditor(comboBox) && c.isOpaque();
    if (changeOpaque) {
      ((JComponent) c).setOpaque(false);
    }

    boolean shouldValidate = false;
    if (c instanceof JPanel) {
      shouldValidate = true;
    }

    ((JComponent) c).setBorder(getCellBorder());

    Insets iPad = null;
    if (c instanceof SimpleColoredComponent) {
      final SimpleColoredComponent scc = (SimpleColoredComponent) c;
      iPad = scc.getIpad();
      scc.setIpad(JBUI.emptyInsets());
    }

    currentValuePane.paintComponent(g, c, comboBox, r.x, r.y, r.width, r.height, shouldValidate);
    // return opaque for combobox popup items painting
    if (changeOpaque) {
      ((JComponent) c).setOpaque(true);
    }

    if (c instanceof SimpleColoredComponent) {
      final SimpleColoredComponent scc = (SimpleColoredComponent) c;
      scc.setIpad(iPad);
    }
  }

  @NotNull
  private JBEmptyBorder getCellBorder() {
    return isCompact() ? JBUI.Borders.empty(2, 1) : JBUI.Borders.empty(10, 4);
  }

  @Override
  public void paintBorder(final Component c, final Graphics g, final int x, final int y, final int width, final int height) {
    if (!(c instanceof JComponent)) {
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

      final float bw = bw();

      final Object op = comboBox.getClientProperty("JComponent.outline");
      if (op != null) {
        paintOutlineBorder(g2, width, height, 0, true, hasFocus, Outline.valueOf(op.toString()));
      } else {
        final Path2D border = new Path2D.Float(Path2D.WIND_EVEN_ODD);
        if (c.isEnabled()) {
          g2.setColor(getBorderColor());
          border.append(new Rectangle2D.Float(r.x, r.height - bw, r.width, bw), false);
          g2.fill(border);
        } else {
          g2.setColor(getBorderColor());
          g2.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0, new float[] {1,
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

  public void paintFocusBorder(final Graphics2D g, final Path2D border) {
    g.setPaint(getSelectedBorderColor());
    g.fill(border);
  }

  private Color getSelectedBorderColor() {
    final Color defaultValue = ColorUtil.fromHex(MTConfig.getInstance().getAccentColor());
    return ObjectUtils.notNull(UIManager.getColor("TextField.selectedSeparatorColor"), defaultValue);
  }

  public boolean isCompact() {
    return MTConfig.getInstance().isCompactDropdowns();
  }

  @Override
  protected Color getArrowButtonFillColor(final Color defaultColor) {
    return MTUiUtils.getColor(
        UIManager.getColor("ComboBox.arrowFillColor"),
        ObjectUtils.notNull(UIManager.getColor("ComboBox.darcula.arrowFillColor"), defaultColor),
        ObjectUtils.notNull(UIManager.getColor("ComboBox.darcula.arrowFillColor"), defaultColor));
  }

  public Color getArrowButtonBackgroundColor(final boolean enabled) {
    final Color color = UIManager.getColor("ComboBox.darcula.arrowButtonBackground");
    return enabled && color != null ? color : UIUtil.getPanelBackground();
  }

  @Override
  public Insets getBorderInsets(final Component c) {
    return ObjectUtils.notNull(myPadding, getPadding());
  }

  @Override
  protected ComboPopup createPopup() {
    return new MTComboPopup(this, comboBox);
  }

  /**
   * Create editable comboboxes
   */
  @Override
  protected ComboBoxEditor createEditor() {
    final ComboBoxEditor comboBoxEditor = new BasicComboBoxEditor.UIResource() {
      @Override
      protected JTextField createEditorComponent() {
        return new JTextField() {

          @Override
          public Color getBackground() {
            return getComboBackground();
          }
        };
      }
    };

    return comboBoxEditor;
  }

  private Color getComboBackground() {
    if (comboBox != null) {
      if (comboBox.isEnabled() && comboBox.isEditable()) {
        return UIManager.getColor("TextField.background");
      } else if (!comboBox.isEnabled()) {
        return UIManager.getColor("Button.background");
      }
    }
    return UIManager.getColor("Button.background");
  }

  private Color getBorderColor() {
    final Color defaultValue = MTUiUtils.getColor(UIManager.getColor("Separator.foreground"),
                                                  new ColorUIResource(0x515151),
                                                  new ColorUIResource(0xcdcdcd));
    final Color defaultDisabled = MTUiUtils.getColor(UIManager.getColor("ComboBox.disabledBackground"),
                                                     new ColorUIResource(0x3c3f41),
                                                     new ColorUIResource(0xe8e8e8));

    if (comboBox != null && comboBox.isEnabled()) {
      return ObjectUtils.notNull(UIManager.getColor("TextField.separatorColor"), defaultValue);
    }
    return ObjectUtils.notNull(UIManager.getColor("TextField.separatorColorDisabled"), defaultDisabled);
  }
}
