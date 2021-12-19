package com.pb.abaieva.hw11;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.time.LocalDateTime.now;

public class Phonebook {

    public static ArrayList<Contact> contactList = new ArrayList<>();

    public static void createPhonebook() throws IOException {

        ArrayList<String> basilPhones = new ArrayList<>();
        basilPhones.add("+380503681732");
        basilPhones.add("+380663227892");

        ArrayList<String> wilhelmPhones = new ArrayList<>();
        wilhelmPhones.add("101");

        ArrayList<String> buckPhones = new ArrayList<>();
        buckPhones.add("+1 (010) 101 111");

        Contact basil = new Contact("Васильев Василий Васильевич", LocalDate.of(1992, 2, 28), basilPhones, LocalDateTime.now());
        Contact wilhelm = new Contact("Фридрих Вильгельм Виктор Альберт Прусский", LocalDate.of(1941, 7, 4), wilhelmPhones, LocalDateTime.now());
        Contact buck = new Contact("Бак", LocalDate.of(1991, 1, 1), buckPhones, LocalDateTime.now());

        contactList.add(basil);
        contactList.add(wilhelm);
        contactList.add(buck);

        showMenu();

    }

    public static void showMenu() throws IOException {

        System.out.println("****************************************");
        System.out.println("ТЕЛЕФОННАЯ КНИГА");
        System.out.println("----------------------------------------");
        System.out.println("МЕНЮ");
        System.out.println("1. Добавить новый контакт");
        System.out.println("2. Удалить контакт");
        System.out.println("3. Найти контакт");
        System.out.println("4. Показать все контакты");
        System.out.println("5. Изменить контакт");
        System.out.println("6. Сохранить контакты");
        System.out.println("7. Загрузить контакты");
        System.out.println("8. Закрыть телефонную книгу");
        System.out.println("****************************************");

        openPhonebook(contactList);

    }

    public static void openPhonebook(ArrayList<Contact> contactList) throws IOException {

        System.out.println("****************************************");
        System.out.println("ВЫБЕРИТЕ ПУНКТ МЕНЮ. Чтобы просмотреть меню еще раз, напишите \"меню\"");

        Scanner scan = new Scanner(System.in);

        switch (scan.nextLine()) {
            case "меню":
                showMenu();
                break;
            case "1":
                addContact(contactList);
                break;
            case "2":
                deleteContact(contactList);
                break;
            case "3":
                findContact(contactList);
                break;
            case "4":
                showAllContacts(contactList);
                break;
            case "5":
                changeContact();
                break;
            case "6":
                createSaveFile(contactList);
                break;
            case "7":
                loadSaveFile();
                break;
            case "8":
                System.exit(8);
                break;
        }
    }

    public static void addContact(ArrayList<Contact> contactList) throws IOException {

        System.out.println("****************************************");
        System.out.println("ДОБАВЛЕНИЕ НОВОГО КОНТАКТА");
        System.out.println("----------------------------------------");

        Contact contact = new Contact();
        Scanner scan = new Scanner(System.in);

        // name
        System.out.println("Введите имя:");
        contact.setName(scan.nextLine());

        // bDate
        System.out.println("Введите дату рождения (дд.ММ.гггг):");
        contact.setbDate(LocalDate.parse(scan.nextLine(), DateTimeFormatter.ofPattern("dd.MM.yyyy")));

        // phoneList
        ArrayList<String> phones = new ArrayList<>();

        System.out.println("Введите номер телефона:");
        phones.add(scan.nextLine());

        String enterAnotherPhoneMsg = "Введите дополнительный номер телефона или напишите \"нет\":";
        System.out.println(enterAnotherPhoneMsg);
        String anotherPhone = scan.nextLine();

        while (!anotherPhone.equals("нет")) {
            System.out.println(enterAnotherPhoneMsg);
            phones.add(anotherPhone);
            anotherPhone = scan.nextLine();
        }

        contact.setPhoneList(phones);

        // lastEdit
        contact.setLastEdit(LocalDateTime.now());

        // system
        contactList.add(contact);
        System.out.println("----------------------------------------");
        System.out.println("Создан новый контакт: " + contact.getName() + "!");
        System.out.println("****************************************");
        openPhonebook(contactList);

    }

    public static void deleteContact(ArrayList<Contact> contactList) throws IOException {

        System.out.println("****************************************");
        System.out.println("УДАЛЕНИЕ КОНТАКТА");
        System.out.println("----------------------------------------");

        for (int i = 0; i < contactList.size(); i++) {

            System.out.println("*** Контакт №" + (i+1) + " ***");
            System.out.println(contactList.get(i).toString());
            System.out.println("----------------------------------------");

        }

        System.out.println("ВЫБЕРИТЕ НОМЕР КОНТАКТА ДЛЯ УДАЛЕНИЯ:");

        Scanner scan = new Scanner(System.in);

        int conNum = scan.nextInt() - 1;

        System.out.println("----------------------------------------");
        System.out.println("Контакт " + contactList.get(conNum).getName() + " удаляется...");

        contactList.remove(conNum);

        System.out.println("Контакт удален. Очень жаль :C");
        System.out.println("****************************************");

        openPhonebook(contactList);

    }

    public static void findContact(ArrayList<Contact> contactList) throws IOException {

        System.out.println("****************************************");
        System.out.println("ПОИСК КОНТАКТА ПО ИМЕНИ");
        System.out.println("----------------------------------------");

        Scanner scan = new Scanner(System.in);

        System.out.println("Введите имя:");

        String searchKey = scan.nextLine();

        int cntSrchResults = 0;

        for (int i = 0; i < contactList.size(); i++) {

            if (contactList.get(i).getName().equals(searchKey)) {
                System.out.println(contactList.get(i));
                System.out.println("----------------------------------------");
                cntSrchResults++;
            }

        }

        if (cntSrchResults == 0) {
            System.out.println("Такого контакта не существует.");
        } else {
            System.out.println("Поиск выполнен.");
        }

        System.out.println("****************************************");

        openPhonebook(contactList);

    }

    public static void showAllContacts(ArrayList<Contact> contactList) throws IOException {

        System.out.println("****************************************");
        System.out.println("СПИСОК КОНТАКТОВ");
        System.out.println("----------------------------------------");

        Scanner scan = new Scanner(System.in);

        System.out.println("Отсортировать по...");
        System.out.println("1. Имени");
        System.out.println("2. Дате рождения");

        switch (scan.nextInt()) {
            case 1:
                contactList.sort(Comparator.comparing(Contact::getName));
                break;
            case 2:
                contactList.sort(Comparator.comparing(Contact::getbDate));
                break;
        }

        for (int i = 0; i < contactList.size(); i++) {
            System.out.println("*** Контакт №" + (i+1) + " ***");
            System.out.println(contactList.get(i).toString());
            System.out.println("----------------------------------------");

        }

        System.out.println("****************************************");
        System.out.println("ВЫ ВОЗВРАЩЕНЫ В МЕНЮ");
        System.out.println("****************************************");

        openPhonebook(contactList);

    }

    public static void changeContact() throws IOException {

        System.out.println("****************************************");
        System.out.println("РЕДАКТИРОВАНИЕ КОНТАКТА");
        System.out.println("----------------------------------------");

        for (int i = 0; i < contactList.size(); i++) {

            System.out.println("*** Контакт №" + (i+1) + " ***");
            System.out.println(contactList.get(i).toString());
            System.out.println("----------------------------------------");

        }

        System.out.println("ВЫБЕРИТЕ НОМЕР КОНТАКТА:");

        Scanner scan = new Scanner(System.in);

        Contact contact = contactList.get(scan.nextInt() - 1);

        String conOldName = contact.getName();

        System.out.println("----------------------------------------");

        // name
        System.out.println("Введите новое имя:");
        contact.setName(scan.nextLine());

        // bDate
        System.out.println("Введите новую дату рождения (дд.ММ.гггг):");
        contact.setbDate(LocalDate.parse(scan.nextLine(), DateTimeFormatter.ofPattern("dd.MM.yyyy")));

        // phoneList
        ArrayList<String> phones = new ArrayList<>();

        System.out.println("Введите новый номер телефона:");
        phones.add(scan.nextLine());

        String enterAnotherPhoneMsg = "Введите дополнительный номер телефона или напишите \"нет\":";
        System.out.println(enterAnotherPhoneMsg);
        String anotherPhone = scan.nextLine();

        while (!anotherPhone.equals("нет")) {
            System.out.println(enterAnotherPhoneMsg);
            phones.add(anotherPhone);
            anotherPhone = scan.nextLine();
        }

        contact.setPhoneList(phones);

        // lastEdit
        contact.setLastEdit(LocalDateTime.now());

        // system
        System.out.println("----------------------------------------");
        contactList.add(contact);
        System.out.println("Контакт *" + conOldName + "* был сохранен заново как *" + contact.getName() + "*!");
        System.out.println("****************************************");
        openPhonebook(contactList);

    }

    public static void createSaveFile(ArrayList<Contact> contactList) throws IOException {

        System.out.println("****************************************");
        System.out.println("СОХРАНЕНИЕ КОНТАКТОВ");
        System.out.println("----------------------------------------");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        // LocalDateTime
        SimpleModule moduleLDT = new SimpleModule();
        moduleLDT.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        moduleLDT.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
        objectMapper.registerModule(moduleLDT);

        //LocalDate
        SimpleModule moduleLD = new SimpleModule();
        moduleLD.addSerializer(LocalDate.class, new LocalDateSerializer());
        moduleLD.addDeserializer(LocalDate.class, new LocalDateDeserializer());
        objectMapper.registerModule(moduleLD);

        String json = objectMapper.writeValueAsString(contactList);

        Path path = Paths.get("files/contacts.txt");

        BufferedWriter saveContacts = Files.newBufferedWriter(path);

        saveContacts.write(json);

        saveContacts.close();

        System.out.println("Контакты сохранены.");

        openPhonebook(contactList);

    }

    public static void loadSaveFile() throws IOException {

        System.out.println("****************************************");
        System.out.println("ЗАГРУЗКА КОНТАКТОВ");
        System.out.println("----------------------------------------");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        // LocalDateTime
        SimpleModule moduleLDT = new SimpleModule();
        moduleLDT.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        moduleLDT.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
        objectMapper.registerModule(moduleLDT);

        //LocalDate
        SimpleModule moduleLD = new SimpleModule();
        moduleLD.addSerializer(LocalDate.class, new LocalDateSerializer());
        moduleLD.addDeserializer(LocalDate.class, new LocalDateDeserializer());
        objectMapper.registerModule(moduleLD);

        // LOAD

        Path path = Paths.get("files/contacts.txt");

        BufferedReader loadContacts = Files.newBufferedReader(path);

        StringBuilder stringBuilder = new StringBuilder();
        String line = loadContacts.readLine();

        while (line != null) {

            stringBuilder.append(line);
            stringBuilder.append("\n");
            line = loadContacts.readLine();

        }

        String sbString = stringBuilder.toString();

        ArrayList<Contact> contactList = objectMapper.readValue(sbString, new TypeReference<ArrayList<Contact>>() {});

        System.out.println("Контакты загружены.");
        System.out.println("****************************************");

        openPhonebook(contactList);

    }




    public static void main(String[] args) throws IOException {

        createPhonebook();

    }


}
