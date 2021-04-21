package com.paperfox.order.services.calculation;

import com.paperfox.order.models.ProductSize;
import com.paperfox.order.models.products.PrintingProduct;
import com.paperfox.order.models.types.MaterialType;

public interface ICalculator {

    PrintingProduct  calcProduct(PrintingProduct product);

    double calculateQuantityPerSheet(MaterialType materialType, ProductSize size);
}
