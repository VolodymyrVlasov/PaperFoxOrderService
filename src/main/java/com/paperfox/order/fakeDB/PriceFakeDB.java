package com.paperfox.order.fakeDB;

import com.paperfox.order.models.materials.Price;

import java.util.ArrayList;
import java.util.List;

public class PriceFakeDB {
    public static final List<Price> raflatacPrice;
    public static final List<Price> raflatacLaminatedPrice;
    public static final List<Price> raflatacPetPrice;
    public static final List<Price> ritramaLaminatedPrice;
    public static final List<Price> ritramaTransparentPrice;

    public static final List<Price> plotterCuttingPrice;
    public static final List<Price> throughPlotterCuttingPrice;

    static {
        raflatacPrice = new ArrayList<>();
        raflatacPrice.add(new Price(11, 28d));
        raflatacPrice.add(new Price(21, 24d));
        raflatacPrice.add(new Price(51, 19d));
        raflatacPrice.add(new Price(101, 17d));
        raflatacPrice.add(new Price(100, 14d));

        raflatacLaminatedPrice = new ArrayList<>();
        raflatacLaminatedPrice.add(new Price(11, 52d));
        raflatacLaminatedPrice.add(new Price(21, 43d));
        raflatacLaminatedPrice.add(new Price(51, 36d));
        raflatacLaminatedPrice.add(new Price(101, 31d));
        raflatacLaminatedPrice.add(new Price(100, 26d));

        raflatacPetPrice = new ArrayList<>();
        raflatacPetPrice.add(new Price(11, 60d));
        raflatacPetPrice.add(new Price(21, 48d));
        raflatacPetPrice.add(new Price(51, 42d));
        raflatacPetPrice.add(new Price(101, 36d));
        raflatacPetPrice.add(new Price(100, 30d));

        ritramaLaminatedPrice = new ArrayList<>();
        ritramaLaminatedPrice.add(new Price(11, 70d));
        ritramaLaminatedPrice.add(new Price(21, 56d));
        ritramaLaminatedPrice.add(new Price(51, 49d));
        ritramaLaminatedPrice.add(new Price(101, 42d));
        ritramaLaminatedPrice.add(new Price(100, 35d));

        ritramaTransparentPrice = new ArrayList<>();
        ritramaTransparentPrice.add(new Price(11, 64d));
        ritramaTransparentPrice.add(new Price(21, 51d));
        ritramaTransparentPrice.add(new Price(51, 45d));
        ritramaTransparentPrice.add(new Price(101, 38d));
        ritramaTransparentPrice.add(new Price(100, 32d));

        /**** CUTTING PRICES ****/

        plotterCuttingPrice = new ArrayList<>();
        plotterCuttingPrice.add(new Price(10, 6d));            // <= 10
        plotterCuttingPrice.add(new Price(50, 4d));            // > 10 && <= 50
        plotterCuttingPrice.add(new Price(100, 3d));           // > 50 && <= 100
        plotterCuttingPrice.add(new Price(500, 2.5));          // > 100 && <= 500
        plotterCuttingPrice.add(new Price(501, 1.5));          // >= 501

        throughPlotterCuttingPrice = new ArrayList<>();
        throughPlotterCuttingPrice.add(new Price(10, 15d));
        throughPlotterCuttingPrice.add(new Price(50, 10d));
        throughPlotterCuttingPrice.add(new Price(100, 6d));
        throughPlotterCuttingPrice.add(new Price(500, 5d));
        throughPlotterCuttingPrice.add(new Price(501, 4d));
    }
}


