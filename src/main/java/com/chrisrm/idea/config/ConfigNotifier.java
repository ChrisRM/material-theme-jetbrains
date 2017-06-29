package com.chrisrm.idea.config;

import com.chrisrm.idea.MTConfig;
import com.intellij.util.messages.Topic;

/**
 * Created by helio on 24/03/2017.
 */
public interface ConfigNotifier {
  /**
   * Topic for Material Theme Settings changes
   */
  Topic<ConfigNotifier> CONFIG_TOPIC = Topic.create("Material Theme Config save", ConfigNotifier.class);

  void configChanged(MTConfig mtConfig);

  class Adapter implements ConfigNotifier {

    @Override
    public void configChanged(MTConfig mtConfig) {

    }
  }
}
