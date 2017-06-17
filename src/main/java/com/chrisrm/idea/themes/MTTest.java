package com.chrisrm.idea.themes;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.AbstractProjectComponent;
import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.fileEditor.FileEditorManagerEvent;
import com.intellij.openapi.fileEditor.FileEditorManagerListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ex.WindowManagerEx;
import com.intellij.openapi.wm.impl.IdeFrameImpl;
import com.intellij.ui.tabs.impl.TabLabel;
import com.intellij.util.ui.UIUtil;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.List;

public class MTTest extends AbstractProjectComponent implements ProjectComponent {
  protected MTTest(Project project) {
    super(project);
  }

  @Override
  public void projectOpened() {
    IdeFrameImpl frame = WindowManagerEx.getInstanceEx().getFrame(myProject);
    myProject.getMessageBus().connect(myProject).subscribe(FileEditorManagerListener.FILE_EDITOR_MANAGER, new FileEditorManagerListener() {
      @Override
      public void selectionChanged(@NotNull FileEditorManagerEvent event) {
        if (frame == null || frame.getFocusOwner() == null) {
          return;
        }
        List<TabLabel> componentOfType = UIUtil.findComponentsOfType(frame.getComponent(), TabLabel.class);

        for (TabLabel tabLabel : componentOfType) {
          ApplicationManager.getApplication().invokeLater(new Runnable() {
            @Override
            public void run() {
              tabLabel.setPreferredSize(new Dimension(100, 40));
            }
          });
          //          Graphics g = toolWindowHeader.getGraphics();
          //          Rectangle r = toolWindowHeader.getBounds();
          //
          //          BufferedImage image = UIUtil.createImage(g, r.width, r.height, BufferedImage.TYPE_INT_ARGB);
          //          Graphics2D g2D = image.createGraphics();
          //
          //          g2D.setColor(MTConfig.getInstance().getSelectedTheme().getBackgroundColor());
          //          g2D.fillRect(r.x, 0, 150, r.height);
          //
          //          g2D.dispose();
          //
          //          StaticPatcher.setFieldValue(toolWindowHeader, "l", image);
          //          //          ReflectionUtil.setField(ToolWindowHeader.class, toolWindowHeader, BufferedImage.class, "myActiveImage",
          // image);
        }

      }
    });
  }
}
