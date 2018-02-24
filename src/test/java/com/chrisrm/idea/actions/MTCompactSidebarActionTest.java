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

package com.chrisrm.idea.actions;

import com.chrisrm.idea.MTConfig;
import com.intellij.testFramework.fixtures.LightPlatformCodeInsightFixtureTestCase;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class MTCompactSidebarActionTest extends LightPlatformCodeInsightFixtureTestCase {

  private MTCompactSidebarAction action;

  @Override
  public void setUp() throws Exception {
    super.setUp();
    action = new MTCompactSidebarAction();
  }

  public void testIsSelected() {
    assertThat(action.isSelected(null), is(MTConfig.getInstance().isCompactSidebar()));
  }

  public void testSetSelected() {
    MTConfig.getInstance().setCompactSidebar(false);
    myFixture.testAction(action);
    assertThat("Should set compact sidebar", MTConfig.getInstance().isCompactSidebar(), is(true));
  }
}
