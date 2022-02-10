package cn.vt.nio.server.handler;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

/**
 * @author vate
 */
public interface IOHandler<T> {
    /**
     * @param channel 如果为 null 则忽略
     * @param selectionKey
     */
    T read(SocketChannel channel, SelectionKey selectionKey) throws IOException;

    /**
     * @param channel 如果为 null 则忽略
     * @param selectionKey
     */
    void write(SocketChannel channel, SelectionKey selectionKey,T data) throws IOException;

    /**
     * @param channel 如果为 null 则忽略
     * @param selectionKey
     */
    void handle(SocketChannel channel, SelectionKey selectionKey);
}
