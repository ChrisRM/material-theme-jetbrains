/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 Chris Magnussen and Elior Boukhobza
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 *
 */

package com.chrisrm.idea.config.scope;

import com.intellij.openapi.extensions.Extensions;
import com.intellij.openapi.project.Project;
import com.intellij.psi.search.scope.packageSet.CustomScopesProvider;
import com.intellij.psi.search.scope.packageSet.CustomScopesProviderEx;
import com.intellij.psi.search.scope.packageSet.NamedScope;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class MTScopeProvider extends CustomScopesProviderEx implements CustomScopesProvider {
  private final List<NamedScope> myScopes;
  private final Project myProject;

  public MTScopeProvider(@NotNull final Project project) {
    myProject = project;
    final NamedScope nonProjectScope = new MTOceanicNonProjectScope();
    final NamedScope nonProjectScope2 = new MTDarkerNonProjectScope();
    final NamedScope nonProjectScope3 = new MTLighterNonProjectScope();
    final NamedScope nonProjectScope4 = new MTPalenightNonProjectScope();

    myScopes = Arrays.asList(nonProjectScope, nonProjectScope2, nonProjectScope3, nonProjectScope4);
  }

  public static MTScopeProvider getInstance(final Project project) {
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
    for (final CustomScopesProvider provider : Extensions.getExtensions(CUSTOM_SCOPES_PROVIDER, myProject)) {
      scopes.addAll(provider.getFilteredScopes());
    }
    return scopes;
  }

  @Nullable
  public NamedScope findCustomScope(final String name) {
    for (final NamedScope scope : getAllCustomScopes()) {
      if (name.equals(scope.getName())) {
        return scope;
      }
    }
    return null;
  }
}
