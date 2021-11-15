package com.pb.abaieva.hw7;

import com.pb.abaieva.hw7.clothesTypes.Pants;
import com.pb.abaieva.hw7.clothesTypes.Skirt;
import com.pb.abaieva.hw7.clothesTypes.Tie;
import com.pb.abaieva.hw7.clothesTypes.Tshirt;

public class Atelier {

    public static void dressMan(Clothes[] clothes) {
        System.out.println("*** Мужская одежда ***");

        for (Clothes c : clothes) {

            if (c instanceof Pants) {
                ((Pants) c).dressMan();
            }
            if (c instanceof Tshirt) {
                ((Tshirt) c).dressMan();
            }
            if (c instanceof Tie) {
                ((Tie) c).dressMan();
            }

        }

    }
    public static void dressWoman(Clothes[] clothes) {
        System.out.println("*** Женская одежда ***");

        for (Clothes c : clothes) {

            if (c instanceof Pants) {
                ((Pants) c).dressWoman();
            }
            if (c instanceof Tshirt) {
                ((Tshirt) c).dressWoman();
            }
            if (c instanceof Skirt) {
                ((Skirt) c).dressWoman();
            }

        }

    }

    public static void main(String[] args) {

        Clothes[] packOfClothes = new Clothes[4];

        Pants greenPants = new Pants();
        greenPants.setSize(Size.XXS);
        greenPants.setPrice(25.59);
        greenPants.setColor("зеленый");

        Tshirt yellowTshirt = new Tshirt();
        yellowTshirt.setSize(Size.S);
        yellowTshirt.setPrice(19.99);
        yellowTshirt.setColor("желтый");

        Skirt redSkirt = new Skirt();
        redSkirt.setSize(Size.M);
        redSkirt.setPrice(39.99);
        redSkirt.setColor("красный");

        Tie blackTie = new Tie();
        blackTie.setSize(Size.L);
        blackTie.setPrice(29.99);
        blackTie.setColor("черный");

        packOfClothes[0] = greenPants;
        packOfClothes[1] = yellowTshirt;
        packOfClothes[2] = redSkirt;
        packOfClothes[3] = blackTie;

        dressMan(packOfClothes);
        dressWoman(packOfClothes);

    }

}
