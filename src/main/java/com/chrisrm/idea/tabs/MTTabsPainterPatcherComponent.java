package com.chrisrm.idea.tabs;

import com.chrisrm.idea.MTConfig;
import com.chrisrm.idea.MTTheme;
import com.intellij.openapi.application.ex.ApplicationManagerEx;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorManagerAdapter;
import com.intellij.openapi.fileEditor.FileEditorManagerEvent;
import com.intellij.openapi.fileEditor.FileEditorManagerListener;
import com.intellij.ui.tabs.JBTabsPosition;
import com.intellij.ui.tabs.impl.DefaultEditorTabsPainter;
import com.intellij.ui.tabs.impl.JBEditorTabs;
import com.intellij.ui.tabs.impl.JBEditorTabsPainter;
import com.intellij.ui.tabs.impl.ShapeTransform;
import com.intellij.util.ReflectionUtil;
import com.intellij.util.messages.MessageBus;
import com.intellij.util.ui.UIUtil;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * @author Dennis.Ushakov
 */
public class MTTabsPainterPatcherComponent implements ApplicationComponent {

  private final Properties properties = new Properties();
  private MTTheme theme;
  private MTConfig config;

  public MTTabsPainterPatcherComponent() {
    config = MTConfig.getInstance();
    theme = config.getSelectedTheme();
  }

  @Override
  public void initComponent() {
    final MessageBus bus = ApplicationManagerEx.getApplicationEx().getMessageBus();

    bus.connect()
        .subscribe(FileEditorManagerListener.FILE_EDITOR_MANAGER, new FileEditorManagerAdapter() {
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
  }

  @Override
  public void disposeComponent() {

  }

  @NotNull
  @Override
  public String getComponentName() {
    return "MTTabsPainterPatcherComponent";
  }

  private void patchPainter(JBEditorTabs component) {
    final JBEditorTabsPainter painter = ReflectionUtil.getField(JBEditorTabs.class, component,
                                                                JBEditorTabsPainter.class, "myDarkPainter");

    if (painter instanceof MTTabsPainter) {
      return;
    }

    final MTTabsPainter tabsPainter = new MTTabsPainter(component);
    final JBEditorTabsPainter proxy = (MTTabsPainter) Enhancer.create(MTTabsPainter.class, new MethodInterceptor() {
      @Override
      public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
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
      }
    });

    ReflectionUtil.setField(JBEditorTabs.class, component, JBEditorTabsPainter.class, "myDarkPainter", proxy);
  }

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
    boolean horizontalTabs = tabsPainter.isHorizontalTabs();

    // color me
    tabsPainter.fillSelectionAndBorder(g2d, fillPath, tabColor, _x, _y, _height);
    g2d.setColor(borderColor);

    if (position == JBTabsPosition.bottom) {
      // Paint on top
      g2d.fillRect(rect.x, rect.y - 1, rect.width, thickness);
    }
    else if (position == JBTabsPosition.top) {
      // Paint on bottom
      g2d.fillRect(rect.x, rect.y + rect.height - thickness + 1, rect.width, thickness);
      g2d.setColor(UIUtil.CONTRAST_BORDER_COLOR);
      g2d.drawLine(Math.max(0, rect.x - 1), rect.y, rect.x + rect.width, rect.y);
    }
    else if (position == JBTabsPosition.left) {
      g2d.fillRect(rect.x, rect.y, thickness, rect.height);
    }
    else if (position == JBTabsPosition.right) {
      g2d.fillRect(rect.x + rect.width - thickness + 1, rect.y, thickness, rect.height);
    }
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

