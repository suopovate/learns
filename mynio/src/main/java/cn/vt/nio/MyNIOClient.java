package cn.vt.nio;

import sun.misc.IOUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author vate
 */
public class MyNIOClient {

    private Socket socket;

    public void start(String data) throws IOException, InterruptedException {
        socket = new Socket("localhost",1234);
        while (true){
            OutputStream os = socket.getOutputStream();
            os.write(data.getBytes());
            os.flush();
            System.out.println(new String(IOUtils.readFully(socket.getInputStream(), data.getBytes().length, true)));
            Thread.sleep(1000);
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        new Thread(() -> {
            try {
                new MyNIOClient().start("测试1");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                new MyNIOClient().start("测试2");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
