package com.paperfox.order.services;

import com.paperfox.order.models.products.PrintingProduct;
import com.paperfox.order.services.calculation.StandardShapeStickerCalculator;
import org.springframework.stereotype.Service;

@Service
public class PriceCalculatorService implements IPriceCalculatorService {

    @Override
    public PrintingProduct calculate(PrintingProduct product) {
        // todo factory for Calculator by product type

        return new StandardShapeStickerCalculator().calcProduct(product);
    }
}
