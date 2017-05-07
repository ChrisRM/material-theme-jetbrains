package com.chrisrm.idea.ui;

import com.intellij.openapi.wm.IdeFrame;
import com.intellij.openapi.wm.WelcomeScreen;
import com.intellij.openapi.wm.impl.welcomeScreen.FlatWelcomeFrame;
import com.intellij.ui.JBColor;
import com.intellij.ui.components.JBList;
import com.intellij.util.ReflectionUtil;
import com.intellij.util.ui.UIUtil;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Method;

public class MTFlatWelcomeFrame extends FlatWelcomeFrame implements IdeFrame {

    MTFlatWelcomeFrame() {
        super();
        Color color = UIManager.getColor("Panel.background");
        Color listColor = UIManager.getColor("List.background");

        // Set welcome frame bg
        WelcomeScreen myScreen = ReflectionUtil.getField(FlatWelcomeFrame.class, this, WelcomeScreen.class, "myScreen");
        JPanel welcomeScreen = (JPanel) myScreen;
        welcomeScreen.setBackground(color);

        // Set projects bg
        JBList list = UIUtil.findComponentOfType(welcomeScreen, JBList.class);
        if (list != null) {
            list.setBackground(new JBColor(listColor, listColor));

            ListCellRenderer renderer = list.getCellRenderer();
            MTProjectsRenderer mtProjectsRenderer = new MTProjectsRenderer();
            // All this crap just because Jetbrains didnt' bother using UIManager for their colors -_-
            final ListCellRenderer proxy = (ListCellRenderer) Enhancer.create(ListCellRenderer.class, new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    Object result = method.invoke(renderer, objects);

                    if (result instanceof JPanel && "getListCellRendererComponent".equals(method.getName())) {
                        mtProjectsRenderer.getListCellRenderedComponent((JPanel) result, method, objects);
                    }

                    return result;
                }
            });

            list.setCellRenderer(proxy);
        }
    }

    public static class MTProjectsRenderer extends DefaultListCellRenderer {

        void getListCellRenderedComponent(JPanel result, Method method, Object[] objects) {
            JList list = (JList) objects[0];
            boolean isSelected = (boolean) objects[3];

            result.setBackground(getListBackground(isSelected, list.hasFocus()));
        }

        Color getListBackground(boolean isSelected, boolean hasFocus) {
            return UIUtil.getListBackground(isSelected);
        }

        protected Color getListForeground(boolean isSelected, boolean hasFocus) {
            return UIUtil.getListForeground(isSelected);
        }

    }
}
