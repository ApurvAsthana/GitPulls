package com.sunny.gitpulls.ui.splitview;

public class FilesDiffItem {
    private int lineNumberFile1;
    private String lineStringFile1;
    private int lineNumberFile2;
    private String lineStringFile2;
    private boolean isHeader;
    private String fileName;
    private boolean isChanged;

    public FilesDiffItem(int lineNumberFile1, String lineStringFile1, int lineNumberFile2, String lineStringFile2, boolean isHeader, String fileName,boolean isChanged) {
        this.lineNumberFile1 = lineNumberFile1;
        this.lineStringFile1 = lineStringFile1;
        this.lineNumberFile2 = lineNumberFile2;
        this.lineStringFile2 = lineStringFile2;
        this.isHeader = isHeader;
        this.fileName = fileName;
    }

    public FilesDiffItem(int lineNumberFile1, String lineStringFile1,boolean isChanged) {
        this.lineNumberFile1 = lineNumberFile1;
        this.lineStringFile1 = lineStringFile1;
        this.isChanged = isChanged;
    }

    public int getLineNumberFile1() {
        return lineNumberFile1;
    }

    public void setLineNumberFile1(int lineNumberFile1) {
        this.lineNumberFile1 = lineNumberFile1;
    }

    public String getLineStringFile1() {
        return lineStringFile1;
    }

    public void setLineStringFile1(String lineStringFile1) {
        this.lineStringFile1 = lineStringFile1;
    }

    public int getLineNumberFile2() {
        return lineNumberFile2;
    }

    public void setLineNumberFile2(int lineNumberFile2) {
        this.lineNumberFile2 = lineNumberFile2;
    }

    public String getLineStringFile2() {
        return lineStringFile2;
    }

    public void setLineStringFile2(String lineStringFile2) {
        this.lineStringFile2 = lineStringFile2;
    }

    public boolean isHeader() {
        return isHeader;
    }

    public void setHeader(boolean header) {
        isHeader = header;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean isChanged() {
        return isChanged;
    }

    public void setChanged(boolean changed) {
        isChanged = changed;
    }
}
