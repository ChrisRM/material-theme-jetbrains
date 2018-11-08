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

package com.chrisrm.idea.help;

import com.chrisrm.idea.MTAnalytics;
import com.chrisrm.idea.config.MTConfigurable;
import com.chrisrm.idea.config.MTCustomThemeConfigurable;
import com.chrisrm.idea.utils.MTUiUtils;
import com.intellij.openapi.help.WebHelpProvider;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings({"MethodWithMultipleReturnPoints",
    "SwitchStatement"})
public final class MTHelpProvider extends WebHelpProvider {

  @NonNls
  @Nullable
  @Override
  public String getHelpPageUrl(@NonNls @NotNull final String helpTopicId) {
    final String unprefixedTopicId = helpTopicId.replace(getHelpTopicPrefix() + ".", "");
    MTAnalytics.getInstance().track(MTAnalytics.HELP);

    switch (unprefixedTopicId) {
      case MTConfigurable.HELP_ID:
        return MTUiUtils.DOCS_URL + "docs/getting-started/";
      case MTCustomThemeConfigurable.HELP_ID:
        return MTUiUtils.DOCS_URL + "docs/configuration/custom-themes/";
      default:
        return null;
    }
  }

  @NotNull
  @Override
  public String getHelpTopicPrefix() {
    return MTUiUtils.HELP_PREFIX;
  }
}
