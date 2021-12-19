package com.pb.abaieva.hw11;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Contact {

    private String name;
    private LocalDate dateOfBirth;
    private ArrayList<String> phoneList;
    private LocalDateTime lastEdit;

    public Contact(String name, LocalDate dateOfBirth, ArrayList<String> phoneList, LocalDateTime lastEdit) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.phoneList = phoneList;
        this.lastEdit = lastEdit;
    }

    public Contact() {
    }

    // SETTERS
    public void setName(String name) {
        this.name = name;
    }

    public void setbDate(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setPhoneList(ArrayList<String> phoneList) {
        this.phoneList = phoneList;
    }

    public void setLastEdit(LocalDateTime lastEdit) {
        this.lastEdit = lastEdit;
    }


    // GETTERS
    public String getName() {
        return name;
    }

    public LocalDate getbDate() {
        return dateOfBirth;
    }

    public ArrayList<String> getPhoneList() {
        return phoneList;
    }

    public LocalDateTime getLastEdit() {
        return lastEdit;
    }

    // METHODS

    @Override
    public String toString(){
        return
                name + "\r\n" +
                        dateOfBirth.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + "\r\n" +
                        phoneList + "\r\nПоследние изменение - " +
                        lastEdit.format(DateTimeFormatter.ofPattern("HH:mm:ss dd.MM.yyyy"));
    }

    public String searchKeys() {

        return name;

    }

}
