package cn.vt.nio.server.reactor;

import cn.vt.nio.server.acceptor.Acceptor;
import cn.vt.nio.server.handler.Handler;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author vate
 */
public interface Reactor extends Runnable {

    default void dispatch(SelectionKey selectionKey) throws IOException {
        if (selectionKey.attachment() instanceof Acceptor) {

            ((Acceptor) selectionKey.attachment()).accept(selectionKey);

        } else if (selectionKey.attachment() instanceof Handler) {

            ((Handler) selectionKey.attachment()).handle(selectionKey);

        }
    }

}
