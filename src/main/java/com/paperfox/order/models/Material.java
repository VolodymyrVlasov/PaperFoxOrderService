package com.paperfox.order.models;


public class Material {

    // todo define standard time for production

    private int index;
    private String name;
    private int[] price;
    private Size printableArea;

    public Material(int index, String name, int[] price, Size printableArea) {
        this.index = index;
        this.name = name;
        this.price = price;
        this.printableArea = printableArea;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public int[] getPrice() {
        return price;
    }

    public Size getPrintableArea() {
        return printableArea;
    }
}