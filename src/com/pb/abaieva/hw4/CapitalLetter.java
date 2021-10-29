package com.pb.abaieva.hw4;

import java.util.Scanner;

public class CapitalLetter {

    //запрашивает ввод строки
    static String requestInput() {
        Scanner scanLine = new Scanner(System.in);
        System.out.println("Введите строку: ");
        return scanLine.nextLine();
    }

    static StringBuilder upFirstLetters(String string) {
        char[] parsedString = string.toCharArray();
        String[] upperString = new String[parsedString.length];

        StringBuilder finalString = new StringBuilder();

        for (int i = 0; i < parsedString.length; i++) {
            if ((i == 0) || (parsedString[i - 1] == ' ')) {
                upperString[i] = String.valueOf((parsedString[i])).toUpperCase();
                    //если символ первый в строке или первый после пробела - переводит в строку и делает заглавным
            } else {
                upperString[i] = String.valueOf(parsedString[i]);
                    //иначе просто переводит символ в строку
            }

            finalString.append(upperString[i]);
        }

        return finalString;

    }

    public static void main(String[] args) {

        String inputString = requestInput();

        StringBuilder outputString = upFirstLetters(inputString);

        System.out.println(outputString);

    }
}
