package com.pb.abaieva.hw7.clothesTypes;

import com.pb.abaieva.hw7.Clothes;
import com.pb.abaieva.hw7.ManClothes;
import com.pb.abaieva.hw7.WomanClothes;

public class Pants extends Clothes implements ManClothes, WomanClothes {

    @Override
    public String toString() {
        return "Штаны";
    }

    @Override
    public void dressMan() {
        System.out.println(this + " | размер: " + this.getSize() + " | цена: "  + this.getPrice()
                + " | цвет: "  + this.getColor()
                + " | (" + this.getSize().getDescription()
                + ", европейский: " + this.getSize().getEuroSize() + ")");
    }

    @Override
    public void dressWoman() {
        System.out.println(this + " | размер: " + this.getSize() + " | цена: "  + this.getPrice()
                + " | цвет: "  + this.getColor()
                + " | (" + this.getSize().getDescription()
                + ", европейский: " + this.getSize().getEuroSize() + ")");
    }
}
