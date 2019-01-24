/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Chris Magnussen and Elior Boukhobza
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

package com.chrisrm.idea.utils;

import com.chrisrm.idea.MTConfig;
import com.intellij.ui.ColorUtil;
import com.intellij.ui.Gray;
import com.intellij.ui.JBColor;
import com.intellij.util.ObjectUtils;
import com.intellij.util.ui.JBInsets;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.UIUtil;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.plaf.*;
import java.awt.*;
import java.awt.geom.*;

@SuppressWarnings( {"StaticMethodOnlyUsedInOneClass",
    "EmptyClass", "MagicNumber"})
public final class MTUI {
  public enum Tree {
    DEFAULT;

    @NonNls
    public static final String TREE_SELECTION_BACKGROUND = "Tree.selectionBackground";

    @NotNull
    public static Color getSelectionBackground() {
      return ColorUtil.withAlpha(UIManager.getColor(TREE_SELECTION_BACKGROUND), 0.25);
    }
  }

  public enum ActionButton {
    DEFAULT;

    @NonNls
    public static final String ACTION_BUTTON_HOVER_BACKGROUND = "ActionButton.hoverBackground";
    @NonNls
    public static final String ACTION_BUTTON_HOVER_BORDER_COLOR = "ActionButton.hoverBorderColor";

    @NotNull
    public static Color getHoverBackground() {
      return JBColor.namedColor(ACTION_BUTTON_HOVER_BACKGROUND, 0x00000000);
    }

    @NotNull
    public static Color getHoverBorderColor() {
      return JBColor.namedColor(ACTION_BUTTON_HOVER_BORDER_COLOR, 0x00000000);
    }
  }

  public enum Button {
    DEFAULT;

    @NonNls
    public static final String BUTTON_BACKGROUND = "Button.background";
    @NonNls
    public static final String BUTTON_FOREGROUND = "Button.foreground";
    @NonNls
    public static final String BUTTON_PRIMARY_BACKGROUND = "Button.focus";
    @NonNls
    public static final String BUTTON_PRIMARY_FOREGROUND = "Button.darcula.selectedButtonForeground";
    @NonNls
    public static final String BUTTON_SELECTED_BACKGROUND = "Button.select";
    @NonNls
    public static final String BUTTON_SELECTED_FOREGROUND = "Button.darcula.selectedButtonForeground";
    @NonNls
    public static final String BUTTON_DISABLED_TEXT_SHADOW = "Button.darcula.disabledText.shadow";
    @NonNls
    public static final String BUTTON_DISABLED_TEXT = "Button.disabledText";

    public static Color getBackgroundColor() {
      return UIManager.getColor(BUTTON_BACKGROUND);
    }

    public static Color getPrimaryBackgroundColor() {
      return UIManager.getColor(BUTTON_PRIMARY_BACKGROUND);
    }

    public static Color getPrimaryForegroundColor() {
      return UIManager.getColor(BUTTON_PRIMARY_FOREGROUND);
    }

    public static Color getForegroundColor() {
      return UIManager.getColor(BUTTON_FOREGROUND);
    }

    public static Color getSelectedBackgroundColor() {
      return UIManager.getColor(BUTTON_SELECTED_BACKGROUND);
    }

    public static Color getSelectedForegroundColor() {
      return UIManager.getColor(BUTTON_SELECTED_FOREGROUND);
    }

    public static Color getDisabledShadowColor() {
      return UIManager.getColor(BUTTON_DISABLED_TEXT_SHADOW);
    }

    public static Color getDisabledColor() {
      return UIManager.getColor(BUTTON_DISABLED_TEXT);
    }
  }

  public enum TextField {
    DEFAULT;
    @NonNls
    public static final String TEXT_FIELD_SEPARATOR_COLOR = "TextField.separatorColor";
    @NonNls
    public static final String TEXT_FIELD_SELECTED_SEPARATOR_COLOR = "TextField.selectedSeparatorColor";
    @NonNls
    public static final String TEXT_FIELD_SEPARATOR_COLOR_DISABLED = "TextField.separatorColorDisabled";

    public static Color getBorderColor(final boolean enabled) {
      return enabled ? UIManager.getColor(TEXT_FIELD_SEPARATOR_COLOR) : UIManager.getColor(TEXT_FIELD_SEPARATOR_COLOR_DISABLED);
    }

    public static Color getSelectedBorderColor() {
      return UIManager.getColor(TEXT_FIELD_SELECTED_SEPARATOR_COLOR);
    }
  }

  public enum List {
    DEFAULT;

    @NonNls
    public static final String LIST_SELECTION_BACKGROUND_PAINTER = "List.sourceListSelectionBackgroundPainter";
    @NonNls
    public static final String LIST_FOCUSED_SELECTION_BACKGROUND_PAINTER = "List.sourceListFocusedSelectionBackgroundPainter";

    public static Border getListSelectionPainter() {
      return UIManager.getBorder(LIST_SELECTION_BACKGROUND_PAINTER);
    }

    public static Border getListFocusedSelectionPainter() {
      return UIManager.getBorder(LIST_FOCUSED_SELECTION_BACKGROUND_PAINTER);
    }
  }

  public enum Table {
    DEFAULT;

    @NonNls
    public static final String TABLE_HIGHLIGHT_OUTER = "Table.highlightOuter";
    @NonNls
    public static final String TABLE_HEADER_BORDER_COLOR = "TableHeader.borderColor";

    public static Color getHighlightOuterColor() {
      return JBColor.namedColor(TABLE_HIGHLIGHT_OUTER, new Color(72, 92, 102));
    }

    public static Color getBorderColor() {
      return JBColor.namedColor(TABLE_HEADER_BORDER_COLOR, 0x425B67);
    }

    @NotNull
    public static Border getCellBorder() {
      final boolean compactTables = MTConfig.getInstance().isCompactTables();
      return compactTables ? JBUI.Borders.empty(3) : JBUI.Borders.empty(10, 5);
    }
  }

  public enum StatusBar {
    DEFAULT;

    @NonNls
    public static final String IDE_STATUS_BAR_BORDER = "IdeStatusBar.border";
  }

  public enum TabbedPane {
    DEFAULT;

    @NonNls
    public static final String TABBED_PANE_SELECTED_FOREGROUND = "TabbedPane.selectedForeground";
    @NonNls
    public static final String TABBED_PANE_FOREGROUND = "TabbedPane.foreground";
    @NonNls
    public static final String TABBED_PANE_SELECTED = "TabbedPane.selectedСolor";

    public static Color getForeground() {
      return UIManager.getColor(TABBED_PANE_FOREGROUND);
    }

    public static Color getSelectedForeground() {
      return UIManager.getColor(TABBED_PANE_SELECTED_FOREGROUND);
    }

    public static Color getHighlightColor() {
      return UIManager.getColor(TABBED_PANE_SELECTED);
    }
  }

  public enum Slider {
    DEFAULT;

    @NonNls
    public static final String SLIDER_THUMB = "Slider.thumb";
    @NonNls
    public static final String SLIDER_TRACK = "Slider.track";
    @NonNls
    public static final String SLIDER_TRACK_DISABLED = "Slider.trackDisabled";

    public static Color getThumbColor() {
      return UIManager.getColor(SLIDER_THUMB);
    }

    public static Color getTrackColor() {
      return UIManager.getColor(SLIDER_TRACK);
    }

    public static Color getTrackDisabledColor() {
      return UIManager.getColor(SLIDER_TRACK_DISABLED);
    }
  }

  @SuppressWarnings("NestedConditionalExpression")
  public enum Spinner {
    DEFAULT;

    @NonNls
    public static final String COMBO_BOX_EDITABLE_ARROW_BACKGROUND = "ComboBox.darcula.editable.arrowButtonBackground";
    @NonNls
    public static final String COMBO_BOX_ARROW_BACKGROUND = "ComboBox.darcula.arrowButtonBackground";
    @NonNls
    public static final String COMBO_BOX_DISABLED_ARROW_BACKGROUND = "ComboBox.darcula.disabledArrowButtonBackground";
    @NonNls
    public static final String COMBO_BOX_ARROW_FOREGROUND = "ComboBox.darcula.arrowButtonForeground";
    @NonNls
    public static final String COMBO_BOX_HOVERED_ARROW_FOREGROUND = "ComboBox.darcula.hoveredArrowButtonForeground";
    @NonNls
    public static final String COMBO_BOX_ARROW_DISABLED_FOREGROUND = "ComboBox.darcula.arrowButtonDisabledForeground";

    public static Color getArrowButtonBackgroundColor(final boolean enabled, final boolean editable) {
      return enabled ?
             editable ?
             JBColor.namedColor(COMBO_BOX_EDITABLE_ARROW_BACKGROUND, Gray.xFC) :
             JBColor.namedColor(COMBO_BOX_ARROW_BACKGROUND, Gray.xFC)
                     : JBColor.namedColor(COMBO_BOX_DISABLED_ARROW_BACKGROUND, Gray.xFC);
    }

    public static Color getArrowButtonForegroundColor(final boolean enabled, final boolean hovered) {
      return enabled ?
             hovered ?
             JBColor.namedColor(COMBO_BOX_HOVERED_ARROW_FOREGROUND, Gray.x66) :
             JBColor.namedColor(COMBO_BOX_ARROW_FOREGROUND, Gray.x66) :
             JBColor.namedColor(COMBO_BOX_ARROW_DISABLED_FOREGROUND, Gray.xAB);
    }
  }

  @SuppressWarnings("unused")
  public enum MTColor {
    DEMO;
    public static final Color PURPLE = new ColorUIResource(0xC792EA);
    public static final Color GREEN = new ColorUIResource(0xC3E88D);
    public static final Color BLUE = new ColorUIResource(0x82AAFF);
    public static final Color CYAN = new ColorUIResource(0x89DDF7);
    public static final Color YELLOW = new ColorUIResource(0xFFCB6B);
    public static final Color RED = new ColorUIResource(0xFF5370);
    public static final Color ORANGE = new ColorUIResource(0xF78C6C);
    public static final Color BROWN = new ColorUIResource(0xAB7967);
    public static final Color PINK = new ColorUIResource(0xBB80B3);
  }

  public enum Separator {
    DEFAULT;

    @NonNls
    public static final String SEPARATOR_SEPARATOR_COLOR = "Separator.separatorColor";

    public static Color getSeparatorColor() {
      return UIManager.getColor(SEPARATOR_SEPARATOR_COLOR);
    }
  }

  public enum Radio {
    DEFAULT;

    @NonNls
    public static final String RADIO_BUTTON_SELECTION_ENABLED_COLOR = "RadioButton.selectionEnabledShadowColor";
    @NonNls
    public static final String RADIO_BUTTON_SELECTION_DISABLED_COLOR = "RadioButton.selectionDisabledShadowColor";
    @NonNls
    public static final String RADIO_BUTTON_BORDER_COLOR = "RadioButton.darcula.borderColor1";
    @NonNls
    public static final String RADIO_BUTTON_FOCUS_COLOR = "RadioButton.focusColor";

    public static Color getSelectedColor(final boolean enabled) {
      return UIManager.getColor(enabled ?
                                RADIO_BUTTON_SELECTION_ENABLED_COLOR :
                                RADIO_BUTTON_SELECTION_DISABLED_COLOR);
    }

    public static Color getBorderColor() {
      return UIManager.getColor(RADIO_BUTTON_BORDER_COLOR);
    }

    public static Color getFocusColor() {
      return ColorUtil.withAlpha(UIManager.getColor(RADIO_BUTTON_FOCUS_COLOR), 0.5);
    }
  }

  public enum ProgressBar {
    DEFAULT;

    public static final String PROGRESS_BAR_TRACK_COLOR = "ProgressBar.trackColor";
    public static final String PROGRESS_BAR_PROGRESS_COLOR = "ProgressBar.progressColor";
    public static final String PROGRESS_BAR_INDETERMINATE_START_COLOR = "ProgressBar.indeterminateStartColor";
    public static final String PROGRESS_BAR_INDETERMINATE_END_COLOR = "ProgressBar.indeterminateEndColor";

    @NotNull
    public static JBColor getTrackColor() {
      return JBColor.namedColor(PROGRESS_BAR_TRACK_COLOR, new JBColor(Gray.xC4, Gray.x55));
    }

    @NotNull
    public static JBColor getProgressColor() {
      return JBColor.namedColor(PROGRESS_BAR_PROGRESS_COLOR, new JBColor(Gray.x80, Gray.xA0));
    }

    @NotNull
    public static Color getIndeterminateStartColor() {
      return JBColor.namedColor(PROGRESS_BAR_INDETERMINATE_START_COLOR, new JBColor(Gray.xC4,
                                                                                    Gray.x69)).brighter().brighter();
    }

    @NotNull
    public static JBColor getIndeterminateEndColor() {
      return JBColor.namedColor(PROGRESS_BAR_INDETERMINATE_END_COLOR, new JBColor(Gray.x80,
                                                                                  Gray.x83));
    }
  }

  public enum Switch {
    DEFAULT;

    public static final String OFF_THUMB_COLOR = "ToggleButton.off.foreground";
    public static final String ON_THUMB_COLOR = "ToggleButton.on.foreground";
    public static final String OFF_BACKGROUND_COLOR = "ToggleButton.off.background";
    public static final String ON_BACKGROUND_COLOR = "ToggleButton.on.background";

    public static Color getOffThumbColor() {
      return UIManager.getColor(OFF_THUMB_COLOR).brighter().brighter();
    }

    public static Color getOnThumbColor() {
      return UIManager.getColor(ON_THUMB_COLOR);
    }

    @NotNull
    public static Color getOffSwitchColor() {
      return UIManager.getColor(OFF_BACKGROUND_COLOR);
    }

    public static Color getOnSwitchColor() {
      return UIManager.getColor(ON_BACKGROUND_COLOR).darker().darker();
    }
  }

  public enum NavBar {
    DEFAULT;

    public static final String NAVBAR_ARROW_COLOR = "NavBar.arrowColor";
    public static final String NAVBAR_HIGHLIGHT_COLOR = "NavBar.selectedColor";

    public static Color getArrowColor() {
      return JBColor.namedColor(NAVBAR_ARROW_COLOR, Gray._100);
    }

    public static Color getHighlightColor() {
      return ColorUtil.withAlpha(JBColor.namedColor(NAVBAR_HIGHLIGHT_COLOR, UIUtil.getListSelectionBackground(true)), 0.5);
    }

    public static int getDecorationOffset() {
      return JBUI.scale(14);
    }

    public static int getDecorationHOffset() {
      return JBUI.scale(9);
    }

    public static int getFirstElementLeftOffset() {
      return JBUI.scale(6);
    }
  }

  public enum ComboBox {
    DEFAULT;

    public static final String COMBO_BOX_ARROW_BUTTON_NON_EDITABLE_BACKGROUND = "ComboBox.ArrowButton.nonEditableBackground";
    public static final String COMBO_BOX_NON_EDITABLE_BACKGROUND = "ComboBox.nonEditableBackground";
    public static final String COMBO_BOX_DISABLED_FOREGROUND = "ComboBox.disabledForeground";
    public static final String TEXT_FIELD_BACKGROUND = "TextField.background";

    @SuppressWarnings("ImplicitNumericConversion")
    public static Shape getArrowShape(@NotNull final Component button) {
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

    public static Color getArrowButtonBackgroundColor(final boolean enabled) {
      final Color color = UIManager.getColor(COMBO_BOX_ARROW_BUTTON_NON_EDITABLE_BACKGROUND);
      return enabled && color != null ? color : UIUtil.getPanelBackground();
    }

    public static Color getNonEditableBackground() {
      return ObjectUtils.notNull(UIManager.getColor(COMBO_BOX_NON_EDITABLE_BACKGROUND), new JBColor(0xfcfcfc, 0x3c3f41));
    }

    public static Color getDisabledForeground() {
      return UIManager.getColor(COMBO_BOX_DISABLED_FOREGROUND);
    }

    public static Color getFallbackBackground() {
      return UIManager.getColor(Button.BUTTON_BACKGROUND);
    }

    public static Color getDisabledBackground() {
      return UIManager.getColor(Button.BUTTON_BACKGROUND);
    }

    public static Color getEnabledBackground() {
      return UIManager.getColor(TEXT_FIELD_BACKGROUND);
    }
  }

  public enum CheckBox {
    DEFAULT;

    private static Color getColor(@NonNls final String shortPropertyName, final Color defaultValue) {
      final Color color = UIManager.getColor("CheckBox.darcula." + shortPropertyName);
      return color == null ? defaultValue : color;
    }

    private static Color getColor(@NonNls final String shortPropertyName, final Color defaultValue, final boolean selected) {
      if (selected) {
        final Color color = getColor(shortPropertyName + ".selected", null);
        if (color != null) {
          return color;
        }
      }
      return getColor(shortPropertyName, defaultValue);
    }

    public static Color getInactiveFillColor() {
      return getColor("inactiveFillColor", Gray._40.withAlpha(180));
    }

    public static Color getBorderColor(final boolean enabled, final boolean selected) {
      return enabled ? getColor("borderColor1", Gray._120.withAlpha(0x5a), selected)
                     : getColor("disabledBorderColor1", Gray._120.withAlpha(90), selected);
    }

    public static Color getBorderColorSelected(final boolean enabled, final boolean selected) {
      return enabled ? getColor("borderColor.selected", Gray._120.withAlpha(0x5a), selected)
                     : getColor("disabledBorderColor.selected", Gray._120.withAlpha(90), selected);
    }

    public static Color getBackgroundColor1(final boolean selected) {
      return getColor("backgroundColor1", Gray._110, selected);
    }

    public static Color getCheckSignColor(final boolean enabled) {
      return enabled ? getColor("checkSignColor", Gray._170, true)
                     : getColor("checkSignColorDisabled", Gray._120, true);
    }

    public static Color getShadowColor(final boolean enabled) {
      return enabled ? getColor("shadowColor", Gray._30, true)
                     : getColor("shadowColorDisabled", Gray._60, true);
    }

    public static Color getFocusedBackgroundColor1(final boolean armed, final boolean selected) {
      return armed ? getColor("focusedArmed.backgroundColor1", Gray._100, selected)
                   : getColor("focused.backgroundColor1", Gray._120, selected);
    }
  }
}
