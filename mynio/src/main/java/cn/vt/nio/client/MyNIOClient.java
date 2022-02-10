package cn.vt.nio.client;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.Console;
import sun.nio.ch.IOUtil;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

/**
 * @author vate
 */
public class MyNIOClient {

    public static void sendMsgNIO(String data, int port) throws IOException {

        SocketChannel sc = SocketChannel.open();
        sc.configureBlocking(false);

        Selector selector = Selector.open();

        sc.register(selector, SelectionKey.OP_CONNECT);

        sc.connect(new InetSocketAddress(port));

        while (true) {

            selector.select();

            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {

                SelectionKey selectionKey = iterator.next();
                iterator.remove();

                if (selectionKey.isConnectable()) {

                    System.out.println("成功连接到服务器...");
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    if (channel.isConnectionPending()) {
                        channel.finishConnect();
                    }
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    byteBuffer.put("测试发送1".getBytes());
                    // wrap 的方法后 不需要 flip 因为 这时候 position 为 0 如果 flip 会把 limit 变为 position 导致不能读取数据,所以不需要 flip
//                    ((SocketChannel) selectionKey.channel()).write((ByteBuffer) ByteBuffer.wrap("客户端: 你好啊.".getBytes()));
                    ((SocketChannel) selectionKey.channel()).write((ByteBuffer) byteBuffer.flip());

                    selectionKey.channel().register(selector, SelectionKey.OP_READ);

                } else if (selectionKey.isReadable()) {
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

                    ((SocketChannel) selectionKey.channel()).read(byteBuffer);

                    Console.log(new String(byteBuffer.array()));

                    selectionKey.channel().close();
                    selectionKey.cancel();
                    System.exit(0);
                }
            }
        }
    }

    public static void sendMsgIO(String data, int port) throws IOException {
        Socket client = new Socket("127.0.0.1", port);

        OutputStream outToServer = client.getOutputStream();
        DataOutputStream out = new DataOutputStream(outToServer);

        out.writeUTF("Hello from " + client.getLocalSocketAddress());
        InputStream inFromServer = client.getInputStream();
        DataInputStream in = new DataInputStream(inFromServer);
        System.out.println("服务器响应： " + in.readUTF());
        client.close();
    }

    public static void main(String[] args) throws IOException {
        sendMsgNIO("发送给服务端", 7777);
    }
}
