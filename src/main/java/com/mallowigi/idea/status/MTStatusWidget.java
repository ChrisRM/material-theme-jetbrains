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

package com.mallowigi.idea.status;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.CustomStatusBarWidget;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.StatusBarWidget;
import com.intellij.openapi.wm.impl.status.EditorBasedWidget;
import com.intellij.ui.ColorUtil;
import com.intellij.util.Consumer;
import com.intellij.util.messages.MessageBusConnection;
import com.intellij.util.ui.ImageUtil;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.StartupUiUtil;
import com.intellij.util.ui.UIUtil;
import com.mallowigi.idea.MTConfig;
import com.mallowigi.idea.listeners.AccentsListener;
import com.mallowigi.idea.listeners.ConfigNotifier;
import com.mallowigi.idea.listeners.MTTopics;
import com.mallowigi.idea.listeners.ThemeListener;
import com.mallowigi.idea.themes.MTThemeFacade;
import com.mallowigi.idea.utils.MTUiUtils;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.text.AttributedString;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@SuppressWarnings({"SyntheticAccessorCall",
  "AssignmentToStaticFieldFromInstanceMethod"})
final class MTStatusWidget extends EditorBasedWidget implements CustomStatusBarWidget, StatusBarWidget.IconPresentation {

  @NonNls
  private static final String MT_SETTINGS_PAGE = "Material Theme";
  private final Project project;
  private final MTWidget mtWidget;
  @Nullable
  private static Image myBufferedImage = null;

  MTStatusWidget(final Project project) {
    super(project);
    this.project = project;
    mtWidget = new MTWidget();
  }

  @Nullable
  @Override
  public String getTooltipText() {
    return null;
  }

  @Override
  public WidgetPresentation getPresentation() {
    return this;
  }

  @Override
  public Consumer<MouseEvent> getClickConsumer() {
    return e -> ShowSettingsUtil.getInstance().showSettingsDialog(project, MT_SETTINGS_PAGE);
  }

  @NonNls
  @NotNull
  @Override
  public String ID() {
    return "MTStatusBarWidget";
  }

  @Override
  public void install(@NotNull final StatusBar statusBar) {
    super.install(statusBar);

    final MessageBusConnection connect = ApplicationManager.getApplication().getMessageBus().connect(this);
    connect.subscribe(MTTopics.THEMES, new ThemeListener() {
      @Override
      public void themeChanged(@NotNull final MTThemeFacade theme) {
        refresh();
      }
    });
    connect.subscribe(MTTopics.ACCENTS, new AccentsListener() {
      @Override
      public void accentChanged(@NotNull final Color accentColor) {
        refresh();
      }
    });
    connect.subscribe(ConfigNotifier.CONFIG_TOPIC, new ConfigNotifier() {
      @Override
      public void configChanged(final MTConfig mtConfig) {
        refresh();
      }
    });
  }

  private void refresh() {
    if (project.isDisposed()) {
      return;
    }
    myBufferedImage = null;
    mtWidget.setVisible(MTConfig.getInstance().isStatusBarTheme());
    mtWidget.repaint();
    mtWidget.updateUI();
  }

  @Override
  public JComponent getComponent() {
    return mtWidget;
  }

  @Nullable
  @Override
  public Icon getIcon() {
    return null;
  }

  @SuppressWarnings("MagicNumber")
  static final class MTWidget extends JButton {
    private static final int DEFAULT_FONT_SIZE = JBUI.scale(11);
    private static final int STATUS_PADDING = 4;
    private static final int STATUS_HEIGHT = 16;
    private final MTConfig mtConfig;

    MTWidget() {
      mtConfig = MTConfig.getInstance();
      setFont(getWidgetFont());
    }

    /**
     * Returns the widget font
     */
    private static Font getWidgetFont() {
      final GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
      final Font[] fonts = e.getAllFonts();
      for (final Font font : fonts) {
        if (Objects.equals(font.getFontName(), MTUiUtils.MATERIAL_FONT)) {
          final Map<TextAttribute, Object> attributes = new HashMap<>(10);

          attributes.put(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
          attributes.put(TextAttribute.SIZE, JBUI.scale(DEFAULT_FONT_SIZE));

          return font.deriveFont(attributes);
        }
      }
      return JBUI.Fonts.create(Font.DIALOG, 12);
    }

    @Override
    @SuppressWarnings("FeatureEnvy")
    public void paintComponent(final Graphics g) {
      if (!mtConfig.isStatusBarTheme()) {
        return;
      }

      final String themeName = mtConfig.getSelectedTheme().getTheme().getName();
      if (themeName.isEmpty()) {
        return;
      }

      final Color accentColor = ColorUtil.fromHex(mtConfig.getAccentColor());
      final int accentDiameter = JBUI.scale(STATUS_HEIGHT - 2);

      if (myBufferedImage == null) {
        final Dimension size = getSize();
        final Dimension arcs = new Dimension(8, 8);

        myBufferedImage = ImageUtil.createImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
        final Graphics2D g2 = (Graphics2D) myBufferedImage.getGraphics().create();
        final FontMetrics fontMetrics = g.getFontMetrics();

        g2.setRenderingHints(MTUiUtils.getHints());

        final int nameWidth = fontMetrics.charsWidth(themeName.toCharArray(), 0, themeName.length());
        final int nameHeight = fontMetrics.getAscent();

        final AttributedString as = new AttributedString(themeName);

        as.addAttribute(TextAttribute.FAMILY, getFont().getFamily());
        as.addAttribute(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
        as.addAttribute(TextAttribute.SIZE, DEFAULT_FONT_SIZE);

        // background
        g2.setColor(mtConfig.getSelectedTheme().getTheme().getContrastColor());
        g2.fillRoundRect(0, 0, size.width + accentDiameter - JBUI.scale(arcs.width), JBUI.scale(STATUS_HEIGHT), arcs.width, arcs.height);

        // label
        g2.setColor(UIUtil.getLabelForeground());
        g2.setFont(getFont());
        g2.drawString(as.getIterator(), (size.width - accentDiameter - nameWidth) / 2,
          nameHeight + (size.height - nameHeight) / 2 - JBUI.scale(1));

        g2.setColor(accentColor);
        g2.fillOval(size.width - JBUI.scale(STATUS_HEIGHT), JBUI.scale(1), accentDiameter, accentDiameter);
        g2.dispose();
      }

      StartupUiUtil.drawImage(g, myBufferedImage, 0, 0, null);
    }

    @Override
    public Dimension getPreferredSize() {
      final String themeName = mtConfig.getSelectedTheme().getThemeColorScheme();
      final int width = getFontMetrics(getWidgetFont()).charsWidth(themeName.toCharArray(), 0,
        themeName.length()) + 2 * STATUS_PADDING;
      final int accentDiameter = JBUI.scale(STATUS_HEIGHT);
      return new Dimension(width + accentDiameter, accentDiameter);
    }

    @Override
    public Dimension getMinimumSize() {
      return getPreferredSize();
    }

    @Override
    public Dimension getMaximumSize() {
      return getPreferredSize();
    }
  }
}
