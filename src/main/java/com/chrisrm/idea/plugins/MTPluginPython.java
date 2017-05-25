package com.chrisrm.idea.plugins;

import com.chrisrm.idea.utils.IconReplacer;
import com.intellij.openapi.components.ApplicationComponent;
import org.jetbrains.annotations.NotNull;

public class MTPluginPython implements ApplicationComponent {

  public void initComponent() {
    try {
      Class<?> iconsClass = Class.forName("icons.PythonPsiApiIcons", false, getClass().getClassLoader());
      IconReplacer.replaceIcons(iconsClass, "/icons/plugins/python/");
    }
    catch (ClassNotFoundException e) {
      // Suppress
    }
  }

  public void disposeComponent() {
    // TODO: insert component disposal logic here
  }

  @NotNull
  public String getComponentName() {
    return "MTPythonPHP";
  }
}
