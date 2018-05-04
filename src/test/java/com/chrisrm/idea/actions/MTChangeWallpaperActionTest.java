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

import com.chrisrm.idea.MTFixtureTestCase;
import com.chrisrm.idea.messages.MaterialThemeBundle;
import com.chrisrm.idea.utils.Notify;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.notification.NotificationType;
import com.intellij.openapi.wm.impl.IdeBackgroundUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.mockito.ArgumentMatchers.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({
    IdeBackgroundUtil.class,
    Notify.class})
public class MTChangeWallpaperActionTest extends MTFixtureTestCase {

  private PropertiesComponent propertiesComponent;
  private final String FRAME_PROP = IdeBackgroundUtil.FRAME_PROP;
  private MTChangeWallpaperAction action;

  @Mock
  private Notify notify;

  @Mock
  private IdeBackgroundUtil ideBackgroundUtil;

  @Override
  @Before
  public void setUp() throws Exception {
    super.setUp();
    propertiesComponent = PropertiesComponent.getInstance();
    action = new MTChangeWallpaperAction();

    PowerMockito.mockStatic(IdeBackgroundUtil.class);
    PowerMockito.mockStatic(Notify.class);
  }

  @Test
  public void testBackgroundSet() {
    assertNull(propertiesComponent.getValue(FRAME_PROP));
    myFixture.testAction(action);
    assertNotNull(propertiesComponent.getValue(FRAME_PROP));
  }

  @Test
  public void testRepaintCalled() {
    myFixture.testAction(action);
    PowerMockito.verifyStatic();
    IdeBackgroundUtil.repaintAllWindows();
  }

  @Test
  public void testNotifyCalled() {
    myFixture.testAction(action);
    PowerMockito.verifyStatic();
    Notify.show(eq(myFixture.getProject()),
        anyString(),
        eq(MaterialThemeBundle.message("mt.wallpaperInstalled")),
        eq(NotificationType.INFORMATION),
        any()
    );
  }
}
