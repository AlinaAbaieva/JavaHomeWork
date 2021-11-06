package com.pb.abaieva.hw6;

import java.util.Objects;

public class Horse extends Animal {

    private boolean isMounted;

    public boolean getIsMounted() {
        return this.isMounted;
    }

    public void setIsMounted(boolean isMounted) {
        this.isMounted = isMounted;
    }

    @Override
    public void eat() {
        System.out.println("Лошадь ест.");
    };

    @Override
    public void makeNoise() {
        System.out.println("Лошадь ржёт.");
    };

    @Override
    public String toString() {
        String horseRider = isMounted ? "есть" : "нет";
        return "Лошадь (питание: " + food + "; место проживания: " + location + ", наездник: " + horseRider + ")";
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        };
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Horse horse = (Horse) o;
        return (Objects.equals(food, horse.food) & Objects.equals(location, horse.location) & Objects.equals(isMounted, horse.isMounted));

    }

    @Override
    public int hashCode() {
        return Objects.hash(food, location, isMounted);
    }

}
