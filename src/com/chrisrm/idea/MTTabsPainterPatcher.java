package com.chrisrm.idea;

import com.intellij.openapi.application.ex.ApplicationManagerEx;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorManagerAdapter;
import com.intellij.openapi.fileEditor.FileEditorManagerEvent;
import com.intellij.openapi.fileEditor.FileEditorManagerListener;
import com.intellij.ui.ColorUtil;
import com.intellij.ui.tabs.impl.DefaultEditorTabsPainter;
import com.intellij.ui.tabs.impl.JBEditorTabs;
import com.intellij.ui.tabs.impl.JBEditorTabsPainter;
import com.intellij.util.ReflectionUtil;
import com.intellij.util.messages.MessageBus;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.lang.reflect.Method;

/**
 * @author Dennis.Ushakov
 */
public class MTTabsPainterPatcher implements ApplicationComponent {

    /*final Properties properties = new Properties();
    String theme;

    public MTTabsPainterPatcher() {
        this.theme = new MTDataLayer().getValue("theme", "default");

        try {
            InputStream stream = getClass().getResourceAsStream("/properties/" + this.theme + "/mt-" + this.theme + ".properties");
            properties.load(stream);
            stream.close();
        } catch (IOException e) {
            ;
        }
    }*/

    @Override
    public void initComponent() {
        final MessageBus bus = ApplicationManagerEx.getApplicationEx().getMessageBus();
        bus.connect().subscribe(FileEditorManagerListener.FILE_EDITOR_MANAGER, new FileEditorManagerAdapter() {
            @Override
            public void selectionChanged(@NotNull FileEditorManagerEvent event) {
                final FileEditor editor = event.getNewEditor();
                if (editor != null) {
                    Component component = editor.getComponent();
                    while (component != null) {
                        if (component instanceof JBEditorTabs) {
                            patchPainter((JBEditorTabs)component);
                            return;
                        }
                        component = component.getParent();
                    }
                }
            }
        });
    }

    private void patchPainter(JBEditorTabs component) {
        final JBEditorTabsPainter painter = ReflectionUtil.getField(JBEditorTabs.class, component,
                                                                    JBEditorTabsPainter.class, "myDarkPainter");

        if (painter instanceof MTTabsPainter) return;

        final MTTabsPainter tabsPainter = new MTTabsPainter();
        final JBEditorTabsPainter proxy = (MTTabsPainter)Enhancer.create(MTTabsPainter.class, new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                final Object result = method.invoke(tabsPainter, objects);

                if ("paintSelectionAndBorder".equals(method.getName())) {
                    final Graphics2D g2d = (Graphics2D)objects[0];
                    final Rectangle rect = (Rectangle) objects[1];
                    g2d.setColor(ColorUtil.fromHex("#80CBC4"));
                    g2d.fillRect(rect.x, rect.height - 2, rect.width, 2);
                }

                return result;
            }
        });

        ReflectionUtil.setField(JBEditorTabs.class, component, JBEditorTabsPainter.class, "myDarkPainter", proxy);
    }

    @Override
    public void disposeComponent() {

    }

    @NotNull
    @Override
    public String getComponentName() {
        return "MTTabsPainterPatcher";
    }


    public static class MTTabsPainter extends DefaultEditorTabsPainter {

        /*final static Properties properties = new Properties();
        static String theme;

        static {
            theme = new MTDataLayer().getValue("theme", "default");

            try {
                InputStream stream = MTTabsPainter.class.getResourceAsStream("/properties/" + theme + "/mt-" + theme + ".properties");
                properties.load(stream);
                stream.close();
            } catch (IOException e) {
                ;
            }
        }*/

//        @Override
//        public void paintSelectionAndBorder(Graphics2D g2d, Rectangle rect, JBTabsImpl.ShapeInfo selectedShape, Insets insets, Color tabColor, boolean horizontalTabs) {
//            super.paintSelectionAndBorder(g2d, rect, selectedShape, insets, tabColor, horizontalTabs);
//        }
//
        @Override
        protected Color getDefaultTabColor() {
            if (myDefaultTabColor != null) {
                return myDefaultTabColor;
            }
            //return ColorUtil.fromHex("#"+properties.getProperty("material.tab.background"));
            return ColorUtil.fromHex("#263238");
        }

        @Override
        protected Color getInactiveMaskColor() {
            //return ColorUtil.fromHex("#" + properties.getProperty("material.tab.background"));
            return ColorUtil.fromHex("#263238");
        }
    }
}
