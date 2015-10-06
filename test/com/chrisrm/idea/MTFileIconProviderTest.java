package com.chrisrm.idea;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import static com.chrisrm.idea.MTIcons.*;

public class MTFileIconProviderTest {
    private final ImageIcon testIcon = new ImageIcon();

    private MTFileIconProvider mTFileIconProvider;

    @Before
    public void setUp() throws Exception {
        mTFileIconProvider = new MTFileIconProvider();
    }

    @Test
    public void testGetIconForFont() {
        Assert.assertEquals(FILE_FONT, mTFileIconProvider.getIcon("sample.ttf", null));
    }

    @Test
    public void testGetIconForDocker() {
        Assert.assertEquals(FILE_DOCKER, mTFileIconProvider.getIcon("dockerfile", null));
    }

    @Test
    public void testGetIconForTodo() {
        Assert.assertEquals(FILE_TODO, mTFileIconProvider.getIcon("TODO", null));
    }

    @Test
    public void testGetIconForBlade() {
        Assert.assertEquals(PLUGIN_BLADE, mTFileIconProvider.getIcon("blade.php", null));
    }

    @Test
    public void testGetIconForGit() {
        Assert.assertEquals(PLUGIN_GIT, mTFileIconProvider.getIcon(".git", null));
    }

    @Test
    public void testGetIconForImage() {
        Assert.assertEquals(FILE_IMAGE, mTFileIconProvider.getIcon(null, "images"));
    }

    @Test
    public void testRegisterNewType() {
        String testType = "test";
        mTFileIconProvider.registerFileType(testIcon, testType);
        Assert.assertEquals(testIcon, mTFileIconProvider.getIcon(null, testType));
    }

    @Test
    public void testRegisterExtension() {
        String testFileName = "test.test";
        mTFileIconProvider.registerExtension(testIcon, "test");
        Assert.assertEquals(testIcon, mTFileIconProvider.getIcon(testFileName, null));
    }

    @Test
    public void testRegisterFileName() {
        String testFileName = "some.test";
        mTFileIconProvider.registerFileName(testIcon, testFileName);
        Assert.assertEquals(testIcon, mTFileIconProvider.getIcon(testFileName, null));
    }

}