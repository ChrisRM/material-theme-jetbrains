package com.chrisrm.idea.themes;

import com.chrisrm.idea.MTConfig;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.wm.impl.IdeBackgroundUtil;
import org.jetbrains.annotations.NotNull;

public class MTWallpaperComponent implements ApplicationComponent {
  @Override
  public void initComponent() {
    MTConfig mtConfig = MTConfig.getInstance();
    final String wallpaper = mtConfig.getWallpaper();

    if (mtConfig.isWallpaperSet()) {
      PropertiesComponent.getInstance().unsetValue(IdeBackgroundUtil.FRAME_PROP);
      PropertiesComponent.getInstance().setValue(IdeBackgroundUtil.FRAME_PROP, wallpaper + ",60");
      IdeBackgroundUtil.repaintAllWindows();
    }
  }

  @Override
  public void disposeComponent() {

  }

  @NotNull
  @Override
  public String getComponentName() {
    return "MTWallpaperComponent";
  }
}
