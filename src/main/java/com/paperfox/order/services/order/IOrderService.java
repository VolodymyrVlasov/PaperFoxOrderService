package com.paperfox.order.services.order;

import com.paperfox.order.models.Order;

public interface IOrderService {

    Order create(Order order);

    Order edit(Order order);


}
