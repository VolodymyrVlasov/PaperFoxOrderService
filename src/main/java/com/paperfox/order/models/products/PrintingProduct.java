package com.paperfox.order.models.products;

import com.paperfox.order.models.ProductGroup;
import com.paperfox.order.models.materials.Material;
import com.paperfox.order.models.ProductSize;
import com.paperfox.order.models.types.CuttingType;
import com.paperfox.order.models.types.ProductType;

import java.io.File;
import java.util.GregorianCalendar;

public class PrintingProduct {
    private ProductGroup productGroup;
    private double quantity;
    private double totalPrice;
    private double cutPrice;
    private double printPrice;
    private double quantityPerSheet;
    private ProductSize productSize;
    private Material material;
    private CuttingType cuttingType;
    private GregorianCalendar productionTime;
    private String productToken;
    private String previewFileName;

    public PrintingProduct(ProductGroup productGroup, double quantity, double totalPrice, double cutPrice,
                           double printPrice, double quantityPerSheet, ProductSize productSize, Material material,
                           CuttingType cuttingType, GregorianCalendar productionTime, String productToken, String previewFileName) {
        this.productGroup = productGroup;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.cutPrice = cutPrice;
        this.printPrice = printPrice;
        this.quantityPerSheet = quantityPerSheet;
        this.productSize = productSize;
        this.material = material;
        this.cuttingType = cuttingType;
        this.productionTime = productionTime;
        this.productToken = productToken;
        this.previewFileName = previewFileName;
    }

    public ProductGroup getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(ProductGroup productGroup) {
        this.productGroup = productGroup;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
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

    public double getQuantityPerSheet() {
        return quantityPerSheet;
    }

    public void setQuantityPerSheet(double quantityPerSheet) {
        this.quantityPerSheet = quantityPerSheet;
    }

    public ProductSize getProductSize() {
        return productSize;
    }

    public void setProductSize(ProductSize productSize) {
        this.productSize = productSize;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public CuttingType getCuttingType() {
        return cuttingType;
    }

    public void setCuttingType(CuttingType cuttingType) {
        this.cuttingType = cuttingType;
    }

    public GregorianCalendar getProductionTime() {
        return productionTime;
    }

    public void setProductionTime(GregorianCalendar productionTime) {
        this.productionTime = productionTime;
    }

    public String getProductToken() {
        return productToken;
    }

    public void setProductToken(String productToken) {
        this.productToken = productToken;
    }

    public String getPreviewFileName() {
        return previewFileName;
    }

    public void setPreviewFileName(String previewFileName) {
        this.previewFileName = previewFileName;
    }

    @Override
    public String toString() {
        return "\nPrintingProduct{" +
                "\n\tproductGroup=" + productGroup +
                "\n\tquantity=" + quantity +
                "\n\ttotalPrice=" + totalPrice +
                "\n\tcutPrice=" + cutPrice +
                "\n\tprintPrice=" + printPrice +
                "\n\tquantityPerSheet=" + quantityPerSheet +
                "\n\tproductSize=" + productSize +
                "\n\tmaterial=" + material +
                "\n\tcuttingType=" + cuttingType +
                "\n\tproductionTime=" + productionTime +
                "\n\tproductToken='" + productToken + '\'' +
                "\n\tpreviewFileName='" + previewFileName + '\'' +
                "\n}";
    }
}
