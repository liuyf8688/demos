package com.liuyf.demos.java8.nio;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.Future;

/**
 * Created by tony on 2017/10/22.
 */
public class AsynchronousFileChannelDemo {

    public static void main(String[] args) throws URISyntaxException, IOException {

        // AsynchronousFileChannel
        // 读操作
        // fileChannelRead();
        // 写操作
        fileChannelWrite();
    }

    private static void fileChannelWrite() throws URISyntaxException, IOException {
        System.out.println(AsynchronousFileChannel.class.getResource("/"));

        Path path = Paths.get(AsynchronousFileChannel.class.getResource("/").toURI().resolve("output.txt"));
        if (!Files.exists(path)) {
            Files.createFile(path);
        }

        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        long position = 0;

        buffer.put("write something".getBytes());
        buffer.flip();
        /**
        Future<Integer> operation = fileChannel.write(buffer, position);

        while (!operation.isDone());
        buffer.clear();

        System.out.println("Write done");
         */
        fileChannel.write(buffer, position, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                System.out.println("bytes written: "  + result);
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {

            }
        });
    }

    private static void fileChannelRead() throws URISyntaxException, IOException {
        System.out.println(AsynchronousFileChannel.class.getResource("/"));

        Path path = Paths.get(AsynchronousFileChannel.class.getResource("/").toURI().resolve("input.txt"));

        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.READ);

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        long position = 0;

        // 没有对比就没有伤害
        /**
        Future<Integer> operation = fileChannel.read(buffer, position);

        while (! operation.isDone());

        buffer.flip();
        byte[] data = new byte[buffer.limit()];
        buffer.get(data);
        System.out.println(new String(data));
        buffer.clear();
         */

        fileChannel.read(buffer, position, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer result, ByteBuffer attachment) {

                System.out.println("result = " + result);

                attachment.flip();
                byte[] data = new byte[attachment.limit()];
                attachment.get(data);
                System.out.println(new String(data));
                attachment.clear();
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {

            }
        });
    }
}
