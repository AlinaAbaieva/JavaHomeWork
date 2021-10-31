package com.pb.abaieva.hw5;

public class Library {

    public static void printBooks(Book... book) {
        System.out.println("Список книг: ");
        for (Book value : book) {
            System.out.println(value.getTitle() + " (" + value.getAuthor() + " " + value.getDatePublished() + " г.)");
        }
    }

    public static void printReaders(Reader... reader) {
        System.out.println("Список читателей: ");
        for (Reader value : reader) {
            System.out.println(value.getFullName() + " (" + value.getLibraryCardNumber() + ", " + value.getFacultyName() + ")");
        }
    }

    public static void main(String[] args) {

        Book book1 = new Book();
        book1.setTitle("«Программирование: введение в профессию»");
        book1.setAuthor("Столяров А.В.");
        book1.setDatePublished(2016);

        Book book2 = new Book();
        book2.setTitle("«Белый Клык»");
        book2.setAuthor("Джек Лондон");
        book2.setDatePublished(1906);

        Book book3 = new Book();
        book3.setTitle("«Властелин колец»");
        book3.setAuthor("Толкин Дж. Р.Р.");
        book3.setDatePublished(1954);

        Reader reader1 = new Reader();
        reader1.setFullName("Кузнецов И.В.");
        reader1.setLibraryCardNumber(1101);
        reader1.setFacultyName("Информационные технологии");

        Reader reader2 = new Reader();
        reader2.setFullName("Бонд Дж.");
        reader2.setLibraryCardNumber(1007);
        reader2.setFacultyName("Секретная служба МИ-6");

        Reader reader3 = new Reader();
        reader3.setFullName("Нотфаунд Н.Н.");
        reader3.setLibraryCardNumber(404);
        reader3.setFacultyName("Факультет не найден");


        printBooks(book1, book2, book3);

        printReaders(reader1, reader2, reader3);

        System.out.println("Журнал библиотеки: ");

        reader1.takeBook(3);
        reader1.returnBook(2);
        reader2.takeBook("«Программирование: введение в профессию»", "«Белый Клык»");
        reader2.returnBook("«Белый Клык»", "«Программирование: введение в профессию»");
        reader3.takeBook(book1, book3);
        reader3.returnBook(book3, book1);

    }

}
