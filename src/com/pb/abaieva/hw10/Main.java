package com.pb.abaieva.hw10;

public class Main {

    public static void main(String[] args) throws ArrayIsFullException {

        NumBox<Integer> boxOfNumbers = new NumBox<>(5);

        boxOfNumbers.add(1);
        boxOfNumbers.add(2);
        boxOfNumbers.add(10);
        boxOfNumbers.add(5);
        boxOfNumbers.add(8);

        System.out.println("Элементы массива добавлены: 1, 2, 10, 5, 8.");

        // boxOfNumbers.get(2);
        System.out.println("Третий элемент массива: " +
                boxOfNumbers.get(2) + ".");

        // boxOfNumbers.length();
        System.out.println("Длина массива: " +
                boxOfNumbers.length() + ".");

        // boxOfNumbers.average();
        System.out.println("Среднее арифметическое массива: " +
                boxOfNumbers.average() + ".");

        // boxOfNumbers.sum();
        System.out.println("Сумма массива: " +
                boxOfNumbers.sum() + ".");

        // boxOfNumbers.max();
        System.out.println("Наибольший элемент массива: " +
                boxOfNumbers.max() + ".");

    }

}

/*

               _ |\_
               \` ..\
          __,.-" =__Y=
        ."        )
  _    /   ,    \/\_  _
 ((____|    )_-\ \_-`(_)
 `-----'`-----` `--`
 */