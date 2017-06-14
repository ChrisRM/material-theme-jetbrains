package com.chrisrm.idea.config.scope;

import com.intellij.openapi.extensions.Extensions;
import com.intellij.openapi.project.Project;
import com.intellij.packageDependencies.ProblemScope;
import com.intellij.psi.search.scope.NonProjectFilesScope;
import com.intellij.psi.search.scope.packageSet.CustomScopesProvider;
import com.intellij.psi.search.scope.packageSet.CustomScopesProviderEx;
import com.intellij.psi.search.scope.packageSet.NamedScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MTScopeProvider extends CustomScopesProviderEx implements CustomScopesProvider {
  private final List<NamedScope> myScopes;
  private final Project myProject;

  public MTScopeProvider(@NotNull Project project) {
    myProject = project;
    NamedScope nonProjectScope = new MTNonProjectScope();
    myScopes = Arrays.asList(nonProjectScope);
  }

  public static MTScopeProvider getInstance(Project project) {
    return Extensions.findExtension(CUSTOM_SCOPES_PROVIDER, project, MTScopeProvider.class);
  }

  @NotNull
  @Override
  public List<NamedScope> getCustomScopes() {
    return myScopes;
  }
  @NotNull
  public List<NamedScope> getAllCustomScopes() {
    final List<NamedScope> scopes = new ArrayList<>();
    for (CustomScopesProvider provider : Extensions.getExtensions(CUSTOM_SCOPES_PROVIDER, myProject)) {
      scopes.addAll(provider.getFilteredScopes());
    }
    return scopes;
  }

  @Nullable
  public NamedScope findCustomScope(String name) {
    for (NamedScope scope : getAllCustomScopes()) {
      if (name.equals(scope.getName())) {
        return scope;
      }
    }
    return null;
  }
}
