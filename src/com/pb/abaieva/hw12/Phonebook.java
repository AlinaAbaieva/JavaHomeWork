package com.pb.abaieva.hw12;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Phonebook {

    public static class Contact {

        private String name;
        private LocalDate dateOfBirth;
        private ArrayList<String> phoneList;
        private LocalDateTime lastEdit;

        public Contact(){}

        public Contact(
                String name,
                LocalDate dateOfBirth,
                ArrayList<String> phoneList,
                LocalDateTime lastEdit
        ) {
            this.name = name;
            this.dateOfBirth = dateOfBirth;
            this.phoneList = phoneList;
            this.lastEdit = lastEdit;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setDateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        public LocalDate getDateOfBirth() {
            return dateOfBirth;
        }

        public void setPhoneList(ArrayList<String> phoneList) {
            this.phoneList = phoneList;
        }

        public ArrayList<String> getPhoneList() {
            return phoneList;
        }

        public void setLastEdit(LocalDateTime lastEdit) {
            this.lastEdit = lastEdit;
        }

        public LocalDateTime getLastEdit() {
            return lastEdit;
        }

        @Override
        public String toString() {
            return
                    name + "\r\nДата рождения: " +
                    dateOfBirth.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + "\r\n" +
                    phoneList + "\r\nПоследнее изменение - " +
                    lastEdit.format(DateTimeFormatter.ofPattern("HH:mm:ss dd.MM.yyyy"));
        }

    }

    public static void showMenu() {

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
        System.out.println("----------------------------------------");

    }

    public static String menuChoice() {

        System.out.println("****************************************");
        System.out.println("ВЫБЕРИТЕ ПУНКТ МЕНЮ. Напишите \"меню\", чтобы посмотреть меню еще раз.");
        System.out.println("----------------------------------------");
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();

    }

    @FunctionalInterface
    interface MenuAction {
        ArrayList<Contact> doThing(ArrayList<Contact> contacts);
    }

    public static ArrayList<Contact> contactList = new ArrayList<>();

    public static void main(String[] args) {

        // creating base contacts
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

        // menu
        while (true) {

            switch (menuChoice()) {

                case "меню":
                    showMenu();
                    continue;

                case "1":
                    MenuAction add = contacts -> {
                        System.out.println("****************************************");
                        System.out.println("ДОБАВЛЕНИЕ НОВОГО КОНТАКТА");
                        System.out.println("----------------------------------------");

                        Scanner scan = new Scanner(System.in);
                        Contact contact = new Contact();

                        // enter info
                        System.out.println("Введите имя:");
                        contact.setName(scan.nextLine());

                        System.out.println("Введите дату рождения (дд.ММ.гггг):");
                        contact.setDateOfBirth(LocalDate.parse(scan.nextLine(), DateTimeFormatter.ofPattern("dd.MM.yyyy")));

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

                        contact.setLastEdit(LocalDateTime.now());

                        // add contact to list
                        contacts.add(contact);
                        System.out.println("----------------------------------------");
                        System.out.println("Создан новый контакт: " + contact.getName() + "!");
                        return contacts;
                    };

                    contactList = add.doThing(contactList);
                    continue;

                case "2":
                    MenuAction del = contacts -> {
                        System.out.println("****************************************");
                        System.out.println("УДАЛЕНИЕ КОНТАКТА");
                        System.out.println("----------------------------------------");

                        for (int i = 0; i < contacts.size(); i++) {
                            System.out.println("*** Контакт №" + (i+1) + " ***");
                            System.out.println(contacts.get(i).toString());
                            System.out.println("----------------------------------------");
                        }

                        System.out.println("ВЫБЕРИТЕ НОМЕР КОНТАКТА ДЛЯ УДАЛЕНИЯ:");
                        Scanner scan = new Scanner(System.in);
                        int conNum = scan.nextInt() - 1;
                        scan.nextLine();

                        System.out.println("----------------------------------------");
                        System.out.println("Контакт *" + contacts.get(conNum).getName() + "* удаляется...");
                        contacts.remove(conNum);
                        System.out.println("Контакт удален. Очень жаль :C");

                        return contacts;
                    };

                    contactList = del.doThing(contactList);
                    continue;

                case "3":
                    MenuAction find = contacts -> {
                        System.out.println("****************************************");
                        System.out.println("ПОИСК КОНТАКТА ПО ИМЕНИ");
                        System.out.println("----------------------------------------");

                        Scanner scan = new Scanner(System.in);
                        System.out.println("Введите имя:");
                        String searchKey = scan.nextLine();

                        int cntSrchResults = 0;

                        for (int i = 0; i < contacts.size(); i++) {

                            if (contacts.get(i).getName().equals(searchKey)) {
                                System.out.println(contacts.get(i));
                                System.out.println("----------------------------------------");
                                cntSrchResults++;
                            }

                        }

                        if (cntSrchResults == 0) {
                            System.out.println("Такого контакта не существует.");
                        } else {
                            System.out.println("Поиск выполнен.");
                        }

                        return contacts;
                    };
                    contactList = find.doThing(contactList);
                    continue;

                case "4":
                    MenuAction showAll = contacts -> {
                        System.out.println("****************************************");
                        System.out.println("СПИСОК КОНТАКТОВ");
                        System.out.println("----------------------------------------");

                        Scanner scan = new Scanner(System.in);

                        System.out.println("Отсортировать по...");
                        System.out.println("1. Имени");
                        System.out.println("2. Дате рождения");
                        System.out.println("3. Последнему изменению");

                        switch (scan.nextLine()) {
                            case "1":
                                List<Contact> sortedContactsName = contacts
                                        .stream()
                                        .sorted(Comparator.comparing(Contact::getName))
                                        .collect(Collectors.toList());


                                Integer[] indexName = new Integer[1];
                                indexName[0] = 1;

                                /**
                                 Переменная в стриме должна быть final, но элемент массива может изменяться,
                                 т.к. сам массив - это ссылка, он остается effective final.
                                 Спасибо, что напомнили об этом на лекции :)
                                 Пригодилось для нумерации выводимого списка.
                                 Надеюсь, в реальных задачах от такого решения не возникает неожиданных ошибок
                                 (если делать с умом).
                                 */

                                sortedContactsName.forEach
                                        (
                                                x -> {
                                                    System.out.println("----------------------------------------");
                                                    System.out.println("*** Контакт №" + (indexName[0]++) + " ***");
                                                    System.out.println(x.toString());
                                                }
                                        );
                                break;

                            case "2":
                                List<Contact> sortedContactsDOB = contacts
                                        .stream()
                                        .sorted(Comparator.comparing(Contact::getDateOfBirth))
                                        .collect(Collectors.toList());

                                Integer[] indexDOB = new Integer[1];
                                indexDOB[0] = 1;

                                sortedContactsDOB.forEach
                                        (
                                                x -> {
                                                    System.out.println("----------------------------------------");
                                                    System.out.println("*** Контакт №" + (indexDOB[0]++) + " ***");
                                                    System.out.println(x.toString());
                                                }
                                        );
                                break;

                            case "3":
                                List<Contact> sortedContactsLE = contacts
                                        .stream()
                                        .sorted(Comparator.comparing(Contact::getLastEdit))
                                        .collect(Collectors.toList());

                                Integer[] indexLE = new Integer[1];
                                indexLE[0] = 1;

                                sortedContactsLE.forEach
                                        (
                                                x -> {
                                                    System.out.println("----------------------------------------");
                                                    System.out.println("*** Контакт №" + (indexLE[0]++) + " ***");
                                                    System.out.println(x.toString());
                                                }
                                        );
                                break;
                        }

                        System.out.println("----------------------------------------");
                        System.out.println("ВЫ ВОЗВРАЩЕНЫ В МЕНЮ");

                        return contacts;

                    };

                    contactList = showAll.doThing(contactList);
                    continue;

                case "5":
                    MenuAction edit = contacts -> {
                        System.out.println("****************************************");
                        System.out.println("РЕДАКТИРОВАНИЕ КОНТАКТА");

                        for (int i = 0; i < contacts.size(); i++) {
                            System.out.println("----------------------------------------");
                            System.out.println("*** Контакт №" + (i+1) + " ***");
                            System.out.println(contacts.get(i).toString());
                        }

                        System.out.println("ВЫБЕРИТЕ НОМЕР КОНТАКТА:");
                        Scanner scan = new Scanner(System.in);
                        Contact contact = contacts.get(scan.nextInt() - 1);
                        scan.nextLine();

                        System.out.println("----------------------------------------");
                        String conOldName = contact.getName();

                        // info
                        System.out.println("Введите новое имя:");
                        contact.setName(scan.nextLine());

                        System.out.println("Введите новую дату рождения (дд.ММ.гггг):");
                        contact.setDateOfBirth(LocalDate.parse(scan.nextLine(), DateTimeFormatter.ofPattern("dd.MM.yyyy")));

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

                        contact.setLastEdit(LocalDateTime.now());

                        // done
                        System.out.println("----------------------------------------");
                        System.out.println("Контакт *" + conOldName + "* был сохранен заново как *" + contact.getName() + "*!");

                        return contacts;
                    };

                    contactList = edit.doThing(contactList);
                    continue;

                case "6":
                    MenuAction save = contacts -> {

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

                        String json = null;
                        try {
                            json = objectMapper.writeValueAsString(contacts);
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }

                        Path path = Paths.get("files/contacts.txt");

                        BufferedWriter saveContacts = null;

                        try {
                            saveContacts = Files.newBufferedWriter(path);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        try {
                            assert json != null;
                            assert saveContacts != null;
                            saveContacts.write(json);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        try {
                            saveContacts.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        System.out.println("Контакты сохранены.");

                        return contactList;
                    };

                    save.doThing(contactList);
                    continue;

                case "7":
                    MenuAction load = contacts -> {
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

                        BufferedReader loadContacts = null;
                        try {
                            loadContacts = Files.newBufferedReader(path);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        StringBuilder stringBuilder = new StringBuilder();
                        String line = null;
                        try {
                            line = loadContacts.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        while (line != null) {

                            stringBuilder.append(line);
                            stringBuilder.append("\n");
                            try {
                                line = loadContacts.readLine();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }

                        String sbString = stringBuilder.toString();

                        ArrayList<Contact> contactList = null;
                        try {
                            contactList = objectMapper.readValue(sbString, new TypeReference<ArrayList<Contact>>() {});
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }

                        try {
                            loadContacts.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        System.out.println("Контакты загружены.");

                        return contactList;
                    };

                    contactList = load.doThing(contactList);
                    continue;

                case "8":
                    System.exit(8);

            }

        }

    }

}
