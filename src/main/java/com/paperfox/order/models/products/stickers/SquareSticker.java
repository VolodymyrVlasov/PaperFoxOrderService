package com.paperfox.order.models.products.stickers;

import com.paperfox.order.models.ProductSize;
import com.paperfox.order.models.types.CuttingType;
import com.paperfox.order.models.types.MaterialType;

public class SquareSticker extends AbstractSticker {

    public SquareSticker(ProductSize size, double quantity, double totalPrice, MaterialType materialType, double cutPrice, double printPrice, CuttingType cuttingType) {
        super(size, quantity, totalPrice, materialType, cutPrice, printPrice, cuttingType);
    }
}
