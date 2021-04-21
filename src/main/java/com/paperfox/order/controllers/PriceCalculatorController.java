package com.paperfox.order.controllers;

import com.paperfox.order.models.products.PrintingProduct;
import com.paperfox.order.services.PriceCalculatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PriceCalculatorController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private PriceCalculatorService calculatorService;

    @RequestMapping(value = "/api/calc", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    private PrintingProduct calcProduct(@RequestBody PrintingProduct product) {
        logger.info("Create price calculation request: " + product);
        return calculatorService.calculate(product);
    }
}
