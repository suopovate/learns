package cn.vt.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @author vate
 */
public class MyIOServer {

    private ServerSocket serverSocket;

    private Set<Socket> sockets = new HashSet<>();

    private Executor handleExecutors = Executors.newFixedThreadPool(1);

    private Executor tsExecutors = Executors.newFixedThreadPool(1);

    public void start(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        accept(serverSocket);
        handle();
    }

    private void accept(ServerSocket serverSocket) {
        Executors.newFixedThreadPool(1).execute(() -> {
            try {
                sockets.add(serverSocket.accept());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void handle() {
        handleExecutors.execute(() -> {
            while (true){
                sockets.forEach(socket -> {
                    transform(socket);
                });
            }
        });
    }

    private void transform(Socket socket) {
        tsExecutors.execute(() -> {
            if (!checkState(socket)) {
                return;
            }
            try {
                write(socket, read(socket));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private boolean checkState(Socket socket) {
        if (socket.isClosed()) {
            sockets.remove(socket);
            return false;
        }
        return true;
    }

    private String read(Socket socket) throws IOException {
        InputStream is = socket.getInputStream();
        byte[] buff = new byte[1024];
        StringBuilder stringBuilder = new StringBuilder();
        while (is.read(buff) != -1) {
            stringBuilder.append(new String(buff));
        }
        return stringBuilder.toString();
    }

    private void write(Socket socket, String readData) throws IOException {
        OutputStream os = socket.getOutputStream();
        os.write(readData.getBytes());
        os.flush();
    }

    public static void main(String[] args) throws IOException {
//        new MyNIOServer().start(1234);
        List<Integer> collect = Arrays.asList("1", "2", "3").stream().map(s -> Integer.valueOf(s)).collect(Collectors.toList());
    }
}