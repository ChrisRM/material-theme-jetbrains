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

import com.intellij.openapi.application.ApplicationInfo;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ApplicationNamesInfo;
import com.intellij.util.ObjectUtils;
import com.mallowigi.idea.config.application.MTConfig;
import com.mallowigi.idea.messages.MaterialThemeBundle;
import com.mixpanel.mixpanelapi.ClientDelivery;
import com.mixpanel.mixpanelapi.MessageBuilder;
import com.mixpanel.mixpanelapi.MixpanelAPI;
import org.jetbrains.annotations.NonNls;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

@SuppressWarnings({"WeakerAccess",
  "StaticMethodOnlyUsedInOneClass"})
public final class MTAnalytics {
  @NonNls
  public static final String CONFIG = "ConfigV2";
  @NonNls
  public static final String UPDATE_NOTIFICATION = "Notification";
  @NonNls
  public static final String RECOMMENDED_HEIGHT = "RecommendedTabHeight";
  @NonNls
  public static final String CHANGE_WALLPAPER = "ChangeWallpaper";
  @NonNls
  public static final String REMOVE_WALLPAPER = "RemoveWallpaper";
  @NonNls
  public static final String COMPACT_DROPDOWNS = "CompactDropdowns";
  @NonNls
  public static final String COMPACT_SIDEBAR = "CompactSidebar";
  @NonNls
  public static final String COMPACT_STATUSBAR = "CompactStatusBar";
  @NonNls
  public static final String COMPACT_MENUS = "CompactMenus";
  @NonNls
  public static final String SHOW_WIZARD = "ShowWizard";
  @NonNls
  public static final String CONTRAST_MODE = "ContrastMode";
  @NonNls
  public static final String HIGH_CONTRAST = "HighContrast";
  @NonNls
  public static final String MATERIAL_FONTS = "MaterialFonts";
  @NonNls
  public static final String OVERRIDE_ACCENT = "OverrideAccent";
  @NonNls
  public static final String COLORED_DIRS = "ColoredDirs";
  @NonNls
  public static final String LANGUAGE_ADDITIONS = "LanguageAdditions";
  @NonNls
  public static final String UPPERCASE_TABS = "UppercaseTabs";
  @NonNls
  public static final String ACCENT = "AccentColor";
  @NonNls
  public static final String ACCENT_MODE = "AccentMode";
  @NonNls
  public static final String INDICATOR_STYLE = "IndicatorStyle";
  @NonNls
  public static final String SELECT_THEME = "SelectTheme";
  @NonNls
  public static final String HELP = "Help";
  @NonNls
  public static final String COMPACT_TABLES = "CompactTables";
  @NonNls
  public static final String TAB_HIGHLIGHT_POSITION = "TabHighlightPosition";
  @NonNls
  public static final String MATERIAL_WALLPAPERS = "Material Wallpapers";
  @NonNls
  public static final String PROJECT_FRAME = "ProjectFrame";
  @NonNls
  public static final String STRIPED_TOOL_WINDOWS = "StripedToolWindows";
  @NonNls
  private static final String MIXPANEL_KEY = "mixpanelKey";
  @NonNls
  public static final String OUTLINE_BUTTONS = "OutlineButtons";
  @NonNls
  public static final String OVERLAYS = "Overlays";

  private final MessageBuilder messageBuilder;
  private final MixpanelAPI mixpanel;
  private final String userId;
  private boolean isOffline;

  @SuppressWarnings("CallToSystemGetenv")
  public MTAnalytics() {
    messageBuilder = new MessageBuilder(ObjectUtils.notNull(System.getenv(MIXPANEL_KEY), MaterialThemeBundle.message("mixpanel.key")));
    mixpanel = new MixpanelAPI();
    userId = MTConfig.getInstance().getUserId();
    isOffline = false;

    ApplicationManager.getApplication().executeOnPooledThread(this::ping);
  }

  public static MTAnalytics getInstance() {
    return ApplicationManager.getApplication().getService(MTAnalytics.class);
  }

  /**
   * Initialize the MixPanel analytics
   */
  void initAnalytics() {
    identify();
    try {
      trackWithData(CONFIG, MTConfig.getInstance().asJson());
    } catch (final JSONException e) {
      e.printStackTrace();
    }
  }

  /**
   * Track a random event
   */
  public void track(final String event) {
    if (MTConfig.getInstance().isDisallowDataCollection() || isOffline) {
      return;
    }

    try {
      final JSONObject sentEvent = messageBuilder.event(userId, event, null);
      final ClientDelivery delivery = new ClientDelivery();
      delivery.addMessage(sentEvent);

      mixpanel.deliver(delivery);

    } catch (final IOException e) {
      isOffline = true;
    }
  }

  /**
   * Track an event with data
   */
  void trackWithData(final String event, final JSONObject props) {
    if (MTConfig.getInstance().isDisallowDataCollection() || isOffline) {
      return;
    }

    try {
      final JSONObject sentEvent = messageBuilder.event(userId, event, props);
      final ClientDelivery delivery = new ClientDelivery();
      delivery.addMessage(sentEvent);

      mixpanel.deliver(delivery);

    } catch (final IOException e) {
      isOffline = true;
    }
  }

  /**
   * Track an event with a single value
   */
  public void trackValue(final String event, final Object value) {
    try {
      final JSONObject props = new JSONObject();
      props.put(event, value);
      trackWithData(event, props);
    } catch (final JSONException e) {
      isOffline = true;
    }
  }

  /**
   * Identify an user
   */
  @SuppressWarnings({"FeatureEnvy",
    "DuplicateStringLiteralInspection"})
  private void identify() {
    if (MTConfig.getInstance().isDisallowDataCollection() || isOffline) {
      return;
    }
    try {
      @NonNls final JSONObject props = new JSONObject();
      props.put("IDE", ApplicationNamesInfo.getInstance().getFullProductName());
      props.put("IDEVersion", ApplicationInfo.getInstance().getBuild().getBaselineVersion());
      props.put("version", MTConfig.getInstance().getVersion());

      final JSONObject update = messageBuilder.set(userId, props);
      mixpanel.sendMessage(update);
    } catch (final IOException | JSONException e) {
      isOffline = true;
    }
  }

  /**
   * Test connection
   */
  private void ping() {
    if (MTConfig.getInstance().isDisallowDataCollection() || isOffline) {
      return;
    }

    try {
      final JSONObject props = new JSONObject();
      final JSONObject update = messageBuilder.set(userId, props);
      mixpanel.sendMessage(update);
    } catch (final IOException e) {
      isOffline = true;
    }
  }
}
