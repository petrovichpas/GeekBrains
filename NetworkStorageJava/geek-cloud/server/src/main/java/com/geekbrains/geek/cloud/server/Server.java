package com.geekbrains.geek.cloud.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

public class Server {
    public static void main(String[] args) throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(); //Пул потоков для обработки подключений клиентов
        EventLoopGroup workerGroup = new NioEventLoopGroup(); //Пул потоков для обработки сетевых сообщений
        try{
            ServerBootstrap bootstrap = new ServerBootstrap();  //Создание настроек сервера
            bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {
                protected void initChannel(SocketChannel socketChannel) {
                    socketChannel.pipeline().addLast(
                        new ObjectDecoder(300 * 1024 * 1024, ClassResolvers.cacheDisabled(null)),
                        new ObjectEncoder(), new AuthHandler()); //декодер преобразует байты в объект, енкодер наоборот
                }
            });
            ChannelFuture future = bootstrap.bind(8189).sync(); //запуск прослушивания порта 8189 для подключения клиентов
            System.out.println("Сервер запущен.");
            future.channel().closeFuture().sync(); //ожидание завершения работы сервера
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
