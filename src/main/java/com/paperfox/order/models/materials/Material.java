package com.paperfox.order.models.materials;

import com.paperfox.order.models.Size;

import java.util.Arrays;
import java.util.List;

public class Material {

    private int index;
    private String name;
    private List<Price> price;
    private Size printableArea;
    private int productionTime;
    private MaterialGroupType groupType;
    private MaterialType materialType;

    public Material(int index, String name, List<Price> price, Size printableArea, int productionTime, MaterialGroupType groupType, MaterialType materialType) {
        this.index = index;
        this.name = name;
        this.price = price;
        this.printableArea = printableArea;
        this.productionTime = productionTime;
        this.groupType = groupType;
        this.materialType = materialType;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Price> getPrice() {
        return price;
    }

    public void setPrice(List<Price> price) {
        this.price = price;
    }

    public Size getPrintableArea() {
        return printableArea;
    }

    public void setPrintableArea(Size printableArea) {
        this.printableArea = printableArea;
    }

    public int getProductionTime() {
        return productionTime;
    }

    public void setProductionTime(int productionTime) {
        this.productionTime = productionTime;
    }

    public MaterialGroupType getGroupType() {
        return groupType;
    }

    public void setGroupType(MaterialGroupType groupType) {
        this.groupType = groupType;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    @Override
    public String toString() {
        return "Material{" +
                "index=" + index +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", printableArea=" + printableArea +
                ", productionTime=" + productionTime +
                ", groupType=" + groupType +
                ", materialType=" + materialType +
                '}';
    }
}