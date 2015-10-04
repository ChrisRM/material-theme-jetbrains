package com.chrisrm.idea.utils;

import com.intellij.openapi.vfs.VirtualFile;

public class FileAffiliator {

    private VirtualFile file;

    private String fileName;
    private String fileNameWithoutExtension;
    private String fileExtension;
    private String filePath;


    /**
     * @param   file
     */
    public FileAffiliator(VirtualFile file) {
        this.file		= file;

        this.fileName					= this.file.getName();
        this.fileNameWithoutExtension	= this.file.getNameWithoutExtension();

        this.fileExtension	= this.file.getExtension();
        if( this.fileExtension != null && this.fileExtension.length() > 0 ) {
            this.fileExtension	= this.fileExtension.toLowerCase();
        }

        this.filePath	= this.file.getPath();
    }



    /**
     * @return  VirtualFile
     */
    public VirtualFile getFile() {
        return this.file;
    }



    /**
     * @return  String
     */
    public String getFilePath() {
        return this.filePath;
    }



    /**
     * @return  String
     */
    public String getFileExtension() {
        return this.fileExtension;
    }



    /**
     * @return  String
     */
    public String getFileName() {
        return this.fileName;
    }



    /**
     * @return  String
     */
    public String getFileNameWithoutExtension() {
        return this.fileNameWithoutExtension;
    }




    /**
     * @return  Boolean
     */
    public boolean isJavaScriptFile() {
        return this.fileExtension != null && this.fileExtension.equals("js");
    }



    /**
     * @return  Boolean
     */
    public boolean isModelFile() {
        return this.isModelFile(false);
    }

    public boolean isModelFile(Boolean includeFilename) {
        if( includeFilename ) {
            return this.filePath.toLowerCase().contains(wrapInFileSeparator("model"));
        }

        return StringHelper.removeLast(this.filePath, this.fileName).contains(wrapInFileSeparator("model"));
    }



    /**
     * @return  Boolean
     */
    public boolean isViewFile() {
        return this.isViewFile(false);
    }

    public boolean isViewFile(Boolean includeFilename) {
        if( includeFilename ) {
            return this.filePath.toLowerCase().contains(wrapInFileSeparator("view"));
        }

        return StringHelper.removeLast(this.filePath, this.fileName).contains(wrapInFileSeparator("view"));
    }



    /**
     * @return  Boolean
     */
    public boolean isControllerFile() {
        return this.isControllerFile(false);
    }

    public boolean isControllerFile(Boolean includeFilename) {
        if( includeFilename ) {
            return this.filePath.toLowerCase().contains(wrapInFileSeparator("controller"));
        }

        return StringHelper.removeLast(this.filePath, this.fileName).contains(wrapInFileSeparator("controller"));
    }



    /**
     * @return  Boolean
     */
    public boolean isStoreFile() {
        return this.isStoreFile(false);
    }



    /**
     * @param   includeFilename
     * @return  Boolean
     */
    public boolean isStoreFile(Boolean includeFilename) {
        if( includeFilename ) {
            return this.filePath.toLowerCase().contains(wrapInFileSeparator("store"));
        }

        return StringHelper.removeLast(this.filePath, this.fileName).contains(wrapInFileSeparator("store"));
    }



    /**
     * @return  Boolean
     */
    public boolean isMVCSfile() {
        return 	   this.isModelFile()
                || this.isViewFile()
                || this.isControllerFile()
                || this.isStoreFile();
    }



    /**
     * @param   filename
     * @return  String
     */
    private String wrapInFileSeparator(String filename) {
        return filename;
    }
}