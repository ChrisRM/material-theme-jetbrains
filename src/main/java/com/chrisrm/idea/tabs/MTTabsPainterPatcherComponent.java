/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Chris Magnussen and Elior Boukhobza
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
import com.chrisrm.idea.MTTheme;
import com.chrisrm.idea.config.ConfigNotifier;
import com.chrisrm.idea.themes.MTThemeManager;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.application.ex.ApplicationManagerEx;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorManagerEvent;
import com.intellij.openapi.fileEditor.FileEditorManagerListener;
import com.intellij.openapi.wm.impl.ToolWindowImpl;
import com.intellij.ui.tabs.JBTabsPosition;
import com.intellij.ui.tabs.TabInfo;
import com.intellij.ui.tabs.impl.DefaultEditorTabsPainter;
import com.intellij.ui.tabs.impl.JBEditorTabs;
import com.intellij.ui.tabs.impl.JBEditorTabsPainter;
import com.intellij.ui.tabs.impl.ShapeTransform;
import com.intellij.util.ReflectionUtil;
import com.intellij.util.messages.MessageBus;
import com.intellij.util.messages.MessageBusConnection;
import com.intellij.util.ui.UIUtil;
import javassist.*;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Dennis.Ushakov
 */
public class MTTabsPainterPatcherComponent implements ApplicationComponent {

  public static final String TABS_HEIGHT = "MTTabsHeight";
  private MTTheme theme;
  private MTConfig config;

  public MTTabsPainterPatcherComponent() {
    config = MTConfig.getInstance();
    theme = config.getSelectedTheme();

    PropertiesComponent.getInstance().setValue(TABS_HEIGHT, 25, 24);
  }

  /**
   * Hack ToolWindowHeight to not take TabsUtil.getHeight
   */
  private static void hackToolWindowHeight() {
    // Hack method
    try {
      ClassPool cp = new ClassPool(true);
      cp.insertClassPath(new ClassClassPath(ToolWindowImpl.class));
      CtClass ctClass = cp.get("com.intellij.openapi.wm.impl.ToolWindowHeader");
      CtMethod ctMethod = ctClass.getDeclaredMethod("getPreferredSize");
      ctMethod.instrument(new ExprEditor() {
        public void edit(MethodCall m) throws CannotCompileException {
          if (m.getClassName().equals("com.intellij.ui.tabs.TabsUtil") && m.getMethodName().equals("getTabsHeight")) {
            m.replace("{ $_ = 25; }");
          }
        }
      });
      ctClass.toClass();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Hack TabsUtil,getHeight to override SDK
   */
  private void hackTabsGetHeight() throws
      NotFoundException,
      CannotCompileException,
      IOException,
      IllegalAccessException,
      InvocationTargetException,
      ClassNotFoundException {

    ClassPool cp = new ClassPool(true);
    cp.insertClassPath(new ClassClassPath(TabInfo.class));
    CtClass ctClass = cp.get("com.intellij.ui.tabs.impl.TabLabel");
    CtMethod ctMethod = ctClass.getDeclaredMethod("getPreferredSize");

    ctMethod.instrument(new ExprEditor() {
      public void edit(MethodCall m) throws CannotCompileException {
        if (m.getClassName().equals("com.intellij.ui.tabs.TabsUtil") && m.getMethodName().equals("getTabsHeight")) {
          m.replace("{ $_ = com.intellij.ide.util.PropertiesComponent.getInstance().getInt(\"" + TABS_HEIGHT + "\", 25); }");
        }
      }
    });
    ctClass.toClass();
  }

  @Override
  public void disposeComponent() {

  }

  @NotNull
  @Override
  public String getComponentName() {
    return "MTTabsPainterPatcherComponent";
  }

  @Override
  public void initComponent() {
    final MessageBus bus = ApplicationManagerEx.getApplicationEx().getMessageBus();

    MessageBusConnection connect = bus.connect();
    connect.subscribe(FileEditorManagerListener.FILE_EDITOR_MANAGER, new FileEditorManagerListener() {
      @Override
      public void selectionChanged(@NotNull FileEditorManagerEvent event) {
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

    // Listen to option save to set tab height
    setTabsHeight();
    connect.subscribe(ConfigNotifier.CONFIG_TOPIC, mtConfig -> setTabsHeight());

    try {
      hackTabsGetHeight();
      hackToolWindowHeight();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Patch tabsPainter
   *
   * @param component
   */
  private void patchPainter(JBEditorTabs component) {
    final JBEditorTabsPainter painter = ReflectionUtil.getField(JBEditorTabs.class, component,
        JBEditorTabsPainter.class, "myDarkPainter");

    if (painter instanceof MTTabsPainter) {
      return;
    }

    final MTTabsPainter tabsPainter = new MTTabsPainter(component);
    final JBEditorTabsPainter proxy = (MTTabsPainter) Enhancer.create(MTTabsPainter.class, (MethodInterceptor) (o, method, objects,
                                                                                                                methodProxy) -> {
      final Object result = method.invoke(tabsPainter, objects);
      final Color defaultColor = theme.getBorderColor();

      // Custom props
      boolean isColorEnabled = config.isHighlightColorEnabled();
      Color borderColor = isColorEnabled ? config.getHighlightColor() : defaultColor;
      int borderThickness = config.getHighlightThickness();

      if ("paintSelectionAndBorder".equals(method.getName())) {
        paintSelectionAndBorder(objects, borderColor, borderThickness, tabsPainter);
      }

      return result;
    });

    ReflectionUtil.setField(JBEditorTabs.class, component, JBEditorTabsPainter.class, "myDarkPainter", proxy);
  }

  /**
   * Paint tab selected and highlight border
   *
   * @param objects
   * @param borderColor
   * @param borderThickness
   * @param tabsPainter
   */
  private void paintSelectionAndBorder(Object[] objects,
                                       Color borderColor,
                                       int borderThickness,
                                       MTTabsPainter tabsPainter)
      throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
    // Get the shapeinfo class because it is protected
    Class<?> clazz = Class.forName("com.intellij.ui.tabs.impl.JBTabsImpl$ShapeInfo");

    // Retrieve arguments
    final Graphics2D g2d = (Graphics2D) objects[0];
    final Rectangle rect = (Rectangle) objects[1];
    Object selectedShape = objects[2];
    final Insets insets = (Insets) objects[3];
    final Color tabColor = (Color) objects[4];

    // Retrieve private fields of ShapeInfo class
    Field fillPathField = clazz.getField("fillPath");
    ShapeTransform fillPath = (ShapeTransform) fillPathField.get(selectedShape);
    Field labelPathField = clazz.getField("labelPath");
    ShapeTransform labelPath = (ShapeTransform) labelPathField.get(selectedShape);
    Field pathField = clazz.getField("path");
    ShapeTransform path = (ShapeTransform) pathField.get(selectedShape);

    // Other properties needed for drawing
    int _x = rect.x;
    int _y = rect.y;
    int _height = rect.height;
    Insets i = path.transformInsets(insets);
    int thickness = borderThickness;

    // The tabs component
    JBEditorTabs tabsComponent = tabsPainter.getTabsComponent();

    // Position of tabs
    JBTabsPosition position = tabsComponent.getTabsPosition();

    // color me
    tabsPainter.fillSelectionAndBorder(g2d, fillPath, tabColor, _x, _y, _height);
    g2d.setColor(borderColor);

    if (position == JBTabsPosition.bottom) {
      // Paint on top
      g2d.fillRect(rect.x, rect.y - 1, rect.width, thickness);
    } else if (position == JBTabsPosition.top) {
      // Paint on bottom
      g2d.fillRect(rect.x, rect.y + rect.height - thickness + 1, rect.width, thickness);
      g2d.setColor(UIUtil.CONTRAST_BORDER_COLOR);
      g2d.drawLine(Math.max(0, rect.x - 1), rect.y, rect.x + rect.width, rect.y);
    } else if (position == JBTabsPosition.left) {
      g2d.fillRect(rect.x, rect.y, thickness, rect.height);
    } else if (position == JBTabsPosition.right) {
      g2d.fillRect(rect.x + rect.width - thickness + 1, rect.y, thickness, rect.height);
    }
  }

  private void setTabsHeight() {
    MTThemeManager.getInstance().setTabsHeight();
  }

  public static class MTTabsPainter extends DefaultEditorTabsPainter {
    public MTTabsPainter() {
      super(null);
    }

    public MTTabsPainter(JBEditorTabs tabs) {
      super(tabs);
    }

    public void fillSelectionAndBorder(Graphics2D g, ShapeTransform selectedShape, Color tabColor, int x, int y, int height) {
      g.setColor(tabColor != null ? tabColor : this.getDefaultTabColor());
      g.fill(selectedShape.getShape());
    }

    public Color getBackgroundColor() {
      MTConfig config = MTConfig.getInstance();
      final MTTheme mtTheme = config.getSelectedTheme();
      return mtTheme.getBackgroundColor();
    }

    public Color getContrastColor() {
      MTConfig config = MTConfig.getInstance();
      final MTTheme mtTheme = config.getSelectedTheme();
      return config.getIsContrastMode() ? mtTheme.getContrastColor() : mtTheme.getBackgroundColor();
    }

    public JBEditorTabs getTabsComponent() {
      return this.myTabs;
    }

    public boolean isHorizontalTabs() {
      return this.myTabs.getTabsPosition() == JBTabsPosition.top || this.myTabs.getTabsPosition() == JBTabsPosition.bottom;
    }

    @Override
    protected Color getDefaultTabColor() {
      if (myDefaultTabColor != null) {
        return myDefaultTabColor;
      }

      return this.getBackgroundColor();
    }

    @Override
    protected Color getInactiveMaskColor() {
      return this.getContrastColor();
    }
  }
}

