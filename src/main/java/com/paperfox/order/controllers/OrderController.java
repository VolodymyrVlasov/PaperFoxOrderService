package com.paperfox.order.controllers;

import com.paperfox.order.models.Order;
import com.paperfox.order.services.order.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private final List<Order> orderList = new ArrayList<>();

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/api/order", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    private void createOrder(@RequestBody Order order) {
        logger.info("Create Order: " + order.toString());
        this.orderList.add(order);
    }

    @GetMapping("/api/order")
    public List<Order> getOrder() {
        return this.orderList;
    }
}

