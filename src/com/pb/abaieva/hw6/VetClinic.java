package com.pb.abaieva.hw6;

public class VetClinic {

    public static void main(String[] args) {

        Animal[] patientsVC = new Animal[3];

        Dog rover;
        rover.food = "премиум корм";
        rover.location = "конура";
        rover.setNeedsWalk(false);

        Cat tom;
        tom.food = "сырая рыба";
        tom.location = "подвал";
        tom.setClawSharpness("острые");

        Horse bucephalus;
        bucephalus.food = "свежее сено";
        bucephalus.location = "конюшня";
        bucephalus.setIsMounted() = false;

        patientsVC[0] = rover;
        patientsVC[1] = tom;
        patientsVC[2] = bucephalus;

        Veterenarian aibolit = new Veterenarian();

        for (int i = 0; i < patientsVC.length; i++) {

            aibolit.(patientsVC[i]);

        }

    }

}
