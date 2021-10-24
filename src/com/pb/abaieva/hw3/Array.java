package com.pb.abaieva.hw3p2;

import java.util.Scanner;

public class Array {
    public static void main(String[] args) {
        int[] array = new int[10];
        Scanner scan = new Scanner(System.in);

        // Заполнение массива
        for (int i = 0; i < 10; i++) {
            System.out.println("Введите число массива №" + (i+1) + ":");
            array[i] = scan.nextInt();
        }

        // Сортировка массива
        int tmpElem = 0;
        int countEv = 0;
        do {
            countEv = 0;
            for (int n = 0; n < 9; n++) {
                if (array[n] > array[(n + 1)]) {
                    tmpElem = array[n];
                    array[n] = array[(n + 1)];
                    array[(n + 1)] = tmpElem;
                } else {
                    countEv++;
                }
            }
        } while (countEv < 9);

        System.out.println("Массив отсортирован: ");
        for (int m = 0; m < 10; m++) {
            System.out.println(array[m]);
        }

        // Сумма массива
        int arrSum = 0;
        for (int k = 0; k < 10; k++) {
            arrSum += array[k];
        }
        System.out.println("Сумма массива: " + arrSum);

        // Кол-во положительных элементов массива
        int countPos = 0;
        for (int n = 0; n < 10; n++) {
            if (array[n] > 0) {
                countPos++;
            }
        }
        System.out.println("Кол-во положительных элементов массива: " + countPos);
    }
}
