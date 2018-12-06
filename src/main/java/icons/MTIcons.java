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

import com.chrisrm.idea.icons.DirIcon;
import com.intellij.openapi.util.IconLoader;
import org.jetbrains.annotations.NonNls;

import javax.swing.*;

@SuppressWarnings("ALL")
public final class MTIcons {

  public static final String FOLDERS_PATH = "/icons/folders";
  public static final String FOLDERS_OPEN_PATH = "/icons/foldersOpen";
  public static final Icon EXCLUDED = load("/icons/mt/modules/ExcludedTreeOpen.svg");
  public static final Icon MODULE = load("/icons/mt/modules/ModuleOpen.svg");
  public static final Icon SOURCE = load("/icons/mt/modules/sourceRootOpen.svg");
  public static final Icon TEST = load("/icons/mt/modules/testRootOpen.svg");
  public static final Icon SEARCH_WITH_HISTORY_HOVERED = load("/icons/actions/searchWithHistoryHovered.svg");
  public static final Icon SEARCH_WITH_HISTORY = load("/icons/actions/searchWithHistory.svg");
  public static final Icon SEARCH = load("/icons/actions/search.svg");
  public static final Icon CLEAR = load("/icons/actions/clear.svg");

  private MTIcons() {

  }

  public static DirIcon getFolderIcon(String iconPath) {
    return new DirIcon(IconLoader.getIcon(FOLDERS_PATH + iconPath), IconLoader.getIcon(FOLDERS_OPEN_PATH + iconPath));
  }

  private static Icon load(@NonNls final String path) {
    return IconLoader.findIcon(path);
  }


  public static final class Arrows {
    public static Icon MaterialDownSelected = load("/icons/mac/material/down_selected.svg");
    public static Icon MaterialRightSelected = load("/icons/mac/material/right_selected.svg");
    public static Icon DarculaDownSelected = load("/icons/mac/darcula/down_selected.svg");
    public static Icon DarculaRightSelected = load("/icons/mac/darcula/right_selected.svg");
    public static Icon PlusSelected = load("/icons/mac/plusminus/plus_selected.svg");
    public static Icon MinusSelected = load("/icons/mac/plusminus/minus_selected.svg");
    public static Icon DownSelected = load("/icons/mac/arrow/down_selected.svg");
    public static Icon RightSelected = load("/icons/mac/arrow/right_selected.svg");

    public static Icon MaterialDown = load("/icons/mac/material/down.svg");
    public static Icon MaterialRight = load("/icons/mac/material/right.svg");
    public static Icon DarculaDown = load("/icons/mac/darcula/down.svg");
    public static Icon DarculaRight = load("/icons/mac/darcula/right.svg");
    public static Icon Plus = load("/icons/mac/plusminus/plus.svg");
    public static Icon Minus = load("/icons/mac/plusminus/minus.svg");
    public static Icon Down = load("/icons/mac/arrow/down.svg");
    public static Icon Right = load("/icons/mac/arrow/right.svg");
  }

  public static final class Nodes2 {
    public static final Icon FolderOpen = load("/icons/nodes/folderOpen.svg");
  }

}
