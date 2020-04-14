package com.mallowigi.idea.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.mallowigi.idea.messages.MaterialThemeBundle;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public final class MTAtomPluginAction extends AnAction {
  @Override
  public void actionPerformed(@NotNull final AnActionEvent e) {
    try {
      Desktop.getDesktop().browse(new URI(MaterialThemeBundle.message("atom.plugin.url")));
    } catch (final IOException | URISyntaxException ioException) {
      ioException.printStackTrace();
    }

  }

  @Override
  public boolean isDumbAware() {
    return true;
  }
}
