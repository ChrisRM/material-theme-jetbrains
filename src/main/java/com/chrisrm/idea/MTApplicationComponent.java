/*
 *  The MIT License (MIT)
 *
 *  Copyright (c) 2018 Chris Magnussen and Elior Boukhobza
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 *
 */

package com.chrisrm.idea;

import com.chrisrm.idea.wizard.MTWizardDialog;
import com.chrisrm.idea.wizard.MTWizardStepsProvider;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.BaseComponent;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.ui.AppUIUtil;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;

import java.awt.*;
import java.io.InputStream;
import java.net.URL;

/**
 * Component for Material Theme plugin initializations
 */
public final class MTApplicationComponent implements BaseComponent {

  @Override
  public void initComponent() {
    // Show the wizard
    checkWizard();

    // Init analytics
    initAnalytics();

    // Install bundled fonts
    //    installFonts();
  }

  /**
   * Install Roboto fonts
   *
   * @todo fix this
   */
  private void installFonts() {
    registerFont("/fonts/RobotoMT-Black.ttf");
    registerFont("/fonts/RobotoMT-BlackItalic.ttf");
    registerFont("/fonts/RobotoMT-Bold.ttf");
    registerFont("/fonts/RobotoMT-BoldItalic.ttf");
    registerFont("/fonts/RobotoMT-Regular.ttf");
    registerFont("/fonts/RobotoMT-Italic.ttf");
    registerFont("/fonts/RobotoMT-Light.ttf");
    registerFont("/fonts/RobotoMT-LightItalic.ttf");
    registerFont("/fonts/RobotoMT-Medium.ttf");
    registerFont("/fonts/RobotoMT-MediumItalic.ttf");
    registerFont("/fonts/RobotoMT-Thin.ttf");
    registerFont("/fonts/RobotoMT-ThinItalic.ttf");

    registerFont("/fonts/NotoSans-Black.ttf");
    registerFont("/fonts/NotoSans-BlackItalic.ttf");
    registerFont("/fonts/NotoSans-Bold.ttf");
    registerFont("/fonts/NotoSans-BoldItalic.ttf");
    registerFont("/fonts/NotoSans-Regular.ttf");
    registerFont("/fonts/NotoSans-Italic.ttf");
    registerFont("/fonts/NotoSans-Light.ttf");
    registerFont("/fonts/NotoSans-LightItalic.ttf");
    registerFont("/fonts/NotoSans-Medium.ttf");
    registerFont("/fonts/NotoSans-MediumItalic.ttf");
    registerFont("/fonts/NotoSans-Thin.ttf");
    registerFont("/fonts/NotoSans-ThinItalic.ttf");
  }

  /**
   * Registers a font
   *
   * @param fontPath font path
   */
  private void registerFont(@NonNls final String fontPath) {
    final ClassLoader loader = getClass().getClassLoader();
    final URL url = loader.getResource(fontPath);
    if (url == null) {
      Logger.getInstance(getClass()).warn("Resource missing: " + fontPath);
      return;
    }

    try {
      try (final InputStream is = url.openStream()) {
        final Font font = Font.createFont(Font.TRUETYPE_FONT, is);
        GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
      }
    } catch (final Throwable t) {
      Logger.getInstance(AppUIUtil.class).warn("Cannot register font: " + url, t);
    }
  }

  /**
   * Initializes the MTAnalytics
   */
  private static void initAnalytics() {
    MTAnalytics.getInstance().identify();
    try {
      MTAnalytics.getInstance().track(MTAnalytics.CONFIG, MTConfig.getInstance().asJson());
    } catch (final JSONException e) {
      e.printStackTrace();
    }
  }

  /**
   * Display wizard for new users
   */
  private static void checkWizard() {
    final boolean isWizardShown = MTConfig.getInstance().getIsWizardShown();
    if (!isWizardShown) {
      new MTWizardDialog(new MTWizardStepsProvider()).show();
      MTConfig.getInstance().setIsWizardShown(true);
    }
  }

  /**
   * Component dispose method.
   */
  @Override
  public void disposeComponent() {
  }

  /**
   * Returns component's name.
   *
   * @return component's name
   */
  @NotNull
  @Override
  public String getComponentName() {
    return "MTApplicationComponent";
  }

  /**
   * Returns this component
   *
   * @return the MTApplicationComponent
   */
  public static MTApplicationComponent getInstance() {
    return ApplicationManager.getApplication().getComponent(MTApplicationComponent.class);
  }

}
