package com.pb.abaieva.hw15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerMultiClient {

    static class Handler implements Runnable {
        private final Socket socket;

        Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String clName = br.readLine();

                SimpleDateFormat format = new SimpleDateFormat("'['HH:mm:ss']'");
                String str;
                while ((str = br.readLine()) != null) {
                    System.out.println(Thread.currentThread().getName() + " ::: получено сообщение от "
                            + clName + "\n[" + str + "]");

                    if (str.equals("выход")) {
                        System.out.println("Запрос на выход от клиента.");
                        pw.println("Выход клиента.");
                        break;
                    } else {
                        Date date = new Date(System.currentTimeMillis());
                        str = format.format(date) + " " + clName + ": " + str;
                        pw.println(str);
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

        // постоянное ожидание запроса
        while (true) {
            Socket clientSocket = servSocket.accept();
            threadPool.submit(new Handler(clientSocket));
        }


    }

}
