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

package com.chrisrm.idea.themes;

import com.chrisrm.idea.MTConfig;
import com.google.common.collect.Sets;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.ui.ColorUtil;
import com.intellij.ui.JBColor;

import java.awt.*;
import java.util.Collections;
import java.util.Set;

import static com.chrisrm.idea.themes.lists.AccentResources.ACCENT_EXTRA_RESOURCES;
import static com.chrisrm.idea.utils.MTUiUtils.buildResources;

public final class MTAccentMode {

  public static MTAccentMode getInstance() {
    return ServiceManager.getService(MTAccentMode.class);
  }

  public void buildAllResources() {
    final Color accentColor = ColorUtil.fromHex(MTConfig.getInstance().getAccentColor());
    // Add accent resources
    buildResources(ACCENT_EXTRA_RESOURCES, accentColor);
    // Add new selection color resources
    buildResources(getResources(), getSelectionColor());
  }

  private Color getSelectionColor() {
    return new JBColor(0x111111, 0xFFFFFF);
  }

  private Set<String> getResources() {
    return Collections.unmodifiableSet(
        Sets.newHashSet(
            "CheckBox.darcula.checkSignColor",
            "CheckBox.darcula.checkSignColor.selected",
            "CompletionPopup.matchForeground",
            "CompletionPopup.matchSelectedForeground", // deprecated
            "CompletionPopup.matchSelectionForeground",
            "DefaultTabs.inactiveUnderlineColor", // deprecated
            "DefaultTabs.underlineColor",
            "EditorTabs.active.underlineColor", // deprecated
            "EditorTabs.inactiveUnderlineColor",
            "EditorTabs.active.foreground", // deprecated
            "EditorTabs.selectedForeground",
            "Notification.errorForeground",
            "Notification.Error.foreground", // deprecated
            "Notification.linkForeground", // deprecated
            "Notification.Link.foreground", //deprecated
            "TabbedPane.selectedСolor", // deprecated
            "TabbedPane.underlineColor",
            "ToolWindow.HeaderTab.underlineColor",
            "ToolWindow.HeaderTab.inactiveUnderlineColor",
            "Tree.modifiedItemForeground"
        )
    );
  }
}
