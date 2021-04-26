package com.paperfox.order.fakeDB;

import com.paperfox.order.models.materials.MaterialType;
import com.paperfox.order.models.types.CuttingType;

import java.util.HashMap;
import java.util.Map;

class Price {
    Map<Integer, Double> price = new HashMap<>();

    public Price(Map<Integer, Double> price) {
        this.price = price;
    }

    public Map<Integer, Double> getPrice() {
        return price;
    }

    public void setPrice(Map<Integer, Double> price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Price{" +
                "price=" + price +
                '}';
    }
}

public class PriceFakeDB {
    public static final Map<MaterialType, Price> materialPrices = new HashMap<>();  //fake database of material prices
    public static final Map<CuttingType, Price> cuttingPrices = new HashMap<>();    //fake database of material prices

    static {
        Map<Integer, Double> raflatacPrice = new HashMap<>();
        raflatacPrice.put(11, 28d);
        raflatacPrice.put(21, 24d);
        raflatacPrice.put(51, 19d);
        raflatacPrice.put(101, 17d);
        raflatacPrice.put(100, 14d);
        materialPrices.put(MaterialType.RAFLATAC, new Price(raflatacPrice));

        Map<Integer, Double> raflatacLaminatedPrice = new HashMap<>();
        raflatacLaminatedPrice.put(11, 52d);
        raflatacLaminatedPrice.put(21, 43d);
        raflatacLaminatedPrice.put(51, 36d);
        raflatacLaminatedPrice.put(101, 31d);
        raflatacLaminatedPrice.put(100, 26d);
        materialPrices.put(MaterialType.RAFLATAC, new Price(raflatacLaminatedPrice));

        Map<Integer, Double> raflatacPetPrice = new HashMap<>();
        raflatacPetPrice.put(11, 60d);
        raflatacPetPrice.put(21, 48d);
        raflatacPetPrice.put(51, 42d);
        raflatacPetPrice.put(101, 36d);
        raflatacPetPrice.put(100, 30d);
        materialPrices.put(MaterialType.RAFLATAC, new Price(raflatacPetPrice));

        Map<Integer, Double> ritramaLaminatedPrice = new HashMap<>();
        ritramaLaminatedPrice.put(11, 70d);
        ritramaLaminatedPrice.put(21, 56d);
        ritramaLaminatedPrice.put(51, 49d);
        ritramaLaminatedPrice.put(101, 42d);
        ritramaLaminatedPrice.put(100, 35d);
        materialPrices.put(MaterialType.RAFLATAC, new Price(ritramaLaminatedPrice));

        Map<Integer, Double> ritramaTransparentPrice = new HashMap<>();
        ritramaTransparentPrice.put(11, 64d);
        ritramaTransparentPrice.put(21, 51d);
        ritramaTransparentPrice.put(51, 45d);
        ritramaTransparentPrice.put(101, 38d);
        ritramaTransparentPrice.put(100, 32d);
        materialPrices.put(MaterialType.RAFLATAC, new Price(ritramaTransparentPrice));

        /**** CUTTING PRICES ****/

        Map<Integer, Double> plotterCuttingPrice = new HashMap<>();
        plotterCuttingPrice.put(10, 6d);            // <= 10
        plotterCuttingPrice.put(50, 4d);            // > 10 && <= 50
        plotterCuttingPrice.put(100, 3d);           // > 50 && <= 100
        plotterCuttingPrice.put(250, 2.5);          // > 100 && <= 250
        plotterCuttingPrice.put(251, 1.5);          // >= 251
        cuttingPrices.put(CuttingType.PLOTTER_CUTTING, new Price(plotterCuttingPrice));

        Map<Integer, Double> throughPlotterCuttingPrice = new HashMap<>();
        throughPlotterCuttingPrice.put(10, 15d);
        throughPlotterCuttingPrice.put(50, 10d);
        throughPlotterCuttingPrice.put(100, 6d);
        throughPlotterCuttingPrice.put(250, 5d);
        throughPlotterCuttingPrice.put(251, 4d);
        cuttingPrices.put(CuttingType.PLOTTER_CUTTING, new Price(throughPlotterCuttingPrice));

    }

    public static final int[] RAFLATAC_PRINT_PRICE = new int[]{28, 24, 19, 17, 14};
    public static final int[] RAFLATAC_LAMINATED_PRINT_PRICE = new int[]{52, 43, 36, 31, 26};
    public static final int[] RAFLATAC_PET_PRINT_PRICE = new int[]{60, 48, 42, 36, 30};
    public static final int[] RITRAMA_LAMINATED_PRINT_PRICE = new int[]{70, 56, 49, 42, 35};
    public static final int[] RITRAMA_TRANSPARENT_PRINT_PRICE = new int[]{64, 51, 45, 38, 32};

    public static final double[] PLOTTER_CUTTING_PRICE = new double[]{6, 4, 3, 2.5, 1.5};
    public static final double[] THROUGH_PLOTTER_CUTTING_PRICE = new double[]{15, 10, 6, 5, 4};

    public static final int[] PRINT_CIRCULATION_INDEX = new int[]{11, 21, 51, 101, 100};        //тираж

    public static final int[] CUT_CIRCULATION_INDEX = new int[]{10, 50, 100, 250};              //тираж
}


