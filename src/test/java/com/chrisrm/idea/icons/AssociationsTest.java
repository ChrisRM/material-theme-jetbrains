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

package com.chrisrm.idea.icons;

import com.intellij.psi.PsiElement;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AssociationsTest {
  private final Associations associations = Associations.AssociationsFactory.create();

  @Test
  public void testFindIconForFiles() throws Exception {
    assertAssociationMatches("Images", "sample.png", "Images");
    assertAssociationMatches("Angular", "sample.ng.html");
    assertAssociationMatches("Angular", "sample.ng.js");
    assertAssociationMatches("Blade", "sample.blade.php");
    assertAssociationMatches("PHP", "sample.php");
    assertAssociationMatches("SASS", "style.sass");
    assertAssociationMatches("SCSS", "style.scss");
    assertAssociationMatches("HAML", "sample.haml");
    assertAssociationMatches("Less", "style.less");
    assertAssociationMatches("Git", ".gitignore");
    assertAssociationMatches("CoffeeScript", "sample.coffee");
    assertAssociationMatches("Markdown", "readme.md");
    assertAssociationMatches("Shell", ".bashrc");
    assertAssociationMatches("Shell", ".zsh");
    assertAssociationMatches("Shell", ".sh");
    assertAssociationMatches("Fish", "sample.fish");
    assertAssociationMatches("Properties", "language.properties");
    assertAssociationMatches("HTML", "index.html");
    assertAssociationMatches("HTML", "index.xhtml");
    assertAssociationMatches("HTML", "index.tpl");
    assertAssociationMatches("AppleScript", "sample.applescript");
    assertAssociationMatches("Perl", "sample.pl");
    assertAssociationMatches("YAML", "sample.yml");
    assertAssociationMatches("YAML", "sample.config");
    assertAssociationMatches("Photoshop", "awesome.psd");
    assertAssociationMatches("Illustrator", "awesome.ai");
    assertAssociationMatches("ActionScript", "sample.as");
    assertAssociationMatches("LISP", "sample.lisp");
    assertAssociationMatches("Bower", ".bower");
    assertAssociationMatches("Bower", ".bowerrc");
    assertAssociationMatches("Ruby", "sample.rb");
    assertAssociationMatches("Archive", "sample.zip");
    assertAssociationMatches("Archive", "sample.rar");
    assertAssociationMatches("Archive", "sample.tar");
    assertAssociationMatches("Archive", "sample.gz");
    assertAssociationMatches("Archive", "sample.tar.gz");
    assertAssociationMatches("PDF", "sample.pdf");
    assertAssociationMatches("SQL", "update.sql");
    assertAssociationMatches("Python", "sample.py");
    assertAssociationMatches("R", "sample.r");
    assertAssociationMatches("Haskell", "sample.hs");
    assertAssociationMatches("Jade/Pug", "sample.jade");
    assertAssociationMatches("Scala", "euler.scala");
    assertAssociationMatches("ASP", "sample.asp");
    assertAssociationMatches("ASP", "sample.cshtml");
    assertAssociationMatches("GO", "sample.go");
    assertAssociationMatches("GO", "sample.gohtml");
    assertAssociationMatches("TypeScript", "sample.ts");
    assertAssociationMatches("TypeScript", "sample.tsx");
    assertAssociationMatches("Stylus", "styles.styl");
    assertAssociationMatches("C#", "main.cs");
    assertAssociationMatches("C", "sample.c");
    assertAssociationMatches("C", "sample.h");
    assertAssociationMatches("C++", "sample.cpp");
    assertAssociationMatches("C++", "sample.hpp");
    assertAssociationMatches("CFC", "sample.cfc");
    assertAssociationMatches("CFM", "sample.cfm");
    assertAssociationMatches("CFM", "sample.cfml");
    assertAssociationMatches("Clojure", "sample.cljs");
    assertAssociationMatches("Clojure", "sample.clj");
    assertAssociationMatches("DLang", "sample.d");
    assertAssociationMatches("nginx", "sample.conf");
    assertAssociationMatches("Docker", "Dockerfile");
    assertAssociationMatches("Docker", "sample.extra");
    assertAssociationMatches("ERLang", "sample.erc");
    assertAssociationMatches("Elixir", "sample.ex");
    assertAssociationMatches("Elixir", "sample.exs");
    assertAssociationMatches("Font", "Roboto.ttf");
    assertAssociationMatches("Font", "Roboto.ttc");
    assertAssociationMatches("Font", "Roboto.pfb");
    assertAssociationMatches("Font", "Roboto.pfm");
    assertAssociationMatches("Font", "Roboto.otf");
    assertAssociationMatches("Font", "Roboto.dfont");
    assertAssociationMatches("Font", "Roboto.pfa");
    assertAssociationMatches("Font", "Roboto.afm");
    assertAssociationMatches("Hack", "sample.hh");
    assertAssociationMatches("Slim", "sample.slim");
    assertAssociationMatches("Swift", "sample.swift");
    assertAssociationMatches("TCL", "sample.tcl");
    assertAssociationMatches("TODO", "TODO");
    assertAssociationMatches("Twig", "sample.twig");
    assertAssociationMatches("Puppet", "sample.pp");
    assertAssociationMatches("Julia", "sample.jl");

    assertAssociationMatches("Mustache", "sample.hbs");
    assertAssociationMatches("Rails", "sample.erb");
    assertAssociationMatches("Behat", "sample.feature");
    assertAssociationMatches("Haxe", "sample.hx");
    assertAssociationMatches("XML", "sample.xml");
    assertAssociationMatches("XML", "sample.ini");
    assertAssociationMatches("CSS", "styles.css");

    assertAssociationMatches("Any", ".*");
  }


  @Test
  public void testFindIconForFilesCaseInSensitivie() throws Exception {
    assertAssociationMatches("Angular", "sample.NG.HTML");
    assertAssociationMatches("Blade", "sample.BLADE.php");
    assertAssociationMatches("PHP", "sample.PHp");
    assertAssociationMatches("SASS", "stYLE.SAss");
    assertAssociationMatches("SCSS", "stYLE.SCSS");
  }


  private void assertAssociationMatches(String associationName, String fileName) {
    assertAssociationMatches(associationName, fileName, "TEXT");
  }

  private void assertAssociationMatches(String associationName, String fileName, String fileType) {
    Association associationForFile =
        associations.findAssociationForFile(new TestFileInfo(fileName, fileType, null));
    assertNotNull(associationForFile);
    assertEquals(associationName, associationForFile.getName());
  }

  private class TestFileInfo implements FileInfo {
    private final String fileName;
    private final String fileType;
    private final PsiElement psiElement;

    TestFileInfo(String fileName, String fileType, PsiElement psiElement) {
      this.fileName = fileName;
      this.fileType = fileType;
      this.psiElement = psiElement;
    }

    @Override
    public String getName() {
      return fileName;
    }

    @Override
    public String getFileType() {
      return fileType;
    }

    @Override
    public PsiElement getPsiElement() {
      return psiElement;
    }
  }
}