package com.paperfox.order.models.types;

import static com.paperfox.order.constants.Price.*;

public enum CuttingType {
    HAND_CUTTING(HAND_CUTTING_PRICE),
    PLOTTER_CUTTING(PLOTTER_CUTTING_PRICE),
    THROUGH_PLOTTER_CUTTING(THROUGH_PLOTTER_CUTTING_PRICE);

    public final double[] price;

    CuttingType(double[] price) {
        this.price = price;
    }
}
