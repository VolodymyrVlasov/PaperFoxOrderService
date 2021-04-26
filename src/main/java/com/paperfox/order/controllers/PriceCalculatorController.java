package com.paperfox.order.controllers;

import com.paperfox.order.models.params.CalculatorParams;
import com.paperfox.order.models.products.PrintingProduct;
import com.paperfox.order.services.calculator.PriceCalculatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class PriceCalculatorController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private PriceCalculatorService calculatorService;

    @RequestMapping(value = "/api/calc", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    private PrintingProduct calcProduct(@RequestBody PrintingProduct product) throws Exception {
        logger.info("Create price calculation request: " + product);
        return calculatorService.calculate(product);
    }

    @GetMapping(value = "/api/getRenderParams")
    private CalculatorParams getRenderParams(@RequestParam String type) throws Exception {
        return calculatorService.getRenderParamsByType(type);
    }
}
