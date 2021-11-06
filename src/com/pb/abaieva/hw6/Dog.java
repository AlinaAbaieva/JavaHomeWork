package com.pb.abaieva.hw6;

import java.util.Objects;

public class Dog extends Animal {

    private boolean needsWalk;

    public boolean getNeedsWalk() {
        return this.needsWalk;
    }

    public void setNeedsWalk(boolean needsWalk) {
        this.needsWalk = needsWalk;
    }

    @Override
    public void eat() {
        System.out.println("Собака ест.");
    };

    @Override
    public void makeNoise() {
        System.out.println("Собака гавкает.");
    };

    @Override
    public String toString() {
        String walkStatus = needsWalk ? "требуется" : "не требуется";
        return "Собака (питание: " + food + "; место проживания: " + location + "; выгул: " + walkStatus + ")";
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        };
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Dog dog = (Dog) o;
        return (Objects.equals(food, dog.food) & Objects.equals(location, dog.location) & Objects.equals(needsWalk, dog.needsWalk));

    }

    @Override
    public int hashCode() {
        return Objects.hash(food, location, needsWalk);
    }

}
