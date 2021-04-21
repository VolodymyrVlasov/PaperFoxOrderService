package com.paperfox.order.models.products.stickers;

import com.paperfox.order.models.ProductSize;
import com.paperfox.order.models.types.CuttingType;
import com.paperfox.order.models.types.MaterialType;

public class RoundSticker extends AbstractSticker{
    public RoundSticker(ProductSize size, double quantity, double totalPrice, MaterialType materialType, double cutPrice, double printPrice, CuttingType cuttingType) {
        super(size, quantity, totalPrice, materialType, cutPrice, printPrice, cuttingType);
    }

//    public RoundSticker(AbstractCalculator calculator) {
//        super.setCutPrice(calculator.getCutPrice());
//        super.setPrintPrice(calculator.getPrintPrice());
//        super.setTotalPrice(calculator.getTotalPrice());
//    }
}
