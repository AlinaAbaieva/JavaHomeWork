package com.pb.abaieva.hw8;

public class OnlineShop {

    public static void main(String[] args) {

        Auth userDina = new Auth();

        try {
            userDina.signUp();
            userDina.signIn();
        } catch (WrongLoginException | WrongPasswordException e) {
            e.printStackTrace();
        }

    }

}
