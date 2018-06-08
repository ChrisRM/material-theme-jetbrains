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

package com.chrisrm.idea.wizard;

import com.intellij.ui.TitlePanel;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

public class MTWizardRootPanel extends JPanel {
  public MTWizardRootPanel(final JPanel myWizardPanel) {
    initComponents();
    myRootPanel.add(myWizardPanel);
  }

  private void initComponents() {
    // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
    // Generated using JFormDesigner non-commercial license
    myRootPanel = new JPanel();
    title = new TitlePanel();

    //======== myRootPanel ========
    {
      myRootPanel.setMaximumSize(new Dimension(2147483647, 600));
      myRootPanel.setLayout(new MigLayout(
          "fillx,align left top",
          // columns
          "[fill]",
          // rows
          "[55,grow]"));

      //---- title ----
      title.setText("Material Theme Wizard");
      myRootPanel.add(title, "cell 0 0,aligny top,grow 100 0");
    }
    // JFormDesigner - End of component initialization  //GEN-END:initComponents
  }

  // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
  // Generated using JFormDesigner non-commercial license
  private JPanel myRootPanel;
  private TitlePanel title;
  // JFormDesigner - End of variables declaration  //GEN-END:variables
}
