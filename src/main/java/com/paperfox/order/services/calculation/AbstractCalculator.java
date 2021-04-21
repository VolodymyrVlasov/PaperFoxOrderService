package com.paperfox.order.services.calculation;

import com.paperfox.order.constants.Price;
import com.paperfox.order.models.types.CuttingType;
import com.paperfox.order.models.types.MaterialType;

public abstract class AbstractCalculator {
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

    public double calcPrintPrice(double quantitySheetsPerCirculation, MaterialType materialType) {
        if (quantitySheetsPerCirculation < Price.PRINT_CIRCULATION_INDEX[0]) {
            return quantitySheetsPerCirculation * materialType.price[0];
        } else if (quantitySheetsPerCirculation < Price.PRINT_CIRCULATION_INDEX[1]) {
            return quantitySheetsPerCirculation * materialType.price[1];
        } else if (quantitySheetsPerCirculation < Price.PRINT_CIRCULATION_INDEX[2]) {
            return quantitySheetsPerCirculation * materialType.price[2];
        } else if (quantitySheetsPerCirculation < Price.PRINT_CIRCULATION_INDEX[3]) {
            return quantitySheetsPerCirculation * materialType.price[4];
        } else {
            return quantitySheetsPerCirculation * materialType.price[4];
        }
    }
}
