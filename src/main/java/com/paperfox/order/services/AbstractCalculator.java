package com.paperfox.order.services;

import com.paperfox.order.constants.Price;
import com.paperfox.order.models.Size;
import com.paperfox.order.models.products.PrintingProduct;
import com.paperfox.order.models.products.stickers.RoundSticker;
import com.paperfox.order.models.products.stickers.SquareSticker;
import com.paperfox.order.models.types.*;

public class AbstractCalculator {
    private double quantityPerSheet;
    private double quantitySheetsPerCirculation;
    private double cutPerSheet;
    private double cutQuantityPerCirculation;
    private double printPrice;
    private double cutPrice;
    private double totalPrice;

    public AbstractCalculator(double quantityPerSheet, double quantitySheetsPerCirculation, double cutPerSheet, double cutQuantityPerCirculation, double printPrice, double cutPrice, double totalPrice) {
        this.quantityPerSheet = quantityPerSheet;
        this.quantitySheetsPerCirculation = quantitySheetsPerCirculation;
        this.cutPerSheet = cutPerSheet;
        this.cutQuantityPerCirculation = cutQuantityPerCirculation;
        this.printPrice = printPrice;
        this.cutPrice = cutPrice;
        this.totalPrice = totalPrice;
    }

    public PrintingProduct calcRoundSticker(RoundSticker sticker) {
        this.quantityPerSheet = this.calculateQuantityPerSheet(sticker.getMaterialType(), sticker.getSize());
        this.cutPerSheet = Math.ceil(sticker.getSize().diameter * 3.14) * this.quantityPerSheet;
        this.quantitySheetsPerCirculation = Math.ceil(sticker.getQuantity() / this.quantityPerSheet);
        this.cutQuantityPerCirculation = sticker.getQuantity() / this.cutPerSheet;
        this.printPrice = this.calcPrintPrice(this.quantitySheetsPerCirculation, sticker.getMaterialType());
        this.cutPrice = this.calcCutPrice(this.cutQuantityPerCirculation, sticker.getCuttingType());
        this.totalPrice = this.printPrice + this.cutPrice;
        return (PrintingProduct) new RoundSticker(this) ;
    }

    public double calcSquareSticker(SquareSticker sticker) {
        return 0;
    }

    private double calcCutPrice(double cutQuantityPerCirculation, CuttingType cuttingType) {
        if (cutQuantityPerCirculation < Price.CUT_CIRCULATION_INDEX[0]) {
            return cutQuantityPerCirculation * cuttingType.price[0];
        } else if (cutQuantityPerCirculation < Price.CUT_CIRCULATION_INDEX[1]) {
            return cutQuantityPerCirculation * cuttingType.price[1];
        } else if (cutQuantityPerCirculation < Price.CUT_CIRCULATION_INDEX[2]) {
            return cutQuantityPerCirculation * cuttingType.price[2];
        } else if (cutQuantityPerCirculation > Price.CUT_CIRCULATION_INDEX[3]) {
            return cutQuantityPerCirculation * cuttingType.price[3];
        } else {
            return 0d; // fixme
        }
    }

    private double calcPrintPrice(double quantitySheetsPerCirculation, MaterialType materialType) {
        if (quantitySheetsPerCirculation < Price.PRINT_CIRCULATION_INDEX[0]) {
            return quantitySheetsPerCirculation * materialType.price[0];
        } else if (quantitySheetsPerCirculation < Price.PRINT_CIRCULATION_INDEX[1]) {
            return quantitySheetsPerCirculation * materialType.price[1];
        } else if (quantitySheetsPerCirculation < Price.PRINT_CIRCULATION_INDEX[2]) {
            return quantitySheetsPerCirculation * materialType.price[2];
        } else if (quantitySheetsPerCirculation < Price.PRINT_CIRCULATION_INDEX[3]) {
            return quantitySheetsPerCirculation * materialType.price[4];
        } else if (quantitySheetsPerCirculation > Price.PRINT_CIRCULATION_INDEX[4]) {
            return quantitySheetsPerCirculation * materialType.price[4];
        } else {
            return 0d; // fixme
        }
    }

    private double calculateQuantityPerSheet(MaterialType materialType, Size size) {
        double quantityPerSheet = 0;
        if (size.diameter != 0) {
            quantityPerSheet = ((Math.floor((size.diameter + 2d) / materialType.printableSize.height)) *
                    (Math.floor((size.diameter + 2d) / materialType.printableSize.width)));
        } else if (size.borderRadius != 0) {
            double horizontal = ((Math.floor((size.width + 2d) / materialType.printableSize.width)) *
                    (Math.floor((size.height + 2d) / materialType.printableSize.height)));
            double vertical = ((Math.floor((size.height + 2d) / materialType.printableSize.width)) *
                    (Math.floor((size.width + 2d) / materialType.printableSize.height)));
            quantityPerSheet = Math.max(horizontal, vertical);
        } else {
            double horizontal = (double) ((Math.floor((size.width) / materialType.printableSize.width)) *
                    (Math.floor((size.height) / materialType.printableSize.height)));
            double vertical = ((Math.floor((size.height) / materialType.printableSize.width)) *
                    (Math.floor((size.width) / materialType.printableSize.height)));
            quantityPerSheet = Math.max(horizontal, vertical);
        }
        return quantityPerSheet;
    }

    public double getQuantityPerSheet() {
        return quantityPerSheet;
    }

    public double getQuantitySheetsPerCirculation() {
        return quantitySheetsPerCirculation;
    }

    public double getCutPerSheet() {
        return cutPerSheet;
    }

    public double getCutQuantityPerCirculation() {
        return cutQuantityPerCirculation;
    }

    public double getPrintPrice() {
        return printPrice;
    }

    public double getCutPrice() {
        return cutPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
}
