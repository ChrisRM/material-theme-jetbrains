package com.chrisrm.idea.config.scope;

import java.io.Serializable;

public class MenuIcon implements Serializable {

  private String id;
  private String icon;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }
}
