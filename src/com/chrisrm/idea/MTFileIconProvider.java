package com.chrisrm.idea;

import com.intellij.ide.IconProvider;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class MTFileIconProvider extends IconProvider {
    @Nullable
    @Override
    public Icon getIcon(@NotNull PsiElement psiElement, int i) {

        PsiFile containingFile = psiElement.getContainingFile();

        if (containingFile != null) {
            VirtualFile vFile = containingFile.getVirtualFile();

            if (vFile != null) {
                String filePath = vFile.getPath().toLowerCase();

                /**
                 * File Types
                 */
                if (filePath.endsWith(".sh") || filePath.endsWith(".zsh") || filePath.endsWith(".bashrc")) {
                    return MTIcons.FILE_SHELL;
                }
                if (filePath.endsWith(".properties")) {
                    return MTIcons.FILE_SOURCE;
                }
                if (filePath.endsWith(".html") || filePath.endsWith(".xhtml") || filePath.endsWith(".tpl")) {
                    return MTIcons.FILE_HTML;
                }
                if (filePath.endsWith(".applescript")) {
                    return MTIcons.FILE_APL;
                }
                if (filePath.endsWith(".pl")) {
                    return MTIcons.FILE_PERL;
                }
                if (filePath.endsWith(".yml") || filePath.endsWith(".config")) {
                    return MTIcons.FILE_YAML;
                }
                if (filePath.endsWith(".psd")) {
                    return MTIcons.FILE_PSD;
                }
                if (filePath.endsWith(".ai")) {
                    return MTIcons.FILE_AI;
                }
                if (filePath.endsWith(".as")) {
                    return MTIcons.FILE_AS;
                }
                if (filePath.endsWith(".lisp")) {
                    return MTIcons.FILE_LISP;
                }
                if (filePath.endsWith(".ng.html") || filePath.endsWith(".ng.js")) {
                    return MTIcons.FILE_ANGULAR;
                }
                if (filePath.endsWith(".bower") || filePath.endsWith(".bowerrc")) {
                    return MTIcons.FILE_BOWER;
                }
                if (filePath.endsWith(".rb")) {
                    return MTIcons.FILE_RUBY;
                }
                if (filePath.endsWith(".zip") || filePath.endsWith(".tar") || filePath.endsWith(".tar.gz")) {
                    return MTIcons.FILE_ARCHIVE;
                }
                if (filePath.endsWith(".pdf")) {
                    return MTIcons.FILE_PDF;
                }
                if (filePath.endsWith(".sql")) {
                    return MTIcons.FILE_SQL;
                }
                if (vFile.getFileType().toString().equals("Images")) {
                    return MTIcons.FILE_IMAGE;
                }
                if (filePath.endsWith(".py")) {
                    return MTIcons.FILE_PYTHON;
                }
                if (filePath.endsWith(".hs")) {
                    return MTIcons.FILE_HASKELL;
                }
                if (filePath.endsWith(".r")) {
                    return MTIcons.FILE_R;
                }
                if (filePath.endsWith(".jade")) {
                    return MTIcons.FILE_JADE;
                }
                if (filePath.endsWith(".scala")) {
                    return MTIcons.FILE_SCALA;
                }
                if (filePath.endsWith(".asp") || filePath.endsWith(".cshtml")) {
                    return MTIcons.FILE_ASP;
                }
                if (filePath.endsWith(".conf")) {
                    return MTIcons.FILE_APACHE;
                }
                if (filePath.endsWith(".go") || filePath.endsWith(".gohtml")) {
                    return MTIcons.FILE_GO;
                }
                if (filePath.endsWith(".ts") || filePath.endsWith(".tsx")) {
                    return MTIcons.FILE_TYPESCRIPT;
                }
                if (filePath.endsWith(".styl")) {
                    return MTIcons.FILE_STYLUS;
                }
                if (filePath.endsWith(".c") || filePath.endsWith(".h")) {
                    return MTIcons.FILE_C;
                }
                if (filePath.endsWith(".cpp") || filePath.endsWith(".hpp")) {
                    return MTIcons.FILE_CPP;
                }
                if (filePath.endsWith(".cfc")) {
                    return MTIcons.FILE_CFC;
                }
                if (filePath.endsWith(".cfm") || filePath.endsWith(".cfml")) {
                    return MTIcons.FILE_CFM;
                }
                if (filePath.endsWith(".cljs") || filePath.endsWith(".clj")) {
                    return MTIcons.FILE_CLOJURE;
                }
                if (filePath.endsWith(".cs")) {
                    return MTIcons.FILE_CHASH;
                }
                if (filePath.endsWith(".d")) {
                    return MTIcons.FILE_DLANG;
                }
                if (vFile.getName().equals("Dockerfile") || filePath.endsWith(".extra")) {
                    return MTIcons.FILE_DOCKER;
                }
                if (filePath.endsWith(".erl")) {
                    return MTIcons.FILE_ERLANG;
                }
                if (filePath.endsWith(".ex") || filePath.endsWith(".exs")) {
                    return MTIcons.FILE_ELIXIR;
                }
                if (filePath.endsWith(".fish")) {
                    return MTIcons.FILE_FISH;
                }
                if (filePath.endsWith(".ttf") ||
                        filePath.endsWith(".ttc") ||
                        filePath.endsWith(".pfb") ||
                        filePath.endsWith(".pfm") ||
                        filePath.endsWith(".otf") ||
                        filePath.endsWith(".dfont") ||
                        filePath.endsWith(".pfa") ||
                        filePath.endsWith(".afm")) {
                    return MTIcons.FILE_FONT;
                }
                if (filePath.endsWith(".hh")) {
                    return MTIcons.FILE_HACKLANG;
                }
                if (filePath.endsWith(".swift")) {
                    return MTIcons.FILE_SWIFT;
                }
                if (filePath.endsWith(".slim")) {
                    return MTIcons.FILE_SLIM;
                }
                if (filePath.endsWith(".tcl")) {
                    return MTIcons.FILE_TCL;
                }
                if (vFile.getName().equals("TODO")) {
                    return MTIcons.FILE_TODO;
                }
                if (filePath.endsWith(".twig")) {
                    return MTIcons.FILE_TWIG;
                }
                if (filePath.endsWith(".pp")) {
                    return MTIcons.FILE_PUPPET;
                }
                if (filePath.endsWith(".jl")) {
                    return MTIcons.FILE_JULIA;
                }
                if (filePath.endsWith(".java")) {
                    return MTIcons.FILE_JAVA;
                }
                if (filePath.endsWith(".hbs")) {
                    return MTIcons.FILE_MUSTACHE;
                }
                if (filePath.endsWith(".erb")) {
                    return MTIcons.FILE_RAILS;
                }
                if (filePath.endsWith(".feature")) {
                    return MTIcons.FILE_BEHAT;
                }
                if (filePath.endsWith(".hx")) {
                    return MTIcons.FILE_HAXE;
                }
                if (filePath.endsWith(".ini")) {
                    return MTIcons.FILE_XML;
                }

                /**
                 * Plugins
                 */
                if (filePath.endsWith(".blade.php")) {
                    return MTIcons.PLUGIN_BLADE;
                }
                if (filePath.endsWith(".php") || filePath.endsWith(".phtml") || filePath.endsWith(".phps")) {
                    return MTIcons.PLUGIN_PHP;
                }
                if (filePath.endsWith(".sass")) {
                    return MTIcons.PLUGIN_SASS;
                }
                if (filePath.endsWith(".scss")) {
                    return MTIcons.PLUGIN_SCSS;
                }
                if (filePath.endsWith(".haml")) {
                    return MTIcons.PLUGIN_HAML;
                }
                if (filePath.endsWith(".less")) {
                    return MTIcons.PLUGIN_LESS;
                }
                if (filePath.endsWith(".coffee")) {
                    return MTIcons.PLUGIN_COFFEESCRIPT;
                }
                if (vFile.getName().startsWith(".git")) {
                    return MTIcons.PLUGIN_GIT;
                }
                if (filePath.endsWith(".md")) {
                    return MTIcons.PLUGIN_MARKDOWN;
                }

                return null;
            }
        }

        return null;
    }
}
