package com.geekbrains.geek.cloud.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TempFileWriter {
    public static void writeFile(OutputStream out, InputStream in) throws IOException {
        byte[] buf = new byte[8152];
        int tmp;
        while ((tmp = in.read(buf)) != -1){
            out.write(buf, 0, tmp);
        }
    }
}
