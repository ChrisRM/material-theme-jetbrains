package com.chrisrm.idea;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.intellij.ide.IconProvider;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.chrisrm.idea.MTIcons.*;

public class MTFileIconProvider extends IconProvider {

    private final Splitter EXTENSION_SPLITTER = Splitter.on('.').omitEmptyStrings().trimResults();

    private final Map<String, Icon> extensionToIconMap = Maps.newHashMap();
    private final Map<String, Icon> fileNameToIconMap = Maps.newHashMap();
    private final Map<String, Icon> fileTypeToIconMap = Maps.newHashMap();

    {
        registerExtension(FILE_SHELL, "sh", "zsh", "bashrc");
        registerExtension(FILE_HTML, "html", "xhtml", "tpl");
        registerExtension(FILE_ARCHIVE, "zip", "tar", "gz", "tgz");
        registerExtension(FILE_YAML, "yml", "config");
        registerExtension(FILE_SOURCE, "properties");

        registerExtension(FILE_ASP, "asp", "cshtml");
        registerExtension(FILE_GO, "go", "gohtml");
        registerExtension(FILE_TYPESCRIPT, "ts", "tsx");
        registerExtension(FILE_C, "c", "h");
        registerExtension(FILE_CPP, "cpp", "hpp");
        registerExtension(FILE_CFM, "cfm", "cfml");
        registerExtension(FILE_CLOJURE, "cljs", "clj");

        registerExtension(FILE_APL, "applescript");
        registerExtension(FILE_PERL, "pl");
        registerExtension(FILE_PSD, "psd");
        registerExtension(FILE_AI, "ai");
        registerExtension(FILE_AS, "as");
        registerExtension(FILE_LISP, "lisp");
        registerExtension(FILE_RUBY, "rb");
        registerExtension(FILE_PDF, "pdf");
        registerExtension(FILE_SQL, "sql");

        registerExtension(FILE_PYTHON, "py");
        registerExtension(FILE_HASKELL, "hs");
        registerExtension(FILE_R, "r");
        registerExtension(FILE_JADE, "jade");
        registerExtension(FILE_SCALA, "scala");
        registerExtension(FILE_APACHE, "conf");
        registerExtension(FILE_STYLUS, "styl");
        registerExtension(FILE_CFC, "cfc");
        registerExtension(FILE_CHASH, "cs");
        registerExtension(FILE_DLANG, "d");

        registerExtension(FILE_TWIG, "twig");
        registerExtension(FILE_PUPPET, "pp");
        registerExtension(FILE_JULIA, "jl");
        registerExtension(FILE_JAVA, "java");
        registerExtension(FILE_MUSTACHE, "hbs");
        registerExtension(FILE_RAILS, "erb");
        registerExtension(FILE_BEHAT, "feature");
        registerExtension(FILE_HAXE, "hx");
        registerExtension(FILE_XML, "ini");

        registerExtension(FILE_FONT, "ttf", "ttc", "pfb", "pfm", "otf", "dfont", "pfa", "afm");

        registerExtension(FILE_ELIXIR, "ex", "exs");

        registerExtension(FILE_ERLANG, "erl");
        registerExtension(FILE_FISH, "fish");
        registerExtension(FILE_HACKLANG, "hh");
        registerExtension(FILE_SWIFT, "swift");
        registerExtension(FILE_SLIM, "slim");
        registerExtension(FILE_TCL, "tcl");

        registerExtension(PLUGIN_SASS, "sass");
        registerExtension(PLUGIN_SCSS, "scss");
        registerExtension(PLUGIN_HAML, "haml");
        registerExtension(PLUGIN_LESS, "less");
        registerExtension(PLUGIN_COFFEESCRIPT, "coffee");
        registerExtension(PLUGIN_PHP, "php", "phtml", "phps");

        registerExtension(PLUGIN_MARKDOWN, "md");

        registerExtension(FILE_BOWER, "bower", "bowerrc");

        registerExtension(FILE_ANGULAR, "ng.html", "ng.js");

        registerFileName(FILE_DOCKER, "dockerfile", "extra");
        registerFileName(FILE_TODO, "TODO");

        registerFileName(PLUGIN_BLADE, "blade.php");
        registerFileName(PLUGIN_GIT, ".git");


        registerFileType(FILE_IMAGE, "images");
    }

    public void registerFileType(Icon icon, String... fileTypes) {
        for (String fileType : fileTypes) {
            fileTypeToIconMap.put(fileType, icon);
        }
    }

    public void registerFileName(Icon icon, String... fileNames) {
        for (String fileName : fileNames) {
            fileNameToIconMap.put(fileName, icon);
        }
    }

    public void registerExtension(Icon icon, String... extensions) {
        for (String extension : extensions) {
            extensionToIconMap.put(extension, icon);
        }
    }

    @Nullable
    @Override
    public Icon getIcon(@NotNull PsiElement psiElement, int i) {
        PsiFile containingFile = psiElement.getContainingFile();
        Icon result = null;

        if (containingFile != null) {
            VirtualFile vFile = containingFile.getVirtualFile();
            if (vFile != null) {
                result = getIcon(vFile.getName(), Objects.toString(vFile.getFileType()));
            }
        }
        return result;
    }

    public Icon getIcon(String fileName, String fileType) {
        fileName = Strings.nullToEmpty(fileName);
        fileType = Strings.nullToEmpty(fileType);
        Icon extensionIcon = getIconFromExtension(fileName);
        Icon fileTypeIcon = getFileTypeIcon(fileType);
        Icon fileNameIcon = getFileNameIcon(fileName);
        return firstNonNull(fileNameIcon, fileTypeIcon, extensionIcon);
    }

    private Icon firstNonNull(Icon fileNameIcon, Icon fileTypeIcon, Icon extensionIcon) {
        Icon result = fileNameIcon;

        if (result == null && fileTypeIcon != null) {
            result = fileTypeIcon;
        }

        if (result == null && extensionIcon != null) {
            result = extensionIcon;
        }

        return result;
    }

    @Nullable
    private Icon getFileNameIcon(String fileName) {
        return fileNameToIconMap.get(fileName);
    }

    @Nullable
    private Icon getFileTypeIcon(String fileType) {
        return fileTypeToIconMap.get(fileType.toLowerCase());
    }

    @Nullable
    private Icon getIconFromExtension(String fileName) {
        List<String> fileNameSegments = Lists.reverse(Lists.newArrayList(EXTENSION_SPLITTER.split(fileName)));
        Icon result = null;
        for (String fileNameSegment : fileNameSegments) {
            result = extensionToIconMap.get(fileNameSegment.toLowerCase());
            if (result != null)
                break;
        }
        return result;
    }
}