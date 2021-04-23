package com.paperfox.order.models.types;

import static com.paperfox.order.constants.Price.PLOTTER_CUTTING_PRICE;
import static com.paperfox.order.constants.Price.THROUGH_PLOTTER_CUTTING_PRICE;

public enum CuttingType {
    //    HAND_CUTTING(HAND_CUTTING_PRICE,""),
    PLOTTER_CUTTING(PLOTTER_CUTTING_PRICE, "Контурна на аркушах А3"),
    THROUGH_PLOTTER_CUTTING(THROUGH_PLOTTER_CUTTING_PRICE, "Наскрізна (кожна окремо)");

    public final double[] price;
    public final String name;


    CuttingType(double[] price, String name) {
        this.price = price;
        this.name = name;
    }
}
