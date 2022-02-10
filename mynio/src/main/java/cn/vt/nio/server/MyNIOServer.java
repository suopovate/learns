package cn.vt.nio.server;

import cn.hutool.core.lang.Console;
import cn.vt.nio.server.handler.DefaultIOHandler;
import cn.vt.nio.server.handler.IOHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.concurrent.*;

/**
 * @author vate
 */
public class MyNIOServer {

    private final ServerSocketChannel serverSocketChannel;

    private final Selector connSelector;
    private final ThreadPoolExecutor connSelectorExecutor;

    private final Selector ioSelector;
    private final ThreadPoolExecutor ioSelectorExecutor;

    private final IOHandler ioHandler;
    private final ThreadPoolExecutor ioExecutor;


    public MyNIOServer(int port) throws IOException {

        SelectorProvider selectorProvider = SelectorProvider.provider();

        // 创建服务监听通道
        serverSocketChannel = selectorProvider.openServerSocketChannel();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(port));

        // conn 监听选择器
        connSelector = selectorProvider.openSelector();

        // io 监听选择器
        ioSelector = selectorProvider.openSelector();

        ioHandler = new DefaultIOHandler();

        connSelectorExecutor = new ThreadPoolExecutor(
                1,
                1,
                0,
                TimeUnit.SECONDS
                , new LinkedBlockingQueue<>()
                , r -> new Thread(r, "连接选择器线程-"));

        ioSelectorExecutor = new ThreadPoolExecutor(
                1,
                1,
                0,
                TimeUnit.SECONDS
                , new LinkedBlockingQueue<>()
                , r -> new Thread(r, "IO选择器线程-"));

        ioExecutor = new ThreadPoolExecutor(
                100,
                1000,
                0,
                TimeUnit.SECONDS
                , new LinkedBlockingQueue<>()
                , r -> new Thread(r, "IO线程-"));

    }

    public MyNIOServer start() throws IOException {

        startAccept();
        startIO();

        return this;
    }

    private void startAccept() throws IOException {

        serverSocketChannel.register(connSelector, SelectionKey.OP_ACCEPT);

        Console.log("连接服务启动.");

        new Thread(() -> {
                try {
                    while (true) {
                        if (connSelector.select() <= 0){
                            continue;
                        }
                        Iterator<SelectionKey> selectionKeys = connSelector.selectedKeys().iterator();

                        while (selectionKeys.hasNext()) {
                            SelectionKey selectionKey = selectionKeys.next();
                            selectionKeys.remove();

                            if (selectionKey.isAcceptable()) {
                                Console.log("客户端连接成功.");

                                if (selectionKey.channel() instanceof ServerSocketChannel) {
                                    SocketChannel socketChannel = ((ServerSocketChannel) selectionKey.channel()).accept();
                                    if (socketChannel != null){
                                        socketChannel.configureBlocking(false).register(ioSelector, SelectionKey.OP_READ);
                                    }
                                }
                            }

                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }).start();
    }

    private void startIO() {

        Console.log("IO服务启动.");

        new Thread(() -> {
            while (true) {
                try {
                    // 该 选择器不能一直阻塞，因为连接选择器会将通道注册到本选择器
                    // 如果一直阻塞 会导致新注册的事件没办法被检测 所以设置超时 或者 selectNow
                    if (ioSelector.select(100) <= 0) {
                        continue;
                    }
                    Iterator<SelectionKey> selectionKeys = ioSelector.selectedKeys().iterator();
                    while (selectionKeys.hasNext()) {
                        SelectionKey selectionKey = selectionKeys.next();
                        if (selectionKey.isValid() && selectionKey.isReadable()) {
                            selectionKey.cancel();
                            ioExecutor.execute(() -> {
                                ioHandler.handle(((SocketChannel) selectionKey.channel()), selectionKey);
                            });
                        }
                        selectionKeys.remove();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static void main(String[] args) throws IOException {
        new MyNIOServer(7777).start();
    }
}
