/*
 *  The MIT License (MIT)
 *
 *  Copyright (c) 2018 Chris Magnussen and Elior Boukhobza
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 *
 */

package com.chrisrm.idea;

import com.intellij.openapi.application.ApplicationInfo;
import com.intellij.openapi.application.ApplicationNamesInfo;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.util.ObjectUtils;
import com.mixpanel.mixpanelapi.ClientDelivery;
import com.mixpanel.mixpanelapi.MessageBuilder;
import com.mixpanel.mixpanelapi.MixpanelAPI;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MTAnalytics {
  public static final String CONFIG = "ConfigV2";
  public static final String UPDATE_NOTIFICATION = "Notification";
  public static final String ADD_FILE_COLORS = "AddFileColors";
  public static final String RECOMMENDED_HEIGHT = "RecommendedTabHeight";
  public static final String CHANGE_WALLPAPER = "ChangeWallpaper";
  public static final String COMPACT_DROPDOWNS = "CompactDropdowns";
  public static final String COMPACT_SIDEBAR = "CompactSidebar";
  public static final String COMPACT_STATUSBAR = "CompactStatusBar";
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
  public static final String PROJECT_VIEW_DECORATORS = "ProjectViewDecorators";
  public static final String STATUSBAR_THEME = "StatusBarTheme";
  public static final String UPPERCASE_TABS = "UppercaseTabs";
  public static final String ACCENT = "AccentColor";
  public static final String ARROWS_STYLE = "ArrowsStyle";
  public static final String INDICATOR_STYLE = "IndicatorStyle";
  public static final String SELECT_THEME = "SelectTheme";
  public static final String HELP = "Help";

  private final MessageBuilder messageBuilder;
  private final MixpanelAPI mixpanel;
  private final String userId;

  public MTAnalytics() {
    messageBuilder = new MessageBuilder(ObjectUtils.notNull(System.getenv("mixpanelKey"), "ab773bb5ba50d6a2a35f0dabcaf7cd2c"));
    mixpanel = new MixpanelAPI();
    userId = MTConfig.getInstance().getUserId();
  }

  public static MTAnalytics getInstance() {
    return ServiceManager.getService(MTAnalytics.class);
  }

  public void track(final String event) {
    if (MTConfig.getInstance().isDisallowDataCollection()) {
      return;
    }

    try {
      final JSONObject sentEvent = messageBuilder.event(userId, event, null);
      final ClientDelivery delivery = new ClientDelivery();
      delivery.addMessage(sentEvent);

      mixpanel.deliver(delivery);

    } catch (final IOException e) {
      e.printStackTrace();
    }
  }

  public void track(final String event, final JSONObject props) {
    if (MTConfig.getInstance().isDisallowDataCollection()) {
      return;
    }

    try {
      final JSONObject sentEvent = messageBuilder.event(userId, event, props);
      final ClientDelivery delivery = new ClientDelivery();
      delivery.addMessage(sentEvent);

      mixpanel.deliver(delivery);

    } catch (final IOException e) {
      e.printStackTrace();
    }
  }

  public void track(final String event, final Object value) {
    if (MTConfig.getInstance().isDisallowDataCollection()) {
      return;
    }

    try {
      final JSONObject props = new JSONObject();
      props.put(event, value);
      final JSONObject sentEvent = messageBuilder.event(userId, event, props);
      final ClientDelivery delivery = new ClientDelivery();
      delivery.addMessage(sentEvent);

      mixpanel.deliver(delivery);

    } catch (final IOException | JSONException e) {
      e.printStackTrace();
    }
  }

  public void identify() {
    try {
      final JSONObject props = new JSONObject();
      props.put("IDE", ApplicationNamesInfo.getInstance().getFullProductName());
      props.put("IDEVersion", ApplicationInfo.getInstance().getBuild().getBaselineVersion());
      props.put("version", MTConfig.getInstance().getVersion());

      final JSONObject update = messageBuilder.set(userId, props);
      mixpanel.sendMessage(update);
    } catch (final IOException | JSONException e) {
      e.printStackTrace();
    }
  }
}
