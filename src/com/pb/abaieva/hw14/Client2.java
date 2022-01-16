package com.pb.abaieva.hw14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client2 {

    public static void main(String[] args) throws IOException {

        int portNumber = 1777;
        String clName = "CLIENT-2";

        Socket socket = new Socket("127.0.0.1", portNumber);

        System.out.println(clName + " подключен.");

        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
        pw.println(clName);

        Scanner scanner = new Scanner(System.in);
        String strToServer = (scanner.nextLine());
        pw.println(strToServer);

        String str;
        while ((str = br.readLine()) != null) {
            if (str.equals("выход")) {
                System.out.println("Запрос на выход отправлен.");

                br.close();
                pw.close();
                socket.close();

                break;
            }
            System.out.println(str);
            pw.println(scanner.nextLine());
        }

    }
}
