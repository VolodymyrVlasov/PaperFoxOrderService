package com.paperfox.order.models.products;

import com.paperfox.order.models.Size;
import com.paperfox.order.models.types.MaterialType;

public abstract class PrintingProduct {
    private Size size;
    private int quantity;
    private double totalPrice;
    private MaterialType materialType;
    private double cutPrice;
    private double printPrice;

    public Size getSize() {
        return size;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public double getCutPrice() {
        return cutPrice;
    }

    public double getPrintPrice() {
        return printPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    public void setCutPrice(double cutPrice) {
        this.cutPrice = cutPrice;
    }

    public void setPrintPrice(double printPrice) {
        this.printPrice = printPrice;
    }
}
