package com.paperfox.order.services.calculator;

import com.paperfox.order.constants.Price;
import com.paperfox.order.models.Material;
import com.paperfox.order.models.types.CuttingType;

import java.util.Calendar;
import java.util.GregorianCalendar;

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

    public GregorianCalendar getProductionDate(int productionTime) {
        GregorianCalendar today = new GregorianCalendar();
        GregorianCalendar deadLine = new GregorianCalendar();
        deadLine.set(Calendar.HOUR_OF_DAY, 18);
        deadLine.set(Calendar.MINUTE, 30);
        deadLine.set(Calendar.SECOND, 00);

        if (productionTime == 1) {
            if (today.get(Calendar.HOUR_OF_DAY) > 12 && today.get(Calendar.DAY_OF_WEEK)
                    != Calendar.SATURDAY && today.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                productionTime++;
                deadLine.set(Calendar.HOUR_OF_DAY, 14);
                if (today.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
                    deadLine.set(Calendar.HOUR_OF_DAY, 18);
                    productionTime--;
                } else if (today.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                    if (today.get(Calendar.HOUR_OF_DAY) > 16) {
                        deadLine.set(Calendar.HOUR_OF_DAY, 18);
                    }
                    productionTime++;
                }
            }
            if (today.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                productionTime++;
            }
        }
        if (productionTime > 1) {
            deadLine.set(Calendar.HOUR_OF_DAY, 14);
            if (today.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
                productionTime += 2;
            } else if (today.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                deadLine.set(Calendar.HOUR_OF_DAY, 18);
                productionTime++;
            } else if (today.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                productionTime++;
            }
        }
        deadLine.set(Calendar.DATE, today.get(Calendar.DATE) + productionTime);
        return deadLine;
    }
}
