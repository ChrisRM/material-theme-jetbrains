package com.chrisrm.idea;

import com.chrisrm.idea.utils.UIReplacer;
import com.google.common.collect.ImmutableList;
import com.intellij.ide.plugins.PluginManager;
import com.intellij.ide.ui.UISettings;
import com.intellij.ide.ui.laf.darcula.DarculaInstaller;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.actionSystem.impl.ActionToolbarImpl;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.extensions.PluginId;
import com.intellij.openapi.util.IconLoader;
import com.intellij.ui.ColorUtil;
import com.intellij.ui.JBColor;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public enum MTTheme {
  DARKER("mt.darker", "Material Theme - Darker", true),
  DEFAULT("mt.default", "Material Theme - Default", true),
  PALENIGHT("mt.palenight", "Material Theme - Palenight", true),
  LIGHTER("mt.lighter", "Material Theme - Lighter", false),
  NONE("mt.none", "Darcula", true);

  @NonNls
  private static final String[] ourPatchableFontResources = {
      "Button.font",
      "ToggleButton.font",
      "RadioButton.font",
      "CheckBox.font",
      "ColorChooser.font",
      "ComboBox.font",
      "Label.font",
      "List.font",
      "MenuBar.font",
      "MenuItem.font",
      "MenuItem.acceleratorFont",
      "RadioButtonMenuItem.font",
      "CheckBoxMenuItem.font",
      "Menu.font",
      "PopupMenu.font",
      "OptionPane.font",
      "Panel.font",
      "ProgressBar.font",
      "ScrollPane.font",
      "Viewport.font",
      "TabbedPane.font",
      "Table.font",
      "TableHeader.font",
      "TextField.font",
      "FormattedTextField.font",
      "Spinner.font",
      "PasswordField.font",
      "TextArea.font",
      "TextPane.font",
      "EditorPane.font",
      "TitledBorder.font",
      "ToolBar.font",
      "ToolTip.font",
      "Tree.font"};

  private static final String[] contrastedResources = {
      "Tree.textBackground",
      "Table.background",
      "Viewport.background",
      "ToolBar.background",
      "SidePanel.background",
      "List.background",
      "TabbedPane.background",
      "TextField.background",
      "PasswordField.background",
      "TextArea.background",
      "TextPane.background",
      "EditorPane.background",
      "ToolBar.background",
      "Panel.background",
      "RadioButton.darcula.selectionDisabledColor",
      "RadioButton.background",
      "Spinner.background",
      "CheckBox.background",
      "CheckBox.darcula.backgroundColor1",
      "CheckBox.darcula.backgroundColor2",
      "CheckBox.darcula.shadowColor",
      "CheckBox.darcula.shadowColorDisabled",
      "CheckBox.darcula.focusedArmed.backgroundColor1",
      "CheckBox.darcula.focusedArmed.backgroundColor2",
      "CheckBox.darcula.focused.backgroundColor1",
      "CheckBox.darcula.focused.backgroundColor2",
      "ComboBox.disabledBackground",
      "control",
      "window",
      "activeCaption"
  };

  private static final List<String> EDITOR_COLORS_SCHEMES;
  @Nullable
  private static Properties properties;

  static {
    List<String> schemes = new ArrayList<String>();
    for (MTTheme theme : values()) {
      schemes.add(theme.editorColorsScheme);
    }
    EDITOR_COLORS_SCHEMES = ImmutableList.copyOf(schemes);
  }

  private final String id;
  private final String editorColorsScheme;
  private final boolean dark;

  MTTheme(@NotNull String id, @NotNull String editorColorsScheme, boolean dark) {
    this.id = id;
    this.editorColorsScheme = editorColorsScheme;
    this.dark = dark;
  }

  static void applyCustomFonts(UIDefaults uiDefaults, String fontFace, int fontSize) {
    uiDefaults.put("Tree.ancestorInputMap", null);
    FontUIResource uiFont = new FontUIResource(fontFace, Font.PLAIN, fontSize);
    FontUIResource textFont = new FontUIResource("Serif", Font.PLAIN, fontSize);
    FontUIResource monoFont = new FontUIResource("Monospaced", Font.PLAIN, fontSize);

    for (String fontResource : ourPatchableFontResources) {
      uiDefaults.put(fontResource, uiFont);
    }

    uiDefaults.put("PasswordField.font", monoFont);
    uiDefaults.put("TextArea.font", monoFont);
    uiDefaults.put("TextPane.font", textFont);
    uiDefaults.put("EditorPane.font", textFont);
  }

  public static void applyContrast(boolean apply) {
    for (String resource : contrastedResources) {
      Color contrastedColor = apply ? getContrastColor() : getBackgroundColor();
      UIManager.put(resource, contrastedColor);
    }
  }

  @Nullable
  public static MTTheme valueOfIgnoreCase(@Nullable String name) {
    for (MTTheme theme : MTTheme.values()) {
      if (theme.name().equalsIgnoreCase(name)) {
        return theme;
      }
    }
    return null;
  }

  @NotNull
  public static MTTheme getCurrentPreference() {
    String name = PropertiesComponent.getInstance().getValue(getSettingsPrefix() + ".theme");
    MTTheme theme = MTTheme.valueOfIgnoreCase(name);
    return theme == null ? MTTheme.DEFAULT : theme;
  }

  /**
   * @deprecated if more settings are needed for this plugin, you should create a {@link
   * com.intellij.openapi.components.PersistentStateComponent} and store all the settings in a separate file without
   * the prefix on the property name.
   */
  @Deprecated
  private static String getSettingsPrefix() {
    PluginId pluginId = PluginManager.getPluginByClassName(MTTheme.class.getName());
    return pluginId == null ? "com.chrisrm.idea.MaterialThemeUI" : pluginId.getIdString();
  }

  /**
   * Retrieve current theme properties
   *
   * @return
   */
  private static Properties getProperties() {
    if (MTTheme.properties == null) {
      MTTheme.properties = new Properties();
      MTTheme theme = MTTheme.getCurrentPreference();

      InputStream stream = MTTheme.class.getResourceAsStream(theme.getId() + ".properties");
      try {
        properties.load(stream);
        stream.close();
      }
      catch (Exception e) {
        ;
      }
    }
    return MTTheme.properties;
  }

  public static Color getBackgroundColor() {
    Properties properties = getProperties();
    return ColorUtil.fromHex("#" + properties.getProperty("material.tab.backgroundColor"));
  }

  public static Color getBorderColor() {
    Properties properties = getProperties();
    return ColorUtil.fromHex("#" + properties.getProperty("material.tab.borderColor"));
  }

  public static int getBorderThickness() {
    Properties properties = getProperties();
    return Integer.parseInt(properties.getProperty("material.tab.borderThickness"));
  }

  public static Color getContrastColor() {
    Properties properties = getProperties();
    return ColorUtil.fromHex(properties.getProperty("material.contrast"));
  }

  @NotNull
  public String getId() {
    return id;
  }

  public void activate() {
    //  Reload properties
    MTTheme.properties = null;

    try {
      UIManager.setLookAndFeel(new MTLaf(this));
      JBColor.setDark(dark);
      IconLoader.setUseDarkIcons(dark);

      PropertiesComponent.getInstance().setValue(getSettingsPrefix() + ".theme", name());
      applyContrast(MTConfig.getInstance().getIsContrastMode());
    }
    catch (UnsupportedLookAndFeelException e) {
      e.printStackTrace();
    }

    String currentScheme = EditorColorsManager.getInstance().getGlobalScheme().getName();

    String makeActiveScheme = !EDITOR_COLORS_SCHEMES.contains(currentScheme) ?
                              currentScheme : editorColorsScheme;

    EditorColorsScheme scheme = EditorColorsManager.getInstance().getScheme(makeActiveScheme);
    if (scheme != null) {
      EditorColorsManager.getInstance().setGlobalScheme(scheme);
    }

    UISettings uiSettings = UISettings.getInstance();
    uiSettings.fireUISettingsChanged();
    ActionToolbarImpl.updateAllToolbarsImmediately();

    // We need this to update parts of the UI that do not change
    DarculaInstaller.uninstall();
    DarculaInstaller.install();
    UIDefaults uiDefaults2 = UIManager.getLookAndFeelDefaults();

    if (uiSettings.getOverrideLafFonts()) {
      applyCustomFonts(uiDefaults2, uiSettings.getFontFace(), uiSettings.getFontSize());
    }

    UIReplacer.patchUI();
  }

  public void toggleContrast() {
    MTConfig mtConfig = MTConfig.getInstance();
    mtConfig.setIsContrastMode(!mtConfig.getIsContrastMode());

    this.activate();
  }
}
