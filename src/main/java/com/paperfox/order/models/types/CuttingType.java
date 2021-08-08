package com.paperfox.order.models.types;

import com.paperfox.order.fakeDB.PriceFakeDB;
import com.paperfox.order.models.materials.Price;

import java.util.List;

public enum CuttingType {
    PLOTTER_CUTTING_A3(PriceFakeDB.plotterCuttingPrice, 0),
    PLOTTER_CUTTING_A4(PriceFakeDB.plotterCuttingPrice, 1),
    THROUGH_PLOTTER_CUTTING(PriceFakeDB.throughPlotterCuttingPrice, 2);

    public final List<Price> price;
    public final int name;


    CuttingType(List<Price> price, int index) {
        this.price = price;
        this.name = index;
    }
}
