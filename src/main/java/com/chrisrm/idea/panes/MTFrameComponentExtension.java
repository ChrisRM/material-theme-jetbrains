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

package com.chrisrm.idea.panes;

import com.chrisrm.idea.MTConfig;
import com.intellij.ide.ui.UISettings;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.wm.IdeRootPaneNorthExtension;
import com.intellij.ui.ColorUtil;
import com.intellij.util.ui.JBInsets;
import com.intellij.util.ui.JBSwingUtilities;
import com.intellij.util.ui.JBUI;
import com.intellij.util.ui.UIUtil;
import sun.swing.SwingUtilities2;

import javax.swing.*;
import java.awt.*;

/**
 * Component for theming the Top Frame
 */
public final class MTFrameComponentExtension extends IdeRootPaneNorthExtension {
  private JComponent myWrapperPanel;
  private Project myProject;
  private JPanel myBar;

  public MTFrameComponentExtension(final Project project) {
    myProject = project;
    Disposer.register(myProject, this);
  }

  @Override
  public String getKey() {
    return "MTFrame";
  }

  @Override
  public JComponent getComponent() {
    if (myWrapperPanel == null) {
      myWrapperPanel = new MTWrapperPanel(new BorderLayout()) {
        @Override
        public Insets getInsets() {
          return new JBInsets(0, 0, 0, 0);
        }
      };
      myWrapperPanel.add(buildMTPanel(), BorderLayout.CENTER);
    }
    return myWrapperPanel;
  }

  @Override
  public void uiSettingsChanged(final UISettings settings) {

  }

  @Override
  public IdeRootPaneNorthExtension copy() {
    return new MTFrameComponentExtension(myProject);
  }

  @Override
  public void dispose() {
    myWrapperPanel = null;
    myProject = null;
    myBar = null;
  }

  private Component buildMTPanel() {
    myBar = new JPanel(true);
    //    myWrapperPanel.putClientProperty("MTBarPanel", myBar);

    final JPanel panel = new JPanel(new BorderLayout()) {
      @Override
      public Insets getInsets() {
        return JBUI.insets(10);
      }

      @Override
      public void doLayout() {
        // align vertically
        final Rectangle r = getBounds();
        final Insets insets = getInsets();
        final int x = insets.left;

        final Component navBar = myBar;

        navBar.setBounds(x, insets.top, r.width, r.height);
      }

      @Override
      public void updateUI() {
        super.updateUI();
        setOpaque(true);

        myBar.setOpaque(false);
        myBar.setBorder(null);
      }

      @Override
      protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        final Component navBar = myBar;
        final Rectangle r = navBar.getBounds();

        final Graphics2D g2d = (Graphics2D) g.create();
        g2d.translate(r.x, r.y);

        final FontMetrics metrics = SwingUtilities2.getFontMetrics(this, g);
        g.setColor(ColorUtil.fromHex(MTConfig.getInstance().getAccentColor()));
        g.fillRect(0, 0, r.width, r.height);

        g.setColor(UIUtil.getListSelectionForeground());
        g.setFont(getFont().deriveFont(Font.BOLD));
        final String textToDraw = myProject.getName().toUpperCase();
        SwingUtilities2.drawString(this, g, textToDraw, r.x + getXOffset(), r.y + metrics.getAscent() - 2);

        g2d.dispose();
      }

      private int getXOffset() {
        return JBUI.scale(12);
      }
    };

    panel.add(myBar, BorderLayout.CENTER);
    panel.updateUI();
    return panel;
  }

  private class MTWrapperPanel extends JPanel {
    MTWrapperPanel(final LayoutManager layout) {
      super(layout);
    }

    @Override
    protected Graphics getComponentGraphics(final Graphics graphics) {
      return JBSwingUtilities.runGlobalCGTransform(this, super.getComponentGraphics(graphics));
    }
  }
}
