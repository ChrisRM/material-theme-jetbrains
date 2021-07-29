/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015-2021 Elior "Mallowigi" Boukhobza
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
import com.intellij.history.integration.ui.views.RevisionsList;
import com.intellij.ide.actions.Switcher;
import com.intellij.ide.bookmarks.actions.MnemonicChooser;
import com.intellij.ide.navigationToolbar.ui.NavBarUIManager;
import com.intellij.ide.plugins.PluginManagerCore;
import com.intellij.openapi.actionSystem.ex.ActionButtonLook;
import com.intellij.openapi.extensions.PluginId;
import com.intellij.openapi.keymap.impl.ui.MouseShortcutPanel;
import com.intellij.openapi.roots.ui.configuration.JavaModuleSourceRootEditHandler;
import com.intellij.openapi.roots.ui.configuration.JavaTestSourceRootEditHandler;
import com.intellij.ui.*;
import com.intellij.ui.colorpicker.ColorPickerBuilderKt;
import com.intellij.ui.tabs.FileColorManagerImpl;
import com.intellij.ui.tabs.impl.SingleHeightTabs;
import com.intellij.util.PlatformUtils;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.ui.PlatformColors;
import com.intellij.util.ui.UIUtil;
import com.mallowigi.idea.config.application.MTConfig;
import com.mallowigi.idea.ui.MTActionButtonLook;
import com.mallowigi.idea.ui.MTNavBarUI;
import com.mallowigi.idea.utils.MTUI;
import com.mallowigi.idea.utils.MTUiUtils;
import com.mallowigi.idea.utils.StaticPatcher;
import training.ui.UISettings;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

@SuppressWarnings({"FeatureEnvy",
  "MagicNumber",
  "DuplicateStringLiteralInspection",
  "KotlinInternalInJava"})
public enum UIReplacer {
  DEFAULT;

  public static void patchUI() {
    try {
      patchCompletionPopup();
      patchTabs();
      patchGrays();
      patchNavBar();
      patchIdeaActionButton();
      patchOnMouseOver();
      patchAndroid();
      patchKotlin();
      patchAttributes();
      patchKeymap();
      patchDebugWindow();
      patchBookmarks();
      patchJavaModules();
      patchColors();
      patchColorPicker();
      patchScopes();

      if (PluginManagerCore.isPluginInstalled(PluginId.getId("training"))) {
        patchLearner();
      }

      if (!"CodeWithMeGuest" .equals(PlatformUtils.getPlatformPrefix())) {
        patchLocalHistory();
      }
    } catch (final IllegalAccessException | NoSuchFieldException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  private static void patchLearner() throws NoSuchFieldException, IllegalAccessException {
    try {
      final Class<?> uiSettings = Class.forName("training.ui.UISettings");
      final JBColor border = new JBColor(MTUI.Separator.getSeparatorColor(), MTUI.Separator.getSeparatorColor());
      final Field[] fields = uiSettings.getDeclaredFields();
      final Stream<Field> fieldStream = Arrays.stream(fields).filter(field -> field.getType().equals(Color.class));

      StaticPatcher.setFinal(UISettings.Companion.getInstance(), MTUiUtils.findField(fieldStream, "separatorColor"), border);
    } catch (final Exception e) {
      // do nothing, plugin is absent
    }
  }

  private static void patchBookmarks() throws NoSuchFieldException, IllegalAccessException {
    StaticPatcher.setFinalStatic(MnemonicChooser.class, "OCCUPIED_CELL_COLOR", MTUI.Button.getSelectedBackgroundColor());
    StaticPatcher.setFinalStatic(MnemonicChooser.class, "FREE_CELL_COLOR", MTUI.Button.getBackgroundColor());
  }

  private static void patchJavaModules() throws NoSuchFieldException, IllegalAccessException {
    StaticPatcher.setFinalStatic(JavaModuleSourceRootEditHandler.class, "SOURCES_COLOR", MTUI.MTColor.BLUE);
    StaticPatcher.setFinalStatic(JavaTestSourceRootEditHandler.class, "TESTS_COLOR", MTUI.MTColor.GREEN);
  }

  private static void patchColorPicker() throws NoSuchFieldException, IllegalAccessException {
    StaticPatcher.setFinalStatic(ColorPickerBuilderKt.class, "PICKER_BACKGROUND_COLOR", MTUI.Panel.getSecondaryBackground());
    StaticPatcher.setFinalStatic(ColorPickerBuilderKt.class, "PICKER_TEXT_COLOR", MTUI.Panel.getForeground());
  }

  @SuppressWarnings("OverlyLongMethod")
  private static void patchColors() throws NoSuchFieldException, IllegalAccessException {
    //    StaticPatcher.setFinalStatic(JBColor.class, "red", new JBColor(MTUI.MTColor.RED, MTUI.MTColor.DARK_RED));
    //    StaticPatcher.setFinalStatic(JBColor.class, "RED", new JBColor(MTUI.MTColor.RED, MTUI.MTColor.DARK_RED));
    StaticPatcher.setFinalStatic(JBColor.class, "blue", MTUI.Panel.getAccentColor());
    StaticPatcher.setFinalStatic(JBColor.class, "BLUE", MTUI.Panel.getAccentColor());
    StaticPatcher.setFinalStatic(JBColor.class, "orange", new JBColor(MTUI.MTColor.ORANGE, MTUI.MTColor.DARK_ORANGE));
    StaticPatcher.setFinalStatic(JBColor.class, "ORANGE", new JBColor(MTUI.MTColor.ORANGE, MTUI.MTColor.DARK_ORANGE));
    StaticPatcher.setFinalStatic(JBColor.class, "pink", new JBColor(MTUI.MTColor.PINK, MTUI.MTColor.DARK_PINK));
    StaticPatcher.setFinalStatic(JBColor.class, "PINK", new JBColor(MTUI.MTColor.PINK, MTUI.MTColor.DARK_PINK));
    StaticPatcher.setFinalStatic(JBColor.class, "yellow", new JBColor(MTUI.MTColor.YELLOW, MTUI.MTColor.DARK_YELLOW));
    StaticPatcher.setFinalStatic(JBColor.class, "YELLOW", new JBColor(MTUI.MTColor.YELLOW, MTUI.MTColor.DARK_YELLOW));
    StaticPatcher.setFinalStatic(JBColor.class, "green", new JBColor(MTUI.MTColor.GREEN, MTUI.MTColor.DARK_GREEN));
    StaticPatcher.setFinalStatic(JBColor.class, "GREEN", new JBColor(MTUI.MTColor.GREEN, MTUI.MTColor.DARK_GREEN));
    StaticPatcher.setFinalStatic(JBColor.class, "magenta", new JBColor(MTUI.MTColor.PURPLE, MTUI.MTColor.DARK_PURPLE));
    StaticPatcher.setFinalStatic(JBColor.class, "MAGENTA", new JBColor(MTUI.MTColor.PURPLE, MTUI.MTColor.DARK_PURPLE));
    StaticPatcher.setFinalStatic(JBColor.class, "cyan", new JBColor(MTUI.MTColor.CYAN, MTUI.MTColor.DARK_CYAN));
    StaticPatcher.setFinalStatic(JBColor.class, "CYAN", new JBColor(MTUI.MTColor.CYAN, MTUI.MTColor.DARK_CYAN));

    StaticPatcher.setFinalStatic(JBColor.class, "white", MTUI.Panel.getBackground());
    StaticPatcher.setFinalStatic(JBColor.class, "WHITE", MTUI.Panel.getBackground());
    StaticPatcher.setFinalStatic(JBColor.class, "black", MTUI.Panel.getForeground());
    StaticPatcher.setFinalStatic(JBColor.class, "BLACK", MTUI.Panel.getForeground());
    StaticPatcher.setFinalStatic(JBColor.class, "gray", MTUI.Panel.getPrimaryForeground());
    StaticPatcher.setFinalStatic(JBColor.class, "GRAY", MTUI.Panel.getPrimaryForeground());
    StaticPatcher.setFinalStatic(JBColor.class, "lightGray", MTUI.Separator.getSeparatorColor());
    StaticPatcher.setFinalStatic(JBColor.class, "LIGHT_GRAY", MTUI.Separator.getSeparatorColor());
    StaticPatcher.setFinalStatic(JBColor.class, "darkGray", MTUI.Separator.getSeparatorColor());
    StaticPatcher.setFinalStatic(JBColor.class, "DARK_GRAY", MTUI.Separator.getSeparatorColor());

    StaticPatcher.setFinalStatic(DarculaColors.class, "BLUE", MTUI.Panel.getAccentColor());
    StaticPatcher.setFinalStatic(DarculaColors.class, "RED", MTUI.Panel.getAccentColor());
    StaticPatcher.setFinalStatic(PlatformColors.class, "BLUE", MTUI.Panel.getAccentColor());

    StaticPatcher.setFinalStatic(LightColors.class, "BLUE", new JBColor(MTUI.MTColor.BLUE, MTUI.MTColor.DARK_BLUE));
    StaticPatcher.setFinalStatic(LightColors.class, "RED", new JBColor(MTUI.MTColor.RED, MTUI.MTColor.DARK_RED));
    StaticPatcher.setFinalStatic(LightColors.class, "YELLOW", new JBColor(MTUI.MTColor.YELLOW, MTUI.MTColor.DARK_YELLOW));
    StaticPatcher.setFinalStatic(LightColors.class, "GREEN", new JBColor(MTUI.MTColor.GREEN, MTUI.MTColor.DARK_GREEN));
    StaticPatcher.setFinalStatic(LightColors.class, "CYAN", new JBColor(MTUI.MTColor.CYAN, MTUI.MTColor.DARK_CYAN));

    // comment
    final int abbbb = 10;

  }

  private static void patchOnMouseOver() throws NoSuchFieldException, IllegalAccessException {
    StaticPatcher.setFinalStatic(Switcher.class, "ON_MOUSE_OVER_BG_COLOR", UIUtil.getListSelectionBackground(true));
  }

  private static void patchLocalHistory() throws NoSuchFieldException, IllegalAccessException {
    StaticPatcher.setFinalStatic(RevisionsList.MyCellRenderer.class, "USER_LABEL_COLOR", MTUI.Panel.getAccentColor());
  }

  private static void patchKeymap() throws NoSuchFieldException, IllegalAccessException {
    StaticPatcher.setFinalStatic(MouseShortcutPanel.class, "BACKGROUND", MTUI.Panel.getSecondaryBackground());
    StaticPatcher.setFinalStatic(MouseShortcutPanel.class, "BORDER", MTUI.Panel.getSecondaryBackground());
    StaticPatcher.setFinalStatic(MouseShortcutPanel.class, "FOREGROUND", MTUI.Panel.getForeground());
  }

  private static void patchDebugWindow() throws NoSuchFieldException, IllegalAccessException {
    StaticPatcher.setFinalStatic(CaptionPanel.class, "CNT_ACTIVE_BORDER_COLOR", MTUI.Panel.getBackground());
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
      StaticPatcher.setFinalStatic(studioColors, "primaryPanelBackground", new JBColor(contrastBackground, contrastBackground));
      StaticPatcher.setFinalStatic(studioColors, "secondaryPanelBackground", panelBackground);
      StaticPatcher.setFinalStatic(studioColors, "border", panelBackground);
      StaticPatcher.setFinalStatic(studioColors, "borderLight", secondaryBackground);
    } catch (final ClassNotFoundException e) {
      //      e.printStackTrace();
    }
  }

  private static void patchKotlin() throws NoSuchFieldException, IllegalAccessException {
    final Color highlightBackground = JBColor.namedColor("ParameterInfo.currentOverloadBackground",
      UIUtil.getListSelectionBackground(false));

    try {
      final Class<?> kotlinParamInfo = Class.forName("org.jetbrains.kotlin.idea.parameterInfo.KotlinParameterInfoWithCallHandlerBase");
      final JBColor color = new JBColor(highlightBackground, highlightBackground);

      final Field[] fields = kotlinParamInfo.getDeclaredFields();
      final Object[] objects = Arrays.stream(fields)
                                     .filter(field -> field.getType().equals(Color.class))
                                     .toArray();

      StaticPatcher.setFinalStatic((Field) objects[0], color);

      //      StaticPatcher.setFinalStatic(kotlinParamInfo, "GREEN_BACKGROUND", color);
    } catch (final ClassNotFoundException e) {
      //      e.printStackTrace();
    }
  }

  /**
   * Very clever way to theme excluded files color
   */
  private static void patchScopes() throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException {
    final Color excludedColor = MTConfig.getInstance().getSelectedTheme().getTheme().getExcludedColor();

    // Do not replace file colors on native themes
    if (MTConfig.getInstance().getSelectedTheme().isNative()) {
      return;
    }

    // Colors for the scope editor
    StaticPatcher.setFinalStatic(Class.forName("com.intellij.ide.util.scopeChooser.ScopeEditorPanel$MyTreeCellRenderer"),
      "WHOLE_INCLUDED", MTUI.MTColor.BLUE);
    StaticPatcher.setFinalStatic(Class.forName("com.intellij.ide.util.scopeChooser.ScopeEditorPanel$MyTreeCellRenderer"),
      "PARTIAL_INCLUDED", MTUI.MTColor.ORANGE);

    final Map<String, Color> ourDefaultColors = ContainerUtil.<String, Color>immutableMapBuilder()
                                                             .put("Sea", UIManager.getColor("FileColor.Blue")) //NON-NLS
                                                             .put("Forest", UIManager.getColor("FileColor.Green"))//NON-NLS
                                                             .put("Spice", UIManager.getColor("FileColor.Orange"))//NON-NLS
                                                             .put("Crimson", UIManager.getColor("FileColor.Rose"))//NON-NLS
                                                             .put("DeepPurple", UIManager.getColor("FileColor.Violet"))//NON-NLS
                                                             .put("Amber", UIManager.getColor("FileColor.Yellow"))//NON-NLS
                                                             .put("Theme Excluded Color", excludedColor)//NON-NLS
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
