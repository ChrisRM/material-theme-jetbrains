/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2017 hsz Jakub Chrzanowski <jakub@hsz.mobi>
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
 */

package com.chrisrm.idea.utils;

import com.intellij.concurrency.JobScheduler;
import com.intellij.openapi.project.DumbAwareRunnable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Wrapper for {@link JobScheduler} that runs a scheduled operation {@link #maxAttempts} times every {@link #delay}
 * milliseconds. It is possible to manually break scheduled task with calling {@link #cancel()}.
 *
 * @author Jakub Chrzanowski <jakub@hsz.mobi>
 * @since 2.0
 */
public class InterruptibleScheduledFuture implements DumbAwareRunnable {
  /** Delay between invocations. */
  private final int delay;

  /** Max limit of invocations. */
  private final int maxAttempts;

  /** Invocations counter. */
  private int attempt = 0;

  /** Task to run. */
  @NotNull
  private final Runnable task;

  /** Current scheduled feature. */
  @Nullable
  private ScheduledFuture<?> future;

  /**
   * Constructor.
   *
   * @param task runnable task
   * @param delay time to wait before next task's run
   * @param maxAttempts max amount of task's invocations
   */
  public InterruptibleScheduledFuture(@NotNull Runnable task, int delay, int maxAttempts) {
    this.task = task;
    this.delay = delay;
    this.maxAttempts = maxAttempts;
  }

  /** Main run function. */
  @Override
  public void run() {
    cancel();
    future = JobScheduler.getScheduler().scheduleWithFixedDelay(new Runnable() {
      @Override
      public void run() {
        task.run();
        if (++attempt == maxAttempts) {
          cancel();
        }
      }
    }, delay, delay, TimeUnit.MILLISECONDS);
  }

  /** Function that cancels current {@link #future}. */
  public void cancel() {
    if (future != null) {
      future.cancel(true);
      future = null;
    }
  }
}