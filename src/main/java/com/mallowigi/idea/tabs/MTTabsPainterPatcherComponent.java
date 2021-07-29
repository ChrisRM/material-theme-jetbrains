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

package com.mallowigi.idea.tabs;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ModalityState;
import com.intellij.openapi.application.ex.ApplicationManagerEx;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorManagerEvent;
import com.intellij.openapi.fileEditor.FileEditorManagerListener;
import com.intellij.openapi.fileEditor.ex.FileEditorManagerEx;
import com.intellij.openapi.fileEditor.impl.EditorWindow;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupActivity;
import com.intellij.openapi.wm.WindowManager;
import com.intellij.ui.SimpleTextAttributes;
import com.intellij.ui.tabs.JBTabPainter;
import com.intellij.ui.tabs.TabInfo;
import com.intellij.ui.tabs.impl.JBEditorTabs;
import com.intellij.ui.tabs.impl.TabLabel;
import com.intellij.util.ReflectionUtil;
import com.intellij.util.messages.MessageBusConnection;
import com.intellij.util.ui.UIUtil;
import com.mallowigi.idea.config.application.MTConfig;
import com.mallowigi.idea.config.project.MTProjectConfig;
import com.mallowigi.idea.listeners.ConfigNotifier;
import com.mallowigi.idea.utils.MTUiUtils;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Patch the Tabs Component to get the Material Design style
 *
 * @author Dennis.Ushakov
 */
@SuppressWarnings("WeakerAccess")
public final class MTTabsPainterPatcherComponent implements StartupActivity.Background, Disposable {
  private MTConfig config = null;
  private final MessageBusConnection connect = ApplicationManagerEx.getApplicationEx().getMessageBus().connect();

  @SuppressWarnings("OverlyComplexAnonymousInnerClass")
  public void initComponent(final JBEditorTabs editorTabs,
                            final @NotNull Project project) {
    config = MTConfig.getInstance();

    if (editorTabs != null) {
      patchPainter(editorTabs, project);
    }

    connect.subscribe(FileEditorManagerListener.FILE_EDITOR_MANAGER, new FileEditorManagerListener() {
      @Override
      public void selectionChanged(@NotNull final FileEditorManagerEvent event) {
        final FileEditor editor = event.getNewEditor();
        if (editor != null) {
          Component component = editor.getComponent();
          while (component != null) {
            if (component instanceof JBEditorTabs) {
              patchPainter((JBEditorTabs) component, project);
              return;
            }
            component = component.getParent();
          }
        }
      }
    });
    connect.subscribe(ConfigNotifier.CONFIG_TOPIC, new ConfigNotifier() {
      @Override
      public void configChanged(final MTConfig mtConfig) {
        ApplicationManager.getApplication().invokeAndWait(() -> resetTabsBoldness(project), ModalityState.NON_MODAL);
      }

      @Override
      public void projectConfigChanged(final MTProjectConfig mtConfig) {
        ApplicationManager.getApplication().invokeAndWait(() -> resetTabsBoldness(project), ModalityState.NON_MODAL);
      }

    });
  }

  @Override
  public void runActivity(@NotNull final Project project) {
    final JBEditorTabs editorTabs =
      UIUtil.findComponentOfType(Objects.requireNonNull(WindowManager.getInstance().getIdeFrame(project)).getComponent(),
        JBEditorTabs.class);
    initComponent(editorTabs, project);
  }

  @Override
  public void dispose() {
    connect.disconnect();
  }

  /**
   * Patch tabsPainter
   */
  void patchPainter(final JBEditorTabs component, final @NotNull Project project) {
    final MTTabsPainter tabsPainter = new MTTabsPainter(component, project);
    final JBTabPainter proxy = (JBTabPainter) Enhancer.create(MTTabsPainter.class, new TabPainterInterceptor(tabsPainter));

    ApplicationManager.getApplication().invokeLater(() -> {
      applyCustomFontSize(component, project);
      applyBoldTabs(component, project);
    });

    ReflectionUtil.setField(JBEditorTabs.class, component, JBTabPainter.class, "myTabPainter", proxy);
  }

  private void applyCustomFontSize(final JBEditorTabs component,
                                   final @NotNull Project project) {
    if (config.isTabFontSizeEnabled()) {
      final float tabFontSize = config.getTabFontSize();
      final Map<TabInfo, TabLabel> myInfo2Label = component.myInfo2Label;

      for (final TabLabel value : myInfo2Label.values()) {
        final Font font = value.getLabelComponent().getFont().deriveFont(tabFontSize);
        value.getLabelComponent().setFont(font);
      }
    }
  }

  private void applyBoldTabs(final JBEditorTabs component,
                             final @NotNull Project project) {
    if (isActiveBoldTab(project)) {
      final TabInfo selectedInfo = component.getSelectedInfo();
      final Map<TabInfo, TabLabel> myInfo2Label = component.myInfo2Label;

      for (final TabLabel value : myInfo2Label.values()) {
        value.getInfo().setDefaultStyle(SimpleTextAttributes.STYLE_PLAIN);
      }

      if (selectedInfo != null) {
        selectedInfo.setDefaultStyle(SimpleTextAttributes.STYLE_BOLD);
      }
    }
  }

  private boolean isActiveBoldTab(final @NotNull Project project) {
    final MTProjectConfig projectConfig = MTUiUtils.getProjectConfigIfEnabled(project);
    if (projectConfig != null) {
      return projectConfig.isActiveBoldTab();
    }
    return config.isActiveBoldTab();
  }

  void resetTabsBoldness(final @NotNull Project project) {
    final FileEditorManagerEx manager = FileEditorManagerEx.getInstanceEx(project);
    final EditorWindow[] windows = manager.getWindows();
    for (final EditorWindow editorWindow : windows) {
      final TabInfo selectedInfo = editorWindow.getTabbedPane().getTabs().getSelectedInfo();
      final List<TabInfo> tabs = editorWindow.getTabbedPane().getTabs().getTabs();

      for (final TabInfo tab : tabs) {
        if (tab == selectedInfo) {
          final int style = isActiveBoldTab(project) ? SimpleTextAttributes.STYLE_BOLD : SimpleTextAttributes.STYLE_PLAIN;
          tab.setDefaultStyle(style);
        } else {
          tab.setDefaultStyle(SimpleTextAttributes.STYLE_PLAIN);
        }
      }
    }
  }

  private static class TabPainterInterceptor implements MethodInterceptor {
    private final MTTabsPainter tabsPainter;

    TabPainterInterceptor(final MTTabsPainter tabsPainter) {
      this.tabsPainter = tabsPainter;
    }

    @Override
    public final Object intercept(final Object o, final Method method, final Object[] objects, final MethodProxy methodProxy)
      throws IllegalAccessException, InvocationTargetException {

      return method.invoke(tabsPainter, objects);
    }
  }
}

