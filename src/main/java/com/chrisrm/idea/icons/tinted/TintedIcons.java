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

import com.intellij.openapi.components.ServiceManager;

public final class TintedIcons {

  public static final String[] TINTED_ICONS = new String[]{
      "/icons/actions/closeNewHovered.png",
      "/icons/general/expandAllHover.png",
      "/icons/general/collapseAllHover.png",
      "/icons/general/gearHover.png",
      "/icons/general/hideDownHover.png",
      "/icons/general/hideDownPartHover.png",
      "/icons/general/hideLeftHover.png",
      "/icons/general/hideLeftPartHover.png",
      "/icons/general/hideRightHover.png",
      "/icons/general/hideRightPartHover.png",
      "/icons/general/locateHover.png",
      "/icons/general/modified.png",
      "/icons/mac/tree_white_down_arrow_selected.png",
      "/icons/mac/tree_white_right_arrow_selected.png",
      "/icons/nodes/folder.png",
      "/icons/nodes/pinToolWindow.png",
      "/icons/nodes/TreeOpen.png",
      "/icons/nodes/tabPin.png",
      "/icons/process/step_1.png",
      "/icons/process/step_2.png",
      "/icons/process/step_3.png",
      "/icons/process/step_4.png",
      "/icons/process/step_5.png",
      "/icons/process/step_6.png",
      "/icons/process/step_7.png",
      "/icons/process/step_8.png",
      "/icons/process/step_9.png",
      "/icons/process/step_10.png",
      "/icons/process/step_11.png",
      "/icons/process/step_12.png",
      "/icons/process/step_passive.png",
      "/icons/process/big/step_passive.png",
  };

  public static TintedIcons getInstance() {
    return ServiceManager.getService(TintedIcons.class);
  }

  private TintedIcons() {
  }
}
