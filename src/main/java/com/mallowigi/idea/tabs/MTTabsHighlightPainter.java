/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015-2021 Elior "Mallowigi" Boukhobza
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

package com.mallowigi.idea.tabs;

import com.intellij.openapi.project.Project;
import com.mallowigi.idea.MTConfig;
import com.mallowigi.idea.MTProjectConfig;
import com.mallowigi.idea.config.enums.TabHighlightPositions;
import com.mallowigi.idea.tabs.highlights.HighlightTabPainter;
import com.mallowigi.idea.utils.MTUiUtils;

import java.awt.*;

@SuppressWarnings({"FeatureEnvy",
  "StaticMethodOnlyUsedInOneClass"})
public enum MTTabsHighlightPainter {
  DEFAULT;

  static void paintHighlight(final Project project,
                             final int borderThickness,
                             final Graphics2D g2d,
                             final Rectangle rect) {
    TabHighlightPositions tabHighlightPosition = MTConfig.getInstance().getTabHighlightPosition();

    // Check if per project enabled
    final MTProjectConfig projectConfig = MTUiUtils.getProjectConfigIfEnabled(project);
    if (projectConfig != null) {
      tabHighlightPosition = projectConfig.getTabHighlightPosition();
    }

    final HighlightTabPainter tabPainter = HighlightTabPainter.getHighlightTabPainter(tabHighlightPosition);

    tabPainter.paintBottom(borderThickness, g2d, rect, rect.width);
    tabPainter.paintTop(borderThickness, g2d, rect, rect.width);
    tabPainter.paintLeft(borderThickness, g2d, rect, rect.width);
    tabPainter.paintRight(borderThickness, g2d, rect, rect.width);
  }

}
