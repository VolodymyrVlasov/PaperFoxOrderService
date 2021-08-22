package com.paperfox.order.fakeDB;

import com.paperfox.order.models.Order;
import com.paperfox.order.models.types.OrderStatusType;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OrderListDB {
    public static Map<UUID, Order> orderList = new HashMap<>();

    public static Order createOrder(Order order) {
        UUID orderUUID = UUID.randomUUID();
        Integer orderId = OrderId.getOrderId();
        GregorianCalendar timeStamp = new GregorianCalendar();
        order.setOrderUUID(orderUUID);
        order.setOrderID(orderId);
        order.setTimeStamp(timeStamp);
        order.setOrderStatusType(OrderStatusType.NEW);
        orderList.put(orderUUID, order);
        Order newOrder = orderList.get(orderUUID);
        System.out.println("order was write to database:");
        System.out.println(newOrder);
        return newOrder;
    }
}
