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

  private final MessageBuilder messageBuilder;
  private final MixpanelAPI mixpanel;

  public MTAnalytics() {
    messageBuilder = new MessageBuilder(ObjectUtils.notNull(System.getenv("mixpanelKey"), "ab773bb5ba50d6a2a35f0dabcaf7cd2c"));
    mixpanel = new MixpanelAPI();
  }

  public static MTAnalytics getInstance() {
    return ServiceManager.getService(MTAnalytics.class);
  }

  public void track(final String event, final JSONObject props) {
    if (MTConfig.getInstance().isDisallowDataCollection()) {
      return;
    }

    final String userId = MTConfig.getInstance().getUserId();

    try {
      final JSONObject sentEvent = messageBuilder.event(userId, event, props);
      final ClientDelivery delivery = new ClientDelivery();
      delivery.addMessage(sentEvent);

      mixpanel.deliver(delivery);

    } catch (final IOException e) {
      e.printStackTrace();
    }
  }

  public void identify() {
    final String userId = MTConfig.getInstance().getUserId();

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
