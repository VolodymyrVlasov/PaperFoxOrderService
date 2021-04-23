package com.paperfox.order.services.calculation;

import com.paperfox.order.models.Material;
import com.paperfox.order.models.ProductSize;
import com.paperfox.order.models.Size;
import com.paperfox.order.models.products.PrintingProduct;
import com.paperfox.order.models.types.MaterialGroups;

public interface ICalculator {

    PrintingProduct calcProduct(PrintingProduct product);

    double calculateQuantityPerSheet(Size printableArea, ProductSize size);
}
