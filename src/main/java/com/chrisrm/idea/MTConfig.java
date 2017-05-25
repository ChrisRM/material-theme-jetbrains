package com.chrisrm.idea;

import com.chrisrm.idea.config.ConfigNotifier;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
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
  private String highlightColor;
  private boolean highlightColorEnabled;
  private Integer highlightThickness;

  private boolean isContrastMode = false;

  private boolean isMaterialDesign = true;
  private boolean isBoldTabs;
  private String accentColor = "80CBC4";

  public MTConfig() {
    MTTheme theme = MTTheme.getCurrentPreference();

    try {
      InputStream stream = getClass().getResourceAsStream(theme.getId() + ".properties");
      Properties properties = new Properties();
      properties.load(stream);
      stream.close();

      if (this.highlightColor == null) {
        highlightColor = properties.getProperty("material.tab.borderColor");
        highlightColorEnabled = false;
      }

      if (this.highlightThickness == null) {
        highlightThickness = Integer.parseInt(properties.getProperty("material.tab.borderThickness"));
      }

    }
    catch (IOException ignored) {
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

  /**
   * Return whether custom highlight is enabled
   *
   * @return true if enabled
   */
  public boolean isHighlightColorEnabled() {
    return this.highlightColorEnabled;
  }

  /**
   * Enable/Disable custom highlight
   *
   * @param enabled state
   */
  public void setHighlightColorEnabled(boolean enabled) {
    this.highlightColorEnabled = enabled;
  }

  /**
   * Get user's highlight thickness
   *
   * @return highlight thickness
   */
  public int getHighlightThickness() {
    return highlightThickness;
  }

  /**
   * Set highlight thickness
   *
   * @param thickness thickness value
   */
  public void setHighlightThickness(int thickness) {
    this.highlightThickness = thickness;
  }

  /**
   * Checks whether we are in contrast mode
   *
   * @return true if contrast mode
   */
  public boolean getIsContrastMode() {
    return isContrastMode;
  }

  /**
   * Enable/disable contrast mode
   *
   * @param isContrastMode contrast mode value
   */
  public void setIsContrastMode(boolean isContrastMode) {
    this.isContrastMode = isContrastMode;
  }

  public boolean getIsMaterialDesign() {
    return isMaterialDesign;
  }

  public void setIsMaterialDesign(boolean materialDesign) {
    isMaterialDesign = materialDesign;
  }
  //region Dirty checking

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

  /**
   * Checks whether the highlight color enabled state has changed
   *
   * @param enabled new enabled state
   * @return true if changed
   */
  public boolean isHighlightColorEnabledChanged(boolean enabled) {
    return this.highlightColorEnabled != enabled;
  }

  /**
   * Checks whether the highlight thickness has changed
   *
   * @param thickness new thickness
   * @return true if changed
   */
  public boolean isHighlightThicknessChanged(int thickness) {
    return highlightThickness != thickness;
  }

  public boolean isContrastModeChanged(boolean isContrastMode) {
    return this.isContrastMode != isContrastMode;
  }

  public boolean isMaterialDesignChanged(boolean isMaterialDesign) {
    return this.isMaterialDesign != isMaterialDesign;
  }

  public boolean isBoldTabsChanged(boolean isBoldTabs) {
    return this.isBoldTabs != isBoldTabs;
  }
  //endregion

  /**
   * Fire an event to the application bus that the settings have changed
   */
  public void fireChanged() {
    ApplicationManager.getApplication().getMessageBus()
                      .syncPublisher(ConfigNotifier.CONFIG_TOPIC)
                      .configChanged(this);
  }

  public boolean getIsBoldTabs() {
    return isBoldTabs;
  }

  public void setIsBoldTabs(boolean isBoldTabs) {
    this.isBoldTabs = isBoldTabs;
  }


  public String getAccentColor() {
    return accentColor;
  }

  public void setAccentColor(String accentColor) {
    this.accentColor = accentColor;
  }
}
