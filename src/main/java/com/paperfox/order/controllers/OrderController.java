package com.paperfox.order.controllers;

import com.paperfox.order.models.Order;
import com.paperfox.order.services.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController("/api/order")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @PostMapping
//    @CrossOrigin("http://127.0.0.1:53532/")
    private void createOrder(@RequestBody Order order) {
        logger.info("Create Order: " + order.toString());
//        orderService.create(order);
    }
}
