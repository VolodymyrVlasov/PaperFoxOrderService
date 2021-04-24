package com.paperfox.order.services.calculator;

import com.paperfox.order.constants.Price;
import com.paperfox.order.models.Material;
import com.paperfox.order.models.types.CuttingType;

public abstract class AbstractCalculator {

    // todo implement Date calculation
    public double calcCutPrice(double cutQuantityPerCirculation, CuttingType cuttingType) {
        if (cutQuantityPerCirculation < Price.CUT_CIRCULATION_INDEX[0]) {
            return cutQuantityPerCirculation * cuttingType.price[0];
        } else if (cutQuantityPerCirculation < Price.CUT_CIRCULATION_INDEX[1]) {
            return cutQuantityPerCirculation * cuttingType.price[1];
        } else if (cutQuantityPerCirculation < Price.CUT_CIRCULATION_INDEX[2]) {
            return cutQuantityPerCirculation * cuttingType.price[2];
        } else if (cutQuantityPerCirculation > Price.CUT_CIRCULATION_INDEX[3]) {
            return cutQuantityPerCirculation * cuttingType.price[3];
        } else {
            return 0d; // fixme
        }
    }

    public double calcPrintPrice(double quantitySheetsPerCirculation, Material material) {
        if (quantitySheetsPerCirculation < Price.PRINT_CIRCULATION_INDEX[0]) {
            return quantitySheetsPerCirculation * material.getPrice()[0];
        } else if (quantitySheetsPerCirculation < Price.PRINT_CIRCULATION_INDEX[1]) {
            return quantitySheetsPerCirculation * material.getPrice()[1];
        } else if (quantitySheetsPerCirculation < Price.PRINT_CIRCULATION_INDEX[2]) {
            return quantitySheetsPerCirculation * material.getPrice()[2];
        } else if (quantitySheetsPerCirculation < Price.PRINT_CIRCULATION_INDEX[3]) {
            return quantitySheetsPerCirculation * material.getPrice()[4];
        } else {
            return quantitySheetsPerCirculation * material.getPrice()[4];
        }
    }
}
