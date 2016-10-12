package com.chrisrm.idea.icons;

import com.intellij.lang.ASTNode;
import com.intellij.lang.Language;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.*;
import com.intellij.psi.impl.java.stubs.PsiClassStub;
import com.intellij.psi.impl.source.PsiClassImpl;
import com.intellij.psi.javadoc.PsiDocComment;
import com.intellij.psi.scope.PsiScopeProcessor;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.SearchScope;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.junit.Test;

import javax.swing.*;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

public class AssociationsTest {
    final Associations associations = Associations.AssociationsFactory.create();

    @Test
    public void testFindIconForFiles() throws Exception {
        assertAssociationMatches("Images", "sample.png", "Images", null);
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
        assertAssociationMatches("Jade", "sample.jade");
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
        assertAssociationMatches("HTAccess", "sample.conf");
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
        assertAssociationMatches("HackLang", "sample.hh");
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

        assertAssociationMatches("PSIClass", "jettyRunner.java", "", new MyPsiClass());

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
        assertAssociationMatches(associationName, fileName, "TEXT", null);
    }

    private void assertAssociationMatches(String associationName, String fileName, String fileType, PsiElement psiElement) {
        final Association associationForFile = associations.findAssociationForFile(new TestFileInfo(fileName, fileType, psiElement));
        assertNotNull(associationForFile);
        assertEquals(associationName, associationForFile.getName());
    }

    private static class MyPsiClass implements PsiClass {
        @Nullable
        @Override
        public String getQualifiedName() {
            return null;
        }

        @Override
        public boolean isInterface() {
            return false;
        }

        @Override
        public boolean isAnnotationType() {
            return false;
        }

        @Override
        public boolean isEnum() {
            return false;
        }

        @Nullable
        @Override
        public PsiReferenceList getExtendsList() {
            return null;
        }

        @Nullable
        @Override
        public PsiReferenceList getImplementsList() {
            return null;
        }

        @NotNull
        @Override
        public PsiClassType[] getExtendsListTypes() {
            return new PsiClassType[0];
        }

        @NotNull
        @Override
        public PsiClassType[] getImplementsListTypes() {
            return new PsiClassType[0];
        }

        @Nullable
        @Override
        public PsiClass getSuperClass() {
            return null;
        }

        @Override
        public PsiClass[] getInterfaces() {
            return new PsiClass[0];
        }

        @NotNull
        @Override
        public PsiClass[] getSupers() {
            return new PsiClass[0];
        }

        @NotNull
        @Override
        public PsiClassType[] getSuperTypes() {
            return new PsiClassType[0];
        }

        @NotNull
        @Override
        public PsiField[] getFields() {
            return new PsiField[0];
        }

        @NotNull
        @Override
        public PsiMethod[] getMethods() {
            return new PsiMethod[0];
        }

        @NotNull
        @Override
        public PsiMethod[] getConstructors() {
            return new PsiMethod[0];
        }

        @NotNull
        @Override
        public PsiClass[] getInnerClasses() {
            return new PsiClass[0];
        }

        @NotNull
        @Override
        public PsiClassInitializer[] getInitializers() {
            return new PsiClassInitializer[0];
        }

        @NotNull
        @Override
        public PsiField[] getAllFields() {
            return new PsiField[0];
        }

        @NotNull
        @Override
        public PsiMethod[] getAllMethods() {
            return new PsiMethod[0];
        }

        @NotNull
        @Override
        public PsiClass[] getAllInnerClasses() {
            return new PsiClass[0];
        }

        @Nullable
        @Override
        public PsiField findFieldByName(String name, boolean checkBases) {
            return null;
        }

        @Nullable
        @Override
        public PsiMethod findMethodBySignature(PsiMethod patternMethod, boolean checkBases) {
            return null;
        }

        @NotNull
        @Override
        public PsiMethod[] findMethodsBySignature(PsiMethod patternMethod, boolean checkBases) {
            return new PsiMethod[0];
        }

        @NotNull
        @Override
        public PsiMethod[] findMethodsByName(String name, boolean checkBases) {
            return new PsiMethod[0];
        }

        @NotNull
        @Override
        public List<Pair<PsiMethod, PsiSubstitutor>> findMethodsAndTheirSubstitutorsByName(String name, boolean checkBases) {
            return null;
        }

        @NotNull
        @Override
        public List<Pair<PsiMethod, PsiSubstitutor>> getAllMethodsAndTheirSubstitutors() {
            return null;
        }

        @Nullable
        @Override
        public PsiClass findInnerClassByName(String name, boolean checkBases) {
            return null;
        }

        @Nullable
        @Override
        public PsiElement getLBrace() {
            return null;
        }

        @Nullable
        @Override
        public PsiElement getRBrace() {
            return null;
        }

        @Nullable
        @Override
        public PsiIdentifier getNameIdentifier() {
            return null;
        }

        @Override
        public PsiElement getScope() {
            return null;
        }

        @Override
        public boolean isInheritor(PsiClass baseClass, boolean checkDeep) {
            return false;
        }

        @Override
        public boolean isInheritorDeep(PsiClass baseClass, PsiClass classToByPass) {
            return false;
        }

        @Nullable
        @Override
        public PsiClass getContainingClass() {
            return null;
        }

        @NotNull
        @Override
        public Collection<HierarchicalMethodSignature> getVisibleSignatures() {
            return null;
        }

        @Override
        public PsiElement setName(String name) throws IncorrectOperationException {
            return null;
        }

        @Nullable
        @Override
        public PsiDocComment getDocComment() {
            return null;
        }

        @Override
        public boolean isDeprecated() {
            return false;
        }

        @Override
        public boolean hasTypeParameters() {
            return false;
        }

        @Nullable
        @Override
        public PsiTypeParameterList getTypeParameterList() {
            return null;
        }

        @NotNull
        @Override
        public PsiTypeParameter[] getTypeParameters() {
            return new PsiTypeParameter[0];
        }

        @Nullable
        @Override
        public ItemPresentation getPresentation() {
            return null;
        }

        @Override
        public void navigate(boolean requestFocus) {

        }

        @Override
        public boolean canNavigate() {
            return false;
        }

        @Override
        public boolean canNavigateToSource() {
            return false;
        }

        @Nullable
        @Override
        public PsiModifierList getModifierList() {
            return null;
        }

        @Override
        public boolean hasModifierProperty(String name) {
            return false;
        }

        @Nullable
        @Override
        public String getName() {
            return null;
        }

        @NotNull
        @Override
        public Project getProject() throws PsiInvalidElementAccessException {
            return null;
        }

        @NotNull
        @Override
        public Language getLanguage() {
            return null;
        }

        @Override
        public PsiManager getManager() {
            return null;
        }

        @NotNull
        @Override
        public PsiElement[] getChildren() {
            return new PsiElement[0];
        }

        @Override
        public PsiElement getParent() {
            return null;
        }

        @Override
        public PsiElement getFirstChild() {
            return null;
        }

        @Override
        public PsiElement getLastChild() {
            return null;
        }

        @Override
        public PsiElement getNextSibling() {
            return null;
        }

        @Override
        public PsiElement getPrevSibling() {
            return null;
        }

        @Override
        public PsiFile getContainingFile() throws PsiInvalidElementAccessException {
            return null;
        }

        @Override
        public TextRange getTextRange() {
            return null;
        }

        @Override
        public int getStartOffsetInParent() {
            return 0;
        }

        @Override
        public int getTextLength() {
            return 0;
        }

        @Nullable
        @Override
        public PsiElement findElementAt(int offset) {
            return null;
        }

        @Nullable
        @Override
        public PsiReference findReferenceAt(int offset) {
            return null;
        }

        @Override
        public int getTextOffset() {
            return 0;
        }

        @Override
        public String getText() {
            return null;
        }

        @NotNull
        @Override
        public char[] textToCharArray() {
            return new char[0];
        }

        @Override
        public PsiElement getNavigationElement() {
            return null;
        }

        @Override
        public PsiElement getOriginalElement() {
            return null;
        }

        @Override
        public boolean textMatches(CharSequence text) {
            return false;
        }

        @Override
        public boolean textMatches(PsiElement element) {
            return false;
        }

        @Override
        public boolean textContains(char c) {
            return false;
        }

        @Override
        public void accept(PsiElementVisitor visitor) {

        }

        @Override
        public void acceptChildren(PsiElementVisitor visitor) {

        }

        @Override
        public PsiElement copy() {
            return null;
        }

        @Override
        public PsiElement add(PsiElement element) throws IncorrectOperationException {
            return null;
        }

        @Override
        public PsiElement addBefore(PsiElement element, PsiElement anchor) throws IncorrectOperationException {
            return null;
        }

        @Override
        public PsiElement addAfter(PsiElement element, PsiElement anchor) throws IncorrectOperationException {
            return null;
        }

        @Override
        @Deprecated
        public void checkAdd(PsiElement element) throws IncorrectOperationException {

        }

        @Override
        public PsiElement addRange(PsiElement first, PsiElement last) throws IncorrectOperationException {
            return null;
        }

        @Override
        public PsiElement addRangeBefore(PsiElement first, PsiElement last, PsiElement anchor) throws IncorrectOperationException {
            return null;
        }

        @Override
        public PsiElement addRangeAfter(PsiElement first, PsiElement last, PsiElement anchor) throws IncorrectOperationException {
            return null;
        }

        @Override
        public void delete() throws IncorrectOperationException {

        }

        @Override
        @Deprecated
        public void checkDelete() throws IncorrectOperationException {

        }

        @Override
        public void deleteChildRange(PsiElement first, PsiElement last) throws IncorrectOperationException {

        }

        @Override
        public PsiElement replace(PsiElement newElement) throws IncorrectOperationException {
            return null;
        }

        @Override
        public boolean isValid() {
            return false;
        }

        @Override
        public boolean isWritable() {
            return false;
        }

        @Nullable
        @Override
        public PsiReference getReference() {
            return null;
        }

        @NotNull
        @Override
        public PsiReference[] getReferences() {
            return new PsiReference[0];
        }

        @Nullable
        @Override
        public <T> T getCopyableUserData(Key<T> key) {
            return null;
        }

        @Override
        public <T> void putCopyableUserData(Key<T> key, T value) {

        }

        @Override
        public boolean processDeclarations(PsiScopeProcessor processor, ResolveState state, PsiElement lastParent, PsiElement place) {
            return false;
        }

        @Nullable
        @Override
        public PsiElement getContext() {
            return null;
        }

        @Override
        public boolean isPhysical() {
            return false;
        }

        @NotNull
        @Override
        public GlobalSearchScope getResolveScope() {
            return null;
        }

        @NotNull
        @Override
        public SearchScope getUseScope() {
            return null;
        }

        @Override
        public ASTNode getNode() {
            return null;
        }

        @Override
        public boolean isEquivalentTo(PsiElement another) {
            return false;
        }

        @Override
        public Icon getIcon(int flags) {
            return null;
        }

        @Nullable
        @Override
        public <T> T getUserData(Key<T> key) {
            return null;
        }

        @Override
        public <T> void putUserData(Key<T> key, T value) {

        }
    }

    private class TestFileInfo implements FileInfo {
        private final String fileName;
        private final String fileType;
        private final PsiElement psiElement;

        public TestFileInfo(String fileName, String fileType, PsiElement psiElement) {
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