package com.paperfox.order.models.materials;

import com.paperfox.order.models.Size;

import java.util.Arrays;

public class Material {

    private int index;
    private String name;
    private int[] price;
    private Size printableArea;
    private int productionTime;
    private MaterialGroupType groupType;
    private MaterialType materialType;

    public Material(int index, String name, int[] price, Size printableArea, int productionTime,
                    MaterialGroupType groupType, MaterialType materialType) {
        this.index = index;
        this.name = name;
        this.price = price;
        this.printableArea = printableArea;
        this.productionTime = productionTime;
        this.groupType = groupType;
        this.materialType = materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
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

    public MaterialGroupType getGroupType() {
        return groupType;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    @Override
    public String toString() {
        return "Material{" +
                "index=" + index +
                ", name='" + name + '\'' +
                ", price=" + Arrays.toString(price) +
                ", printableArea=" + printableArea +
                ", productionTime=" + productionTime +
                ", groupType=" + groupType +
                ", materialType=" + materialType +
                '}';
    }
}