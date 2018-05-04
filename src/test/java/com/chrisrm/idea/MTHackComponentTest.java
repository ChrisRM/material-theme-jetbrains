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

package com.chrisrm.idea;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.wm.impl.IdePanePanel;
import com.intellij.util.ui.UIUtil;
import org.junit.Test;

import javax.swing.*;

import static java.lang.Integer.parseInt;

public class MTHackComponentTest extends MTFixtureTestCase {

  @Test
  public void testTabsHeightSetup() {
    final String tabsHeight = PropertiesComponent.getInstance().getValue(MTHackComponent.TABS_HEIGHT);
    assertNotNull(tabsHeight);
    assertEquals(parseInt(tabsHeight), mtConfig.getTabsHeight());
  }

  @Test
  public void testBoldTabsSetup() {
    final String boldTabs = PropertiesComponent.getInstance().getValue(MTHackComponent.BOLD_TABS);
    assertNotNull(boldTabs);
    assertEquals(Boolean.parseBoolean(boldTabs), mtConfig.getIsBoldTabs());
  }

  @Test
  public void testHackIdeBackground() {
    final IdePanePanel panel = new IdePanePanel(null);
    assertEquals(panel.getBackground(), UIManager.getColor("Viewport.background"));
  }

  @Test
  public void testHackTitleLabel() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
    final JComponent titlePanel = (JComponent) Class.forName("com.intellij.ui.TitlePanel").newInstance();
    final JLabel label = UIUtil.findComponentOfType(titlePanel, JLabel.class);

    // Change font
    assertEquals(label.getFont().getSize(), 16);

    // Alignment
    assertEquals(label.getHorizontalAlignment(), SwingConstants.LEFT);
  }

}
