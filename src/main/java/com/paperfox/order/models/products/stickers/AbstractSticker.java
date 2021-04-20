package com.paperfox.order.models.products.stickers;


import com.paperfox.order.models.Size;
import com.paperfox.order.models.products.PrintingProduct;
import com.paperfox.order.models.types.CuttingType;
import com.paperfox.order.models.types.MaterialType;

public abstract class AbstractSticker extends PrintingProduct {
    private CuttingType cuttingType;

    public CuttingType getCuttingType() {
        return cuttingType;
    }
}
