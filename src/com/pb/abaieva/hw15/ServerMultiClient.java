package com.pb.abaieva.hw15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ServerMultiClient {

    static class Handler implements Runnable {
        private static final AtomicInteger INDEX = new AtomicInteger(0);

        private final Socket socket;
        private final List<Handler> clientHandlers;
        private final int currentIndex;
        private final PrintWriter pw;
        private final BufferedReader br;

        public Handler(Socket socket, List<Handler> clientHandlers) {
            this.currentIndex = INDEX.incrementAndGet();
            this.socket = socket;
            this.clientHandlers = clientHandlers;

            try {
                this.pw = new PrintWriter(socket.getOutputStream(), true);
                this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }

        public int getCurrentIndex() {
            return currentIndex;
        }

        @Override
        public void run() {
            try {
                String clName = br.readLine();
                for(Handler handler: clientHandlers) {
                    handler.pw.println(clName + " подключен.");
                }

                SimpleDateFormat format = new SimpleDateFormat("'['HH:mm:ss']'");

                String str;
                while ((str = br.readLine()) != null) {
                    if (str.equals("выход")) {
                        System.out.println("Запрос на выход от " + clName);
                        for(Handler handler: clientHandlers) {
                            handler.pw.println(clName + " отключился от сервера.");
                        }
                        clientHandlers.remove(this);
                        socket.close();
                        break;
                    }

                    System.out.println(Thread.currentThread().getName() + " ::: получено сообщение от "
                            + clName + "\n[" + str + "]");

                    Date date = new Date(System.currentTimeMillis());
                    str = format.format(date) + " " + clName + ": " + str;

                    for(Handler handler: clientHandlers) {
                        handler.pw.println(str);
                    }

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int port = 1777;

        ServerSocket servSocket = new ServerSocket(port);
        System.out.println("Сервер запущен, порт: " + port);
        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        List<Handler> clients = Collections.synchronizedList(new ArrayList<>());

        // постоянное ожидание запроса
        while (true) {
            Socket clientSocket = servSocket.accept();
            Handler handler = new Handler(clientSocket, clients);
            clients.add(handler);
            threadPool.submit(handler);
        }


    }

}
