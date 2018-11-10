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

package com.chrisrm.idea.themes.models;

import com.chrisrm.idea.themes.MTCustomTheme;
import com.chrisrm.idea.utils.MTColorUtils;
import com.intellij.util.ObjectUtils;
import com.intellij.util.xmlb.annotations.Property;
import com.intellij.util.xmlb.annotations.Tag;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.plaf.ColorUIResource;
import java.util.Collections;
import java.util.List;

/**
 * Represents a Dark theme parsed from XML
 */
public class MTDarkBundledTheme extends MTCustomTheme implements MTBundledTheme {
  @Tag
  private List<? extends MTThemeColor> colors;
  @Property
  private String themeId;
  @Property
  private String accentColor;
  @Property
  private String excludedColor;

  @NonNls
  @NotNull
  @Override
  public final String getId() {
    return "mt.dark";
  }

  private static final String NOTIFICATIONS_COLOR = "323232";
  private static final String TREE_SELECTION_COLOR = "546E7A50";
  private static final String HIGHLIGHT_COLOR = "425B67";
  private static final String SECOND_BORDER_COLOR = "2A373E";
  private static final String TABLE_SELECTED_COLOR = "314549";
  private static final String CONTRAST_COLOR = "1E272C";
  private static final String DISABLED_COLOR = "415967";
  private static final String SECONDARY_BACKGROUND_COLOR = "32424A";
  private static final String BUTTON_COLOR = "2E3C43";
  private static final String SELECTION_FOREGROUND_COLOR = "FFFFFF";
  private static final String SELECTION_BACKGROUND_COLOR = "546E7A";
  private static final String TEXT_COLOR = "607D8B";
  private static final String FOREGROUND_COLOR = "B0BEC5";
  private static final String BACKGROUND_COLOR = "263238";

  @Override
  public final List<MTThemeColor> getColors() {
    return Collections.unmodifiableList(colors);
  }

  @NotNull
  @Override
  public final String getThemeId() {
    return themeId;
  }

  @Override
  public final ColorUIResource getNotificationsColorString() {
    return new ColorUIResource(MTColorUtils.parseColor(ObjectUtils.notNull(findColor(NOTIFICATIONS_TAG), NOTIFICATIONS_COLOR)));
  }

  @Override
  public final ColorUIResource getTreeSelectionColorString() {
    return new ColorUIResource(MTColorUtils.parseColor(ObjectUtils.notNull(findColor(TREE_SELECTION_TAG), TREE_SELECTION_COLOR)));
  }

  @Override
  public final ColorUIResource getHighlightColorString() {
    return new ColorUIResource(MTColorUtils.parseColor(ObjectUtils.notNull(findColor(HIGHLIGHT_TAG), HIGHLIGHT_COLOR)));
  }

  @Override
  public final ColorUIResource getSecondBorderColorString() {
    return new ColorUIResource(MTColorUtils.parseColor(ObjectUtils.notNull(findColor(SECOND_BORDER_TAG), SECOND_BORDER_COLOR)));
  }

  @Override
  public final ColorUIResource getTableSelectedColorString() {
    return new ColorUIResource(MTColorUtils.parseColor(ObjectUtils.notNull(findColor(TABLE_SELECTED_TAG), TABLE_SELECTED_COLOR)));
  }

  @Override
  public final ColorUIResource getContrastColorString() {
    return new ColorUIResource(MTColorUtils.parseColor(ObjectUtils.notNull(findColor(CONTRAST_TAG), CONTRAST_COLOR)));
  }

  @Override
  public final ColorUIResource getDisabledColorString() {
    return new ColorUIResource(MTColorUtils.parseColor(ObjectUtils.notNull(findColor(DISABLED_TAG), DISABLED_COLOR)));
  }

  @Override
  public final ColorUIResource getSecondaryBackgroundColorString() {
    return new ColorUIResource(MTColorUtils.parseColor(ObjectUtils.notNull(findColor(SECONDARY_BACKGROUND_TAG),
        SECONDARY_BACKGROUND_COLOR)));
  }

  @Override
  public final ColorUIResource getButtonColorString() {
    return new ColorUIResource(MTColorUtils.parseColor(ObjectUtils.notNull(findColor(BUTTON_TAG), BUTTON_COLOR)));
  }

  @Override
  public final ColorUIResource getSelectionForegroundColorString() {
    return new ColorUIResource(MTColorUtils.parseColor(ObjectUtils.notNull(findColor(SELECTION_FOREGROUND_TAG),
        SELECTION_FOREGROUND_COLOR)));
  }

  @Override
  public final ColorUIResource getSelectionBackgroundColorString() {
    return new ColorUIResource(MTColorUtils.parseColor(ObjectUtils.notNull(findColor(SELECTION_BACKGROUND_TAG),
        SELECTION_BACKGROUND_COLOR)));
  }

  @Override
  public final ColorUIResource getTextColorString() {
    return new ColorUIResource(MTColorUtils.parseColor(ObjectUtils.notNull(findColor(TEXT_TAG), TEXT_COLOR)));
  }

  @Override
  public final ColorUIResource getForegroundColorString() {
    return new ColorUIResource(MTColorUtils.parseColor(ObjectUtils.notNull(findColor(FOREGROUND_TAG), FOREGROUND_COLOR)));
  }

  @Override
  public final ColorUIResource getBackgroundColorString() {
    return new ColorUIResource(MTColorUtils.parseColor(ObjectUtils.notNull(findColor(BACKGROUND_TAG), BACKGROUND_COLOR)));
  }

  @Override
  public final ColorUIResource getAccentColorString() {
    return MTColorUtils.parseColor(accentColor);
  }

  @Override
  public final ColorUIResource getExcludedColorString() {
    return MTColorUtils.parseColor(excludedColor);
  }

  @Override
  public final int getOrder() {
    return 100;
  }
}
