package com.pb.abaieva.hw15;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client2 {

    public static void main(String[] args) throws IOException {

        Frame frame = new Frame();
        Dimension frameWH = new Dimension(500,400);
        frame.setLayout(null);

        frame.setVisible(true);
        frame.setSize(frameWH);
        frame.setResizable(false);
        frame.setMinimumSize(frameWH);
        frame.setTitle("Клиент-сервер");
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                System.exit(0);
            }
        });

        Button bSendMsg = new Button("Отправить");
        bSendMsg.setBounds(370,340,100,30);
        frame.add(bSendMsg);

        String txt = "Начните работу с сервером.";
        TextArea msgLog = new TextArea(txt);
        msgLog.setBackground(Color.GRAY);
        msgLog.setBounds(20,50,458, 228);
        frame.add(msgLog);

        TextField textField = new TextField();
        textField.setBounds(20,308,458,30);
        frame.add(textField);

        int portNumber = 1777;
        String clName = "CLIENT-2";

        Socket socket = new Socket("127.0.0.1", portNumber);

        String txtStr = "\n" + clName + " подключен.";
        System.out.println(txtStr);
        msgLog.append(txtStr);
        new TextArea(clName + " подключен.");

        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
        pw.println(clName);

        ActionListener clickSendMsg = e -> {
            String tfText = textField.getText();
            pw.println(tfText);
            textField.setText("");
        };
        ActionListener clickEnter = e -> {
            String tfText = textField.getText();
            pw.println(tfText);
            textField.setText("");
        };

        bSendMsg.addActionListener(clickSendMsg);
        textField.addActionListener(clickEnter);

        String str;
        while ((str = br.readLine()) != null) {
            if (str.equals("Выход клиента.")) {
                msgLog.append("\n" + "Соединение с сервером закрыто.");

                br.close();
                pw.close();
                socket.close();

                break;
            }
            msgLog.append("\n" + str);
        }

    }
}
