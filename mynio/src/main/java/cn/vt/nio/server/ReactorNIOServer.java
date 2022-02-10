package cn.vt.nio.server;

import cn.hutool.core.lang.Console;
import cn.vt.nio.server.acceptor.DefaultAcceptor;
import cn.vt.nio.server.handler.DefaultIOHandler;
import cn.vt.nio.server.handler.IOHandler;
import cn.vt.nio.server.reactor.ConnectReactor;
import cn.vt.nio.server.reactor.WorkReactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author vate
 */
public class ReactorNIOServer {

    private WorkReactor workReactor;
    private ConnectReactor connectReactor;
    private int port;

    public ReactorNIOServer(int port) throws IOException {
        this.port = port;
    }

    public ReactorNIOServer start() throws IOException {

        // 创建服务监听通道
        ServerSocketChannel serverSocketChannel = SelectorProvider.provider().openServerSocketChannel();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(port));

        // conn 监听选择器
        Selector connSelector = Selector.open();
        // io 监听选择器
        Selector ioSelector = Selector.open();

        connectReactor = new ConnectReactor(new DefaultAcceptor(ioSelector), serverSocketChannel, connSelector);

        workReactor = new WorkReactor(ioSelector);

        new Thread(connectReactor).start();
        new Thread(workReactor).start();

        return this;
    }

    public static void main(String[] args) throws IOException {
        new ReactorNIOServer(7777).start();
    }
}
