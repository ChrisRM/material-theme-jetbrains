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

package com.chrisrm.idea.utils;

import com.chrisrm.idea.MTConfig;
import com.intellij.ide.ui.laf.darcula.DarculaLaf;
import com.intellij.ide.ui.laf.darcula.DarculaUIUtil;
import com.intellij.ui.ColorUtil;
import com.intellij.ui.Gray;
import com.intellij.ui.JBColor;
import com.intellij.util.ui.JBInsets;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.UIUtil;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import sun.swing.SwingUtilities2;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.geom.Path2D;

@SuppressWarnings({"StaticMethodOnlyUsedInOneClass",
    "EmptyClass",
    "MagicNumber"})
public final class MTUI {
  public enum Tree {
    MARIJUANA;

    @NonNls
    private static final String TREE_SELECTION_BACKGROUND = "Tree.selectionBackground";

    @NotNull
    public static Color getSelectionBackground() {
      final Color color = JBColor.namedColor(TREE_SELECTION_BACKGROUND, new JBColor(0x27384C, 0x0D293E));
      return ColorUtil.withAlpha(color, 0.25);
    }

    public static Color getSelectionInactiveBackground() {
      final Color color = JBColor.namedColor(TREE_SELECTION_BACKGROUND, new JBColor(0x27384C, 0x0D293E));
      return ColorUtil.withAlpha(color, 0.25);
    }
  }

  public enum ActionButton {
    FIGHT;

    @NonNls
    public static final String ACTION_BUTTON_HOVER_BACKGROUND = "ActionButton.hoverBackground";
    @NonNls
    public static final String ACTION_BUTTON_HOVER_BORDER_COLOR = "ActionButton.hoverBorderColor";

    @NotNull
    public static Color getHoverBackground() {
      return JBColor.namedColor(ACTION_BUTTON_HOVER_BACKGROUND, new JBColor(0xdfdfdf, 0x4c5052));
    }

    @NotNull
    public static Color getHoverBorderColor() {
      return JBColor.namedColor(ACTION_BUTTON_HOVER_BORDER_COLOR, new JBColor(0xdfdfdf, 0x4c5052));
    }
  }

  public enum Button {
    BOTON;

    @NonNls
    static final String BUTTON_BACKGROUND = "Button.background";
    @NonNls
    private static final String BUTTON_FOREGROUND = "Button.foreground";
    @NonNls
    private static final String BUTTON_PRIMARY_BACKGROUND = "Button.default.startBackground";
    @NonNls
    private static final String BUTTON_PRIMARY_FOREGROUND = "Button.default.foreground";
    @NonNls
    private static final String BUTTON_SELECTED_BACKGROUND = "Button.focus";
    @NonNls
    private static final String BUTTON_SELECTED_FOREGROUND = "Button.default.foreground";
    @NonNls
    private static final String BUTTON_DISABLED_TEXT_SHADOW = "Button.default.shadowColor";
    @NonNls
    public static final String BUTTON_DISABLED_TEXT = "Button.disabledText";

    public static Color getBackgroundColor() {
      return JBColor.namedColor(BUTTON_BACKGROUND, new JBColor(0xf2f2f2, 0x3c3f41));
    }

    public static Color getPrimaryBackgroundColor() {
      return JBColor.namedColor(BUTTON_PRIMARY_BACKGROUND, new JBColor(0x4A86C7, 0x365880));
    }

    public static Color getPrimaryForegroundColor() {
      return JBColor.namedColor(BUTTON_PRIMARY_FOREGROUND, new JBColor(0xf0f0f0, 0xbbbbbb));
    }

    public static Color getForegroundColor() {
      return JBColor.namedColor(BUTTON_FOREGROUND, new JBColor(0x000000, 0xbbbbbb));
    }

    public static Color getSelectedBackgroundColor() {
      return JBColor.namedColor(BUTTON_SELECTED_BACKGROUND, new JBColor(0x97c3f3, 0x43688c));
    }

    public static Color getSelectedForegroundColor() {
      return JBColor.namedColor(BUTTON_SELECTED_FOREGROUND, new JBColor(0xf0f0f0, 0xbbbbbb));
    }

    public static Color getDisabledShadowColor() {
      return JBColor.namedColor(BUTTON_DISABLED_TEXT_SHADOW, new JBColor(0xa6a6a680, 0x36363680));
    }

    public static Color getDisabledColor() {
      return JBColor.namedColor(BUTTON_DISABLED_TEXT, new JBColor(0x999999, 0x777777));
    }
  }

  public enum TextField {
    SEXTFIELD;

    @NonNls
    private static final String TEXT_FIELD_SEPARATOR_COLOR = "Component.borderColor";
    @NonNls
    private static final String TEXT_FIELD_SELECTED_SEPARATOR_COLOR = "Component.focusedBorderColor";
    @NonNls
    public static final String TEXT_FIELD_SEPARATOR_COLOR_DISABLED = "Component.disabledBorderColor";

    public static Color getBorderColor(final boolean enabled) {
      final JBColor borderColor = JBColor.namedColor(TEXT_FIELD_SEPARATOR_COLOR, new JBColor(0xc4c4c4, 0x646464));
      final JBColor disabledColor = JBColor.namedColor(TEXT_FIELD_SEPARATOR_COLOR_DISABLED, new JBColor(0xcfcfcf, 0x646464));

      return enabled ? borderColor : disabledColor;
    }

    public static Color getSelectedBorderColor() {
      return JBColor.namedColor(TEXT_FIELD_SELECTED_SEPARATOR_COLOR, new JBColor(0x87AFDA, 0x466D94));
    }
  }

  public enum List {
    ARRAY;

    @NonNls
    public static final String LIST_BACKGROUND_PAINTER = "List.sourceListBackgroundPainter";
    @NonNls
    public static final String LIST_SELECTION_BACKGROUND_PAINTER = "List.sourceListSelectionBackgroundPainter";
    @NonNls
    public static final String LIST_FOCUSED_SELECTION_BACKGROUND_PAINTER = "List.sourceListFocusedSelectionBackgroundPainter";

    public static Border getListPainter() {
      return UIManager.getBorder(LIST_BACKGROUND_PAINTER);
    }

    public static Border getListSelectionPainter() {
      return UIManager.getBorder(LIST_SELECTION_BACKGROUND_PAINTER);
    }

    public static Border getListFocusedSelectionPainter() {
      return UIManager.getBorder(LIST_FOCUSED_SELECTION_BACKGROUND_PAINTER);
    }
  }

  public enum Table {
    CHAIR;

    @NonNls
    private static final String TABLE_HIGHLIGHT_OUTER = "Table.highlightOuter";
    @NonNls
    private static final String TABLE_HEADER_BORDER_COLOR = "TableHeader.borderColor";

    public static Color getHighlightOuterColor() {
      return JBColor.namedColor(TABLE_HIGHLIGHT_OUTER, new JBColor(0x79c0ff, 0x79c0ff));
    }

    public static Color getBorderColor() {
      return JBColor.namedColor(TABLE_HEADER_BORDER_COLOR, new JBColor(0xdddddd, 0x2c2c2c));
    }

    @NotNull
    public static Border getCellBorder() {
      final boolean compactTables = MTConfig.getInstance().isCompactTables();
      return compactTables ? JBUI.Borders.empty(3) : JBUI.Borders.empty(10, 5);
    }
  }

  public enum StatusBar {
    SB;

    @NonNls
    public static final String IDE_STATUS_BAR_BORDER = "IdeStatusBar.border";
  }

  public enum TabbedPane {
    TABUU;

    @NonNls
    private static final String TABBED_PANE_SELECTED_FOREGROUND = "TabbedPane.selectedForeground";
    @NonNls
    private static final String TABBED_PANE_FOREGROUND = "TabbedPane.foreground";
    @NonNls
    private static final String TABBED_PANE_SELECTED = "TabbedPane.selectedСolor";
    @NonNls
    private static final String HOVERED_TAB_BACKGROUND = "DefaultTabs.hoverBackground";
    @NonNls
    private static final String TAB_BACKGROUND = "DefaultTabs.background";
    @NonNls
    private static final String INACTIVE_TAB_BACKGROUND = "EditorTabs.inactiveColoredFileBackground";
    @NonNls
    private static final String INACTIVE_TAB_CONTRAST_BACKGROUND = "DefaultTabs.inactiveMaskColor";

    public static Color getForeground() {
      return JBColor.namedColor(TABBED_PANE_FOREGROUND, new JBColor(0x000000, 0xbbbbbb));
    }

    public static Color getSelectedForeground() {
      return JBColor.namedColor(TABBED_PANE_SELECTED_FOREGROUND, new JBColor(0xffffff, 0xffffff));
    }

    public static Color getHighlightColor() {
      return JBColor.namedColor(TABBED_PANE_SELECTED, new JBColor(0xdae4ed, 0x3d4b5c));
    }

    public static Color getHoveredBackground() {
      return JBColor.namedColor(HOVERED_TAB_BACKGROUND, new JBColor(0xdae4ed, 0x3d4b5c));
    }

    public static Color getBackground() {
      return JBColor.namedColor(TAB_BACKGROUND, new JBColor(0xdae4ed, 0x3d4b5c));
    }

    public static Color getInactiveBackground(final boolean isContrast) {
      final JBColor inactiveTabBG = JBColor.namedColor(INACTIVE_TAB_BACKGROUND, new JBColor(0xdae4ed, 0x3d4b5c));
      final JBColor inactiveContrastBG = JBColor.namedColor(INACTIVE_TAB_CONTRAST_BACKGROUND, new JBColor(0xdae4ed, 0x3d4b5c));
      return isContrast ? inactiveContrastBG : inactiveTabBG;
    }
  }

  public enum Slider {
    PARALLEL_WORLDS;

    @NonNls
    private static final String SLIDER_THUMB = "Slider.thumb";
    @NonNls
    private static final String SLIDER_TRACK = "Slider.track";
    @NonNls
    private static final String SLIDER_TRACK_DISABLED = "Slider.trackDisabled";

    public static Color getThumbColor() {
      return JBColor.namedColor(SLIDER_THUMB, new JBColor(0xc4c4c4, 0x646464));
    }

    public static Color getTrackColor() {
      return JBColor.namedColor(SLIDER_TRACK, new JBColor(0xc4c4c4, 0x646464));
    }

    public static Color getTrackDisabledColor() {
      return JBColor.namedColor(SLIDER_TRACK_DISABLED, new JBColor(0xcfcfcf, 0x646464));
    }
  }

  @SuppressWarnings("NestedConditionalExpression")
  public enum Spinner {
    FIDGET;

    @NonNls
    private static final String COMBO_BOX_EDITABLE_ARROW_BACKGROUND = "ComboBox.darcula.editable.arrowButtonBackground";
    @NonNls
    private static final String COMBO_BOX_ARROW_BACKGROUND = "ComboBox.darcula.arrowButtonBackground";
    @NonNls
    private static final String COMBO_BOX_DISABLED_ARROW_BACKGROUND = "ComboBox.darcula.disabledArrowButtonBackground";
    @NonNls
    private static final String COMBO_BOX_ARROW_FOREGROUND = "ComboBox.darcula.arrowButtonForeground";
    @NonNls
    private static final String COMBO_BOX_HOVERED_ARROW_FOREGROUND = "ComboBox.darcula.hoveredArrowButtonForeground";
    @NonNls
    private static final String COMBO_BOX_ARROW_DISABLED_FOREGROUND = "ComboBox.darcula.arrowButtonDisabledForeground";

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
    IROIRO;

    public static final Color PURPLE = new ColorUIResource(0xC792EA);
    public static final Color GREEN = new ColorUIResource(0xC3E88D);
    public static final Color BLUE = new ColorUIResource(0x82AAFF);
    public static final Color CYAN = new ColorUIResource(0x89DDF7);
    public static final Color YELLOW = new ColorUIResource(0xFFCB6B);
    public static final Color RED = new ColorUIResource(0xFF5370);
    public static final Color ORANGE = new ColorUIResource(0xF78C6C);
    public static final Color BROWN = new ColorUIResource(0xAB7967);
    public static final Color PINK = new ColorUIResource(0xBB80B3);

    public static final Color DARK_PURPLE = new ColorUIResource(0x4D2C91);
    public static final Color DARK_GREEN = new ColorUIResource(0x387002);
    public static final Color DARK_BLUE = new ColorUIResource(0x004BA0);
    public static final Color DARK_CYAN = new ColorUIResource(0x008BA3);
    public static final Color DARK_YELLOW = new ColorUIResource(0xC49000);
    public static final Color DARK_RED = new ColorUIResource(0x9A0007);
    public static final Color DARK_ORANGE = new ColorUIResource(0xB53D00);
    public static final Color DARK_BROWN = new ColorUIResource(0x6D4C41);
    public static final Color DARK_PINK = new ColorUIResource(0xA00037);
  }

  public enum Separator {
    DEFAULT;

    @NonNls
    private static final String SEPARATOR_SEPARATOR_COLOR = "Separator.separatorColor";

    public static Color getSeparatorColor() {
      return JBColor.namedColor(SEPARATOR_SEPARATOR_COLOR, new JBColor(0xcdcdcd, 0x515151));
    }
  }

  public enum Radio {
    GAGA;

    @NonNls
    private static final String RADIO_BUTTON_SELECTION_ENABLED_COLOR = "RadioButton.selectionEnabledShadowColor";
    @NonNls
    private static final String RADIO_BUTTON_SELECTION_DISABLED_COLOR = "RadioButton.selectionDisabledShadowColor";
    @NonNls
    private static final String RADIO_BUTTON_BORDER_COLOR = "RadioButton.darcula.borderColor1";
    @NonNls
    private static final String RADIO_BUTTON_FOCUS_COLOR = "RadioButton.focusColor";

    public static Color getSelectedColor(final boolean enabled) {
      final JBColor enabledColor = JBColor.namedColor(RADIO_BUTTON_SELECTION_ENABLED_COLOR, new JBColor(0x00000040, 0x1e1e1e));
      final JBColor disabledColor = JBColor.namedColor(RADIO_BUTTON_SELECTION_DISABLED_COLOR, new JBColor(0x00000020, 0x3c3c3c));

      return enabled ? enabledColor : disabledColor;
    }

    public static Color getBorderColor() {
      return JBColor.namedColor(RADIO_BUTTON_BORDER_COLOR, new JBColor(0xcdcdcd, 0x515151));
    }

    public static Color getFocusColor() {
      final Color color = JBColor.namedColor(RADIO_BUTTON_FOCUS_COLOR, new JBColor(0xcfcfcf, 0xaaaaaa));

      return ColorUtil.withAlpha(color, 0.5);
    }
  }

  public enum ProgressBar {
    CHOCO;

    private static final String PROGRESS_BAR_TRACK_COLOR = "ProgressBar.trackColor";
    private static final String PROGRESS_BAR_PROGRESS_COLOR = "ProgressBar.progressColor";
    private static final String PROGRESS_BAR_INDETERMINATE_START_COLOR = "ProgressBar.indeterminateStartColor";
    private static final String PROGRESS_BAR_INDETERMINATE_END_COLOR = "ProgressBar.indeterminateEndColor";

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
    NINTENDO;

    private static final String OFF_THUMB_COLOR = "ToggleButton.offForeground";
    private static final String ON_THUMB_COLOR = "ToggleButton.onForeground";
    private static final String OFF_BACKGROUND_COLOR = "ToggleButton.offBackground";
    private static final String ON_BACKGROUND_COLOR = "ToggleButton.onBackground";

    public static Color getOffThumbColor() {
      return JBColor.namedColor(OFF_THUMB_COLOR, Gray.x77).brighter().brighter();
    }

    public static Color getOnThumbColor() {
      return JBColor.namedColor(ON_THUMB_COLOR, Gray.xFF);
    }

    @NotNull
    public static Color getOffSwitchColor() {
      return JBColor.namedColor(OFF_BACKGROUND_COLOR, new JBColor(0xf2f2f2, 0x3c3f41));
    }

    public static Color getOnSwitchColor() {
      return JBColor.namedColor(ON_BACKGROUND_COLOR, new JBColor(0x4a9249, 0x4d694c)).darker().darker();
    }
  }

  public enum NavBar {
    ALLAH;

    private static final String NAVBAR_ARROW_COLOR = "NavBar.arrowColor";
    private static final String NAVBAR_HIGHLIGHT_COLOR = "NavBar.selectedColor";

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
    CCCCOMBO;

    private static final String COMBO_BOX_ARROW_BUTTON_NON_EDITABLE_BACKGROUND = "ComboBox.ArrowButton.nonEditableBackground";
    private static final String COMBO_BOX_NON_EDITABLE_BACKGROUND = "ComboBox.nonEditableBackground";
    private static final String COMBO_BOX_DISABLED_FOREGROUND = "ComboBox.disabledForeground";
    private static final String TEXT_FIELD_BACKGROUND = "TextField.background";

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
      final Color color = JBColor.namedColor(COMBO_BOX_ARROW_BUTTON_NON_EDITABLE_BACKGROUND, new JBColor(0xffffff, 0x3c3f41));
      return enabled ? color : UIUtil.getPanelBackground();
    }

    public static Color getNonEditableBackground() {
      return JBColor.namedColor(COMBO_BOX_NON_EDITABLE_BACKGROUND, new JBColor(0xffffff, 0x3c3f41));
    }

    public static Color getDisabledForeground() {
      return JBColor.namedColor(COMBO_BOX_DISABLED_FOREGROUND, new JBColor(0xb1b1b1, 0xb1b1b1));
    }

    public static Color getFallbackBackground() {
      return JBColor.namedColor(Button.BUTTON_BACKGROUND, new JBColor(0xf2f2f2, 0x3c3f41));
    }

    public static Color getDisabledBackground() {
      return JBColor.namedColor(Button.BUTTON_BACKGROUND, new JBColor(0xf2f2f2, 0x3c3f41));
    }

    public static Color getEnabledBackground() {
      return JBColor.namedColor(TEXT_FIELD_BACKGROUND, new JBColor(0xffffff, 0x45494A));
    }
  }

  public enum CheckBox {
    CHECK1212;

    private static Color getColor(@NonNls final String shortPropertyName, @NotNull final Color defaultValue) {
      return JBColor.namedColor("CheckBox.darcula." + shortPropertyName, defaultValue);
    }

    private static Color getColor(@NonNls final String shortPropertyName, @NotNull final Color defaultValue, final boolean selected) {
      if (selected) {
        return getColor(shortPropertyName + ".selected", defaultValue);
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

  public enum Label {
    JOHNNY_WALKER;

    private static final String LABEL_DISABLED_FOREGROUND = "Label.disabledForeground";
    private static final String LABEL_GRAY_FOREGROUND = "Label.grayForeground";
    private static final String LABEL_GRAY_DISABLED_FOREGROUND = "Label.disabledForeground";
    private static final String LABEL_SELECTED_FOREGROUND = "Label.selectedForeground";

    public static Color getLabelForeground() {
      return JBColor.namedColor(LABEL_GRAY_FOREGROUND, new JBColor(0x777777, 0x787878));
    }

    public static Color getLabelDisabledForeground() {
      return JBColor.namedColor(LABEL_GRAY_DISABLED_FOREGROUND, new JBColor(0x777777, 0x787878));
    }

    public static Color getLabelForeground(final JLabel label) {
      Color foreground = label.getForeground();
      if (foreground == Gray.x78 || foreground == Gray.x80) {
        foreground = JBColor.namedColor(LABEL_GRAY_FOREGROUND, new JBColor(0x777777, 0x787878));
      }
      return foreground;
    }

    public static Color getDisabledBackground() {
      return JBColor.namedColor(LABEL_DISABLED_FOREGROUND, new JBColor(0x000000, 0xBBBBBB));
    }

    public static void paintText(final JLabel label, final Graphics g, final String s, final int textX, final int textY) {
      final int mnemIndex = DarculaLaf.isAltPressed() ? label.getDisplayedMnemonicIndex() : -1;
      SwingUtilities2.drawStringUnderlineCharAt(label, g, s, mnemIndex, textX, textY);
    }

    public static Color getSelectedForeground() {
      return JBColor.namedColor(LABEL_SELECTED_FOREGROUND, new JBColor(0x11111, 0xFFFFFF));
    }
  }

  public enum Panel {
    DE_PON;

    public static final String PANEL_BACKGROUND = "Panel.background";
    public static final String CONTRAST_BACKGROUND = "EditorPane.background";
    public static final String SECONDARY_BACKGROUND = "List.background";
    public static final String HIGHLIGHT_BACKGROUND = "Component.focusedBorderColor";
    public static final String LINK_FOREGROUND = "Link.foreground";

    public static Color getBackground() {
      return JBColor.namedColor(PANEL_BACKGROUND, UIUtil.getPanelBackground());
    }

    public static Color getContrastBackground() {
      return JBColor.namedColor(CONTRAST_BACKGROUND, UIUtil.getEditorPaneBackground());
    }

    public static Color getSecondaryBackground() {
      return JBColor.namedColor(SECONDARY_BACKGROUND, UIUtil.getListBackground());
    }

    public static Color getHighlightBackground() {
      return JBColor.namedColor(HIGHLIGHT_BACKGROUND, DarculaUIUtil.getOutlineColor(true, true));
    }

    public static Color getTransparentBackground() {
      return ColorUtil.withAlpha(JBColor.namedColor(PANEL_BACKGROUND, UIUtil.getPanelBackground()), 0.3);
    }

    public static Color getLinkForeground() {
      return JBColor.namedColor(LINK_FOREGROUND, JBColor.blue);
    }
  }
}
