/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Chris Magnussen and Elior Boukhobza
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
import com.intellij.ide.ui.laf.IntelliJLookAndFeelInfo;
import com.intellij.ide.ui.laf.LafManagerImpl;
import com.intellij.ide.ui.laf.darcula.DarculaLookAndFeelInfo;
import com.intellij.openapi.util.IconLoader;
import com.intellij.ui.ColorUtil;
import com.intellij.ui.JBColor;
import com.intellij.util.IconUtil;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.plaf.*;
import java.awt.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

import static com.chrisrm.idea.utils.MTColorUtils.contrastifyBackground;
import static com.chrisrm.idea.utils.MTColorUtils.contrastifyForeground;

@SuppressWarnings( {"DuplicateStringLiteralInspection",
    "HardCodedStringLiteral",
    "SerializableHasSerializationMethods"})
public abstract class MTAbstractTheme implements Serializable, MTThemeable, MTSerializedTheme {

  private String id;
  private String editorColorsScheme;
  private boolean dark;
  private String name;
  private String icon;
  private MTConfig config;
  private boolean isNotHighContrast;

  protected MTAbstractTheme() {
    init();
  }

  /**
   * Theme Builder
   */
  private void init() {
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
    config = MTConfig.getInstance();
    isNotHighContrast = !config.isHighContrast();
    try {
      if (dark) {
        LafManagerImpl.getTestInstance().setCurrentLookAndFeel(new DarculaLookAndFeelInfo());
      } else {
        LafManagerImpl.getTestInstance().setCurrentLookAndFeel(new IntelliJLookAndFeelInfo());
      }
      JBColor.setDark(dark);
      IconLoader.setUseDarkIcons(dark);
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

      UIManager.getDefaults().put("Component.grayForeground", ColorUtil.darker(getTextColorResource(), 2));

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

  @Override
  public final Color getSelectionBackgroundColor() {
    return getSecondaryBackgroundColorResource();
  }

  @Override
  public final Color getSelectionForegroundColor() {
    return getSelectionForegroundColorResource();
  }

  @Override
  public final Color getExcludedColor() {
    return contrastifyBackground(dark, getExcludedColorResource(), isNotHighContrast);
  }

  /**
   * Get background color custom property
   */
  @Override
  @NotNull
  public final Color getPrimaryColor() {
    return contrastifyForeground(dark, getTextColorResource(), isNotHighContrast);
  }

  //endregion

  //region MTThemeable methods

  /**
   * Get resources using the background color
   */
  @NonNls
  private static Set<String> getBackgroundResources() {
    return Collections.unmodifiableSet(
        Sets.newHashSet(
            //        "Menu.background",
            "activeCaption",
            "Borders.color",
            "Borders.ContrastBorderColor",
            //            "Button.darcula.color1",
            //            "Button.darcula.color2",
            "Button.darcula.disabledText.shadow",
            "CheckBox.background",
            "CheckBox.darcula.backgroundColor1",
            "CheckBox.darcula.backgroundColor2",
            "CheckBox.darcula.checkSignColor",
            "CheckBox.darcula.focused.backgroundColor1",
            "CheckBox.darcula.focused.backgroundColor2",
            "CheckBox.darcula.focusedArmed.backgroundColor1",
            "CheckBox.darcula.focusedArmed.backgroundColor2",
            "CheckBox.darcula.shadowColor",
            "CheckBox.darcula.shadowColorDisabled",
            "CheckBoxMenuItem.background",
            "CheckBoxMenuItem.disabledBackground",
            "ColorChooser.background",
            "ComboBox.ArrowButton.nonEditableBackground",
            "ComboBox.arrowFillColor",
            "ComboBox.background",
            "ComboBox.darcula.arrowButtonBackground",
            "ComboBox.darcula.disabledArrowButtonBackground",
            "ComboBox.darcula.editable.arrowButtonBackground",
            "ComboBox.darcula.nonEditableBackground",
            "ComboBox.disabledBackground",
            "ComboBox.nonEditableBackground",
            "control",
            "darcula.background",
            "Desktop.background",
            "Dialog.titleColor",
            "DialogWrapper.southPanelBackground",
            "DialogWrapper.southPanelDivider",
            "DragAndDrop.areaBorderColor",
            "DragAndDrop.backgroundBorderColor",
            "DragAndDrop.backgroundColor",
            "DragAndDrop.areaBackground",
            "Editor.background",
            "EditorPane.inactiveBackground",
            "EditorTabs.inactive.maskColor",
            "EditorTabs.inactiveMaskColor",
            "FormattedTextField.background",
            "HeaderColor.active",
            "HelpTooltip.background",
            "HelpTooltip.backgroundColor",
            "inactiveCaptionBorder",
            "intellijlaf.background",
            "InternalFrame.inactiveTitleBackground",
            "material.background",
            "material.tab.backgroundColor",
            "MenuBar.background",
            "MenuBar.borderColor",
            "MenuBar.disabledBackground",
            "MenuBar.highlight",
            "MenuBar.shadow",
            "NavBar.borderColor",
            "OptionPane.background",
            "PasswordField.background",
            "Plugins.background",
            "Plugins.SearchField.background",
            "Popup.Advertiser.background",
            "Popup.Border.inactiveColor",
            "Popup.Header.activeBackground",
            "Popup.inactiveBorderColor",
            "Popup.preferences.background",
            "Popup.preferences.borderColor",
            "PopupMenu.background",
            "PopupMenu.translucentBackground",
            "RadioButton.background",
            "RadioButton.darcula.selectionDisabledColor",
            "SearchEverywhere.background",
            "SearchEverywhere.Dialog.background",
            "SearchEverywhere.Header.background",
            "SearchEverywhere.SearchField.Border.color",
            "SearchEverywhere.SearchField.borderColor",
            "SidePanel.background",
            "Slider.background",
            "Spinner.background",
            "SplitPane.highlight",
            "StatusBar.background",
            "StatusBar.borderColor",
            "StatusBar.bottomColor",
            "StatusBar.top2Color",
            "StatusBar.topColor",
            "TabbedPane.background",
            "TabbedPane.borderColor",
            "TabbedPane.mt.tab.background",
            "Table.background",
            "Table.gridColor",
            "TextField.background",
            "TextField.borderColor",
            "TextField.focusedBorderColor",
            "TextField.hoverBorderColor",
            "TextField.separatorColorDisabled",
            "TextPane.background",
            "ToolTip.actions.background",
            "ToolTip.Actions.background",
            "ToolTip.background",
            "tooltips.actions.settings.icon.background.color",
            "ToolWindow.header.background",
            "ToolWindow.header.closeButton.background",
            "ToolWindow.Header.inactiveBackground",
            "ToolWindow.HeaderCloseButton.background",
            "ToolWindow.inactive.Header.background",
            "Tree.background",
            "VersionControl.FileHistory.Commit.otherBranchBackground",
            "VersionControl.FileHistory.Commit.selectedBranchBackground",
            "WelcomeScreen.background",
            "WelcomeScreen.borderColor",
            "WelcomeScreen.headerBackground",
            "window"
        ));
  }

  /**
   * Get resources using the foreground color
   */
  private static Set<String> getForegroundResources() {
    return Collections.unmodifiableSet(
        Sets.newHashSet(
            "CheckBox.darcula.borderColor1",
            "CheckBox.foreground",
            "CheckBoxMenuItem.foreground",
            "ColorChooser.foreground",
            "ComboBox.darcula.arrowButtonForeground",
            "ComboBox.ArrowButton.iconColor",
            "ComboBox.foreground",
            "CompletionPopup.foreground",
            "darcula.foreground",
            "DragAndDrop.areaForeground",
            "DragAndDrop.foregroundColor",
            "EditorPane.foreground",
            "EditorTabs.active.foreground",
            "EditorTabs.selectedForeground",
            "FormattedTextField.foreground",
            "Git.Log.Ref.RemoteBranch",
            "Github.List.tallRow.foreground",
            "Group.separatorColor",
            "HelpTooltip.foreground",
            "HelpTooltip.textColor",
            "Hg.Log.Ref.ClosedBranch",
            "intellijlaf.foreground",
            "Label.foreground",
            "Label.selectedDisabledForeground",
            "List.foreground",
            "material.branchColor",
            "material.foreground",
            "Menu.foreground",
            "MenuBar.foreground",
            "MenuItem.foreground",
            "MenuItem.foreground",
            "NavBar.arrowColor",
            "Notification.foreground",
            "Notification.MoreButton.foreground",
            "OptionPane.messageForeground",
            "ParameterInfo.foreground",
            "PasswordField.foreground",
            "Plugins.Button.installForeground",
            "Plugins.Button.updateForeground",
            "Plugins.SectionHeader.foreground",
            "Popup.separatorForeground",
            "Popup.Separator.foreground",
            "PopupMenu.foreground",
            "RadioButton.darcula.borderColor1",
            "RadioButton.foreground",
            "RadioButtonMenuItem.foreground",
            "SearchEverywhere.foreground",
            "SpeedSearch.foreground",
            "Spinner.foreground",
            "TabbedPane.foreground",
            "Table.foreground",
            "Table.sortIconColor",
            "TableHeader.foreground",
            "TextArea.foreground",
            "TextField.foreground",
            "TextPane.foreground",
            "TitledBorder.titleColor",
            "ToggleButton.foreground",
            "ToggleButton.off.foreground",
            "ToggleButton.off.background",
            "ToolBar.foreground",
            "ToolTip.foreground",
            "tooltips.description.title.text.color",
            "WelcomeScreen.captionForeground",
            "WelcomeScreen.footerForeground",
            "WelcomeScreen.headerForeground"
        ));
  }

  /**
   * Get resources using the label color
   */
  private static Set<String> getTextResources() {
    return Collections.unmodifiableSet(
        Sets.newHashSet(
            "Button.foreground",
            "Button.mt.foreground",
            "CheckBoxMenuItem.acceleratorForeground",
            "CheckBoxMenuItem.acceleratorSelectionForeground",
            "CompletionPopup.grayedForeground",
            "CompletionPopup.grayForeground",
            "CompletionPopup.infoForeground",
            "Component.infoForeground",
            "Component.grayForeground",
            "controlText",
            "Editor.shortcutForeground",
            "Git.Log.Ref.Other",
            "Git.Log.Ref.Tag",
            "Github.List.tallRow.secondary.foreground",
            "HelpTooltip.shortcutForeground",
            "HelpTooltip.shortcutTextColor",
            "Hg.Log.Ref.LocalTag",
            "Hg.Log.Ref.MqTag",
            "Hg.Log.Ref.Tag",
            "inactiveCaptionText",
            "infoText",
            "InternalFrame.inactiveTitleForeground",
            "Label.grayForeground",
            "Label.infoForeground",
            "Label.textForeground",
            "material.primaryColor",
            "material.tagColor",
            "Menu.acceleratorForeground",
            "MenuItem.acceleratorForeground",
            "ParameterInfo.ContextHelp.foreground",
            "Plugins.Button.installFillForeground",
            "RadioButtonMenuItem.acceleratorForeground",
            "RadioButtonMenuItem.acceleratorSelectionForeground",
            "SearchEverywhere.shortcutForeground",
            "Table.lightSelectionInactiveForeground",
            "text",
            "textInactiveText",
            "textText",
            "ToolBar.borderHandleColor",
            "ToolBar.floatingForeground",
            "ToolTip.Actions.grayForeground",
            "ToolTip.Actions.infoForeground",
            "tooltips.actions.keymap.text.color",
            "Tree.foreground",
            "VersionControl.Log.Commit.unmatchedForeground"
        ));
  }

  /**
   * Get resources using the selection background color
   */
  private static Set<String> getSelectionBackgroundResources() {
    return Collections.unmodifiableSet(
        Sets.newHashSet(
            "Autocomplete.selectionBackground",
            "CheckBoxMenuItem.selectionBackground",
            "CompletionPopup.selectionBackground",
            "EditorPane.selectionBackground",
            "Github.List.tallRow.selectionBackground",
            "List.selectionBackground",
            "Menu.selectionBackground",
            "MenuItem.selectionBackground",
            "RadioButtonMenuItem.selectionBackground",
            "TabbedPane.selected",
            "WelcomeScreen.Projects.selectionBackground",
            "material.selectionBackground"
        ));
  }

  /**
   * Get resources using the selection foreground color
   */
  private static Set<String> getSelectionForegroundResources() {
    return Collections.unmodifiableSet(
        Sets.newHashSet(
            "Button.darcula.selectedButtonForeground",
            "Button.default.foreground",
            "Button.highlight",
            "Button.mt.selectedForeground",
            "CheckBoxMenuItem.selectionForeground",
            "ComboBox.selectionForeground",
            "CompletionPopup.selectedForeground",
            "CompletionPopup.selectionForeground",
            "CompletionPopup.selectedGrayedForeground",
            "CompletionPopup.selectionGrayForeground",
            "Counter.foreground",
            "EditorPane.selectionForeground",
            "FormattedTextField.selectionForeground",
            "Github.List.tallRow.selectionForeground",
            "Github.List.tallRow.selectionForeground.unfocused",
            "Label.selectedForeground",
            "List.selectionForeground",
            "List.selectionInactiveForeground",
            "Menu.acceleratorSelectionForeground",
            "Menu.selectionForeground",
            "MenuItem.acceleratorSelectionForeground",
            "MenuItem.selectionForeground",
            "PasswordField.selectionForeground",
            "Plugins.selectionForeground",
            "Plugins.Tab.active.foreground",
            "Plugins.Tab.selectedForeground",
            "SearchEverywhere.Tab.active.foreground",
            "SearchEverywhere.Tab.selectedForeground",
            "SearchEverywhere.Tab.selected.foreground",
            "TabbedPane.selectedForeground",
            "Table.focusCellForeground",
            "Table.lightSelectionForeground",
            "Table.selectionForeground",
            "TableHeader.focusCellForeground",
            "TextArea.selectionForeground",
            "TextField.selectionForeground",
            "TextPane.selectionForeground",
            "ToolWindow.Button.selectedForeground",
            "Tree.selectionForeground",
            "Tree.selectionInactiveForeground",
            "VersionControl.Ref.foreground",
            "material.selectionForeground"
        ));
  }

  /**
   * Get resources using the button color
   */
  private static Set<String> getButtonColorResources() {
    return Collections.unmodifiableSet(
        Sets.newHashSet(
            "Button.background",
            "Button.darcula.borderColor",
            "Button.darcula.defaultBorderColor",
            "Button.darcula.defaultEndColor",
            "Button.darcula.defaultOutlineColor",
            "Button.darcula.defaultStartColor",
            "Button.darcula.disabledBorderColor",
            "Button.darcula.endColor",
            "Button.darcula.outlineColor",
            "Button.darcula.smallComboButtonBackground",
            "Button.darcula.startColor",
            "Button.default.borderColor",
            "Button.default.startBackground",
            "Button.default.endBackground",
            "Button.endBackground",
            "Button.mt.background",
            "Button.mt.color1",
            "Button.mt.color2",
            "Button.select",
            "Button.startBackground",
            "ComboBoxButton.background",
            "ComboBox.ArrowButton.background",
            "ComboBox.buttonBackground",
            "Component.borderColor",
            "material.mergeCommits",
            "Notification.MoreButton.background",
            "Notification.MoreButton.innerBorderColor",
            "Outline.color",
            "Plugins.Button.installBackground",
            "Plugins.Button.installBorderColor",
            "Plugins.Button.installFillBackground",
            "Plugins.Button.updateBackground",
            "Plugins.Button.updateBorderColor",
            "ToolBar.comboBoxButtonBackground",
            "WelcomeScreen.groupIconBorderColor"
        ));
  }

  /**
   * Get resources using the secondary background color
   */
  private static Set<String> getSecondaryBackgroundResources() {
    return Collections.unmodifiableSet(
        Sets.newHashSet(
            "CompletionPopup.background",
            "EditorTabs.borderColor",
            "inactiveCaption",
            "List.background",
            "MemoryIndicator.unusedColor",
            "Menu.borderColor",
            "ParameterInfo.background",
            "ParameterInfo.borderColor",
            "Plugins.SectionHeader.background",
            "Popup.separatorColor",
            "ProgressBar.trackColor",
            "Separator.background",
            "Separator.foreground",
            "Separator.separatorColor",
            "Slider.tickColor",
            "Table.lightSelectionInactiveBackground",
            "TextArea.background",
            "ToolWindow.active.Header.background",
            "ToolWindow.Header.background",
            "ToolWindow.header.active.background",
            "ToolWindow.header.border.background",
            "ToolWindow.Header.borderColor",
            "VersionControl.Log.Commit.currentBranchBackground",
            "WelcomeScreen.Projects.background",
            "WelcomeScreen.Projects.selectionInactiveBackground"
        ));
  }

  /**
   * Get resources using the disabled color
   */
  private static Set<String> getDisabledResources() {
    return Collections.unmodifiableSet(
        Sets.newHashSet(
            MTUI.Button.BUTTON_DISABLED_TEXT,
            "CheckBox.darcula.checkSignColorDisabled",
            "CheckBox.darcula.disabledBorderColor1",
            "CheckBox.darcula.disabledBorderColor2",
            "CheckBox.disabledText",
            "CheckBoxMenuItem.disabledForeground",
            "ComboBox.ArrowButton.disabledIconColor",
            "ComboBox.darcula.arrowButtonDisabledForeground",
            "ComboBox.disabledForeground",
            "Component.disabledBorderColor",
            "EditorPane.inactiveForeground",
            "FormattedTextField.inactiveForeground",
            "Label.disabledForeground",
            "Label.disabledShadow",
            "Menu.disabledForeground",
            "MenuBar.disabledForeground",
            "MenuItem.disabledForeground",
            "Outline.disabledColor",
            "ParameterInfo.disabledColor",
            "PasswordField.inactiveForeground",
            "Plugins.disabledForeground",
            "RadioButton.disabledText",
            "RadioButtonMenuItem.disabledForeground",
            "SearchEverywhere.SearchField.grayForeground",
            "SearchEverywhere.SearchField.infoForeground",
            "TabbedPane.disabledForeground",
            "TabbedPane.disabledText",
            "TabbedPane.disabledUnderlineColor",
            "TabbedPane.selectedDisabledColor",
            "TextArea.inactiveForeground",
            "TextField.inactiveForeground",
            "TextPane.inactiveForeground",
            "ToggleButton.disabledText"
        ));
  }

  /**
   * Get resources using the contrast color
   */
  private static Set<String> getContrastResources() {
    return Collections.unmodifiableSet(
        Sets.newHashSet(
            "EditorPane.background",
            "HeaderColor.inactive",
            "material.contrast",
            "Popup.Border.color",
            "Popup.borderColor",
            "Popup.Header.inactiveBackground",
            "Popup.Toolbar.background",
            "Popup.Toolbar.Border.color",
            "Popup.Toolbar.borderColor",
            "ScrollBar.thumb",
            "SearchEverywhere.Advertiser.background",
            "SearchEverywhere.SearchField.background",
            "Table.stripeColor",
            "Table.stripedBackground",
            "TitlePane.background",
            "ToolBar.background",
            "ToolWindow.Button.selectedBackground",
            "ToolWindow.header.tab.selected.active.background",
            "ToolWindow.header.tab.selected.background",
            "ToolWindow.HeaderTab.selectedInactiveBackground",
            "ToolWindow.HeaderTab.selectedBackground",
            "ToolWindow.inactive.HeaderTab.background",
            "ToolWindow.active.HeaderTab.background",
            "WelcomeScreen.captionBackground",
            "WelcomeScreen.footerBackground"
        ));
  }

  /**
   * Get resources using the table/button selection color
   */
  private static Set<String> getTableSelectedResources() {
    return Collections.unmodifiableSet(
        Sets.newHashSet(
            "Button.darcula.defaultFocusedBorderColor",
            "Button.darcula.focusedBorderColor",
            "Button.darcula.selection.color1",
            "Button.darcula.selection.color2",
            "Button.mt.selection.color1",
            "Button.mt.selection.color2",
            "Button.focus",
            "ComboBox.selectionBackground",
            "EditorTabs.active.background",
            "EditorTabs.selectedBackground",
            "FormattedTextField.selectionBackground",
            "ParameterInfo.borderColor",
            "PasswordField.selectionBackground",
            "Plugins.selectionBackground",
            "Plugins.Tab.active.background",
            "Plugins.Tab.selectedBackground",
            "Plugins.Tab.hover.background",
            "Slider.track",
            "TabbedPane.focusColor",
            "Table.focusCellBackground",
            "Table.lightSelectionBackground",
            "Table.selectionBackground",
            "TextArea.selectionBackground",
            "TextField.selectionBackground",
            "TextPane.selectionBackground",
            "ToolWindow.Button.hoverBackground"
        ));
  }

  /**
   * Get resources using the second border color
   */
  private static Set<String> getSecondBorderResources() {
    return Collections.unmodifiableSet(
        Sets.newHashSet(
            "Borders.color",
            "Button.darcula.disabledOutlineColor",
            "Button.darcula.shadowColor",
            "Button.disabledBorderColor",
            "Button.shadowColor",
            "HelpTooltip.borderColor",
            "Menu.separatorColor",
            "OnePixelDivider.background",
            "Plugins.SearchField.borderColor",
            "Popup.Separator.color",
            "SearchEverywhere.List.Separator.Color",
            "SearchEverywhere.List.separatorForeground",
            "SearchEverywhere.List.Separator.foreground",
            "SearchEverywhere.List.separatorColor",
            "SpeedSearch.borderColor",
            "TabbedPane.darkShadow",
            "TabbedPane.highlight",
            "TabbedPane.shadow",
            "ToolWindow.HeaderTab.hoverInactiveBackground",
            "WelcomeScreen.separatorColor",
            "windowBorder"
        ));
  }

  /**
   * Get resources using the highlight color
   */
  private static Set<String> getHighlightResources() {
    return Collections.unmodifiableSet(
        Sets.newHashSet(
            "ActionButton.pressedBackground",
            "ActionButton.pressedBorderColor",
            "Autocomplete.selectionUnfocus",
            "CheckBox.darcula.inactiveFillColor",
            "CompletionPopup.selectionInactiveBackground",
            "Component.focusedBorderColor",
            "DebuggerTabs.active.background",
            "Focus.color",
            "Github.List.tallRow.selectionBackground.unfocused",
            "MemoryIndicator.usedColor",
            "Outline.focusedColor",
            "Plugins.Button.installFocusedBackground",
            "Plugins.eapTagBackground",
            "Plugins.tagBackground",
            "ProgressBar.halfColor",
            "ProgressBar.trackColor",
            "ProgressBar.selectionBackground",
            "SearchEverywhere.Tab.active.background",
            "SearchEverywhere.Tab.selectedBackground",
            "SearchEverywhere.Tab.selected.background",
            "SpeedSearch.background",
            "Slider.trackDisabled",
            "TabbedPane.contentAreaColor",
            "TabbedPane.hoverColor",
            "TabbedPane.selectHighlight",
            "TabbedPane.selectedColor",
            "TabbedPane.underlineColor",
            "TableHeader.borderColor",
            "TextField.separatorColor",
            "ToolWindow.HeaderTab.hoverBackground",
            "VersionControl.Ref.backgroundBase"
        ));
  }

  /**
   * Get resources using the tree selected row color
   */
  private static Set<String> getTreeSelectionResources() {
    return Collections.unmodifiableSet(
        Sets.newHashSet(
            "CompletionPopup.nonFocusedState",
            "List.selectionInactiveBackground",
            "Table.selectionInactiveBackground",
            "Tree.selectionBackground",
            "Tree.selectionInactiveBackground"
        ));
  }

  /**
   * Get notifications colors resources
   */
  private static Set<String> getNotificationsResources() {
    return Collections.unmodifiableSet(
        Sets.newHashSet(
            "Notification.background",
            "Notification.borderColor",
            "Notifications.background",
            "Notifications.borderColor",
            "ValidationTooltip.errorBackground",
            "ValidationTooltip.errorBackgroundColor",
            "ValidationTooltip.errorBorderColor",
            "ValidationTooltip.warningBackground",
            "ValidationTooltip.warningBackgroundColor",
            "ValidationTooltip.warningBorderColor"
        ));
  }
  //endregion

  /**
   * Iterate over theme resources and fill up the UIManager
   */
  private static void buildResources(final Iterable<String> resources, final Color color) {
    for (final String resource : resources) {
      UIManager.getDefaults().put(resource, color);
    }
  }

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
    UIManager.put("Notification.ToolWindowInfo.borderColor", infoColor);
    UIManager.put("Notification.ToolWindow.infoBorderColor", infoColor);
    UIManager.put("Notification.ToolWindowInfo.background", infoColor);
    UIManager.put("Notification.ToolWindow.infoBackground", infoColor);
  }

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
}
