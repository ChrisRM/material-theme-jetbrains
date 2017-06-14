package com.chrisrm.idea.config;

import com.chrisrm.idea.messages.MaterialThemeBundle;
import com.intellij.ide.ui.search.SearchableOptionContributor;
import com.intellij.ide.ui.search.SearchableOptionProcessor;
import org.jetbrains.annotations.NotNull;

public class MTOptionContributor extends SearchableOptionContributor {
  @Override
  public void processOptions(@NotNull SearchableOptionProcessor processor) {
    MTConfigurable configurable = new MTConfigurable();
    String displayName = configurable.getDisplayName();

    // todo create iterator (and use it in MTConfigTopHitProvider)
    processor.addOptions(MaterialThemeBundle.message("mt.activetab"), null, displayName, MTConfigurable.ID,
        displayName, true);
    processor.addOptions(MaterialThemeBundle.message("mt.contrast"), null, displayName, MTConfigurable.ID,
        displayName, true);
    processor.addOptions(MaterialThemeBundle.message("mt.materialdesign"), null, displayName, MTConfigurable.ID,
        displayName, true);
    processor.addOptions(MaterialThemeBundle.message("mt.boldtabs"), null, displayName, MTConfigurable.ID,
        displayName, true);
    processor.addOptions(MaterialThemeBundle.message("mt.iswallpaperset"), null, displayName, MTConfigurable.ID,
        displayName, true);
    processor.addOptions(MaterialThemeBundle.message("MTForm.isMaterialIconsCheckbox.text"), null, displayName, MTConfigurable.ID,
        displayName, true);
    processor.addOptions(MaterialThemeBundle.message("MTForm.projectViewDecorators"), null, displayName, MTConfigurable.ID,
        displayName, true);
    processor.addOptions(MaterialThemeBundle.message("MTForm.hideFileIcons"), null, displayName, MTConfigurable.ID,
        displayName, true);
    processor.addOptions(MaterialThemeBundle.message("MTForm.isCompactSidebarCheckbox.text"), null, displayName, MTConfigurable.ID,
        displayName, true);
    processor.addOptions(MaterialThemeBundle.message("MTForm.themeStatus"), null, displayName, MTConfigurable.ID,
        displayName, true);
  }
}
