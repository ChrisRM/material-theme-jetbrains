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

package com.chrisrm.idea.config.ui.load;

import com.chrisrm.idea.MTBundledThemesManager;
import com.chrisrm.idea.MTCustomThemeConfig;
import com.chrisrm.idea.config.ui.MTCustomThemeForm;
import com.chrisrm.idea.messages.MaterialThemeBundle;
import com.chrisrm.idea.themes.MTThemeFacade;
import com.chrisrm.idea.themes.MTThemes;
import com.chrisrm.idea.themes.models.MTBundledTheme;
import com.intellij.icons.AllIcons;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.actionSystem.ex.ComboBoxAction;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
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
import java.util.Objects;

public final class MTLoadCustomThemeComboBoxAction extends ComboBoxAction {
  private final MTCustomThemeForm mtCustomThemeForm;
  private final MTCustomThemeConfig customThemeConfig;

  public MTLoadCustomThemeComboBoxAction(final MTCustomThemeForm mtCustomThemeForm) {
    this.mtCustomThemeForm = mtCustomThemeForm;
    customThemeConfig = MTCustomThemeConfig.getInstance();
  }

  @Override
  public void update(@NotNull final AnActionEvent e) {
    super.update(e);
    e.getPresentation().setIcon(AllIcons.General.GearPlain);
  }

  @Override
  protected int getMinHeight() {
    return 40;
  }

  @NotNull
  @Override
  public JComponent createCustomComponent(@NotNull final Presentation presentation) {
    final ComboBoxButton comboBoxButton = new ComboBoxButton(presentation);
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
    group.addSeparator(MaterialThemeBundle.message("MTCustomThemeForm.loadFromButton.loadFrom"));
    for (final MTThemeFacade name : MTThemes.getAllThemes()) {
      group.add(new AnAction(name.getThemeName(), name.getThemeName(), name.getIcon()) {
        @Override
        public void actionPerformed(@NotNull final AnActionEvent e) {
          customThemeConfig.importFrom(name.getTheme());
          mtCustomThemeForm.setFormState(customThemeConfig);
        }
      });
    }
    group.addSeparator(MaterialThemeBundle.message("MTCustomThemeForm.loadFromButton.loadFromDisk"));
    group.add(new AnAction(MaterialThemeBundle.message("MTCustomThemeForm.loadFromButton.fromDisk")) {
      private void loadTheme(final VirtualFile virtualFile) {
        final MTBundledTheme theme = MTBundledThemesManager.loadBundledTheme(virtualFile);
        assert theme != null;

        customThemeConfig.importFrom(theme);
        mtCustomThemeForm.setFormState(customThemeConfig);
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
    group.addSeparator(MaterialThemeBundle.message("MTCustomThemeForm.loadFromButton.save"));
    group.add(new AnAction(MaterialThemeBundle.message("MTCustomThemeForm.loadFromButton.saveAs")) {
      @Override
      public void actionPerformed(@NotNull final AnActionEvent e) {
        final MTSaveCustomThemeDialog dialog =
            new MTSaveCustomThemeDialog(SwingUtilities.getWindowAncestor(mtCustomThemeForm.getContent()));
        dialog.setVisible(true);
        //        final MTBundledTheme customTheme = MTCustomThemeConfig.export(mtCustomThemeForm);
        //        MTBundledThemesManager.saveTheme(customTheme);
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
