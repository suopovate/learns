package cn.vt.nio.server.reactor;

import cn.vt.nio.server.acceptor.Acceptor;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Set;

/**
 * @author vate
 */
public class ConnectReactor implements Reactor {

    private final ServerSocketChannel serverSocketChannel;

    private final Selector connSelector;

    private final Acceptor acceptor;

    public ConnectReactor(Acceptor acceptor, ServerSocketChannel serverSocketChannel, Selector connSelector) {
        this.acceptor = acceptor;
        this.serverSocketChannel = serverSocketChannel;
        this.connSelector = connSelector;
    }

    @Override
    public void run() {
        try {
            serverSocketChannel.register(connSelector, SelectionKey.OP_ACCEPT).attach(acceptor);

            while (true) {
                connSelector.select();
                Set<SelectionKey> selected = connSelector.selectedKeys();
                for (SelectionKey selectionKey : selected) {
                    //Reactor负责dispatch收到的事件
                    dispatch(selectionKey);
                }
                selected.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
