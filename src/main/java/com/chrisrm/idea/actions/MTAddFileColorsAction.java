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

package com.chrisrm.idea.actions;

import com.chrisrm.idea.messages.MaterialThemeBundle;
import com.chrisrm.idea.themes.MTDarkerTheme;
import com.chrisrm.idea.themes.MTLighterTheme;
import com.chrisrm.idea.themes.MTOceanicTheme;
import com.chrisrm.idea.themes.MTPalenightTheme;
import com.chrisrm.idea.utils.Notify;
import com.intellij.notification.Notification;
import com.intellij.notification.NotificationListener;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ModalityState;
import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.openapi.project.Project;
import com.intellij.psi.search.scope.NonProjectFilesScope;
import com.intellij.ui.FileColorManager;
import org.jetbrains.annotations.NotNull;

import javax.swing.event.*;

public final class MTAddFileColorsAction extends AnAction {

  @Override
  public void actionPerformed(final AnActionEvent e) {
    addDisabledFileColors(e.getProject());
  }

  private void addDisabledFileColors(final Project project) {
    final FileColorManager manager = FileColorManager.getInstance(project);
    manager.addScopeColor(NonProjectFilesScope.NAME, MTOceanicTheme.DISABLED, false);
    manager.addScopeColor(NonProjectFilesScope.NAME, MTDarkerTheme.DISABLED, false);
    manager.addScopeColor(NonProjectFilesScope.NAME, MTLighterTheme.DISABLED, false);
    manager.addScopeColor(NonProjectFilesScope.NAME, MTPalenightTheme.DISABLED, false);

    Notify.show(project,
                "",
                MaterialThemeBundle.message("mt.fileColorsInstalled"),
                NotificationType.INFORMATION,
                new NotificationListener.Adapter() {
                  @Override
                  protected void hyperlinkActivated(@NotNull final Notification notification, @NotNull final HyperlinkEvent e) {
                    ApplicationManager.getApplication().invokeLater(() -> ShowSettingsUtil.getInstance().showSettingsDialog(
                        project,
                        "File Colors"), ModalityState.NON_MODAL);
                  }
                });
  }
}
