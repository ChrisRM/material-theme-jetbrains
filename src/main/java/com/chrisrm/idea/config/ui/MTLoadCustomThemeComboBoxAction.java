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

package com.chrisrm.idea.config.ui;

import com.chrisrm.idea.MTBundledThemesManager;
import com.chrisrm.idea.MTCustomThemeConfig;
import com.chrisrm.idea.messages.MaterialThemeBundle;
import com.chrisrm.idea.themes.MTThemeFacade;
import com.chrisrm.idea.themes.MTThemes;
import com.chrisrm.idea.themes.models.MTBundledTheme;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.actionSystem.ex.ComboBoxAction;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.components.panels.NonOpaquePanel;
import com.intellij.util.ui.JBUI;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

final class MTLoadCustomThemeComboBoxAction extends ComboBoxAction {
  final MTCustomThemeForm mtCustomThemeForm;
  final MTCustomThemeConfig customThemeConfig;

  MTLoadCustomThemeComboBoxAction(final MTCustomThemeForm mtCustomThemeForm) {
    this.mtCustomThemeForm = mtCustomThemeForm;
    customThemeConfig = MTCustomThemeConfig.getInstance();
  }

  @Override
  public void update(@NotNull final AnActionEvent e) {
    super.update(e);
    e.getPresentation().setText(MaterialThemeBundle.message("MTCustomThemeForm.loadFromButton.title"));
  }

  @Override
  protected int getMinHeight() {
    return 40;
  }

  @NotNull
  @Override
  public JComponent createCustomComponent(@NotNull final Presentation presentation) {
    final ComboBoxButton comboBoxButton = new ComboBoxButton(presentation);
    comboBoxButton.setFont(comboBoxButton.getFont().deriveFont(Font.BOLD));
    final NonOpaquePanel panel = new NonOpaquePanel(new BorderLayout());
    final Border border = JBUI.Borders.empty(0);

    panel.setBorder(border);
    panel.add(comboBoxButton);
    return panel;
  }

  @SuppressWarnings({"FeatureEnvy",
      "ObjectAllocationInLoop"})
  @NotNull
  @Override
  protected DefaultActionGroup createPopupActionGroup(final JComponent button) {
    final DefaultActionGroup group = new DefaultActionGroup(null, true);

    for (final MTThemeFacade name : MTThemes.getAllThemes()) {
      group.add(new AnAction(name.getThemeName(), name.getThemeName(), name.getIcon()) {
        @Override
        public void actionPerformed(@NotNull final AnActionEvent e) {
          customThemeConfig.importFrom(name.getTheme());
          mtCustomThemeForm.setFormState(customThemeConfig);
        }
      });
    }
    group.addSeparator();
    group.add(new AnAction(MaterialThemeBundle.message("MTCustomThemeForm.loadFromButton.fromDisk")) {
      private void loadTheme(final VirtualFile virtualFile) {
        try {
          final MTBundledTheme theme = MTBundledThemesManager.loadBundledTheme(virtualFile);
          assert theme != null;

          customThemeConfig.importFrom(theme);
          mtCustomThemeForm.setFormState(customThemeConfig);
        } catch (final IOException ex) {
          // Show a notification error
          Messages.showDialog(ex.getMessage(),
              MaterialThemeBundle.message("MTCustomThemeForm.comboboxAction.errorLoading"),
              new String[]{MaterialThemeBundle.message("common.ok")},
              0,
              Messages.getErrorIcon());
        }
      }

      @Override
      public void actionPerformed(@NotNull final AnActionEvent e) {
        final FileChooserDescriptor descriptor = new MyFileChooserDescriptor();
        descriptor.setTitle(MaterialThemeBundle.message("MTCustomThemeForm.importButton.selectFile"));

        final String oldPath = PropertiesComponent.getInstance().getValue("plugins.preselection.path");
        final VirtualFile toSelect = oldPath == null ? null :
                                     VfsUtil.findFileByIoFile(new File(FileUtil.toSystemDependentName(oldPath)), false);

        FileChooser.chooseFile(descriptor, null, null, toSelect, this::loadTheme);
      }
    });
    return group;
  }

  private static final class MyFileChooserDescriptor extends FileChooserDescriptor {
    MyFileChooserDescriptor() {
      super(true, false, false, false, false, false);
    }

    @Override
    public boolean isFileSelectable(final VirtualFile file) {
      return Objects.equals(file.getExtension(), "xml");
    }
  }
}
