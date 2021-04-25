package com.paperfox.order.models;

import java.util.Arrays;

public class Material {

    private int index;
    private String name;
    private int[] price;
    private Size printableArea;
    private int productionTime;
    private MaterialGroupType groupType; //todo
//    private MaterialType materialType; // todo

    public Material(int index, String name, int[] price, Size printableArea, int productionTime) {
        this.index = index;
        this.name = name;
        this.price = price;
        this.printableArea = printableArea;
        this.productionTime = productionTime;
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

    public int getProductionTime() {
        return productionTime;
    }

    @Override
    public String toString() {
        return "Material{" +
                "index=" + index +
                ", name='" + name + '\'' +
                ", price=" + Arrays.toString(price) +
                ", printableArea=" + printableArea +
                ", productionTime=" + productionTime +
                '}';
    }
}