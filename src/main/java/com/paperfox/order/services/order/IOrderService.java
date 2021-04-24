package com.paperfox.order.services.order;

import com.paperfox.order.models.Order;

public interface IOrderService {

    void create(Order order);

    void edit(Order order);


}
