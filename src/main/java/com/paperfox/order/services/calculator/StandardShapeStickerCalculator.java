package com.paperfox.order.services.calculator;

import com.paperfox.order.controllers.OrderController;
import com.paperfox.order.models.ProductGroup;
import com.paperfox.order.models.ProductSize;
import com.paperfox.order.models.Size;
import com.paperfox.order.models.products.PrintingProduct;
import com.paperfox.order.models.types.CuttingType;
import com.paperfox.order.models.types.ProductType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.GregorianCalendar;

public class StandardShapeStickerCalculator extends AbstractCalculator {
    private static final String TAG = "STANDARD SHAPE STICKER CALCULATOR";
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private final double BLEED = 4.0;

    public PrintingProduct calcProduct(PrintingProduct product) throws Exception {
        double amountPerSheet = calculateQuantityPerSheet(product);
        double cutPerSheet = calculateCutPerSheet(product, amountPerSheet);
        double quantitySheetsPerCirculation = Math.round(product.getQuantity() / amountPerSheet);
        double cutQuantityPerCirculation = cutPerSheet * quantitySheetsPerCirculation;
        double printPrice = calcPrintPrice(quantitySheetsPerCirculation, product.getMaterial());
        double cutPrice = Math.round(calcCutPrice(cutQuantityPerCirculation, product.getCuttingType()));
        double totalPrice = printPrice + Math.ceil(cutPrice);
        double stickersQuantityPerCirculation = amountPerSheet * quantitySheetsPerCirculation;
        GregorianCalendar productionTime = getProductionDate(product.getMaterial().getProductionTime());
        PrintingProduct calculatedProduct = new PrintingProduct(
                product.getProductGroup(), stickersQuantityPerCirculation, totalPrice, cutPrice, printPrice,
                amountPerSheet, product.getProductSize(), product.getMaterial(), product.getCuttingType(),
                productionTime, product.getProductToken(), product.getPreviewFileName());
        System.out.println(calculatedProduct);
        return calculatedProduct;
    }

    private double calculateQuantityPerSheet(PrintingProduct product) throws Exception {
        double bleedForA4 = 14;
        double materialWidth = product.getMaterial().getPrintableArea().width;
        double materialHeight = product.getCuttingType() == CuttingType.PLOTTER_CUTTING_A4 ?
                product.getMaterial().getPrintableArea().height - bleedForA4 : product.getMaterial().getPrintableArea().height;
        ProductSize printingSize = new ProductSize(
                product.getProductSize().width != 0 ? product.getProductSize().width + BLEED : product.getProductSize().width,
                product.getProductSize().height != 0 ? product.getProductSize().height + BLEED : product.getProductSize().height,
                product.getProductSize().diameter != 0 ? product.getProductSize().diameter + BLEED : product.getProductSize().diameter,
                product.getProductSize().borderRadius);
        if (product.getProductGroup().getProductType() == ProductType.ROUND) {
            return Math.floor(materialHeight / printingSize.diameter) * Math.floor(materialWidth / printingSize.diameter);
        } else if (product.getProductGroup().getProductType() == ProductType.SQUARED) {
            if (product.getProductSize().borderRadius != 0) {
                return Math.max(Math.floor(materialWidth / printingSize.width) * Math.floor(materialHeight / printingSize.height),
                        Math.floor(materialWidth / printingSize.height) * Math.floor(materialHeight / printingSize.width));
            }
            return Math.max(Math.floor(materialWidth / printingSize.width) * Math.floor(materialHeight / (printingSize.height - BLEED)),
                    Math.floor(materialWidth / printingSize.height) * Math.floor(materialHeight / (printingSize.width - BLEED)));
        } else if (product.getProductGroup().getProductType() == ProductType.FIGURE) {
            return Math.max(Math.floor(materialWidth / printingSize.width) * Math.floor(materialHeight / printingSize.height),
                    Math.floor(materialWidth / printingSize.height) * Math.floor(materialHeight / printingSize.width));
        }
        throw new Exception("No such product type");
    }

    public double calculateCutPerSheet(PrintingProduct product, double quantityPerSheet) throws Exception {
        if (product.getProductGroup().getProductType() == ProductType.ROUND) {
            return product.getProductSize().diameter * 0.00314 * quantityPerSheet;
        } else if (product.getProductGroup().getProductType() == ProductType.SQUARED) {
            if (product.getProductSize().borderRadius != 0) {
                double perimeter = (product.getProductSize().width + product.getProductSize().height) * 2;
                double cornersLinealLength = product.getProductSize().borderRadius * 8;
                double cornersRoundingLength = product.getProductSize().borderRadius * 2 * 3.14;
                return (perimeter - cornersLinealLength + cornersRoundingLength) * quantityPerSheet * 0.001;
            }
            return (product.getProductSize().width + product.getProductSize().height) * quantityPerSheet * 0.001;
        } else if (product.getProductGroup().getProductType() == ProductType.FIGURE) {
            return (product.getProductSize().width + product.getProductSize().height) * 4.5 * 0.0001;
        }
        throw new Exception("No such product type");
    }
}
