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

import com.chrisrm.idea.messages.MaterialThemeBundle;
import com.chrisrm.idea.utils.Notify;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.wm.impl.IdeBackgroundUtil;
import com.intellij.testFramework.fixtures.LightPlatformCodeInsightFixtureTestCase;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class MTChangeWallpaperActionTest extends LightPlatformCodeInsightFixtureTestCase {

  private PropertiesComponent propertiesComponent;
  private String FRAME_PROP;
  private MTChangeWallpaperAction action;

  @Override
  public void setUp() throws Exception {
    super.setUp();
    propertiesComponent = PropertiesComponent.getInstance();
    FRAME_PROP = IdeBackgroundUtil.FRAME_PROP;
    action = new MTChangeWallpaperAction();
  }

  public void testBackgroundSet() {
    assertNull(propertiesComponent.getValue(FRAME_PROP));
    myFixture.testAction(action);
    assertNotNull(propertiesComponent.getValue(FRAME_PROP));
  }

  public void testRepaintCalled() {
    final IdeBackgroundUtil mock = mock(IdeBackgroundUtil.class);
    myFixture.testAction(action);
    verify(mock).repaintAllWindows();
  }

  public void testNotifyCalled() {
    final Notify mock = spy(Notify.class);
    myFixture.testAction(action);
    verify(mock).show(myFixture.getProject(), "",
        MaterialThemeBundle.message("mt.wallpaperInstalled"),
        NotificationType.INFORMATION,
        any()
    );
  }
}
