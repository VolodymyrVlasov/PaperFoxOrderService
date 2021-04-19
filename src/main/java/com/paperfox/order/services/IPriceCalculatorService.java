package com.paperfox.order.services;

import com.paperfox.order.models.Order;

public interface IPriceCalculatorService {
    int perSheet = 0;
    int sheetsQuantity = 0;
    double cutQuantity = 0;
    double printPrice = 0;
    double cutPrice = 0;
    double totalPrice = 0;

    void calculate();



}
