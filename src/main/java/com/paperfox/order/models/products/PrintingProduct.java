package com.paperfox.order.models.products;

import com.paperfox.order.models.Material;
import com.paperfox.order.models.ProductSize;
import com.paperfox.order.models.types.CuttingType;
import com.paperfox.order.models.types.MaterialGroups;
import com.paperfox.order.models.types.ProductType;

public class PrintingProduct {
    private ProductType productType;
    private double quantity;
    private double totalPrice;
    private double cutPrice;
    private double printPrice;
    private double quantityPerSheet;
    private ProductSize size;
    private Material material;
    private CuttingType cuttingType;

    public PrintingProduct(ProductType productType, double quantity, double totalPrice, double cutPrice,
                           double printPrice, double quantityPerSheet, ProductSize size, Material material, CuttingType cuttingType) {
        this.productType = productType;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.cutPrice = cutPrice;
        this.printPrice = printPrice;
        this.quantityPerSheet = quantityPerSheet;
        this.size = size;
        this.material = material;
        this.cuttingType = cuttingType;
    }

    public ProductType getProductType() {
        return productType;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getCutPrice() {
        return cutPrice;
    }

    public double getPrintPrice() {
        return printPrice;
    }

    public double getQuantityPerSheet() {
        return quantityPerSheet;
    }

    public ProductSize getSize() {
        return size;
    }

    public Material getMaterial() {
        return material;
    }

    public CuttingType getCuttingType() {
        return cuttingType;
    }

    @Override
    public String toString() {
        return "PrintingProduct{" +
                "productType=" + productType +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                ", cutPrice=" + cutPrice +
                ", printPrice=" + printPrice +
                ", quantityPerSheet=" + quantityPerSheet +
                ", size=" + size +
                ", material=" + material +
                ", cuttingType=" + cuttingType +
                '}';
    }
}
