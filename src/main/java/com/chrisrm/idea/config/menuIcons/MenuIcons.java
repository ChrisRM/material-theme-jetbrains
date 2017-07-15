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

package com.chrisrm.idea.config.menuIcons;

import com.intellij.ide.ui.customization.CustomActionsSchema;
import com.thoughtworks.xstream.XStream;

import java.io.Serializable;
import java.net.URL;
import java.util.List;

public final class MenuIcons implements Serializable {

  private List<MenuIcon> menuIcons;

  public List<MenuIcon> getMenuIcons() {
    return menuIcons;
  }

  public void setMenuIcons(final List<MenuIcon> menuIcons) {
    this.menuIcons = menuIcons;
  }

  public void loadIcons() {
    final CustomActionsSchema customActionsSchema = CustomActionsSchema.getInstance();
    for (MenuIcon menuIcon : menuIcons) {
      customActionsSchema.addIconCustomization(menuIcon.getId(), Factory.class.getResource(menuIcon.getIcon()).getPath());
    }
  }

  public static class Factory {
    public static MenuIcons create() {
      final URL resource = Factory.class.getResource("/menu_icons.xml");
      final XStream xStream = new XStream();
      xStream.alias("menuIcons", MenuIcons.class);
      xStream.alias("menuIcon", MenuIcon.class);

      xStream.useAttributeFor(MenuIcon.class, "id");
      xStream.useAttributeFor(MenuIcon.class, "icon");

      return (MenuIcons) xStream.fromXML(resource);
    }
  }
}
