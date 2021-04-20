package com.paperfox.order.services;

import com.paperfox.order.models.products.PrintingProduct;
import com.paperfox.order.models.products.stickers.RoundSticker;
import org.springframework.stereotype.Service;

@Service
public class PriceCalculatorService extends AbstractCalculator implements IPriceCalculatorService {

    public PriceCalculatorService(double quantityPerSheet, double quantitySheetsPerCirculation, double cutPerSheet, double cutQuantityPerCirculation, double printPrice, double cutPrice, double totalPrice) {
        super(quantityPerSheet, quantitySheetsPerCirculation, cutPerSheet, cutQuantityPerCirculation, printPrice, cutPrice, totalPrice);
    }

    @Override
    public PrintingProduct calculate(PrintingProduct product) {
        // todo factory for product type
        return calcRoundSticker((RoundSticker) product);
    }
}
