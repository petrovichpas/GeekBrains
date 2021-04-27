package com.geekbrains.geek.cloud.common;

import java.util.ArrayList;

public class UpdateServerMessage extends AbstractMessage {
    private ArrayList<String> serverFileList;

    public UpdateServerMessage() {
    }

    public UpdateServerMessage(ArrayList<String> serverFileList) {
        this.serverFileList = serverFileList;
    }

    public ArrayList<String> getServerFileList() {
        return serverFileList;
    }
}
