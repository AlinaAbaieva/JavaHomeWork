package com.pb.abaieva.hw3;

import java.util.Scanner;

public class Bingo {
    public static void main(String[] args) {
        // Загадывание случайного числа от 0 до 100
        System.out.println("Bingo загадывает число от 0 до 100!");
        int randNum = (int) (Math.random()*101);

        // Первая попытка
        Scanner scan = new Scanner(System.in);
        System.out.println("Пропробуйте угадать число. Ваша догадка: ");
        int guessNum = scan.nextInt();

        // Проверка числа
        int countFail = 0;

        // Число не угадано
        while (guessNum != randNum) {
            System.out.println("Не угадали :Р ");

            if (guessNum < randNum) {
                System.out.println("У Bingo число больше.");
            } else {
                System.out.println("У Bingo число меньше.");
            }

            countFail++;

            // Следующая попытка
            System.out.println("Это уже " + countFail + " попытка. Еще разок? Введите число или признайте поражение, написав \"сдаюсь\".");
            String contGame = scan.next();

            if ("сдаюсь".equals(contGame)) {
                System.out.println("Вы сдались. Число было " + randNum + ".");
                break; // Игрок сдался, выход из цикла

            } else {
                guessNum = Integer.valueOf(contGame);
            }
        }

        // Число угадано
        if (guessNum == randNum) {
            System.out.println("Правильно! Попыток потребовалось: " + (countFail + 1));
        }
    }
}
