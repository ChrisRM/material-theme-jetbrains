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
import com.intellij.ui.SimpleColoredComponent;
import com.intellij.util.ObjectUtils;
import com.intellij.util.ui.JBInsets;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.MacUIUtil;
import com.intellij.util.ui.UIUtil;
import org.jetbrains.annotations.NotNull;
import sun.swing.DefaultLookup;

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

  //  @Override
  //  public void installUI(final JComponent c) {
  //    super.installUI(c);
  //    comboBox = (JComboBox) c;
  //  }

  @Override
  protected void installDefaults() {
    super.installDefaults();
    comboBox.setBorder(this);
    comboBox.setRenderer(new MyListCellRenderer());
    myPadding = getPadding();
  }

  @NotNull
  private Insets getPadding() {
    if (MTConfig.getInstance().isCompactDropdowns()) {
      return new JBInsets(2, 4, 2, 2);
    }
    return new JBInsets(5, 4, 5, 2);
  }

  @Override
  public void paint(final Graphics g, final JComponent c) {
    final Container parent = c.getParent();
    if (parent != null) {
      g.setColor(MTComboBoxUI.isTableCellEditor(c) && editor != null ? editor.getBackground() : parent.getBackground());
      g.fillRect(0, 0, c.getWidth(), c.getHeight());
    }
    final Graphics2D g2 = (Graphics2D) g.create();
    try {
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);

      final float bw = bw();
      final float arc = arc();

      final boolean editable = editor != null && comboBox.isEditable();
      final Color background = editable && comboBox.isEnabled() ? editor.getBackground() : UIUtil.getPanelBackground();
      g2.setColor(background);

      final Shape innerShape = new RoundRectangle2D.Float(bw, bw, c.getWidth() - bw * 2, c.getHeight() - bw * 2, arc, arc);
      g2.fill(innerShape);
    } finally {
      g2.dispose();
    }

    if (!comboBox.isEditable()) {
      checkFocus();
      final Rectangle r = rectangleForCurrentValue();
      paintCurrentValue(g, r, hasFocus);
    }
  }

  @Override
  public void paintCurrentValue(final Graphics g, final Rectangle bounds, final boolean hasFocus) {
    final ListCellRenderer renderer = comboBox.getRenderer();
    final Component c;

    if (hasFocus && !isPopupVisible(comboBox)) {
      c = renderer.getListCellRendererComponent(listBox, comboBox.getSelectedItem(), -1, true, false);
    } else {
      c = renderer.getListCellRendererComponent(listBox, comboBox.getSelectedItem(), -1, false, false);
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
        c.setForeground(DefaultLookup.getColor(comboBox, this, "ComboBox.disabledForeground", null));
        c.setBackground(DefaultLookup.getColor(comboBox, this, "ComboBox.disabledBackground", null));
      }
    }
    // paint selection in table-cell-editor mode correctly
    final boolean changeOpaque = c instanceof JComponent && MTComboBoxUI.isTableCellEditor(comboBox) && c.isOpaque();
    if (changeOpaque) {
      ((JComponent) c).setOpaque(false);
    }

    boolean shouldValidate = false;
    if (c instanceof JPanel) {
      shouldValidate = true;
    }

    final Rectangle r = new Rectangle(bounds);

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

  private static void doPaint(final Graphics2D g, final int width, final int height, final float arc, final boolean symmetric) {
    float bw = UIUtil.isUnderDefaultMacTheme() ? JBUI.scale(3) : bw();
    final float lw = UIUtil.isUnderDefaultMacTheme() ? JBUI.scale(UIUtil.isRetina(g) ? 0.5f : 1.0f) : JBUI.scale(0.5f);

    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL,
                       MacUIUtil.USE_QUARTZ ? RenderingHints.VALUE_STROKE_PURE : RenderingHints.VALUE_STROKE_NORMALIZE);

    final float outerArc = arc > 0 ? arc + bw - JBUI.scale(2f) : bw;
    final float rightOuterArc = symmetric ? outerArc : JBUI.scale(6f);
    final Path2D outerRect = new Path2D.Float(Path2D.WIND_EVEN_ODD);
    outerRect.moveTo(width - rightOuterArc, 0);
    outerRect.quadTo(width, 0, width, rightOuterArc);
    outerRect.lineTo(width, height - rightOuterArc);
    outerRect.quadTo(width, height, width - rightOuterArc, height);
    outerRect.lineTo(outerArc, height);
    outerRect.quadTo(0, height, 0, height - outerArc);
    outerRect.lineTo(0, outerArc);
    outerRect.quadTo(0, 0, outerArc, 0);
    outerRect.closePath();

    bw += lw;
    final float rightInnerArc = symmetric ? outerArc : JBUI.scale(7f);
    final Path2D innerRect = new Path2D.Float(Path2D.WIND_EVEN_ODD);
    innerRect.moveTo(width - rightInnerArc, bw);
    innerRect.quadTo(width - bw, bw, width - bw, rightInnerArc);
    innerRect.lineTo(width - bw, height - rightInnerArc);
    innerRect.quadTo(width - bw, height - bw, width - rightInnerArc, height - bw);
    innerRect.lineTo(outerArc, height - bw);
    innerRect.quadTo(bw, height - bw, bw, height - outerArc);
    innerRect.lineTo(bw, outerArc);
    innerRect.quadTo(bw, bw, outerArc, bw);
    innerRect.closePath();

    final Path2D path = new Path2D.Float(Path2D.WIND_EVEN_ODD);
    path.append(outerRect, false);
    path.append(innerRect, false);
    g.fill(path);
  }

  @Override
  public void paintCurrentValueBackground(final Graphics g, final Rectangle bounds, final boolean hasFocus) {
    final Color oldColor = g.getColor();
    if (comboBox.isEnabled()) {
      g.setColor(DefaultLookup.getColor(comboBox, this, "ComboBox.background", null));
    } else {
      g.setColor(ColorUtil.toAlpha(DefaultLookup.getColor(comboBox, this, "ComboBox.disabledBackground", null), 50));
    }
    g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);
    g.setColor(oldColor);
  }

  public void paintFocusBorder(final Graphics2D g, final Path2D border) {
    g.setPaint(getSelectedBorderColor());
    g.fill(border);
  }

  public Color getOutlineColor(final boolean enabled) {
    return enabled ? getSelectedBorderColor() : getBorderColor();
  }

  private Color getSelectedBorderColor() {
    final Color defaultValue = ColorUtil.fromHex(MTConfig.getInstance().getAccentColor());
    return ObjectUtils.notNull(UIManager.getColor("TextField.selectedSeparatorColor"), defaultValue);
  }

  @Override
  public boolean isBorderOpaque() {
    return false;
  }

  @Override
  protected JButton createArrowButton() {
    final Color bg = comboBox.getBackground();
    final Color fg = comboBox.getForeground();
    final JButton button = new BasicArrowButton(SwingConstants.SOUTH, bg, fg, fg, fg) {

      @Override
      public void paint(final Graphics g) {
        final Graphics2D g2 = (Graphics2D) g.create();
        try {
          g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
          g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);

          final int w = getWidth();
          final int h = getHeight();
          final float bw = bw();
          final float lw = lw(g2);
          final float arc = arc() - bw - lw;

          final Path2D innerShape = new Path2D.Float();
          innerShape.moveTo(lw, bw + lw);
          innerShape.lineTo(w - bw - lw - arc, bw + lw);
          innerShape.quadTo(w - bw - lw, bw + lw, w - bw - lw, bw + lw + arc);
          innerShape.lineTo(w - bw - lw, h - bw - lw - arc);
          innerShape.quadTo(w - bw - lw, h - bw - lw, w - bw - lw - arc, h - bw - lw);
          innerShape.lineTo(lw, h - bw - lw);
          innerShape.closePath();

          g2.setColor(getArrowButtonFillColor(comboBox.getBackground()));
          g2.fill(innerShape);

          // Paint vertical line
          g2.setColor(getArrowButtonFillColor(getOutlineColor(comboBox.isEnabled())));
          g2.fill(new Rectangle2D.Float(0, bw + lw, lw(g2), getHeight() - (bw + lw) * 2));

          g2.setColor(comboBox.getForeground());
          g2.fill(getArrowShape());
        } finally {
          g2.dispose();
        }
      }

      private Shape getArrowShape() {
        final int tW = JBUI.scale(8);
        final int tH = JBUI.scale(6);
        final int xU = (getWidth() - tW) / 2 - JBUI.scale(1);
        final int yU = (getHeight() - tH) / 2 + JBUI.scale(1);

        final Path2D path = new Path2D.Float();
        path.moveTo(xU, yU);
        path.lineTo(xU + tW, yU);
        path.lineTo(xU + tW / 2, yU + tH);
        path.lineTo(xU, yU);
        path.closePath();
        return path;
      }

      @Override
      public Dimension getPreferredSize() {
        final Insets i = comboBox.getInsets();
        return new Dimension(JBUI.scale(14) + i.left, JBUI.scale(18) + i.top + i.bottom);
      }
    };
    button.setBorder(JBUI.Borders.empty());
    button.setOpaque(false);
    return button;
  }

  @Override
  protected Color getArrowButtonFillColor(final Color defaultColor) {
    return MTUiUtils.getColor(
        UIManager.getColor("ComboBox.arrowFillColor"),
        ObjectUtils.notNull(UIManager.getColor("ComboBox.darcula.arrowFillColor"), defaultColor),
        ObjectUtils.notNull(UIManager.getColor("ComboBox.darcula.arrowFillColor"), defaultColor));
  }

  @Override
  public Insets getBorderInsets(final Component c) {
    return ObjectUtils.notNull(myPadding, getPadding());
  }

  @Override
  protected Insets getInsets() {
    return JBUI.insets(0, 4).asUIResource();
  }

  @Override
  protected Dimension getSizeForComponent(final Component comp) {
    currentValuePane.add(comp);
    comp.setFont(comboBox.getFont());
    final Dimension d = comp.getPreferredSize();
    currentValuePane.remove(comp);
    return d;
  }

  @Override
  public void paintBorder(final Component c, final Graphics g, final int x, final int y, final int width, final int height) {
    if (comboBox == null || arrowButton == null) {
      return; //NPE on LaF change
    }

    final Graphics2D g2 = (Graphics2D) g.create();
    final Rectangle arrowButtonBounds = arrowButton.getBounds();
    final int xxx = arrowButtonBounds.x - JBUI.scale(5);
    final int h = height - JBUI.scale(2);
    final int w = width - JBUI.scale(2);
    try {
      checkFocus();
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);

      g2.translate(x, y);

      // Paint background to take all component
      final Color background = editor != null && comboBox.isEditable()
                               ? editor.getBackground()
                               : UIManager.getColor("ComboBox.background");
      g.setColor(background);
      g.fillRect(x + JBUI.scale(1), y + JBUI.scale(1), w, h);
      g.setColor(getArrowButtonFillColor(arrowButton.getBackground()));
      g.fillRect(xxx, y + JBUI.scale(1), width - xxx, h);
      g.setColor(background);
      g.fillRect(xxx, y + JBUI.scale(1), JBUI.scale(5), h);

      final Rectangle r = rectangleForCurrentValue();
      paintCurrentValueBackground(g, r, hasFocus);
      paintCurrentValue(g, r, false);

      // Paint the border
      final float bw = bw();
      final float off = bw * 2;

      final Path2D border = new Path2D.Float(Path2D.WIND_EVEN_ODD);
      if (c.isEnabled()) {
        g2.setColor(getBorderColor());
        border.append(new Rectangle2D.Float(bw, height - bw, width - off, off), false);
        g2.fill(border);
      } else {
        g2.setColor(getBorderColor());
        g2.setStroke(new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0, new float[] {1,
            2}, 0));
        g2.draw(new Rectangle2D.Double(bw, height - bw, width - off, off));
      }

      final Object op = comboBox.getClientProperty("JComponent.outline");
      if (op != null) {
        paintOutlineBorder(g2, width, height, 0, true, hasFocus, Outline.valueOf(op.toString()));
      } else if (hasFocus) {
        paintFocusBorder(g2, border);
      }
    } finally {
      g2.dispose();
    }
  }

  @Override
  protected ComboPopup createPopup() {
    return new MTComboPopup(comboBox);
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

  private class MyListCellRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(final JList list, final Object value, final int index, final boolean isSelected,
                                                  final boolean cellHasFocus) {
      final Component comp = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
      comp.setFont(comp.getFont().deriveFont(Font.PLAIN, JBUI.scale(13.0f)));
      setBorder(BorderFactory.createEmptyBorder(myPadding.top * 2, myPadding.left, myPadding.bottom * 2, myPadding.right));
      return comp;
    }
  }

  private class MTComboPopup extends BasicComboPopup implements ComboPopup {
    MTComboPopup(final JComboBox combo) {
      super(combo);
    }

    @Override
    protected void paintBorder(final Graphics g) {
      final Graphics2D g2 = (Graphics2D) g.create();
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);

      final float lw = lw(g2);
      final float bw = 6;

      final Path2D border = new Path2D.Float(Path2D.WIND_EVEN_ODD);
      border.append(new RoundRectangle2D.Float(bw, bw, getWidth() - bw * 2, getHeight() - bw * 2, 0, 0), false);
      g2.setColor(getBorderColor());
      doPaint(g2, getWidth(), getHeight(), 0, true);
    }
  }
}
