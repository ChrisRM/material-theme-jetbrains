/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2018 Chris Magnussen and Elior Boukhobza
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
package com.chrisrm.idea.icons;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.IconPathPatcher;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Konstantin Bulenkov
 */
public class MTIconPathPatcher extends IconPathPatcher {
  private static final Logger LOG = Logger.getInstance("#com.chrisrm.idea.icons.MTIconPathPatcher");

  @NonNls
  private static final Map<String, String> REPLACEMENTS = new HashMap<>();

  static {
    //region Gradle
    REPLACEMENTS.put("/icons/gradle.svg", "/icons/plugins/gradle/gradle.svg");
    REPLACEMENTS.put("GradleIcons.Gradle", "/icons/plugins/gradle/gradle.svg");

    REPLACEMENTS.put("/icons/gradleFile.svg", "/icons/plugins/gradle/gradleFile.svg");
    REPLACEMENTS.put("GradleIcons.GradleFile", "/icons/plugins/gradle/gradleFile.svg");

    REPLACEMENTS.put("/icons/gradlesync.png", "AllIcons.Actions.Refresh");
    REPLACEMENTS.put("/icons/gradleSync.png", "AllIcons.Actions.Refresh");
    REPLACEMENTS.put("GradleIcons.GradleSync", "AllIcons.Actions.Refresh");

    REPLACEMENTS.put("/icons/offlineMode.svg", "/icons/plugins/gradle/offlineMode.svg");

    REPLACEMENTS.put("GradleIcons.OfflineMode", "/icons/plugins/gradle/offlineMode.svg");

    REPLACEMENTS.put("/icons/toolWindowGradle.svg", "/icons/plugins/gradle/toolWindowGradle.svg");
    REPLACEMENTS.put("GradleIcons.ToolWindowGradle", "/icons/plugins/gradle/toolWindowGradle.svg");
    //endregion

    //region Tasks
    REPLACEMENTS.put("/icons/task.png", "/icons/tasks/task.svg");
    REPLACEMENTS.put("/icons/taskGroup.png", "/icons/svg/tasks.svg");
    //endregion

    //region Maven
    REPLACEMENTS.put("/images/toolWindowMaven.png", "/icons/plugins/maven/toolWindowMaven.svg");
    REPLACEMENTS.put("/images/executeMavenGoal.png", "/icons/plugins/maven/maven.svg");
    REPLACEMENTS.put("/images/mavenPlugin.png", "/icons/plugins/maven/mavenPlugin.png");

    REPLACEMENTS.put("/images/mavenProject.png", "/icons/svg/tasks.svg");
    REPLACEMENTS.put("/images/modulesClosed.png", "/icons/nodes/moduleGroup.svg");

    REPLACEMENTS.put("/actions/offlineMode.svg", "/icons/actions/offlineMode.svg");
    REPLACEMENTS.put("/images/phase.png", "/icons/tasks/task.svg");
    REPLACEMENTS.put("/images/phasesClosed.png", "/icons/svg/tasks.svg");
    REPLACEMENTS.put("/images/pluginGoal.png", "/icons/plugins/maven/mavenPlugin.png");
    REPLACEMENTS.put("/images/profilesClosed.png", "/icons/plugins/maven/profiles.svg");
    REPLACEMENTS.put("/images/updateFolders.png", "/icons/actions/synchronizeFS.png");

    //endregion

  }

  @Nullable
  @Override
  public ClassLoader getContextClassLoader(final String path, final ClassLoader originalClassLoader) {
    return getClass().getClassLoader();
  }

  @Nullable
  @Override
  public String patchPath(final String path, final ClassLoader classLoader) {
    //    LOG.info(path);
    return REPLACEMENTS.get(path);
  }
}
