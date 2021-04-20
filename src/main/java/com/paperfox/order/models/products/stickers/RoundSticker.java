package com.paperfox.order.models.products.stickers;

import com.paperfox.order.services.AbstractCalculator;

public class RoundSticker extends AbstractSticker{


    public RoundSticker(AbstractCalculator calculator) {
        super.setCutPrice(calculator.getCutPrice());
        super.setPrintPrice(calculator.getPrintPrice());
        super.setTotalPrice(calculator.getTotalPrice());
    }
}
