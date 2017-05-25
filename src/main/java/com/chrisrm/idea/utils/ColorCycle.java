package com.chrisrm.idea.utils;

import javax.swing.*;
import java.awt.*;

public class ColorCycle {
    private final float stepSize;
    private final Timer timer;
    private Color[] colors;
    private int index;

    private JComponent c;

    public ColorCycle(final int steps, int fps) {
        stepSize = 1f / steps;
        timer = new Timer(1000 / fps, e -> {
            index++;
            if (index > steps) {
                index = 0;
                this.stop();
            }

            if (index != 0) {
                c.setBackground(this.colors[index - 1]);
                c.repaint();
            }
        });
    }

    public void setC(JComponent c) {
        this.c = c;
    }

    public void start(Color... colors) {
        this.colors = colors;
        timer.start();
    }

    public void stop() {
        timer.stop();
    }
}
