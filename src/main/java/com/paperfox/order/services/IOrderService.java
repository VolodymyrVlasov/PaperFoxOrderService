package com.paperfox.order.services;

import com.paperfox.order.models.Order;

public interface IOrderService {

    void create(Order order);

    void edit(Order order);


}
