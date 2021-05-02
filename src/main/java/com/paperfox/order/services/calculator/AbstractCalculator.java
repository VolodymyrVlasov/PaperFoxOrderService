package com.paperfox.order.services.calculator;

import com.paperfox.order.models.materials.Material;
import com.paperfox.order.models.materials.Price;
import com.paperfox.order.models.types.CuttingType;
import com.paperfox.order.repositories.calculator.FakeRepository;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public abstract class AbstractCalculator {

    // todo implement Date calculation
    public double calcCutPrice(double cutQuantityPerCirculation, CuttingType cuttingType) {
        List<Price> price = cuttingType.price;

        if (cutQuantityPerCirculation <= price.get(0).getCirculation()) {
            return cutQuantityPerCirculation * cuttingType.price.get(0).getPrice();
        } else if (cutQuantityPerCirculation > price.get(0).getCirculation() && cutQuantityPerCirculation <= price.get(1).getCirculation()) {
            return cutQuantityPerCirculation * cuttingType.price.get(1).getPrice();
        } else if (cutQuantityPerCirculation > price.get(1).getCirculation() && cutQuantityPerCirculation <= price.get(2).getCirculation()) {
            return cutQuantityPerCirculation * cuttingType.price.get(2).getPrice();
        } else if (cutQuantityPerCirculation > price.get(2).getCirculation() && cutQuantityPerCirculation <= price.get(3).getCirculation()) {
            return cutQuantityPerCirculation * cuttingType.price.get(3).getPrice();
        } else {
            return cutQuantityPerCirculation * cuttingType.price.get(4).getPrice();
        }
    }

    public double calcPrintPrice(double quantitySheetsPerCirculation, Material material) {
        List<Price> price = material.getPrice();

        if (quantitySheetsPerCirculation < price.get(0).getCirculation()) {
            return quantitySheetsPerCirculation * price.get(0).getPrice();
        } else if (quantitySheetsPerCirculation < price.get(1).getCirculation()) {
            return quantitySheetsPerCirculation * price.get(1).getPrice();
        } else if (quantitySheetsPerCirculation < price.get(2).getCirculation()) {
            return quantitySheetsPerCirculation * price.get(2).getPrice();
        } else if (quantitySheetsPerCirculation < price.get(3).getCirculation()) {
            return quantitySheetsPerCirculation * price.get(3).getPrice();
        } else {
            return quantitySheetsPerCirculation * price.get(4).getPrice();
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
