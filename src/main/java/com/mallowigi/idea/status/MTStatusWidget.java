/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015-2021 Elior "Mallowigi" Boukhobza
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

import com.intellij.openapi.Disposable;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.wm.CustomStatusBarWidget;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.ui.ColorUtil;
import com.intellij.ui.GotItTooltip;
import com.intellij.util.messages.MessageBusConnection;
import com.intellij.util.ui.*;
import com.mallowigi.idea.config.application.MTConfig;
import com.mallowigi.idea.listeners.ConfigNotifier;
import com.mallowigi.idea.listeners.MTTopics;
import com.mallowigi.idea.messages.MaterialThemeBundle;
import com.mallowigi.idea.utils.MTUI;
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
final class MTStatusWidget implements CustomStatusBarWidget {

  private static final String MT_SETTINGS_PAGE = MaterialThemeBundle.message("mt.settings.titles.materialTheme");
  private final MTWidget mtWidget;
  @Nullable
  private static Image myBufferedImage = null;
  private final MessageBusConnection connect = ApplicationManager.getApplication().getMessageBus().connect(this);

  MTStatusWidget() {
    mtWidget = new MTWidget();
  }

  @NonNls
  @NotNull
  @Override
  public String ID() {
    return "MTStatusBarWidget";
  }

  @Override
  public void install(@NotNull final StatusBar statusBar) {
    connect.subscribe(MTTopics.THEMES, theme -> refresh());
    connect.subscribe(MTTopics.ACCENTS, accentColor -> refresh());
    connect.subscribe(MTTopics.CONFIG, new ConfigNotifier() {
      @Override
      public void configChanged(final MTConfig mtConfig) {
        refresh();
      }
    });
  }

  @Override
  public void dispose() {
    Disposer.dispose(this);
    myBufferedImage = null;
    connect.disconnect();
  }

  private void refresh() {
    myBufferedImage = null;
    mtWidget.setVisible(true);
    mtWidget.repaint();
    mtWidget.updateUI();
    mtWidget.setToolTipText(MTConfig.getInstance().getTooltip());
  }

  @Override
  public JComponent getComponent() {
    return mtWidget;
  }

  @SuppressWarnings({"MagicNumber",
    "InnerClassTooDeeplyNested"})
  private static final class MTWidget extends JButton implements Disposable {
    private static final int DEFAULT_FONT_SIZE = JBUI.scale(11);
    private static final int STATUS_PADDING = 4;
    private static final int STATUS_HEIGHT = 16;
    private final MTConfig mtConfig;
    private final Font widgetFont = getWidgetFont();

    MTWidget() {
      mtConfig = MTConfig.getInstance();

      new BaseButtonBehavior(this, TimedDeadzone.NULL) {
        @Override
        protected void execute(final MouseEvent e) {
          ShowSettingsUtil.getInstance().showSettingsDialog(null, MT_SETTINGS_PAGE);
        }
      }
        .setActionTrigger(MouseEvent.MOUSE_PRESSED);

      setFont(widgetFont);
      putClientProperty(MTUI.Button.NO_BORDER, Boolean.TRUE);
      showGotItTooltip();
    }

    @Override
    public void dispose() {
      // do nothing
    }

    @SuppressWarnings("FeatureEnvy")
    private void showGotItTooltip() {
      final GotItTooltip gotIt = new GotItTooltip("NewFeaturesWidget",
        MaterialThemeBundle.message("gotIt.newFeatures.widget"),
        this)
        .withHeader(MaterialThemeBundle.message("gotIt.newFeatures.title"))
        .withPosition(Balloon.Position.above)
        .withLink("Show me!", () -> ShowSettingsUtil.getInstance().showSettingsDialog(null, MT_SETTINGS_PAGE));

      if (gotIt.canShow()) {
        ApplicationManager.getApplication().invokeLater(() -> gotIt.show(this, GotItTooltip.TOP_MIDDLE));
      }
    }

    private static Font getWidgetFont() {
      final GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
      final Font[] fonts = e.getAllFonts();
      for (final Font font : fonts) {
        if (Objects.equals(font.getFontName(), MTConfig.DEFAULT_FONT)) {
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

        // Accent
        g2.setColor(accentColor);
        g2.fillOval(size.width - JBUI.scale(STATUS_HEIGHT), JBUI.scale(1), accentDiameter, accentDiameter);
        g2.dispose();
      }

      StartupUiUtil.drawImage(g, myBufferedImage, 0, 0, null);
    }

    @Override
    public Dimension getPreferredSize() {
      final String themeName = mtConfig.getSelectedTheme().getThemeName();
      assert themeName != null;
      final int width = getFontMetrics(widgetFont).charsWidth(themeName.toCharArray(), 0,
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
