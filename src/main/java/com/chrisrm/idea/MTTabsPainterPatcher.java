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
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * @author Dennis.Ushakov
 */
public class MTTabsPainterPatcher implements ApplicationComponent {

    private final Properties properties = new Properties();
    private MTTheme theme;

    public MTTabsPainterPatcher() {
        theme = MTTheme.getCurrentPreference();

        try {
            InputStream stream = getClass().getResourceAsStream(theme.getId() + ".properties");
            properties.load(stream);
            stream.close();
        } catch (IOException e) {
            ;
        }
    }

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
                            patchPainter((JBEditorTabs) component);
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

        final MTTabsPainter tabsPainter = new MTTabsPainter(component);
        final JBEditorTabsPainter proxy = (MTTabsPainter) Enhancer.create(MTTabsPainter.class, new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                final Object result = method.invoke(tabsPainter, objects);

                if ("paintSelectionAndBorder".equals(method.getName())) {
                    final Graphics2D g2d = (Graphics2D) objects[0];
                    final Rectangle rect = (Rectangle) objects[1];

                    g2d.setColor(ColorUtil.fromHex("#" + properties.getProperty("material.tab.borderColor")));
                    g2d.fillRect(rect.x, rect.y + rect.height - 2, rect.width, 2);
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

        public MTTabsPainter(JBEditorTabs tabs) {
            super(tabs);
        }

        @Override
        protected Color getDefaultTabColor() {
            if (myDefaultTabColor != null) {
                return myDefaultTabColor;
            }

            Properties properties = getProperties();

            return ColorUtil.fromHex("#" + properties.getProperty("material.tab.backgroundColor"));
        }

        @Override
        protected Color getInactiveMaskColor() {
            return this.getDefaultTabColor();
        }

        private Properties getProperties() {
            Properties properties = new Properties();
            MTTheme theme = MTTheme.getCurrentPreference();

            try {
                InputStream stream = MTTabsPainter.class.getResourceAsStream(theme.getId() + ".properties");
                properties.load(stream);
                stream.close();
            } catch (IOException e) {}

            return properties;
        }
    }
}
