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
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

import static com.chrisrm.idea.utils.MTColorUtils.contrastifyBackground;
import static com.chrisrm.idea.utils.MTColorUtils.contrastifyForeground;

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
      buildFileColors();

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
    return getSecondaryBackgroundColorResource();
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

  //region MTThemeable methods

  /**
   * Get resources using the background color
   */
  @NonNls
  private static Set<String> getBackgroundResources() {
    return Collections.unmodifiableSet(
        Sets.newHashSet(
            "activeCaption", // deprecated
            "Borders.ContrastBorderColor",
            "Button.darcula.disabledText.shadow", // deprecated
            "Button.default.shadowColor",
            "CheckBox.background",
            "CheckBox.darcula.backgroundColor1", //deprecated
            "CheckBox.darcula.backgroundColor2", // deprecated
            "CheckBox.darcula.checkSignColor", // deprecated
            "CheckBox.darcula.checkSignColor.selected", // deprecated
            "CheckBox.darcula.focused.backgroundColor1", // deprecated
            "CheckBox.darcula.focused.backgroundColor2", // deprecated
            "CheckBox.darcula.focusedArmed.backgroundColor1", // deprecated
            "CheckBox.darcula.focusedArmed.backgroundColor2", // deprecated
            "CheckBox.darcula.shadowColor", // deprecated
            "CheckBox.darcula.shadowColorDisabled", // deprecated
            "CheckBoxMenuItem.background",
            "CheckBoxMenuItem.disabledBackground",
            "ColorChooser.background",
            "ComboBox.ArrowButton.nonEditableBackground",
            "ComboBox.arrowFillColor",
            "ComboBox.background",
            "ComboBox.darcula.arrowButtonBackground", // deprecated
            "ComboBox.darcula.disabledArrowButtonBackground", // deprecated
            "ComboBox.darcula.editable.arrowButtonBackground", // deprecated
            "ComboBox.darcula.nonEditableBackground", // deprecated
            "ComboBox.disabledBackground",
            "control",
            "darcula.background",
            "DebuggerPopup.borderColor",
            "DefaultTabs.background",
            "DefaultTabs.borderColor", //deprecated?
            "Desktop.background",
            "Dialog.titleColor", // deprecated
            "DialogWrapper.southPanelBackground",
            "DialogWrapper.southPanelDivider",
            "DragAndDrop.areaBorderColor",
            "DragAndDrop.backgroundBorderColor", // deprecated
            "DragAndDrop.backgroundColor", //deprecated
            "DragAndDrop.areaBackground",
            "Editor.background",
            "EditorPane.inactiveBackground",
            "EditorTabs.background", // deprecated
            "EditorTabs.selectedBackground",
            "EditorTabs.inactive.maskColor", // deprecated
            "EditorTabs.inactiveMaskColor",
            "FormattedTextField.background",
            "GutterTooltip.borderColor", // deprecated
            "GutterTooltip.lineSeparatorColor",
            "HeaderColor.active", // deprecated
            "HelpTooltip.background",
            "HelpTooltip.backgroundColor", // deprecated
            "inactiveCaptionBorder",
            "intellijlaf.background", // deprecated
            "InplaceRefactoringPopup.borderColor",
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
            "Popup.Border.inactiveColor", // deprecated
            "Popup.Header.activeBackground",
            "Popup.inactiveBorderColor",
            "Popup.preferences.background", // deprecated
            "Popup.preferences.borderColor", // deprecated
            "Popup.Toolbar.background",
            "Popup.Toolbar.borderColor",
            "PopupMenu.background",
            "PopupMenu.translucentBackground",
            "RadioButton.background",
            "RadioButton.darcula.selectionDisabledColor", // deprecated
            "RadioButton.selectionDisabledColor", // deprecated
            "ScrollBar.Transparent.hoverTrackColor",
            "ScrollBar.Transparent.trackColor",
            "ScrollBar.hoverTrackColor",
            "ScrollBar.trackColor",
            "ScrollBar.Mac.Transparent.hoverTrackColor",
            "ScrollBar.Mac.Transparent.trackColor",
            "ScrollBar.Mac.hoverTrackColor",
            "ScrollBar.Mac.trackColor",
            "SearchEverywhere.background", //deprecated
            "SearchEverywhere.Dialog.background", //deprecated
            "SearchEverywhere.Header.background",
            "SearchEverywhere.SearchField.Border.color", //deprecated
            "SearchEverywhere.SearchField.borderColor",
            "SidePanel.background",
            "Slider.background",
            "Spinner.background",
            "SplitPane.highlight",
            "StatusBar.background", //deprecated
            "StatusBar.borderColor",
            "StatusBar.bottomColor", //deprecated
            "StatusBar.top2Color", //deprecated
            "StatusBar.topColor", //deprecated
            "TabbedPane.background",
            "TabbedPane.borderColor", // deprecated
            "TabbedPane.mt.tab.background",
            "Table.background",
            "Table.gridColor",
            "TextField.background",
            "TextField.borderColor", // deprecated
            "TextField.focusedBorderColor", // deprecated
            "TextField.hoverBorderColor", //deprecated
            "TextField.separatorColorDisabled", // deprecated
            "TextPane.background",
            "ToolTip.actions.background", // deprecated
            "ToolTip.Actions.background",
            "ToolTip.background",
            "tooltips.actions.settings.icon.background.color", // deprecated
            "ToolWindow.header.background", // deprecated
            "ToolWindow.header.closeButton.background", // deprecated
            "ToolWindow.Header.inactiveBackground",
            "ToolWindow.HeaderCloseButton.background",
            "ToolWindow.inactive.Header.background", // deprecated
            "Tree.background",
            "UiDesigner.Panel.background",
            "UiDesigner.Preview.background",
            "VersionControl.FileHistory.Commit.otherBranchBackground", // deprecated
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
            "CheckBox.darcula.borderColor1", // deprecated
            "CheckBox.foreground",
            "CheckBoxMenuItem.foreground",
            "ColorChooser.foreground",
            "ComboBox.darcula.arrowButtonForeground", // deprecated
            "ComboBox.ArrowButton.iconColor",
            "ComboBox.foreground",
            "CompletionPopup.foreground",
            "darcula.foreground", // deprecated
            "DragAndDrop.areaForeground",
            "DragAndDrop.foregroundColor", // deprecated
            "Editor.foreground",
            "EditorPane.foreground",
            "EditorTabs.active.foreground", // deprecated
            "EditorTabs.selectedForeground",
            "FormattedTextField.foreground",
            "Git.Log.Ref.RemoteBranch", // deprecated
            "Github.List.tallRow.foreground", // deprecated
            "GutterTooltip.infoForeground",
            "Group.separatorColor",
            "HelpTooltip.foreground",
            "HelpTooltip.textColor", // deprecated
            "Hg.Log.Ref.ClosedBranch", //deprecated
            "intellijlaf.foreground", // deprecated
            "InternalFrame.activeTitleForeground",
            "Label.foreground",
            "Label.selectedDisabledForeground", // deprecated
            "List.foreground",
            "material.branchColor",
            "material.foreground",
            "Menu.foreground",
            "MenuBar.foreground",
            "MenuItem.foreground",
            "NavBar.arrowColor",
            "Notification.foreground",
            "Notification.MoreButton.foreground",
            "Notification.ToolWindow.errorForeground",
            "Notification.ToolWindow.infoForeground", // deprecated
            "Notification.ToolWindow.informativeForeground",
            "Notification.ToolWindow.warningForeground",
            "Notification.ToolWindowError.foreground", // deprecated
            "Notification.ToolWindowInfo.foreground", // deprecated
            "Notification.ToolWindowWarning.foreground", // deprecated
            "OptionPane.messageForeground",
            "ParameterInfo.foreground",
            "PasswordField.foreground",
            "Plugins.Button.installForeground",
            "Plugins.Button.updateForeground",
            "Plugins.SectionHeader.foreground",
            "Popup.separatorForeground",
            "Popup.Separator.foreground", // deprecated
            "PopupMenu.foreground",
            "RadioButton.darcula.borderColor1", // deprecated
            "RadioButton.foreground",
            "RadioButtonMenuItem.foreground",
            "SearchEverywhere.foreground", // deprecated
            "SpeedSearch.foreground",
            "SpeedSearch.errorForeground",
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
            "ToggleButton.off.foreground", // deprecated
            "ToggleButton.off.background", // deprecated
            "ToggleButton.offBackground",
            "ToggleButton.offForeground",
            "ToolBar.foreground",
            "ToolTip.foreground",
            "tooltips.description.title.text.color", // deprecated
            "VersionControl.GitLog.remoteBranchIconColor",
            "VersionControl.HgLog.closedBranchIconColor",
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
            "CompletionPopup.grayedForeground", //deprecated
            "CompletionPopup.grayForeground", //deprcated
            "CompletionPopup.infoForeground",
            "CompletionPopup.selectionInactiveForeground",
            "Component.infoForeground",
            "Component.grayForeground", // deprecated
            "controlText",
            "Editor.shortcutForeground",
            "Git.Log.Ref.Other", //deprecated
            "Git.Log.Ref.Tag", // deprecated
            "Github.List.tallRow.secondary.foreground",
            "HelpTooltip.shortcutForeground",
            "HelpTooltip.shortcutTextColor", // deprecated
            "Hg.Log.Ref.LocalTag", //deprecated
            "Hg.Log.Ref.MqTag", //deprecated
            "Hg.Log.Ref.Tag", //deprecated
            "inactiveCaptionText", // deprecated
            "infoText", // deprecated
            "InternalFrame.inactiveTitleForeground", // deprecated
            "Label.grayForeground", // deprecated
            "Label.infoForeground",
            "Label.textForeground", // deprecated
            "Link.secondaryForeground",
            "material.primaryColor",
            "material.tagColor",
            "Menu.acceleratorForeground",
            "MenuItem.acceleratorForeground",
            "ParameterInfo.ContextHelp.foreground", // deprecated
            "ParameterInfo.infoForeground",
            "Plugins.Button.installFillForeground",
            "RadioButtonMenuItem.acceleratorForeground",
            "RadioButtonMenuItem.acceleratorSelectionForeground",
            "SearchEverywhere.shortcutForeground", // deprecated
            "Table.lightSelectionInactiveForeground",
            "text",
            "textInactiveText",
            "textText",
            "ToolBar.borderHandleColor",
            "ToolBar.floatingForeground",
            "ToolTip.Actions.grayForeground", // deprecated
            "ToolTip.Actions.infoForeground",
            "ToolTip.infoForeground",
            "tooltips.actions.keymap.text.color", //deprecated
            "Tree.foreground",
            "VersionControl.GitLog.otherIconColor",
            "VersionControl.GitLog.tagIconColor",
            "VersionControl.HgLog.localTagIconColor",
            "VersionControl.HgLog.mqTagIconColor",
            "VersionControl.HgLog.tagIconColor",
            "VersionControl.HgLog.tipIconColor"
        ));
  }

  /**
   * Get resources using the selection background color
   */
  private static Set<String> getSelectionBackgroundResources() {
    return Collections.unmodifiableSet(
        Sets.newHashSet(
            "Autocomplete.selectionBackground", // deprecated
            "CheckBoxMenuItem.selectionBackground",
            "ComboBox.nonEditableBackground",
            "CompletionPopup.selectionBackground",
            "EditorPane.selectionBackground",
            "Github.List.tallRow.selectionBackground",
            "List.selectionBackground",
            "Menu.selectionBackground",
            "MenuItem.selectionBackground",
            "RadioButtonMenuItem.selectionBackground",
            "TabbedPane.selected", // deprecated
            "VersionControl.Log.Commit.unmatchedForeground",
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
            "Button.darcula.selectedButtonForeground", // deprecated
            "Button.default.foreground",
            "Button.highlight",
            "Button.mt.selectedForeground",
            "CheckBoxMenuItem.selectionForeground",
            "ComboBox.selectionForeground",
            "CompletionPopup.selectedForeground", //deprecated
            "CompletionPopup.selectionForeground",
            "CompletionPopup.selectedGrayedForeground", //deprecated
            "CompletionPopup.selectionGrayForeground", // deprecated
            "CompletionPopup.selectionInfoForeground",
            "Counter.foreground",
            "EditorPane.selectionForeground",
            "FormattedTextField.selectionForeground",
            "Github.List.tallRow.selectionForeground", // deprecated
            "Github.List.tallRow.selectionForeground.unfocused", //deprecated
            "Label.selectedForeground",
            "List.selectionForeground",
            "List.selectionInactiveForeground",
            "Menu.acceleratorSelectionForeground",
            "Menu.selectionForeground",
            "MenuItem.acceleratorSelectionForeground",
            "MenuItem.selectionForeground",
            "PasswordField.selectionForeground",
            "Plugins.selectionForeground", // deprecated
            "Plugins.Tab.active.foreground", // deprecated
            "Plugins.Tab.selectedForeground",
            "SearchEverywhere.Tab.active.foreground", // deprecated
            "SearchEverywhere.Tab.selectedForeground",
            "SearchEverywhere.Tab.selected.foreground", // deprecated
            "TabbedPane.selectedForeground", // deprecated
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
            "VersionControl.Ref.foreground", //deprecated
            "VersionControl.RefLabel.foreground",
            "VersionControl.HgLog.bookmarkIconColor",
            "material.selectionForeground"
        ));
  }

  /**
   * Get resources using the button color
   */
  private static Set<String> getButtonColorResources() {
    return Collections.unmodifiableSet(
        Sets.newHashSet(
            "Button.background", // deprecated
            "Button.darcula.borderColor", // deprecated
            "Button.darcula.defaultBorderColor", // deprecated
            "Button.darcula.defaultEndColor", // deprecated
            "Button.darcula.defaultOutlineColor", // deprecated
            "Button.darcula.defaultStartColor", // deprecated
            "Button.darcula.disabledBorderColor", // deprecated
            "Button.darcula.endColor", // deprecated
            "Button.darcula.outlineColor", // deprecated
            "Button.darcula.smallComboButtonBackground", // deprecated
            "Button.darcula.startColor", // deprecated
            "Button.default.borderColor", // deprecated
            "Button.endBackground",
            "Button.mt.background",
            "Button.mt.color1", // deprecated
            "Button.mt.color2", // deprecated
            "Button.select", // deprecated
            "Button.startBackground",
            "ComboBoxButton.background",
            "ComboBox.ArrowButton.background",
            "ComboBox.buttonBackground",
            "Component.borderColor",
            "material.mergeCommits",
            "Notification.MoreButton.background",
            "Notification.MoreButton.innerBorderColor",
            "Outline.color", // deprecated
            "Plugins.Button.installBackground",
            "Plugins.Button.installBorderColor",
            "Plugins.Button.installFillBackground",
            "Plugins.Button.updateBackground",
            "Plugins.Button.updateBorderColor",
            "ToggleButton.borderColor",
            "ToggleButton.buttonColor",
            "ToolBar.comboBoxButtonBackground", // deprecated
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
            "MemoryIndicator.allocatedBackground",
            "MemoryIndicator.unusedColor", // deprecated
            "Menu.borderColor",
            "ParameterInfo.background",
            "Plugins.SectionHeader.background",
            "Popup.separatorColor",
            "Separator.background",
            "Separator.foreground",
            "Separator.separatorColor",
            "Slider.tickColor",
            "Table.lightSelectionInactiveBackground",
            "TextArea.background",
            "ToolWindow.active.Header.background",
            "ToolWindow.Header.background",
            "ToolWindow.header.active.background", //deprecated
            "ToolWindow.header.border.background", //deprecated
            "ToolWindow.Header.borderColor",
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
            "Button.disabledText",
            "CheckBox.darcula.checkSignColorDisabled", // deprecated
            "CheckBox.darcula.disabledBorderColor1", // deprecated
            "CheckBox.darcula.disabledBorderColor2", // deprecated
            "CheckBox.disabledText",
            "CheckBoxMenuItem.disabledForeground",
            "ComboBox.ArrowButton.disabledIconColor",
            "ComboBox.darcula.arrowButtonDisabledForeground", // deprecated
            "ComboBox.disabledForeground",
            "Component.disabledBorderColor",
            "EditorPane.inactiveForeground",
            "FormattedTextField.inactiveForeground",
            "Label.disabledForeground",
            "Label.disabledForegroundColor", // deprecated
            "Label.disabledShadow", // deprecated
            "Label.disabledText",
            "Menu.disabledForeground",
            "MenuBar.disabledForeground",
            "MenuItem.disabledForeground",
            "Outline.disabledColor", // deprecated
            "ParameterInfo.disabledColor", //deprecated
            "ParameterInfo.disabledForeground",
            "PasswordField.inactiveForeground",
            "Plugins.disabledForeground",
            "RadioButton.disabledText",
            "RadioButtonMenuItem.disabledForeground",
            "SearchEverywhere.SearchField.grayForeground", // deprecated
            "SearchEverywhere.SearchField.infoForeground",
            "TabbedPane.disabledForeground",
            "TabbedPane.disabledText", // deprecated
            "TabbedPane.disabledUnderlineColor",
            "TabbedPane.selectedDisabledColor",
            "TextArea.inactiveForeground",
            "TextField.inactiveForeground",
            "TextPane.inactiveForeground",
            "ToggleButton.disabledText",
            "VersionControl.HgLog.closedBranchIconColor"
        ));
  }

  /**
   * Get resources using the contrast color
   */
  private static Set<String> getContrastResources() {
    return Collections.unmodifiableSet(
        Sets.newHashSet(
            "DefaultTabs.inactiveMaskColor", // deprecated
            "EditorPane.background",
            "HeaderColor.inactive", // deprecated
            "material.contrast",
            "Popup.Border.color", // deprecated
            "Popup.borderColor",
            "Popup.Header.inactiveBackground",
            "Popup.Toolbar.background",
            "Popup.Toolbar.Border.color", // deprecated
            "Popup.Toolbar.borderColor",
            "ScrollBar.thumb",
            "SearchEverywhere.Advertiser.background",
            "SearchEverywhere.SearchField.background",
            "Table.stripeColor",
            "Table.stripedBackground", // deprecated
            "TitlePane.background",
            "ToolBar.background",
            "ToolWindow.Button.selectedBackground",
            "ToolWindow.header.tab.selected.active.background", // deprecated
            "ToolWindow.header.tab.selected.background", // deprecated
            "ToolWindow.HeaderTab.selectedInactiveBackground",
            "ToolWindow.HeaderTab.selectedBackground",
            "ToolWindow.inactive.HeaderTab.background", // deprecated
            "ToolWindow.active.HeaderTab.background", // deprecated
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
            "Button.darcula.defaultFocusedBorderColor", // deprecated
            "Button.darcula.focusedBorderColor", // deprecated
            "Button.darcula.selection.color1", // deprecated
            "Button.darcula.selection.color2", // deprecated
            "Button.mt.selection.color1",
            "Button.mt.selection.color2",
            "Button.default.startBackground",
            "Button.default.endBackground",
            "Button.focus", // deprecated
            "ComboBox.selectionBackground",
            "EditorTabs.active.background", // deprecated
            "EditorTabs.selectedBackground",
            "FormattedTextField.selectionBackground",
            "ParameterInfo.borderColor",
            "ParameterInfo.lineSeparatorColor",
            "ParameterInfo.currentOverloadBackground",
            "PasswordField.selectionBackground",
            "Plugins.selectionBackground", // deprecated
            "Plugins.lightSelectionBackground",
            "Plugins.Tab.active.background", // deprecated
            "Plugins.Tab.selectedBackground",
            "Plugins.Tab.hover.background", // deprecated
            "Plugins.Tab.hoverBackground",
            "Slider.track", // deprecated
            "TabbedPane.focusColor",
            "Table.highlightOuter",
            "Table.focusCellBackground",
            "Table.lightSelectionBackground", // deprecated
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
            "Button.darcula.disabledOutlineColor", // deprecated
            "Button.darcula.shadowColor", // deprecated
            "Button.disabledBorderColor",
            "Button.shadowColor",
            "ComboPopup.border",
            "Group.disabledSeparatorColor",
            "HelpTooltip.borderColor",
            "InformationHint.borderColor",
            "Menu.separatorColor",
            "OnePixelDivider.background", // deprecated
            "Plugins.SearchField.borderColor",
            "Popup.Separator.color", // deprecated
            "SearchEverywhere.List.Separator.Color", // deprecated
            "SearchEverywhere.List.separatorForeground",
            "SearchEverywhere.List.Separator.foreground", // deprecated
            "SearchEverywhere.List.separatorColor",
            "SpeedSearch.borderColor",
            "TabbedPane.darkShadow", // deprecated
            "TabbedPane.highlight", // deprecated
            "TabbedPane.shadow", // deprecated
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
            //            "ActionButton.pressedBackground",
            //            "ActionButton.pressedBorderColor",
            "Autocomplete.selectionUnfocus", // deprecated
            "CheckBox.darcula.inactiveFillColor", // deprecated
            "CompletionPopup.selectionInactiveBackground",
            "Component.focusedBorderColor",
            "DefaultTabs.hoverColor", // deprecated
            "DefaultTabs.hoverMaskColor", // deprecated
            "DebuggerTabs.active.background", // deprecated
            "DebuggerTabs.selectedBackground",
            "EditorTabs.hoverColor", // deprecated
            "EditorTabs.hoverMaskColor", // deprecated
            "Focus.color", // deprecated
            "Github.List.tallRow.selectionBackground.unfocused", // deprecated
            "MemoryIndicator.usedColor", // deprecated
            "MemoryIndicator.usedBackground",
            "Outline.focusedColor", // deprecated
            "Plugins.Button.installFocusedBackground",
            "Plugins.eapTagBackground",
            "Plugins.tagBackground",
            "ProgressBar.halfColor", // deprecated
            "ProgressBar.trackColor",
            "ProgressBar.selectionBackground",
            "SearchEverywhere.Tab.active.background", // deprecated
            "SearchEverywhere.Tab.selectedBackground",
            "SearchEverywhere.Tab.selected.background", // deprecated
            "SpeedSearch.background",
            "Slider.trackDisabled", // deprecated
            "TabbedPane.contentAreaColor",
            "TabbedPane.hoverColor",
            "TabbedPane.selectHighlight", // deprecated
            "TabbedPane.selectedColor", // deprecated
            "TabbedPane.underlineColor",
            "TableHeader.borderColor", // deprecated
            "TextField.separatorColor", // deprecated
            "ToolWindow.HeaderTab.hoverBackground",
            "VersionControl.Ref.backgroundBase", //deprecated
            "VersionControl.RefLabel.backgroundBase"
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
            "Tree.selectionInactiveBackground",
            "VersionControl.Log.Commit.currentBranchBackground"
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
            "Notifications.background", // deprecated
            "Notifications.borderColor", // deprecated
            "ValidationTooltip.errorBackground",
            "ValidationTooltip.errorBackgroundColor", // deprecated
            "ValidationTooltip.errorBorderColor",
            "ValidationTooltip.warningBackground",
            "ValidationTooltip.warningBackgroundColor", // deprecated
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
    UIManager.put("Notification.ToolWindowInfo.borderColor", infoColor); // deprecated
    UIManager.put("Notification.ToolWindow.infoBorderColor", infoColor); // deprecated
    UIManager.put("Notification.ToolWindow.informativeBorderColor", infoColor);

    UIManager.put("Notification.ToolWindowInfo.background", infoColor); // deprecated
    UIManager.put("Notification.ToolWindow.infoBackground", infoColor); // deprecated
    UIManager.put("Notification.ToolWindow.informativeBackground", infoColor); // deprecated
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

  private static void buildFileColors() {
    UIManager.put("FileColor.Green", MTUI.MTColor.DARK_GREEN);
    UIManager.put("FileColor.Blue", MTUI.MTColor.DARK_BLUE);

    UIManager.put("FileColor.Yellow", MTUI.MTColor.DARK_YELLOW);
    UIManager.put("FileColor.Orange", MTUI.MTColor.DARK_ORANGE);
    UIManager.put("FileColor.Violet", MTUI.MTColor.DARK_PURPLE);
    UIManager.put("FileColor.Rose", MTUI.MTColor.DARK_PINK);
  }
}
