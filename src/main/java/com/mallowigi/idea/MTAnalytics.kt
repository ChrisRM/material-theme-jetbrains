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
package com.mallowigi.idea

import com.intellij.openapi.application.ApplicationInfo
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.application.ApplicationNamesInfo
import com.intellij.util.ObjectUtils
import com.mallowigi.idea.MTAnalytics
import com.mallowigi.idea.config.application.MTConfig
import com.mallowigi.idea.messages.MaterialThemeBundle
import com.mixpanel.mixpanelapi.ClientDelivery
import com.mixpanel.mixpanelapi.MessageBuilder
import com.mixpanel.mixpanelapi.MixpanelAPI
import org.jetbrains.annotations.NonNls
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class MTAnalytics {
  private val messageBuilder: MessageBuilder =
    MessageBuilder(ObjectUtils.notNull(System.getenv(MIXPANEL_KEY), MaterialThemeBundle.message("mixpanel.key")))
  private val mixpanel: MixpanelAPI = MixpanelAPI()
  private val userId: String = MTConfig.getInstance().userId
  private var isOffline: Boolean

  /**
   * Initialize the MixPanel analytics
   */
  fun initAnalytics() {
    identify()
    try {
      trackWithData(CONFIG, MTConfig.getInstance().asJson())
    } catch (e: JSONException) {
      e.printStackTrace()
    }
  }

  /**
   * Track a random event
   */
  fun track(event: String?) {
    if (MTConfig.getInstance().isDisallowDataCollection || isOffline) return
    try {
      val sentEvent = messageBuilder.event(userId, event, null)
      val delivery = ClientDelivery()
      delivery.addMessage(sentEvent)
      mixpanel.deliver(delivery)
    } catch (e: IOException) {
      isOffline = true
    }
  }

  /**
   * Track an event with data
   */
  private fun trackWithData(event: String?, props: JSONObject?) {
    if (MTConfig.getInstance().isDisallowDataCollection || isOffline) return
    try {
      val sentEvent = messageBuilder.event(userId, event, props)
      val delivery = ClientDelivery()
      delivery.addMessage(sentEvent)
      mixpanel.deliver(delivery)
    } catch (e: IOException) {
      isOffline = true
    }
  }

  /**
   * Track an event with a single value
   */
  fun trackValue(event: String?, value: Any?) {
    try {
      val props = JSONObject()
      props.put(event, value)
      trackWithData(event, props)
    } catch (e: JSONException) {
      isOffline = true
    }
  }

  /**
   * Identify an user
   */
  private fun identify() {
    if (MTConfig.getInstance().isDisallowDataCollection || isOffline) return
    try {
      val props = JSONObject()
      props.put("IDE", ApplicationNamesInfo.getInstance().fullProductName)
      props.put("IDEVersion", ApplicationInfo.getInstance().build.baselineVersion)
      props.put("version", MTConfig.getInstance().version)
      val update = messageBuilder.set(userId, props)
      mixpanel.sendMessage(update)
    } catch (e: IOException) {
      isOffline = true
    } catch (e: JSONException) {
      isOffline = true
    }
  }

  /**
   * Test connection
   */
  private fun ping() {
    if (MTConfig.getInstance().isDisallowDataCollection || isOffline) return
    try {
      val props = JSONObject()
      val update = messageBuilder.set(userId, props)
      mixpanel.sendMessage(update)
    } catch (e: IOException) {
      isOffline = true
    }
  }

  companion object {
    const val CONFIG: @NonNls String = "ConfigV2"
    const val UPDATE_NOTIFICATION: @NonNls String = "Notification"
    private const val MIXPANEL_KEY: @NonNls String = "mixpanelKey"

    const val RECOMMENDED_HEIGHT: @NonNls String = "RecommendedTabHeight"
    const val CHANGE_WALLPAPER: @NonNls String = "ChangeWallpaper"
    const val REMOVE_WALLPAPER: @NonNls String = "RemoveWallpaper"
    const val COMPACT_DROPDOWNS: @NonNls String = "CompactDropdowns"
    const val COMPACT_SIDEBAR: @NonNls String = "CompactSidebar"
    const val COMPACT_STATUSBAR: @NonNls String = "CompactStatusBar"
    const val COMPACT_MENUS: @NonNls String = "CompactMenus"
    const val SHOW_WIZARD: @NonNls String = "ShowWizard"
    const val CONTRAST_MODE: @NonNls String = "ContrastMode"
    const val HIGH_CONTRAST: @NonNls String = "HighContrast"
    const val MATERIAL_FONTS: @NonNls String = "MaterialFonts"
    const val OVERRIDE_ACCENT: @NonNls String = "OverrideAccent"
    const val COLORED_DIRS: @NonNls String = "ColoredDirs"
    const val LANGUAGE_ADDITIONS: @NonNls String = "LanguageAdditions"
    const val UPPERCASE_TABS: @NonNls String = "UppercaseTabs"
    const val ACCENT: @NonNls String = "AccentColor"
    const val ACCENT_MODE: @NonNls String = "AccentMode"
    const val INDICATOR_STYLE: @NonNls String = "IndicatorStyle"
    const val SELECT_THEME: @NonNls String = "SelectTheme"
    const val HELP: @NonNls String = "Help"
    const val COMPACT_TABLES: @NonNls String = "CompactTables"
    const val TAB_HIGHLIGHT_POSITION: @NonNls String = "TabHighlightPosition"
    const val MATERIAL_WALLPAPERS: @NonNls String = "Material Wallpapers"
    const val PROJECT_FRAME: @NonNls String = "ProjectFrame"
    const val STRIPED_TOOL_WINDOWS: @NonNls String = "StripedToolWindows"
    const val OUTLINE_BUTTONS: @NonNls String = "OutlineButtons"
    const val OVERLAYS: @NonNls String = "Overlays"

    @JvmStatic
    val instance: MTAnalytics
      get() = ApplicationManager.getApplication().getService(MTAnalytics::class.java)
  }

  init {
    isOffline = false
    ApplicationManager.getApplication().executeOnPooledThread { ping() }
  }
}
