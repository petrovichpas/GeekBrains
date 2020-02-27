package com.geekbrains.geek.cloud.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileMessage extends AbstractMessage {
    private String userName;
    private String fileName;
    private byte[] file;


    public String getUserName() {
        return userName;
    }

    public String getFileName() {
        return fileName;
    }

    public byte[] getFile() {
        return file;
    }

    public FileMessage(Path path, String userName) throws IOException {
        this.fileName = path.getFileName().toString();
        this.file = Files.readAllBytes(path);
        this.userName = userName;
    }
}
