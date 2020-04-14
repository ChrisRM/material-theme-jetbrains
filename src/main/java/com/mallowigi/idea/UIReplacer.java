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

package com.mallowigi.idea;

import com.intellij.codeInsight.lookup.impl.LookupCellRenderer;
import com.intellij.ide.actions.Switcher;
import com.intellij.ide.navigationToolbar.ui.NavBarUIManager;
import com.intellij.openapi.actionSystem.ex.ActionButtonLook;
import com.intellij.openapi.keymap.impl.ui.MouseShortcutPanel;
import com.intellij.ui.Gray;
import com.intellij.ui.JBColor;
import com.intellij.ui.SimpleTextAttributes;
import com.intellij.ui.tabs.FileColorManagerImpl;
import com.intellij.ui.tabs.TabsUtil;
import com.intellij.ui.tabs.UiDecorator;
import com.intellij.ui.tabs.impl.JBTabsImpl;
import com.intellij.ui.tabs.impl.SingleHeightTabs;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.JBValue;
import com.intellij.util.ui.UIUtil;
import com.mallowigi.idea.ui.MTActionButtonLook;
import com.mallowigi.idea.ui.MTNavBarUI;
import com.mallowigi.idea.utils.MTUI;
import com.mallowigi.idea.utils.StaticPatcher;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;

@SuppressWarnings({"FeatureEnvy",
  "MagicNumber",
  "DuplicateStringLiteralInspection"})
public enum UIReplacer {
  DEFAULT;

  public static void patchUI() {
    try {
      patchCompletionPopup();
      patchTabs();
      patchGrays();
      patchScopes();
      patchNavBar();
      patchIdeaActionButton();
      patchOnMouseOver();
      patchAndroid();
      patchAttributes();
      patchKeymap();
    } catch (final IllegalAccessException | NoSuchFieldException e) {
      e.printStackTrace();
    }
  }

  private static void patchOnMouseOver() throws NoSuchFieldException, IllegalAccessException {
    StaticPatcher.setFinalStatic(Switcher.class, "ON_MOUSE_OVER_BG_COLOR", UIUtil.getListSelectionBackground(true));
  }

  private static void patchKeymap() throws NoSuchFieldException, IllegalAccessException {
    StaticPatcher.setFinalStatic(MouseShortcutPanel.class, "BACKGROUND", MTUI.Panel.getSecondaryBackground());
    StaticPatcher.setFinalStatic(MouseShortcutPanel.class, "BORDER", MTUI.Panel.getSecondaryBackground());
    StaticPatcher.setFinalStatic(MouseShortcutPanel.class, "FOREGROUND", MTUI.Panel.getForeground());
  }

  private static void patchGrays() throws NoSuchFieldException, IllegalAccessException {
    // Replace Gray with a clear and transparent color
    final Gray gray = Gray._85;
    final Color alphaGray = gray.withAlpha(1);
    StaticPatcher.setFinalStatic(Gray.class, "_85", alphaGray);
    StaticPatcher.setFinalStatic(Gray.class, "_40", alphaGray);
    StaticPatcher.setFinalStatic(Gray.class, "_145", alphaGray);
    StaticPatcher.setFinalStatic(Gray.class, "_201", alphaGray);

    // Quick info border
    StaticPatcher.setFinalStatic(Gray.class, "_90", gray.withAlpha(25));

    // tool window color
    final boolean dark = MTConfig.getInstance().getSelectedTheme().isDark();
    StaticPatcher.setFinalStatic(Gray.class, "_15", dark ? Gray._15.withAlpha(255) : Gray._200.withAlpha(15));
  }

  private static void patchAndroid() throws NoSuchFieldException, IllegalAccessException {
    final Color panelBackground = MTUI.Panel.getBackground();
    final Color contrastBackground = MTUI.Panel.getContrastBackground();
    final Color secondaryBackground = MTUI.Panel.getSecondaryBackground();
    final Color highlightBackground = MTUI.Panel.getHighlightBackground();

    try {
      final Class<?> uiUtils = Class.forName("com.android.tools.idea.assistant.view.UIUtils");
      StaticPatcher.setFinalStatic(uiUtils, "AS_STANDARD_BACKGROUND_COLOR", panelBackground);
      StaticPatcher.setFinalStatic(uiUtils, "BACKGROUND_COLOR", panelBackground);
      StaticPatcher.setFinalStatic(uiUtils, "SECONDARY_COLOR", secondaryBackground);

      final Class<?> wizardConstants = Class.forName("com.android.tools.idea.wizard.WizardConstants");
      StaticPatcher.setFinalStatic(wizardConstants, "ANDROID_NPW_HEADER_COLOR", panelBackground);

      final Class<?> navColorSet = Class.forName("com.android.tools.idea.naveditor.scene.NavColorSet");
      StaticPatcher.setFinalStatic(navColorSet, "BACKGROUND_COLOR", contrastBackground);
      StaticPatcher.setFinalStatic(navColorSet, "FRAME_COLOR", contrastBackground);
      StaticPatcher.setFinalStatic(navColorSet, "HIGHLIGHTED_FRAME_COLOR", highlightBackground);
      StaticPatcher.setFinalStatic(navColorSet, "SUBDUED_FRAME_COLOR", highlightBackground);
      StaticPatcher.setFinalStatic(navColorSet, "SUBDUED_BACKGROUND_COLOR", panelBackground);
      StaticPatcher.setFinalStatic(navColorSet, "COMPONENT_BACKGROUND_COLOR", secondaryBackground);
      StaticPatcher.setFinalStatic(navColorSet, "LIST_MOUSEOVER_COLOR", secondaryBackground);
      StaticPatcher.setFinalStatic(navColorSet, "PLACEHOLDER_BACKGROUND_COLOR", secondaryBackground);

      final Class<?> studioColors = Class.forName("com.android.tools.adtui.common.StudioColorsKt");
      StaticPatcher.setFinalStatic(studioColors, "primaryPanelBackground", contrastBackground);
      StaticPatcher.setFinalStatic(studioColors, "secondaryPanelBackground", panelBackground);
      StaticPatcher.setFinalStatic(studioColors, "border", panelBackground);
      StaticPatcher.setFinalStatic(studioColors, "borderLight", secondaryBackground);
    } catch (final ClassNotFoundException e) {
      //      e.printStackTrace();
    }
  }

  /**
   * Very clever way to theme excluded files color
   */
  private static void patchScopes() throws NoSuchFieldException, IllegalAccessException {
    final Color disabledColor = MTConfig.getInstance().getSelectedTheme().getTheme().getExcludedColor();

    final Map<String, Color> ourDefaultColors = ContainerUtil.<String, Color>immutableMapBuilder()
      .put("Sea", UIManager.getColor("FileColor.Blue")) //NON-NLS
      .put("Forest", UIManager.getColor("FileColor.Green"))//NON-NLS
      .put("Spice", UIManager.getColor("FileColor.Orange"))//NON-NLS
      .put("Crimson", UIManager.getColor("FileColor.Rose"))//NON-NLS
      .put("DeepPurple", UIManager.getColor("FileColor.Violet"))//NON-NLS
      .put("Amber", UIManager.getColor("FileColor.Yellow"))//NON-NLS
      .put("Theme", disabledColor)//NON-NLS
      .build();

    final Field[] fields = FileColorManagerImpl.class.getDeclaredFields();
    final Object[] objects = Arrays.stream(fields)
                                   .filter(field -> field.getType().equals(Map.class))
                                   .toArray();

    StaticPatcher.setFinalStatic((Field) objects[0], ourDefaultColors);
  }

  /**
   * Replace NavBar with MTNavBar
   */
  private static void patchNavBar() throws NoSuchFieldException, IllegalAccessException {
    StaticPatcher.setFinalStatic(NavBarUIManager.class, "DARCULA", new MTNavBarUI());
    StaticPatcher.setFinalStatic(NavBarUIManager.class, "COMMON", new MTNavBarUI());
  }

  /**
   * Replace IdeaActionButton with MTIdeaActionButton
   */
  private static void patchIdeaActionButton() throws NoSuchFieldException, IllegalAccessException {
    StaticPatcher.setFinalStatic(ActionButtonLook.class, "SYSTEM_LOOK", new MTActionButtonLook());
  }

  /**
   * New implementation for tabs height
   */
  private static void patchTabs() throws NoSuchFieldException, IllegalAccessException {
    final int tabsHeight = MTConfig.getInstance().getTabsHeight() + 10;
    StaticPatcher.setFinalStatic(SingleHeightTabs.class, "UNSCALED_PREF_HEIGHT", tabsHeight);
    UIManager.put("TabbedPane.tabHeight", tabsHeight);
  }

  /**
   * New implementation for tabs height
   */
  private static void patchTabsOld() throws NoSuchFieldException, IllegalAccessException {
    final int baseHeight = 9;
    final int tabsHeight = MTConfig.getInstance().getTabsHeight() / 2 - baseHeight;
    StaticPatcher.setFinalStatic(TabsUtil.class, "TAB_VERTICAL_PADDING", new JBValue.Float(tabsHeight));
    StaticPatcher.setFinalStatic(TabsUtil.class, "NEW_TAB_VERTICAL_PADDING", tabsHeight);

    StaticPatcher.setFinalStatic(JBTabsImpl.class, "ourDefaultDecorator",
      (UiDecorator) () -> new UiDecorator.UiDecoration(null,
        JBUI.insets(-1 * TabsUtil.NEW_TAB_VERTICAL_PADDING, 8)));
  }

  /**
   * Patch the Completion Popup background to match the currently selected
   * theme.
   */
  @SuppressWarnings("HardCodedStringLiteral")
  static void patchCompletionPopup() {
    final Color autoCompleteBackground = MTUI.Panel.getSecondaryBackground();
    try {
      final Field backgroundColorField = LookupCellRenderer.class.getDeclaredField("BACKGROUND_COLOR");
      StaticPatcher.setFinalStatic(backgroundColorField, autoCompleteBackground);
    } catch (final NoSuchFieldException | IllegalAccessException e) {
      System.err.println("Unable to patch completion popup: " + e.getLocalizedMessage());
    }
  }

  static void patchAttributes() {
    try {
      StaticPatcher.setFinalStatic(JBColor.class, "GRAY", MTUI.Label.getLabelInfoForeground());
      StaticPatcher.setFinalStatic(JBColor.class, "LIGHT_GRAY", MTUI.Label.getSelectedForeground());
      StaticPatcher.setFinalStatic(JBColor.class, "DARK_GRAY", MTUI.Label.getLabelDisabledForeground());

      StaticPatcher.setFinalStatic(SimpleTextAttributes.class, "DARK_TEXT", new SimpleTextAttributes(
        SimpleTextAttributes.STYLE_PLAIN,
        MTUI.Label.getLabelDisabledForeground()));

      StaticPatcher.setFinalStatic(SimpleTextAttributes.class, "SIMPLE_CELL_ATTRIBUTES", new SimpleTextAttributes(
        SimpleTextAttributes.STYLE_PLAIN,
        MTUI.Label.getLabelInfoForeground()));

      StaticPatcher.setFinalStatic(SimpleTextAttributes.class, "EXCLUDED_ATTRIBUTES", new SimpleTextAttributes(
        SimpleTextAttributes.STYLE_PLAIN,
        MTUI.Label.getLabelDisabledForeground()));

      StaticPatcher.setFinalStatic(SimpleTextAttributes.class, "GRAY_ATTRIBUTES", new SimpleTextAttributes(
        SimpleTextAttributes.STYLE_PLAIN,
        MTUI.Label.getLabelInfoForeground()));
      StaticPatcher.setFinalStatic(SimpleTextAttributes.class, "GRAY_SMALL_ATTRIBUTES", new SimpleTextAttributes(
        SimpleTextAttributes.STYLE_SMALLER,
        MTUI.Label.getLabelInfoForeground()));
      StaticPatcher.setFinalStatic(SimpleTextAttributes.class, "GRAY_ITALIC_ATTRIBUTES", new SimpleTextAttributes(
        SimpleTextAttributes.STYLE_ITALIC,
        MTUI.Label.getLabelInfoForeground()));

      StaticPatcher.setFinalStatic(SimpleTextAttributes.class, "SYNTHETIC_ATTRIBUTES",
        new SimpleTextAttributes(
          SimpleTextAttributes.STYLE_PLAIN,
          MTUI.Panel.getLinkForeground()
        )
      );
    } catch (final NoSuchFieldException | IllegalAccessException e) {
      e.printStackTrace();
    }
  }
}
