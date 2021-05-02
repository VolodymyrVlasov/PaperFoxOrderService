package com.paperfox.order.services.calculator;

import com.paperfox.order.models.ProductSize;
import com.paperfox.order.models.Size;
import com.paperfox.order.models.products.PrintingProduct;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class StandardShapeStickerCalculator extends AbstractCalculator implements ICalculator {

    @Override
    public PrintingProduct calcProduct(PrintingProduct product) {
        double quantityPerSheet = calculateQuantityPerSheet(product.getMaterial().getPrintableArea(), product.getSize());
        double cutPerSheet = ((product.getSize().diameter / 1000) * 3.14) * quantityPerSheet;
        double quantitySheetsPerCirculation = Math.ceil(product.getQuantity() / quantityPerSheet);
        double cutQuantityPerCirculation = cutPerSheet * quantitySheetsPerCirculation;
        double printPrice = calcPrintPrice(quantitySheetsPerCirculation, product.getMaterial());
        double cutPrice = calcCutPrice(cutQuantityPerCirculation, product.getCuttingType());
        double totalPrice = printPrice + Math.ceil(cutPrice);
        double stickersQuantityPerCirculation = quantityPerSheet * quantitySheetsPerCirculation;

        return new PrintingProduct(product.getProductType(), stickersQuantityPerCirculation, totalPrice, cutPrice,
                printPrice, quantityPerSheet, product.getSize(), product.getMaterial(), product.getCuttingType(),
                getProductionDate(product.getMaterial().getProductionTime()));
    }


    @Override
    public double calculateQuantityPerSheet(Size printableArea, ProductSize size) {
        double quantityPerSheet = 0;
        if (size.diameter != 0) {
            quantityPerSheet = ((Math.floor(printableArea.height / (size.diameter + 2))) *
                    (Math.floor(printableArea.width / (size.diameter + 2))));
        } else if (size.borderRadius != 0) {
            double horizontal = ((Math.floor(printableArea.width / (size.width + 2))) *
                    (Math.floor(printableArea.height / (size.height + 2))));
            double vertical = ((Math.floor((size.height + 2) / printableArea.width)) *
                    (Math.floor(printableArea.height / (size.width + 2))));
            quantityPerSheet = Math.max(horizontal, vertical);
        } else {
            double horizontal = (Math.floor(printableArea.width / (size.width))) *
                    (Math.floor(printableArea.height / (size.height)));
            double vertical = ((Math.floor(printableArea.width / (size.height))) *
                    (Math.floor(printableArea.height / (size.width))));
            quantityPerSheet = Math.max(horizontal, vertical);
        }
        return quantityPerSheet;
    }



}
