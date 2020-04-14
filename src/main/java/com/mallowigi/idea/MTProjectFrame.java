package com.mallowigi.idea;

import com.intellij.ide.ui.UISettings;
import com.intellij.openapi.Disposable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.IdeRootPaneNorthExtension;
import com.intellij.ui.ColorUtil;
import com.intellij.util.messages.MessageBusConnection;
import com.intellij.util.ui.*;
import com.mallowigi.idea.listeners.ConfigNotifier;
import com.mallowigi.idea.utils.MTUI;
import com.mallowigi.idea.utils.MTUiUtils;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import static com.mallowigi.idea.utils.MTUiUtils.stringToARGB;

public final class MTProjectFrame extends IdeRootPaneNorthExtension implements Disposable {
  private final Project myProject;
  private final MessageBusConnection connect;
  @Nullable
  private JComponent myWrapperPanel;
  @Nullable
  private JPanel myProjectFramePanel;

  private MTProjectFrame(@NotNull final Project project) {
    myProject = project;

    connect = myProject.getMessageBus().connect();
    connect.subscribe(ConfigNotifier.CONFIG_TOPIC, new ConfigNotifier() {
      @Override
      public void configChanged(final MTConfig mtConfig) {
        addFrame(shouldShowProjectFrame());
      }
    });
  }

  private static boolean shouldShowProjectFrame() {
    final UISettings uiSettings = UISettings.getInstance();
    return !uiSettings.getPresentationMode() && MTConfig.getInstance().isUseProjectFrame();
  }

  @Override
  public void dispose() {
    connect.disconnect();
  }

  private void addFrame(final boolean show) {
    if (myWrapperPanel == null) {
      return;
    }

    if (show && myProjectFramePanel == null) {
      myProjectFramePanel = buildPanel();
      myWrapperPanel.add(myProjectFramePanel, BorderLayout.CENTER);
    } else if (!show && myProjectFramePanel != null) {
      myWrapperPanel.remove(myProjectFramePanel);
      myProjectFramePanel = null;
    }
  }

  @NonNls
  @NotNull
  @Override
  public String getKey() {
    return "MTProjectFrame";
  }

  @NotNull
  @Override
  public JComponent getComponent() {
    if (myWrapperPanel == null) {
      myWrapperPanel = new MTProjectFrameWrapperPanel(new BorderLayout());
      addFrame(shouldShowProjectFrame());
    }
    return myWrapperPanel;
  }

  private JPanel buildPanel() {
    final MTProjectTitlePanel mtProjectTitlePanel = new MTProjectTitlePanel(myProject);
    final JPanel panel = new JPanel(new BorderLayout()) {

      @Override
      protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        mtProjectTitlePanel.paintComponent(g);
      }

      @Override
      public Insets getInsets() {
        return JBInsets.create(JBUI.scale(12), 0);
      }
    };
    panel.add(mtProjectTitlePanel, BorderLayout.CENTER);
    panel.updateUI();

    return panel;
  }

  @Override
  public void uiSettingsChanged(final UISettings settings) {
    addFrame(shouldShowProjectFrame());
  }

  @Override
  public IdeRootPaneNorthExtension copy() {
    return new MTProjectFrame(myProject);
  }

  private static final class MTProjectFrameWrapperPanel extends JPanel {
    @SuppressWarnings("HardCodedStringLiteral")
    private MTProjectFrameWrapperPanel(final LayoutManager layout) {
      super(layout);
      setName("mtRootPane");
    }

    @Override
    protected Graphics getComponentGraphics(final Graphics g) {
      return JBSwingUtilities.runGlobalCGTransform(this, super.getComponentGraphics(g));
    }
  }

  private static final class MTProjectTitlePanel extends JPanel {
    private final Project myProject;

    private MTProjectTitlePanel(final Project project) {
      super(new BorderLayout());
      myProject = project;
    }

    private static void drawCenteredString(@NotNull final Graphics2D g,
                                           @NotNull final Rectangle rect,
                                           @NotNull final String str) {
      final FontMetrics fm = g.getFontMetrics(g.getFont());
      final int textWidth = fm.stringWidth(str) - 1;
      final int x = Math.max(rect.x, rect.x + (rect.width - textWidth) / 2);
      final int y = Math.max(rect.y, rect.y + rect.height / 2 + fm.getAscent() * 2 / 5);
      final int padding = JBUI.scale(4);
      final Shape oldClip = g.getClip();

      g.setColor(MTUI.Panel.getBackground());
      g.fillRoundRect(x - padding, padding, textWidth + padding * 2, rect.height - padding * 2, padding, padding);

      g.clip(rect);
      g.setColor(MTUI.Panel.getForeground());
      g.drawString(str, x, y);
      g.setClip(oldClip);
    }

    @Override
    protected void paintComponent(final Graphics g) {
      super.paintComponent(g);

      final Graphics2D graphics = (Graphics2D) g.create();
      graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      graphics.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);

      try {
        final Rectangle headerRectangle = new Rectangle(0, 0, getWidth(), JBUI.insetsTop(24).top);
        graphics.setColor(getFrameColor());
        graphics.fill(headerRectangle);
        graphics.setFont(MTUI.Panel.getFont());

        final int controlButtonsWidth = 70;
        final String textToDraw = myProject.getName();
        final double widthToFit = ((controlButtonsWidth << 1) + GraphicsUtil.stringWidth(textToDraw, g.getFont())) - getWidth();

        // Draw the title
        if (widthToFit <= 0) {
          drawCenteredString(graphics, headerRectangle, textToDraw);
        } else {
          final FontMetrics fm = graphics.getFontMetrics();
          final Rectangle2D stringBounds = fm.getStringBounds(textToDraw, graphics);
          final Rectangle bounds =
            AffineTransform.getTranslateInstance(controlButtonsWidth,
              fm.getAscent() + (headerRectangle.height - stringBounds.getHeight()) / 2).createTransformedShape(stringBounds).getBounds();
          UIUtil.drawCenteredString(graphics, bounds, textToDraw, false, true);
        }
      } finally {
        graphics.dispose();
      }
    }

    @NotNull
    private Color getFrameColor() {
      final Color projectColor = new Color(stringToARGB(myProject.getName()));

      return ColorUtil.withAlpha(MTUiUtils.darker(projectColor, 8), 0.5);
    }
  }
}
