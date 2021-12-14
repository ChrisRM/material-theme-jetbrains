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
package com.mallowigi.idea.themes.themes

import com.intellij.ide.util.PropertiesComponent
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.util.IconLoader
import com.intellij.openapi.util.io.FileUtil
import com.intellij.openapi.util.text.StringUtil
import com.intellij.openapi.wm.impl.IdeBackgroundUtil
import com.intellij.ui.ColorUtil
import com.intellij.ui.JBColor
import com.mallowigi.idea.MTThemeManager
import com.mallowigi.idea.config.application.MTConfig
import com.mallowigi.idea.lafs.MTDarkLaf
import com.mallowigi.idea.lafs.MTLightLaf
import com.mallowigi.idea.themes.MTAccentMode
import com.mallowigi.idea.themes.MTAccentMode.selectionColor
import com.mallowigi.idea.themes.lists.ContrastResources
import com.mallowigi.idea.themes.lists.MTThemeResources.backgroundResources
import com.mallowigi.idea.themes.lists.MTThemeResources.buttonColorResources
import com.mallowigi.idea.themes.lists.MTThemeResources.contrastResources
import com.mallowigi.idea.themes.lists.MTThemeResources.disabledResources
import com.mallowigi.idea.themes.lists.MTThemeResources.excludedResources
import com.mallowigi.idea.themes.lists.MTThemeResources.foregroundResources
import com.mallowigi.idea.themes.lists.MTThemeResources.highlightResources
import com.mallowigi.idea.themes.lists.MTThemeResources.notificationsResources
import com.mallowigi.idea.themes.lists.MTThemeResources.secondBorderResources
import com.mallowigi.idea.themes.lists.MTThemeResources.secondaryBackgroundResources
import com.mallowigi.idea.themes.lists.MTThemeResources.selectionBackgroundResources
import com.mallowigi.idea.themes.lists.MTThemeResources.selectionForegroundResources
import com.mallowigi.idea.themes.lists.MTThemeResources.selectionTransparentBackgroundResources
import com.mallowigi.idea.themes.lists.MTThemeResources.tableSelectedResources
import com.mallowigi.idea.themes.lists.MTThemeResources.textResources
import com.mallowigi.idea.themes.lists.MTThemeResources.treeSelectionResources
import com.mallowigi.idea.themes.models.MTSerializedTheme
import com.mallowigi.idea.themes.models.MTThemeable
import com.mallowigi.idea.utils.MTColorUtils.contrastifyBackground
import com.mallowigi.idea.utils.MTColorUtils.contrastifyForeground
import com.mallowigi.idea.utils.MTUI
import com.mallowigi.idea.utils.MTUI.List.selectedColor
import com.mallowigi.idea.utils.MTUI.Panel.background
import com.mallowigi.idea.utils.MTUI.Panel.editorColor
import com.mallowigi.idea.utils.MTUI.Panel.transparentBackground
import com.mallowigi.idea.utils.MTUI.Panel.transparentSelectionBackground
import com.mallowigi.idea.utils.MTUiUtils
import java.awt.Color
import java.io.FileOutputStream
import java.io.IOException
import java.io.Serializable
import java.util.Objects
import javax.swing.Icon
import javax.swing.UIManager
import javax.swing.UnsupportedLookAndFeelException
import javax.swing.plaf.ColorUIResource

/**
 * Abstract Theme for Material Themes
 *
 */
abstract class MTAbstractTheme protected constructor() : Serializable, MTThemeable, MTSerializedTheme {

  @Transient
  private var isNotHighContrast = false

  override val icon: Icon?
    get() = if (themeIcon != null) IconLoader.getIcon(themeIcon!!, MTAbstractTheme::class.java) else null

  override val isCustom: Boolean
    get() = false

  override val isNative: Boolean
    get() = false

  //region Theme Colors
  override val backgroundColor: Color
    get() = contrastifyBackground(isThemeDark, backgroundColorResource, isNotHighContrast)

  override val contrastColor: Color
    get() = contrastifyBackground(isThemeDark, contrastColorResource, isNotHighContrast)

  override val foregroundColor: Color
    get() = contrastifyForeground(isThemeDark, foregroundColorResource, isNotHighContrast)

  override val primaryColor: Color
    get() = contrastifyForeground(isThemeDark, textColorResource, isNotHighContrast)

  override val selectionBackgroundColor: Color
    get() = selectionBackgroundColorResource

  override val selectionForegroundColor: Color
    get() = selectionForegroundColorResource

  override val excludedColor: Color
    get() = contrastifyBackground(isThemeDark, excludedColorResource, isNotHighContrast)

  override val notificationsColor: Color
    get() = notificationsColorResource

  override val secondBorderColor: Color
    get() = secondBorderColorResource

  override val disabledColor: Color
    get() = disabledColorResource

  override val secondaryBackgroundColor: Color
    get() = secondaryBackgroundColorResource

  override val buttonColor: Color
    get() = buttonColorResource

  override val tableSelectedColor: Color
    get() = tableSelectedColorResource

  override val textColor: Color
    get() = textColorResource

  override val treeSelectionColor: Color
    get() = treeSelectionColorResource

  override val highlightColor: Color
    get() = highlightColorResource

  override val accentColor: Color
    get() = accentColorResource
  //endregion

  override fun toString(): String = themeId

  /**
   * Activate the theme by overriding UIManager with the theme resources and by setting the relevant Look and feel
   */
  override fun activate() {
    val config = MTConfig.getInstance()
    isNotHighContrast = !config.isHighContrast
    try {
      JBColor.setDark(isThemeDark)
      IconLoader.setUseDarkIcons(isThemeDark)
      // Overridable method
      buildAllResources()

      // Apply theme accent color if said so
      if (config.isOverrideAccentColor) {
        config.accentColor = ColorUtil.toHex(accentColorResource)
        MTThemeManager.applyAccents(true)
      }
      installBackgroundImage()

      // Set MT Look and Feel
      setLookAndFeel()
    } catch (e: UnsupportedLookAndFeelException) {
      thisLogger().error(e)
    }
  }

  /**
   * Set look and feel for the current theme
   *
   */
  @Throws(UnsupportedLookAndFeelException::class)
  protected open fun setLookAndFeel() {
    if (isThemeDark) {
      UIManager.setLookAndFeel(MTDarkLaf())
    } else {
      UIManager.setLookAndFeel(MTLightLaf())
    }
  }

  /**
   * Build all resources. Overridable
   */
  protected open fun buildAllResources() {
    MTUiUtils.buildResources(
      backgroundResources,
      contrastifyBackground(isThemeDark, backgroundColorResource, isNotHighContrast)
    )
    MTUiUtils.buildResources(foregroundResources, foregroundColorResource)
    MTUiUtils.buildResources(
      textResources,
      contrastifyForeground(isThemeDark, textColorResource, isNotHighContrast)
    )
    MTUiUtils.buildResources(selectionBackgroundResources, selectionBackgroundColorResource)
    MTUiUtils.buildResources(
      selectionTransparentBackgroundResources,
      ColorUtil.withAlpha(selectionBackgroundColorResource, 0.8)
    )
    MTUiUtils.buildResources(selectionForegroundResources, selectionForegroundColorResource)
    MTUiUtils.buildResources(buttonColorResources, buttonColorResource)
    MTUiUtils.buildResources(secondaryBackgroundResources, secondaryBackgroundColorResource)
    MTUiUtils.buildResources(disabledResources, disabledColorResource)
    MTUiUtils.buildResources(
      contrastResources,
      contrastifyBackground(isThemeDark, contrastColorResource, isNotHighContrast)
    )
    MTUiUtils.buildResources(tableSelectedResources, tableSelectedColorResource)
    MTUiUtils.buildResources(secondBorderResources, secondBorderColorResource)
    MTUiUtils.buildResources(highlightResources, highlightColorResource)
    MTUiUtils.buildResources(treeSelectionResources, treeSelectionColorResource)
    MTUiUtils.buildResources(notificationsResources, notificationsColorResource)
    MTUiUtils.buildResources(excludedResources, excludedColorResource)
    buildNotificationsColors()
    buildFlameChartColors()
    buildFileColors()
    buildTransparentColors()
    buildTabsTransparentColors()
    buildOutlineButtons()
    buildCompletionSelectionColor()
    UIManager.getDefaults()["Component.grayForeground"] = ColorUtil.darker(textColorResource, 2)
    UIManager.getDefaults()["EditorGroupsTabs.underlineHeight"] = MTConfig.getInstance().highlightThickness
  }

  //region Theme methods

  override fun setPristine() {
    isNotHighContrast = true
  }

  private fun buildTabsTransparentColors() {
    val colors = setOf(
      "EditorTabs.inactiveColoredFileBackground"
    )
    val transparentBackground = ColorUtil.withAlpha(secondaryBackgroundColorResource, 0.5)
    MTUiUtils.buildResources(colors, transparentBackground)
  }

  private fun buildOutlineButtons() {
    val colors = setOf(
      "Button.background",
      "Button.endBackground",
      "Button.startBackground"
    )
    val buttonColor = buttonColor
    val transparentBackground = if (MTConfig.getInstance().isBorderedButtons) background else buttonColor
    MTUiUtils.buildResources(colors, transparentBackground)
  }

  override fun applyContrast(apply: Boolean) {
    val contrastedColor = if (apply) contrastColor else backgroundColor
    for (resource in ContrastResources.contrastedResources) {
      UIManager.put(resource, contrastedColor)
    }
  }

  @Suppress("HardCodedStringLiteral", "NestedBlockDepth")
  private fun installBackgroundImage() {
    val currentSpec = PropertiesComponent.getInstance().getValue(IdeBackgroundUtil.FRAME_PROP)
    val oldCurrentSpec = PropertiesComponent.getInstance().getValue("old.mt.${IdeBackgroundUtil.FRAME_PROP}")
    if (!MTConfig.getInstance().isUseMaterialWallpapers) {
      removeBackgroundImage(null)
      return
    }

    try {
      val path = backgroundImage
      if (path != null) {
        val tmpImage = FileUtil.createTempFile("mtBackgroundImage", path.substring(path.lastIndexOf('.')), true)
        val resource = javaClass.classLoader.getResource(path)
        if (resource != null) {
          javaClass.classLoader.getResourceAsStream(path).use { input ->
            FileOutputStream(tmpImage).use {
              FileUtil.copy(Objects.requireNonNull(input), it)
            }
          }

          val image = tmpImage.path
          val alpha = 85.toString()
          val fill = MTUiUtils.parseEnumValue("fill", IdeBackgroundUtil.Fill.PLAIN)
          val anchor = MTUiUtils.parseEnumValue("center", IdeBackgroundUtil.Anchor.CENTER)
          val spec = StringUtil.join(arrayOf(image, alpha, fill, anchor), ",")
          PropertiesComponent.getInstance().setValue("old.mt." + IdeBackgroundUtil.FRAME_PROP, currentSpec)
          PropertiesComponent.getInstance().setValue(IdeBackgroundUtil.FRAME_PROP, spec)
          ApplicationManager.getApplication().invokeLater { IdeBackgroundUtil.repaintAllWindows() }
        } else {
          throw IllegalArgumentException("Can't load background: $path")
        }
      } else {
        removeBackgroundImage(oldCurrentSpec)
      }
    } catch (ignored: IOException) {
      thisLogger().error(ignored)
    }
  }

  //endregion

  override fun applyAccentMode() {
    val mtConfig = MTConfig.getInstance()
    val accentColor = ColorUtil.fromHex(mtConfig.accentColor)
    val darkerAccentColor = ColorUtil.darker(accentColor, 2)
    val accentColorTransparent = ColorUtil.withAlpha(accentColor, 0.5)
    val secondAccentColor = ColorUtil.fromHex(mtConfig.secondAccentColor)
    val accentMode = mtConfig.isAccentMode
    if (accentMode) {
      // Add accent resources
      MTUiUtils.buildResources(MTAccentMode.accentModeResources, accentColor)
      MTUiUtils.buildResources(MTAccentMode.darkerAccentResources, darkerAccentColor)
      MTUiUtils.buildResources(MTAccentMode.accentModeTransparentResources, accentColorTransparent)
      // Add new selection color resources
      MTUiUtils.buildResources(MTAccentMode.selectionResources, selectionColor)
      MTUiUtils.buildResources(MTAccentMode.secondAccentResources, secondAccentColor)
    }
  }

  private fun buildNotificationsColors() {
    val errorColor = JBColor(ColorUIResource(0xef9694), ColorUIResource(0xb71c1c))
    UIManager.put("Notification.ToolWindowError.background", errorColor)
    UIManager.put("Notification.ToolWindow.errorBackground", errorColor)
    UIManager.put("Notification.ToolWindowError.borderColor", errorColor)
    UIManager.put("Notification.ToolWindow.errorBorderColor", errorColor)
    val warnColor = JBColor(ColorUIResource(0xffeca0), ColorUIResource(0x5D4037))
    UIManager.put("Notification.ToolWindowWarning.background", warnColor)
    UIManager.put("Notification.ToolWindow.warningBackground", warnColor)
    UIManager.put("Notification.ToolWindowWarning.borderColor", warnColor)
    UIManager.put("Notification.ToolWindow.warningBorderColor", warnColor)
    val infoColor = JBColor(ColorUIResource(0x87bb91), ColorUIResource(0x1B5E20))
    UIManager.put("Notification.ToolWindowInfo.borderColor", infoColor)
    UIManager.put("Notification.ToolWindow.infoBorderColor", infoColor)
    UIManager.put("Notification.ToolWindow.informativeBorderColor", infoColor)
    UIManager.put("Notification.ToolWindowInfo.background", infoColor)
    UIManager.put("Notification.ToolWindow.infoBackground", infoColor)
    UIManager.put("Notification.ToolWindow.informativeBackground", infoColor)
  }

  private fun buildFlameChartColors() {
    UIManager.put("FlameGraph.JVMBackground", MTUI.MTColor.CYAN)
    UIManager.put("FlameGraph.JVMFocusBackground", MTUI.MTColor.BLUE)
    UIManager.put("FlameGraph.JVMSearchNotMatchedBackground", MTUI.MTColor.RED)
    UIManager.put("FlameGraph.JVMFocusSearchNotMatchedBackground", MTUI.MTColor.BROWN)
    UIManager.put("FlameGraph.nativeBackground", MTUI.MTColor.YELLOW)
    UIManager.put("FlameGraph.nativeFocusBackground", MTUI.MTColor.ORANGE)
    UIManager.put("FlameGraph.nativeSearchNotMatchedBackground", MTUI.MTColor.PURPLE)
    UIManager.put("FlameGraph.nativeFocusSearchNotMatchedBackground", MTUI.MTColor.PINK)
  }

  private fun buildFileColors() {
    UIManager.put("FileColor.Green", JBColor(MTUI.MTColor.GREEN, MTUI.MTColor.DARK_GREEN))
    UIManager.put("FileColor.Blue", JBColor(MTUI.MTColor.BLUE, MTUI.MTColor.DARK_BLUE))
    UIManager.put("FileColor.Yellow", JBColor(MTUI.MTColor.YELLOW, MTUI.MTColor.DARK_YELLOW))
    UIManager.put("FileColor.Orange", JBColor(MTUI.MTColor.ORANGE, MTUI.MTColor.DARK_ORANGE))
    UIManager.put("FileColor.Violet", JBColor(MTUI.MTColor.PURPLE, MTUI.MTColor.DARK_PURPLE))
    UIManager.put("FileColor.Rose", JBColor(MTUI.MTColor.RED, MTUI.MTColor.DARK_RED))
    UIManager.put("FileColor.Gray", MTUI.Label.labelInfoForeground)
  }

  private fun buildTransparentColors() {
    val colors = setOf(
      "ScrollBar.hoverTrackColor",
      "ScrollBar.trackColor",
      "ScrollBar.Mac.hoverTrackColor",
      "ScrollBar.Mac.trackColor",
      "ScrollBar.Transparent.hoverTrackColor",
      "ScrollBar.Transparent.trackColor",
      "ScrollBar.Mac.Transparent.hoverTrackColor",
      "ScrollBar.Mac.Transparent.trackColor"
    )
    val selectionColors = setOf(
      "DragAndDrop.areaBackground"
    )
    val transparentBackground = transparentBackground
    MTUiUtils.buildResources(colors, transparentBackground)
    val transparentSelectionBackground = transparentSelectionBackground
    MTUiUtils.buildResources(selectionColors, transparentSelectionBackground)
  }

  private fun buildCompletionSelectionColor() {
    val colors = setOf(
      "CompletionPopup.selectionBackground",
      "CompletionPopup.selectionInactiveBackground"
    )
    val selectedColor = selectedColor
    val invertedColor = editorColor
    val assignedColor = if (MTConfig.getInstance().isInvertedSelectionColor) invertedColor else selectedColor
    MTUiUtils.buildResources(colors, assignedColor)
  }

  private fun removeBackgroundImage(oldCurrentSpec: String?) {
    PropertiesComponent.getInstance().setValue(IdeBackgroundUtil.FRAME_PROP, oldCurrentSpec)
    PropertiesComponent.getInstance().setValue("old.mt.${IdeBackgroundUtil.FRAME_PROP}", null)
    ApplicationManager.getApplication().invokeLater { IdeBackgroundUtil.repaintAllWindows() }
  }
}
