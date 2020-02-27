package com.geekbrains.geek.cloud.common;

public class DeleteMessage extends AbstractMessage {
    private String fileName;
    private String userName;

    public String getFileName() {
        return fileName;
    }

    public String getUserName() {
        return userName;
    }

    public DeleteMessage(String userName, String fileName) {
        this.fileName = fileName;
        this.userName = userName;
    }
}
