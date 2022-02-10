package cn.vt.nio.server.handler;

import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

/**
 * @author vate
 */
public interface Handler {
    /**
     * @param selectionKey
     */
    void handle(SelectionKey selectionKey);
}
