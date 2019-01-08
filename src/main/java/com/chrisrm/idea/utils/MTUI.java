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
import com.intellij.util.ui.JBUI;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;

@SuppressWarnings({"StaticMethodOnlyUsedInOneClass",
    "EmptyClass"})
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
    public static final String RADIO_BUTTON_SELECTION_ENABLED_COLOR = "RadioButton.darcula.selectionEnabledShadowColor";
    @NonNls
    public static final String RADIO_BUTTON_SELECTION_DISABLED_COLOR = "RadioButton.darcula.selectionDisabledShadowColor";
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
      return UIManager.getColor(RADIO_BUTTON_FOCUS_COLOR);
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
}
