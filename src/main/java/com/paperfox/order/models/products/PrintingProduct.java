package com.paperfox.order.models.products;

import com.paperfox.order.models.ProductSize;
import com.paperfox.order.models.Size;
import com.paperfox.order.models.types.CuttingType;
import com.paperfox.order.models.types.MaterialType;
import org.springframework.beans.factory.annotation.Autowired;

public class PrintingProduct {
    // todo ProductType enum
    private ProductSize size;
    private double quantity;
    private double totalPrice;
    private MaterialType materialType;
    private double cutPrice;
    private double printPrice;
    private CuttingType cuttingType;

    public PrintingProduct(ProductSize size, double quantity, double totalPrice, MaterialType materialType, double cutPrice, double printPrice, CuttingType cuttingType) {
        this.size = size;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.materialType = materialType;
        this.cutPrice = cutPrice;
        this.printPrice = printPrice;
        this.cuttingType = cuttingType;
    }

    public ProductSize getSize() {
        return size;
    }

    public CuttingType getCuttingType() {
        return cuttingType;
    }

    public double getQuantity() {
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

    @Override
    public String toString() {
        return "\nPrintingProduct{" +
                "\nsize=" + size +
                ", \nquantity=" + quantity +
                ", \ntotalPrice=" + totalPrice +
                ", \nmaterialType=" + materialType +
                ", \ncutPrice=" + cutPrice +
                ", \nprintPrice=" + printPrice +
                "\n}";
    }
}
