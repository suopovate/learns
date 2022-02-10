package cn.vt.nio.server.handler;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.Console;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author vate
 */
public class ExecutorHandler implements Handler {

    private final ThreadPoolExecutor workExecutor;

    public ExecutorHandler(int maxHandle) {
        this.workExecutor = new ThreadPoolExecutor(100, maxHandle, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
    }

    /**
     * @param selectionKey
     */
    @Override
    public void handle(SelectionKey selectionKey) {

        SocketChannel channel = (SocketChannel) selectionKey.channel();

        workExecutor.execute(() -> {
            try {
                Console.log("开始处理客户端数据.");
                write(channel, read(channel));
                Console.log("客户端数据,处理完成.");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                IoUtil.close(channel);
            }
        });

        // 防止反复触发，取消对本通道的监听
        selectionKey.cancel();
    }

    public String read(SocketChannel channel) throws IOException {

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

    public void write(SocketChannel channel, String data) throws IOException {

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 4 * 100);

        byteBuffer.put(data.getBytes(StandardCharsets.UTF_8));

        byteBuffer.flip();

        channel.write(byteBuffer);
    }

}
