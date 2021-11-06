package com.pb.abaieva.hw6;

public class AnimalTest {

    public static void main(String[] args) {

        Horse horse1 = new Horse();

        horse1.food = "trava";
        horse1.location = "luga";
        horse1.isMounted = true;

        Horse horse2 = new Horse();

        horse2.food = "trava";
        horse2.location = "luga";
        horse2.isMounted = true;

        Horse horse3 = new Horse();

        horse3.food = "trava";
        horse3.location = "luga";
        horse3.isMounted = false;

        System.out.println("horse1 equals horse 2: " + horse1.equals(horse2));
        System.out.println("horse2 equals horse 3: " + horse2.equals(horse3));
        System.out.println("horse1 equals horse 3: " + horse1.equals(horse3));

        System.out.println("horse1 hashCode: " + horse1.hashCode());
        System.out.println("horse2 hashCode: " + horse2.hashCode());
        System.out.println("horse3 hashCode: " + horse3.hashCode());

    }

}
