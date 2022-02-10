package cn.vt.nio.server.acceptor;

import cn.vt.nio.server.handler.ExecutorHandler;
import cn.vt.nio.server.handler.Handler;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author vate
 */
public class DefaultAcceptor implements Acceptor {

    private final Selector ioSelector;
    private final Handler handler = new ExecutorHandler(100);

    public DefaultAcceptor(Selector ioSelector) {
        this.ioSelector = ioSelector;
    }

    @Override
    public void accept(SelectionKey selectionKey) throws IOException {
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();

        serverSocketChannel.accept()
                .configureBlocking(false)
                .register(ioSelector, SelectionKey.OP_READ)
                .attach(handler);

        ioSelector.wakeup();
    }
}
