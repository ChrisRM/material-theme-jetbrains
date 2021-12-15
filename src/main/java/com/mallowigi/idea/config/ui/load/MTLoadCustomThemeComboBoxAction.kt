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
package com.mallowigi.idea.config.ui.load

import com.intellij.icons.AllIcons
import com.intellij.ide.GeneralSettings
import com.intellij.ide.util.PropertiesComponent
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.DefaultActionGroup
import com.intellij.openapi.actionSystem.Presentation
import com.intellij.openapi.actionSystem.ex.ComboBoxAction
import com.intellij.openapi.fileChooser.FileChooser
import com.intellij.openapi.fileChooser.FileChooserDescriptor
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.util.io.FileUtil
import com.intellij.openapi.vfs.VfsUtil
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.ui.components.panels.NonOpaquePanel
import com.intellij.util.ui.JBUI
import com.mallowigi.idea.config.custom.MTCustomThemeConfig
import com.mallowigi.idea.config.ui.MTCustomThemeForm
import com.mallowigi.idea.messages.MaterialThemeBundle.message
import com.mallowigi.idea.themes.MTBundledThemesManager
import com.mallowigi.idea.themes.MTThemeCollection
import com.mallowigi.idea.themes.MTThemeFacade
import java.awt.BorderLayout
import java.io.File
import javax.swing.JComponent
import javax.swing.border.Border

/**
 * Action to load custom themes from XML
 *
 * @property mtCustomThemeForm the custom theme form
 */
class MTLoadCustomThemeComboBoxAction(private val mtCustomThemeForm: MTCustomThemeForm) : ComboBoxAction() {
  private val customThemeConfig: MTCustomThemeConfig = MTCustomThemeConfig.getInstance().clone()

  /**
   * Add Gear icon
   *
   */
  override fun update(e: AnActionEvent) {
    super.update(e)
    e.presentation.icon = AllIcons.General.GearPlain
  }

  /**
   * Get min height
   *
   * @return
   */
  override fun getMinHeight(): Int = 40

  /**
   * Create the action custom component: a dropdown menu
   *
   * @param presentation action presentation
   * @param place action placement
   * @return the component wrapping the action
   */
  override fun createCustomComponent(presentation: Presentation, place: String): JComponent {
    val comboBoxButton = ComboBoxButton(presentation)
    val panel = NonOpaquePanel(BorderLayout())
    val border: Border = JBUI.Borders.empty()

    panel.border = border
    panel.add(comboBoxButton)

    return panel
  }

  /**
   * Create popup action group
   *
   * @param button action button
   * @return the action group
   */
  override fun createPopupActionGroup(button: JComponent): DefaultActionGroup {
    val group = DefaultActionGroup(/* shortName = */ null, /* popup = */ true)
    group.addSeparator(message("MTCustomThemeForm.loadFromButton.loadFrom"))

    // Add the bundled themes
    MTThemeCollection.getAllThemes()
      .filterNot { it.isCustom || it.isNative }
      .forEach { group.add(ImportBundledThemeAction(it)) }

    group.addSeparator(message("MTCustomThemeForm.loadFromButton.loadFromDisk"))
    group.add(LoadFromXmlAction())
    group.addSeparator(message("MTCustomThemeForm.loadFromButton.save"))
    group.add(SaveToXmlAction())

    return group
  }

  /**
   * Import bundled theme action
   *
   * @property mtThemeFacade original theme
   */
  inner class ImportBundledThemeAction(private val mtThemeFacade: MTThemeFacade) :
    AnAction(mtThemeFacade.themeName, mtThemeFacade.themeName, mtThemeFacade.icon) {
    /**
     * When Import action is triggered
     */
    override fun actionPerformed(e: AnActionEvent) {
      customThemeConfig.importFrom(mtThemeFacade.theme)
      mtCustomThemeForm.setFormState(customThemeConfig)
    }
  }

  /**
   * Load from xml action
   *
   */
  inner class LoadFromXmlAction : AnAction(
    /* text = */ message("MTCustomThemeForm.loadFromButton.fromDisk"),
    /* description = */ message("MTCustomThemeForm.loadFromButton.fromDisk.description"),
    /* icon = */ AllIcons.Actions.Install
  ) {
    /**
     * When Load from XML action is triggered
     */
    override fun actionPerformed(e: AnActionEvent) {
      val descriptor: FileChooserDescriptor = MyFileChooserDescriptor()
      descriptor.title = message("MTCustomThemeForm.importButton.selectFile")

      val oldPath =
        PropertiesComponent.getInstance().getValue("plugins.preselection.path")
          ?: e.project?.basePath
          ?: GeneralSettings.getInstance().defaultProjectDirectory

      // Open the file chooser at the old path location
      val toSelect = VfsUtil.findFileByIoFile(File(FileUtil.toSystemDependentName(oldPath)), false)

      FileChooser.chooseFile(descriptor, null, null, toSelect, ::loadTheme)
    }

    /**
     * Load theme from XML and put it in the MTBundledThemesManager
     *
     * @param virtualFile the file to load
     */
    private fun loadTheme(virtualFile: VirtualFile) {
      val theme = MTBundledThemesManager.loadBundledTheme(virtualFile)

      if (theme == null) {
        Messages.showErrorDialog(message("error.parsing.xml"), message("error"))
        return
      }

      customThemeConfig.importFrom(theme)
      mtCustomThemeForm.setFormState(customThemeConfig)
      Messages.showDialog(
        /* project = */ null,
        /* message = */ String.format(message("MTCustomThemeForm.loadFromButton.success"), virtualFile.name),
        /* title = */ message("MTCustomThemeForm.loadFromButton.importSuccess"),
        /* options = */ arrayOf(message("common.ok")),
        /* defaultOptionIndex = */ 0,
        /* icon = */ Messages.getInformationIcon()
      )
    }
  }

  /**
   * Save to xml action
   *
   */
  inner class SaveToXmlAction : AnAction(
    /* text = */ message("MTCustomThemeForm.loadFromButton.saveAs"),
    /* description = */ message("MTCustomThemeForm.loadFromButton.saveAs.description"),
    /* icon = */ AllIcons.Actions.MenuSaveall
  ) {
    /**
     * When Save Action is triggered
     */
    override fun actionPerformed(e: AnActionEvent): Unit = MTSaveCustomThemeDialog(mtCustomThemeForm).show()
  }

  /**
   * File chooser for xml files
   *
   */
  private class MyFileChooserDescriptor :
    FileChooserDescriptor(
      /* chooseFiles = */ true,
      /* chooseFolders = */ false,
      /* chooseJars = */ false,
      /* chooseJarsAsFiles = */ false,
      /* chooseJarContents = */ false,
      /* chooseMultiple = */ false
    ) {

    override fun isFileSelectable(file: VirtualFile?): Boolean = file!!.extension == "xml"
  }
}
