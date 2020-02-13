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
package com.mallowigi.idea.ui;

import com.intellij.ide.ui.laf.darcula.DarculaUIUtil;
import com.intellij.ide.ui.laf.darcula.ui.DarculaComboBoxUI;
import com.intellij.openapi.util.ColoredItem;
import com.intellij.openapi.util.Comparing;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.ui.ColorUtil;
import com.intellij.ui.OffsetIcon;
import com.intellij.ui.SimpleColoredComponent;
import com.intellij.util.ObjectUtils;
import com.intellij.util.ui.JBEmptyBorder;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.StartupUiUtil;
import com.mallowigi.idea.MTConfig;
import com.mallowigi.idea.utils.MTUI;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicComboBoxEditor;
import javax.swing.plaf.basic.ComboPopup;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import java.util.Objects;

/**
 * @author Konstantin Bulenkov
 */
@SuppressWarnings("StandardVariableNames")
public final class MTComboBoxUI extends DarculaComboBoxUI {

  private Insets myPadding = null;
  private final MTConfig config = MTConfig.getInstance();

  @SuppressWarnings("AssignmentToSuperclassField")
  private MTComboBoxUI(final JComboBox comboBox) {
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
  public void paint(final Graphics g, final JComponent c) {
    final Container parent = Objects.requireNonNull(c).getParent();
    if (parent != null) {
      g.setColor(getBackgroundColor());
      g.fillRect(0, 0, c.getWidth(), c.getHeight());
    }
    final Graphics2D g2 = (Graphics2D) g.create();
    final Rectangle r = new Rectangle(c.getSize());
    //    JBInsets.removeFrom(r, JBUI.insets(1));

    try {
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);
      g2.translate(r.x, r.y);

      final float bw = DarculaUIUtil.BW.getFloat();

      g2.setColor(getBackgroundColor());

      g2.fill(new Rectangle2D.Float(r.x, r.y, r.width, r.height - bw));
    } finally {
      g2.dispose();
    }

    if (!comboBox.isEditable()) {
      checkFocus();
      //      paintCurrentValueBackground(g, r, hasFocus);
      paintCurrentValue(g, rectangleForCurrentValue(), hasFocus);
    }
  }

  @SuppressWarnings({"MethodOverridesInaccessibleMethodOfSuper",
    "FeatureEnvy"})
  private Color getBackgroundColor() {
    final Color bg = comboBox.getBackground();
    final boolean enabled = comboBox.isEnabled();
    final boolean isBackgroundSet = comboBox.isBackgroundSet() && !(bg instanceof UIResource);

    if (comboBox.isEditable() && editor != null) {
      if (enabled) {
        return editor.getBackground();
      } else if (isBackgroundSet) {
        return bg;
      }
      return MTUI.ComboBox.getDisabledBackground();
    } else {
      final Object value = comboBox.getSelectedItem();
      final Color coloredItemColor = value instanceof ColoredItem ? ((ColoredItem) value).getColor() : null;

      if (coloredItemColor != null) {
        return coloredItemColor;
      } else if (isBackgroundSet) {
        return bg;
      }
      return enabled ?
             MTUI.ComboBox.getNonEditableBackground() :
             MTUI.ComboBox.getDisabledBackground();
    }
  }

  @SuppressWarnings({"OverlyComplexMethod",
    "OverlyLongMethod",
    "ChainOfInstanceofChecks"})
  @Override
  public void paintCurrentValue(final Graphics g, final Rectangle bounds, final boolean hasFocus) {
    final ListCellRenderer renderer = comboBox.getRenderer();
    final Component cellRendererComponent = renderer.getListCellRendererComponent(listBox, comboBox.getSelectedItem(), -1, false, false);

    // Set cell font and bg;
    cellRendererComponent.setFont(comboBox.getFont());
    cellRendererComponent.setBackground(getBackgroundColor());
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
        cellRendererComponent.setForeground(
          comboBox.isEnabled() ?
          comboBox.getForeground() :
          MTUI.ComboBox.getDisabledForeground());
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

    Icon icon = null;
    Insets iPad = null;
    final Border cellBorder;

    if (cellRendererComponent instanceof SimpleColoredComponent) {
      final SimpleColoredComponent scc = (SimpleColoredComponent) cellRendererComponent;
      iPad = scc.getIpad();
      scc.setIpad(JBUI.emptyInsets());

      cellBorder = scc.getBorder();
      scc.setBorder(JBUI.Borders.empty());

      icon = scc.getIcon();
      if (!scc.isIconOnTheRight()) {
        scc.setIcon(OffsetIcon.getOriginalIcon(icon));
      }
    } else if (cellRendererComponent instanceof JLabel) {
      final JLabel jLabel = (JLabel) cellRendererComponent;

      cellBorder = jLabel.getBorder();
      jLabel.setBorder(JBUI.Borders.empty());

      icon = jLabel.getIcon();
      jLabel.setIcon(OffsetIcon.getOriginalIcon(icon));

      // the following trimMiddle approach is not good for smooth resizing:
      // the text jumps as more or less space becomes available.
      // a proper text layout algorithm on painting in DarculaLabelUI can fix that.
      final String text = jLabel.getText();
      final int maxWidth = bounds.width - (padding == null || StartupUiUtil.isUnderDarcula() ? 0 : padding.right);

      if (StringUtil.isNotEmpty(text) && jLabel.getPreferredSize().width > maxWidth) {
        final int max0 = ObjectUtils.binarySearch(7, text.length() - 1, idx -> {
          jLabel.setText(StringUtil.trimMiddle(text, idx));
          return Comparing.compare(jLabel.getPreferredSize().width, maxWidth);
        });
        final int max = max0 < 0 ? -max0 - 2 : max0;
        if (max > 7 && max < text.length()) {
          jLabel.setText(StringUtil.trimMiddle(text, max));
        }
      }
    } else {
      final JComponent component = (JComponent) cellRendererComponent;
      cellBorder = component.getBorder();
      component.setBorder(JBUI.Borders.empty());
    }

    currentValuePane.paintComponent(g, cellRendererComponent, comboBox, r.x, r.y, r.width, r.height, shouldValidate);
    // return opaque for combobox popup items painting
    if (changeOpaque) {
      ((JComponent) cellRendererComponent).setOpaque(true);
    }

    if (cellRendererComponent instanceof SimpleColoredComponent) {
      final SimpleColoredComponent scc = (SimpleColoredComponent) cellRendererComponent;
      scc.setIpad(iPad);
      scc.setIcon(icon);
      scc.setBorder(cellBorder);
    } else if (cellRendererComponent instanceof JLabel) {
      final JLabel jLabel = (JLabel) cellRendererComponent;
      jLabel.setBorder(cellBorder);
      jLabel.setIcon(icon);
    } else {
      final JComponent component = (JComponent) cellRendererComponent;
      component.setBorder(cellBorder);
    }
  }

  /**
   * Create editable comboboxes
   */
  @Override
  protected ComboBoxEditor createEditor() {
    return new BasicComboBoxEditor.UIResource() {
      @SuppressWarnings("InnerClassTooDeeplyNested")
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
      //      JBInsets.removeFrom(r, JBUI.insets(1));
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
