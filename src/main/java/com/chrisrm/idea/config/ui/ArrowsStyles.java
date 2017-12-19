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

package com.chrisrm.idea.config.ui;

public enum ArrowsStyles {
  MATERIAL("Material", "/icons/mac/tree_white_down_arrow", "/icons/mac/tree_white_right_arrow"),
  DARCULA("Darcula", "/icons/mac/darcula/tree_white_down_arrow", "/icons/mac/darcula/tree_white_right_arrow");


  private final String type;
  private final String downPath;
  private final String rightPath;

  ArrowsStyles(final String type, final String downPath, final String rightPath) {
    this.type = type;
    this.downPath = downPath;
    this.rightPath = rightPath;
  }


  @Override
  public String toString() {
    return type;
  }

  public String getCollapsedIcon() {
    return downPath;
  }

  public String getExpandedIcon() {
    return rightPath;
  }
}
