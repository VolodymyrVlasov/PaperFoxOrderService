package com.paperfox.order.services.calculation;

import com.paperfox.order.models.ProductSize;
import com.paperfox.order.models.products.PrintingProduct;
import com.paperfox.order.models.products.stickers.RoundSticker;
import com.paperfox.order.models.types.MaterialType;

public class StandardShapeStickerCalculator extends AbstractCalculator implements ICalculator {

    @Override
    public PrintingProduct calcProduct(PrintingProduct product) {
        double quantityPerSheet = calculateQuantityPerSheet(product.getMaterialType(), product.getSize());
        System.out.println("наклеек на листе: " + quantityPerSheet + " шт.");

        double cutPerSheet = ((product.getSize().diameter / 1000) * 3.14) * quantityPerSheet;
        System.out.println("резки на листе: " + cutPerSheet + " м.п.");

        double quantitySheetsPerCirculation = Math.ceil(product.getQuantity() / quantityPerSheet);
        System.out.println("Листов в тираже: " + quantitySheetsPerCirculation + " шт.");

        double cutQuantityPerCirculation = Math.floor(cutPerSheet * quantitySheetsPerCirculation);
        System.out.println("резки в тираже: " + cutQuantityPerCirculation + " м.п.");

        double printPrice = calcPrintPrice(quantitySheetsPerCirculation, product.getMaterialType());
        System.out.println("Стоимость печати: " + quantitySheetsPerCirculation + " грн.");

        double cutPrice = calcCutPrice(cutQuantityPerCirculation, product.getCuttingType());
        System.out.println("Стоимость резки: " + cutPrice + " грн.");

        double totalPrice = printPrice + cutPrice;
        System.out.println("Стоимость тиража: " + totalPrice + " грн.");

        double stickersQuantityPerCirculation = quantityPerSheet * quantitySheetsPerCirculation;
        System.out.println("Наклеек в тираже: " + stickersQuantityPerCirculation + " шт.");
        return new RoundSticker(
                product.getSize(),
                stickersQuantityPerCirculation,
                totalPrice,
                product.getMaterialType(),
                cutPrice,
                printPrice,
                product.getCuttingType());
    }

    @Override
    public double calculateQuantityPerSheet(MaterialType materialType, ProductSize size) {
        double quantityPerSheet = 0;
        if (size.diameter != 0) {
            quantityPerSheet = ((Math.floor(materialType.printableSize.height / (size.diameter + 2))) *
                    (Math.floor(materialType.printableSize.width / (size.diameter + 2))));
        } else if (size.borderRadius != 0) {
            double horizontal = ((Math.floor(materialType.printableSize.width / (size.width + 2))) *
                    (Math.floor(materialType.printableSize.height / (size.height + 2))));
            double vertical = ((Math.floor((size.height + 2) / materialType.printableSize.width)) *
                    (Math.floor(materialType.printableSize.height / (size.width + 2))));
            quantityPerSheet = Math.max(horizontal, vertical);
        } else {
            double horizontal = (Math.floor(materialType.printableSize.width / (size.width))) *
                    (Math.floor(materialType.printableSize.height / (size.height)));
            double vertical = ((Math.floor(materialType.printableSize.width / (size.height))) *
                    (Math.floor(materialType.printableSize.height / (size.width))));
            quantityPerSheet = Math.max(horizontal, vertical);
        }
        return quantityPerSheet;
    }
}
