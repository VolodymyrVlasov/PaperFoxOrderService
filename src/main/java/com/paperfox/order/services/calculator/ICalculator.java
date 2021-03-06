package com.paperfox.order.services.calculator;

import com.paperfox.order.models.ProductSize;
import com.paperfox.order.models.Size;
import com.paperfox.order.models.products.PrintingProduct;

import java.util.Calendar;

public interface ICalculator {

    // todo make Date caclculation method

    PrintingProduct calcProduct(PrintingProduct product) throws Exception;

    double calculateQuantityPerSheet(Size printableArea, ProductSize size);
    double calculateCutPerSheet(ProductSize size, double quantityPerSheet);


}
