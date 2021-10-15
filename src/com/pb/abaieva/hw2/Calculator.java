package com.pb.abaieva.hw2;

import java.util.Scanner;

public class Calculator {
    public static void main(String[] args) {
        int operand1;
        int operand2;
        String sign;
        int result = 0;
        String divByZero = "0";
        Scanner in = new Scanner(System.in);

        System.out.print("Введите первое число: ");
        operand1 = in.nextInt();

        System.out.print("Введите второе число: ");
        operand2 = in.nextInt();

        System.out.print("Введите знак операции (+, -, *, /): ");
        sign = in.next();

        switch(sign) {
            case "+":
                result = operand1 + operand2;
                break;
            case "-":
                result = operand1 - operand2;
                break;
            case "*":
                result = operand1 * operand2;
                break;
            case "/":
                if (operand2 == 0) divByZero = "Ошибка: нельзя делить на ноль!";
                else result = operand1 / operand2;
                break;
        }
        if (divByZero == "0") {
            System.out.println("Результат операции " + sign + " между числами " +
                    operand1 + " и " + operand2 + " равен: " + result + ".");
        } else System.out.println(divByZero);
    }
}
