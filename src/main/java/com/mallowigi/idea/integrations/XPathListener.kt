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
    val xpathConfig = XPathAppComponent.getInstance().config
    if (MTThemeManager.isMaterialTheme(currentLookAndFeel)) {
      val schemeForCurrentUITheme = EditorColorsManager.getInstance().schemeForCurrentUITheme
      // install themed search results text attributes.
      val textSearchResultAttributes = schemeForCurrentUITheme.getAttributes(EditorColors.TEXT_SEARCH_RESULT_ATTRIBUTES)
      xpathConfig.attributes.backgroundColor = textSearchResultAttributes.backgroundColor
      xpathConfig.attributes.foregroundColor = textSearchResultAttributes.foregroundColor
      xpathConfig.contextAttributes.backgroundColor =
        schemeForCurrentUITheme.getAttributes(IDENTIFIER_UNDER_CARET_ATTRIBUTES).backgroundColor
    } else {
      // reset to defaults
      val config = Config()
      xpathConfig.attributes.backgroundColor = config.attributes.backgroundColor
      xpathConfig.attributes.foregroundColor = config.attributes.foregroundColor

      xpathConfig.contextAttributes.backgroundColor =
        config.contextAttributes.backgroundColor
      xpathConfig.contextAttributes.foregroundColor =
        config.contextAttributes.foregroundColor
    }
  }

  override fun runActivity(project: Project) {
    installXPathSearchColors()
  }
}
