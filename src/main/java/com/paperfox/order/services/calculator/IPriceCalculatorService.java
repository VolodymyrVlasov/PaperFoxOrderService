package com.paperfox.order.services.calculator;

import com.paperfox.order.models.products.PrintingProduct;

public interface IPriceCalculatorService {

    PrintingProduct calculate(PrintingProduct product) throws Exception;


}
