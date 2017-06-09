package com.chrisrm.idea.themes;

import com.chrisrm.idea.MTConfig;
import com.chrisrm.idea.config.ConfigNotifier;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.wm.impl.IdeBackgroundUtil;
import org.jetbrains.annotations.NotNull;

public class MTWallpaperComponent implements ApplicationComponent {
  @Override
  public void initComponent() {
    this.reloadWallpaper();

    ApplicationManager.getApplication().getMessageBus().connect()
                      .subscribe(ConfigNotifier.CONFIG_TOPIC, mtConfig -> this.reloadWallpaper());
  }

  private void reloadWallpaper() {
    MTConfig mtConfig = MTConfig.getInstance();
    final String wallpaper = mtConfig.getWallpaper();

//    String customBg = PropertiesComponent.getInstance().getValue(IdeBackgroundUtil.FRAME_PROP);

    if (mtConfig.isWallpaperSet() && wallpaper != null) {
      PropertiesComponent.getInstance().unsetValue(IdeBackgroundUtil.FRAME_PROP);
      PropertiesComponent.getInstance().setValue(IdeBackgroundUtil.FRAME_PROP, wallpaper);
      IdeBackgroundUtil.repaintAllWindows();
    } else if (mtConfig.isWallpaperSet() && wallpaper == null) {
      PropertiesComponent.getInstance().unsetValue(IdeBackgroundUtil.FRAME_PROP);
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
