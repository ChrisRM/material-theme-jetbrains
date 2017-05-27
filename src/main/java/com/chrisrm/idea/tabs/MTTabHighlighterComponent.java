package com.chrisrm.idea.tabs;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.ApplicationComponent;
import com.intellij.openapi.fileEditor.FileEditorManagerListener;
import com.intellij.util.messages.MessageBusConnection;
import org.jetbrains.annotations.NotNull;

/**
 * Created by helio on 25/03/2017.
 */
public class MTTabHighlighterComponent implements ApplicationComponent {

  private MessageBusConnection connection;

  @Override
  public void initComponent() {
    connection = ApplicationManager.getApplication().getMessageBus().connect();
    connection.subscribe(FileEditorManagerListener.FILE_EDITOR_MANAGER, new MTFileEditorListener());

//    connection.subscribe(ConfigNotifier.CONFIG_TOPIC, new ConfigNotifier() {
//      @Override
//      public void configChanged(MTConfig mtConfig) {
//        Registry.get("ide.mac.boldEditorTabs").setValue(mtConfig.getIsBoldTabs());
//      }
//    });
  }

  @Override
  public void disposeComponent() {
    connection.disconnect();
  }

  @NotNull
  @Override
  public String getComponentName() {
    return "com.chrisrm.idea.tabs.MTTabHighlighterComponent";
  }
}
