package com.chrisrm.idea.tabs;

import com.chrisrm.idea.MTConfig;
import com.chrisrm.idea.MTTheme;
import com.intellij.openapi.fileEditor.impl.EditorTabColorProvider;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.JBColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

public class MTEditorTabColor implements EditorTabColorProvider {

  private static final Color COLOR_DARKER = new JBColor(new Color(33, 33, 33, 255), new Color(33, 33, 33, 255));
  private static final Color COLOR_DEFAULT = new JBColor(new Color(38, 50, 56, 255), new Color(38, 50, 56, 255));
  private static final Color COLOR_PALENIGHT = new JBColor(new Color(41, 45, 62, 255), new Color(41, 45, 62, 255));
  private static final Color COLOR_LIGHTER = new JBColor(new Color(250, 250, 250, 255), new Color(250, 250, 250, 255));

  @Nullable
  @Override
  public Color getEditorTabColor(@NotNull Project project, @NotNull VirtualFile virtualFile) {
    final MTTheme mtTheme = MTConfig.getInstance().getSelectedTheme();

    switch (mtTheme) {
      case DARKER:
        return COLOR_DARKER;
      case PALENIGHT:
        return COLOR_PALENIGHT;
      case LIGHTER:
        return COLOR_LIGHTER;
      default:
        return COLOR_DEFAULT;
    }
  }
}
