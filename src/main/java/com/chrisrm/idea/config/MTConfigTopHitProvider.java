package com.chrisrm.idea.config;

import com.chrisrm.idea.MTConfig;
import com.chrisrm.idea.messages.MaterialThemeBundle;
import com.intellij.ide.ui.OptionsTopHitProvider;
import com.intellij.ide.ui.PublicMethodBasedOptionDescription;
import com.intellij.ide.ui.search.BooleanOptionDescription;
import com.intellij.ide.ui.search.OptionDescription;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class MTConfigTopHitProvider extends OptionsTopHitProvider {

  private static final Collection<OptionDescription> ourOptions = Collections.unmodifiableCollection(Arrays.asList(
      option(messageIde("mt.boldtabs"), "getIsBoldTabs", "setIsBoldTabs")
  ));

  static String messageIde(String property) {
    return StringUtil.stripHtml(MaterialThemeBundle.message(property), false);
  }

  static BooleanOptionDescription option(String option, String getter, String setter) {
    return new PublicMethodBasedOptionDescription(option, "com.chrisrm.idea.config", getter, setter) {
      @Override
      public Object getInstance() {
        return MTConfig.getInstance();
      }
    };
  }

  @NotNull
  @Override
  public Collection<OptionDescription> getOptions(@Nullable Project project) {
    return ourOptions;
  }

  @Override
  public String getId() {
    return "mtconfig";
  }
}
