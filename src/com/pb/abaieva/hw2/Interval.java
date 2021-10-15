package com.pb.abaieva.hw2;

import java.util.Scanner;

public class Interval {
    public static void main(String[] args) {
        int numChosen;
        String intervalChosen;
        Scanner in = new Scanner(System.in);
        System.out.println("Определение интервала числа. Доступные интервалы: [0-14], [15-35], [36-50], [51-100].");
        System.out.println("Введите целое число: ");
        numChosen = in.nextInt();

        if ((numChosen > -1) && (numChosen < 15)) {
            intervalChosen = "Интервал числа " + numChosen + " - [0-14].";
        } else if ((numChosen > 14) && (numChosen < 36)) {
            intervalChosen = "Интервал числа " + numChosen + " - [15-35].";
        } else if ((numChosen > 35) && (numChosen < 51)) {
            intervalChosen = "Интервал числа " + numChosen + " - [36-50].";
        } else if ((numChosen > 50) && (numChosen < 101)) {
            intervalChosen = "Интервал числа " + numChosen + " - [51-100].";
        } else intervalChosen =
                "Ошибка! Число не принадлежит допустимым интервалам: [0-14], [15-35], [36-50], [51-100].";
        System.out.println(intervalChosen);
    }
}
