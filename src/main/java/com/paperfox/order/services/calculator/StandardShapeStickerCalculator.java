package com.paperfox.order.services.calculator;

import com.paperfox.order.controllers.OrderController;
import com.paperfox.order.models.ProductSize;
import com.paperfox.order.models.Size;
import com.paperfox.order.models.products.PrintingProduct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StandardShapeStickerCalculator extends AbstractCalculator implements ICalculator {
    private static final String TAG = "STANDARD SHAPE STICKER CALCULATOR";
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Override
    public PrintingProduct calcProduct(PrintingProduct product) {
        double quantityPerSheet = calculateQuantityPerSheet(product.getMaterial().getPrintableArea(), product.getProductSize());
        double cutPerSheet = calculateCutPerSheet(product.getProductSize(), quantityPerSheet);
        double quantitySheetsPerCirculation = Math.ceil(product.getQuantity() / quantityPerSheet);
        double cutQuantityPerCirculation = cutPerSheet * quantitySheetsPerCirculation;
        double printPrice = calcPrintPrice(quantitySheetsPerCirculation, product.getMaterial());
        double cutPrice = Math.round(calcCutPrice(cutQuantityPerCirculation, product.getCuttingType()));
        double totalPrice = printPrice + Math.ceil(cutPrice);
        double stickersQuantityPerCirculation = quantityPerSheet * quantitySheetsPerCirculation;

        StringBuilder stringBuilder = new StringBuilder("\n");
        stringBuilder.append("Наклеек на листе: " + quantityPerSheet + " шт\n");
        stringBuilder.append("Порезки на листе: " + cutPerSheet + " м.п.\n");
        stringBuilder.append("Листов в тираже: " + quantitySheetsPerCirculation + " шт\n");
        stringBuilder.append("Порезки в тираже: " + cutQuantityPerCirculation + " м.п.\n");
        stringBuilder.append("Наклеек в тираже: " + stickersQuantityPerCirculation + " шт\n");
        stringBuilder.append("Стоимость печати: " + printPrice + " грн\n");
        stringBuilder.append("Стоимость порезки: " + cutPrice + " грн\n");
        stringBuilder.append("Общая стоимость: " + totalPrice + " грн\n");

//        logger.info(stringBuilder.toString());

        return new PrintingProduct(product.getProductGroup(), stickersQuantityPerCirculation, totalPrice, cutPrice,
                printPrice, quantityPerSheet, product.getProductSize(), product.getMaterial(), product.getCuttingType(),
                getProductionDate(product.getMaterial().getProductionTime()), product.getProductToken(), product.getPreviewFileName());
    }


    @Override
    public double calculateQuantityPerSheet(Size printableArea, ProductSize productSize) {
        double quantityPerSheet = 0;
        if (productSize.diameter != 0) {
            quantityPerSheet = ((Math.floor(printableArea.height / (productSize.diameter + 2))) *
                    (Math.floor(printableArea.width / (productSize.diameter + 2))));
        } else if (productSize.borderRadius != 0) {

            double horizontal = ((Math.floor(printableArea.width / (productSize.width + 2))) *
                    (Math.floor(printableArea.height / (productSize.height + 2))));

            double vertical = ((Math.floor(printableArea.width / (productSize.height + 2))) *
                    (Math.floor(printableArea.height / (productSize.width + 2))));

//            System.out.println("\nvertical: " + vertical + " psc/sheet, horizontal: " + horizontal + " psc/sheet\n");
            quantityPerSheet = Math.max(horizontal, vertical);
        } else {
            double horizontal = (Math.floor(printableArea.width / (productSize.width))) *
                    (Math.floor(printableArea.height / (productSize.height)));
            double vertical = ((Math.floor(printableArea.width / (productSize.height))) *
                    (Math.floor(printableArea.height / (productSize.width))));
            quantityPerSheet = Math.max(horizontal, vertical);
        }
        return quantityPerSheet;
    }

    @Override
    public double calculateCutPerSheet(ProductSize size, double quantityPerSheet) {
        double cutPerSheet = 0;
        if (size.diameter != 0) {
            cutPerSheet = size.diameter / 1000 * 3.14 * quantityPerSheet;
        } else if (size.borderRadius != 0) {
            double perimeter = (size.width + size.height) * 2;
            double cornersLinealLength = size.borderRadius * 8;
            double cornersRoundingLength = size.borderRadius * 2 * 3.14;

            cutPerSheet = (perimeter - cornersLinealLength + cornersRoundingLength)  / 1000 * quantityPerSheet;
        } else {
            cutPerSheet = (size.width + size.height) * quantityPerSheet / 1000;
        }
        return cutPerSheet;
    }
}
