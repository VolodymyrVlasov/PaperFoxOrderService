package com.paperfox.order.services;

import com.paperfox.order.controllers.OrderController;
import com.paperfox.order.models.products.PrintingProduct;
import com.paperfox.order.services.calculation.StandardShapeStickerCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PriceCalculatorService implements IPriceCalculatorService {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Override
    public PrintingProduct calculate(PrintingProduct product) throws Exception {

        switch (product.getProductType()) {
            case DRAWING:
                logger.info("Calc request: " + product.getProductType());
                break;
            case BIZ_CARD:
                logger.info("Calc request: " + product.getProductType());
                break;
            case POSTER:
                logger.info("Calc request: " + product.getProductType());
                break;
            case MAGNET:
                logger.info("Calc request: " + product.getProductType());
                break;
            case FLYER:
                logger.info("Calc request: " + product.getProductType());
                break;
            case STICKER:
                logger.info("Calc request" + product.getProductType());
                return new StandardShapeStickerCalculator().calcProduct(product);
            case PHOTO:
        }

        throw new Exception("Calculation for " + product.getProductType() + " product type is not defined,");
    }
}
