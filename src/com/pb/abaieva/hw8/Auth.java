package com.pb.abaieva.hw8;

import java.util.Scanner;

public class Auth {

    private String login;
    private String password;

    //GETTERS

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    //SETTERS

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //METHODS

    public void signUp() throws WrongLoginException, WrongPasswordException {

        System.out.println("Пожалуйста, зарегистрируйтесь на сайте онлайн магазина.");

        Scanner scanAuth = new Scanner(System.in);

        System.out.println("Введите логин: ");

        String checkLogin = scanAuth.nextLine();

        if (checkLogin.length() < 5 | checkLogin.length() > 20 | !checkLogin.matches("\\w+")) {
            throw new WrongLoginException("Логин должен содержать от 5 до 20 символов латинского алфавита или цифр.");
        } else {
            setLogin(checkLogin);
        }

        System.out.println("Введите пароль: ");
        String checkPassword = scanAuth.nextLine();

        if (checkPassword.length() < 5 | !checkPassword.matches("\\w+_*")) {
            throw new WrongPasswordException("Пароль должен содержать не менее 5 символов латинского алфавита или цифр, допускается знак подчеркивания (_).");
        } else {
            System.out.println("Подтвердите пароль: ");
            if (checkPassword.equals(scanAuth.nextLine())) {
                setPassword(checkPassword);
                System.out.println("Вы успешно зарегистрировались.");
            } else {
                throw new WrongPasswordException("Пароли не совпадают.");
            }
        }
    }

    public void signIn() throws WrongLoginException {

        System.out.println("Теперь можете войти на сайт.");

        Scanner scanAuth = new Scanner(System.in);

        System.out.println("Введите свой логин: ");

        if (scanAuth.nextLine().equals(getLogin())) {
            System.out.println("Введите свой пароль: ");

            if (scanAuth.nextLine().equals(getPassword())) {
                System.out.println("Добро пожаловать, " + getLogin() + "!");
            } else {
                throw new WrongLoginException("Неправильный логин или пароль.");
            }

        }

    }

}