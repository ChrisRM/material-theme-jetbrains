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

package com.mallowigi.idea.themes.themes;

import com.google.common.collect.Sets;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.wm.impl.IdeBackgroundUtil;
import com.intellij.ui.ColorUtil;
import com.intellij.ui.JBColor;
import com.intellij.util.IconUtil;
import com.mallowigi.idea.MTConfig;
import com.mallowigi.idea.MTThemeManager;
import com.mallowigi.idea.lafs.MTDarkLaf;
import com.mallowigi.idea.lafs.MTLightLaf;
import com.mallowigi.idea.themes.MTAccentMode;
import com.mallowigi.idea.themes.lists.ContrastResources;
import com.mallowigi.idea.themes.lists.MTThemeResources;
import com.mallowigi.idea.themes.models.MTSerializedTheme;
import com.mallowigi.idea.themes.models.MTThemeable;
import com.mallowigi.idea.utils.MTColorUtils;
import com.mallowigi.idea.utils.MTUI;
import com.mallowigi.idea.utils.MTUiUtils;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;

import static com.mallowigi.idea.themes.MTAccentMode.getSelectionColor;

@SuppressWarnings({"DuplicateStringLiteralInspection",
  "HardCodedStringLiteral",
  "SerializableHasSerializationMethods",
  "NegativelyNamedBooleanVariable",
  "DesignForExtension"})
public abstract class MTAbstractTheme implements Serializable, MTThemeable, MTSerializedTheme {

  private String id = null;
  private String editorColorsScheme = null;
  private boolean dark = false;
  private String name = null;
  private String icon = null;
  private transient boolean isNotHighContrast = false;

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
  @SuppressWarnings("FeatureEnvy")
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

      installBackgroundImage();

      // Set MT Look and Feel
      setLookAndFeel();
    } catch (final UnsupportedLookAndFeelException e) {
      e.printStackTrace();
    }
  }

  @SuppressWarnings("DesignForExtension")
  protected void setLookAndFeel() throws UnsupportedLookAndFeelException {
    if (dark) {
      UIManager.setLookAndFeel(new MTDarkLaf(this));
    } else {
      UIManager.setLookAndFeel(new MTLightLaf(this));
    }
  }

  /**
   * Build all resources. Overridable
   */
  @SuppressWarnings({"CheckStyle",
    "FeatureEnvy",
    "DesignForExtension",
    "MagicNumber"})
  protected void buildAllResources() {
    MTUiUtils.buildResources(MTThemeResources.getBackgroundResources(), MTColorUtils.contrastifyBackground(dark,
      getBackgroundColorResource(), isNotHighContrast));
    MTUiUtils.buildResources(MTThemeResources.getForegroundResources(), getForegroundColorResource());
    MTUiUtils.buildResources(MTThemeResources.getTextResources(), MTColorUtils.contrastifyForeground(dark, getTextColorResource(),
      isNotHighContrast));
    MTUiUtils.buildResources(MTThemeResources.getSelectionBackgroundResources(), getSelectionBackgroundColorResource());
    MTUiUtils.buildResources(MTThemeResources.getSelectionTransparentBackgroundResources(),
      ColorUtil.toAlpha(getSelectionBackgroundColorResource(), 80));
    MTUiUtils.buildResources(MTThemeResources.getSelectionForegroundResources(), getSelectionForegroundColorResource());
    MTUiUtils.buildResources(MTThemeResources.getButtonColorResources(), getButtonColorResource());
    MTUiUtils.buildResources(MTThemeResources.getSecondaryBackgroundResources(), getSecondaryBackgroundColorResource());
    MTUiUtils.buildResources(MTThemeResources.getDisabledResources(), getDisabledColorResource());
    MTUiUtils.buildResources(MTThemeResources.getContrastResources(), MTColorUtils.contrastifyBackground(dark, getContrastColorResource()
      , isNotHighContrast));
    MTUiUtils.buildResources(MTThemeResources.getTableSelectedResources(), getTableSelectedColorResource());
    MTUiUtils.buildResources(MTThemeResources.getSecondBorderResources(), getSecondBorderColorResource());
    MTUiUtils.buildResources(MTThemeResources.getHighlightResources(), getHighlightColorResource());

    MTUiUtils.buildResources(MTThemeResources.getTreeSelectionResources(), getTreeSelectionColorResource());
    MTUiUtils.buildResources(MTThemeResources.getNotificationsResources(), getNotificationsColorResource());
    MTUiUtils.buildResources(MTThemeResources.getExcludedResources(), getExcludedColorResource());

    buildNotificationsColors();
    buildFlameChartColors();
    buildFileColors();
    buildTransparentColors();
    buildTreeSelectionInactiveColors();
    buildTabsTransparentColors();

    UIManager.getDefaults().put("Component.grayForeground", ColorUtil.darker(getTextColorResource(), 2));
    UIManager.getDefaults().put("EditorGroupsTabs.underlineHeight", MTConfig.getInstance().getHighlightThickness());
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
    return MTColorUtils.contrastifyBackground(dark, getBackgroundColorResource(), isNotHighContrast);
  }

  /**
   * Get contrast color custom property
   */
  @Override
  @NotNull
  public final Color getContrastColor() {
    return MTColorUtils.contrastifyBackground(dark, getContrastColorResource(), isNotHighContrast);
  }

  /**
   * Get foreground color custom property
   */
  @Override
  @NotNull
  public final Color getForegroundColor() {
    return MTColorUtils.contrastifyForeground(dark, getForegroundColorResource(), isNotHighContrast);
  }

  /**
   * Get background color custom property
   */
  @Override
  @NotNull
  public final Color getPrimaryColor() {
    return MTColorUtils.contrastifyForeground(dark, getTextColorResource(), isNotHighContrast);
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
    return MTColorUtils.contrastifyBackground(dark, getExcludedColorResource(), isNotHighContrast);
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
    isNotHighContrast = true;
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
    UIManager.put("FileColor.Rose", MTConfig.getInstance().getSelectedTheme().getTheme().getExcludedColor());
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
    MTUiUtils.buildResources(colors, transparentBackground);
  }

  /**
   * Build Tree Selection Inactive Colors
   */
  private static void buildTreeSelectionInactiveColors() {
    final Set<String> colors = Collections.unmodifiableSet(
      Sets.newHashSet(
        "CompletionPopup.nonFocusedState"
      ));

    final Color transparentBackground = MTUI.Tree.getSelectionInactiveBackground();
    MTUiUtils.buildResources(colors, transparentBackground);
  }

  /**
   * Build Tabs Selection Inactive Colors
   */
  private void buildTabsTransparentColors() {
    final Set<String> colors = Collections.unmodifiableSet(
      Sets.newHashSet(
        "EditorTabs.inactiveColoredFileBackground"
      ));

    final Color transparentBackground = ColorUtil.withAlpha(getSecondaryBackgroundColorResource(), 0.5);
    MTUiUtils.buildResources(colors, transparentBackground);
  }

  @Override
  public void applyContrast(final boolean apply) {
    final Color contrastedColor = apply ? getContrastColor() : getBackgroundColor();
    for (final String resource : ContrastResources.CONTRASTED_RESOURCES) {
      UIManager.put(resource, contrastedColor);
    }
  }

  @Override
  public final void setThemeName(final String name) {
    setName(name);
  }

  @Override
  public boolean isNative() {
    return false;
  }

  @SuppressWarnings({"MagicCharacter",
    "OverlyBroadCatchBlock",
    "NestedTryStatement"})
  private void installBackgroundImage() {
    final String currentSpec = PropertiesComponent.getInstance().getValue(IdeBackgroundUtil.FRAME_PROP);
    final String oldCurrentSpec = PropertiesComponent.getInstance().getValue("old.mt." + IdeBackgroundUtil.FRAME_PROP);

    if (!MTConfig.getInstance().isUseMaterialWallpapers()) {
      removeBackgroundImage(null);
      return;
    }

    try {
      final String path = getBackgroundImage();
      if (path != null) {
        final File tmpImage = FileUtil.createTempFile("mtBackgroundImage", path.toString().substring(path.lastIndexOf('.')), true);
        final URL resource = getClass().getClassLoader().getResource(path);

        if (resource != null) {
          try (final InputStream input = getClass().getClassLoader().getResourceAsStream(path)) {
            try (final FileOutputStream output = new FileOutputStream(tmpImage)) {
              FileUtil.copy(Objects.requireNonNull(input), output);
            }
          }

          final String image = tmpImage.getPath();
          final String alpha = String.valueOf(85);
          final String fill = MTUiUtils.parseEnumValue("fill", IdeBackgroundUtil.Fill.PLAIN);
          final String anchor = MTUiUtils.parseEnumValue("center", IdeBackgroundUtil.Anchor.CENTER);

          final String spec = StringUtil.join(new String[]{image,
            alpha,
            fill,
            anchor}, ",");
          PropertiesComponent.getInstance().setValue("old.mt." + IdeBackgroundUtil.FRAME_PROP, currentSpec);
          PropertiesComponent.getInstance().setValue(IdeBackgroundUtil.FRAME_PROP, spec);
          IdeBackgroundUtil.repaintAllWindows();
        } else {
          throw new IllegalArgumentException("Can't load background: " + path);
        }
      } else {
        removeBackgroundImage(oldCurrentSpec);
      }
    } catch (final IOException ignored) {
    }
  }

  private static void removeBackgroundImage(final String oldCurrentSpec) {
    PropertiesComponent.getInstance().setValue(IdeBackgroundUtil.FRAME_PROP, oldCurrentSpec);
    PropertiesComponent.getInstance().setValue("old.mt." + IdeBackgroundUtil.FRAME_PROP, null);
    IdeBackgroundUtil.repaintAllWindows();
  }

  @NonNls
  protected abstract String getBackgroundImage();
  //endregion

  @SuppressWarnings("FeatureEnvy")
  @Override
  public void applyAccentMode() {
    final MTConfig mtConfig = MTConfig.getInstance();
    final Color accentColor = ColorUtil.fromHex(mtConfig.getAccentColor());
    final Color darkerAccentColor = ColorUtil.darker(accentColor, 2);
    final Color accentColorTransparent = ColorUtil.withAlpha(accentColor, 0.5);
    final Color secondAccentColor = ColorUtil.fromHex(mtConfig.getSecondAccentColor());
    final boolean accentMode = mtConfig.isAccentMode();

    if (accentMode) {
      // Add accent resources
      MTUiUtils.buildResources(MTAccentMode.ACCENT_EXTRA_RESOURCES, accentColor);
      MTUiUtils.buildResources(MTAccentMode.DARKER_ACCENT_RESOURCES, darkerAccentColor);
      MTUiUtils.buildResources(MTAccentMode.ACCENT_TRANSPARENT_EXTRA_RESOURCES, accentColorTransparent);
      // Add new selection color resources
      MTUiUtils.buildResources(MTAccentMode.SELECTION_RESOURCES, getSelectionColor());
      MTUiUtils.buildResources(MTAccentMode.SECOND_ACCENT_RESOURCES, secondAccentColor);
    }
  }
}
