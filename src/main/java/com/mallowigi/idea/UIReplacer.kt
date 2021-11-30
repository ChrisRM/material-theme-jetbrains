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
@file:Suppress("HardCodedStringLiteral", "SpellCheckingInspection")

package com.mallowigi.idea

import com.intellij.codeInsight.lookup.impl.LookupCellRenderer
import com.intellij.history.integration.ui.views.RevisionsList
import com.intellij.ide.navigationToolbar.ui.NavBarUIManager
import com.intellij.ide.plugins.PluginManagerCore
import com.intellij.ide.ui.UISettings
import com.intellij.openapi.actionSystem.ex.ActionButtonLook
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.extensions.PluginId
import com.intellij.openapi.roots.ui.configuration.JavaModuleSourceRootEditHandler
import com.intellij.openapi.roots.ui.configuration.JavaTestSourceRootEditHandler
import com.intellij.ui.CaptionPanel
import com.intellij.ui.DarculaColors
import com.intellij.ui.Gray
import com.intellij.ui.JBColor
import com.intellij.ui.LightColors
import com.intellij.ui.SimpleTextAttributes
import com.intellij.ui.colorpicker.ColorPickerBuilder
import com.intellij.ui.tabs.FileColorManagerImpl
import com.intellij.ui.tabs.impl.SingleHeightTabs
import com.intellij.util.PlatformUtils
import com.intellij.util.containers.ContainerUtil
import com.intellij.util.ui.PlatformColors
import com.intellij.util.ui.UIUtil
import com.mallowigi.idea.config.application.MTConfig
import com.mallowigi.idea.ui.MTActionButtonLook
import com.mallowigi.idea.ui.MTNavBarUI
import com.mallowigi.idea.utils.MTUI
import com.mallowigi.idea.utils.MTUI.Label.labelDisabledForeground
import com.mallowigi.idea.utils.MTUI.Label.labelInfoForeground
import com.mallowigi.idea.utils.MTUI.Label.selectedForeground
import com.mallowigi.idea.utils.MTUI.Panel.accentColor
import com.mallowigi.idea.utils.MTUI.Panel.background
import com.mallowigi.idea.utils.MTUI.Panel.contrastBackground
import com.mallowigi.idea.utils.MTUI.Panel.foreground
import com.mallowigi.idea.utils.MTUI.Panel.highlightBackground
import com.mallowigi.idea.utils.MTUI.Panel.linkForeground
import com.mallowigi.idea.utils.MTUI.Panel.primaryForeground
import com.mallowigi.idea.utils.MTUI.Panel.secondaryBackground
import com.mallowigi.idea.utils.MTUI.Separator.separatorColor
import com.mallowigi.idea.utils.MTUiUtils
import com.mallowigi.idea.utils.StaticPatcher.setFinal
import com.mallowigi.idea.utils.StaticPatcher.setFinalStatic
import java.awt.Color
import java.lang.reflect.Field
import java.util.Arrays
import javax.swing.UIManager

private const val TRAINING_PLUGIN = "training"

private const val TAB_PADDING = 10

/**
 * Replace static UI elements with custom ones.
 *
 */
object UIReplacer {

  /**
   * Main method
   */
  @Suppress("UnstableApiUsage")
  fun patchUI() {
    try {
      patchCompletionPopup()
      patchTabs()
      patchGrays()
      patchNavBar()
      patchIdeaActionButton()
      patchAndroid()
      patchKotlin()
      patchAttributes()
      patchDebugWindow()
      patchJavaModules()
      patchColors()
      patchColorPicker()
      patchScopes()

      if (PluginManagerCore.isPluginInstalled(PluginId.getId(TRAINING_PLUGIN))) {
        patchLearner()
      }

      if ("CodeWithMeGuest" != PlatformUtils.getPlatformPrefix()) {
        patchLocalHistory()
      }

    } catch (e: Exception) {
      thisLogger().error(e)
    }
  }

  @Throws(NoSuchFieldException::class, IllegalAccessException::class)
  private fun patchLearner() {
    try {
      val uiSettings = Class.forName("training.ui.UISettings")
      val border = JBColor(separatorColor, separatorColor)
      val fields = uiSettings.declaredFields
      val fieldStream = Arrays.stream(fields).filter { field: Field -> field.type == Color::class.java }
      setFinal(UISettings.instance, MTUiUtils.findField(fieldStream, "separatorColor"), border)
    } catch (e: Exception) {
      // do nothing, plugin is absent
    }
  }

  @Throws(NoSuchFieldException::class, IllegalAccessException::class)
  private fun patchJavaModules() {
    setFinalStatic(JavaModuleSourceRootEditHandler::class.java, "SOURCES_COLOR", MTUI.MTColor.BLUE)
    setFinalStatic(JavaTestSourceRootEditHandler::class.java, "TESTS_COLOR", MTUI.MTColor.GREEN)
  }

  @Throws(NoSuchFieldException::class, IllegalAccessException::class)
  private fun patchColorPicker() {
    setFinalStatic(ColorPickerBuilder::class.java, "PICKER_BACKGROUND_COLOR", secondaryBackground)
    setFinalStatic(ColorPickerBuilder::class.java, "PICKER_TEXT_COLOR", foreground)
  }

  @Throws(NoSuchFieldException::class, IllegalAccessException::class)
  private fun patchColors() {
    setFinalStatic(JBColor::class.java, "red", JBColor(MTUI.MTColor.RED, MTUI.MTColor.DARK_RED))
    setFinalStatic(JBColor::class.java, "RED", JBColor(MTUI.MTColor.RED, MTUI.MTColor.DARK_RED))
    setFinalStatic(JBColor::class.java, "blue", accentColor)
    setFinalStatic(JBColor::class.java, "BLUE", accentColor)
    setFinalStatic(JBColor::class.java, "orange", JBColor(MTUI.MTColor.ORANGE, MTUI.MTColor.DARK_ORANGE))
    setFinalStatic(JBColor::class.java, "ORANGE", JBColor(MTUI.MTColor.ORANGE, MTUI.MTColor.DARK_ORANGE))
    setFinalStatic(JBColor::class.java, "pink", JBColor(MTUI.MTColor.PINK, MTUI.MTColor.DARK_PINK))
    setFinalStatic(JBColor::class.java, "PINK", JBColor(MTUI.MTColor.PINK, MTUI.MTColor.DARK_PINK))
    setFinalStatic(JBColor::class.java, "yellow", JBColor(MTUI.MTColor.YELLOW, MTUI.MTColor.DARK_YELLOW))
    setFinalStatic(JBColor::class.java, "YELLOW", JBColor(MTUI.MTColor.YELLOW, MTUI.MTColor.DARK_YELLOW))
    setFinalStatic(JBColor::class.java, "green", JBColor(MTUI.MTColor.GREEN, MTUI.MTColor.DARK_GREEN))
    setFinalStatic(JBColor::class.java, "GREEN", JBColor(MTUI.MTColor.GREEN, MTUI.MTColor.DARK_GREEN))
    setFinalStatic(JBColor::class.java, "magenta", JBColor(MTUI.MTColor.PURPLE, MTUI.MTColor.DARK_PURPLE))
    setFinalStatic(JBColor::class.java, "MAGENTA", JBColor(MTUI.MTColor.PURPLE, MTUI.MTColor.DARK_PURPLE))
    setFinalStatic(JBColor::class.java, "cyan", JBColor(MTUI.MTColor.CYAN, MTUI.MTColor.DARK_CYAN))
    setFinalStatic(JBColor::class.java, "CYAN", JBColor(MTUI.MTColor.CYAN, MTUI.MTColor.DARK_CYAN))
    setFinalStatic(JBColor::class.java, "white", background)
    setFinalStatic(JBColor::class.java, "WHITE", background)
    setFinalStatic(JBColor::class.java, "black", foreground)
    setFinalStatic(JBColor::class.java, "BLACK", foreground)
    setFinalStatic(JBColor::class.java, "gray", primaryForeground)
    setFinalStatic(JBColor::class.java, "lightGray", separatorColor)
    setFinalStatic(JBColor::class.java, "darkGray", separatorColor)
    setFinalStatic(DarculaColors::class.java, "BLUE", accentColor)
    setFinalStatic(DarculaColors::class.java, "RED", accentColor)
    setFinalStatic(PlatformColors::class.java, "BLUE", accentColor)
    setFinalStatic(LightColors::class.java, "BLUE", JBColor(MTUI.MTColor.BLUE, MTUI.MTColor.DARK_BLUE))
    setFinalStatic(LightColors::class.java, "RED", JBColor(MTUI.MTColor.RED, MTUI.MTColor.DARK_RED))
    setFinalStatic(LightColors::class.java, "YELLOW", JBColor(MTUI.MTColor.YELLOW, MTUI.MTColor.DARK_YELLOW))
    setFinalStatic(LightColors::class.java, "GREEN", JBColor(MTUI.MTColor.GREEN, MTUI.MTColor.DARK_GREEN))
    setFinalStatic(LightColors::class.java, "CYAN", JBColor(MTUI.MTColor.CYAN, MTUI.MTColor.DARK_CYAN))
  }

  /**
   * Patch local history user label color
   *
   */
  @Throws(NoSuchFieldException::class, IllegalAccessException::class)
  private fun patchLocalHistory() {
    setFinalStatic(RevisionsList.MyCellRenderer::class.java, "USER_LABEL_COLOR", accentColor)
  }

  @Throws(NoSuchFieldException::class, IllegalAccessException::class)
  private fun patchDebugWindow() {
    setFinalStatic(CaptionPanel::class.java, "CNT_ACTIVE_BORDER_COLOR", background)
  }

  @Throws(NoSuchFieldException::class, IllegalAccessException::class)
  private fun patchGrays() {
    // Replace Gray with a clear and transparent color
    val gray = Gray._85
    val alphaGray = gray.withAlpha(1)
    setFinalStatic(Gray::class.java, "_85", alphaGray)
    setFinalStatic(Gray::class.java, "_40", alphaGray)
    setFinalStatic(Gray::class.java, "_145", alphaGray)
    setFinalStatic(Gray::class.java, "_201", alphaGray)

    // Quick info border
    setFinalStatic(Gray::class.java, "_90", gray.withAlpha(25))

    // tool window color
    val dark = MTConfig.getInstance().selectedTheme.isDark
    setFinalStatic(Gray::class.java, "_15", if (dark) Gray._15.withAlpha(255) else Gray._200.withAlpha(15))
  }

  @Suppress("StringLiteralDuplication")
  @Throws(NoSuchFieldException::class, IllegalAccessException::class)
  private fun patchAndroid() {
    val panelBackground = background
    val contrastBackground = contrastBackground
    val secondaryBackground = secondaryBackground
    val highlightBackground = highlightBackground
    try {
      val uiUtils = Class.forName("com.android.tools.idea.assistant.view.UIUtils")
      setFinalStatic(uiUtils, "AS_STANDARD_BACKGROUND_COLOR", panelBackground)
      setFinalStatic(uiUtils, "BACKGROUND_COLOR", panelBackground)
      setFinalStatic(uiUtils, "SECONDARY_COLOR", secondaryBackground)
      val wizardConstants = Class.forName("com.android.tools.idea.wizard.WizardConstants")
      setFinalStatic(wizardConstants, "ANDROID_NPW_HEADER_COLOR", panelBackground)
      val navColorSet = Class.forName("com.android.tools.idea.naveditor.scene.NavColorSet")
      setFinalStatic(navColorSet, "BACKGROUND_COLOR", contrastBackground)
      setFinalStatic(navColorSet, "FRAME_COLOR", contrastBackground)
      setFinalStatic(navColorSet, "HIGHLIGHTED_FRAME_COLOR", highlightBackground)
      setFinalStatic(navColorSet, "SUBDUED_FRAME_COLOR", highlightBackground)
      setFinalStatic(navColorSet, "SUBDUED_BACKGROUND_COLOR", panelBackground)
      setFinalStatic(navColorSet, "COMPONENT_BACKGROUND_COLOR", secondaryBackground)
      setFinalStatic(navColorSet, "LIST_MOUSEOVER_COLOR", secondaryBackground)
      setFinalStatic(navColorSet, "PLACEHOLDER_BACKGROUND_COLOR", secondaryBackground)
      val studioColors = Class.forName("com.android.tools.adtui.common.StudioColorsKt")
      setFinalStatic(studioColors, "primaryPanelBackground", JBColor(contrastBackground, contrastBackground))
      setFinalStatic(studioColors, "secondaryPanelBackground", panelBackground)
      setFinalStatic(studioColors, "border", panelBackground)
      setFinalStatic(studioColors, "borderLight", secondaryBackground)
    } catch (e: ClassNotFoundException) {
      // do not log
    }
  }

  @Throws(NoSuchFieldException::class, IllegalAccessException::class)
  private fun patchKotlin() {
    val highlightBackground: Color = JBColor.namedColor(
      "ParameterInfo.currentOverloadBackground",
      UIUtil.getListSelectionBackground(false)
    )
    try {
      val kotlinParamInfo =
        Class.forName("org.jetbrains.kotlin.idea.parameterInfo.KotlinParameterInfoWithCallHandlerBase")
      val color = JBColor(highlightBackground, highlightBackground)
      val fields = kotlinParamInfo.declaredFields
      val objects = Arrays.stream(fields)
        .filter { field: Field -> field.type == Color::class.java }
        .toArray()
      setFinalStatic((objects[0] as Field), color)
    } catch (e: ClassNotFoundException) {
      // do not log
    }
  }

  /**
   * Very clever way to theme excluded files color
   */
  @Throws(NoSuchFieldException::class, IllegalAccessException::class, ClassNotFoundException::class)
  private fun patchScopes() {
    val excludedColor = MTConfig.getInstance().selectedTheme.theme.excludedColor

    // Do not replace file colors on native themes
    if (MTConfig.getInstance().selectedTheme.isNative) {
      return
    }

    // Colors for the scope editor
    setFinalStatic(
      Class.forName("com.intellij.ide.util.scopeChooser.ScopeEditorPanel\$MyTreeCellRenderer"),
      "WHOLE_INCLUDED",
      MTUI.MTColor.BLUE
    )
    setFinalStatic(
      Class.forName("com.intellij.ide.util.scopeChooser.ScopeEditorPanel\$MyTreeCellRenderer"),
      "PARTIAL_INCLUDED",
      MTUI.MTColor.ORANGE
    )
    val ourDefaultColors = ContainerUtil.immutableMapBuilder<String, Color>()
      .put("Sea", UIManager.getColor("FileColor.Blue")) //NON-NLS
      .put("Forest", UIManager.getColor("FileColor.Green")) //NON-NLS
      .put("Spice", UIManager.getColor("FileColor.Orange")) //NON-NLS
      .put("Crimson", UIManager.getColor("FileColor.Rose")) //NON-NLS
      .put("DeepPurple", UIManager.getColor("FileColor.Violet")) //NON-NLS
      .put("Amber", UIManager.getColor("FileColor.Yellow")) //NON-NLS
      .put("Theme Excluded Color", excludedColor) //NON-NLS
      .build()
    val fields = FileColorManagerImpl::class.java.declaredFields
    val objects = Arrays.stream(fields)
      .filter { field: Field -> field.type == MutableMap::class.java }
      .toArray()
    setFinalStatic((objects[0] as Field), ourDefaultColors)
  }

  /**
   * Replace NavBar with MTNavBar
   */
  @Throws(NoSuchFieldException::class, IllegalAccessException::class)
  private fun patchNavBar() {
    setFinalStatic(NavBarUIManager::class.java, "DARCULA", MTNavBarUI())
    setFinalStatic(NavBarUIManager::class.java, "COMMON", MTNavBarUI())
  }

  /**
   * Replace IdeaActionButton with MTIdeaActionButton
   */
  @Throws(NoSuchFieldException::class, IllegalAccessException::class)
  private fun patchIdeaActionButton() {
    setFinalStatic(ActionButtonLook::class.java, "SYSTEM_LOOK", MTActionButtonLook())
  }

  /**
   * New implementation for tabs height
   */
  @Throws(NoSuchFieldException::class, IllegalAccessException::class)
  private fun patchTabs() {
    val tabsHeight = MTConfig.getInstance().tabsHeight + TAB_PADDING
    setFinalStatic(SingleHeightTabs::class.java, "UNSCALED_PREF_HEIGHT", tabsHeight)
    UIManager.put("TabbedPane.tabHeight", tabsHeight)
  }

  /**
   * Patch the Completion Popup background to match the currently selected theme.
   * Note: This has since been replaced from the color scheme, yet it's better to use it this way for now
   */
  private fun patchCompletionPopup() {
    val autoCompleteBackground = secondaryBackground
    try {
      val backgroundColorField = LookupCellRenderer::class.java.getDeclaredField("BACKGROUND_COLOR")
      setFinalStatic(backgroundColorField, autoCompleteBackground)
    } catch (e: NoSuchFieldException) {
      thisLogger().error(e)
    } catch (e: IllegalAccessException) {
      thisLogger().error(e)
    }
  }

  private fun patchAttributes() {
    try {
      setFinalStatic(JBColor::class.java, "GRAY", labelInfoForeground)
      setFinalStatic(JBColor::class.java, "LIGHT_GRAY", selectedForeground)
      setFinalStatic(JBColor::class.java, "DARK_GRAY", labelDisabledForeground)
      setFinalStatic(
        SimpleTextAttributes::class.java,
        "DARK_TEXT",
        SimpleTextAttributes(SimpleTextAttributes.STYLE_PLAIN, labelDisabledForeground)
      )
      setFinalStatic(
        SimpleTextAttributes::class.java,
        "SIMPLE_CELL_ATTRIBUTES",
        SimpleTextAttributes(SimpleTextAttributes.STYLE_PLAIN, labelInfoForeground)
      )
      setFinalStatic(
        SimpleTextAttributes::class.java,
        "EXCLUDED_ATTRIBUTES",
        SimpleTextAttributes(SimpleTextAttributes.STYLE_PLAIN, labelDisabledForeground)
      )
      setFinalStatic(
        SimpleTextAttributes::class.java,
        "GRAY_ATTRIBUTES",
        SimpleTextAttributes(SimpleTextAttributes.STYLE_PLAIN, labelInfoForeground)
      )
      setFinalStatic(
        SimpleTextAttributes::class.java,
        "GRAY_SMALL_ATTRIBUTES",
        SimpleTextAttributes(SimpleTextAttributes.STYLE_SMALLER, labelInfoForeground)
      )
      setFinalStatic(
        SimpleTextAttributes::class.java,
        "GRAY_ITALIC_ATTRIBUTES",
        SimpleTextAttributes(SimpleTextAttributes.STYLE_ITALIC, labelInfoForeground)
      )
      setFinalStatic(
        SimpleTextAttributes::class.java,
        "SYNTHETIC_ATTRIBUTES",
        SimpleTextAttributes(SimpleTextAttributes.STYLE_PLAIN, linkForeground)
      )
    } catch (e: NoSuchFieldException) {
      thisLogger().error(e)
    } catch (e: IllegalAccessException) {
      thisLogger().error(e)
    }
  }
}

