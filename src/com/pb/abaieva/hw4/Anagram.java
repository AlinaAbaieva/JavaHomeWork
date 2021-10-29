package com.pb.abaieva.hw4;

import java.util.Scanner;

public class Anagram {

    //запрашивает ввод строки
    static String requestInput(int lineNumber) {
        Scanner scanLine = new Scanner(System.in);
        System.out.println("Введите строку " + lineNumber + ": ");
        return scanLine.nextLine();
    }

    //переводит все буквы в нижний регистр и сотрирует их от А до Я
    static char[] sortLetters(String line) {

        char[] lineChars = line.toLowerCase().toCharArray();

        char savedChar;
        int countCheck;
        do {
            countCheck = 0;
            for (int i = 0; i < (lineChars.length - 1); i++) {
                if (lineChars[i] > lineChars[(i + 1)]) {
                    savedChar = lineChars[i];
                    lineChars[i] = lineChars[(i + 1)];
                    lineChars[(i + 1)] = savedChar;
                } else {
                    countCheck++;
                }
            }
        } while (countCheck < (lineChars.length - 1));

        return lineChars;
    }

    //складывает новую строку из букв, не учитывает другие знаки (пробелы, запятые и тд.)
    static String makeString(char[] charArray) {
            String[] stringArray = new String[charArray.length];

            StringBuilder finalString = new StringBuilder();

            for (char c : charArray) {

                if (Character.isLetter(c)) {
                    finalString.append(c);
                }
            }

            return String.valueOf(finalString);

    }

    //применяет все предыдущие методы, создавая 2 строки и сравнивая их
    static String isAnagram() {

        String line1 = makeString(sortLetters(requestInput(1)));
        String line2 = makeString(sortLetters(requestInput(2)));

        return (line1.equals(line2)) ? "Строка 2 является анаграммой строки 1." : "Строка 2 НЕ является анаграммой строки 1.";

    }

    public static void main(String[] args) {
        System.out.println("Проверка анаграммы.");
        System.out.println(isAnagram());

    }
}
