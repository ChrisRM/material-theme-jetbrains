/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Chris Magnussen and Elior Boukhobza
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

package com.chrisrm.idea.icons.tinted;

import com.intellij.util.ui.UIUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;

public final class TintedIcon implements Icon {
  private final BufferedImage image;
  private final int width;
  private final int height;
  private final String path;

  public TintedIcon(final Icon original, final Color tint, final String newPath) {
    width = original.getIconWidth();
    height = original.getIconHeight();
    path = newPath;

    // Draw the icon and save a buffered image
    final BufferedImage imageIcon = UIUtil.createImage(width, height, BufferedImage.TYPE_INT_ARGB);
    final Graphics graphics = imageIcon.createGraphics();
    original.paintIcon(null, graphics, 0, 0);
    graphics.dispose();

    image = UIUtil.createImage(width, height, BufferedImage.TYPE_INT_ARGB);

    applyTint(imageIcon, tint);
  }

  /**
   * Apply tint to all pixels
   *
   * @param imageIcon
   * @param tint
   */
  private void applyTint(final BufferedImage imageIcon, final Color tint) {
    for (int y = 0; y < image.getHeight(); y++) {
      for (int x = 0; x < image.getWidth(); x++) {
        final int alpha = imageIcon.getRGB(x, y) >> 24 & 0xff;

        final Color sourceColor = new Color(imageIcon.getRGB(x, y));
        // Apply the tint to rgb components
        final Color imageColor = multiplyColor(new Color(sourceColor.getRed(), sourceColor.getGreen(), sourceColor.getBlue(), alpha), tint);

        image.setRGB(x, y, imageColor.getRGB());
      }
    }
  }

  /**
   * Multiply rgb components of two colors
   *
   * @param color1
   * @param color2
   */
  private Color multiplyColor(final Color color1, final Color color2) {
    final float[] color1Components = color1.getRGBComponents(null);
    final float[] color2Components = color2.getRGBColorComponents(null);
    final float[] newComponents = new float[3];

    for (int i = 0; i < 3; i++) {
      newComponents[i] = color1Components[i] * color2Components[i];
    }

    return new Color(newComponents[0], newComponents[1], newComponents[2], color1Components[3]);
  }

  @Override
  public void paintIcon(final Component c, final Graphics g, final int x, final int y) {
    g.drawImage(image, x, y, width, height, c);
  }

  @Override
  public int getIconWidth() {
    return this.width;
  }

  @Override
  public int getIconHeight() {
    return this.height;
  }

  public String getPath() {
    return path;
  }
}
