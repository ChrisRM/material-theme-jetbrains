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

package com.chrisrm.idea.config.menuIcons;

import com.intellij.ide.ui.customization.CustomActionsSchema;
import com.intellij.util.xmlb.annotations.Property;
import com.thoughtworks.xstream.XStream;
import org.jetbrains.annotations.NonNls;

import java.io.Serializable;
import java.net.URL;
import java.util.List;

public final class MenuIcons implements Serializable {
  private static final String MENU_ICONS_XML = "/menu_icons.xml";
  @Property
  private List<MenuIcon> menuIcons;

  public void loadIcons() {
    final CustomActionsSchema customActionsSchema = CustomActionsSchema.getInstance();
    for (final MenuIcon menuIcon : menuIcons) {
      customActionsSchema.addIconCustomization(menuIcon.getId(), Factory.class.getResource(menuIcon.getIcon()).getPath());
    }
  }

  private enum Factory {
    MENUICONS;

    public static MenuIcons create() {
      final URL resource = Factory.class.getResource(MENU_ICONS_XML);
      @NonNls final XStream xStream = new XStream();
      XStream.setupDefaultSecurity(xStream);
      xStream.allowTypesByWildcard(new String[]{"com.chrisrm.idea.config.menuIcons.*"});
      xStream.alias("menuIcons", MenuIcons.class);
      xStream.alias("menuIcon", MenuIcon.class);

      xStream.useAttributeFor(MenuIcon.class, "id");
      xStream.useAttributeFor(MenuIcon.class, "icon");

      return (MenuIcons) xStream.fromXML(resource);
    }
  }
}
