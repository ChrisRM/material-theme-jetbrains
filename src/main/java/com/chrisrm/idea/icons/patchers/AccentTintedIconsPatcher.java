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

package com.chrisrm.idea.icons.patchers;

import com.chrisrm.idea.MTConfig;
import com.intellij.openapi.util.IconPathPatcher;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class AccentTintedIconsPatcher extends IconPathPatcher {
  private static final Map<String, String> CACHE = new HashMap<>();
  @NonNls
  private static final Map<String, String> REPLACEMENTS = new HashMap<>();

  static {
    replaceSVGs();
  }

  private MTConfig instance;

  private static synchronized void replaceSVGs() {
    REPLACEMENTS.put("/actions/closeHovered", "MTIcons.Actions.CloseHovered");
    REPLACEMENTS.put("/actions/closeNewHovered", "MTIcons.Actions.CloseNewHovered");

    REPLACEMENTS.put("/general/expandComponentHover", "MTIcons.General.ExpandComponentHover");
    REPLACEMENTS.put("/general/collapseComponentHover", "MTIcons.General.CollapseComponentHover");

    REPLACEMENTS.put("/general/copyHovered", "MTIcons.General.CopyHovered");
    REPLACEMENTS.put("/general/gearHover", "MTIcons.General.GearHover");
    REPLACEMENTS.put("/general/gearPlainHover", "MTIcons.General.GearPlainHover");
    REPLACEMENTS.put("/general/hideDownHover", "MTIcons.General.HideDownHover");
    REPLACEMENTS.put("/general/hideDownPartHover", "MTIcons.General.HideDownPartHover");
    REPLACEMENTS.put("/general/hideLeftHover", "MTIcons.General.HideLeftHover");
    REPLACEMENTS.put("/general/hideLeftPartHover", "MTIcons.General.HideLeftPartHover");
    REPLACEMENTS.put("/general/hideRightHover", "MTIcons.General.HideRightHover");
    REPLACEMENTS.put("/general/hideRightPartHover", "MTIcons.General.HideRightHover");
    //
    REPLACEMENTS.put("/general/inline_edit_hovered", "MTIcons.General.InlineEditHovered");
    REPLACEMENTS.put("/general/inspectionsError", "MTIcons.General.InspectionsError");
    REPLACEMENTS.put("/general/modified", "MTIcons.General.Modified");
    REPLACEMENTS.put("/general/openDiskHover", "MTIcons.General.OpenDiskHover");

    REPLACEMENTS.put("/ide/rating", "MTIcons.Ide.Rating");
    REPLACEMENTS.put("/ide/rating1", "MTIcons.Ide.Rating1");
    REPLACEMENTS.put("/ide/rating2", "MTIcons.Ide.Rating2");
    REPLACEMENTS.put("/ide/rating3", "MTIcons.Ide.Rating3");
    REPLACEMENTS.put("/ide/rating4", "MTIcons.Ide.Rating4");

    REPLACEMENTS.put("/mac/tree_white_down_arrow_selected", "MTIcons.Arrows.MaterialDownSelected");
    REPLACEMENTS.put("/mac/tree_white_right_arrow_selected", "MTIcons.Arrows.MaterialRightSelected");

    REPLACEMENTS.put("/mac/material/down_selected", "MTIcons.Arrows.MaterialDownSelected");
    REPLACEMENTS.put("/mac/material/right_selected", "MTIcons.Arrows.MaterialRightSelected");
    REPLACEMENTS.put("/mac/darcula/down_selected", "MTIcons.Arrows.DarculaDownSelected");
    REPLACEMENTS.put("/mac/darcula/right_selected", "MTIcons.Arrows.DarculaRightSelected");
    REPLACEMENTS.put("/mac/plusminus/plus_selected", "MTIcons.Arrows.PlusSelected");
    REPLACEMENTS.put("/mac/plusminus/minus_selected", "MTIcons.Arrows.MinusSelected");

    REPLACEMENTS.put("/nodes/pinToolWindow", "MTIcons.Nodes2.PinToolWindow");
    REPLACEMENTS.put("/nodes/tabPin", "MTIcons.Nodes2.TabPin");

    REPLACEMENTS.put("/process/step_1", "MTIcons.Process.Step1");
    REPLACEMENTS.put("/process/step_2", "MTIcons.Process.Step2");
    REPLACEMENTS.put("/process/step_3", "MTIcons.Process.Step3");
    REPLACEMENTS.put("/process/step_4", "MTIcons.Process.Step4");
    REPLACEMENTS.put("/process/step_5", "MTIcons.Process.Step5");
    REPLACEMENTS.put("/process/step_6", "MTIcons.Process.Step6");
    REPLACEMENTS.put("/process/step_7", "MTIcons.Process.Step7");
    REPLACEMENTS.put("/process/step_8", "MTIcons.Process.Step8");
    REPLACEMENTS.put("/process/step_9", "MTIcons.Process.Step9");
    REPLACEMENTS.put("/process/step_10", "MTIcons.Process.Step10");
    REPLACEMENTS.put("/process/step_11", "MTIcons.Process.Step11");
    REPLACEMENTS.put("/process/step_12", "MTIcons.Process.Step12");
    REPLACEMENTS.put("/process/step_mask", "MTIcons.Process.StepMask");
    //    REPLACEMENTS.put("/process/step_passive", "MTIcons.Process.StepMask");

    REPLACEMENTS.put("/process/big/step_1", "MTIcons.Process.BigStep1");
    REPLACEMENTS.put("/process/big/step_2", "MTIcons.Process.BigStep2");
    REPLACEMENTS.put("/process/big/step_3", "MTIcons.Process.BigStep3");
    REPLACEMENTS.put("/process/big/step_4", "MTIcons.Process.BigStep4");
    REPLACEMENTS.put("/process/big/step_5", "MTIcons.Process.BigStep5");
    REPLACEMENTS.put("/process/big/step_6", "MTIcons.Process.BigStep6");
    REPLACEMENTS.put("/process/big/step_7", "MTIcons.Process.BigStep7");
    REPLACEMENTS.put("/process/big/step_8", "MTIcons.Process.BigStep8");
    REPLACEMENTS.put("/process/big/step_9", "MTIcons.Process.BigStep9");
    REPLACEMENTS.put("/process/big/step_10", "MTIcons.Process.BigStep10");
    REPLACEMENTS.put("/process/big/step_11", "MTIcons.Process.BigStep11");
    REPLACEMENTS.put("/process/big/step_12", "MTIcons.Process.BigStep12");
    REPLACEMENTS.put("/process/big/step_mask", "MTIcons.Process.BigStepMask");
    //    REPLACEMENTS.put("/process/big/step_passive", "MTIcons.Process.BigStepMask");

    REPLACEMENTS.put("/process/progressPauseHover", "MTIcons.Process.ProgressPauseHover");
    REPLACEMENTS.put("/process/progressPauseSmallHover", "MTIcons.Process.ProgressPauseSmallHover");
    REPLACEMENTS.put("/process/progressResumeHover", "MTIcons.Process.ProgressResumeHover");
    REPLACEMENTS.put("/process/progressResumeSmallHover", "MTIcons.Process.ProgressResumeSmallHover");
    REPLACEMENTS.put("/process/stopHover", "MTIcons.Process.StopHover");
    REPLACEMENTS.put("/process/stopSmallHover", "MTIcons.Process.StopSmallHover");

    REPLACEMENTS.put("/windows/closeHover", "MTIcons.Windows.CloseHover");

    REPLACEMENTS.put("/icons/plugins/datagrip/consoleRunHover", "MTIcons.DataGrip.ConsoleRunHover");
  }

  public static void clearCache() {
    CACHE.clear();
  }

  @Nullable
  @Override
  public String patchPath(final String path, final ClassLoader classLoader) {
    //    if (getInstance() == null || !getInstance().isUseMaterialIcons()) {
    //      return null;
    //    }
    final String vPath = path.replace(".svg", "").replace(".png", "");

    if (CACHE.containsKey(vPath)) {
      return CACHE.get(vPath);
    }
    if (REPLACEMENTS.get(vPath) != null) {
      CACHE.put(vPath, REPLACEMENTS.get(vPath));
      return CACHE.get(vPath);
    }
    return null;
  }

  @Nullable
  @Override
  public ClassLoader getContextClassLoader(final String path, final ClassLoader originalClassLoader) {
    return getClass().getClassLoader();
  }

  public MTConfig getInstance() {
    if (instance == null) {
      instance = MTConfig.getInstance();
    }
    return instance;
  }
}
