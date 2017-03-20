package com.chrisrm.idea;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import org.jetbrains.annotations.Nullable;


public class MTConfig implements PersistentStateComponent<MTConfig> {
  @Nullable
  @Override
  public MTConfig getState() {
    return null;
  }

  @Override
  public void loadState(MTConfig state) {

  }
}
