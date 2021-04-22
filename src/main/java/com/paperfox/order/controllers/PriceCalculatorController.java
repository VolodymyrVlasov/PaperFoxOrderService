package com.paperfox.order.controllers;

import com.paperfox.order.models.params.CalculatorParams;
import com.paperfox.order.models.products.PrintingProduct;
import com.paperfox.order.models.types.CuttingType;
import com.paperfox.order.models.types.MaterialType;
import com.paperfox.order.services.PriceCalculatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping(value = "/api/stickers/getInitialParams")
    private CalculatorParams getInitialParams(@RequestParam String type) {
//        System.out.println(type);
        Map<String,String> materialType = new HashMap<>();
        Map<String,String> cuttingType = new HashMap<>();

        for (MaterialType e : MaterialType.values()) {
            materialType.put(e.name(), e.name);
        }

        for (CuttingType e : CuttingType.values()) {
            cuttingType.put(e.name(), e.name());
        }

        System.out.println(materialType);

        return new CalculatorParams(materialType, cuttingType);
    }
}
