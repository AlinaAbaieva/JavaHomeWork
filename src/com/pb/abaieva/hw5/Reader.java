package com.pb.abaieva.hw5;

import java.sql.Date;

public class Reader {

    private String fullName;
    private int libraryCardNumber;
    private String facultyName;
    private Date dateOfBirth;

    /**************************************************************************
     Геттеры и сеттеры
     **************************************************************************/

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getLibraryCardNumber() {
        return libraryCardNumber;
    }

    public void setLibraryCardNumber(int libraryCardNumber) {
        this.libraryCardNumber = libraryCardNumber;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public Date getDateOfBirthdateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**************************************************************************
     Методы
     **************************************************************************/

    public void takeBook(int amount) {

        if (amount > 0) {
            System.out.println(fullName + " взял " + amount + " книги.");
        }

    }

    public void takeBook(String ... title) {
        StringBuilder notifyBookTitleTaken = new StringBuilder();
        notifyBookTitleTaken.append(fullName).append(" взял книги: ");

        for (int i = 0; i < title.length; i++) {
            notifyBookTitleTaken.append(title[i]);

            if (i < title.length-1) {
                notifyBookTitleTaken.append(", ");
            } else {
                notifyBookTitleTaken.append(".");
            }
        }

        System.out.println(notifyBookTitleTaken);
    }

    public void takeBook(Book ... book) {

        StringBuilder notifyBookObjectTaken = new StringBuilder();
        notifyBookObjectTaken.append(fullName).append(" взял книнги: ");

        for (int i = 0; i < book.length; i++) {
            notifyBookObjectTaken.append(book[i].getTitle()).append(" (").append(book[i].getAuthor()).append(" ")
                    .append(book[i].getDatePublished()).append(" г.)");

            if (i < book.length - 1) {
                notifyBookObjectTaken.append(", ");
            } else {
                notifyBookObjectTaken.append(".");
            }
        }

        System.out.println(notifyBookObjectTaken);

    }

    public void returnBook(int amount) {

        if (amount > 0) {
            System.out.println(fullName + " вернул " + amount + " книги.");
        }

    }

    public void returnBook(String ... title) {
        StringBuilder notifyBookTitleReturned = new StringBuilder();
        notifyBookTitleReturned.append(fullName).append(" вернул книги: ");

        for (int i = 0; i < title.length; i++) {
            notifyBookTitleReturned.append(title[i]);

            if (i < title.length-1) {
                notifyBookTitleReturned.append(", ");
            } else {
                notifyBookTitleReturned.append(".");
            }
        }

        System.out.println(notifyBookTitleReturned);
    }

    public void returnBook(Book ... book) {

        StringBuilder notifyBookObjectReturned = new StringBuilder();
        notifyBookObjectReturned.append(fullName).append(" вернул книнги: ");

        for (int i = 0; i < book.length; i++) {
            notifyBookObjectReturned.append(book[i].getTitle()).append(" (").append(book[i].getAuthor())
                    .append(" ").append(book[i].getDatePublished()).append(" г.)");

            if (i < book.length - 1) {
                notifyBookObjectReturned.append(", ");
            } else {
                notifyBookObjectReturned.append(".");
            }
        }

        System.out.println(notifyBookObjectReturned);

    }

    public static void main(String[] args) {

    }

}
