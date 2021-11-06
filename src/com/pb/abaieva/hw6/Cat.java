package com.pb.abaieva.hw6;

import java.util.Objects;

public class Cat extends Animal {

    private String clawSharpness;

    public String getClawSharpness() {
        return this.clawSharpness;
    }

    public void setClawSharpness(String clawSharpness) {
        this.clawSharpness = clawSharpness;
    }

    @Override
    public void eat() {
        System.out.println("Кот ест.");
    };

    @Override
    public void makeNoise() {
        System.out.println("Кот мяукает.");
    };

    @Override
    public String toString() {
        return "Кот (питание: " + food + ", ареал: " + location + ", состояние когтей: " + clawSharpness + ")";
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        };
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Cat cat = (Cat) o;
        return (Objects.equals(food, cat.food) & Objects.equals(location, cat.location) & Objects.equals(clawSharpness, cat.clawSharpness));

    }

    @Override
    public int hashCode() {
        return Objects.hash(food, location, clawSharpness);
    }

}
