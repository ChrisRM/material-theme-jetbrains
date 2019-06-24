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

package com.chrisrm.idea;

import com.chrisrm.idea.messages.MaterialThemeBundle;
import com.intellij.openapi.application.ApplicationInfo;
import com.intellij.openapi.application.ApplicationNamesInfo;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.util.ObjectUtils;
import com.mixpanel.mixpanelapi.ClientDelivery;
import com.mixpanel.mixpanelapi.MessageBuilder;
import com.mixpanel.mixpanelapi.MixpanelAPI;
import org.jetbrains.annotations.NonNls;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

@SuppressWarnings("WeakerAccess")
public final class MTAnalytics {
  public static final String CONFIG = "ConfigV2";
  public static final String UPDATE_NOTIFICATION = "Notification";
  public static final String RECOMMENDED_HEIGHT = "RecommendedTabHeight";
  public static final String CHANGE_WALLPAPER = "ChangeWallpaper";
  public static final String REMOVE_WALLPAPER = "RemoveWallpaper";
  public static final String COMPACT_DROPDOWNS = "CompactDropdowns";
  public static final String COMPACT_SIDEBAR = "CompactSidebar";
  public static final String COMPACT_STATUSBAR = "CompactStatusBar";
  public static final String COMPACT_MENUS = "CompactMenus";
  public static final String SHOW_WIZARD = "ShowWizard";
  public static final String CONTRAST_MODE = "ContrastMode";
  public static final String TITLE_BAR = "TitleBar";
  public static final String MATERIAL_COMPONENTS = "MaterialComponents";
  public static final String MATERIAL_THEME = "MaterialTheme";
  public static final String HIDE_FILE_ICONS = "HideFileIcons";
  public static final String HIGH_CONTRAST = "HighContrast";
  public static final String MATERIAL_FONTS = "MaterialFonts";
  public static final String MATERIAL_ICONS = "MaterialIcons";
  public static final String MONOCHROME = "Monochrome";
  public static final String OVERRIDE_ACCENT = "OverrideAccent";
  public static final String HOLLOW_FOLDERS = "ProjectViewDecorators";
  public static final String STATUSBAR_THEME = "StatusBarTheme";
  public static final String UPPERCASE_TABS = "UppercaseTabs";
  public static final String ACCENT = "AccentColor";
  public static final String ARROWS_STYLE = "ArrowsStyle";
  public static final String INDICATOR_STYLE = "IndicatorStyle";
  public static final String SELECT_THEME = "SelectTheme";
  public static final String HELP = "Help";
  public static final String COMPACT_TABLES = "CompactTables";
  public static final String MATERIAL_PSI_ICONS = "PsiIcons";
  public static final String MATERIAL_FILE_ICONS = "FileIcons";
  public static final String TAB_HIGHLIGHT_POSITION = "TabHighlightPosition";
  @NonNls
  private static final String MIXPANEL_KEY = "mixpanelKey";

  private final MessageBuilder messageBuilder;
  private final MixpanelAPI mixpanel;
  private final String userId;
  private boolean isOffline;

  public MTAnalytics() {
    messageBuilder = new MessageBuilder(ObjectUtils.notNull(System.getenv(MIXPANEL_KEY), MaterialThemeBundle.message("mixpanel.key")));
    mixpanel = new MixpanelAPI();
    userId = MTConfig.getInstance().getUserId();
    isOffline = false;

    ping();
  }

  public static MTAnalytics getInstance() {
    return ServiceManager.getService(MTAnalytics.class);
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
    try {
      final JSONObject props = new JSONObject();
      final JSONObject update = messageBuilder.set(userId, props);
      mixpanel.sendMessage(update);
    } catch (final IOException e) {
      isOffline = true;
    }
  }
}
