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

import com.intellij.openapi.application.ex.ApplicationManagerEx;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorManagerEvent;
import com.intellij.openapi.fileEditor.FileEditorManagerListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.startup.StartupActivity;
import com.intellij.openapi.wm.WindowManager;
import com.intellij.ui.tabs.JBTabPainter;
import com.intellij.ui.tabs.TabInfo;
import com.intellij.ui.tabs.impl.JBEditorTabs;
import com.intellij.ui.tabs.impl.TabLabel;
import com.intellij.util.ReflectionUtil;
import com.intellij.util.messages.MessageBus;
import com.intellij.util.messages.MessageBusConnection;
import com.intellij.util.ui.UIUtil;
import com.mallowigi.idea.MTConfig;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;

/**
 * Patch the Tabs Component to get the Material Design style
 *
 * @author Dennis.Ushakov
 */
@SuppressWarnings("WeakerAccess")
public final class MTTabsPainterPatcherComponent implements StartupActivity {
  private MTConfig config = null;

  @SuppressWarnings("OverlyComplexAnonymousInnerClass")
  public void initComponent(final JBEditorTabs editorTabs) {
    config = MTConfig.getInstance();

    if (editorTabs != null) {
      patchPainter(editorTabs);
    }

    final MessageBus bus = ApplicationManagerEx.getApplicationEx().getMessageBus();

    final MessageBusConnection connect = bus.connect();
    connect.subscribe(FileEditorManagerListener.FILE_EDITOR_MANAGER, new FileEditorManagerListener() {
      @Override
      public void selectionChanged(@NotNull final FileEditorManagerEvent event) {
        final FileEditor editor = event.getNewEditor();
        if (editor != null) {
          Component component = editor.getComponent();
          while (component != null) {
            if (component instanceof JBEditorTabs) {
              patchPainter((JBEditorTabs) component);
              return;
            }
            component = component.getParent();
          }
        }
      }
    });
  }

  @Override
  public void runActivity(@NotNull final Project project) {
    final JBEditorTabs editorTabs =
      UIUtil.findComponentOfType(Objects.requireNonNull(WindowManager.getInstance().getIdeFrame(project)).getComponent(),
        JBEditorTabs.class);
    initComponent(editorTabs);
  }

  /**
   * Patch tabsPainter
   */
  void patchPainter(final JBEditorTabs component) {
    final MTTabsPainter tabsPainter = new MTTabsPainter(component);
    final JBTabPainter proxy = (JBTabPainter) Enhancer.create(MTTabsPainter.class, new TabPainterInterceptor(tabsPainter));

    applyCustomFontSize(component);

    ReflectionUtil.setField(JBEditorTabs.class, component, JBTabPainter.class, "myTabPainter", proxy);
  }

  private void applyCustomFontSize(final JBEditorTabs component) {
    if (config.isTabFontSizeEnabled()) {
      final float tabFontSize = config.getTabFontSize();
      final Map<TabInfo, TabLabel> myInfo2Label = component.myInfo2Label;

      for (final TabLabel value : myInfo2Label.values()) {
        final Font font = value.getLabelComponent().getFont().deriveFont(tabFontSize);
        value.getLabelComponent().setFont(font);
      }
    }
  }

  private static class TabPainterInterceptor implements MethodInterceptor {
    private final MTTabsPainter tabsPainter;

    TabPainterInterceptor(final MTTabsPainter tabsPainter) {
      this.tabsPainter = tabsPainter;
    }

    @SuppressWarnings("CallToSuspiciousStringMethod")
    @Override
    public final Object intercept(final Object o, final Method method, final Object[] objects, final MethodProxy methodProxy)
      throws IllegalAccessException, java.lang.reflect.InvocationTargetException {

      return method.invoke(tabsPainter, objects);
    }
  }
}

