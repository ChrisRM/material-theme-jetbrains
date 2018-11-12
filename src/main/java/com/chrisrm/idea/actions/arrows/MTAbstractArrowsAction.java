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

package com.chrisrm.idea.actions.arrows;

import com.chrisrm.idea.MTAnalytics;
import com.chrisrm.idea.MTConfig;
import com.chrisrm.idea.actions.MTToggleAction;
import com.chrisrm.idea.config.ui.ArrowsStyles;
import com.chrisrm.idea.ui.MTTreeUI;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.impl.ActionToolbarImpl;
import org.jetbrains.annotations.NotNull;

public abstract class MTAbstractArrowsAction extends MTToggleAction {

  private final MTConfig mtConfig = MTConfig.getInstance();

  @Override
  public final boolean isSelected(@NotNull final AnActionEvent e) {
    return mtConfig.getArrowsStyle() == getArrowsStyle();
  }

  @Override
  public final void setSelected(@NotNull final AnActionEvent e, final boolean state) {
    final ArrowsStyles arrowsStyle = getArrowsStyle();
    mtConfig.setArrowsStyle(arrowsStyle);

    MTTreeUI.resetIcons();
    ActionToolbarImpl.updateAllToolbarsImmediately();
    MTAnalytics.getInstance().trackValue(MTAnalytics.ARROWS_STYLE, arrowsStyle);
  }

  /**
   * The arrows style
   */
  protected abstract ArrowsStyles getArrowsStyle();
}
