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

        Contact basil = new Contact("Basil BestFriendo", LocalDate.of(1992, 2, 28), basilPhones, LocalDateTime.now());
        Contact vince = new Contact("Vince", LocalDate.of(2009, 4, 22), basilPhones, LocalDateTime.now());
        Contact buck = new Contact("Buck", LocalDate.of(1991, 1, 1), basilPhones, LocalDateTime.now());

        contactList.add(basil);
        contactList.add(vince);
        contactList.add(buck);

        showMenu();

    }

    public static void showMenu() throws IOException {

        System.out.println("----------------------------------------");
        System.out.println("Your Phonebook!");
        System.out.println("----------------------------------------");
        System.out.println("MENU");
        System.out.println("1. Add new Contact.");
        System.out.println("2. Delete Contact.");
        System.out.println("3. Find Contact.");
        System.out.println("4. Show all Contacts.");
        System.out.println("5. Change Contact.");
        System.out.println("6. Create a save file.");
        System.out.println("7. Load a save file.");
        System.out.println("8. Exit.");
        System.out.println("----------------------------------------");
        System.out.println("SELECT MENU NUMBER. TYPE \"menu\" TO SEE MENU AGAIN");
        System.out.println("----------------------------------------");

        openPhonebook(contactList);

    }

    public static void openPhonebook(ArrayList<Contact> contactList) throws IOException {

        Scanner scan = new Scanner(System.in);

        switch (scan.next()) {
            case "menu":
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

        Contact contact = new Contact();
        Scanner scan = new Scanner(System.in);

        // name
        System.out.println("Enter name:");
        contact.setName(scan.next());

        // bDate
        System.out.println("Enter date of birth:");
        contact.setbDate(LocalDate.parse(scan.next(), DateTimeFormatter.ofPattern("dd.MM.yyyy")));

        // phoneList
        ArrayList<String> phones = new ArrayList<>();

        System.out.println("Enter phone number:");
        phones.add(scan.next());

        String enterAnotherPhoneMsg = "Enter another phone number or write \"done\":";
        System.out.println(enterAnotherPhoneMsg);
        String anotherPhone = scan.next();

        while (!anotherPhone.equals("done")) {
            System.out.println(enterAnotherPhoneMsg);
            phones.add(anotherPhone);
            anotherPhone = scan.next();
        }

        contact.setPhoneList(phones);

        // lastEdit
        contact.setLastEdit(LocalDateTime.now());

        // system
        contactList.add(contact);
        System.out.println("Contact created: " + contact.getName() + "!");
        openPhonebook(contactList);

    }

    public static void deleteContact(ArrayList<Contact> contactList) throws IOException {

        for (int i = 0; i < contactList.size(); i++) {

            System.out.println("*** Contact " + (i+1) + " ***");
            System.out.println(contactList.get(i).toString());
            System.out.println("----------------------------------------");

        }

        System.out.println("SELECT CONTACT NUMBER TO DELETE:");

        Scanner scan = new Scanner(System.in);

        int conNum = scan.nextInt() - 1;

        System.out.println("Contact " + contactList.get(conNum).getName() + " is being deleted...");

        contactList.remove(conNum);

        System.out.println("Deleted successfully. So sad :C");

        openPhonebook(contactList);

    }

    public static void findContact(ArrayList<Contact> contactList) throws IOException {

        Scanner scan = new Scanner(System.in);

        System.out.println("Enter search key:");

        String searchKey = scan.next();

        int cntSrchResults = 0;

        for (int i = 0; i < contactList.size(); i++) {

            if (contactList.get(i).getName().equals(searchKey)) {
                System.out.println(contactList.get(i));
                System.out.println("----------------------------------------");
                cntSrchResults++;
            }

        }

        if (cntSrchResults == 0) {
            System.out.println("No such contact.");
        } else {
            System.out.println("Search done.");
        }

        openPhonebook(contactList);

    }

    public static void showAllContacts(ArrayList<Contact> contactList) throws IOException {

        Scanner scan = new Scanner(System.in);

        System.out.println("Select sorting by...");
        System.out.println("1. Name");
        System.out.println("2. Date of Birth");

        switch (scan.nextInt()) {
            case 1:
                contactList.sort(Comparator.comparing(Contact::getName));
                break;
            case 2:
                contactList.sort(Comparator.comparing(Contact::getbDate));
                break;
        }

        for (int i = 0; i < contactList.size(); i++) {
            System.out.println("*** Contact " + (i+1) + " ***");
            System.out.println(contactList.get(i).toString());
            System.out.println("----------------------------------------");

        }

        openPhonebook(contactList);

    }

    public static void changeContact() throws IOException {


        for (int i = 0; i < contactList.size(); i++) {

            System.out.println("*** Contact " + (i+1) + " ***");
            System.out.println(contactList.get(i).toString());
            System.out.println("----------------------------------------");

        }

        System.out.println("SELECT CONTACT NUMBER TO CHANGE:");

        Scanner scan = new Scanner(System.in);

        Contact contact = contactList.get(scan.nextInt() - 1);

        String conOldName = contact.getName();

        // name
        System.out.println("Enter new name:");
        contact.setName(scan.next());

        // bDate
        System.out.println("Enter new date of birth:");
        contact.setbDate(LocalDate.parse(scan.next(), DateTimeFormatter.ofPattern("dd.MM.yyyy")));

        // phoneList
        ArrayList<String> phones = new ArrayList<>();

        System.out.println("Enter new phone number:");
        phones.add(scan.next());

        String enterAnotherPhoneMsg = "Enter another phone number or write \"done\":";
        System.out.println(enterAnotherPhoneMsg);
        String anotherPhone = scan.next();

        while (!anotherPhone.equals("done")) {
            System.out.println(enterAnotherPhoneMsg);
            phones.add(anotherPhone);
            anotherPhone = scan.next();
        }

        contact.setPhoneList(phones);

        // lastEdit
        contact.setLastEdit(LocalDateTime.now());

        // system
        contactList.add(contact);
        System.out.println("Contact *" + conOldName + "* saved as *" + contact.getName() + "*!");
        openPhonebook(contactList);

        openPhonebook(contactList);

    }

    public static void createSaveFile(ArrayList<Contact> contactList) throws IOException {

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

        System.out.println("Contacts saved.");

        openPhonebook(contactList);

    }

    public static void loadSaveFile() throws IOException {

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
        // String json = objectMapper.readValue(scan.next(), ArrayList.class);

        // System.out.println(json);

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

        System.out.println("Contacts loaded.");

    }




    public static void main(String[] args) throws IOException {

        createPhonebook();

    }


}
