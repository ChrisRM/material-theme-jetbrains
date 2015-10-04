package com.chrisrm.idea;

import com.intellij.openapi.fileEditor.impl.EditorTabColorProvider;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.JBColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;

public class MTEditorTabColor implements EditorTabColorProvider {

    public static final Color COLOR_DEFAULT = new JBColor(new Color(38, 50, 56, 255), new Color(38, 50, 56, 255));

    @Nullable
    @Override
    public Color getEditorTabColor(@NotNull Project project, @NotNull VirtualFile virtualFile) {
        return COLOR_DEFAULT;
    }
}
