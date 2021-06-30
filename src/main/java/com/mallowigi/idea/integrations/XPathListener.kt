package com.mallowigi.idea.integrations

import com.intellij.ide.ui.LafManager
import com.intellij.ide.ui.LafManagerListener
import com.intellij.openapi.editor.colors.EditorColors
import com.intellij.openapi.editor.colors.EditorColors.IDENTIFIER_UNDER_CARET_ATTRIBUTES
import com.intellij.openapi.editor.colors.EditorColorsManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity
import com.mallowigi.idea.MTThemeManager
import org.intellij.plugins.xpathView.Config
import org.intellij.plugins.xpathView.XPathAppComponent

class XPathListener : LafManagerListener, StartupActivity {
  override fun lookAndFeelChanged(source: LafManager) {
    installXPathSearchColors()
  }

  private fun installXPathSearchColors() {
    val currentLookAndFeel = LafManager.getInstance().currentLookAndFeel
    if (MTThemeManager.isMaterialTheme(currentLookAndFeel)) {
      val schemeForCurrentUITheme = EditorColorsManager.getInstance().schemeForCurrentUITheme
      // install themed search results text attributes.
      val textSearchResultAttributes = schemeForCurrentUITheme.getAttributes(EditorColors.TEXT_SEARCH_RESULT_ATTRIBUTES)
      XPathAppComponent.getInstance().config.attributes.backgroundColor = textSearchResultAttributes.backgroundColor
      XPathAppComponent.getInstance().config.attributes.foregroundColor = textSearchResultAttributes.foregroundColor
      XPathAppComponent.getInstance().config.contextAttributes.backgroundColor =
        schemeForCurrentUITheme.getAttributes(IDENTIFIER_UNDER_CARET_ATTRIBUTES).backgroundColor
    } else {
      // reset to defaults
      val config = Config()
      XPathAppComponent.getInstance().config.attributes.backgroundColor = config.attributes.backgroundColor
      XPathAppComponent.getInstance().config.attributes.backgroundColor = config.attributes.foregroundColor

      XPathAppComponent.getInstance().config.contextAttributes.backgroundColor =
        config.contextAttributes.backgroundColor
      XPathAppComponent.getInstance().config.contextAttributes.foregroundColor =
        config.contextAttributes.foregroundColor
    }
  }

  override fun runActivity(project: Project) {
    installXPathSearchColors()
  }
}
