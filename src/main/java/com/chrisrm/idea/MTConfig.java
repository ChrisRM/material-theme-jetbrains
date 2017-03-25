package com.chrisrm.idea;

import com.chrisrm.idea.config.ConfigNotifier;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.*;
import com.intellij.ui.ColorUtil;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;


@State(
    name = "MaterialThemeConfig",
    storages = @Storage("material_theme.xml")
)
public class MTConfig implements PersistentStateComponent<MTConfig> {
  public String highlightColor;
  public boolean highlightColorEnabled;
  public int highlightThickness;

  public MTConfig() {
    MTTheme theme = MTTheme.getCurrentPreference();

    try {
      InputStream stream = getClass().getResourceAsStream(theme.getId() + ".properties");
      Properties properties = new Properties();
      properties.load(stream);
      stream.close();

      if (highlightColor == null) {
        highlightColor = properties.getProperty("material.tab.borderColor");
        highlightColorEnabled = false;
        highlightThickness = Integer.parseInt(properties.getProperty("material.tab.borderThickness"));
      }
    } catch (IOException ignored) {
      ;
    }
  }

  /**
   * Get instance of the config from the ServiceManager
   *
   * @return the MTConfig instance
   */
  public static MTConfig getInstance() {
    return ServiceManager.getService(MTConfig.class);
  }

  /**
   * Get the state of MTConfig
   *
   * @return
   */
  @Nullable
  @Override
  public MTConfig getState() {
    return this;
  }

  /**
   * Load the state from XML
   *
   * @param state the MTConfig instance
   */
  @Override
  public void loadState(MTConfig state) {
    XmlSerializerUtil.copyBean(state, this);
  }

  /**
   * Get the set highlight color
   *
   * @return the highlight color
   */
  public Color getHighlightColor() {
    return ColorUtil.fromHex(this.highlightColor);
  }

  /**
   * Set a new highlight color
   *
   * @param color the new hightlight color
   */
  public void setHighlightColor(@NotNull Color color) {
    highlightColor = ColorUtil.toHex(color);
  }

  public boolean isHighlightColorEnabled() {
    return this.highlightColorEnabled;
  }

  public void setHighlightColorEnabled(boolean enabled) {
    this.highlightColorEnabled = enabled;
  }

  public int getHighlightThickness(){
    return highlightThickness;
  }

  public void setHighlightThickness(int thickness) {
    this.highlightThickness = thickness;
  }

  /**
   * Checks whether the new highlightColor is different from the previous one
   *
   * @param color new highlight color
   * @return true if changed
   */
  public boolean isHighlightColorChanged(@NotNull Color color) {
    Color current = this.getHighlightColor();
    return !Objects.equals(current, color);
  }

  public boolean isHighlightColorEnabledChanged(boolean enabled) {
    return this.highlightColorEnabled != enabled;
  }

  public boolean isHighlightThicknessChanged(int thickness) {
    return highlightThickness != thickness;
  }

  /**
   * Fire an event to the application bus that the settings have changed
   */
  public void fireChanged() {
    ApplicationManager.getApplication().getMessageBus()
        .syncPublisher(ConfigNotifier.CONFIG_TOPIC)
        .configChanged(this);
  }
}
