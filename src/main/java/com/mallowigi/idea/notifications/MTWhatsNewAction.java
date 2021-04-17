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
package com.mallowigi.idea.notifications;

import com.intellij.ide.BrowserUtil;
import com.intellij.ide.IdeBundle;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.fileEditor.impl.HTMLEditorProvider;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.NlsContexts.DetailedDescription;
import com.intellij.openapi.util.io.StreamUtil;
import com.intellij.ui.jcef.JBCefApp;
import com.intellij.util.Url;
import com.intellij.util.Urls;
import com.mallowigi.idea.MTConfig;
import com.mallowigi.idea.MTUpdatesComponent;
import com.mallowigi.idea.messages.MaterialThemeBundle;
import com.mallowigi.idea.utils.MTUiUtils;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public final class MTWhatsNewAction extends AnAction implements DumbAware {
  public static final String WHATS_NEW_URL = "https://www.material-theme.com/docs/what-s-new/";

  private static boolean shouldShow() {
    @NonNls final String pluginVersion = MTUiUtils.getVersion();

    return !pluginVersion.equals(MTConfig.getInstance().getVersion());
  }

  @Override
  public void actionPerformed(@NotNull final AnActionEvent e) {
    final String whatsNewUrl = WHATS_NEW_URL;
    final Project project = e.getProject();
    if (project != null && JBCefApp.isSupported() && shouldShow()) {
      openWhatsNewFile(project, whatsNewUrl, null);
    } else {
      BrowserUtil.browse(whatsNewUrl);
    }
  }

  @Override
  public void update(@NotNull final AnActionEvent e) {
    final boolean available = shouldShow();
    e.getPresentation().setEnabledAndVisible(available);
    if (available) {
      e.getPresentation().setText(MaterialThemeBundle.message("whats.new.action.custom.text", MTUiUtils.getVersion()));
      e.getPresentation().setDescription(MaterialThemeBundle.message("whats.new.action.custom.description", MTUiUtils.getVersion()));
    }
  }

  @Contract("_, null, null -> fail")
  public static void openWhatsNewFile(@NotNull final Project project, final String url, @DetailedDescription final String content) {
    if (url == null && content == null) {
      throw new IllegalArgumentException();
    }

    final String title = MaterialThemeBundle.message("whats.new.action.title");

    if (!JBCefApp.isSupported()) {
      Notify.showUpdate(project, MTUpdatesComponent::onPaypalClick);
    } else if (url != null) {
      final String themeId = MTConfig.getInstance().getSelectedTheme().getThemeId();
      final Url embeddedUrl = Urls.newFromEncoded(url).addParameters(Map.of("theme", themeId));
      final String finalUrl = embeddedUrl.toExternalForm();

      String timeoutContent = null;
      try (final InputStream html = MTWhatsNewAction.class.getResourceAsStream("messages/whatsNewTimeoutText.html")) {
        if (html != null) {
          //noinspection HardCodedStringLiteral
          timeoutContent = new String(StreamUtil.readBytes(html), StandardCharsets.UTF_8)
            .replace("__THEME__", "")
            .replace("__TITLE__", IdeBundle.message("whats.new.timeout.title"))
            .replace("__MESSAGE__", IdeBundle.message("whats.new.timeout.message"))
            .replace("__ACTION__", IdeBundle.message("whats.new.timeout.action", url));
        }
      } catch (final IOException e) {
        Logger.getInstance(MTWhatsNewAction.class).error(e);
      }

      HTMLEditorProvider.openEditor(project, title, finalUrl, timeoutContent);
    } else {
      HTMLEditorProvider.openEditor(project, title, content);
    }
  }
}
