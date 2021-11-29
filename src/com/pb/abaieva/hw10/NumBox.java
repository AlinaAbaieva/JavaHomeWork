package com.pb.abaieva.hw10;

import java.sql.Array;

public class NumBox <T extends Number> {

    private final T[] tArray;

    @SuppressWarnings("unchecked")
    public NumBox(int tArraySize) {

        // Нельзя создать массив типа T.
        // Зато можно привести любой другой массив к типу (T[]), но это небезопасно.
        // Тем не менее, в данном случае всё будет работать.

        // vehicles = new T[size];
        tArray = (T[]) new Number[tArraySize];
    }

    public void add(T num) throws ArrayIsFullException {

        for (int i = 0; i < tArray.length; i++) {

            if (tArray[i] == null) {
                tArray[i] = num;
                break;
            } else if (i == tArray.length - 1) {
                throw new ArrayIsFullException("Массив уже заполнен.");
            }

        }

    }

    public T get(int index) {
        return tArray[index];
    }

    public int length() {

        int countLength = 0;

        while (countLength < tArray.length) {

            if (tArray[countLength] != null) {
                countLength++;
            }

        }

        return countLength;

    }

    public double average() {

        double tAvg = tArray[0].doubleValue();

        for (int i = 1; i < tArray.length; i++) {
            tAvg += tArray[i].doubleValue();
        }

        tAvg = tAvg / tArray.length;

        return tAvg;

    }

    public double sum() {

        double tSum = tArray[0].doubleValue();

        for (int i = 1; i < tArray.length; i++) {
            tSum += tArray[i].doubleValue();
        }

        return tSum;
    }

    public T max() {

        int tIndex = 0;
        double tMax = tArray[0].doubleValue();

        for (int i = 1; i < tArray.length; i++) {

            double tNum = tArray[i].doubleValue();

            if (tNum > tMax) {
                tMax = tNum;
                tIndex = i;
            }

        }

        return tArray[tIndex];

    }

}
