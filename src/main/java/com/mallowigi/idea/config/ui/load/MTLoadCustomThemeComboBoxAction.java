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

package com.mallowigi.idea.config.ui.load;

import com.intellij.icons.AllIcons;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.actionSystem.ex.ComboBoxAction;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.components.panels.NonOpaquePanel;
import com.intellij.util.ui.JBUI;
import com.mallowigi.idea.MTBundledThemesManager;
import com.mallowigi.idea.config.custom.MTCustomThemeConfig;
import com.mallowigi.idea.config.ui.MTCustomThemeForm;
import com.mallowigi.idea.messages.MaterialThemeBundle;
import com.mallowigi.idea.themes.MTThemeFacade;
import com.mallowigi.idea.themes.MTThemes;
import com.mallowigi.idea.themes.models.MTBundledTheme;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.util.Objects;

@SuppressWarnings("SyntheticAccessorCall")
public final class MTLoadCustomThemeComboBoxAction extends ComboBoxAction {
  private final MTCustomThemeForm mtCustomThemeForm;
  private final MTCustomThemeConfig customThemeConfig;

  public MTLoadCustomThemeComboBoxAction(final MTCustomThemeForm mtCustomThemeForm) {
    this.mtCustomThemeForm = mtCustomThemeForm;
    customThemeConfig = MTCustomThemeConfig.getInstance().clone();
  }

  @Override
  public void update(@NotNull final AnActionEvent e) {
    super.update(e);
    e.getPresentation().setIcon(AllIcons.General.GearPlain);
  }

  @NotNull
  @Override
  public JComponent createCustomComponent(@NotNull final Presentation presentation) {
    return createCustomComponent(presentation, ActionPlaces.STATUS_BAR_PLACE);
  }

  @NotNull
  @Override
  public JComponent createCustomComponent(@NotNull final Presentation presentation, @NotNull final String place) {
    final ComboBoxButton comboBoxButton = new ComboBoxButton(presentation);
    final NonOpaquePanel panel = new NonOpaquePanel(new BorderLayout());
    final Border border = JBUI.Borders.empty(0);

    panel.setBorder(border);
    panel.add(comboBoxButton);
    return panel;
  }

  @SuppressWarnings({"FeatureEnvy",
    "ObjectAllocationInLoop",
    "ContinueStatement",
    "OverlyComplexAnonymousInnerClass"})
  @NotNull
  @Override
  protected DefaultActionGroup createPopupActionGroup(final JComponent button) {
    final DefaultActionGroup group = new DefaultActionGroup(null, true);
    group.addSeparator(MaterialThemeBundle.message("MTCustomThemeForm.loadFromButton.loadFrom"));
    for (final MTThemeFacade theme : MTThemes.getAllThemes()) {
      if (theme.isCustom()) {
        continue;
      }
      group.add(new AnAction(theme.getThemeName(), theme.getThemeName(), theme.getIcon()) {
        @Override
        public void actionPerformed(@NotNull final AnActionEvent e) {
          customThemeConfig.importFrom(theme.getTheme());
          mtCustomThemeForm.setFormState(customThemeConfig);
        }
      });
    }
    group.addSeparator(MaterialThemeBundle.message("MTCustomThemeForm.loadFromButton.loadFromDisk"));
    group.add(new AnAction(MaterialThemeBundle.message("MTCustomThemeForm.loadFromButton.fromDisk"),
      MaterialThemeBundle.message("load.an.external.theme.into.your.custom.theme.colors"),
      AllIcons.Actions.Install) {
      @Override
      public void actionPerformed(@NotNull final AnActionEvent e) {
        final FileChooserDescriptor descriptor = new MyFileChooserDescriptor();
        descriptor.setTitle(MaterialThemeBundle.message("MTCustomThemeForm.importButton.selectFile"));

        final String oldPath = PropertiesComponent.getInstance().getValue("plugins.preselection.path");
        final VirtualFile toSelect = oldPath == null ? null :
                                     VfsUtil.findFileByIoFile(new File(FileUtil.toSystemDependentName(oldPath)), false);

        FileChooser.chooseFile(descriptor, null, null, toSelect, this::loadTheme);
      }

      private void loadTheme(final VirtualFile virtualFile) {
        final MTBundledTheme theme = MTBundledThemesManager.loadBundledTheme(virtualFile);
        if (theme == null) {
          Messages.showErrorDialog(MaterialThemeBundle.message("error.parsing.xml.file.make.sure.that.this.is.a.valid.external.theme" +
            ".file"), MaterialThemeBundle.message("error"));
          return;
        }

        customThemeConfig.importFrom(theme);
        mtCustomThemeForm.setFormState(customThemeConfig);
        Messages.showDialog((Project) null,
          String.format(MaterialThemeBundle.message("MTCustomThemeForm.loadFromButton.success"), virtualFile.getName()),
          MaterialThemeBundle.message("MTCustomThemeForm.loadFromButton.importSuccess"),
          new String[]{MaterialThemeBundle.message("common.ok")},
          0,
          Messages.getInformationIcon());
      }
    });
    group.addSeparator(MaterialThemeBundle.message("MTCustomThemeForm.loadFromButton.save"));
    group.add(new AnAction(MaterialThemeBundle.message("MTCustomThemeForm.loadFromButton.saveAs"),
      MaterialThemeBundle.message("save.your.custom.theme.as.an.external.theme"),
      AllIcons.Actions.Menu_saveall) {
      @Override
      public void actionPerformed(@NotNull final AnActionEvent e) {
        new MTSaveCustomThemeDialog(mtCustomThemeForm).show();
      }
    });
    return group;
  }

  @SuppressWarnings("MagicNumber")
  @Override
  protected int getMinHeight() {
    return 40;
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
