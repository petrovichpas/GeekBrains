package com.geekbrains.geek.cloud.server;

import com.geekbrains.geek.cloud.common.*;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class ServerHandler extends ChannelInboundHandlerAdapter {
    private String userName;

    ServerHandler(String userName) {
        this.userName = userName;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object message) throws Exception {
        try {
            ctx.writeAndFlush(new AuthMessage());

            if (message == null) return;

            if (message instanceof DownloadRequest) {
                DownloadRequest downloadRequest = (DownloadRequest) message;
                FileMessage fileMessage = new FileMessage(Paths.get("server/root_repository/" + downloadRequest.getUserName() + "/" + downloadRequest.getFileName()), downloadRequest.getUserName());
                ctx.writeAndFlush(fileMessage);
            }
            if (message instanceof DeleteRequest) {
                DeleteRequest deleteRequest = (DeleteRequest) message;
                Files.delete(Paths.get("server/root_repository/" + deleteRequest.getUserName() + "/"  + deleteRequest.getFileName()));
                updateServerListView(ctx);
            }
            if (message instanceof FileMessage) {
                FileMessage fileMessage = (FileMessage) message;
                if (Files.exists(Paths.get("server/root_repository/" + fileMessage.getUserName() + "/"))) {
                    Files.write(Paths.get("server/root_repository/" + fileMessage.getUserName() + "/" + fileMessage.getFileName()), fileMessage.getFile(), StandardOpenOption.CREATE);
                }else {
                    Files.createDirectories(Paths.get("server/root_repository/" + fileMessage.getUserName() + "/"));
                    Files.write(Paths.get("server/root_repository/" + fileMessage.getUserName() + "/" + fileMessage.getFileName()), fileMessage.getFile(), StandardOpenOption.CREATE);
                    updateServerListView(ctx);
                }
            }
            if (message instanceof UpdateServerMessage) {
                updateServerListView(ctx);
            }
        } finally {
            ReferenceCountUtil.release(message);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    private void updateServerListView(ChannelHandlerContext ctx) {
        try {
            ArrayList<String> serverFileList = new ArrayList<>();
            Files.list(Paths.get("server/root_repository/" + userName + "/")).map(file -> file.getFileName().toString()).forEach(serverFileList::add);
            ctx.writeAndFlush(new UpdateServerMessage(serverFileList));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
