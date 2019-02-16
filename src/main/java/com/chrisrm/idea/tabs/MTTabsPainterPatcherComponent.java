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

package com.chrisrm.idea.tabs;

import com.chrisrm.idea.MTConfig;
import com.chrisrm.idea.tabs.shadowPainters.*;
import com.chrisrm.idea.utils.MTAccents;
import com.intellij.openapi.application.ex.ApplicationManagerEx;
import com.intellij.openapi.components.BaseComponent;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorManagerEvent;
import com.intellij.openapi.fileEditor.FileEditorManagerListener;
import com.intellij.ui.ColorUtil;
import com.intellij.ui.tabs.JBTabsPosition;
import com.intellij.ui.tabs.TabInfo;
import com.intellij.ui.tabs.impl.JBEditorTabs;
import com.intellij.ui.tabs.impl.JBEditorTabsPainter;
import com.intellij.ui.tabs.impl.ShapeTransform;
import com.intellij.ui.tabs.impl.TabLabel;
import com.intellij.util.ObjectUtils;
import com.intellij.util.ReflectionUtil;
import com.intellij.util.messages.MessageBus;
import com.intellij.util.messages.MessageBusConnection;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Patch the Tabs Component to get the Material Design style
 *
 * @author Dennis.Ushakov
 */
@SuppressWarnings("WeakerAccess")
public final class MTTabsPainterPatcherComponent implements BaseComponent {

  private final MTConfig config;
  private final Field pathField;
  private final Field fillPathField;
  private final Field labelPathField;
  private MessageBusConnection connect;

  public MTTabsPainterPatcherComponent() throws ClassNotFoundException, NoSuchFieldException {
    config = MTConfig.getInstance();

    // Get the shape info class because it is protected
    final Class<?> clazz = Class.forName("com.intellij.ui.tabs.impl.JBTabsImpl$ShapeInfo");
    // Retrieve private fields of ShapeInfo class
    pathField = clazz.getField("path");
    fillPathField = clazz.getField("fillPath");
    labelPathField = clazz.getField("labelPath");
  }

  @Override
  public void disposeComponent() {
    connect.disconnect();
  }

  @NonNls
  @NotNull
  @Override
  public String getComponentName() {
    return "MTTabsPainterPatcherComponent";
  }

  @SuppressWarnings("OverlyComplexAnonymousInnerClass")
  @Override
  public void initComponent() {
    final MessageBus bus = ApplicationManagerEx.getApplicationEx().getMessageBus();

    connect = bus.connect();
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

  /**
   * Patch tabsPainter
   */
  void patchPainter(final JBEditorTabs component) {
    final Color accentColor = ObjectUtils.notNull(ColorUtil.fromHex(config.getAccentColor()), MTAccents.TURQUOISE.getColor());
    final MTTabsPainter tabsPainter = new MTTabsPainter(component);
    final JBEditorTabsPainter proxy = (JBEditorTabsPainter) Enhancer.create(MTTabsPainter.class, new MyMethodInterceptor(tabsPainter,
        accentColor));

    applyCustomFontSize(component);
    ReflectionUtil.setField(JBEditorTabs.class, component, JBEditorTabsPainter.class, "myDefaultPainter", proxy);
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

  /**
   * Paint tab selected and highlight border
   */
  private void paintSelectionAndBorder(final Object[] objects,
                                       final Color borderColor,
                                       final int borderThickness,
                                       final MTTabsPainter tabsPainter)
      throws IllegalAccessException {

    // Retrieve arguments
    final Graphics2D g2d = (Graphics2D) objects[0];
    final Rectangle rect = (Rectangle) objects[1];
    final Object selectedShape = objects[2];
    final Color tabColor = (Color) objects[4];

    final ShapeTransform path = (ShapeTransform) pathField.get(selectedShape);
    final ShapeTransform fillPath = (ShapeTransform) fillPathField.get(selectedShape);
    final ShapeTransform labelPath = (ShapeTransform) labelPathField.get(selectedShape);

    // Other properties needed for drawing
    final int rectX = rect.x;
    final int rectY = rect.y;
    final int rectHeight = rect.height;

    // The tabs component
    final JBEditorTabs tabsComponent = tabsPainter.getTabsComponent();

    // Position of tabs
    final JBTabsPosition position = tabsComponent.getTabsPosition();

    // color me
    tabsPainter.fillSelectionAndBorder(g2d, fillPath, tabColor, rectX, rectY, rectHeight);

    // shadow
    if (MTConfig.getInstance().isTabsShadow()) {
      drawTabShadow(tabsPainter, g2d, rect, path, labelPath, position);
    }

    // Finally paint the active tab highlighter
    g2d.setColor(borderColor);
    MTTabsHighlightPainter.paintHighlight(borderThickness, g2d, rect);
  }

  private static void drawTabShadow(final MTTabsPainter tabsPainter,
                                    final Graphics2D g2d,
                                    final Rectangle rect,
                                    final ShapeTransform path,
                                    final ShapeTransform labelPath,
                                    final JBTabsPosition position) {
    final ShadowPainter shadowPainter = getShadowPainter(position);
    shadowPainter.drawShadow(tabsPainter, g2d, path, labelPath, rect);
  }

  @SuppressWarnings("MethodWithMultipleReturnPoints")
  public static ShadowPainter getShadowPainter(final JBTabsPosition position) {
    switch (position) {
      case top:
        return new BottomShadowPainter();
      case bottom:
        return new TopShadowPainter();
      case left:
        return new RightShadowPainter();
      case right:
        return new LeftShadowPainter();
      default:
        return new NoneShadowPainter();
    }
  }

  @SuppressWarnings("WeakerAccess")
  private class MyMethodInterceptor implements MethodInterceptor {
    private final MTTabsPainter tabsPainter;
    private final Color accentColor;

    MyMethodInterceptor(final MTTabsPainter tabsPainter, final Color accentColor) {
      this.tabsPainter = tabsPainter;
      this.accentColor = accentColor;
    }

    @SuppressWarnings({"HardCodedStringLiteral",
        "CallToSuspiciousStringMethod",
        "SyntheticAccessorCall",
        "FeatureEnvy"})
    @Override
    public final Object intercept(final Object o, final Method method, final Object[] objects, final MethodProxy methodProxy)
        throws IllegalAccessException, java.lang.reflect.InvocationTargetException {
      final Object result = method.invoke(tabsPainter, objects);

      // Custom props
      final boolean isColorEnabled = config.isHighlightColorEnabled();
      final Color borderColor = isColorEnabled ? config.getHighlightColor() : accentColor;
      final int borderThickness = config.getHighlightThickness();

      if ("paintSelectionAndBorder".equals(method.getName())) {
        paintSelectionAndBorder(objects, borderColor, borderThickness, tabsPainter);
      }

      return result;
    }
  }
}

