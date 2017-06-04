package com.chrisrm.idea.utils;
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


import com.intellij.concurrency.JobScheduler;
import com.intellij.openapi.project.DumbAwareRunnable;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * Debounced runnable class that allows to run command just once in case it was triggered to often.
 *
 * @author Jakub Chrzanowski <jakub@hsz.mobi>
 * @since 2.0
 */
public abstract class Debounced implements DumbAwareRunnable {
  /**
   * Timer that depends on the given {@link #delay} value.
   */
  @Nullable
  private ScheduledFuture<?> timer;

  /**
   * Debounce time.
   */
  private final int delay;

  /**
   * Constructor.
   *
   * @param delay debounce time
   */
  public Debounced(int delay) {
    this.delay = delay;
  }

  /**
   * Wrapper run() method to invoke {@link #timer} properly.
   */
  @Override
  public final void run() {
    if (timer != null) {
      timer.cancel(false);
    }

    timer = JobScheduler.getScheduler().schedule(new DumbAwareRunnable() {
      @Override
      public void run() {
        task();
      }
    }, delay, TimeUnit.MILLISECONDS);
  }

  /**
   * Task to run in debounce way.
   */
  protected abstract void task();
}