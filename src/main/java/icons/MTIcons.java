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

package icons;

import com.intellij.openapi.util.IconLoader;

import javax.swing.*;

public final class MTIcons {
  private static Icon load(final String path) {
    return IconLoader.findIcon(path);
  }

  public static final Icon EXCLUDED = load("/icons/mt/modules/ExcludedTreeOpen.svg");
  public static final Icon MODULE = load("/icons/mt/modules/ModuleOpen.svg");
  public static final Icon SOURCE = load("/icons/mt/modules/sourceRootOpen.svg");
  public static final Icon TEST = load("/icons/mt/modules/testRootOpen.svg");

  public static class Actions {
    public static Icon CloseHovered = load("/icons/actions/closeHovered.svg");
    public static Icon CloseNewHovered = load("/icons/actions/closeNewHovered.svg");
  }

  public static class General {
    public static Icon ExpandAllHover = load("/icons/general/expandAllHover.svg");
    public static Icon ExpandComponentHover = load("/icons/general/expandComponentHover.svg");
    public static Icon CollapseAllHover = load("/icons/general/collapseAllHover.svg");
    public static Icon CollapseComponentHover = load("/icons/general/collapseComponentHover.svg");

    public static Icon CopyHovered = load("/icons/general/copyHovered.svg");

    public static Icon GearHover = load("/icons/general/gearHover.svg");
    public static Icon GearPlainHover = load("/icons/general/gearPlainHover.svg");
    public static Icon HideDownHover = load("/icons/general/hideDownHover.svg");
    public static Icon HideDownPartHover = load("/icons/general/hideDownPartHover.svg");
    public static Icon HideLeftHover = load("/icons/general/hideLeftHover.svg");
    public static Icon HideLeftPartHover = load("/icons/general/hideLeftPartHover.svg");
    public static Icon HideRightHover = load("/icons/general/hideRightHover.svg");
    public static Icon HideRightPartHover = load("/icons/general/hideRightPartHover.svg");
    public static Icon InlineEditHovered = load("/icons/general/inline_edit_hovered.svg");

    public static Icon InspectionsError = load("/icons/general/inspectionsError.svg");
    public static Icon LocateHover = load("/icons/general/locateHover.svg");
    public static Icon Modified = load("/icons/general/modified.svg");
    public static Icon OpenDiskHover = load("/icons/general/openDiskHover.svg");
    public static Icon ProjectConfigurable = load("/icons/general/projectConfigurable.svg");

  }

  public static class Ide {
    public static Icon Rating = load("/icons/ide/rating.svg");
    public static Icon Rating1 = load("/icons/ide/rating1.svg");
    public static Icon Rating2 = load("/icons/ide/rating2.svg");
    public static Icon Rating3 = load("/icons/ide/rating3.svg");
    public static Icon Rating4 = load("/icons/ide/rating4.svg");
  }

  public static class Arrows {
    public static Icon MaterialDownSelected = load("/icons/mac/material/down_selected.svg");
    public static Icon MaterialRightSelected = load("/icons/mac/material/right_selected.svg");
    public static Icon DarculaDownSelected = load("/icons/mac/darcula/down_selected.svg");
    public static Icon DarculaRightSelected = load("/icons/mac/darcula/right_selected.svg");
    public static Icon PlusSelected = load("/icons/mac/plusminus/plus_selected.svg");
    public static Icon MinusSelected = load("/icons/mac/plusminus/minus_selected.svg");

    public static Icon MaterialDown = load("/icons/mac/material/down.svg");
    public static Icon MaterialRight = load("/icons/mac/material/right.svg");
    public static Icon DarculaDown = load("/icons/mac/darcula/down.svg");
    public static Icon DarculaRight = load("/icons/mac/darcula/right.svg");
    public static Icon Plus = load("/icons/mac/plusminus/plus.svg");
    public static Icon Minus = load("/icons/mac/plusminus/minus.svg");
  }

  public static class Nodes {
    public static Icon TreeClosed = load("/icons/nodes/TreeClosed.svg");
    public static Icon FolderClosed = load("/icons/nodes/folderClosed.svg");
    public static Icon FolderOpen = load("/icons/nodes/folderOpen.svg");
  }

  public static class Nodes2 {
    public static Icon PinToolWindow = load("/icons/nodes/pinToolWindow.svg");
    public static Icon TabPin = load("/icons/nodes/tabPin.svg");

    public static Icon Folder = load("/icons/nodes/folder.svg");
    public static Icon FolderOpen = load("/icons/nodes/folderOpen.svg");
  }

  public static class Process {
    public static Icon Step1 = load("/icons/process/step_1.svg");
    public static Icon Step2 = load("/icons/process/step_2.svg");
    public static Icon Step3 = load("/icons/process/step_3.svg");
    public static Icon Step4 = load("/icons/process/step_4.svg");
    public static Icon Step5 = load("/icons/process/step_5.svg");
    public static Icon Step6 = load("/icons/process/step_6.svg");
    public static Icon Step7 = load("/icons/process/step_7.svg");
    public static Icon Step8 = load("/icons/process/step_8.svg");
    public static Icon Step9 = load("/icons/process/step_9.svg");
    public static Icon Step10 = load("/icons/process/step_10.svg");
    public static Icon Step11 = load("/icons/process/step_11.svg");
    public static Icon Step12 = load("/icons/process/step_12.svg");
    public static Icon StepMask = load("/icons/process/step_mask.svg");
    public static Icon StepPassive = load("/icons/process/step_passive.svg");

    public static Icon ProgressPauseHover = load("/icons/process/progressPauseHover.svg");
    public static Icon ProgressPauseSmallHover = load("/icons/process/progressPauseSmallHover.svg");
    public static Icon ProgressResumeHover = load("/icons/process/progressResumeHover.svg");
    public static Icon ProgressResumeSmallHover = load("/icons/process/progressResumeSmallHover.svg");
    public static Icon StopHover = load("/icons/process/stopHovered.svg");
    public static Icon StopSmallHover = load("/icons/process/stopSmallHovered.svg");

    public static Icon BigStep1 = load("/icons/process/big/step_1.svg");
    public static Icon BigStep2 = load("/icons/process/big/step_2.svg");
    public static Icon BigStep3 = load("/icons/process/big/step_3.svg");
    public static Icon BigStep4 = load("/icons/process/big/step_4.svg");
    public static Icon BigStep5 = load("/icons/process/big/step_5.svg");
    public static Icon BigStep6 = load("/icons/process/big/step_6.svg");
    public static Icon BigStep7 = load("/icons/process/big/step_7.svg");
    public static Icon BigStep8 = load("/icons/process/big/step_8.svg");
    public static Icon BigStep9 = load("/icons/process/big/step_9.svg");
    public static Icon BigStep10 = load("/icons/process/big/step_10.svg");
    public static Icon BigStep11 = load("/icons/process/big/step_11.svg");
    public static Icon BigStep12 = load("/icons/process/big/step_12.svg");
    public static Icon BigStepMask = load("/icons/process/big/step_mask.svg");
    public static Icon BigStepPassive = load("/icons/process/big/step_passive.svg");
  }

  public static class Windows {
    public static Icon CloseHover = load("/icons/windows/closeHover.svg");
  }

  public static class AppCode {
    public static Icon Group = load("/icons/plugins/appcode/Group.svg");
  }

  public static class DataGrip {
    public static Icon ConsoleRunHover = load("/icons/plugins/datagrip/consoleRunHover.svg");

    public static Icon ObjectGroup = load("/icons/plugins/datagrip/objectGroup.svg");
    public static Icon Table = load("/icons/plugins/datagrip/table.svg");
  }

}
