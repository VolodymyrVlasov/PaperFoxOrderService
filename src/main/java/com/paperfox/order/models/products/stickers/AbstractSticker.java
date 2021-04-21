package com.paperfox.order.models.products.stickers;


import com.paperfox.order.models.ProductSize;
import com.paperfox.order.models.products.PrintingProduct;
import com.paperfox.order.models.types.CuttingType;
import com.paperfox.order.models.types.MaterialType;

public abstract class AbstractSticker extends PrintingProduct {

    public AbstractSticker(ProductSize size, double quantity, double totalPrice, MaterialType materialType, double cutPrice, double printPrice, CuttingType cuttingType) {
        super(size, quantity, totalPrice, materialType, cutPrice, printPrice, cuttingType);
    }
}
