package com.pb.abaieva.hw13;


import java.util.ArrayList;
import java.util.List;

public class Milk {

    // класс - ведро молока, которое может быть пустым / полным, имеет какое-то кол-во литров
    public static class MilkBucket {

        private boolean isFull;
        private int liters;

        public MilkBucket(boolean isFull, int liters) {
            this.isFull = isFull;
            this.liters = liters;
        }

        public void setIsFull(boolean full) {
            isFull = full;
        }

        public void setLiters(int amount) {
            this.liters = amount;
        }

        public boolean getIsFull() {
            return isFull;
        }

        public int getLiters() {
            return liters;
        }

        @Override
        public String toString() {
            String isFullString;

            if(isFull) {
                isFullString = "полное";
            } else {
                isFullString = "пустое";
            }

            return "Ведро молока (" + isFullString +
                    ", " + liters + "л)";
        }
    }

    // партия молока состоит из ведер, код работает с 5 ведрами
    public final static List<MilkBucket> milkBatch = new ArrayList<>();

    // производитель молока
    static class Producer implements Runnable {

        // рандомайзер литров молока (от 1 до 5 литров в ведре)
        public int makeMilk() {
            return (int) (Math.random()*5+1);
        }

        @Override
        public void run() {

            while(true) {

                // создаем молоко, пока есть свободные ведра
                for (int i = milkBatch.size(); i < 5; i++) {
                    MilkBucket milkBucket = new MilkBucket(true, makeMilk());

                    milkBatch.add(milkBucket);
                    System.out.println("Произведено: " + milkBucket + ". Наполнено ведер: " + milkBatch.size());
                }

                // сообщаем, что все пять ведер заполнены, тормозим поток
                synchronized (milkBatch) {
                    System.out.println("Все ведра заполнены, некуда разливать молоко!");
                    milkBatch.notify();
                    try {
                        milkBatch.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

    }

    // потребитель молока
    static class Consumer implements Runnable {

        @Override
        public void run() {

            // пьем молоко, пока есть полные ведра
            while(true) {
                for (int i = milkBatch.size(); i > 0; i--) {

                    System.out.println("Выпито: " + milkBatch.get(i - 1) + ". Наполнено ведер: " + (milkBatch.size() - 1));
                    milkBatch.remove(i - 1);

                }

                // сообщаем, что все ведра пусты, ждем появления нового молока
                synchronized (milkBatch) {
                    System.out.println("Все ведра пусты, нечего пить!");
                    milkBatch.notify();
                    try {
                        milkBatch.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }

        }

    }

    public static void main(String[] args) {

        Producer cow = new Producer();
        Thread makeMilk = new Thread(cow);

        Consumer human = new Consumer();
        Thread getMilk = new Thread(human);

        makeMilk.start();
        getMilk.start();

    }

}
