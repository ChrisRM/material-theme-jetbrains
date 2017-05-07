package com.chrisrm.idea.ui;

import com.intellij.openapi.wm.IdeFrame;
import com.intellij.openapi.wm.WelcomeFrameProvider;

public class MTFlatWelcomeFrameProvider implements WelcomeFrameProvider {

    @Override
    public IdeFrame createFrame() {
        return new MTFlatWelcomeFrame();
    }
}
