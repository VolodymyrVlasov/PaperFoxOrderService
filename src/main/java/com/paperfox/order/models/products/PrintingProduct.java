package com.paperfox.order.models.products;

import com.paperfox.order.models.ProductSize;
import com.paperfox.order.models.types.CuttingType;
import com.paperfox.order.models.types.MaterialType;

public class PrintingProduct {
    // todo ProductType enum
    private ProductSize size;
    private double quantity;
    private double totalPrice;
    private MaterialType materialType;
    private double cutPrice;
    private double printPrice;
    private CuttingType cuttingType;
    private double quantityPerSheet;

    public PrintingProduct(ProductSize size, double quantity, double totalPrice, MaterialType materialType,
                           double cutPrice, double printPrice, CuttingType cuttingType, double quantityPerSheet) {
        this.size = size;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.materialType = materialType;
        this.cutPrice = cutPrice;
        this.printPrice = printPrice;
        this.cuttingType = cuttingType;
        this.quantityPerSheet = quantityPerSheet;
    }

    public double getQuantityPerSheet() {
        return quantityPerSheet;
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

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    public double getCutPrice() {
        return cutPrice;
    }

    public void setCutPrice(double cutPrice) {
        this.cutPrice = cutPrice;
    }

    public double getPrintPrice() {
        return printPrice;
    }

    public void setPrintPrice(double printPrice) {
        this.printPrice = printPrice;
    }

    @Override
    public String toString() {
        return "PrintingProduct{" +
                "size=" + size +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                ", materialType=" + materialType +
                ", cutPrice=" + cutPrice +
                ", printPrice=" + printPrice +
                ", cuttingType=" + cuttingType +
                ", quantityPerSheet=" + quantityPerSheet +
                '}';
    }
}
