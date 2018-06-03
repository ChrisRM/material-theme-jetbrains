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

import com.intellij.openapi.components.ServiceManager;
import com.segment.analytics.Analytics;
import com.segment.analytics.messages.IdentifyMessage;
import com.segment.analytics.messages.TrackMessage;

import java.util.Map;

public class MTAnalytics {
  private final Analytics analytics;

  public MTAnalytics() {
    analytics = Analytics.builder("glWDCzBtmGn3ERy0agOJUT8Om6aKsSrA").build();
  }

  public static MTAnalytics getInstance() {
    return ServiceManager.getService(MTAnalytics.class);
  }

  public Analytics getAnalytics() {
    return analytics;
  }

  public void track(final String event, final Map<String, Object> properties) {
    if (!MTConfig.getInstance().isAllowDataCollection()) {
      return;
    }

    analytics.enqueue(TrackMessage.builder(event)
                          .userId(MTConfig.getInstance().getUserId())
                          .properties(properties));
  }

  public void identify() {
    if (!MTConfig.getInstance().isAllowDataCollection()) {
      return;
    }

    analytics.enqueue(IdentifyMessage.builder().userId(MTConfig.getInstance().getUserId()));
  }
}
