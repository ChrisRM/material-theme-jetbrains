package com.chrisrm.idea.config;

import com.chrisrm.idea.MTConfig;
import com.chrisrm.idea.config.ui.MTForm;
import com.intellij.util.messages.Topic;

/**
 * Created by helio on 24/03/2017.
 */
public interface BeforeConfigNotifier {
  /**
   * Topic for Material Theme Settings changes
   */
  Topic<BeforeConfigNotifier> BEFORE_CONFIG_TOPIC = Topic.create("Material Theme Before Config save", BeforeConfigNotifier.class);

  void beforeConfigChanged(MTConfig mtConfig, MTForm form);

  class Adapter implements BeforeConfigNotifier {

    @Override
    public void beforeConfigChanged(MTConfig mtConfig, MTForm form) {

    }
  }
}
