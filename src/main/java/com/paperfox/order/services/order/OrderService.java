package com.paperfox.order.services.order;

import com.paperfox.order.models.Order;
import com.paperfox.order.repositories.calculator.FakeRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService implements IOrderService {

    @Override
    public Order create(Order order) {
        return FakeRepository.createOrder(order);
    }

    @Override
    public Order edit(Order order) {
        return order;

    }
}
