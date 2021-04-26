package com.paperfox.order.services.calculator;

import com.paperfox.order.fakeDB.PriceFakeDB;
import com.paperfox.order.fakeDB.TimeScopesFakeDB;
import com.paperfox.order.models.materials.Material;
import com.paperfox.order.models.types.CuttingType;
import com.paperfox.order.repositories.calculator.FakeRepository;

import java.util.Calendar;
import java.util.GregorianCalendar;

public abstract class AbstractCalculator {

    // todo implement Date calculation
    public double calcCutPrice(double cutQuantityPerCirculation, CuttingType cuttingType) {
        if (cutQuantityPerCirculation < PriceFakeDB.CUT_CIRCULATION_INDEX[0]) {
            return cutQuantityPerCirculation * cuttingType.price[0];
        } else if (cutQuantityPerCirculation < PriceFakeDB.CUT_CIRCULATION_INDEX[1]) {
            return cutQuantityPerCirculation * cuttingType.price[1];
        } else if (cutQuantityPerCirculation < PriceFakeDB.CUT_CIRCULATION_INDEX[2]) {
            return cutQuantityPerCirculation * cuttingType.price[2];
        } else if (cutQuantityPerCirculation > PriceFakeDB.CUT_CIRCULATION_INDEX[3]) {
            return cutQuantityPerCirculation * cuttingType.price[3];
        } else {
            return 0d; // fixme
        }
    }

    public double calcPrintPrice(double quantitySheetsPerCirculation, Material material) {
        if (quantitySheetsPerCirculation < PriceFakeDB.PRINT_CIRCULATION_INDEX[0]) {
            return quantitySheetsPerCirculation * material.getPrice()[0];
        } else if (quantitySheetsPerCirculation < PriceFakeDB.PRINT_CIRCULATION_INDEX[1]) {
            return quantitySheetsPerCirculation * material.getPrice()[1];
        } else if (quantitySheetsPerCirculation < PriceFakeDB.PRINT_CIRCULATION_INDEX[2]) {
            return quantitySheetsPerCirculation * material.getPrice()[2];
        } else if (quantitySheetsPerCirculation < PriceFakeDB.PRINT_CIRCULATION_INDEX[3]) {
            return quantitySheetsPerCirculation * material.getPrice()[4];
        } else {
            return quantitySheetsPerCirculation * material.getPrice()[4];
        }
    }

    public GregorianCalendar getProductionDate(int productionTime) {
        // Первое время готовности
        int firstTimeHour = FakeRepository.getFirstDeadlineTime().get("HOUR_OF_DAY");
        int firstTimeMinute = FakeRepository.getFirstDeadlineTime().get("MINUTE");

        // Второе время готовности
        int secondTimeHour = FakeRepository.getSecondDeadlineTime().get("HOUR_OF_DAY");
        int secondTimeMinute = FakeRepository.getSecondDeadlineTime().get("MINUTE");

        GregorianCalendar today = new GregorianCalendar();
        GregorianCalendar deadLine = new GregorianCalendar();

        // default time
        deadLine.set(Calendar.HOUR_OF_DAY, secondTimeHour);
        deadLine.set(Calendar.MINUTE, secondTimeMinute);
        deadLine.set(Calendar.SECOND, 0);

        if (productionTime == 1) {
            switch (today.get(Calendar.DAY_OF_WEEK)) {
                case Calendar.MONDAY:
                case Calendar.TUESDAY:
                case Calendar.WEDNESDAY:
                case Calendar.THURSDAY:
                    if (today.get(Calendar.HOUR_OF_DAY) < 12) {
                        deadLine.set(Calendar.DATE, today.get(Calendar.DAY_OF_MONTH));
                        deadLine.set(Calendar.HOUR_OF_DAY, secondTimeHour);
                        deadLine.set(Calendar.MINUTE, secondTimeMinute);
                    } else if (today.get(Calendar.HOUR_OF_DAY) >= 12 && today.get(Calendar.HOUR_OF_DAY) < 16) {
                        deadLine.set(Calendar.DATE, today.get(Calendar.DAY_OF_MONTH) + 1);
                        deadLine.set(Calendar.HOUR_OF_DAY, firstTimeHour);
                        deadLine.set(Calendar.MINUTE, firstTimeMinute);
                    } else {
                        deadLine.set(Calendar.DATE, today.get(Calendar.DAY_OF_MONTH) + 1);
                        deadLine.set(Calendar.HOUR_OF_DAY, secondTimeHour);
                        deadLine.set(Calendar.MINUTE, secondTimeMinute);
                    }
                    break;
                case Calendar.FRIDAY:
                    if (today.get(Calendar.HOUR_OF_DAY) < 12) {
                        deadLine.set(Calendar.DATE, today.get(Calendar.DAY_OF_MONTH));
                        deadLine.set(Calendar.HOUR_OF_DAY, secondTimeHour);
                        deadLine.set(Calendar.MINUTE, secondTimeMinute);
                    } else if (today.get(Calendar.HOUR_OF_DAY) >= 12 && today.get(Calendar.HOUR_OF_DAY) < 16) {
                        deadLine.set(Calendar.DATE, today.get(Calendar.DATE) + 3);
                        deadLine.set(Calendar.HOUR_OF_DAY, firstTimeHour);
                        deadLine.set(Calendar.MINUTE, firstTimeMinute);
                    } else {
                        deadLine.set(Calendar.DATE, today.get(Calendar.DATE) + 3);
                        deadLine.set(Calendar.HOUR_OF_DAY, secondTimeHour);
                        deadLine.set(Calendar.MINUTE, secondTimeMinute);
                    }
                    break;
                case Calendar.SATURDAY:
                    deadLine.set(Calendar.DATE, today.get(Calendar.DATE) + 2);
                    deadLine.set(Calendar.HOUR_OF_DAY, secondTimeHour);
                    deadLine.set(Calendar.MINUTE, secondTimeMinute);
                    break;
                case Calendar.SUNDAY:
                    deadLine.set(Calendar.DATE, today.get(Calendar.DATE) + 1);
                    deadLine.set(Calendar.HOUR_OF_DAY, secondTimeHour);
                    deadLine.set(Calendar.MINUTE, secondTimeMinute);
            }
        }

        if (productionTime == 2) {
            switch (today.get(Calendar.DAY_OF_WEEK)) {
                case Calendar.MONDAY:
                case Calendar.TUESDAY:
                case Calendar.WEDNESDAY:
                    if (today.get(Calendar.HOUR_OF_DAY) < 12) {
                        deadLine.set(Calendar.DAY_OF_MONTH, today.get(Calendar.DATE) + 1);
                        deadLine.set(Calendar.HOUR_OF_DAY, firstTimeHour);
                        deadLine.set(Calendar.MINUTE, firstTimeMinute);
                    } else if (today.get(Calendar.HOUR_OF_DAY) >= 12 && today.get(Calendar.HOUR_OF_DAY) < 16) {
                        deadLine.set(Calendar.DATE, today.get(Calendar.DAY_OF_MONTH) + 2);
                        deadLine.set(Calendar.HOUR_OF_DAY, firstTimeHour);
                        deadLine.set(Calendar.MINUTE, firstTimeMinute);
                    } else {
                        deadLine.set(Calendar.DATE, today.get(Calendar.DAY_OF_MONTH) + 2);
                        deadLine.set(Calendar.HOUR_OF_DAY, secondTimeHour);
                        deadLine.set(Calendar.MINUTE, secondTimeMinute);
                    }
                    break;
                case Calendar.THURSDAY:
                    if (today.get(Calendar.HOUR_OF_DAY) < 12) {
                        deadLine.set(Calendar.DATE, today.get(Calendar.DATE) + 1);
                        deadLine.set(Calendar.HOUR_OF_DAY, secondTimeHour);
                        deadLine.set(Calendar.MINUTE, secondTimeMinute);
                    } else {
                        deadLine.set(Calendar.DATE, today.get(Calendar.DAY_OF_MONTH) + 4);
                        deadLine.set(Calendar.HOUR_OF_DAY, firstTimeHour);
                        deadLine.set(Calendar.MINUTE, firstTimeMinute);
                    }
                    break;
                case Calendar.FRIDAY:
                    if (today.get(Calendar.HOUR_OF_DAY) < 12) {
                        deadLine.set(Calendar.DATE, today.get(Calendar.DAY_OF_MONTH) + 3);
                        deadLine.set(Calendar.HOUR_OF_DAY, secondTimeHour);
                        deadLine.set(Calendar.MINUTE, secondTimeMinute);
                    } else {
                        deadLine.set(Calendar.DATE, today.get(Calendar.DAY_OF_MONTH) + 4);
                        deadLine.set(Calendar.HOUR_OF_DAY, secondTimeHour);
                        deadLine.set(Calendar.MINUTE, secondTimeMinute);
                    }
                    break;
                case Calendar.SATURDAY:
                    deadLine.set(Calendar.DATE, today.get(Calendar.DATE) + 3);
                    deadLine.set(Calendar.HOUR_OF_DAY, firstTimeHour);
                    deadLine.set(Calendar.MINUTE, firstTimeMinute);
                    break;
                case Calendar.SUNDAY:
                    deadLine.set(Calendar.DATE, today.get(Calendar.DATE) + 2);
                    deadLine.set(Calendar.HOUR_OF_DAY, firstTimeHour);
                    deadLine.set(Calendar.MINUTE, firstTimeMinute);
            }
        }
        return deadLine;

//        GregorianCalendar today = new GregorianCalendar();
//        GregorianCalendar deadLine = new GregorianCalendar();
//        deadLine.set(Calendar.HOUR_OF_DAY, 18);
//        deadLine.set(Calendar.MINUTE, 30);
//        deadLine.set(Calendar.SECOND, 00);
//
//        if (productionTime == 1) {
//            if (today.get(Calendar.HOUR_OF_DAY) > 12 && today.get(Calendar.DAY_OF_WEEK)
//                    != Calendar.SATURDAY && today.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
//                productionTime++;
//                deadLine.set(Calendar.HOUR_OF_DAY, 14);
//                if (today.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
//                    deadLine.set(Calendar.HOUR_OF_DAY, 18);
//                    productionTime--;
//                } else if (today.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
//                    if (today.get(Calendar.HOUR_OF_DAY) > 16) {
//                        deadLine.set(Calendar.HOUR_OF_DAY, 18);
//                    }
//                    productionTime++;
//                }
//            }
//            if (today.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
//                productionTime++;
//            }
//        }
//        if (productionTime > 1) {
//            deadLine.set(Calendar.HOUR_OF_DAY, 14);
//            if (today.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
//                productionTime += 2;
//            } else if (today.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
//                deadLine.set(Calendar.HOUR_OF_DAY, 18);
//                productionTime++;
//            } else if (today.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
//                productionTime++;
//            }
//        }
//        deadLine.set(Calendar.DATE, today.get(Calendar.DATE) + productionTime);
//        return deadLine;
    }
}
