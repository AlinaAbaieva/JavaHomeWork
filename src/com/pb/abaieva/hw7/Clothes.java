package com.pb.abaieva.hw7;

public abstract class Clothes {

    private Size size;
    private double price;
    private String color;

    //GETTERS

    public Size getSize() {
        return size;
    }

    public double getPrice() {
        return price;
    }

    public String getColor() {
        return color;
    }

    //SETTERS

    public void setSize(Size size) {
        this.size = size;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setColor(String color) {
        this.color = color;
    }

}