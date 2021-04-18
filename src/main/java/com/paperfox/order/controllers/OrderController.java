package com.paperfox.order.controllers;

import com.paperfox.order.models.*;
import org.json.JSONException;
import org.json.JSONObject;
import com.paperfox.order.services.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.GregorianCalendar;


@RestController
//@RequestMapping("/api/order")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @CrossOrigin(value = "http://localhost:53532")
    @RequestMapping(value = "/api/order", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    private void createOrder(@RequestBody Order order) throws JSONException {
//        JSONObject jsonObject = new JSONObject(order);
//        Order order1 = new Order(
//                new Customer(
//                        jsonObject.get("name").toString(),
//                        jsonObject.get("lastName").toString(),
//                        jsonObject.get("phone").toString(),
//                        jsonObject.get("email").toString()),
//                DeliveryMethodType.valueOf(jsonObject.get("deliveryMethodType").toString()),
//                PaymentMethodType.valueOf(jsonObject.get("paymentMethodType").toString()),
//                OrderStatusType.NEW,
//                new GregorianCalendar()
//        );

        logger.info("Create Order: " + order.toString());
    }
}
