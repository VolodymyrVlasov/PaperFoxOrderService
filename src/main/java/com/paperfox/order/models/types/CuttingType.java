package com.paperfox.order.models.types;

import com.paperfox.order.fakeDB.PriceFakeDB;
import com.paperfox.order.models.materials.Price;

import java.util.List;

public enum CuttingType {
    //    HAND_CUTTING(HAND_CUTTING_PRICE,""),
    PLOTTER_CUTTING(PriceFakeDB.plotterCuttingPrice, "Контурна на аркушах А3"),
    THROUGH_PLOTTER_CUTTING(PriceFakeDB.throughPlotterCuttingPrice, "Наскрізна (кожна окремо)");

    public final List<Price> price;
    public final String name;


    CuttingType(List<Price> price, String name) {
        this.price = price;
        this.name = name;
    }
}
