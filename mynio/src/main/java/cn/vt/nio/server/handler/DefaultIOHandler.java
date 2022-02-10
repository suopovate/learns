package cn.vt.nio.server.handler;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.Console;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author vate
 */
public class DefaultIOHandler implements IOHandler<String> {

    @Override
    public String read(SocketChannel channel, SelectionKey selectionKey) throws IOException {

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 4 * 100);
        StringBuilder stringBuilder = new StringBuilder();
        while (channel.read(byteBuffer) > 0) {
            byteBuffer.flip();
            byte[] data = byteBuffer.array();
            stringBuilder.append(new String(data, StandardCharsets.UTF_8));
            byteBuffer.clear();
        }
        return stringBuilder.toString();
    }

    @Override
    public void write(SocketChannel channel, SelectionKey selectionKey, String data) throws IOException {

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 4 * 100);

        byteBuffer.put(data.getBytes(StandardCharsets.UTF_8));

        byteBuffer.flip();

        channel.write(byteBuffer);
    }

    @Override
    public void handle(SocketChannel channel, SelectionKey selectionKey) {
        try {
            Console.log("开始处理客户端数据.");
            write(channel,selectionKey,read(channel, selectionKey));
            Console.log("客户端数据,处理完成.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IoUtil.close(channel);
        }
    }
}
