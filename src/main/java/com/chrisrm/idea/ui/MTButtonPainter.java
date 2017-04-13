/*
 * Copyright 2000-2015 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.chrisrm.idea.ui;

import com.intellij.ide.ui.laf.darcula.DarculaUIUtil;
import com.intellij.ide.ui.laf.darcula.ui.DarculaButtonUI;
import com.intellij.openapi.actionSystem.ActionToolbar;
import com.intellij.openapi.ui.GraphicsConfig;
import com.intellij.ui.Gray;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.UIUtil;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.UIResource;
import java.awt.*;

/**
 * @author Konstantin Bulenkov
 */
public class MTButtonPainter implements Border, UIResource {
    private static final int myOffset = 4;

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
    }

    protected Color getBorderColor() {
        return Gray._100.withAlpha(180);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return JBUI.insets(16, 16, 16, 16).asUIResource();
    }

    protected int getOffset() {
        return myOffset;
    }

    @Override
    public boolean isBorderOpaque() {
        return false;
    }
}
