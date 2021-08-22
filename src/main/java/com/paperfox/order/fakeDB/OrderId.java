package com.paperfox.order.fakeDB;

public class OrderId {
    public static Integer orderId = 100;

    public static Integer getOrderId() {
        return ++orderId;
    }
}
