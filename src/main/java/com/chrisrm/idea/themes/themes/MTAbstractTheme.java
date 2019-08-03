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

package com.chrisrm.idea.themes.themes;

import com.chrisrm.idea.MTConfig;
import com.chrisrm.idea.MTThemeManager;
import com.chrisrm.idea.lafs.MTDarkLaf;
import com.chrisrm.idea.lafs.MTLightLaf;
import com.chrisrm.idea.themes.models.MTSerializedTheme;
import com.chrisrm.idea.themes.models.MTThemeable;
import com.chrisrm.idea.utils.MTUI;
import com.google.common.collect.Sets;
import com.intellij.openapi.util.IconLoader;
import com.intellij.ui.ColorUtil;
import com.intellij.ui.JBColor;
import com.intellij.util.IconUtil;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

import static com.chrisrm.idea.themes.lists.MTThemeResources.*;
import static com.chrisrm.idea.utils.MTColorUtils.contrastifyBackground;
import static com.chrisrm.idea.utils.MTColorUtils.contrastifyForeground;
import static com.chrisrm.idea.utils.MTUiUtils.buildResources;

@SuppressWarnings({"DuplicateStringLiteralInspection",
    "HardCodedStringLiteral",
    "SerializableHasSerializationMethods"
})
public abstract class MTAbstractTheme implements Serializable, MTThemeable, MTSerializedTheme {

  private String id;
  private String editorColorsScheme;
  private boolean dark;
  private String name;
  private String icon;
  private transient boolean isNotHighContrast;

  @SuppressWarnings({"OverridableMethodCallDuringObjectConstruction",
      "OverriddenMethodCallDuringObjectConstruction"})
  protected MTAbstractTheme() {
    init();
  }

  /**
   * Theme Builder
   */
  @SuppressWarnings("DesignForExtension")
  protected void init() {
    setId(getThemeId())
        .setIsDark(isThemeDark())
        .setEditorColorScheme(getThemeColorScheme())
        .setIcon(getThemeIcon())
        .setName(getThemeName());
  }

  /**
   * Get the theme id
   */
  @Override
  public final String toString() {
    return getId();
  }

  /**
   * Activate the theme by overriding UIManager with the theme resources and by setting the relevant Look and feel
   */
  @SuppressWarnings({"FeatureEnvy",
      "OverlyLongMethod"})
  @Override
  public final void activate() {
    final MTConfig config = MTConfig.getInstance();
    isNotHighContrast = !config.isHighContrast();
    try {
      JBColor.setDark(dark);
      IconLoader.setUseDarkIcons(dark);
      // Overridable method
      buildAllResources();

      // Apply theme accent color if said so
      if (config.isOverrideAccentColor()) {
        config.setAccentColor(ColorUtil.toHex(getAccentColorResource()));
        MTThemeManager.applyAccents(true);
      }

      if (dark) {
        UIManager.setLookAndFeel(new MTDarkLaf(this));
      } else {
        UIManager.setLookAndFeel(new MTLightLaf(this));
      }
    } catch (final UnsupportedLookAndFeelException e) {
      e.printStackTrace();
    }
  }

  /**
   * Build all resources. Overridable
   */
  @SuppressWarnings("CheckStyle")
  void buildAllResources() {
    buildResources(getBackgroundResources(), contrastifyBackground(dark, getBackgroundColorResource(), isNotHighContrast));
    buildResources(getForegroundResources(), getForegroundColorResource());
    buildResources(getTextResources(), contrastifyForeground(dark, getTextColorResource(), isNotHighContrast));
    buildResources(getSelectionBackgroundResources(), getSelectionBackgroundColorResource());
    buildResources(getSelectionForegroundResources(), getSelectionForegroundColorResource());
    buildResources(getButtonColorResources(), getButtonColorResource());
    buildResources(getSecondaryBackgroundResources(), getSecondaryBackgroundColorResource());
    buildResources(getDisabledResources(), getDisabledColorResource());
    buildResources(getContrastResources(), contrastifyBackground(dark, getContrastColorResource(), isNotHighContrast));
    buildResources(getTableSelectedResources(), getTableSelectedColorResource());
    buildResources(getSecondBorderResources(), getSecondBorderColorResource());
    buildResources(getHighlightResources(), getHighlightColorResource());

    buildResources(getTreeSelectionResources(), getTreeSelectionColorResource());
    buildResources(getNotificationsResources(), getNotificationsColorResource());

    buildNotificationsColors();
    buildFlameChartColors();
    buildFileColors();
    buildTransparentColors();
    buildTreeSelectionInactiveColors();

    UIManager.getDefaults().put("Component.grayForeground", ColorUtil.darker(getTextColorResource(), 2));
  }

  //region Getters/Setters

  /**
   * The theme name
   */
  @NotNull
  @Override
  public final String getName() {
    return name;
  }

  /**
   * Set the theme name
   */
  @Override
  public final MTAbstractTheme setName(final String name) {
    this.name = name;
    return this;
  }

  /**
   * Get the editor color scheme
   */
  @Override
  public final String getEditorColorsScheme() {
    return editorColorsScheme;
  }

  @Override
  public final MTAbstractTheme setEditorColorScheme(final String editorColorsScheme) {
    this.editorColorsScheme = editorColorsScheme;
    return this;
  }

  /**
   * The theme id
   */
  @SuppressWarnings("DesignForExtension")
  @Override
  @NotNull
  public String getId() {
    return id;
  }

  @Override
  public final MTAbstractTheme setId(final String id) {
    this.id = id;
    return this;
  }

  /**
   * Whether the theme is a dark one
   */
  @Override
  public final boolean isDark() {
    return dark;
  }

  @Override
  public final MTAbstractTheme setIsDark(final boolean dark) {
    this.dark = dark;
    return this;
  }

  @NotNull
  @Override
  public final Icon getIcon() {
    return icon != null ? IconLoader.getIcon(icon) : IconUtil.getEmptyIcon(true);
  }

  @Override
  public final MTAbstractTheme setIcon(final String icon) {
    this.icon = icon;
    return this;
  }

  /**
   * Whether the theme is a custom or external one
   */
  @SuppressWarnings("DesignForExtension")
  @Override
  public boolean isCustom() {
    return false;
  }
  //endregion

  //region Theme methods

  /**
   * Get background color custom property
   */
  @Override
  @NotNull
  public final Color getBackgroundColor() {
    return contrastifyBackground(dark, getBackgroundColorResource(), isNotHighContrast);
  }

  /**
   * Get contrast color custom property
   */
  @Override
  @NotNull
  public final Color getContrastColor() {
    return contrastifyBackground(dark, getContrastColorResource(), isNotHighContrast);
  }

  /**
   * Get foreground color custom property
   */
  @Override
  @NotNull
  public final Color getForegroundColor() {
    return contrastifyForeground(dark, getForegroundColorResource(), isNotHighContrast);
  }

  /**
   * Get background color custom property
   */
  @Override
  @NotNull
  public final Color getPrimaryColor() {
    return contrastifyForeground(dark, getTextColorResource(), isNotHighContrast);
  }

  @NotNull
  @Override
  public final Color getSelectionBackgroundColor() {
    return getSelectionBackgroundColorResource();
  }

  @NotNull
  @Override
  public final Color getSelectionForegroundColor() {
    return getSelectionForegroundColorResource();
  }

  @NotNull
  @Override
  public final Color getExcludedColor() {
    return contrastifyBackground(dark, getExcludedColorResource(), isNotHighContrast);
  }

  @NotNull
  @Override
  public final Color getNotificationsColor() {
    return getNotificationsColorResource();
  }

  @NotNull
  @Override
  public final Color getSecondBorderColor() {
    return getSecondBorderColorResource();
  }

  @NotNull
  @Override
  public final Color getDisabledColor() {
    return getDisabledColorResource();
  }

  @NotNull
  @Override
  public final Color getSecondaryBackgroundColor() {
    return getSecondaryBackgroundColorResource();
  }

  @NotNull
  @Override
  public final Color getButtonColor() {
    return getButtonColorResource();
  }

  @NotNull
  @Override
  public final Color getTableSelectedColor() {
    return getTableSelectedColorResource();
  }

  @NotNull
  @Override
  public final Color getTextColor() {
    return getTextColorResource();
  }

  @NotNull
  @Override
  public final Color getTreeSelectionColor() {
    return getTreeSelectionColorResource();
  }

  @NotNull
  @Override
  public final Color getHighlightColor() {
    return getHighlightColorResource();
  }

  @NotNull
  @Override
  public final Color getAccentColor() {
    return getAccentColorResource();
  }

  @Override
  public final void setPristine() {
    this.isNotHighContrast = true;
  }

  //endregion

  //region Other resources

  /**
   * Special treatment for notification colors
   */
  private static void buildNotificationsColors() {
    final JBColor errorColor = new JBColor(new ColorUIResource(0xef5350), new ColorUIResource(0xb71c1c));
    UIManager.put("Notification.ToolWindowError.background", errorColor);
    UIManager.put("Notification.ToolWindow.errorBackground", errorColor);
    UIManager.put("Notification.ToolWindowError.borderColor", errorColor);
    UIManager.put("Notification.ToolWindow.errorBorderColor", errorColor);

    final JBColor warnColor = new JBColor(new ColorUIResource(0xFFD54F), new ColorUIResource(0x5D4037));
    UIManager.put("Notification.ToolWindowWarning.background", warnColor);
    UIManager.put("Notification.ToolWindow.warningBackground", warnColor);
    UIManager.put("Notification.ToolWindowWarning.borderColor", warnColor);
    UIManager.put("Notification.ToolWindow.warningBorderColor", warnColor);

    final JBColor infoColor = new JBColor(new ColorUIResource(0x66BB6A), new ColorUIResource(0x1B5E20));
    UIManager.put("Notification.ToolWindowInfo.borderColor", infoColor); // deprecated
    UIManager.put("Notification.ToolWindow.infoBorderColor", infoColor); // deprecated
    UIManager.put("Notification.ToolWindow.informativeBorderColor", infoColor);

    UIManager.put("Notification.ToolWindowInfo.background", infoColor); // deprecated
    UIManager.put("Notification.ToolWindow.infoBackground", infoColor); // deprecated
    UIManager.put("Notification.ToolWindow.informativeBackground", infoColor); // deprecated
  }

  /**
   * Special treatment for flame chart colors
   */
  private static void buildFlameChartColors() {
    UIManager.put("FlameGraph.JVMBackground", MTUI.MTColor.CYAN);
    UIManager.put("FlameGraph.JVMFocusBackground", MTUI.MTColor.BLUE);
    UIManager.put("FlameGraph.JVMSearchNotMatchedBackground", MTUI.MTColor.RED);
    UIManager.put("FlameGraph.JVMFocusSearchNotMatchedBackground", MTUI.MTColor.BROWN);

    UIManager.put("FlameGraph.nativeBackground", MTUI.MTColor.YELLOW);
    UIManager.put("FlameGraph.nativeFocusBackground", MTUI.MTColor.ORANGE);
    UIManager.put("FlameGraph.nativeSearchNotMatchedBackground", MTUI.MTColor.PURPLE);
    UIManager.put("FlameGraph.nativeFocusSearchNotMatchedBackground", MTUI.MTColor.PINK);
  }

  /**
   * Special treatment for file colors
   */
  private static void buildFileColors() {
    UIManager.put("FileColor.Green", MTUI.MTColor.DARK_GREEN);
    UIManager.put("FileColor.Blue", MTUI.MTColor.DARK_BLUE);
    UIManager.put("FileColor.Yellow", MTUI.MTColor.DARK_YELLOW);
    UIManager.put("FileColor.Orange", MTUI.MTColor.DARK_ORANGE);
    UIManager.put("FileColor.Violet", MTUI.MTColor.DARK_PURPLE);
    UIManager.put("FileColor.Rose", MTUI.MTColor.DARK_PINK);
  }

  /**
   * Build transparent colors
   */
  private static void buildTransparentColors() {
    final Set<String> colors = Collections.unmodifiableSet(
        Sets.newHashSet(
            "ScrollBar.hoverTrackColor",
            "ScrollBar.trackColor",
            "ScrollBar.Mac.hoverTrackColor",
            "ScrollBar.Mac.trackColor",
            "ScrollBar.Transparent.hoverTrackColor",
            "ScrollBar.Transparent.trackColor",
            "ScrollBar.Mac.Transparent.hoverTrackColor",
            "ScrollBar.Mac.Transparent.trackColor"
        ));

    final Color transparentBackground = MTUI.Panel.getTransparentBackground();
    buildResources(colors, transparentBackground);
  }

  /**
   * Build Tree Selection Inactive Colors
   */
  private static void buildTreeSelectionInactiveColors() {
    final Set<String> colors = Collections.unmodifiableSet(
        Sets.newHashSet(
            "Tree.selectionInactiveBackground",
            "CompletionPopup.nonFocusedState",
            "CompletionPopup.nonFocusedMask",
            "List.selectionInactiveBackground",
            "Table.selectionInactiveBackground",
            "TitlePane.inactiveBackground"
        ));

    final Color transparentBackground = MTUI.Tree.getSelectionInactiveBackground();
    buildResources(colors, transparentBackground);
  }
  //endregion
}
