package com.chrisrm.idea.config.scope;

import com.intellij.ide.ui.customization.CustomActionsSchema;
import com.thoughtworks.xstream.XStream;

import java.io.Serializable;
import java.net.URL;
import java.util.List;

public class MenuIcons implements Serializable {

  private List<MenuIcon> menuIcons;

  public List<MenuIcon> getMenuIcons() {
    return menuIcons;
  }

  public void setMenuIcons(List<MenuIcon> menuIcons) {
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
