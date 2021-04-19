package com.paperfox.order.controllers;

import com.paperfox.order.models.Order;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PriceCalculatorController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @RequestMapping(value = "/api/calc", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    private String createOrder(@RequestBody Order order) {
        logger.info("Create price calculation request: " );
        return "TEST";
    }
}
